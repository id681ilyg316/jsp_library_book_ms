package com.sa.domain;

import java.sql.Date;

public class Book {
	private String bookId;
	private String bookName;
	private String type;
	private String isbn;
	private String author;
	private String press;
	private Date pubTime;
	private Integer allQuantity;
	private Integer aviQuantity;
	private String imgPath;
	private String pdfPath;
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String press) {
		this.press = press;
	}
	public Date getPubTime() {
		return pubTime;
	}
	public void setPubTime(Date pubTime) {
		this.pubTime = pubTime;
	}
	public Integer getAllQuantity() {
		return allQuantity;
	}
	public void setAllQuantity(Integer allQuantity) {
		this.allQuantity = allQuantity;
	}
	public Integer getAviQuantity() {
		return aviQuantity;
	}
	public void setAviQuantity(Integer aviQuantity) {
		this.aviQuantity = aviQuantity;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public Book(String bookId, String bookName, String type, String isbn, String author, String press, Date pubTime,
			Integer allQuantity, Integer aviQuantity, String imgPath, String pdfPath) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.type = type;
		this.isbn = isbn;
		this.author = author;
		this.press = press;
		this.pubTime = pubTime;
		this.allQuantity = allQuantity;
		this.aviQuantity = aviQuantity;
		this.imgPath = imgPath;
		this.pdfPath = pdfPath;
	}
	public Book() {
		
	}
	@Override
	public String toString() {
		return "Book [bookid="+bookId+",bookname="+bookName+",type="+type+",isbn="+isbn+
				"allquantity"+allQuantity+",aviQuantity="+aviQuantity+",imgPath="+imgPath+",press="+press+
				",author="+author+"]";
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
}
