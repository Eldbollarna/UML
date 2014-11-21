package BlackJack.model.rules;

public class TieRules {

	  public boolean IsDealerWinner(int playerCalcScore, int dealerCalcScore) {
		  int maxScore = 21;
		  if(playerCalcScore > maxScore) {
			  return true;
		  }
		  else if(dealerCalcScore > maxScore) {
		  	return false;
		  }
	    return dealerCalcScore >= playerCalcScore;
	  }
	}
