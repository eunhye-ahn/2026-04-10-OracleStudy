package com.sist.books;
/**
 * NO       NOT NULL NUMBER         
BOOKNAME NOT NULL VARCHAR2(2000) 
POSTER   NOT NULL VARCHAR2(2000) 
AUTHOR   NOT NULL VARCHAR2(1000) 
PRICE    NOT NULL VARCHAR2(100)  
PUBDATE  NOT NULL VARCHAR2(100)  
ISBN     NOT NULL VARCHAR2(100)  
CONTENT           CLOB           
TAG               CLOB    
 */
public class BooksVO {
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPubdate() {
		return pubdate;
	}
	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	private int no;
	private String bookname,poster,author,price,pubdate,
	isbn,content,tag;
}
