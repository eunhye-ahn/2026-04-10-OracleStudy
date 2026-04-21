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
	
	public static void main(String[] args) {
		BoardDAO dao = BoardDAO.newInstance();
		 
		List<BoardVO> list = dao.board_list(1);
		for(BoardVO vo : list) {
			System.out.println(vo.getName());
		}
	}
}
