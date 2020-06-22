package com.interview.ds.system.design.carddeck;

public class Card {
	
	int faceValue;
	Suites suite;
	
	public Card(int faceValue , Suites suite) {
		this.faceValue = faceValue;
		this.suite = suite;
	}
}
