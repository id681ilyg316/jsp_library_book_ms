package com.sa.dao.impl;

import java.awt.image.CropImageFilter;
import java.util.List;

import com.sa.dao.BookDao;
import com.sa.domain.Book;
import com.sa.domain.BookBorrow;
import com.sa.util.CriteriaUserUtils;
import com.sa.web.CriteriaBook;
import com.sa.web.Page;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public Book getBook(String id) {
		String sql = "SELECT bookId,bookName,type,isbn,author,press,pubTime,allQuantity,"+
					"aviQuantity,imgPath,pdfPath FROM book_info WHERE bookId = ?";
		return query(sql, id);
	}

	@Override
	public void insert(Book book) {
		String sql = "INSERT INTO book_info(bookName,type,isbn,author,press,pubTime,allQuantity,"+
					"aviQuantity,imgPath,pdfPath) VALUES(?,?,?,?,?,?,?,?,?,?)";
		update(sql, book.getBookName(),book.getType(),book.getIsbn(),book.getAuthor(),book.getPress(),
				book.getPubTime(),book.getAllQuantity(),book.getAviQuantity(),book.getImgPath(),book.getPdfPath());
	}

	@Override
	public void delete(String id) {
		String sql = "DELETE FROM book_info WHERE bookId = ?";
		update(sql, id);
	}

	@Override
	public void update(Book book) {
		String sql = "UPDATE book_info SET bookName = ?,type = ?,isbn = ?,author = ?,press = ?,pubTime = ?"
				+ ",allQuantity = ?,aviQuantity = ?,imgPath = ?,pdfPath = ? WHERE bookId = ?";
		update(sql, book.getBookName(),book.getType(),book.getIsbn(),book.getAuthor(),book.getPress(),
				book.getPubTime(),book.getAllQuantity(),book.getAviQuantity(),book.getImgPath(),book.getPdfPath()
				,book.getBookId());
	}

	@Override
	public Page<Book> getBookPage(CriteriaBook cb) {
		Page page = new Page<>(cb.getPageNo());
		page.setTotalItemNumber((int)getTotalBookNumber(cb));
		//校验不合法的 pageNo
		cb.setPageNo(page.getPageNo());
//		System.out.println(cu.getPageNo());
		page.setList(getBookPageList(cb, 10));
		return page;
	}

	@Override
	public long getTotalBookNumber(CriteriaBook cb) {
		String sql = "SELECT COUNT(*) FROM book_info WHERE"+CriteriaUserUtils.getBookFilter(cb).toString();
		return getSingleVal(sql);
	}

	@Override
	public List<Book> getBookPageList(CriteriaBook cb, int pageSize) {
		String sql = "SELECT bookId,bookName,type,isbn,author,press,pubTime,allQuantity,"
				+ "aviQuantity,imgPath,pdfPath FROM book_info WHERE"+CriteriaUserUtils.getBookFilter(cb).toString()+
				" LIMIT ?,?";
		return queryForList(sql, (cb.getPageNo()-1)*pageSize,pageSize);
	}
}
