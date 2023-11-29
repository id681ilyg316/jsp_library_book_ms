package com.sa.dao;

import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.BookBorrow;
import com.sa.web.CriteriaBook;
import com.sa.web.Page;

public interface BookDao {
	/**
	 * 根据图书 id 返回书本信息
	 * @param id
	 * @return
	 */
	public Book getBook(String id);
	
	/**
	 * 根据实体类对象添加图书信息
	 * @param book
	 */
	public void insert(Book book);
	
	/**
	 * 根据图书 id 删除图书信息
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * 根据实体类对象更新图书信息
	 * @param book
	 */
	public void update(Book book);
	
	/**
	 * 根据传入的 CriteriaBook 对象返回对应的 Page 对象
	 * @param ub
	 * @return
	 */
	public abstract Page<Book> getBookPage(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 对象返回其对应的记录数
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);
	
	/**
	 * 根据传入的 CriteriaBook 和 pageSize 返回当前页对应的 List 
	 * @param ub
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getBookPageList(CriteriaBook cu,int pageSize);
	
}
