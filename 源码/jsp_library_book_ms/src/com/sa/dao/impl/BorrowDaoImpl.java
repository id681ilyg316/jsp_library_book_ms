package com.sa.dao.impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.sa.dao.BorrowDao;
import com.sa.domain.Book;
import com.sa.domain.Borrow;

public class BorrowDaoImpl extends BaseDao<Borrow> implements BorrowDao{

	@Override
	public List<Borrow> getBorrowRecord(String id) {
		
		String sql = "SELECT b.id,userId,name,b.bookId,bookName,borTime,retTime,"
		+ "relTime,overFine FROM borrow_info b left join user_info u on b.userId = u.id "
		+ "left join book_info bk ON b.bookid = bk.bookid WHERE u.id = ? ORDER BY borTime DESC";
		List<Borrow> list = queryForList(sql,id);
		for(Borrow borrow:list) {
 			update("UPDATE borrow_info set overfine = ( select to_days(now()) - to_days('"+borrow.getRetTime()+"'))*0.1 WHERE id="+borrow.getId());
 			
		}
		return queryForList(sql,id);
	}

	@Override
	public long getCurBorrowCount(String userId) {
		String sql = "SELECT COUNT(*) FROM borrow_info WHERE userid = ? AND relTime is null";
		return getSingleVal(sql, userId);
	}
	
	@Override
	public void borrowBook(String userId,Collection<Book> books) {
		String sql = "INSERT INTO borrow_info(userId,bookId,borTime,retTime)"
				+ " VALUES(?,?,?,?);";
		Object[][] params = new Object[books.size()][4];
		List<Book> bookItems = new ArrayList(books);
		//获取两个月后的时间
		Date two_Month_later = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, 60);
		two_Month_later = new Date(c.getTime().getTime());
		//时间处理
		for(int i = 0;i<bookItems.size();i++) {
			params[i][0] = userId;
			params[i][1] = bookItems.get(i).getBookId();
			params[i][2] = new Date(new java.util.Date().getTime());
			params[i][3] = two_Month_later;
			
		}
		batch(sql, params);
	}

	@Override
	public long overBookCount(String userId) {
		String sql = "SELECT b.id,userId,name,b.bookId,bookName,borTime,retTime,"
				+ "relTime,overFine FROM borrow_info b left join user_info u on b.userId = u.id "
				+ "left join book_info bk ON b.bookid = bk.bookid WHERE u.id = ? ORDER BY borTime DESC";
				List<Borrow> list = queryForList(sql,userId);
				for(Borrow borrow:list) {
		 			update("UPDATE borrow_info set overfine = ( select to_days(now()) - to_days('"+borrow.getRetTime()+"'))*0.1 WHERE id="+borrow.getId());
		 			
				}
		sql = "select count(*) from borrow_info WHERE overfine > 5"
				+ " AND userId = ?;";
		return getSingleVal(sql, userId);
	}

	@Override
	public long hasBook(String bookId) {
		String sql = "SELECT COUNT(*) FROM book_info WHERE aviQuantity > 0 AND bookId = ?";
		return getSingleVal(sql, bookId);
	}

	@Override
	public void updateBookQuantityForBorrow(Collection<Book> books) {
		String sql = "UPDATE book_info SET aviQuantity = aviQuantity - 1 WHERE bookId = ?";
		Object[][] params = new Object[books.size()][1];
		List<Book> bookItems = new ArrayList<Book>(books);
		for(int i = 0;i<bookItems.size();i++) {
			params[i][0] = bookItems.get(i).getBookId();
		}
		batch(sql, params);
	}

	@Override
	public long isAlreadyBorrow(String userId, String bookId) {
		String sql = "SELECT COUNT(*) FROM borrow_info WHERE userId = ? AND bookId = ? AND relTime is NULL";
		return getSingleVal(sql, userId,bookId);
	}
}
