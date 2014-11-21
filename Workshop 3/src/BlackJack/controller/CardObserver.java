package BlackJack.controller;

import java.util.Observable;
import java.util.Observer;

import BlackJack.model.Card;

public class CardObserver implements Observer{
	private String playerName;
	
	public CardObserver(String name){
		playerName = name;
	}
	
	@Override
	public void update(Observable o, Object card) {
		synchronized (card) {

			try {
				Card c = (Card)card;
				System.out.println("Dealt '" + c.GetValue().toString() + " of " + c.GetColor().toString() +"' to " + playerName);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
