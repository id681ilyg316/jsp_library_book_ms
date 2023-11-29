package com.sa.domain;

public class User {
	private String id;
	private String name;
	private String college;
	private String major;
	private String classes;
	private String password;
	private String imgPath;
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(String id, String name, String college, String major, String classes, String password,String imgPath) {
		super();
		this.id = id;
		this.name = name;
		this.college = college;
		this.major = major;
		this.classes = classes;
		this.password = password;
		this.imgPath = imgPath;
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User[id"+id+",name"+name+",college"+college+",major="+major+",classes="+
				classes+",password="+password+",imgPath="+imgPath+"]";
	}
}
