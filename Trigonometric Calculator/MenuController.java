/** TransformationsController
  * All components in the MenuGUI are enabled to respond to events
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/21/2016
  * */
import javax.swing.*;
import java.awt.event.*;

public class MenuController implements ActionListener
{
  private TrigGraph graph; //the model
  private JButton startButton; //button that starts the program
  private JButton helpButton; //button that displays the user manual
  private GraphGUI graphDisplay; //The GUI that displays the graphs
  private Picture manual = new Picture ("Manual.png"); //A picture of the user's manual
  private JFrame graphFrame = new JFrame("Suave Graphing"); //Window for the GUI that displays graphs
  private JFrame helpFrame = new JFrame ("User's Manual"); //Window for displaying the user's manual
  
  /**Creates a controller associated with the MenuGUI
    * @param model is what is going to be used to open the GUI that displays the graph
    * @param start is the button that starts up the GraphGUI
    * @param help enables users to see the instructions of how to use the program
    * */
  public MenuController (TrigGraph model, JButton start, JButton help)
  {
    this.graph = model;
    this.startButton = start;
    this.helpButton = help;
  }
  
  /**Identifies if a user wants to interact with a certain component
    * @param e is the event
    * */
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource().equals(startButton))
    {
      //Opens a GUI that displays the graphs
      graphDisplay = new GraphGUI(graph);
      graphFrame.setSize(1350,720);
      graphFrame.setLocationRelativeTo(null);
      graphFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      graphFrame.setResizable(false);
      graphFrame.setContentPane(graphDisplay);
      graphFrame.setVisible(true);
    }
    if (e.getSource().equals(helpButton))
    {
      //Opens a GUI that displays the user's manual
      helpFrame.setSize(1000,720);
      helpFrame.setLocationRelativeTo(null);
      helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      helpFrame.setResizable(false);
      //helpFrame.setContentPane(manual);
      helpFrame.setVisible(true);
    }
  }
}
