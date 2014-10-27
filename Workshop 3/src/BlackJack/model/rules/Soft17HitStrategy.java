package BlackJack.model.rules;

import java.util.List;

import BlackJack.model.Card;
import BlackJack.model.Player;

class Soft17HitStrategy implements IHitStrategy {
    private final int g_hitLimit = 17;

    public boolean DoHit(Player a_dealer) {
    	int score = a_dealer.CalcScore();
    	
    	if(score == 16)
    	{
    		Iterable<Card> cards = a_dealer.GetHand();
    		
    		for(Card card : cards){
    			if(card.GetValue() == Card.Value.Ace){
    				return true;
    			}
    		}
    	}
    	return false;
    }
}