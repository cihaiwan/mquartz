package com.codezjsos.log.entity;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name="tracklog_entity")
public class TrackLogEntity extends TrackBase{


	private String optiontype;
	private String createid;
	private String classname;
	
	
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getOptiontype() {
		return optiontype;
	}
	public void setOptiontype(String optiontype) {
		this.optiontype = optiontype;
	}
	
	
}
