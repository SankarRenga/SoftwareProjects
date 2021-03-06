//Poker Card Game Card Class
//Prgrammed by: Guha and Sankar
//1/14/2015
//Hand Class
import hsa.Console;

public class Hand
{
  static Console c;
  private int card1;
  private int card2;
  private int minCard = 15;
  private int maxCard = 0;
  
  //Hand constructor
  //playerCard1 - One of the cards in the player's hand
  //playerCard2 - One of the cards in the player's hand
  public Hand (int playerCard1, int playerCard2)
  {
    
    //Set the value of cards to those in the player's hand
    card1 = playerCard1; 
    card2 = playerCard2;
    
  }
  
  //Get the value of card1
  public int getCard1 ()
  {
    return card1; //returns value
  }
  
  //Get the value of card2
  public int getCard2 ()
  {
    return card2; //returns value
  }
  
  //Change value of cards
  //number - the value that will replace the card
  public void changeCards (int number)
  {
    card1 = number;
    card2 = number;
  }
  
  //Check if player has a pair
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public boolean pair(Hand hand, int flop1, int flop2, int flop3)
  {
    //Check if there is only one matching pair
    if (card1 == card2 ^ card1 == flop1 ^ card1 == flop2 ^ card1 == flop3 ^ card2 == flop1 ^ card2 == flop2 ^ card2 == flop3)
      return true;  
    //If there is no pairs
    else
      return false;
  }
  
  //Check if player has a 3 of a kind
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public boolean threeOfAKind(Hand hand, int flop1, int flop2, int flop3)
  {
    //Checks if there is a 3 of a kind between the flop cards and card1
    if (card1 == flop1 && card1 == flop2 ^ card1 == flop1 && card1 == flop3 ^ card1 == flop2 && card1 == flop3)
      return true;
    //Checks if there is a 3 of a kind between the flop cards and card2
    else if (card2 == flop1 && card2 == flop2 ^ card2 == flop1 && card2 == flop3 ^ card2 == flop2 && card2 == flop3)
      return true;
    //Checks if there is a 3 of a kind between the flop cards, card1, and card2
    else if (card1 == card2 && card1 == flop1 ^ card1 == card2 && card1 == flop2 ^  card1 == card2 && card1 == flop3)
      return true;
    //If there is no 3 of a kind
    else
      return false;
  }
  
  //Check if player has a two pair
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public boolean twoPair(Hand hand, int flop1, int flop2, int flop3)
  {
    //Checks for two pairs
    if (card1 != card2 && card1 == flop1 ^ card1 == flop2 ^ card1 == flop3 && card2 == flop1 ^ card2 == flop2 ^ card2 == flop3 )
      return true;
    //If there is no two pair
    else
      return false;     
  }
  
  //Check if player has a 4 of a kind
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public boolean fourOfAKind(Hand hand, int flop1, int flop2, int flop3)
  {
    //Checks for a 4 of a kind between card1, card2, and flop
    if (card1 == card2 && card1 == flop1 && card1 == flop2 ^ card1 == card2 && card1 == flop3 && card1 == flop2 
          ^ card1 == card2 && card1 == flop3 && card1 == flop1)
      return true;
    //Checks for a 4 of a kind between card1 and flop
    else if (card1 != card2 && card1 == flop1 && card1 == flop2 && card1 == flop3)
      return true;
    //Checks for a 4 of a kind between card2 and flop
    else if (card1 != card2 && card2 == flop1 && card2 == flop2 && card2 == flop3)
      return true;
    //If there is no 4 of a kind
    else 
      return false;
  }
  
  //Check if player has a full house
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public boolean fullHouse (Hand hand, int flop1, int flop2, int flop3)
  {
    //Check if player has a pair and 3 of a kind
    if (this.pair(hand, flop1, flop2, flop3) && this.threeOfAKind(hand, flop1, flop2, flop3) && (card1 != card2))
      return true;
    //If there is no full house
    else
      return false;
  }
  
  //Check if player has a flush
  //suit1 - the suit of the first card in the player's hand
  //suit2 - the suit of second card in player's hand
  //suit3 - the suit of the first flop card
  //suit4 - the suit of the second flop card
  //suit5 - the suit of the third flop card
  public boolean flush (String suit1, String suit2, String suit3, String suit4, String suit5)
  {
    if (suit1 == suit2 && suit1 == suit3 && suit1 == suit4 && suit1 == suit5) //If they all equal the same
      return true;
    else //If the suits are not equal
      return false;
  }
  
  //Check if player has a form of a straight
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  //suit1 - the suit of the first card in the player's hand
  //suit2 - the suit of second card in player's hand
  //suit3 - the suit of the first flop card
  //suit4 - the suit of the second flop card
  //suit5 - the suit of the third flop card
  public String straight(Hand hand, int flop1, int flop2, int flop3, String suit1, String suit2, String suit3, String suit4, String suit5)
  {
    //Declare array to store all card values
    int cards [] = new int [5];
    //Array initialization
    cards [0] = card1;
    cards [1] = card2;
    cards [2] = flop1;
    cards [3] = flop2;
    cards [4] = flop3;
    //Declare array to sort cards from smallest to largest
    int smallCard [] = new int [5];
    
    for (int x = 0; x <= 4 ; x++) //Loops to set value for all 5 cards
    {
      for (int loop = 4; loop >= 0 ; loop--) //Loops to find the minimum value of the 5 cards
      {
        minCard = Math.min (minCard, cards[loop]); //Sets minimum value between two cards
      }
      //Set the value of a card to 15 if they are equal to the minimum value
      if (minCard == cards[0])
      {
        cards[0] = 15;
      }
      else if (minCard == cards[1])
      {
        cards[1] = 15;
      } 
      else if (minCard == cards[2])
      {
        cards[2] = 15;
      } 
      else if (minCard == cards[3])
      {
        cards[3] = 15;
      } 
      else if (minCard == cards[4])
      {
        cards[4] = 15;
      } 
      smallCard [x] = minCard; //Set it to the value of minCard
    }
    
    //Loops in order to sort the cards array from min to max
    for (int x = 0; x <= 4 ; x++)
    {
      cards [x] = smallCard [x]; //Sorts array
    }
    
    //If player has a straight
    if (cards [0] == cards [1] - 1 && cards [1] == cards [2] - 1 && cards [2] == cards [3] - 1 && cards [3] == cards [4] - 1 && suit1 != suit2)
      return ("Straight");
    //If player has a royal flush
    else if (cards [0] == 10 && cards [1] == 11 && cards [2] == 12 && cards [3] == 13 && cards [4] == 14
               && suit1 == suit2 && suit1 == suit3 && suit1 == suit4 && suit1 == suit5)
      return ("RoyalFlush");
    //If player has a straight flush
    else if (cards [0] == cards [1] - 1 && cards [1] == cards [2] - 1 && cards [2] == cards [3] - 1 && cards [3] == cards [4] - 1
               && suit1 == suit2 && suit1 == suit3 && suit1 == suit4 && suit1 == suit5)
      return ("StraightFlush");
    //If player has nothing
    else
      return ("None");
  }
  
  //Evaluate the rank of the player's hand
  //hand - the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  //suit1 - the suit of the first card in the player's hand
  //suit2 - the suit of second card in player's hand
  //suit3 - the suit of the first flop card
  //suit4 - the suit of the second flop card
  //suit5 - the suit of the third flop card
  public int evaluate (Hand hand, int flop1, int flop2, int flop3, String suit1, String suit2, String suit3, String suit4, String suit5)
  {
    //Return a number value depending on the rank of their hand
    
    //if player has a royal flush
    if (this.straight(hand, flop1, flop2, flop3, suit1, suit2, suit3, suit4, suit5).equals("RoyalFlush"))
    {
      return (10);
    }
    
    //if player has a straight flush
    else if (this.straight(hand, flop1, flop2, flop3, suit1, suit2, suit3, suit4, suit5).equals("StraightFlush"))
    {
      return (9);
    }
    
    //if player has a 4 of a kind
    else if (this.fourOfAKind(hand, flop1, flop2, flop3))
    {
      return (8);
    }
    
    //If player has a full house
    else if (this.fullHouse(hand, flop1, flop2, flop3))
    {
      return (7);
    }
    
    //if player has a straight
    else if (this.straight(hand, flop1, flop2, flop3, suit1, suit2, suit3, suit4, suit5).equals("Straight"))
    {
      return (6);
    }
    
    //if player has a flush
    else if (this.flush(suit1, suit2, suit3, suit4, suit5))
    {
      return (5);
    }
    
    //if player has a 3 of a kind
    else if (this.threeOfAKind(hand, flop1, flop2, flop3))
    {
      return (4);
    }
    
    //if player has a two pair
    else if (this.twoPair(hand, flop1, flop2, flop3))
    {
      return (3);
    }
    
    //if player has a pair
    else if (this.pair(hand, flop1, flop2, flop3))
    {
      return (2);
    }
    
    //if player has a high card
    else
      return (1);
  }
  
  //Check which of the cards in the player's hand is involved with the flop
  //card - one of the player's cards
  //score - the rank of the player's hand
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public int cardCheck (int card, int score, int flop1, int flop2, int flop3)
  {
    
    //If the player has a pair
    if (score == 2)
    {
      //Check if card1 is involved in the pair
      if (card == flop1 ^ card == flop2 ^ card == flop3)
        return card1; //Return value of card1
      //If card1 is not involved in the pair
      else
        return card2; //Return value of card2
    }
    
    //If player has a 3 of a kind
    else if (score == 4)
    {
      //Check if card1 is involved in the 3 of a kind
      if (card == flop1 && card == flop2 ^ card == flop1 && card == flop3 ^ card == flop2 && card == flop3)
        return card1; //Return value of card1
      //If card1 is not involved in the 3 of a kind
      else
        return card2; //Return value of card2
    }
    
    //If player has 4 of a kind
    else if (score == 8)
    {
      //Check if card1 is involved in the 4 of a kind
      if (card == flop1 && card == flop2 && card == flop3)
        return card1; //Return value of card1
      //If card1 is not involved in the 4 of a kind
      else
        return card2; //Return value of card2
    }
    else
      return card2; //return card2
  }
  
  //Determine a winner if players have the same rank for 2 players
  //score - the rank of the player's hand
  //hand1 - the hand of player 1
  //hand2 - the hand of player 2
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public int tieBreaker (int score, Hand hand1, Hand hand2, int flop1, int flop2, int flop3, int counter, int counter2)
  {
    //Array Declaration
    int handCard [] = new int [4];
    //Set values of elements to the cards of the players
    handCard[0] = hand1.getCard1();
    handCard[1] = hand1.getCard2();
    handCard[2] = hand2.getCard1();
    handCard[3] = hand2.getCard2();
    int bigCard1; //The highest card for player 1
    int bigCard2; //The highest card for player 2
    
    //If the player has a rank that involves both their cards
    if (score == 1 ^ score == 3 ^ score == 5 ^ score == 6 ^ score == 7 ^ score == 9 ^ score == 10)
    {
      //Loop the amount of player's hands
      for (int x = 0; x < handCard.length; x+=2)
      {
        maxCard = Math.max (maxCard, handCard[x]); //Determines max card between the cards in the player's hands
      }
      
      //If player 1 has the high card
      if (maxCard == handCard [0] ^ maxCard == handCard [1])
        return (counter);
      //If player 2 has the high card
      else if (maxCard == handCard [2] ^ maxCard == handCard [3])
        return (counter2);
      //If it is a tie
      else 
        return (10);
    }
    
    else if (score == 2 ^ score == 4 ^ score == 8)
    {
      //Check which card of the player's hand is involved in the rank
      bigCard1 = hand1.cardCheck (handCard [0], score, flop1, flop2, flop3);
      bigCard2 = hand2.cardCheck (handCard [2], score, flop1, flop2, flop3);
      //Determine the highest card
      maxCard = Math.max (bigCard1, bigCard2);
      //If player 1 has the high card
      if (maxCard == bigCard1)
        return (0);
      //If player 2 has the high card
      else if (maxCard == bigCard2)
        return (1);
      //If it is a tie
      else 
        return (10);
    }
    else
      return (10);
    
  }
  
  //Determine a winner if players have the same rank for 3 players
  //score - the rank of the player's hand
  //hand1 - the hand of player 1
  //hand2 - the hand of player 2
  //hand3 - the hand of player 3
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public int tieBreaker (int score, Hand hand1, Hand hand2, Hand hand3, int flop1, int flop2, int flop3)
  {
    //Array Declaration
    int handCard [] = new int [4];
    //Set values of elements to the cards of the players
    handCard[0] = hand1.getCard1();
    handCard[1] = hand1.getCard2();
    handCard[2] = hand2.getCard1();
    handCard[3] = hand2.getCard2();
    handCard[4] = hand2.getCard1();
    handCard[5] = hand2.getCard2();
    int bigCard1; //The highest card for player 1
    int bigCard2; //The highest card for player 2
    int bigCard3; //The highest card for player 3 
    
    //If the player has a rank that involves both their cards
    if (score == 1 ^ score == 3 ^ score == 5 ^ score == 6 ^ score == 7 ^ score == 9 ^ score == 10)
    {
      //Loop the amount of player's hands
      for (int x = 0; x < handCard.length; x+=2)
      {
        maxCard = Math.max (maxCard, handCard[x]); //Determines max card between the cards in the player's hands
      }
      
      //If player 1 has the high card
      if (maxCard == handCard [0] ^ maxCard == handCard [1])
        return (0);
      //If player 2 has the high card
      else if (maxCard == handCard [2] ^ maxCard == handCard [3])
        return (1);
      //If player 3 has the high card
      else if (maxCard == handCard [4] ^ maxCard == handCard [5])
        return (2);
      //If it is a tie
      else 
        return (10);
      
    }
    
    else if (score == 2 ^ score == 4 ^ score == 8)
    {
      //Check which card of the player's hand is involved in the rank
      bigCard1 = hand1.cardCheck (handCard [0], score, flop1, flop2, flop3);
      bigCard2 = hand2.cardCheck (handCard [2], score, flop1, flop2, flop3);
      bigCard3 = hand3.cardCheck (handCard [4], score, flop1, flop2, flop3);
      //Determine the highest value between cards
      maxCard = Math.max (bigCard1, bigCard2);
      maxCard = Math.max (maxCard, bigCard3);
      //If player 1 has the high card
      if (maxCard == bigCard1)
        return (0);
      //If player 2 has the high card
      else if (maxCard == bigCard2)
        return (1);
      //If player 3 has the high card
      else if (maxCard == bigCard3)
        return (2);
      //If there is a tie
      else 
        return (10);
    }
    else
      return (10);
    
  }
  
  //Determine a winner if players have the same rank for 4 players
  //score - the rank of the player's hand
  //hand1 - the hand of player 1
  //hand2 - the hand of player 2
  //hand3 - the hand of player 3
  //hand4 - the hand of player 4
  //flop1 - the first flop card
  //flop2 - the second flop card
  //flop3 - the third flop card
  public int tieBreaker (int score, Hand hand1, Hand hand2, Hand hand3, Hand hand4, int flop1, int flop2, int flop3)
  {
    //Array Declaration
    int handCard [] = new int [8];
    //Set value of elements to the crads of the player's hands
    handCard[0] = hand1.getCard1();
    handCard[1] = hand1.getCard2();
    handCard[2] = hand2.getCard1();
    handCard[3] = hand2.getCard2();
    handCard[4] = hand2.getCard1();
    handCard[5] = hand2.getCard2();
    handCard[6] = hand2.getCard1();
    handCard[7] = hand2.getCard2();
    
    //If the player has a rank that involves both their cards
    if (score == 1 ^ score == 3 ^ score == 5 ^ score == 6 ^ score == 7 ^ score == 9 ^ score == 10)
    {
      //Loop the amount of player's hands
      for (int x = 0; x < handCard.length; x+=2)
      {
        maxCard = Math.max (maxCard, handCard[x]); //Determines max card between the cards in the player's hands
      }
      
      //If player 1 has the high card
      if (maxCard == handCard [0] ^ maxCard == handCard [1])
        return (0);
      //If player 2 has the high card
      else if (maxCard == handCard [2] ^ maxCard == handCard [3])
        return (1);
      //If player 3 has the high card
      else if (maxCard == handCard [4] ^ maxCard == handCard [5])
        return (2);
      //If player 4 has the high card
      else if (maxCard == handCard [6] ^ maxCard == handCard [7])
        return (3);
      //If it is a tie
      else 
        return (10);
      
    }
    else 
      return (10);
    
  }
}






