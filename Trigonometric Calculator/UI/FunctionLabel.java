  /**
  public String getFunction ()
  {
    String function = "";
    String equation;
    String a;
    String k;
    String p;
    String q;
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
    
    a = "" + this.getAValue();
    if (this.getAValue()== 1);
    {
      a = "";
    }
    k = "" + this.getKValue();
    if (this.getKValue() == 1);
    {
      k = "";
    }
    p = "" + this.getPValue();
    q = "" + this.getQValue();
    if (this.getPValue() > 0)
    {
      if (this.getQValue() > 0)
      {
        equation = a + function + k + "(x) + " + q;
      }
      else if (this.getQValue() == 0)
      {
        equation = a + function + k + "(x)";
      }
      else
      {
        equation = a + function + k + "(x) " + q;
      }
    }
    else if (this.getPValue() == 0)
    {
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
    else
    {
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
    if (this.getAValue() == 0 || this.getKValue() == 0)
    {
      return "";
    }
    else
    {
      return equation;
    }
  }*/