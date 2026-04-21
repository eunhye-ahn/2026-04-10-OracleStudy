package com.sist.client;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;

public class ControllerPenal extends JPanel{
	CardLayout card = new CardLayout();
	UserMainForm mf;
	BoardList bList;
	HomePanel hp = new HomePanel();
	public ControllerPenal(UserMainForm uf) {
		this.mf = mf;
		bList = new BoardList(mf);
		setLayout(card);
		setBackground(Color.cyan);
		add("HOME",hp);
		add("BLIST",bList);
	}
}
