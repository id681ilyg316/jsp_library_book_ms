package com.sa.dao;

import com.sa.domain.System;

public interface SystemDao {
	public abstract System getAdmin(String id);
	public void changePwd(String id,String newPwd);
}
