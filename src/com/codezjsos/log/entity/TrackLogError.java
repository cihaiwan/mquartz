package com.codezjsos.log.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tracklog_error")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TrackLogError extends TrackBase{

	private String errormsg;
	private String remark;
	
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
