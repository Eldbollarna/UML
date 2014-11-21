package BlackJack.controller;

import BlackJack.model.Game;
import BlackJack.view.IView;

 
public class PlayGame  {
   
	public enum MenuItems {
		Play,
		Hit,
		Stand,
		Quit,
		Invalid
	}
	
    public boolean Play(Game a_game, IView a_view) {
    	a_view.DisplayWelcomeMessage();
        
        a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
        a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

        if (a_game.IsGameOver())
        {
            a_view.DisplayGameOver(a_game.IsDealerWinner());
        }

        MenuItems input = a_view.GetInput();
        
        switch (input) {
        case Play:
			a_game.NewGame();
			break;
        case Hit:
			a_game.Hit();
			break;
        case Stand:
			a_game.Stand();
			break;
        case Quit:
			return false;
        }
        return true;
    }
}