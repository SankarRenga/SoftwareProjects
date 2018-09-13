/** TransformationsController
  * Displays the base graph and all the transformation applied to it
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/22/2016
  * */
import javax.swing.*;
import java.awt.*;

public class GraphGUI extends JPanel
{
  private TrigGraph graph; //the model
  private DrawGraph drawFunction; //draws a specific function
  
  //Creates the buttons for choosing which function to display
  private JButton operationSin = new JButton("Sin");
  private JButton operationCos = new JButton("Cos");
  private JButton operationTan = new JButton("Tan");
  private JButton inverseButton = new JButton("Inverse");
  
  //Creates the buttons that show each seperate transition of the function
  private JButton showA = new JButton("Show A");
  private JButton showK = new JButton("Show K");
  private JButton showP = new JButton("Show P");
  private JButton showQ = new JButton("Show Q");
  
  private JButton graphButton = new JButton ("Graph It"); //Creates a button to graph the function
  
  //Create textfields for inputting transformations
  private JTextField aValue = new JTextField(5);  
  private JTextField kValue = new JTextField(5);
  private JTextField pValue = new JTextField(5);
  private JTextField qValue = new JTextField(5);
  
  //TextField and Labels for inputting the x value, to get a corresponding y value
  private JLabel xLabel = new JLabel("Enter an X value: ");
  private JLabel yLabel = new JLabel("Y: ");
  private JTextField xField = new JTextField(5);
  private JTextField yField = new JTextField(5);
 
  private JLabel function = new JLabel (); //Shows the current equation of the function
  
  /** Creates a GUI that displays the function
    * @param newGraph is the model that is to be linked to this view
    * */
  public GraphGUI (TrigGraph newGraph)
  {
    super();
    this.yField.setEditable(false); //Disables the Y field so the user cannot edit it
    this.graph = newGraph;
    this.graph.setGUI(this);
    this.drawFunction = new DrawGraph(this.graph);
    this.initialLayout();
    this.registerControllers();
    this.update();
  }
  
  /** Creates the layout of the GUI
    * */
  public void initialLayout()
  {
    //Sets all the buttons for choosing the base function in one panel
    JPanel operationChooser = new JPanel();
    operationChooser.setBackground(new Color(171,255,235));
    operationChooser.add(operationSin);
    operationChooser.add(operationCos);
    operationChooser.add(operationTan);
    operationChooser.add(inverseButton);
    operationChooser.setBorder(BorderFactory.createTitledBorder("Operation"));
    
    //Panel for the function to be displayed
    JPanel baseTrans = new JPanel();
    baseTrans.setBorder(BorderFactory.createTitledBorder("Function: " + this.function.getText()));
    baseTrans.setPreferredSize (new Dimension (500,2000));
    baseTrans.add (drawFunction);
    drawFunction.setVisible(false);
    
    //Panel that lets users input transformations and view each seperate transformation
    JPanel transformPanel = new JPanel();
    transformPanel.setBackground(new Color(171,255,235));
    transformPanel.add (showA);
    transformPanel.add (aValue);
    transformPanel.add (showK);
    transformPanel.add (kValue);
    transformPanel.add (showP);
    transformPanel.add (pValue);
    transformPanel.add (showQ);
    transformPanel.add (qValue);
    transformPanel.add (qValue);
    transformPanel.add (graphButton);
    transformPanel.add(xLabel);
    transformPanel.add(xField);
    transformPanel.add(yLabel);
    transformPanel.add(yField);

    transformPanel.setBorder(BorderFactory.createTitledBorder("Transform"));
    
    JPanel centre = new JPanel();
    centre.setLayout(new GridLayout(1,2));
    centre.add(baseTrans);
    
    JPanel bot = new JPanel();
    bot.setLayout(new GridLayout(1,2));
    bot.add(transformPanel);
    
    this.setLayout(new BorderLayout());
    this.add(operationChooser, BorderLayout.NORTH); //Functions are chosen at the top of the GUI
    this.add(centre, BorderLayout.CENTER);          //Centres the function
    this.add(bot, BorderLayout.SOUTH);              //User input is added to the bottom
  }
  
  /** Links all components to a controller so they can react to events
    * */
  private void registerControllers()
  {
    //Creates a controller
    TransformationsController controller = new TransformationsController(this.graph, this.operationSin, this.operationCos, this.operationTan, this.inverseButton, this.aValue, this.kValue, this.pValue, this.qValue, this.showA,this.showK,this.showP,this.showQ,this.graphButton, this.xField);
    //Components now respond to events
    this.inverseButton.addActionListener(controller);
    this.operationSin.addActionListener(controller);
    this.operationCos.addActionListener(controller);
    this.operationTan.addActionListener(controller);
    this.showA.addActionListener(controller);
    this.showK.addActionListener(controller);
    this.showP.addActionListener(controller);
    this.showQ.addActionListener(controller);
    this.aValue.addActionListener(controller);
    this.kValue.addActionListener(controller);
    this.pValue.addActionListener(controller);
    this.qValue.addActionListener(controller);
    this.graphButton.addActionListener(controller);
    this.xField.addActionListener(controller);
    this.yField.addActionListener(controller);
  } 
  
  public void update()
  {
    this.function.setText (this.graph.getFunction());
    this.initialLayout();
    this.drawFunction.setVisible (true);
    this.drawFunction.repaint(); //repaints function 
    this.yField.setText(""+this.graph.getYValue());
  }
}