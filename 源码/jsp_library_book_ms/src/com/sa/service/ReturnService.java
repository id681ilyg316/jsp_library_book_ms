package com.sa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sa.dao.ReturnDao;
import com.sa.dao.impl.ReturnDaoImpl;
import com.sa.domain.Book;

public class ReturnService {
	private ReturnDao returnDao = new ReturnDaoImpl();
	public String returnBook(String userId,Collection<Book> books,float fine,Map<String,Float> overFine) {
		//1. 是否读者在借图书 以及 超期罚款校验,超期罚款结果存入 map中
		String msg = checkOverFineAndIsUserBorrowBook(userId, books, overFine, fine);
		//2.更新borrow_info中的 relTime 及 overFine
		returnDao.returnBook(userId, books, overFine);
		//3.更新 book_info 中的aviQuantity
		returnDao.updateBookQuantityForReturn(books);
		return msg;
	}
	public String checkOverFineAndIsUserBorrowBook(String userId,Collection<Book> books,Map<String,Float> overFine,float fine) {
		StringBuffer msg = new StringBuffer();
		List<Book> bookItems = new ArrayList<Book>(books);
		boolean flag1 = false;
		boolean flag2 = false;
		for(int i = 0;i<bookItems.size();i++) {
			boolean checked = isUserBorrowBook(userId, bookItems.get(i).getBookId());
			if(!checked) {
				bookItems.remove(i);
				flag2 = true;
				msg.append(bookItems.get(i).getBookName()+",");
			}
		}
		if(flag2)
			msg.append("不是您的在借图书！");
		for(int i = 0;i<bookItems.size();i++) {
			long day_dif = returnDao.isOverFine(userId, bookItems.get(i).getBookId());
			if(day_dif > 0) {
				flag1 = true;
				msg.append(bookItems.get(i).getBookName()+",");
			}
			overFine.put(bookItems.get(i).getBookId(), day_dif>0?day_dif*fine:0);
		}
		if(flag1)
			msg.append("存在超期罚款,请到服务台缴纳！");
		return msg.toString();
	}
	public boolean isUserBorrowBook(String userId,String bookId) {
		if(returnDao.isUserBorrowBook(userId, bookId) > 0) {
			return true;
		}
		return false;
	}
	
	public void fine(String userid) {
		returnDao.fine(userid);
	}
}
