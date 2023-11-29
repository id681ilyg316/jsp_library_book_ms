package com.sa.dao;

import java.util.List;

import com.sa.domain.User;
import com.sa.web.CriteriaUser;
import com.sa.web.Page;

public interface UserDao {
	/**
	 * 根据 id 返回对应的 User 信息
	 * @param id
	 * @return
	 */
	public abstract User getUser(String id);
	
	/**
	 * 添加 User 信息
	 * 管理员录入时, 密码默与 user.id一致
	 * @param id
	 * @param name
	 * @param college
	 * @param major
	 * @param classes
	 */
	public abstract void insert(User user);
	
	/**
	 * 根据 id 删除对应的 user 信息
	 * @param id
	 */
	public abstract void delete(String id);
	
	/**
	 * 修改学生信息
	 * @param user
	 */
	public abstract void update(User user);
	
	/**
	 * 根据传入的 CriteriaUser 对象返回对应的 Page 对象
	 * @param ub
	 * @return
	 */
	public abstract Page<User> getPage(CriteriaUser cu);
	
	/**
	 * 根据传入的 CriteriaUser 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalUserNumber(CriteriaUser cu);
	
	/**
	 * 根据传入的 CriteriaUser 和 pageSize 返回当前页对应的 List 
	 * @param ub
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<User> getUserPageList(CriteriaUser cu,int pageSize);
	
	public void changePwd(String userId,String newPwd);
	
	
}
