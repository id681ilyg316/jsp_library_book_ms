package com.sa.dao;

import java.util.List;

import com.sa.domain.User;
import com.sa.web.CriteriaUser;
import com.sa.web.Page;

public interface UserDao {
	/**
	 * ���� id ���ض�Ӧ�� User ��Ϣ
	 * @param id
	 * @return
	 */
	public abstract User getUser(String id);
	
	/**
	 * ��� User ��Ϣ
	 * ����Ա¼��ʱ, ����Ĭ�� user.idһ��
	 * @param id
	 * @param name
	 * @param college
	 * @param major
	 * @param classes
	 */
	public abstract void insert(User user);
	
	/**
	 * ���� id ɾ����Ӧ�� user ��Ϣ
	 * @param id
	 */
	public abstract void delete(String id);
	
	/**
	 * �޸�ѧ����Ϣ
	 * @param user
	 */
	public abstract void update(User user);
	
	/**
	 * ���ݴ���� CriteriaUser ���󷵻ض�Ӧ�� Page ����
	 * @param ub
	 * @return
	 */
	public abstract Page<User> getPage(CriteriaUser cu);
	
	/**
	 * ���ݴ���� CriteriaUser ���󷵻����Ӧ�ļ�¼��
	 * @param cb
	 * @return
	 */
	public abstract long getTotalUserNumber(CriteriaUser cu);
	
	/**
	 * ���ݴ���� CriteriaUser �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param ub
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<User> getUserPageList(CriteriaUser cu,int pageSize);
	
	public void changePwd(String userId,String newPwd);
	
	
}
