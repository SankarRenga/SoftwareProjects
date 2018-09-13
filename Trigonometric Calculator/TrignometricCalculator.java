/** TransformationsController
  * Starts up the program
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/22/2016
  * */
import javax.swing.*;

public class TrignometricCalculator
{
  public static void main (String [] args)
  { 
    TrigGraph calculator = new TrigGraph();       //Creates the model
    MenuGUI mainScreen = new MenuGUI(calculator); //Creates the menu screen 
    
    //Opens a window
    JFrame mainFrame = new JFrame("Suave Graphing");
    mainFrame.setSize(500,500);
    mainFrame.setLocationRelativeTo(null);
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setResizable(false);
    mainFrame.setContentPane(mainScreen);
    mainFrame.setVisible(true);
  }
}