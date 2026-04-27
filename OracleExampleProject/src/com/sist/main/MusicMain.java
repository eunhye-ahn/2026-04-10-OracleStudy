package com.sist.main;
import java.awt.*;

import com.sist.vo.*;
import com.sist.dao.*;
import java.util.List;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

import com.sist.dao.MusicDAO;
public class MusicMain extends JFrame implements ActionListener,MouseListener{
	JButton prevBtn,nextBtn;
    JLabel pageLa,titleLa;
    JTable table;
    DefaultTableModel model;
    TableColumn column;
    MusicDAO dao = new MusicDAO();
    int curPage = 1;
    int totalPage = 0;
    public MusicMain()
    {
    	
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
    	
    	prevBtn=new JButton("이전");
    	nextBtn=new JButton("다음");
    	pageLa=new JLabel("0 page / 0 pages"); //<label>0 page / 0 pages</label>
    	titleLa=new JLabel("차트 목록",JLabel.CENTER);// <table>
    	titleLa.setFont(new Font("맑은 고딕",Font.BOLD,30)); //<h3></h3>
    	
    	String[] col={"번호","제목","가수","앨범","순위"};//<tr><th></th>....</tr>
    	String[][] row=new String[0][5];
    	// 한줄에 5개 데이터를 첨부 
    	model=new DefaultTableModel(row,col) // 데이터 관리
    	{

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
    		 // 익명의 클래스 => 포함 클래스 => 상속없이 오버라이딩 => 클릭 => 편집기 => 편집방지 
    		 
    	};
    	table=new JTable(model); // 테이블 모양 관리 
    	JScrollPane js=new JScrollPane(table);
    	for(int i=0;i<col.length;i++)
    	{
    		column=table.getColumnModel().getColumn(i);
    		if(i==0)
    		{
    			column.setPreferredWidth(50);
    		}
    		else if(i==1)
    		{
    			column.setPreferredWidth(350);
    		}
    		else if(i==2)
    		{
    			column.setPreferredWidth(100);
    		}
    		else if(i==3)
    		{
    			column.setPreferredWidth(150);
    		}
    		else if(i==4)
    		{
    			column.setPreferredWidth(50);
    		}
    	}
    	table.getTableHeader().setReorderingAllowed(false);
    	table.setShowVerticalLines(false);
    	table.setRowHeight(30);
    	table.getTableHeader().setBackground(Color.pink);
    	
    	// 배치 
    	setLayout(null);
    	titleLa.setBounds(10, 15, 820, 50);
    	add(titleLa);
    	
    	js.setBounds(10, 110, 800, 450);
    	add(js);
    	
    	JPanel p=new JPanel();
    	p.add(prevBtn);
    	p.add(pageLa);
    	p.add(nextBtn);
    	
    	p.setBounds(10, 570, 800, 35);
    	add(p);
    	
    	
    	setSize(850, 730);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	print();
    	prevBtn.addActionListener(this);
    	nextBtn.addActionListener(this);
    	table.addMouseListener(this);
    }
    public void print() {
    	
     	for(int i=model.getRowCount()-1;i>=0;i--) {
    		model.removeRow(i);
    	}
    	List<MusicVO> list = dao.music_list(curPage);
    	totalPage = dao.music_totalPage();
    	
    	for(MusicVO vo : list) {
    		String[] row = {
    				String.valueOf(vo.getNo()),
    				vo.getTitle(),
    				vo.getSinger(),
    				vo.getAlbum(),
    				vo.getState()
    		};
    		
    		model.addRow(row);
    	}
    	
    	pageLa.setText(curPage+ " / " + totalPage);
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MusicMain();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == prevBtn) {
			if(curPage>1) {
				curPage--;
				print();
			}
		}
		if(e.getSource() == nextBtn) {
			if(curPage<totalPage) {
				curPage++;
				print();
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==table) {
			if(e.getClickCount() == 2) {
				int row = table.getSelectedRow();
				MusicVO vo = dao.music_detail(Integer.parseInt(model.getValueAt(row, 0).toString()));
				String msg = "번호: " + vo.getNo() + "\n"
						+ "제목: " + vo.getTitle() + "\n"
						+ "가수: " + vo.getSinger() + "\n"
						+ "앨범: " + vo.getAlbum() + "\n"
						+ "순위변동: " + vo.getState() + "\n"
						+ "??: " + vo.getIdcrement();
				
				JOptionPane.showMessageDialog(this, msg);
			}
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
