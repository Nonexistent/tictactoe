package tic.tac;

import java.awt.Color;
import java.util.LinkedHashMap;

import tic.Gbutton;
import tic.Gui;

public class Manager {
	LinkedHashMap<Gbutton, String> m = new LinkedHashMap<Gbutton, String>();
	int moveNum = 0;
	int[][] board = new int[3][3];
	Gui gui;
	
	public Manager(LinkedHashMap<Gbutton, String> map, Gui g) {
		this.gui = g;
		this.m = map;
		initBoard();
	}
	
	public void initBoard(){
		this.gui.getLabel().setText("Not yet determined.");
		this.moveNum = 0;
		int temp = 5;
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				board[i][j] = temp;
				temp++;
			}
		}
	}
	
	public int getMoveNum(){
		return moveNum;
	}
	
	public void setMoveNum(int i){
		this.moveNum = i;
	}
	
	public void updateBoard(int row, int colomn, int sign){
		//SIGN; X = 1; O = 2
		board[row][colomn] = sign;
		checkWin();
	}
	
	public void checkWin(){
		int a = 0; int b = 1; int c = 2;
		String q = String.valueOf(a); String w = String.valueOf(b); String e = String.valueOf(c);
		if(((board[a][a] == board[a][b]) && (board[a][b] == board[a][c]))){
			end(q+q, q+w, q+e, board[a][a]);
		}else if((board[b][a] == board[b][b]) && (board[b][b] == board[b][c]))	{
			end(w+q,w+w,w+e,board[b][a]);
		}else if((board[c][a] == board[c][b]) && (board[c][b] == board[c][c])){
			end(e+q,e+w,e+e,board[c][a]);
		}else if((board[a][a] == board[b][a]) && (board[b][a] == board[c][a])){
			end(q+q,w+q,e+q,board[a][a]);
		}else if((board[a][b] == board[b][b]) && (board[b][b] == board[c][b])){
			end(q+w,w+w,e+w,board[a][b]);
		}else if((board[a][c] == board[b][c]) && (board[b][c] == board[c][c]))	{
			end(q+e,w+e,e+e,board[a][c]);
		}else if((board[a][a] == board[b][b]) && (board[b][b] == board[c][c])){
			end(q+q,w+w,e+e,board[a][a]);
		}else if((board[a][c] == board[b][b]) && (board[b][b] == board[c][a])){
			end(q+e,w+w,e+q,board[a][c]);
		}
	}
	
	public void end(String a, String b, String c, int winNum){
		String[] tiles = {a,b,c};
		for(Gbutton button: m.keySet()){
			button.setEnabled(false);
		}
		if(winNum == 1){
			gui.getLabel().setText("Winner: X");
		}else{
			gui.getLabel().setText("Winner: O");
		}
		for(String s : tiles){
			for (Gbutton j : m.keySet()){
				if(s.equals(j.getButtonName())){
					j.setBackground(Color.decode("#4876FF"));
				}
			}
		}
	}
	
}
