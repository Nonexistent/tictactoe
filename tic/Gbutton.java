package tic;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import tic.tac.Manager;

@SuppressWarnings("serial")
public class Gbutton extends JButton {
	String name;
	int row = 0;
	int colomn= 0;
	Manager manager;
	AbstractAction a = new AbstractAction("") {
		@Override
		public void actionPerformed(ActionEvent e) {
			happens();
		}
	};

	public Gbutton(String s) {
		super("     ");
		addActionListener(a);
		this.name = s;
		char[] indexArray = s.toCharArray();
		this.row = Integer.parseInt(String.valueOf(indexArray[0]));
		this.colomn = Integer.parseInt(String.valueOf(indexArray[1]));
	}

	public void happens() {
		this.setEnabled(false);
		if (this.manager.getMoveNum() % 2 == 0) {
			setText("X");
			manager.updateBoard(this.row, this.colomn, 1);
		} else {
			setText("O");
			manager.updateBoard(this.row, this.colomn, 2);
		}
		this.manager.setMoveNum(this.manager.getMoveNum()+1);

	}

	public void addMoveManager(Manager m) {
		this.manager = m;
	}
	
	public String getButtonName(){
		return this.name;
	}
}
