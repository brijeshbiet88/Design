package com.interview.ds.system.design.snakeladder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class SnakeLadderGame {
	
	private static HashMap <Integer,Integer> ladder = new HashMap<Integer, Integer>();
	private static HashMap <Integer,Integer>  snake = new HashMap<Integer, Integer>();
	private static Random random = new Random();
	private static Scanner sc = new Scanner(System.in);
	static {
		
		ladder.put(3, 15);
		ladder.put(7, 26);
		ladder.put(15, 28);
		ladder.put(22, 39);
		ladder.put(28, 93);
		ladder.put(42, 69);
		ladder.put(56, 88);
		
		snake.put(99, 9);
		snake.put(81, 18);
		snake.put(68, 6);
		snake.put(53, 11);
		snake.put(42, 14);
		snake.put(32, 8);
		
	}

	public static void main(String[] args) {
		int noOfPlayers = findPlayersNumber();
		ArrayList<Player> players = new ArrayList<Player>();
		registerPlayers(players , noOfPlayers);
		startGame(players);
		
		
	}

	private static void startGame(ArrayList<Player> players) {
		boolean isOver = false;
		for (int i = 0; i < players.size() && !isOver;) {
			if (players.get(i).isWinner() == false) {
				System.out.print("Turn of Player : " + players.get(i).getName());
				move(players, i);
				isOver = isGameOver(players);
				System.out.println("\n");
				showPositions(players);

			} else
				continue;

			i++;
			if (i == players.size()) {
				i = 0;
			}
		}

	}

	private static boolean isGameOver(ArrayList<Player> players) {
		int countWinners = 0;
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).isWinner() == true) {
				countWinners++;
			}
		}

		if (countWinners == players.size() - 1)
			return true;
		return false;

	}

	private static void showPositions(ArrayList<Player> players) {
		for(int i = 0 ; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.print("Name: "+p.getName()+" Color: "+p.getColor()+" isMovable: "+p.isMovable()+" isWinner: "+p.isWinner()+" Position :"+p.getPosition());
			System.out.println();
		}
		
	}

	private static void move(ArrayList<Player> players, int i) {
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
				if(!move(players , i ,generatedNumber)) {
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
			//generatedNumber = random.nextInt(6)+1;
			System.out.println("Player "+players.get(i).getName()+" Number : "+generatedNumber);
			if(isMovable) {
				move(players , i ,generatedNumber);
			}
		}
		
		if(players.get(i).getPosition() == 100) {
			players.get(i).setWinner(true);
			System.out.println("Player : "+players.get(i).getName()+" Won ");
		}
		}
		else {
			System.out.println("Player : "+players.get(i).getName()+" Skipped ");
		}
	}


	

	private static boolean move(ArrayList<Player> players, int i, int generatedNumber) {
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

	private static void registerPlayers(ArrayList<Player> players , int noOfPlayers) {
		for (int i = 0 ; i < noOfPlayers ; i++) {
			Player p = new Player(String.valueOf((char)(65+i)), Colors.values()[i].name());
			players.add(p);
		}
		
	}

	private static int findPlayersNumber() {
		Scanner sc = new Scanner(System.in);
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
