package com.sist.client;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserMainForm extends JFrame
implements ActionListener{
	MenuPenal mp;
    ControllerPenal cp;
    public UserMainForm() {
    	mp = new MenuPenal();
    	cp = new ControllerPenal(this);
		setLayout(null);
		mp.setBounds(150, 15, 860, 35);
//		mp.setBounds(10, 150, 120, 560 );
		add(mp);
		cp.setBounds(20, 60, 980, 670);
		add(cp);
		setSize(1024,768);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mp.b1.addActionListener(this);
		mp.b5.addActionListener(this);
	}
	public static void main(String[] args) {
			try {
				UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
			}catch(Exception e) {
				e.printStackTrace();
			}
			new UserMainForm();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==mp.b5) {
			cp.card.show(cp, "BLIST");
			cp.bList.print();
		}else if(e.getSource()==mp.b1) {
			cp.card.show(cp, "HOME");			
		}
	}

}
