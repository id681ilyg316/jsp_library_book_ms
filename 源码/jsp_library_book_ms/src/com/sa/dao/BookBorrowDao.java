package com.sa.dao;

import java.util.Collection;
import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.BookBorrow;

public interface BookBorrowDao {
	/**
	 * ����ͼ�� id ,���ظ�ͼ��Ľ�����Ϣ
	 * @param bookId
	 * @return
	 */
	public abstract List<BookBorrow> getBookBorrow(String bookId);
	
	
}
