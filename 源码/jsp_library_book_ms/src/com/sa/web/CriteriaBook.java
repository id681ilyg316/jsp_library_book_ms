package com.sa.web;

import com.sa.domain.Book;

public class CriteriaBook {
	private Book book;
	private int pageNo;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public CriteriaBook(Book book, int pageNo) {
		super();
		this.book = book;
		this.pageNo = pageNo;
	}
	public CriteriaBook() {
	}
	
}
