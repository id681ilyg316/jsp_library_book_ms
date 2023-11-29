package com.sa.service;

import com.sa.dao.SystemDao;
import com.sa.dao.impl.SystemDaoImpl;
import com.sa.domain.System;

public class SystemService {
	private SystemDao systemDao = new SystemDaoImpl();
	public System getAdmin(String id) {
		return systemDao.getAdmin(id);	
	}
	public void changePwd(String userId,String newPwd) {
		systemDao.changePwd(userId, newPwd);
	}
}
