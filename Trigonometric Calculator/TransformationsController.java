/** TransformationsController
  * All components in the Graph GUI are enabled to respond to events
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/22/2016
  * */
import javax.swing.*;
import java.awt.event.*;

public class TransformationsController implements ActionListener
{
  private TrigGraph graph; 
  private TransformationsView tran;
  
  //Variables to represent the buttons displayed
  private JButton inverseButton;
  private JButton sinButton;
  private JButton cosButton;
  private JButton tanButton;
  private JButton a;
  private JButton k;
  private JButton p;
  private JButton q;
  private JButton graphButton;
  
  //Variables to hold information regarding the text fields displayed
  private JTextField aField;
  private JTextField kField;
  private JTextField pField;
  private JTextField qField;
  private JTextField xField;
   
  // A new JFrame that is opens when the TransformationsView class is called
  private JFrame mainFrame = new JFrame();
  
  /** Collects information for all the buttons and text fields displayed, that needs to be handled by the controller
    * 
    * @param currentGraph the model linked with this controller class
    * @param sin holds the value for the sine button
    * @param cos holds the value for the cosine button
    * @param tan holds the value for the tangent button
    * @param inverseIt holds the value for the inverse button
    * @param a holds the value for the A transformation
    * @param k holds the value for the K transformation
    * @param p holds the value for the P transformation
    * @param q holds the value for the Q transformation
    * @param showA holds the value for the showA button
    * @param showK holds the value for the showK button
    * @param showP holds the value for the showP button
    * @param showQ holds the value for the showQ button
    * @param graphIt holds the value for the graphIt button
    */
  public TransformationsController(TrigGraph currentGraph, JButton sin, JButton cos, JButton tan, JButton inverseIt, JTextField a, JTextField k, JTextField p, JTextField q, JButton showA, JButton showK, JButton showP, JButton showQ, JButton graphIt, JTextField x)
  {
    this.graph = currentGraph;
    this.a= showA;
    this.k= showK;
    this.p= showP;
    this.q= showQ;
    this.aField = a;
    this.kField = k;
    this.pField = p;
    this.qField = q;
    this.tran= new TransformationsView (currentGraph);
    this.sinButton = sin;
    this.cosButton = cos;
    this.tanButton = tan;
    this.graphButton = graphIt;
    this.inverseButton = inverseIt;
    this.xField = x;
  }
  
  /** Performs actions based on what event occurred i.e Button click, or text input.
    * 
    */
  public void actionPerformed(ActionEvent e)
  {
    //All the following try and catch statements are used for data validation to ensure the user inputs a valid number 
      try
      {
        double xNumber = Double.parseDouble(this.xField.getText());
        this.graph.setYValue (xNumber);
      } 
      catch (NumberFormatException ex)
      {
        this.xField.selectAll();
      }
      try
      {
        double aNumber = Double.parseDouble(this.aField.getText());
        this.graph.setAValue (aNumber);
      } 
      catch (NumberFormatException ex)
      {
        this.aField.selectAll();
      }
      try
      {
        double kNumber = Double.parseDouble(this.kField.getText());
        this.graph.setKValue (kNumber);
      } 
      catch (NumberFormatException ex)
      {
        this.kField.selectAll();
      }
      try
      {
        double pNumber = Double.parseDouble(this.pField.getText());
        this.graph.setPValue (pNumber);
      } 
      catch (NumberFormatException ex)
      {
        this.pField.selectAll();
      }
      try
      {
        double qNumber = Double.parseDouble(this.qField.getText());
        this.graph.setQValue (qNumber);
      } 
      catch (NumberFormatException ex)
      {
        this.qField.selectAll();
      }
    // The following if else statements checks to see which button was pressed, and passes information to the model class 
    if (e.getSource().equals (graphButton))
    {
      this.graph.updateMainView(); 
    }
    
    else if (e.getSource().equals (sinButton))
    {
      this.graph.setSin();
      this.graph.setInverse(false);
      this.graph.setAValue(1);
      this.graph.setKValue(1);
      this.graph.setPValue(0);
      this.graph.setQValue(0);
      //this.graph.updateMainView(); 
    }
    else if (e.getSource().equals (cosButton))
    {
      this.graph.setCos();
      this.graph.setInverse(false);
      this.graph.setAValue(1);
      this.graph.setKValue(1);
      this.graph.setPValue(0);
      this.graph.setQValue(0);
      //this.graph.updateMainView(); 
    }
    else if (e.getSource().equals (tanButton))
    {
      this.graph.setTan();
      this.graph.setInverse(false);
      this.graph.setAValue(1);
      this.graph.setKValue(1);
      this.graph.setPValue(0);
      this.graph.setQValue(0);
      //this.graph.updateMainView(); 
    }
    
    else if (e.getSource().equals (q))
    {
      this.graph.setQ();
      mainFrame.setTitle("Q Transformation");
      mainFrame.setSize(1400,600);
      mainFrame.setLocation(0,0);
      mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      mainFrame.setContentPane(tran);
      mainFrame.setVisible(true);
    }
    else if (e.getSource().equals (p))
    {
      this.graph.setP();
      mainFrame.setTitle("P Transformation");
      mainFrame.setSize(1400,600);
      mainFrame.setLocation(0,0);
      mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      mainFrame.setContentPane(tran);
      mainFrame.setVisible(true);
    }
    else if (e.getSource().equals (k))
    {
      this.graph.setK();
      mainFrame.setTitle("K Transformation");
      mainFrame.setSize(1400,600);
      mainFrame.setLocation(0,0);
      mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      mainFrame.setContentPane(tran);
      mainFrame.setVisible(true);
    }
    else if (e.getSource().equals (a))
    {
      this.graph.setA();
      mainFrame.setTitle("A Transformation");
      mainFrame.setSize(1400,600);
      mainFrame.setLocation(0,0);
      mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      mainFrame.setContentPane(tran);
      mainFrame.setVisible(true);
    }
    else if (e.getSource().equals (inverseButton))
    {
      this.graph.setInverse(true);
      //this.graph.updateMainView();
    }
    
  }
}