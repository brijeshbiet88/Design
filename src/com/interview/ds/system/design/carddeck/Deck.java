package com.interview.ds.system.design.carddeck;

import java.util.ArrayList;
import java.util.List;

public class Deck {
	
	List<Card> cardDeck ;
	
	public Deck() {
		cardDeck = new ArrayList<Card>();
		for(int i = 1 ; i <= 13 ; i++ ) {
			for(Suites suites : Suites.values()) {
				cardDeck.add(new Card(i , suites));
			}
			
		}
	}
	

}
