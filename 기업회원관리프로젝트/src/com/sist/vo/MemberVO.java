package com.sist.vo;

import java.util.Date;

/*
 ID                                        NOT NULL VARCHAR2(20)
 PWD                                       NOT NULL VARCHAR2(10)
 NAME                                      NOT NULL VARCHAR2(51)
 SEX                                                VARCHAR2(6)
 POST                                      NOT NULL VARCHAR2(7)
 ADDR1                                     NOT NULL VARCHAR2(200)
 ADDR2                                              VARCHAR2(200)
 PHONE                                              VARCHAR2(14)
 CONTENT                                            CLOB
 ISADMIN                                            CHAR(1)
 REGDATE                                            DATE
 */
public class MemberVO {
	   public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
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
	   private String id,pwd,sex,name,post,addr1,addr2,
       phone,content,isadmin,dbday,msg,grade;
	   public String getGrade() {
		return grade;
	}
	   public void setGrade(String grade) {
		   this.grade = grade;
	   }
	   public String getMsg() {
		return msg;
	}
	   public void setMsg(String msg) {
		   this.msg = msg;
	   }
	   private Date regdate;
}