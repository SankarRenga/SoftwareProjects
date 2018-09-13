/** TransformationsController
  * This class handles drawing the function as well as calculating the x and y values
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/22/2016
  * */
import java.awt.*;
import javax.swing.*;
import java.awt.geom.Line2D;

public class DrawGraph extends JComponent
{
  private TrigGraph graph;    // The model
  
  // Variables for determining the transformations to the graph
  private double a;
  private double k;
  private double p;
  private double q;
  
  // Variables for determining which base function will be used
  private boolean isCos;
  private boolean isSin;
  private boolean isTan;
  private boolean isInverse;
  
  /** Collects and sets which function is chosen as well as all the transformation values required
    * @param aGraph the model linked with this controller class
    * */
  public DrawGraph (TrigGraph aGraph)
  {
    super();
    this.graph = aGraph;
    this.setPreferredSize (new Dimension (1200,1000));
    this.isSin = this.graph.getSin();
    this.isCos = this.graph.getCos();
    this.isTan = this.graph.getTan();
  }
  
  /** Method that paints the graph according to the scale (Overrides the JComponent paintComponent)
    * @param g 
    * */
  public void paintComponent(Graphics g)
  { 
    this.isInverse = this.graph.getInverse();
    this.a = this.graph.getAValue()*1.2;      // Scales the a value to the graph
    this.k = this.graph.getKValue();          // Scales the k value to the graph
    this.p = this.graph.getPValue()*10;       // Scales the p value to the graph
    if (this.graph.getQValue() < 0)
    {
      this.q = this.graph.getQValue()*130;    // Scales the q value to the graph if the variable is less than 0
    }
    else
    {
      this.q = this.graph.getQValue()*160;    // Scales the q value to the graph if the variable is greater or equal to than 0
    }
    
    Graphics2D g2 = (Graphics2D) g;
    
    int yScale  = 130;                        // The y axis scale of the graph
    int xAxis   = 540;                        // The length of the x axis
    int numerator = -3;                       //minimum x value
    String xCoordinate = "";
    
    // Returns the boolean value of which base function was chosen
    g.setColor( Color.red );
    this.isSin = this.graph.getSin();
    this.isCos = this.graph.getCos();
    this.isTan = this.graph.getTan();
    
    
    double x = 0;                              // The x value
    double y = 0;                              // The y value
    
    g2.setColor(Color.black);
    g2.drawLine(590, 0, 590, 4*yScale);                 //Y-AXIS
    g2.drawLine(-100, yScale*2, xAxis*2+300, yScale*2); //X-AXIS
    //Labels the x coordinates on the x axis
    for (int z = 50; z <= 3590; z+=180)
    {
      xCoordinate = "" + numerator + "pi"; //Labels every pi value
      if (numerator == 0)
      {
        xCoordinate = "0"; //Labels the zero coordinate
      }
      g2.drawString (xCoordinate, z ,280); //Displays x coordinate
      numerator++;
    }
    
    int counter = -3; //Minimum x value
    //Labels the y coordinates along the y axis
    for (int t = 485; t >= 0; t-=75)
    {
      if (counter != 0)
      {
        g2.drawString ("" + counter, 590, t); //Displays y coordinate
      }
      else
      {
        t = t+10; //Skips labelling the zero coordinate
      }
      counter++;
    }
    
    if (this.isInverse)                                                                    // If the function is inversed
    {
      for(double tempX=-360; tempX <= xAxis * 100; tempX+=0.12)                            // Starts at -2pi and increments the temorary x value until it reaches a certain point on the x axis
      {   
        if (this.graph.getKValue() < 0)                                                    // If the k value is less than 0
        {
          x = 230 - (tempX/k) + (p*(18/Math.PI)) + (((Math.PI*10)/k)*(18/Math.PI));        // Determines the x value for a negative k value after the vertical shift
        }
        else                                                                               // If the k value is greater or equal to 0
        {
          x = 230 + (tempX/k) + (p*(18/Math.PI));                                          // Determines the x value for a positive k value after the vertical shift
        }
        
        if (this.isCos)  
        {
          g2.setColor(Color.red);
          y = (yScale - 1/Math.cos(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                             // Determines the y value for the secant function
          g2.draw(new Line2D.Double(x, y, x, y ));                                                                      // Draws the point on the graph
        }
        
        else if (this.isSin)
        {
          g2.setColor(Color.red);
          y = (yScale - 1/Math.sin(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                             // Determines the y value for the cosecant function
          g2.draw(new Line2D.Double(x, y, x, y ));                                                                      // Draws the point on the graph
        }
        
        else if (this.isTan)
        {
          g2.setColor(Color.red);
          y = (yScale - 1/Math.tan(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                             // Determines the y value for the cotangent function
          g2.draw(new Line2D.Double( x, y, x, y ));                                                                     // Draws the point on the graph
        }
      }
      
      // Draws the asymptotes in blue according to the function and the k and p values
      g2.setColor(Color.blue);
      if (this.isTan)                                                                                               // If the function is a tan function
      {
        for (int asymptote = 0; asymptote <= 180; asymptote+=2)
        {
          for (int b = 0; b <= 530; b+= 10)
          {
            if (this.graph.getKValue() < 0)
            {
              g2.draw(new Line2D.Double(-90 * asymptote/k + 140 + (p*(18/Math.PI)), b, -90 * asymptote/k + 140 + (p*(18/Math.PI)),  b + 5));       // Draws a dotted vertical line where y is undefined in the cot graph when k is less than 0
            }
            else
            {
              g2.draw(new Line2D.Double(90 * asymptote/k - 130 + (p*(18/Math.PI)), b, 90 * asymptote/k - 130 + (p*(18/Math.PI)),  b + 5));          // Draws a dotted vertical line where y is undefined in the cot graph when k is greater or equal to 0
            }
          }
        }
      }
      
      else if (this.isCos)                                                                                          // If the function is a cos function
      {
        for (int asymptote = 1; asymptote <= 180; asymptote+=2)
        {
          for (int b = 0; b <= 530; b+= 10)
          {
            if (this.graph.getKValue() < 0)
            {
              g2.draw(new Line2D.Double(-90 * asymptote/k + 140 + (p*(18/Math.PI)), b, -90 * asymptote/k + 140 + (p*(18/Math.PI)),  b + 5));        // Draws a dotted vertical line where y is undefined in the sec graph when k is less than 0
            }
            else
            {
              g2.draw(new Line2D.Double(90 * asymptote/k - 130 + (p*(18/Math.PI)), b, 90 * asymptote/k - 130 + (p*(18/Math.PI)),  b + 5));          // Draws a dotted vertical line where y is undefined in the sec graph when k is greater or equal to 0
            }
          }
        }
      }
      
      else if (this.isSin)                                                                                          // If the function is a sin function
      {
        for (int asymptote = 0; asymptote <= 180; asymptote+=2)
        {
          for (int b = 1; b <= 530; b+= 10)
          {
            if (this.graph.getKValue() < 0)
            {
              g2.draw(new Line2D.Double(-90 * asymptote/k + 140 + (p*(18/Math.PI)), b, -90 * asymptote/k + 140 + (p*(18/Math.PI)),  b + 5));        // Draws a dotted vertical line where y is undefined in the csc graph when k is less than 0
            }
            else
            {
              g2.draw(new Line2D.Double(90 * asymptote/k - 130 + (p*(18/Math.PI)), b, 90 * asymptote/k - 130 + (p*(18/Math.PI)),  b + 5));          // Draws a dotted vertical line where y is undefined in the csc graph when k is greater or equal to 0
            }
          }
        }
      }    
    }
     
    else                                                                                   // If the function is not inversed
    {
      for(double tempX=-360; tempX <= xAxis * 100; tempX+=0.12)                            // Starts at -2pi and increments the temorary x value until it reaches a certain point on the x axis
      { 
        if (this.graph.getKValue() < 0)                                                    // If the k value is less than 0
        {
          x = 230 - (tempX/k) + (p*(18/Math.PI)) + (((Math.PI*10)/k)*(18/Math.PI));        // Determines the x value for a negative k value after the vertical shift
        }
        else                                                                               // If the k value is greater or equal to 0
        {
          x = 230 + (tempX/k) + (p*(18/Math.PI));                                          // Determines the x value for a positive k value after the vertical shift
        }
        
        if (this.isCos)                                                                                                 // If the function is a cos function
        {
          g2.setColor(Color.red);
          y = (yScale - Math.cos(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                               // Determines the y value for the cosine function
          g2.draw(new Line2D.Double( x, y, x, y ));                                                                     // Draws the point on the graph
        }
        
        else if (this.isSin)                                                                                            // If the function is a sin function
        {
          g2.setColor(Color.red);
          y = (yScale - Math.sin(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                               // Determines the y value for the sine function
          g2.draw(new Line2D.Double( x, y, x, y ));                                                                     // Draws the point on the graph
        }
        
        else if (this.isTan)                                                                                            // If the function is a tan function
        {
          g2.setColor(Color.red);
          y = (yScale - Math.tan(Math.toRadians(tempX)) * a * yScale - q)/2 + yScale*1.5;                               // Determines the y value for the tangent function
          g2.draw(new Line2D.Double( x, y, x, y ));                                                                     // Draws the point on the graph
        } 
      }
      
      g2.setColor(Color.blue);
      if (this.isTan)
      {
        for (int asymptote = 1; asymptote <= 180; asymptote+=2)
        {
          for (int b = 0; b <= 530; b+= 10)
          {
            if (this.graph.getKValue() < 0)
            {
              g2.draw(new Line2D.Double(-90 * asymptote/k + 140 + (p*(18/Math.PI)), b, -90 * asymptote/k + 140 + (p*(18/Math.PI)),  b + 5));        // Draws a dotted vertical line where y is undefined in the tan graph when k is than 0
            }
            else
            {
              g2.draw(new Line2D.Double(90 * asymptote/k - 140 + (p*(18/Math.PI)), b, 90 * asymptote/k - 140 + (p*(18/Math.PI)),  b + 5));          // Draws a dotted vertical line where y is undefined in the tan graph when k is greater or equal to 0
            }
          }
        }
      }
    }
  }
}
