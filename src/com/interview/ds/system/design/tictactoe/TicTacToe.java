package com.interview.ds.system.design.tictactoe;

import java.util.Scanner;

public class TicTacToe {

	
	private final int [][] board;
	int n , totalMoves ;
	int [] rowSum ;
	int [] colSum;
	int daigonalSum = 0;
	int revDiagonalSum = 0;
	public TicTacToe(final int n) {
		this.n = n;
		totalMoves = 0;
		board = new int[n][n];
		rowSum = new int[n];
		colSum = new int[n];
	}
	
	/*
	 *rteurn 0 if player 1 wins -1 if player 2 wins . otherwise return zero 
	 */
	public int move(int player , int row , int col) throws IllegalArgumentException {
		if(row < 0 || col < 0 || row >= n || col >= n) {
			throw new IllegalArgumentException("Illegal Move. Move is outside Of Board Boundary: "+row+","+col);
		}else if(board[row][col] != 0) {
			throw new IllegalArgumentException("Illegal Move. Already Occupied : "+row+","+col);
		}else if(player != 0 && player != 1) {
			throw new IllegalArgumentException("Invalid Player ");
		}else {
			player = player == 0 ? -1 : +1;
			board[row][col] = player;
			totalMoves++;
			
			rowSum[row] += player;
			colSum[col] += player;
			
			if(row == col)
				daigonalSum += player;
			
			if(row == n -1 - col)
				revDiagonalSum += player;
			
			if(Math.abs(rowSum[row]) == n || Math.abs(colSum[col]) == n || daigonalSum == n || revDiagonalSum == n) {
				return player ;
			}
		}
		return 0;
	}
	
	public static void main(String[] args) {
		int n = 3;
		TicTacToe ticTacToe = new TicTacToe(n);
		int result = Integer.MAX_VALUE;
		Scanner sc = new Scanner(System.in);
		int player = 1;
		int row , col ;
		
		while(ticTacToe.totalMoves != n*n && result != 1 && result != -1) {
			System.out.print("Player"+ player+" : Enter row Between 0 to "+(n-1)+" : ");
			row = sc.nextInt();
			System.out.print("Player"+ player+" : Enter col Between 0 to "+(n-1)+" : ");
			col = sc.nextInt();
			
			result = ticTacToe.move(player, row, col);
			if(player == 1) {
				player = 0;
			}else {
				player = 1;
			}
			ticTacToe.showBoard();
		}
		
		if(result == 0) {
			System.out.println("Game Drawn");
		}else if(result == -1){
			System.out.println("Player 0 Won");
		}else if(result == 1) {
			System.out.println("Player 1 Won");
		}
		
	}

	private void showBoard() {
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		
	}

}
