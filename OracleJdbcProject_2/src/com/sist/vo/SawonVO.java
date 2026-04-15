package com.sist.vo;

/**
 * EMPNO     NOT NULL NUMBER(5)     
ENAME     NOT NULL VARCHAR2(30)  
GENDER             CHAR(1)       
ADDRESS            VARCHAR2(50)  
AGE                NUMBER(3)     
POSITION           VARCHAR2(20)  
LOCATION           VARCHAR2(30)  
SALARY             NUMBER(10,2)  
PHONE              VARCHAR2(20)  
INTRO              VARCHAR2(100) 
DEPTNO    NOT NULL NUMBER(2)     
HIRE_DATE          DATE      
 */

import java.util.*;

public class SawonVO {
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public String getPay() {
		return pay;
	}
	public void setPay(String pay) {
		this.pay = pay;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	private int empno, age, salary, deptno;
	private String ename, gender, address, position,
	location, phone, intro, dbday, pay;
	private Date hire_date;
	
	public static void main(String[] args) {
		System.out.println(new Date());
	}
}
