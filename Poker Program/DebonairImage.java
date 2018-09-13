import hsa.Console;      //Console Package
import java.awt.*;       //Graphics Package
import java.io.*;        //Package with code for working with files
import javax.imageio.*;  //Package with code to import an image

public class DebonairImage
{
  
  static Console c;
  
  public static void main (String[] args)
  {
    c = new Console();
    Image img;  //The image to draw
    
    //Try to import the image from the file
    try{
      
      img = ImageIO.read(new File("DebonairLogo.jpg"));
    }
    catch(IOException e)  //File not found
    {
      img = null;
    }
    
    //Draw the image (Image, x location, y location, null)
    c.drawImage(img,250,10,null);

  }
}