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
	 * ����ͼ�� ID ɾ��ָ��ͼ��
	 * @param bookId
	 */
	public void removeBook(String bookId) {
		books.remove(bookId);
	}
	
	/**
	 * ��� ���ﳵ
	 */
	public void clear() {
		books.clear();
	}
	
	/**
	 * ���ع��ﳵ�Ƿ�Ϊ��
	 * @return
	 */
	public boolean isEmpty() {
		return books.isEmpty();
	}
	
	/**
	 * ���ع��ﳵ�����е�ͼ����ɵļ���
	 * @return
	 */
	public Collection<Book> getItems(){
		return books.values();
	}
	
	/**
	 * ���ع��ﳵ��ͼ�������
	 * @return
	 */
	public Integer getBookNumber() {
		return books.size();
	}
	
	/**
	 * ���ﳵ�����ͼ��
	 * @param book
	 */
	public void addBook(Book book) {
		Book bookTemp = books.get(book.getBookId());
		if(bookTemp == null)
			books.put(book.getBookId(), book);
	}
	
	/**
	 * ���鹺�ﳵ���Ƿ���ָ�� ID ��ͼ����Ϣ
	 * @param id
	 * @return
	 */
	public boolean hasBook(Integer id) {
		return books.containsKey(id);
	}
}
