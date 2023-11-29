package com.sa.dao;

import java.util.Collection;
import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.Borrow;
import com.sa.domain.User;

public interface BorrowDao {
	/*
	 * 根据用户id查询该用户借阅记录
	 */
	public abstract List<Borrow> getBorrowRecord(String id);
	
	/**
	 * 根据用户 id 查询用户当前尚在借阅的书籍数量
	 */
	public abstract long getCurBorrowCount(String userId);
	
	public void borrowBook(String userId,Collection<Book> books);
	
	public long overBookCount(String userId);
	
	public long hasBook(String bookId);
	
	public void updateBookQuantityForBorrow(Collection<Book> books);
	
	public long isAlreadyBorrow(String userId,String bookId);
}
