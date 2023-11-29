package com.sa.dao.impl;

import java.util.List;

import com.sa.dao.UserDao;
import com.sa.domain.User;
import com.sa.util.CriteriaUserUtils;
import com.sa.web.CriteriaUser;
import com.sa.web.Page;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public User getUser(String id) {
		String sql = "SELECT id,name,college,major,classes,password,imgPath FROM "
				+ "user_info WHERE id = ?";
		return query(sql, id);
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO user_info(id,name,college,major,classes,password,imgpath) "
				+ "VALUES(?,?,?,?,?,?,?)";
		update(sql, user.getId(), user.getName(),user.getCollege(),user.getMajor(),user.getClasses(),
				user.getId(),user.getImgPath());
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM user_info WHERE id = ?";
		update(sql, id);
	}

	@Override
	public void update(User user) {
		String sql = "UPDATE user_info SET name = ?,college = ?,major = ?,classes = ?,imgPath = ?"
				+ ",password = ? WHERE id = ?";
		update(sql,user.getName(),user.getCollege(),user.getMajor(),user.getClasses(),user.getImgPath()
				,user.getPassword(),user.getId());
	}

	@Override
	public Page<User> getPage(CriteriaUser cu) {
		Page page = new Page<>(cu.getPageNo());
		page.setTotalItemNumber((int)getTotalUserNumber(cu));
		//校验不合法的 pageNo
		cu.setPageNo(page.getPageNo());
//		System.out.println(cu.getPageNo());
		page.setList(getUserPageList(cu, 10));
		return page;
	}

	@Override
	public long getTotalUserNumber(CriteriaUser cu) {
		String sql = "SELECT count(*) FROM user_info WHERE"+CriteriaUserUtils.getUserFilter(cu)
					.toString();
		return getSingleVal(sql);
	}

	@Override
	public List<User> getUserPageList(CriteriaUser cu, int pageSize) {
		String sql = "SELECT id,name,college,major,classes,password FROM user_info WHERE"+
					CriteriaUserUtils.getUserFilter(cu).toString()+" LIMIT ?,?";
//		System.out.println(sql);
		return queryForList(sql, (cu.getPageNo()-1)*pageSize,pageSize);
	}

	@Override
	public void changePwd(String userId, String newPwd) {
		String sql = "UPDATE user_info SET password = ? WHERE id = ?";
		update(sql,newPwd,userId);
	}

}
