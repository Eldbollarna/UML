package BlackJack;

import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program
{

  public static void main(String[] a_args)
  {
    Game g = new Game();
    IView v = new SimpleView();
    PlayGame ctrl = new PlayGame();
    
    CardObserver playerObserver = new CardObserver("Player");
	CardObserver dealerObserver = new CardObserver("Dealer");
	g.getPlayer().addObserver(playerObserver);
	g.getDealer().addObserver(dealerObserver);
    while(ctrl.Play(g,v));
    
  }
  
}