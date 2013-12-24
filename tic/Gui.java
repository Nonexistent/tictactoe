package tic;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.LinkedHashMap;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tic.tac.Manager;

public class Gui {
	private JFrame frame = new JFrame("Tic TacToe");
	LinkedHashMap<Gbutton, String> m = new LinkedHashMap<Gbutton, String>();
	Manager manager;
	public JLabel label = new JLabel("Not yet determined.");

	public void start() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(300, 300);
		
		frame.getContentPane().add(grid());
		frame.getContentPane().add(resetButton());
		frame.getContentPane().add(result());
		frame.setVisible(true);
	}

	private Component grid() {
		JPanel p = new JPanel(new GridLayout(3, 3, 2, 2));// row, colomn, gaps
		for (int i = 0; i < 3; i++) {
			String s = "0" + i;
			m.put(new Gbutton(s), s);
		}
		for (int i = 0; i < 3; i++) {
			String s = "1" + i;
			m.put(new Gbutton(s), s);
		}
		for (int i = 0; i < 3; i++) {
			String s = "2" + i;
			m.put(new Gbutton(s), s);
		}
		
		this.manager = new Manager(m, this);
		for (Gbutton b : m.keySet()) {
			b.addMoveManager(this.manager);
			p.add(b);
		}
		return p;
	}
	
	private Component result(){
		JPanel infoPanel = new JPanel();
		JPanel sec = new JPanel();
		infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		TitledBorder border = BorderFactory.createTitledBorder("Winner" + ":");
		sec.setBorder(border);
		sec.add(label);
		infoPanel.add(sec);
		return infoPanel;
	}
	
	public JLabel getLabel(){
		return this.label;
	}
	
	@SuppressWarnings("serial")
	private Component resetButton(){
		JPanel p = new JPanel();
		p.add(new JButton(new AbstractAction("Reset"){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(Gbutton b : m.keySet()){
					b.setText("     ");
					b.setBackground(null);
					b.setEnabled(true);
					manager.initBoard();
				}
			}
		}));
		return p;
	}
}
