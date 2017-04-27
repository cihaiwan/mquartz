package com.codezjsos.log.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="history_log_statistics")
public class HistoryLogStatistics {

	private String unid;
	
	protected Date createtime;//最后一次更新时间
	
	protected Long collectcelday=0L;//采集
	
	protected Long ktcelday=0L;//kt
	
	protected Long importcelday=0L;//导入
	
	protected Long reportcelday=0L ;//上报
	
	protected String bloodKinshipId;//血缘ID
	
	protected Long allcel=0L;
	
	protected Long allInOut=0L;
	
	protected String comment;

	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(strategy="uuid",name="uuid")
	public String getUnid() {
		return unid;
	}

	public void setUnid(String unid) {
		this.unid = unid;
	}

	
	@Transient
	public Long getAllcel() {
		return allcel;
	}

	public void setAllcel(Long allcel) {
		this.allcel = allcel;
	}

	public void addAllcel(){
		this.allcel=this.collectcelday+this.importcelday+this.reportcelday;
		this.allInOut=this.allcel+ktcelday;
	}
	public void addEachcel(HistoryLogStatistics historyLogStatistics){
		this.collectcelday+=historyLogStatistics.getCollectcelday();
		this.importcelday+=historyLogStatistics.getImportcelday();
		this.reportcelday+=historyLogStatistics.getReportcelday();
		this.ktcelday+=historyLogStatistics.getKtcelday();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getBloodKinshipId() {
		return bloodKinshipId;
	}

	public void setBloodKinshipId(String bloodKinshipId) {
		this.bloodKinshipId = bloodKinshipId;
	}

	public Long getCollectcelday() {
		return collectcelday;
	}

	public void setCollectcelday(Long collectcelday) {
		this.collectcelday = collectcelday;
	}

	public Long getKtcelday() {
		return ktcelday;
	}

	public void setKtcelday(Long ktcelday) {
		this.ktcelday = ktcelday;
	}

	public Long getImportcelday() {
		return importcelday;
	}

	public void setImportcelday(Long importcelday) {
		this.importcelday = importcelday;
	}

	public Long getReportcelday() {
		return reportcelday;
	}

	public void setReportcelday(Long reportcelday) {
		this.reportcelday = reportcelday;
	}

	@Transient
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	@Transient
	public Long getAllInOut() {
		return allInOut;
	}

	public void setAllInOut(Long allInOut) {
		this.allInOut = allInOut;
	}
	
	
	
	
}
