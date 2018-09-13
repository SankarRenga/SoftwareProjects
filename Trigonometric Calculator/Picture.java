/** TransformationsController
  * Displays pictures
  * @authors Sankar, Usman, Arun
  * @Last Modified 01/21/2016
  * */
import javax.swing.*;
import java.awt.*;

public class Picture extends JComponent
{
  ImageIcon image;
  public Picture (String imageFile)
  {
    super ();
    image = new ImageIcon (imageFile);
    this.setPreferredSize (new Dimension (500,500));
  }
  
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Image pic = image.getImage();
    g.drawImage (pic,0,0,null);
  }
}