package com.sa.domain;

import java.sql.Date;

public class BookBorrow {
	private Integer id;
	private String userId;
	private String name;
	private String classes;
	private Date borTime;
	private Date retTime;
	private Date relTime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String userName) {
		this.name = userName;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public Date getBorTime() {
		return borTime;
	}
	public void setBorTime(Date borTime) {
		this.borTime = borTime;
	}
	public Date getRetTime() {
		return retTime;
	}
	public void setRetTime(Date retTime) {
		this.retTime = retTime;
	}
	public Date getRelTime() {
		return relTime;
	}
	public void setRelTime(Date relTime) {
		this.relTime = relTime;
	}
	public BookBorrow(Integer id, String userId, String userName, String classes, Date broTime, Date retTime,
			Date relTime) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = userName;
		this.classes = classes;
		this.borTime = broTime;
		this.retTime = retTime;
		this.relTime = relTime;
	}
	public BookBorrow() {
	}
	@Override
	public String toString() {
		return "BookBorrow[id="+id+"]";
	}
}
