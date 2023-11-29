package com.sa.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookCart {
	private Map<String, Book> books = new HashMap<String, Book>();

	public Map<String, Book> getBooks() {
		return books;
	}
	/**
	 * 根据图书 ID 删除指定图书
	 * @param bookId
	 */
	public void removeBook(String bookId) {
		books.remove(bookId);
	}
	
	/**
	 * 清空 购物车
	 */
	public void clear() {
		books.clear();
	}
	
	/**
	 * 返回购物车是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return books.isEmpty();
	}
	
	/**
	 * 返回购物车中所有的图书组成的集合
	 * @return
	 */
	public Collection<Book> getItems(){
		return books.values();
	}
	
	/**
	 * 返回购物车中图书的数量
	 * @return
	 */
	public Integer getBookNumber() {
		return books.size();
	}
	
	/**
	 * 向购物车中添加图书
	 * @param book
	 */
	public void addBook(Book book) {
		Book bookTemp = books.get(book.getBookId());
		if(bookTemp == null)
			books.put(book.getBookId(), book);
	}
	
	/**
	 * 检验购物车中是否有指定 ID 的图书信息
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id) {
		return books.containsKey(id);
	}
}
