package com.sa.dao;

import java.util.Collection;
import java.util.Map;

import com.sa.domain.Book;

public interface ReturnDao {
	/*
	 * 返回用户归还图书时的超期天数
	 */
	public abstract long isOverFine(String userId,String bookId);
	
	/**
	 * 用户归还书籍，更新 borrow_info 表
	 * @param userId
	 * @param bookId
	 */
	public void returnBook(String userId,Collection<Book> books,Map<String, Float> overFine);
	
	/**
	 * 用户归还书籍，更新 book_info 表
	 * @param bookId
	 */
	public void updateBookQuantityForReturn(Collection<Book> books);
	
	public long isUserBorrowBook(String userId,String bookId);
	
	public void fine(String userid);
}
