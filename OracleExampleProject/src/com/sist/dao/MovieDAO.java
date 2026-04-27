package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.vo.MovieVO;

//드라이버 등록
//Class.for("oracle.jdbc.driver.OracleDriver")
//오라클연결
//Connection conn = DriverManager.getConnection(URL,username,password) => conn hr/happy
//URL(오라클주소) : jdbc:업체명:드라이버종류:@IP:PORT:데이터베이스명
//						oracle:thin:@localhost:1521:XE
								//	127.0.0.1
//sql문장
//String sql = "SELECT/DELTE/UPDATE/INSERT"
//			mybatis : xml
//			jpa 	: 메서드
//오라클 전송
//PreparedStatement ps = conn.preparedStatement(sql)
//오라클 실행 => 결과값받기
//ResultSet rs = ps.executeQuery() // executeUpdate()
//list,vo에 값 채우기
//list.add()
//연결닫기
//ps.close()
//conn.close()

public class MovieDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	//드라이버등록 (1회)
	public MovieDAO() { 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//오라클 연동
	public void getConnection() {
		try {
			this.conn = DriverManager.getConnection(URL, "hr", "happy");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//연결 닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//목록
	public List<MovieVO> movieListData(int page){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql = "select mno, title, genre, actor, regdate "
					+ "from movie "
					+ "order by mno asc "
					+ "offset ? rows fetch next 20 rows only";
			ps=conn.prepareStatement(sql);
			int start = (page*20)-20;
			ps.setInt(1, start);
			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setGenre(rs.getString(3));
				vo.setActor(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {}
		finally {
			disConnection();
		}
		return list;
	}
	//1-1.총페이지
	public int movieTotalPage() {
		int total =0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/20.0) "
					+ "from movie";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			total = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return total;
	}
	
	
	//상세보기
	public MovieVO movieDetail(int mno) {
		MovieVO vo = new MovieVO();
		try {
			getConnection();
			String sql = "select mno, title, genre, actor, regdate, grade, director "
					+ "from movie "
					+ "where mno = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1,mno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setMno(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setGenre(rs.getString(3));
			vo.setActor(rs.getString(4));
			vo.setGrade(rs.getString(5));
			vo.setRegdate(rs.getString(6));
			vo.setDirector(rs.getString(7));
			rs.close();
		}catch(Exception e) {}
		finally {
			disConnection();
		}
		return vo;
	}
	
	//검색
	public List<MovieVO> movieFind(String col, String fd){
		List<MovieVO> list = new ArrayList<MovieVO>();
		try {
			getConnection();
			String sql = "select mno, title, genre, actor, regdate "
					+ "from movie "
					+ "where "+ col +" like '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1,fd);
			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				MovieVO vo = new MovieVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setGenre(rs.getString(3));
				vo.setActor(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {}
		finally {
			disConnection();
		}
		return list;
	}
}
