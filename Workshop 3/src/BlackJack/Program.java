package BlackJack;

import BlackJack.model.EventSource;
import BlackJack.model.Game;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program
{

  public static void main(String[] a_args)
  {
    
    System.out.println("Enter any key to start the game!");
    
    // create an event source - reads from stdin
    final EventSource eventSource = new EventSource();

    // create an observer
    final PlayGame responseHandler = new PlayGame();

    // subscribe the observer to the event source
    eventSource.addObserver(responseHandler);

    // starts the event thread
    Thread thread = new Thread(eventSource);
    thread.start();

  }
}