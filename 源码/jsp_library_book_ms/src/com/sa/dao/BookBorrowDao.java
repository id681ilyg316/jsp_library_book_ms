package com.sa.dao;

import java.util.Collection;
import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.BookBorrow;

public interface BookBorrowDao {
	/**
	 * 根据图书 id ,返回该图书的借阅信息
	 * @param bookId
	 * @return
	 */
	public abstract List<BookBorrow> getBookBorrow(String bookId);
	
	
}
