package com.sist.books;

import java.sql.*;
import java.util.*;
/*
 
 */
import com.sist.vo.*;
public class BooksDAO {
	// 전체적으로 사용 
	  private Connection conn; // Socket => 연결 담당 
	  private PreparedStatement ps; // BufferedReader , OutputStream 
	  // 송(SQL문장) 수신(오라클에서 결과값 받기)
	  private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	  
	  // 1. 드라이버 등록 
	  public BooksDAO()
	  {
		  try
		  {
			  Class.forName("oracle.jdbc.driver.OracleDriver");
		  }catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
	  }
	  // 2. 오라클 연결 
	  public void getConnection()
	  {
		  try
		  {
			  conn=DriverManager.getConnection(URL,"hr",
					         "happy");
		  }catch(Exception ex) {}
	  }
	  // 3. 오라클 연결 해제 
	  public void disConnection()
	  {
		  try
		  {
			  if(ps!=null) ps.close();
			  if(conn!=null) conn.close();
		  }catch(Exception ex) {}
	  }

	  //기능
	  public void books_add(BooksVO vo){
		  try {
			  getConnection();
			  String sql = "insert into books values("
			  		+ "books_no_seq.nextval,?,?,?,?,?,?,?,?)";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getBookname());
			  ps.setString(2, vo.getPoster());
			  ps.setString(3, vo.getAuthor());
			  ps.setString(4, vo.getPrice());
			  ps.setString(5, vo.getPubdate());
			  ps.setString(6, vo.getIsbn());
			  ps.setString(7, vo.getContent());
			  ps.setString(8, vo.getTag());
			  
			  ps.executeUpdate();
		  }catch(Exception e) {
			  e.printStackTrace();
		  }finally {
			  disConnection();
		  }
	  }

}