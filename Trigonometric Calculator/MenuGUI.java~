/** TransformationsController
  * The main menu of the program
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/21/2016
  * */
import javax.swing.*;
import java.awt.*;

public class MenuGUI extends JPanel
{
  private TrigGraph graph;
  private JButton start = new JButton ("Start Graphing"); //Starts the program for graphing
  private JButton help = new JButton ("Help"); //A help button that links to a user's manual
  private Picture companyLogo = new Picture ("Logo.jpg"); //logo of the company
  
  /** Creates an introduction screen to the program
   * @param newGraph is the model that will eventually be linked to the GUI of the graph
   * */
  public MenuGUI (TrigGraph newGraph)
  {
    super();
    this.graph = newGraph;
    this.initialLayout();
    this.registerControllers();
  }
  
  /** Organizes all the components in the GUI
    * */
  public void initialLayout()
  {
    JPanel buttons = new JPanel(); //Displays the buttons
    buttons.add (start);
    buttons.add (help);

    JPanel logo = new JPanel();     //Area where logo will be displayed
    logo.add (companyLogo);
    logo.setPreferredSize(new Dimension(300,400));
    
    //Places each panel at a specific area
    this.setLayout(new BorderLayout());
    this.add (logo, BorderLayout.NORTH);
    this.add(buttons, BorderLayout.SOUTH);             
  }
  
  /**Enables components to respond to events
    * */
  public void registerControllers ()
  {
    MenuController controller = new MenuController (this.graph, this.start, this.help);
    this.start.addActionListener(controller);
    this.help.addActionListener(controller);
  }
}
    
    
  