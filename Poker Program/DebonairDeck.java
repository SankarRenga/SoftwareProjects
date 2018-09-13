import hsa.Console; 
import java.util.*;

//Start Class
public class DebonairDeck
{
  static Console c;
  //Initialize array for suit values
  private String suitArray[] = new String[] {"S","H","D","C"};
  //Initialize array for card values
  private String valueArray[] = new String[] {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
  //number of cards
  private int cardNum = 0;
  //Array of Debonair Card objects 
  private DebonairCard deck[];
  //Constructor
  public DebonairDeck()
  {
    //Deck has 52 cards
    deck = new DebonairCard[52];
    //Loops through all the 4 suits
    for ( int suit = 0; suit <= 3; suit++ )
    {
      //Turns the value of the deck array at positions increasing upto 52 
      //Loops 13 times so there are 13 cards of the same suit and the same 13 different values
      for ( int value = 0; value < 13; value++ ) 
      {
        String cardValue = valueArray[value];
        String cardSuit = suitArray[suit];
        deck[cardNum] = new DebonairCard(cardSuit,cardValue);
        cardNum += 1;
      }
    }
  }
  
  //Randomize array
  public void shuffle()
  {
    Collections.shuffle(Arrays.asList(deck)); //Shuffles deck
  }
  
  //Display player's card
  //c - Where output is shown
  //x - x-coordinate where top left of card is drawn
  //y - y-coordinate where top left of card is drawn
  //topCard - the top card of the array
  public void deal(Console c,int x,int y,int topCard)
  {
    deck[topCard].displayCard(c,x,y); //display card
  }
  
  //Get the value of the card
  //topCard - the top card of the array
  public int getValue (int topCard)
  {
    return (deck[topCard].cardValue()); //returns value
  }
  
  //Get the suit of the card
  //topCard - the top card of the array
  public String suitValue (int topCard)
  {
    return (deck[topCard].getSuit()); //returns suit
  }
  
}
