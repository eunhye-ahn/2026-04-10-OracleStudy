package com.sist.dao;

import java.util.*;

import com.sist.vo.MovieVO;
import com.sist.vo.MusicVO;

import java.sql.*;




public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String URL = "jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버등록
	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//오라클연동
	public void getConnection() {
		try {
			this.conn = DriverManager.getConnection(URL,"hr","happy");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//연결닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//기능
	//목록
	public List<MusicVO> music_list(int page){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql = "select no, title, singer, album, state "
					+ "from genie_music "
					+ "offset ? rows fetch next 10 rows only";
			ps=conn.prepareStatement(sql);
			
			int start = page*10-10;
			ps.setInt(1, start);
			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setState(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	//총페이지
	public int music_totalPage() {
		int totalPage = 0;
		try {
			getConnection();
			String sql = "select ceil(count(*)/10.0) "
					+ "from genie_music";
			ps=conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			rs.next();
			totalPage = rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return totalPage;
	}
	
	/**
	 * NO           NUMBER(3)     
	CNO          NUMBER(1)     
	TITLE        VARCHAR2(300) 
	SINGER       VARCHAR2(200) 
	ALBUM        VARCHAR2(200) 
	POSTER       VARCHAR2(260) 
	STATE        CHAR(6)       
	IDCREMENT    NUMBER(3)
	 */
	
	//상세보기
	public MusicVO music_detail(int no) {
		MusicVO vo = new MusicVO();
		try {
			getConnection();
			String sql = "select no, title, singer, album, state, idcrement "
					+ "from genie_music "
					+ "where no = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setTitle(rs.getString(2));
			vo.setSinger(rs.getString(3));
			vo.setAlbum(rs.getString(4));
			vo.setState(rs.getString(5));
			vo.setIdcrement(rs.getInt(6));
			
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return vo;
	}
	
	//검색
	public List<MusicVO> music_search(String col, String p){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql = "select no, title, singer, album, state "
					+ "from genie_music "
					+ "where "+col+" like '%'||?||'%'";
			ps=conn.prepareStatement(sql);
			ps.setString(1, p);
			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setState(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
	//
	public List<MusicVO> music_sort(int cno){
		List<MusicVO> list = new ArrayList<MusicVO>();
		try {
			getConnection();
			String sql = "select no, title, singer, album, state "
					+ "from genie_music "
					+ "where cno = ? "
					+ "order by no asc";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, cno);
			ResultSet rs = ps.executeQuery();
			rs.next();
			while(rs.next()) {
				MusicVO vo = new MusicVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setState(rs.getString(5));
				
				list.add(vo);
			}
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disConnection();
		}
		return list;
	}
	
}
