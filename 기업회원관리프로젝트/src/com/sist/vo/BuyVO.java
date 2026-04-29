package com.sist.vo;
/*
 *  NO                                        NOT NULL NUMBER
    ID                                                 VARCHAR2(20)
    TYPE                                      NOT NULL NUMBER
    GNO                                       NOT NULL NUMBER
    ACCOUNT                                            NUMBER
    PRICE                                              NUMBER
    REGDATE                                            DATE
 */
import java.util.*;

// 장바구니 / 예약 
public class BuyVO {
   public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public GoodsVO getGvo() {
		return gvo;
	}
	public void setGvo(GoodsVO gvo) {
		this.gvo = gvo;
	}
   private int no,type,gno,account,price;
   private String id,dbday;
   private Date regdate;
   private GoodsVO gvo=new GoodsVO();
}