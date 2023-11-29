package com.sa.service;

import java.util.List;

import com.sa.dao.AjaxDao;
import com.sa.dao.UserDao;
import com.sa.dao.impl.AjaxDaoImpl;
import com.sa.dao.impl.UserDaoImpl;
import com.sa.domain.AjaxFormData;
import com.sa.domain.User;
import com.sa.web.CriteriaUser;
import com.sa.web.Page;

public class UserService {
	private UserDao userDao = new UserDaoImpl();
	private AjaxDao ajaxDao = new AjaxDaoImpl();
	public Page<User> getUserPage(CriteriaUser cu){
		return userDao.getPage(cu);
	}
	public User getUser(String id) {
		return userDao.getUser(id);
	}
	public void updateUser(User user) {
		userDao.update(user);
	}
	public void changePwd(String userId,String newPwd) {
		userDao.changePwd(userId, newPwd);
	}
	public void addUser(User user) {
		userDao.insert(user);
	}
	public List<AjaxFormData> getColleges(){
		List<AjaxFormData> colleges = ajaxDao.getCollege();
		return colleges;
	}
	public List<AjaxFormData> getMajors(String college){
		return ajaxDao.getMajor(college);
	}
	public List<AjaxFormData> getClasses(String major){
		return ajaxDao.getClasses(major);
	}
}
