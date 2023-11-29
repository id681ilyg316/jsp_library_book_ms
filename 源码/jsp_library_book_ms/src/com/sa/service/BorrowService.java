package com.sa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sa.dao.BorrowDao;
import com.sa.dao.impl.BorrowDaoImpl;
import com.sa.domain.Book;
import com.sa.domain.Borrow;

public class BorrowService {
	private BorrowDao borrowDao = new BorrowDaoImpl();
	public List<Borrow> getUserBorrowRec(String id){
		return borrowDao.getBorrowRecord(id);
	}
	public int getCurBorrowCount(String userId) {
		return (int) borrowDao.getCurBorrowCount(userId);
	}
	public String borrowBook(String userId,Collection<Book> books) {
		StringBuffer msg = new StringBuffer();
		//1.是否存在超期图书
		int overBookCount = (int) overBook(userId);
		if(overBookCount > 0) {
			//若存在超期图书，则退出借阅，并且给出提示
			msg.append("您存在"+overBookCount+"本超期图书，当前不能继续借阅。");
			return msg.toString();
		}
		//2. 所借阅图书，馆藏是否可借
		msg.append(bookQuantityChecked(books));
		//3.将借阅信息插入 borrow_info 表中
		borrowDao.borrowBook(userId, books);
		//4.将图书对应的可借数减一
		borrowDao.updateBookQuantityForBorrow(books);
		return msg.toString();
	}
	
	public long overBook(String userId) {
		return borrowDao.overBookCount(userId);
	}
	
	public String bookQuantityChecked(Collection<Book> books) {
		List<Book> allBooks = new ArrayList<Book>(books);
		StringBuffer msg = new StringBuffer();
		boolean flag = true;
		for(int i = 0;i<allBooks.size();i++) {
			if(borrowDao.hasBook(allBooks.get(i).getBookId()) <= 0) {
				msg.append(allBooks.get(i).getBookName()+",");
				allBooks.remove(i);
				flag = false;
			}
		}
		if(!flag)
			msg.append("尚在借阅，馆藏不足！");
		return msg.toString();
	}
	
	public long isAlreadyBorrow(String userId,String bookId) {
		return borrowDao.isAlreadyBorrow(userId, bookId);
	}
}
