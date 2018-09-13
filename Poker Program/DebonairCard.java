//Poker Card Game Card Class
//Prgrammed by: Guha and Sankar
//1/8/2015
//Card Class
import hsa.Console;
import java.awt.*;
import java.io.*;        
import javax.imageio.*;  

public class DebonairCard
{
  static Console c;
  private String debonairSuit; //Suit of the card
  private String debonairValue; //value of card
  //The respective images of cards
  private Image img;
  private Image img2;
  private Image img3;
  private Image img4;
  
  //Determine the card for player
  //suit - the suit of the card
  //value - value of card
  public DebonairCard(String suit, String value)
  {
    debonairValue = value; //Sets face value of card
    debonairSuit = suit; //Sets suit of card
  }
  
  //Determine the numeric value of the card
  public int cardValue ()
  {
    int value = 0; //The initial value of card
    
    if (debonairValue.equals ("2"))
    {
      value = 2;      
    }
    else if (debonairValue.equals ("3"))
    {
      value = 3;     
    }
    else if (debonairValue.equals ("4"))
    {
      value = 4;      
    }
    else if (debonairValue.equals ("5"))
    {
      value = 5;      
    }
    else if (debonairValue.equals ("5"))
    {
      value = 5;      
    }
    else if (debonairValue.equals ("6"))
    {
      value = 6;      
    }
    else if (debonairValue.equals ("7"))
    {
      value = 7;      
    }
    else if (debonairValue.equals ("8"))
    {
      value = 8;      
    }
    else if (debonairValue.equals ("9"))
    {
      value = 9;     
    }
    else if (debonairValue.equals ("10"))
    {
      value = 10;      
    }
    else if (debonairValue.equals ("J"))
    {
      value = 11;      
    }
    else if (debonairValue.equals ("Q"))
    {
      value = 12;      
    }
    else if (debonairValue.equals ("K"))
    {
      value = 13;      
    }
    else if (debonairValue.equals ("A"))
    {
      value = 14;      
    }
    return value; //returns numeric value
  }
  
  //Get the suit of the card
  public String getSuit()
  {
    return debonairSuit; //returns suit
  }
   
  //Display the player's card
  //c - the console where the card will be displayed
  //x - the x-position of the top left of the card
  //y - the y-position of the top left of the card
  public void displayCard(Console c,int x,int y)
  {
     try
   {
      
      img = ImageIO.read(new File("Heart.png"));
      img2 = ImageIO.read(new File("Diamond.png"));
      img3 = ImageIO.read(new File("Spades.png"));
      img4 = ImageIO.read(new File("Clovers.png"));
    }
    catch(IOException e)  //File not found
    {
      img = null;
      img2 = null;
      img3 = null;
      img4 = null;
    }
    
    //Create card if it is a heart
    if (debonairSuit.equals("H"))
    {
      c.setColor(Color.RED); //Sets colour to red
      //Display the card with values and suit
      c.drawRect(x,y,50,75); 
      c.drawString(debonairValue,x+2,y+10);
      c.drawString(debonairSuit,x+2,y+20);
      c.drawImage(img,x+4,y+20,null);
      c.drawString(debonairValue,x+40,y+75);
      c.drawString(debonairSuit,x+43,y+63);
    }
    
    //Create card if it is a diamond
    else if (debonairSuit.equals("D"))
    {
      c.setColor(Color.RED); //Sets colour to red
      //Display the card with values and suit
      c.drawRect(x,y,50,75); 
      c.drawString(debonairValue,x+2,y+10);
      c.drawString(debonairSuit,x+2,y+20);
      c.drawImage(img2,x+6,y+20,null);
      c.drawString(debonairValue,x+40,y+75);
      c.drawString(debonairSuit,x+43,y+63);
    }
    
    //Create card if it is a spades
    else if (debonairSuit.equals("S"))
    {
      c.setColor(Color.BLACK); //Sets colour to black
      //Display the card with values and suit 
      c.drawRect(x,y,50,75); 
      c.drawString(debonairValue,x+2,y+10);
      c.drawString(debonairSuit,x+2,y+20);
      c.drawImage(img3,x+4,y+20,null);
      c.drawString(debonairValue,x+40,y+75);
      c.drawString(debonairSuit,x+43,y+63);
    }
    
    //Create card if it is a clover
    else if (debonairSuit.equals("C"))
    {
      c.setColor(Color.BLACK); //Sets colour to black
      //Display the card with values and suit 
      c.drawRect(x,y,50,75); 
      c.drawString(debonairValue,x+2,y+10);
      c.drawString(debonairSuit,x+2,y+20);
      c.drawImage(img4,x+4,y+20,null);
      c.drawString(debonairValue,x+40,y+75);
      c.drawString(debonairSuit,x+43,y+63);
    }
    
  }
}

