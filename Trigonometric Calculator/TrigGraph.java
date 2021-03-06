/** TransformationsController
  * Applies user input to the function of the graph
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/22/2016
  * */
public class TrigGraph extends Object
{
  private GraphGUI graphDisplay; // The GUI that displays the base funtions
  
  //Factors of a function
  private double aValue;
  private double kValue;
  private double pValue;
  private double qValue;
  private double xValue;
  private double yValue;
  
  //Vairables that check which function needs to be displayed
  private boolean a,k,p,q;
  private boolean sin;
  private boolean cos;
  private boolean tan;
  private boolean inverse;
  
  /**Contsructs the model
    * */
  public TrigGraph()
  {
    super();
    this.aValue = 1;
    this.kValue = 1;
    this.pValue = 0;
    this.qValue = 0;
    this.xValue = 0;
    this.yValue = 0;
    this.sin = false;
    this.cos = false;
    this.tan = false;
    this.a=false;
    this.k= false;
    this.p = false;
    this.q= false;
    this.inverse = false;
  }
  
  /** Associates the base graph to the model
    * @param currentGUI is the GUI displaying the base graph
    * */
  public void setGUI(GraphGUI currentGUI)
  {
    this.graphDisplay = currentGUI;
  }
  
  /** Associates the coordinate finder to the model
    * @param currentGUI is the GUI displaying the coordinate finder
    * */  
  public void setA ()
  {
    this.a = true;
    this.k = false;
    this.p = false;
    this.q = false;
    
    this.updateMainView();
  }
  /** Sets the k value inputted by the user
    * 
    */
  public void setK ()
  {
    this.k = true;
    this.a = false;
    this.p = false;
    this.q = false;
    
    this.updateMainView();
  }
  /** Sets the p value inputted by the user
    * 
    */
  public void setP ()
  {
    this.p = true;
    this.a = false;
    this.k = false;
    this.q = false;
    
    this.updateMainView();
  }
  /** Sets the q value inputted by the user 
    */
  public void setQ ()
  {
    this.q= true;
    this.a = false;
    this.k = false;
    this.p = false;
    
    this.updateMainView();
  }
  
  /** Indicates that the sin function needs to be displayed
    * @return if user wants to display the sin function
    * */
  public boolean getSin ()
  {
    return sin;
  }
  
  /** Indicates that the cos function needs to be displayed
    * @return if user wants to display the cos function
    * */
  public boolean getCos ()
  {
    return cos;
  }
  
  /** Indicates that the tan function needs to be displayed
    * @return if user wants to display the tan function
    * */
  public boolean getTan ()
  {
    return tan;
  }
  
  /** Indicates that the inverse function needs to be displayed
    * @return if user wants to display the inverse function
    * */
  public boolean getInverse()
  {
    return inverse;
  }
  
  public boolean getA ()
  {
    return this.a;
  }
  
  public boolean getK ()
  {
    return this.k;
  }
  public boolean getP ()
  {
    return this.p;
  }
  public boolean getQ ()
  {
    return this.q;
  }
  
  /** Returns the a value set by the user
    * */
  public double getAValue ()
  {
    return this.aValue;
  }
  
  /** Returns the k value set by the user
    * */
  public double getKValue ()
  {
    return this.kValue;
  }
  
  /** Returns the p value set by the user
    * */
  public double getPValue ()
  {
    return this.pValue;
  }
  
  /** Returns the q value set by the user
    * */
  public double getQValue ()
  {
    return this.qValue;
  }
  
  /** Returns the x value inputted
    * */
  public double getXValue ()
  {
    return this.xValue;
  }
  
  /** Returns the corresponding y value for the inputed x value.
    * */
  public double getYValue ()
  {
    return this.yValue;
  }
  
  /** Sets sin to be true
    * */
  public void setSin ()
  {
    this.sin = true;
    this.cos = false;
    this.tan = false;
    this.updateMainView();
  }
  
  /** Sets cos to be true
    * */
  public void setCos ()
  {
    
    this.sin = false;
    this.cos = true;
    this.tan = false;
    this.updateMainView();
  }
  
  /** Sets tan to be true
    * */
  public void setTan ()
  {
    this.sin = false;
    this.cos = false;
    this.tan = true;
    this.updateMainView();
  }
  
  /** Finds the corresponding y value based on the input of the x value
    * @param xCoordinate is the x value associated with the y value
    * */
  public void setYValue (double xCoordinate)
  {
    if (this.sin)
    {
      if (this.inverse)
      {
        this.yValue = ((Math.round(this.aValue*(1/(Math.sin(this.kValue*(xCoordinate+this.pValue))+this.qValue))*100))+ 0.0)/100; //Calculates y value for inverse sin function
      }
      else
      {
        this.yValue = ((Math.round(this.aValue*(Math.sin(this.kValue*(xCoordinate+this.pValue))+this.qValue)*100)) + 0.0)/100; //Calculates y value for sin function
      }
    }
    else if (this.cos)
    {
      if (this.inverse)
      {
        this.yValue = ((Math.round(this.aValue*(1/(Math.cos(this.kValue*(xCoordinate+this.pValue))+this.qValue))*100))+ 0.0)/100; //Calculates y value for inverse cos function
      }
      else
      {
        this.yValue = ((Math.round(this.aValue*(Math.cos(this.kValue*(xCoordinate+this.pValue))+this.qValue)*100)) + 0.0)/100; //Calculates y value for tan function
      }
    }
    else if (this.tan)
    {
      if (this.inverse)
      {
        this.yValue = ((Math.round(this.aValue*(1/(Math.tan(this.kValue*(xCoordinate+this.pValue))+this.qValue))*100))+ 0.0)/100; //Calculates y value for inverse tan function
      }
      else
      {
        this.yValue = ((Math.round(this.aValue*(Math.tan(this.kValue*(xCoordinate+this.pValue))+this.qValue)*100)) + 0.0)/100; //Calculates y value for tan function
      }
    }
    this.updateMainView();
  }
  
  /** Indicates if the inverse function needs to be displayed
    * @param isInv is the boolean value to indicate if the function needs to be displayed
    * */
  public void setInverse(boolean isInv)
  {
    this.inverse = isInv;
    this.updateMainView();
  }
  
  /** Sets the a value based on what the user set
    * @param a is the value of the vertical stretch
    * */
  public void setAValue (double a)
  {
    this.aValue = a;
    this.updateMainView();
  }
  
  /** Sets the k value based on what the user set
    * @param k is the value of the horizontal stretch
    * */
  public void setKValue (double k)
  {
    this.kValue = k;
    this.updateMainView();
  }
  
  /** Sets the p value based on what the user set
    * @param p is the value of the horizontal shift
    * */
  public void setPValue (double p)
  {
    this.pValue = p;
    this.updateMainView();
  }
  
  /** Sets the q value based on what the user set
    * @param q is the value of the vertical shift
    * */
  public void setQValue (double q)
  {
    this.qValue = q;
    this.updateMainView();
  }
  
  /** Creates a function modelling the graph displayed
    * @return the equation of the function
    * */
  public String getFunction ()
  {
    String function = ""; //Trignometric functio 
    String equation; //Full equation
    //Transformation values
    String a;
    String k;
    String p;
    String q;
    //Identifies which function is being displayed
    if (this.sin)
    {
      function = "sin";
    }
    else if (this.cos)
    {
      function = "cos";
    }
    else if (this.tan)
    {
      function = "tan";
    }
    else 
    {
      function = "";
    }
    
    //Displays the a value if necessary
    if (this.getAValue()== 1)
    {
      a = "";
    }
    else
    {
      a = "" + this.getAValue();
    }
    //Displays the k value if necessary
    if (this.getKValue() == 1)
    {
      k = "";
    }
    else
    {
      k = "" + this.getKValue();
    }
   
    p = "" + this.getPValue(); //Sets p value
    q = "" + this.getQValue(); //Sets q value
    
    if (this.getPValue() > 0) //if the p value is greater than 0
    {
      //Changes the display of the function based on the q value
      if (this.getQValue() > 0)
      {
        equation = a + function + k + "(x - " + p + ") + "  + q;
      }
      else if (this.getQValue() == 0)
      {
        equation = a + function + k + "(x - " + p + ")";
      }
      else
      {
        equation = a + function + k + "(x - " + p + ")" + q;
      }
    }
    else if (this.getPValue() == 0) //if the p value matches 0
    {
      //Changes the display of the function based on the q value
      if (this.getQValue() > 0)
      {
        equation = a + function + k + "(x) + " + q;
      }
      else if (this.getQValue() == 0)
      {
        equation = a + function + k + "(x) ";
      }
      else
      {
        equation = a + function + k + "(x) " + q;
      }
    }
    else //if the p value is less than 0
    {
      //Changes the display of the function based on the q value
      if (this.getQValue() > 0)
      {
        equation = a + function + k + "(x - (" + p + ") + " + q;
      }
      else if (this.getQValue() == 0)
      {
        equation = a + function + k + "(x - (" + p + ")";
      }
      else
      {
        equation = a + function + k + "(x - (" + p + ") " + q;
      }
    }

    if (this.getAValue() == 0 || this.getKValue() == 0) //if function does not exist
    {
      return "" + q; 
    }
    else //if function does exist
    {
      return equation;
    }
  }
  
  /** Updates the layout of the GUI of the base graph
    * */
  public void updateMainView()
  {
    this.graphDisplay.update();
  }
}