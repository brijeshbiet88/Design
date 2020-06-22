package com.interview.ds.system.design.carddeck;

import java.util.List;

public class CardGame {

	public static void main(String[] args) {
		Deck deck = new Deck();
		printAllCards(deck);
	}

	private static void printAllCards(Deck deck) {
		
		List<Card> cards = deck.cardDeck;
		
		for(int i = 0 ; i < cards.size() ; ) {
			
			Card card = cards.get(i);
			if(card.faceValue < 10)
				System.out.print(card.faceValue+" "+card.suite+"		");
			else {
				System.out.print(card.faceValue+" "+card.suite+"	");
			}
			
			i++;
			if(i % 4 == 0) {
				System.out.println();
			}
		}
		
	}

}
