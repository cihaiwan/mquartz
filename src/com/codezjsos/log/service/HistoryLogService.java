package com.codezjsos.log.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.stereotype.Service;

import com.codezjsos.base.ICommonService;
import com.codezjsos.base.impl.CommonService;
import com.codezjsos.base.utils.MessageFactory;
import com.codezjsos.base.utils.XmlUtils;
import com.codezjsos.log.entity.HistoryLogStatistics;
import com.codezjsos.log.entity.HistoryLogStatisticsDto;

@Service
public class HistoryLogService implements IHistoryLogService{
	 public static String CONFIG="com/codezjsos/log/config/config.xml";
	
	public static Logger logger = LoggerFactory.getLogger(CommonService.class);
	@Resource
	private ICommonService commonLogService;
	@Resource
	private ICommonService commonService;

	 public  Map<String,String> tableBloodMap=null;//blood==systemName
	 public  Map<String,String> tableBlood2Map=null;//blood=module
	 public  Map<String,List<String>> commentBloods=null;
	 public Map<String,Map<String,Object>> bloodModels=null;
	 @PostConstruct
	 public void init() throws Exception{
		 tableBloodMap=new LinkedHashMap<String, String>();//blood==systemName
		 tableBlood2Map=new LinkedHashMap<String, String>();//blood=module
		 commentBloods=new LinkedHashMap<String, List<String>>();
		 bloodModels=new HashMap<String, Map<String,Object>>();
		 List<Map<String, Object>> list=commonService.getJdbcTemplate().query(XmlUtils.readText(CONFIG, "blooddic")+XmlUtils.readAttrForMap(CONFIG, "blooddic").get("order"), new ColumnMapRowMapper());
			for(Map<String,Object> m:list){
				String bloodkinshipid=m.get("bloodkinshipid").toString();
				String comment=m.get("comment")==null?"":m.get("comment").toString();
				String moduleComment=m.get("moduleComment")==null?"":m.get("moduleComment").toString();
				if(StringUtils.isEmpty(comment)){
					logger.error(bloodkinshipid +" do not define systemName");
					continue;
				}
				tableBloodMap.put(bloodkinshipid,comment);
				
				
				List<String> bloods=commentBloods.get(comment);
				if(bloods==null){
					bloods=new ArrayList<String>();
					commentBloods.put(comment, bloods);
				}
				bloods.add(bloodkinshipid);
				
				tableBlood2Map.put(bloodkinshipid,moduleComment );
			}
		List<Map<String, Object>> list2=commonService.getJdbcTemplate().query("select * from z_bloodkinship ", new ColumnMapRowMapper());
		
		for(Map<String,Object> m:list2){
			bloodModels.put(m.get("unid").toString(),m);
		}

	 }
	
	public  void parseAllHistory(Date date) throws Exception{
		String today=DateFormatUtils.format(date,"yyyy-MM-dd");
		Long todayL=DateUtils.parseDate(today, "yyyy-MM-dd").getTime()-(86400*1000);
		commonLogService.getJdbcTemplate().update("delete from history_log_statistics where createtime=?",new Date(todayL));
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("today",new Date(todayL));
		String hql=commonService.packageSQL(CONFIG, "queryLog",params);	
		List<Map<String,Object>> list=commonLogService.findAll(hql, params);
		Map<String,HistoryLogStatistics> map=new HashMap<String,HistoryLogStatistics>();
		for(Map<String,Object> m:list){
			HistoryLogStatistics historyLogStatistics=map.get(m.get("bloodKinshipId"));
			if(historyLogStatistics==null){
				historyLogStatistics=new HistoryLogStatistics();
				if(m.get("bloodKinshipId")==null){
					logger.error("血缘未加");
				}
				try {
					map.put(m.get("bloodKinshipId").toString(), historyLogStatistics);
					historyLogStatistics.setBloodKinshipId(m.get("bloodKinshipId").toString());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String saveType=m.get("saveType").toString()+"celday";
			PropertyUtils.setProperty(historyLogStatistics, saveType, (Long)m.get("count"));
		}
		for(HistoryLogStatistics  historyLogStatistics:map.values()){
			historyLogStatistics.setCreatetime(new Date(todayL));
			commonLogService.save(historyLogStatistics);
		}
	}
	
	public List<HistoryLogStatistics> queryAllLog(Map<String, Object> params,Date start,Date end) throws Exception {
		params.put("start", start);
		params.put("end", end);
		if(start==null&&end==null){
			params.put("history", DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd")+" 23:59:59", "yyyy-MM-dd HH:mm:ss"));
		}
		
		return getQueryDayRecordedCommon(params,"queryDayRecordedForCreatetime");
		
	}
	
	private List<HistoryLogStatistics> getQueryDayRecordedCommon(Map<String,Object>params,String queryid) throws Exception{
		List<HistoryLogStatistics> map=new ArrayList<HistoryLogStatistics>();
		String hql=commonService.packageSQL(CONFIG, queryid,params);	
		List<Map<String,Object>> list2=commonLogService.findAll(hql, params);
		
		for(Map<String,Object> m:list2){
			HistoryLogStatistics historyLogStatistics=new HistoryLogStatistics();
			BeanUtils.populate(historyLogStatistics,m);
			historyLogStatistics.addAllcel();
			map.add( historyLogStatistics);
		}
		return map;
	}
	

	
	private Date getYestoday(){
		try {
			return new Date(DateUtils.parseDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd"),"yyyy-MM-dd").getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	
	private HistoryLogStatistics totalLog(Map<String, Object> params) throws Exception{
		String hql=commonService.packageSQL(CONFIG, "queryAllRecorded",params);	
		Map<String,Object> map=commonLogService.findOne(hql, params);
		HistoryLogStatistics historyLogStatistics=new HistoryLogStatistics();
		BeanUtils.populate(historyLogStatistics, map);
		historyLogStatistics.addAllcel();
		return historyLogStatistics;
	}
	/*
	*
	*今天以前的数据
	 */
	public HistoryLogStatistics historyLog(Map<String, Object> params) throws Exception {
		//params.put("history", getYestoday());
		return totalLog(params);
	}
	@Override
	public HistoryLogStatistics todayLog(Map<String, Object> params)
			throws Exception {
		params.put("today",new Date());
		return totalLog(params);
	}
	 
	private Map<String,Object> historyAndToday() throws Exception{
		return MessageFactory.create().put("history",historyLog(new HashMap<String,Object>())).put("today",todayLog(new HashMap<String,Object>())).build();
	}
	
	private Map<String,HistoryLogStatistics> calcSomeSystem(List<HistoryLogStatistics> map){
		Map<String,HistoryLogStatistics> list=new LinkedHashMap<String,HistoryLogStatistics>();
		for(String key:new LinkedHashSet<String>(tableBloodMap.values())){
			HistoryLogStatistics historyLogStatistics=new HistoryLogStatistics();
			historyLogStatistics.setComment(key);
			list.put(key,historyLogStatistics);
		}
		for(HistoryLogStatistics hs:map){
				String comment=tableBloodMap.get(hs.getBloodKinshipId());
				HistoryLogStatistics historyLogStatistics=list.get(comment);
				historyLogStatistics.addEachcel(hs);
		}
		return list;
	}
	private HistoryLogStatistics isExist(Map<String,HistoryLogStatistics> data ,String key){
		HistoryLogStatistics historyLogStatistics=data.get(key);
		if(historyLogStatistics==null){
			historyLogStatistics=new HistoryLogStatistics();
		}
		return historyLogStatistics;
		
	}
	public  Map<String,Object> todayBloodLog(Map<String, Object> params)
			throws Exception {
		Map<String,Object> params2=new HashMap<String, Object>();
		params2.putAll(params);
		params.put("today",new Date());
		List<HistoryLogStatistics> map=getQueryDayRecordedCommon(params,"queryDayRecorded");
		List<HistoryLogStatistics> map2=getQueryDayRecordedCommon(params2,"queryDayRecorded");
		Map<String,HistoryLogStatistics> today=calcSomeSystem(map);
		Map<String,HistoryLogStatistics> history=calcSomeSystem(map2);
		Set<String> set=new LinkedHashSet<String>(tableBloodMap.values());
		List<HistoryLogStatistics> list=new ArrayList<HistoryLogStatistics>();
		for(String key :set){
			HistoryLogStatistics historyLogStatistics=isExist(today, key);
			HistoryLogStatistics historyLogStatistics2= isExist(history, key);
			HistoryLogStatisticsDto historyLogStatisticsDto=new HistoryLogStatisticsDto();
			BeanUtils.copyProperties(historyLogStatisticsDto, historyLogStatistics);
//			historyLogStatisticsDto.setComment(key);
			historyLogStatisticsDto.addTotal(historyLogStatistics2);
			historyLogStatisticsDto.addAllcelSpec();
			list.add(historyLogStatisticsDto);
		}
		
//		for(HistoryLogStatistics hs:list.values()){
//			hs.addAllcel();
//		}
		return MessageFactory.create().put("todayBloods", list).putAll(historyAndToday()).build();
	}

	/*
     * 参数comment  某个系统每日详情
     */
	public  Map<String,Object> dayDetail(Map<String, Object> params) throws Exception {
//		StopWatch sw=new StopWatch();
//		sw.start();
		System.out.println("------------------------");
		String comment=params.get("comment")==null?"":params.get("comment").toString();
		if(StringUtils.isEmpty(comment)){
			logger.error("comment"+"do not set");
			return MessageFactory.create().put("msg", comment+":has no this comment").build();
		}
		List<String> list=commentBloods.get(comment);
		if(list==null){
			list=new ArrayList<String>();
		}
		params.put("bloods", list);
		List<HistoryLogStatistics> historyLogStatistics=getQueryDayRecordedCommon(params,"queryDayRecorded");//历史所有
		Map<String,Object> params2=new HashMap<String, Object>();
//		BeanUtils.copyProperties(params2, params);
		params2.putAll(params);
		params2.put("today",new Date());
		List<HistoryLogStatistics> todayHistoryLogStatistics=getQueryDayRecordedCommon(params2,"queryDayRecorded");//今天
		
		/****************历史总和和当天总和********************/
		Map<String,HistoryLogStatistics> all=new LinkedHashMap<String, HistoryLogStatistics>();
		
		for(HistoryLogStatistics hs:todayHistoryLogStatistics){
			HistoryLogStatisticsDto hhs=new HistoryLogStatisticsDto();
			BeanUtils.copyProperties(hhs, hs);
			all.put(hhs.getBloodKinshipId(), hhs);	
		}
		for(HistoryLogStatistics hs:historyLogStatistics){
			HistoryLogStatisticsDto hhs=(HistoryLogStatisticsDto)all.get(hs.getBloodKinshipId());
			if(hhs==null){
				hhs=new HistoryLogStatisticsDto();
				all.put(hhs.getBloodKinshipId(),hs);
			}
			hhs.addTotal(hs);	
		}
		
		/**********************今日详情****************************/
		Set<String> haveDate=new LinkedHashSet<String>(all.keySet());
		List<HistoryLogStatisticsDto> historyLogStatisticsDtos=new ArrayList<HistoryLogStatisticsDto>();
		for(String blood:list){
			HistoryLogStatisticsDto hs=(HistoryLogStatisticsDto)all.get(blood);
			if(!haveDate.contains(blood)){
				 hs=new HistoryLogStatisticsDto();//补齐在日志表中的数据
			}
			hs.setComment(tableBlood2Map.get(blood));
			hs.setBloodModel(bloodModels.get(blood));
			hs.addAllcelSpec();
			historyLogStatisticsDtos.add(hs);
		}
		
		/********************今日某个系统的总和**********************/
		Map<String,Object> params3=new HashMap<String, Object>();
		params3.putAll(params2);
		Map<String,Object> params4=new HashMap<String, Object>();
		params4.putAll(params);
		HistoryLogStatistics historyLogStatistics2=todayLog(params3);
		HistoryLogStatistics historyLogStatistics3=historyLog(params4);
		HistoryLogStatisticsDto historyLogStatisticsDto=new HistoryLogStatisticsDto();
		BeanUtils.copyProperties(historyLogStatisticsDto,historyLogStatistics2);
		historyLogStatisticsDto.addTotal(historyLogStatistics3);
		historyLogStatisticsDto.addAllcelSpec();
//		sw.stop();
//		System.out.println(sw.getTotalTimeSeconds());
		return MessageFactory.create().putAll(historyAndToday()).put("business",MessageFactory.create().put("businessBlood",historyLogStatisticsDto ).put("bloods",historyLogStatisticsDtos).build()).build();
	}
	
}
