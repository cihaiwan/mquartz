package com.codezjsos.log.job;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.codezjsos.log.service.IHistoryLogService;

@Service
public class HistoryJob {
	@Resource
	private IHistoryLogService historyLogService;
	private int LIMITDAY=5;
	public void execute(){
			
			long today=new Date().getTime();
			for(int i=LIMITDAY;i>=0;i--){
				try {
					historyLogService.parseAllHistory(new Date(today-i*86400*1000));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	
	public static void main(String[] args) {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
}
