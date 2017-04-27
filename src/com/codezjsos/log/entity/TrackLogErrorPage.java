package com.codezjsos.log.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name="tracklog_error_page")
public class TrackLogErrorPage extends TrackLogError{

	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
