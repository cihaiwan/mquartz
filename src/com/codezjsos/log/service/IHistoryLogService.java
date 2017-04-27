package com.codezjsos.log.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.codezjsos.log.entity.HistoryLogStatistics;
import com.codezjsos.log.entity.HistoryLogStatisticsDto;

public interface IHistoryLogService {
	
	//public Map<String,HistoryLogStatistics> queryDayLog(Map<String,Object> params) throws Exception;
	public List<HistoryLogStatistics> queryAllLog(Map<String, Object> params,Date start,Date end) throws Exception ;
	public  void parseAllHistory(Date date) throws Exception ;
	
	
	public HistoryLogStatistics historyLog(Map<String, Object> params) throws Exception ;
	public HistoryLogStatistics todayLog(Map<String, Object> params) throws Exception ;
	public  Map<String,Object>  todayBloodLog(Map<String, Object> params) throws Exception ;
	/*
     * 参数comment  某个系统每日详情
     */
	public Map<String,Object> dayDetail(Map<String, Object> params)throws Exception ;

}
