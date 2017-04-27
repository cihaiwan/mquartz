package com.codezjsos.log.entity;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Created by zhufang on 2017/3/7.
 */
@MappedSuperclass
public class TrackBase { 
	protected String unid;
	protected Date createtime;
	
	
	
	@Id
	@GeneratedValue(generator="uuid")
	@GenericGenerator(strategy="uuid",name="uuid")
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}


	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	
}
