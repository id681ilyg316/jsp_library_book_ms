package com.sa.service;

import java.util.List;
import com.sa.dao.BookBorrowDao;
import com.sa.dao.BookDao;
import com.sa.dao.impl.BookBorrowDaoImpl;
import com.sa.dao.impl.BookDaoImpl;
import com.sa.domain.Book;
import com.sa.domain.BookBorrow;
import com.sa.domain.BookCart;
import com.sa.web.CriteriaBook;
import com.sa.web.Page;

public class BookService {
	private BookDao bookDao = new BookDaoImpl();
	private BookBorrowDao bookBorrowDao = new BookBorrowDaoImpl();
	public Page<Book> getBookPage(CriteriaBook cb){
		return bookDao.getBookPage(cb);
	}
	public Book getBook(String id) {
		return bookDao.getBook(id);
	}
	public List<BookBorrow> getBookBorrow(String bookId){
		return bookBorrowDao.getBookBorrow(bookId);
	}
	public void update(Book book) {
		bookDao.update(book);
	}
	public boolean addToCart(String id,BookCart bc) {
		Book book = bookDao.getBook(id);
		if(book != null) {
			bc.addBook(book);
			return true;
		}
		return false;
	}
	public void removeBookFromCart(String bookId,BookCart bc) {
		bc.removeBook(bookId);
	}
	public void clear(BookCart bc) {
		bc.clear();
	}
	public void addBook(Book book) {
		bookDao.insert(book);
	}
}
