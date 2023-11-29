package com.sa.dao.impl;

import java.util.List;

import com.sa.dao.AjaxDao;
import com.sa.domain.AjaxFormData;

public class AjaxDaoImpl extends BaseDao<AjaxFormData> implements AjaxDao {

	@Override
	public List<AjaxFormData> getCollege() {
		String sql = "SELECT DISTINCT college FROM user_info";
		return queryForList(sql);
	}

	@Override
	public List<AjaxFormData> getMajor(String college) {
		String sql = "SELECT DISTINCT major FROM user_info WHERE college = ?";
		return queryForList(sql, college);
	}

	@Override
	public List<AjaxFormData> getClasses(String major) {
		String sql = "SELECT DISTINCT classes FROM user_info WHERE major = ?";
		return queryForList(sql, major);
	}

	@Override
	public void testCommit2git() {
		// TODO Auto-generated method stub
		
	}

	 


}
