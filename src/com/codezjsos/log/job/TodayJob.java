package com.codezjsos.log.job;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import com.codezjsos.base.ICommonService;
import com.codezjsos.log.service.IHistoryLogService;

@Service
public class TodayJob {
	@Resource
	private IHistoryLogService historyLogService;
	
	
	public void execute(){
		
		try {
			String today=DateFormatUtils.format(new Date(),"yyyy-MM-dd");
			Long todayL = DateUtils.parseDate(today, "yyyy-MM-dd").getTime()+86400*1000;
			historyLogService.parseAllHistory(new Date(todayL));
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}
}
