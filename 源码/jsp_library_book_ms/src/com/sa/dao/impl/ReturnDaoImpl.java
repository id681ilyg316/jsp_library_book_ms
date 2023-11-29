package com.sa.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.sa.dao.ReturnDao;
import com.sa.domain.Book;
import com.sa.domain.Borrow;

public class ReturnDaoImpl extends BaseDao<Borrow> implements ReturnDao {

	@Override
	public long isOverFine(String userId, String bookId) {
		String sql = "select to_days(now()) - to_days(retTime) from borrow_info where"
				+ " userId = ? AND bookId = ? AND relTime is null;";
		long num = getSingleVal(sql, userId,bookId);
		return num;
	}

	@Override
	public void returnBook(String userId, Collection<Book> books,Map<String, Float> overFine) {
		String sql = "UPDATE borrow_info SET relTime = now(),overFine = ? WHERE userId = ? "
				+ "AND bookId = ? AND relTime is null";
		Object[][] params = new Object[books.size()][3];
		List<Book> bookItems = new ArrayList<Book>(books);
		for(int i = 0;i<bookItems.size();i++) {
			params[i][0] = overFine.get(bookItems.get(i).getBookId());
			params[i][1] = userId;
			params[i][2] = bookItems.get(i).getBookId();
		}
		batch(sql, params);
	}

	@Override
	public void updateBookQuantityForReturn(Collection<Book> books) {
		String sql = "UPDATE book_info SET aviQuantity = aviQuantity + 1 WHERE bookId = ?";
		Object[][] params = new Object[books.size()][1];
		List<Book> bookItems = new ArrayList<Book>(books);
		for(int i = 0;i<bookItems.size();i++) {
			params[i][0] = bookItems.get(i).getBookId();
		}
		batch(sql, params);
	}

	@Override
	public long isUserBorrowBook(String userId, String bookId) {
		String sql = "SELECT COUNT(*) FROM borrow_info WHERE userId = ? AND bookId = ? "
				+ "AND relTime is null"; 
		return getSingleVal(sql, userId,bookId);
	}

	@Override
	public void fine(String userid) {
		String sql = "UPDATE borrow_info SET overFine = 0, rettime = now() WHERE userid = ?";
		update(sql, userid);
	}
	
	

}
