package com.sa.dao;

import java.util.Collection;
import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.Borrow;
import com.sa.domain.User;

public interface BorrowDao {
	/*
	 * �����û�id��ѯ���û����ļ�¼
	 */
	public abstract List<Borrow> getBorrowRecord(String id);
	
	/**
	 * �����û� id ��ѯ�û���ǰ���ڽ��ĵ��鼮����
	 */
	public abstract long getCurBorrowCount(String userId);
	
	public void borrowBook(String userId,Collection<Book> books);
	
	public long overBookCount(String userId);
	
	public long hasBook(String bookId);
	
	public void updateBookQuantityForBorrow(Collection<Book> books);
	
	public long isAlreadyBorrow(String userId,String bookId);
}
