package com.codezjsos.log.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tracklog_kt_entity")
public class TrackLogKtEntity extends TrackBase{

	
	private String optiontype;//产生类型 save or update
	private String createid;//目标表产生的id
	private String sourceTableName;//原表名称
	private String sourceDataName;//原表数据库名字
	private String sourceAccount;//原表账号
	private String sourceIp;//原表ip
	private String distTableName;//目标表名称
	private String distDataName;//目标表数据库名字
	private String distAccount;//目标表账号
	private String distIp;//目标表ip
	private String bloodKinshipId;//血缘Id
	private String classname;//类名
    private String saveType;//保存类型
	
	
	
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	
	public String getOptiontype() {
		return optiontype;
	}
	public void setOptiontype(String optiontype) {
		this.optiontype = optiontype;
	}
	public String getSourceTableName() {
		return sourceTableName;
	}
	public void setSourceTableName(String sourceTableName) {
		this.sourceTableName = sourceTableName;
	}
	public String getSourceDataName() {
		return sourceDataName;
	}
	public void setSourceDataName(String sourceDataName) {
		this.sourceDataName = sourceDataName;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getDistTableName() {
		return distTableName;
	}
	public void setDistTableName(String distTableName) {
		this.distTableName = distTableName;
	}
	public String getDistDataName() {
		return distDataName;
	}
	public void setDistDataName(String distDataName) {
		this.distDataName = distDataName;
	}
	public String getDistAccount() {
		return distAccount;
	}
	public void setDistAccount(String distAccount) {
		this.distAccount = distAccount;
	}
	public String getDistIp() {
		return distIp;
	}
	public void setDistIp(String distIp) {
		this.distIp = distIp;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getBloodKinshipId() {
		return bloodKinshipId;
	}
	public void setBloodKinshipId(String bloodKinshipId) {
		this.bloodKinshipId = bloodKinshipId;
	}
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	
	
}
