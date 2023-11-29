package com.sa.domain;

public class AjaxFormData {
	private String college;
	private String major;
	private String classes;
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public AjaxFormData(String college, String major, String classes) {
		super();
		this.college = college;
		this.major = major;
		this.classes = classes;
	}
	
	public AjaxFormData() {
		// TODO Auto-generated constructor stub
	}
}
