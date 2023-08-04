package shapes;

/**
 * Interface for shape objects that will be placed on canvas.
 * Objects will be able to change color, size, and position.
 */
public interface IShape {

  /**
   * Method to set the size of a shape.
   * For an oval, side 1 is radius 1 and side 2 is radius 2.
   *
   * @param side1 to set shape to
   * @param side2 to set shape to
   */
  public void setSize(double side1, double side2);

  /**
   * Method to change where the shape is located on the canvas.
   * Positions will be constrained by the size of the canvas.
   *
   * @param x new x position
   * @param y new y position
   **/
  public void move(int x, int y);

  /**
   * Method to set the new color of an object.
   * Colors are specified by the decimal code values (R,G,B).
   * Range of possible options are 0 to 255.
   * If values are outside the range 0-255, the color doesn't change.
   *
   * @param red   decimal value
   * @param green decimal value
   * @param blue  decimal value
   */
  public void setColor(int red, int green, int blue);

  /**
   * Method to return the x coordinate of the center of the shape.
   *
   * @return x
   */
  public int getX();

  /**
   * Method to return the y coordinate of the center of the shape.
   *
   * @return y
   */
  public int getY();

  /**
   * Method to return the shape type.
   *
   * @return shape type
   */
  public String getType();

  /**
   * Method to return the shape name.
   *
   * @return shape type
   */
  public String getName();

  /**
   * Method to return width (or radius_1) of shape.
   *
   * @return width
   */
  public double getWidth();

  /**
   * Method to return height (or radius_2) of shape.
   *
   * @return height
   */
  public double getHeight();

  /**
   * Method to return the color of the shape.
   *
   * @return color
   */
  public Color getColor();

  /**
   * Method to create a clone of the given shape.
   *
   * @return clone of shape
   */
  public IShape clone();

  /**
   * Method to return the area of the shape.
   *
   * @return area
   */
  public double area();

}
