package BlackJack.model;

import BlackJack.model.rules.*;

public class Dealer extends Player {

  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private TieRules m_tieRules;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_tieRules = a_rulesFactory.GetTieRules();
  }
  
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);   
    }
    return false;
  }

  public void DealCard(boolean show, Player a_player)
  {
	  Card c;
	  c = m_deck.GetCard();
      c.Show(show);
      a_player.DealCard(c);
  }
  
  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {
      DealCard(true, a_player);
      return true;
    }
    return false;
  }
  
  public boolean Stand(Player a_player) {
    if (m_deck != null) {
      this.ShowHand();
      
      while(m_hitRule.DoHit(this)) {
    	  DealCard(true, this);
      }
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
	  if (a_player.CalcScore() > g_maxScore) {
	      return true;
	    } else if (CalcScore() > g_maxScore) {
	      return false;
	    }
	  if(a_player.CalcScore() == CalcScore()){
		  return m_tieRules.IsDealerWinner();
	  }
	  else{
		  return a_player.CalcScore() <= CalcScore();
	  }
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }
  
}