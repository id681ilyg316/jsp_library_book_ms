package com.sa.dao;

import java.util.List;

import com.sa.domain.Book;
import com.sa.domain.BookBorrow;
import com.sa.web.CriteriaBook;
import com.sa.web.Page;

public interface BookDao {
	/**
	 * ����ͼ�� id �����鱾��Ϣ
	 * @param id
	 * @return
	 */
	public Book getBook(String id);
	
	/**
	 * ����ʵ����������ͼ����Ϣ
	 * @param book
	 */
	public void insert(Book book);
	
	/**
	 * ����ͼ�� id ɾ��ͼ����Ϣ
	 * @param id
	 */
	public void delete(String id);
	
	/**
	 * ����ʵ����������ͼ����Ϣ
	 * @param book
	 */
	public void update(Book book);
	
	/**
	 * ���ݴ���� CriteriaBook ���󷵻ض�Ӧ�� Page ����
	 * @param ub
	 * @return
	 */
	public abstract Page<Book> getBookPage(CriteriaBook cb);
	
	/**
	 * ���ݴ���� CriteriaBook ���󷵻����Ӧ�ļ�¼��
	 * @param cb
	 * @return
	 */
	public abstract long getTotalBookNumber(CriteriaBook cb);
	
	/**
	 * ���ݴ���� CriteriaBook �� pageSize ���ص�ǰҳ��Ӧ�� List 
	 * @param ub
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public abstract List<Book> getBookPageList(CriteriaBook cu,int pageSize);
	
}
