package com.sa.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.sa.dao.BookBorrowDao;
import com.sa.domain.Book;
import com.sa.domain.BookBorrow;

public class BookBorrowDaoImpl extends BaseDao<BookBorrow> implements BookBorrowDao {

	@Override
	public List<BookBorrow> getBookBorrow(String bookId) {
		String sql = "SELECT b.id,userId,name,classes,borTime,retTime,relTime FROM user_info u"
				+ " LEFT JOIN borrow_info b ON u.id = b.userId WHERE bookId = ? ORDER BY borTime DESC";
		return queryForList(sql, bookId);
	}

	
	

}
