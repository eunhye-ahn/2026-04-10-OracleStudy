package com.sist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sist.commons.DBUtil;
import com.sist.vo.BoardVO;

public class BoardDAO {
	private DBUtil db = new DBUtil();
	private Connection conn;
	private PreparedStatement ps;
	private static BoardDAO dao;
	public static BoardDAO newInstance() {
		if(dao==null) {
			dao= new BoardDAO();
		}
		return dao;
		
	}
	
	public List<BoardVO> board_list(int page){
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn= db.getConnection();
			String sql = "select /*+ index_DESC(board board_no_pk)*/ no, name, subject, TO_CHAR(regdate, 'YYYY-MM-DD'), hit from board "
					+"offset ? rows fetch next 10 rows only";
			int start=(page*10)-10;
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setDbday(rs.getString(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.disConnection(conn, ps);
		}
		return list;
	}
	//총페이지
	public int boardTotalPage() {
		int total = 0;
		try {
			//연결
			conn = db.getConnection();
			//sql문
			String sql = "select ceil(count(*)/10.0) from board";
			//전송
			ps = conn.prepareStatement(sql);
			//?가있는경우 값채우기
			//실행
			ResultSet rs = ps.executeQuery();
			//출력된 메모리 위치에 커서이동
			rs.next();
			//해당데이터형이용해서 데이터 가지고오기
			total=rs.getInt(1);
			rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.disConnection(conn, ps);
		}
		return total;
	}
	//데이터추가
	public void board_insert(BoardVO vo) {
		try {
			conn = db.getConnection();
			String sql = "insert into board values("
					+ "board_seq.nextval,?,?,?,?,"
					+ "sysdate,0)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.executeUpdate(); //db변경
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.disConnection(conn, ps);
		}
	}
	
	//상세보기
	public BoardVO board_detail(int no) {
		try {
			BoardVO vo = new BoardVO();
			conn = db.getConnection();
			String sql = "update board set hit=hit+1 where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ps.executeUpdate();
			
			sql= "select no,name,subject,content,hit, to_char(regdate,'YYYY-MM-DD HH24:MI:SS') from board "
					+ "where no=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			//값채우기
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setHit(rs.getInt(5));
			vo.setDbday(rs.getString(6));
			rs.close();
			return vo;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {

			db.disConnection(conn, ps);
		}
		return null;
	}
	
	//삭제
	public boolean board_delete(int no, String pwd) {
		boolean bCheck = false;
		try {
			conn=db.getConnection();
			String sql = "select pwd from board where no = ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String db_pwd = rs.getString(1);
			rs.close();
			if(db_pwd.equals(pwd)) {
				bCheck=true;
				sql="delete from board where no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				ps.executeUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			db.disConnection(conn, ps);
		}
		return bCheck;
	}
	
}
