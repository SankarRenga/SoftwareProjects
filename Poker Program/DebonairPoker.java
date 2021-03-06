//Summative
//Programmed by Guha and Sankar
//Modified: 14/01/2015
//The main method for Debonair Poker useses all the classes created and image output to create a simulated Poker Game in Java

import hsa.Console;
import java.awt.*; //importing
import java.io.*;        
import javax.imageio.*;  

public class DebonairPoker
{
  static Console c; //Console for the flop cards 
  static Console player []; //player's console's as an array
  static double playerBet[]; //The player's individual bet
  static double pot; //Money bet from each player
  static double bet = 10; //How much a player will bet
  static double raise; //How much a player will raise
  static String gameChoice; //Whether the user wishes to play
  static boolean fold []; //Whether te user wishes to keep their cards for the turn
  static double playerMoney []; //Players money values
  static int flopCard1; //Value of the first flop card
  static int flopCard2; //Value of the second flop card
  static int flopCard3; //Value of the third flop card
  static int counter; //counts loop
  static int tieCounter; //Counts number of ties
  static int foldCounter; //Counts how many people folded
  static String flopCard1Suit; //Suit of the first flop card
  static String flopCard2Suit; //Suit of the second flop card
  static String flopCard3Suit; //Suit of the third flop card
  static boolean turnEnd;
  static String input;
  static boolean userRaise = false;
  static boolean userBet = false;
  
  public static void main (String[ ] args) throws IOException
  {
    c = new Console (35,115);
    player = new Console [4]; //console for the players
    
    //Variable Declarations
    
    int playerScore [] = new int [4]; //The rank of the player's hand
    String turnSelection []; //What the user wants to do on their turn
    String playerName []; //The player's name
    int topCard = 0; //The top card of the deck
    int winner; //The winner of the round
    int bestHand = 0; //The best hand for that turn
    double ante = 10; //Starting bet for each round
    Image img; //Menu image
    
    //Card Variables
    Hand playerHand [];
    String cardSuit [];
    
    DebonairDeck debonairDeck = new DebonairDeck();//New Debonair Deck is created
    
    //Import the menu picture
    try
    {
      
      img = ImageIO.read(new File("menu.jpg")); 
      c.drawImage(img,0,0,null); //Display the image on main console
      c.setCursor(32,7); 
      gameChoice = c.readLine(); //prompts user for what they would like to do
    }
    
    catch(IOException e)  //File not found
    {
      img = null;
    }
    
    //User wishes to quit
    if (gameChoice.equalsIgnoreCase("quit"))
    {
      c.close();
    }
    
    //rules are read from a text file and then printed onto the screen 
    else if (gameChoice.equalsIgnoreCase("Rules"))
    {
      c.clear();
      //Display the rules
      BufferedReader pokerRules;
      pokerRules = new BufferedReader (new FileReader("rules.txt")); //Import text file
      String line = pokerRules.readLine();
      while (line != null)
      {
        line = pokerRules.readLine();
        c.println(line);
      }
      c.println ("Enter any key to continue with the game");
      c.readLine();
      pokerRules.close();
      //Continue to game
      c.clear();
      gameChoice = "yes";
    }
    
    //The Poker Game
    if (gameChoice.equalsIgnoreCase("yes"))
    {
      c.clear();
      c.println("How many players will be playing?(*MAX 4 players)");
      int numberPlayers = c.readInt(); //user enters amount of players
      if(numberPlayers == 1 ^ numberPlayers > 4)
      {
        c.println("Entered players is not valid");
        c.println("Debonair Poker is a 2-4 player game. Enter an appropriate amount of players:");
        numberPlayers = c.readInt();
      }
      //Set all info need for game for each player
      playerName = new String [numberPlayers]; //Player's name
      playerMoney = new double [4]; //How much money the player has
      turnSelection = new String [numberPlayers]; //Player's choice
      playerHand = new Hand [numberPlayers]; //Player's hand
      cardSuit = new String [numberPlayers*2]; //The suit of the cards
      playerScore = new int [4]; //The player's hand rank
      fold = new boolean [4]; //If a player folds
      playerBet = new double [numberPlayers]; //The player's bet
      
      //Asks for names of every player
      for (int x = 0; x < numberPlayers; x++)
      {
        c.println ("Player " + (x + 1) + ", what is your name");
        playerName [x] = c.readLine(); //User inputs name
      }
      
      for (int x = 0; x < 4; x++)
      {
        playerScore [x] = 0; //Initializes consoles
      }
      
      //Create consoles for the players
      for (int x = 0; x < numberPlayers; x++)
      {
        player [x] = new Console(); //Initializes consoles
      }
      
      //Money Loop
      for (int x = 0; x < 4; x++)
      {
        playerMoney [x] = 10000; //Sets each player's money to $10,000
      }
      
      //Loops the 2 betting phases
      while (playerMoney [0] != (numberPlayers*10000.0) && playerMoney [1] != (numberPlayers*10000.0) && playerMoney [2] != (numberPlayers*10000.0) && (playerMoney [3] != numberPlayers*10000.0))
      {
        debonairDeck.shuffle(); //Shuffles deck
        pot = 0;
        pot = pot + (ante*numberPlayers); //Increase amount in the pot by the ante of every player
        foldCounter = 0;
        c.clear();
        
        for (int x = 0; x < numberPlayers; x++)
        {
          playerMoney[x] -= 10; 
        }
        
        for (int x = 0; x < numberPlayers; x++)
        {
          player [x].clear();
        }
        //Loops the player's fold choices
        for (int x = 0; x < 4; x++)
        {
          fold [x] = false; //Sets each player's fold choice to false
        }
        for (int x = 0; x < numberPlayers; x++)
        {
          if (playerMoney [x] == 0)
            fold[x] = true;
        }
        
        for (int loop = 0; loop <= 1; loop++)
        {
          turnEnd = false;
          userBet = false;
          userRaise = false;
          counter = 0; //The counter for suits
          int counter2 = 0;
          int loopCounter = 0;
          //Loops the player's bets
          for (int x = 0; x < numberPlayers; x++)
          {
            playerBet [x] = 0; //Sets each player's bet to 0
          }
          //For loop that performs all actions for a player during their turn
          while(!(turnEnd) && pot != numberPlayers*10000)
          {
            if (loopCounter >= numberPlayers)
              loopCounter = 0;
            //If player still has money
            if (playerMoney [loopCounter] != 0) 
            {
              if (counter2 < numberPlayers && loop == 0)
              {
                player [loopCounter].println (playerName [loopCounter]);  //Prints player's name      
                //Display cards for player
                debonairDeck.deal(player[loopCounter],10,20,topCard);
                cardSuit[counter] = debonairDeck.suitValue(topCard);
                topCard += 1; //Sets up for next card
                //Display second card
                debonairDeck.deal(player[loopCounter],75,20,topCard);
                cardSuit[counter + 1] = debonairDeck.suitValue(topCard);
                playerHand [loopCounter] = new Hand (debonairDeck.getValue (topCard - 1), debonairDeck.getValue (topCard));
                topCard += 1; //Sets up for next card
                counter = counter + 2;
                player[loopCounter].setCursor (6,1);
                counter2++; //Counts if hand has already been displayed
              }
              
              //Prompt user for what they would like to do
              if (fold[loopCounter] == false)
              {
                player [loopCounter].println ("Your Money: " + playerMoney [loopCounter]);
                
                if (playerMoney [loopCounter] == 0)
                {
                  turnSelection [loopCounter] = "check";
                }
                
                //If someone has bet
                else if(userBet == true && userRaise == false)
                {
                  player [loopCounter].println ("A player has bet $" + (bet));
                  player [loopCounter].println ("Would you like to call, raise, or fold");
                  turnSelection [loopCounter] = player[loopCounter].readLine(); //user input
                  bettingPhase (turnSelection [loopCounter], player[loopCounter], loopCounter); //Calls bettingPhase method
                  playerBet [loopCounter] = bet; //Sets value of the player's bet to the total bet
                }
                
                //If someone has raised
                else if (userRaise == true)
                {
                  player [loopCounter].println ("A player has raised by $" + (raise));
                  player [loopCounter].println ("Total bet: " + (bet));
                  player [loopCounter].println ("Would you like to call, raise, or fold");
                  turnSelection [loopCounter] = player[loopCounter].readLine(); //user input
                  bettingPhase (turnSelection [loopCounter], player[loopCounter], loopCounter); //Calls bettingPhase method
                  playerBet [loopCounter] = bet;
                }
                
                //If nothing has happened in the turn
                else
                {
                  player[loopCounter].println("Would you like to check, bet or fold?");
                  turnSelection [loopCounter] = player[loopCounter].readLine();
                  if(turnSelection [loopCounter].equalsIgnoreCase("Raise") && bet == 0)
                  {
                    player[loopCounter].println("Raise not applicable. Enter a valid turn selection");
                    turnSelection [loopCounter] = player[loopCounter].readLine();
                  }
                  bettingPhase (turnSelection [loopCounter],  player[loopCounter], loopCounter); //Calls bettingPhase method
                  playerBet [loopCounter] = bet;
                }
              }
              else
              {
                playerBet [loopCounter] = bet;
                fold[loopCounter] = true;
              }
              
              counter = 0; //Counts the number of bets
              foldCounter = 0; //Counts number of folds
              for(int x = 0; x < numberPlayers; x++)
              {
                //If everyone has bet the same
                if (playerBet [x] == bet)
                  counter++;
                //If player has not folded
                if (fold[x] == false)
                  foldCounter++;
              }
              //If only one player has not folded or all the bets are equal
              if (counter == numberPlayers || foldCounter == 1)
                turnEnd = true;
              
              loopCounter++;
            }
          }
          
          //If only one person is still in the turn break loop
          int moneyCheck = 0;
          if (foldCounter == 1)
          {
            break;
          }
          
          
          //If there are more than 1 person that has not folded
          else if (foldCounter != 1)
          {
            
            //Display flop
            if (loop == 0)
            {
              c.println("THE FLOP:");
              //Display the flop cards and set up for next card being dealt
              topCard+=1;
              debonairDeck.deal(c,10,20,topCard);
              flopCard1 = debonairDeck.getValue (topCard);
              flopCard1Suit = debonairDeck.suitValue(topCard);
              topCard+=1; 
              debonairDeck.deal(c,75,20,topCard);
              flopCard2 = debonairDeck.getValue (topCard);
              flopCard2Suit = debonairDeck.suitValue(topCard);
              topCard+=1; 
              debonairDeck.deal(c,140,20,topCard);
              flopCard3 = debonairDeck.getValue (topCard);
              flopCard3Suit = debonairDeck.suitValue(topCard);
              topCard+=1;
              c.setCursor(6,1);
              //Display the pot and ante
              c.println("POT Total: $" + pot);
              c.println("Current Ante: $" + ante);
            }
          }
          if (moneyCheck == 1)
          {
            break;
          }
          for (int x = 0; x < numberPlayers; x++)
          {
            if (fold[x] == false);
            {
              if (playerMoney [x] == 0)
                moneyCheck = 1;
            }
          }
        }
        //If only one person has not folded
        if (foldCounter == 1)
        {
          for(int x = 0; x < numberPlayers; x++)
          {
            //Player wins by default
            if (fold[x] == false)
            {
              //Display their victory
              c.println (playerName[x] + " has won by default");
              c.println (playerName [x] + " has earned $" + pot);
              playerMoney [x] += pot; //Automatically earns money
              for(int y = 0; y < numberPlayers; y++)
              {
                playerScore [y] = 0;
              }
            }
          }
        }
        
        //If more than 1 person has not folded
        if (foldCounter != 1)
        {
          
          counter = 0;
          //Evaluate the rank of each hand for each player
          for(int x = 0; x < numberPlayers; x++)
          {
            playerScore[x] = playerHand[x].evaluate (playerHand [x], flopCard1, flopCard2, flopCard3, cardSuit[counter], cardSuit [counter + 1], flopCard1Suit, flopCard2Suit, flopCard3Suit);
            counter = counter + 2;
          }
          
          //Loops for every player
          for(int x = 0; x < numberPlayers; x++)
          {
            if (fold[x]) //If player has folded
            {
              //Set their hand and score to 0 so it can not be evaluated
              playerHand [x].changeCards(0);
              playerScore [x]  = 0;
            }
          }
          
          for(int x = 0; x < numberPlayers; x++)
          {
            bestHand = Math.max (bestHand, playerScore [x]); //Determines best hand
          }
          
          //If there is a tie between 4 people
          if (bestHand == playerScore [0] && bestHand == playerScore [1] && bestHand == playerScore [2] && bestHand == playerScore [3])
          {
            //Evaluate the best hand
            winner = playerHand [0].tieBreaker (bestHand, playerHand [0], playerHand [1], playerHand [2], playerHand [3], flopCard1, flopCard2, flopCard3); 
            c.print (playerName [winner] + " has won with a ");
            displayRank(bestHand);
            c.println (playerName [winner] + " has earned $" + pot);
            playerMoney [winner] += pot; //The winner takes the pot
          }
          
          //If there is a tie between 3 people
          else if (bestHand == playerScore [0] && bestHand == playerScore [1] && bestHand == playerScore [2])
          {
            //Evaluate hands
            winner = playerHand [0].tieBreaker (bestHand, playerHand [0], playerHand [1], playerHand [2], flopCard1, flopCard2, flopCard3); 
            c.print (playerName [winner] + " has won with a ");
            displayRank(bestHand);
            c.println (playerName [winner] + " has earned $" + pot);
            playerMoney [winner] += pot; //Winner takes pot
          }
          
          //If there is a tie between 2 people
          else if (bestHand == playerScore [0] && bestHand == playerScore [1] ^ bestHand == playerScore [0] && bestHand == playerScore [2] ^ bestHand == playerScore [0] && bestHand == playerScore [3] ^
                     bestHand == playerScore [1] && bestHand == playerScore [2] ^ bestHand == playerScore [1] && bestHand == playerScore [2] ^ bestHand == playerScore [2] && bestHand == playerScore [3])
          {
            //Check which of the players has mathcing ranks
            if (bestHand == playerScore [0])
              counter = 0;
            
            if (bestHand == playerScore [1])
            {
              if (bestHand != playerScore [0])
                counter = 1;
              else
                tieCounter = 1;
            }
            
            if (bestHand == playerScore [2])
            {
              if (bestHand != playerScore [0] && bestHand != playerScore[1])
                counter = 2;
              else
                tieCounter = 2;
            }
            
            if (bestHand == playerScore [3])
            {
              tieCounter = 3;
            }
            //Evaluate hands
            winner = playerHand [counter].tieBreaker (bestHand, playerHand [counter], playerHand [tieCounter], flopCard1, flopCard2, flopCard3, counter, tieCounter); 
            c.print (playerName [winner] + " has won with a ");
            displayRank(bestHand);
            c.println (playerName [winner] + " has earned $" + pot);
            playerMoney [winner] += pot; //Player takes pot
          } 
          
          //If there is a straight up victory
          else
          {
            for(int x = 0; x < numberPlayers; x++)
            {
              if (playerScore [x] == bestHand)
              {
                c.print (playerName [x] + " has won with a ");
                displayRank(bestHand);
                c.println (playerName [x] + " has earned $" + pot);
                playerMoney [x] += pot; //Player takes pot
              }
            }
          } 
        }
        c.println ("Enter any letter key to continue");
        input = c.readLine(); //Proceeds to next round
      }
      for(int x = 0; x < numberPlayers; x++)
      {
        if (playerMoney [x] == 10000*numberPlayers) //If a player has collected all the money
        {
          c.println (playerName [x] + " has won the tournament");
        }
      }
      
      
    }
  }//end main
  
  //Calculates how much money the user bets or raises and ends their turn if they fold
  //choice - what the player chooses
  //c - the console the prompts should appear
  //x - the stage of the loop
  public static void bettingPhase (String choice, Console c, int x)
  {
    if (choice.equalsIgnoreCase ("Check"))
    {
      fold [x] = false;
    }
    
    else if (choice.equalsIgnoreCase ("Call"))
    {
      pot += bet;
      if (playerMoney[x] < bet)
      {
        playerMoney[x] = playerMoney[x] - playerMoney[x];
        pot += playerMoney[x];
      }
      if (playerBet[x] != 0 && userRaise == true)
        playerMoney[x] -= raise;
      else if (playerBet [x] == 0 && playerMoney [x] >= bet)
        playerMoney[x] -= bet;
      else
        playerMoney[x] -= bet;
    }
    //if user decides to bet
    else if (choice.equalsIgnoreCase ("Bet"))
    {
      userBet = true;
      c.println ("How much would you like to bet");
      bet = c.readDouble(); //user inputs amount
      if(bet > playerMoney[x])
      {
        c.println("You do not have this much money. Bet an amount you have or enter 0 if u do not wish to bet anymore."); 
        bet = c.readDouble();
      }
      pot += bet; //Adds money to the pot
      playerMoney[x] -= bet; //Subtracts money from whoever bet
    }
    
    //if user decides to raise
    else if (choice.equalsIgnoreCase ("Raise"))
    {
      userRaise = true;
      c.println ("How much would you like to raise by");
      raise = c.readDouble(); //user input amount to increase
      bet += raise; //Initial bet is increased by amount
      if(bet > playerMoney[x])
      {
        c.println("You do not have this much money. Bet an amount you have or enter 0 if u do not wish to raise anymore."); 
        bet -= raise;
        raise = c.readDouble();
      }
      pot += bet; //Adds money to pot
      if (playerBet[x] != 0)
        playerMoney[x] -= raise;
      else
        playerMoney[x] -= bet; //Subtracts money from whoever bet
    }
    
    //if user decides to fold
    else if (choice.equalsIgnoreCase ("Fold"))
      fold [x] = true;
    
    c.println ("You have $" + (playerMoney[x]) + " left");
  }
  
  //Display how the player won
  //rank - the rank of player's hand
  public static void displayRank (int rank)
  {
    //Display the rank of the hand depending on score
    if (rank == 1)
      c.println ("High Card");
    else if (rank == 2)
      c.println ("Pair");
    else if (rank == 3)
      c.println ("Two Pair");
    else if (rank == 4)
      c.println ("3 of a kind");
    else if (rank == 5)
      c.println ("Flush");
    else if (rank == 6)
      c.println ("Straight");
    else if (rank == 7)
      c.println ("Full House");
    else if (rank == 8)
      c.println ("4 of a Kind");
    else if (rank == 9)
      c.println ("Straight Flush");
    else if (rank == 2)
      c.println ("Royal Flush");
  }
  
  
}//end Class



