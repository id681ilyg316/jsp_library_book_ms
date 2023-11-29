package com.sa.dao;

import java.util.Collection;
import java.util.Map;

import com.sa.domain.Book;

public interface ReturnDao {
	/*
	 * �����û��黹ͼ��ʱ�ĳ�������
	 */
	public abstract long isOverFine(String userId,String bookId);
	
	/**
	 * �û��黹�鼮������ borrow_info ��
	 * @param userId
	 * @param bookId
	 */
	public void returnBook(String userId,Collection<Book> books,Map<String, Float> overFine);
	
	/**
	 * �û��黹�鼮������ book_info ��
	 * @param bookId
	 */
	public void updateBookQuantityForReturn(Collection<Book> books);
	
	public long isUserBorrowBook(String userId,String bookId);
	
	public void fine(String userid);
}
