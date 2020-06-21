package com.interview.ds.system.design.snakeladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SnakeLadderGame {
	
	private HashMap <Integer,Integer> ladder;
	private HashMap <Integer,Integer>  snake ;
	private Random random ;
	private int noOfWinners;
	private static Scanner sc;
	ArrayList<Player> players;
	
	public SnakeLadderGame() {
		ladder = new HashMap<Integer, Integer>();
		snake = new HashMap<Integer, Integer>();
		random = new Random();
		noOfWinners = 0;
		sc = new Scanner(System.in);
		players = new ArrayList<Player>();
		
		ladder.put(3, 15); ladder.put(7, 26); ladder.put(15, 28); ladder.put(22, 39);
		ladder.put(28, 93); ladder.put(42, 69); ladder.put(56, 88);ladder.put(65, 94);
	 
		snake.put(81, 18); snake.put(68, 6); snake.put(53, 11);
		snake.put(42, 14); snake.put(32, 8);
	}
	public static void main(String[] args) {
		SnakeLadderGame snakeLadderGame = new SnakeLadderGame();
		
		int noOfPlayers = snakeLadderGame.findPlayersNumber();
		snakeLadderGame.registerPlayers(noOfPlayers);
		snakeLadderGame.startGame();
		
	}

	private void startGame() {
		boolean isOver = false;
		int i ;
		for (i = 0; i < players.size() && !isOver;) {
			if (players.get(i).isWinner() == false) {
				System.out.print("Turn of Player : " + players.get(i).getName());
				move(i);
				isOver = isGameOver();
				System.out.println("\n");
				showPositions();

			} else {
				i = (i + 1) % players.size();
				continue;
			}

			i++;
			if (i == players.size()) {
				i = 0;
			}
		}
		
		showRanks(players);

	}

	private void showRanks(ArrayList<Player> players) {
		for(int i = 0 ; i < players.size() ; i++) {
			Player p = players.get(i);
			if(p.getRank() == null) {
				p.setRank(Rank.values()[players.size()-1].name());
			}
			System.out.println("Player : "+p.getName()+" Color : "+p.getColor()+" Rank : "+p.getRank());
		}
		
	}

	private boolean isGameOver() {
		if (noOfWinners == (players.size() - 1)) {
			return true;
		}
			return false;

	}

	private void showPositions() {
		for(int i = 0 ; i < players.size(); i++) {
			Player p = players.get(i);
			char w = p.isWinner()== true ? 'T':'F';
			char m = p.isMovable()== true ? 'T':'F';
			System.out.print("Player : "+p.getName()+" Color: "+p.getColor()+" Movable: "+m+" Winner: "+w+" Position : "+p.getPosition());
			System.out.println();
		}
		
	}

	private void move(int i) {
		System.out.print(" Press 1 Move and any other key to skip : ");
		int c = sc.nextInt();
		if(c == 1) {
		int initialPos = players.get(i).getPosition();
		boolean isMovable = players.get(i).isMovable();
		int generatedNumber = random.nextInt(6)+1;
		int count = 0;
		while (generatedNumber == 6) {
			System.out.println("Player "+players.get(i).getName()+" Number : "+generatedNumber);
			count++;
			if (count == 3) {
				System.out.println("3 Times 6 . So All Moves Cancelled s");
				players.get(i).setPositions(initialPos);
				break;
			}
			if (isMovable) {
				if(!move(i ,generatedNumber)) {
					break;
				}
			}
			else {
				isMovable = true;
				players.get(i).setIsMovable(true);
			}
			generatedNumber = random.nextInt(6) + 1;
		}
		if(count != 3) {
			System.out.println("Player "+players.get(i).getName()+" Number : "+generatedNumber);
			if(isMovable) {
				move(i ,generatedNumber);
			}
		}
		
		if(players.get(i).getPosition() == 100) {
			if(players.get(i).isWinner() == false) {
				players.get(i).setWinner(true);
				players.get(i).setRank(Rank.values()[noOfWinners].name());
				noOfWinners++;
			}
			System.out.println("Player : "+players.get(i).getName()+" Won ");
		}
		}
		else {
			System.out.println("Player : "+players.get(i).getName()+" Skipped ");
		}
	}


	

	private boolean move(int i, int generatedNumber) {
		Player p = players.get(i);
		if((p.getPosition()+generatedNumber ) > 100) {
			System.out.println("Player "+players.get(i).getName()+" is Unmovable .");
			return false;
		}
		players.get(i).setPositions(p.getPosition()+generatedNumber);
		if(ladder.containsKey(p.getPosition())) {
			int newPosition = ladder.get(p.getPosition());
			System.out.println("Player: "+p.getName()+" Moved up Ladder From "+p.getPosition()+" to "+newPosition);
			players.get(i).setPositions(newPosition);
			System.out.println();
		}
		if(snake.containsKey(p.getPosition())) {
			int newPosition = snake.get(players.get(i).getPosition());
			System.out.println("Player: "+p.getName()+" Moved Down Snake From "+p.getPosition()+" to "+newPosition);
			players.get(i).setPositions(newPosition);
		}
		return true;
	}

	private void registerPlayers(int noOfPlayers) {
		for (int i = 0 ; i < noOfPlayers ; i++) {
			Player p = new Player(String.valueOf((char)(65+i)), Colors.values()[i].name());
			players.add(p);
		}
		
	}

	private int findPlayersNumber() {
		System.out.print("Enter Number Of Players : ");
		int noOfPlayers;
		while (true) {
			noOfPlayers = sc.nextInt();
			if (noOfPlayers < 2 && noOfPlayers > 4) {
				System.out.println("Only 2 to 4 Players are allowed to play");
				System.out.println("Please Enter Number Of Players Again");
			} else
				break;
		}
		return noOfPlayers;
	}
}
/*
Final Output 
Player : A Color: R Movable: T Winner: T Position : 100
Player : B Color: G Movable: T Winner: T Position : 100
Player : C Color: B Movable: T Winner: F Position : 99
Player : D Color: Y Movable: T Winner: T Position : 100
Player : A Color : R Rank : SECOND
Player : B Color : G Rank : THIRD
Player : C Color : B Rank : FOURTH
Player : D Color : Y Rank : FIRST
 * */
