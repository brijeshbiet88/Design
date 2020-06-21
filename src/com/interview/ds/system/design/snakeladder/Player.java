package com.interview.ds.system.design.snakeladder;

public class Player {

	private String name;
	private String color;
	private int position;
	private boolean isWinner;
	private boolean isMovable;
	private String rank;

	public Player(String name, String color) {
		super();
		this.name = name;
		this.color = color;
		this.position = 0;
		this.isWinner = false;
		this.isMovable = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPosition() {
		return position;
	}

	public void setPositions(int number) {
		this.position = number;
	}

	public boolean isWinner() {
		return isWinner;
	}

	public void setWinner(boolean isWinner) {
		this.isWinner = isWinner;
	}

	public boolean isMovable() {
		return isMovable;
	}

	public void setIsMovable(boolean isMovable) {
		this.isMovable = isMovable;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

}
