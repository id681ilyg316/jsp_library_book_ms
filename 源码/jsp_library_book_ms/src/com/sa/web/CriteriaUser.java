package com.sa.web;

import com.sa.domain.User;

public class CriteriaUser {
	private User user;
	private int pageNo;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public CriteriaUser(User user, int pageNo) {
		super();
		this.user = user;
		this.pageNo = pageNo;
	}
	public CriteriaUser() {
	}
}
