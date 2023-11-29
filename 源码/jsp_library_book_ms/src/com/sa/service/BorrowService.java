package com.sa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.sa.dao.BorrowDao;
import com.sa.dao.impl.BorrowDaoImpl;
import com.sa.domain.Book;
import com.sa.domain.Borrow;

public class BorrowService {
	private BorrowDao borrowDao = new BorrowDaoImpl();
	public List<Borrow> getUserBorrowRec(String id){
		return borrowDao.getBorrowRecord(id);
	}
	public int getCurBorrowCount(String userId) {
		return (int) borrowDao.getCurBorrowCount(userId);
	}
	public String borrowBook(String userId,Collection<Book> books) {
		StringBuffer msg = new StringBuffer();
		//1.�Ƿ���ڳ���ͼ��
		int overBookCount = (int) overBook(userId);
		if(overBookCount > 0) {
			//�����ڳ���ͼ�飬���˳����ģ����Ҹ�����ʾ
			msg.append("������"+overBookCount+"������ͼ�飬��ǰ���ܼ������ġ�");
			return msg.toString();
		}
		//2. ������ͼ�飬�ݲ��Ƿ�ɽ�
		msg.append(bookQuantityChecked(books));
		//3.��������Ϣ���� borrow_info ����
		borrowDao.borrowBook(userId, books);
		//4.��ͼ���Ӧ�Ŀɽ�����һ
		borrowDao.updateBookQuantityForBorrow(books);
		return msg.toString();
	}
	
	public long overBook(String userId) {
		return borrowDao.overBookCount(userId);
	}
	
	public String bookQuantityChecked(Collection<Book> books) {
		List<Book> allBooks = new ArrayList<Book>(books);
		StringBuffer msg = new StringBuffer();
		boolean flag = true;
		for(int i = 0;i<allBooks.size();i++) {
			if(borrowDao.hasBook(allBooks.get(i).getBookId()) <= 0) {
				msg.append(allBooks.get(i).getBookName()+",");
				allBooks.remove(i);
				flag = false;
			}
		}
		if(!flag)
			msg.append("���ڽ��ģ��ݲز��㣡");
		return msg.toString();
	}
	
	public long isAlreadyBorrow(String userId,String bookId) {
		return borrowDao.isAlreadyBorrow(userId, bookId);
	}
}
