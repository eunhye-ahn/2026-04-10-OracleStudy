package com.sist.dao;
// 오라클 연결 => 요청 데이터 검색 / 삭제 / 수정/ 추가 (repo역할)
import java.util.*;

import com.sist.vo.SawonVO;

import java.sql.*;

public class SawonDAO {
	/**
	 * 연결에 필요한 객체, 변수 생성
	 */
	//연결
	private Connection conn;
	//송수신
	private PreparedStatement ps;
	//싱글턴
	private static SawonDAO dao;
	//오라클 주소
	private static final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	//드라이버 설정
	public SawonDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//싱글턴 - 하나의 연결객체만 생성하도록
	public static SawonDAO newInstance() {
		if(dao==null) dao=new SawonDAO();
		return dao;
	}
	//오라클 연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//오라클 닫기
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//sql문 실행 메서드

	//로그인
	
	//사원목록 -페이징 - rownum
	public void sawonList(SawonVO vo) {
		try {
			getConnection();
			String sql = "";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, vo.getEmpno());
			ps.setString(2, vo.getEname());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//상세보기 - 사번
	
	//통계처리 - group by
}
