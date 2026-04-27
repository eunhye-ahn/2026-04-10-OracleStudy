package com.sist.client;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ControllerPenal extends JPanel{
	CardLayout card = new CardLayout();
	UserMainForm mf;
	BoardList bList;
	HomePanel hp = new HomePanel();
	BoardInsert bInsert;
	BoardDetail bDetail;
	BoardDelete bDelete;
	public ControllerPenal(UserMainForm uf) {
		this.mf = uf;
		bList = new BoardList(mf);
		bInsert = new BoardInsert(mf);
		bDetail = new BoardDetail(mf);
		bDelete = new BoardDelete(mf);
		setLayout(card);
		setBackground(Color.cyan);
		add("HOME",hp);
		add("BLIST",bList);
		add("BINSERT",bInsert);
		add("BDETAIL",bDetail);
		add("BDELETE",bDelete);
	}
}
