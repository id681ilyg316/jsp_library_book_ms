package com.sa.domain;

import java.sql.Date;

public class Borrow {
	private Integer id;
	private String userId;
	private String name;
	private String bookName;
	private Integer bookId;
	private Date borTime;
	private Date retTime;
	private Date relTime;
	private Float overFine;
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
	public void setName(String name) {
		this.name = name;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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
	public Float getOverFine() {
		return overFine;
	}
	public void setOverFine(Float overFine) {
		this.overFine = overFine;
	}
	public Borrow(Integer id, String userId, String userName, String bookName, Integer bookId, Date borTime,
			Date retTime, Date relTime, Float overFine) {
		super();
		this.id = id;
		this.userId = userId;
		this.name = userName;
		this.bookName = bookName;
		this.bookId = bookId;
		this.borTime = borTime;
		this.retTime = retTime;
		this.relTime = relTime;
		this.overFine = overFine;
	}
	public Borrow() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Borrow [id="+id+",bookName="+bookName+",name="+name+",userId="+userId+"bookId="+bookId+",bortime="+borTime+",retTime="+relTime+
				",relTime="+relTime+",overFine"+overFine+"]";
	}
}
