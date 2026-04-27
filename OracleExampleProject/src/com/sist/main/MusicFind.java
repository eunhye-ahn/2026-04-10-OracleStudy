package com.sist.main;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.table.*;

import com.sist.dao.MusicDAO;
import com.sist.vo.MovieVO;
import com.sist.vo.MusicVO;
public class MusicFind extends JFrame implements ActionListener{
    JLabel titleLa;
    JTable table;
    DefaultTableModel model;
    TableColumn column;
    JComboBox box;
    JTextField tf;
    JButton b;
    MusicDAO dao = new MusicDAO();
    public MusicFind()
    {
    	
    	
    	titleLa=new JLabel("차트 목록",JLabel.CENTER);// <table>
    	titleLa.setFont(new Font("맑은 고딕",Font.BOLD,30)); //<h3></h3>
    	
    	box=new JComboBox();
    	box.addItem("제목");
    	box.addItem("가수");
    	box.addItem("앨범");
    	
    	tf=new JTextField(20);
    	b=new JButton("검색");
    	
    	String[] col={"번호","제목","가수","앨범","순위변동"};//<tr><th></th>....</tr>
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
    	p.add(box);
    	p.add(tf);
    	p.add(b);
    	
    	p.setBounds(10, 70, 350, 35);
    	add(p);
    	
    	setSize(850, 700);
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    	tf.addActionListener(this);
    	b.addActionListener(this);
    	
    }
    public void print(String col, String p) {
    	for(int i=model.getRowCount()-1;i>=0;i--) {
    		model.removeRow(i);
    	}
    	
    	List<MusicVO> list = dao.music_search(col,p);
    	for(MusicVO vo : list) {
    		String[] data = {
    				String.valueOf(vo.getNo()),
    				vo.getTitle(),
    				vo.getSinger(),
    				vo.getAlbum(),
    				vo.getState()
    		};
    		
    		model.addRow(data);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new MusicFind();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == b || e.getSource() == tf) {
			String fd = tf.getText();
			if(fd.trim().length()<1) {
				tf.requestFocus();
				return;
			}
			
			String[] column = {"title", "singer", "album"};
			int index = box.getSelectedIndex();
			print(column[index], fd);
		}
	}

}
