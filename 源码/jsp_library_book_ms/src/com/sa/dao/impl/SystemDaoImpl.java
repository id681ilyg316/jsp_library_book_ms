package com.sa.dao.impl;

import com.sa.dao.SystemDao;
import com.sa.domain.System;

public class SystemDaoImpl extends BaseDao<System> implements SystemDao {

	@Override
	public System getAdmin(String id) {
		String sql = "SELECT id,name,password FROM sys_info WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public void changePwd(String id, String newPwd) {
		String sql = "UPDATE sys_info SET password = ? WHERE id = ?";
		update(sql, newPwd,id);
	}

}
