package view;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;

import javax.swing.*;

import controller.PhotoAlbumController;
import snapshot.Snapshot;
import shapes.IShape;

/**
 * Class to represent the JComponent where
 * the shapes will be drawn within the Swing GUI.
 */
public class ShapeDrawingArea extends JComponent {
  private PhotoAlbumController c;
  private List<IShape> shapes;

  /**
   * Constructor for the shapeDarwingArea class. On startup, it will take the first snapshot
   * from the model and render it.
   */
  public ShapeDrawingArea(PhotoAlbumController c) {
    this.c = c;
  }

  /**
   * Override of the paintComponent method.
   * @param g the <code>Graphics</code> object to protect
   */
  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g; // convert to Graphics2D class
    this.drawShapes(g2);
  }


  /**
   * Helper method to draw the shapes in the given snapshot. Method iterates through snapshot list
   * and creates new shapes on the canvas for each.
   *
   * @param g2 graphics
   */
  private void drawShapes(Graphics2D g2) {

    for (int i = 0; i < this.shapes.size(); i++) {
      IShape s = this.shapes.get(i);
      if (s.getType() == "Rectangle") {
        Rectangle2D.Double shape = new Rectangle2D.Double(s.getX(),s.getY(),s.getWidth(),s.getHeight());
        g2.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue()));
        g2.fill(shape);
      } else if (s.getType() == "Oval") {
        Ellipse2D.Double shape = new Ellipse2D.Double(s.getX(),s.getY(),s.getWidth(),s.getHeight());
        g2.setColor(new Color(s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue()));
        g2.fill(shape);
      }
    }
  }

  /**
   * Method to get the snapshot to view on the screen.
   * @param snapshot
   */
  public void getSnapshot(Snapshot snapshot) {
    this.shapes = snapshot.getShapes();
  }

}
