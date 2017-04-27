package com.codezjsos.log.entity;

import java.util.HashMap;
import java.util.Map;

public class HistoryLogStatisticsDto extends HistoryLogStatistics{
	

	private Long collectceldaytotal=0L;//采集
	
	private Long ktceldaytotal=0L;//kt
	
	private Long importceldaytotal=0L;//导入
	

	private Long reportceldaytotal=0L ;//上报
	
	protected Long allceltotal=0L;
	
	protected Long allInOuttotal=0L;
	
	private Map<String,Object> bloodModel=new HashMap<String,Object>();

	public void addTotal(HistoryLogStatistics historyLogStatistics){
		this.collectceldaytotal=historyLogStatistics.getCollectcelday();
		this.ktceldaytotal=historyLogStatistics.getKtcelday();
		this.importceldaytotal=historyLogStatistics.getImportcelday();
		this.reportceldaytotal=historyLogStatistics.getReportcelday();
	}

	public Long getCollectceldaytotal() {
		return collectceldaytotal;
	}

	public void setCollectceldaytotal(Long collectceldaytotal) {
		this.collectceldaytotal = collectceldaytotal;
	}

	public Long getKtceldaytotal() {
		return ktceldaytotal;
	}

	public void setKtceldaytotal(Long ktceldaytotal) {
		this.ktceldaytotal = ktceldaytotal;
	}

	public Long getImportceldaytotal() {
		return importceldaytotal;
	}

	public void setImportceldaytotal(Long importceldaytotal) {
		this.importceldaytotal = importceldaytotal;
	}

	public Long getReportceldaytotal() {
		return reportceldaytotal;
	}

	public void setReportceldaytotal(Long reportceldaytotal) {
		this.reportceldaytotal = reportceldaytotal;
	}

	
	public void addAllcelSpec(){
		addAllcel();
		this.allceltotal=this.collectceldaytotal+this.importceldaytotal+this.reportceldaytotal;
		this.allInOuttotal=this.allceltotal+ktceldaytotal;
	}

	public Map<String, Object> getBloodModel() {
		return bloodModel;
	}

	public void setBloodModel(Map<String, Object> bloodModel) {
		this.bloodModel = bloodModel;
	}

	
	
}
