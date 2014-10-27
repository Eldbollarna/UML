package BlackJack.controller;

import java.util.Observable;
import java.util.Observer;  /* this is Event Handler */

import BlackJack.model.Game;
import BlackJack.view.IView;
import BlackJack.view.SimpleView;
 
public class PlayGame implements Observer {
    Game a_game = new Game();
    IView a_view = new SimpleView();
    public void update(Observable obj, Object arg) {
        if (arg instanceof String) {
            
            a_view.DisplayWelcomeMessage();
            
            a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
            a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

            if (a_game.IsGameOver())
            {
                a_view.DisplayGameOver(a_game.IsDealerWinner());
            }

            int input = a_view.GetInput();
            
            if (input == 'p')
            {
                a_game.NewGame();
            }
            else if (input == 'h')
            {
            	try {
        			Thread.sleep(2000);
        		} catch (InterruptedException e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
                a_game.Hit();
            }
            else if (input == 's')
            {
            	try {
    			Thread.sleep(2000);
    		} catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
                a_game.Stand();
            }
            else if (input == 'q'){
            	System.exit(0);
            }
          }
        }
    }

