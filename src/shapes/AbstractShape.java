package shapes;

/**
 * Abstract Shape class for PhotoAlbum shapes.
 */
public class AbstractShape implements IShape {

  protected double width;
  protected double height;
  protected Point2D center;
  protected Color color;
  protected String type;
  protected String name;

  /**
   * Constructor for Abstract shape class.
   *
   * @param name   of shape
   * @param x      coordinate
   * @param y      coordinate
   * @param width  size 1 (radius_1 for oval)
   * @param height size 2 (radius_2 for oval)
   * @param red    color
   * @param green  color
   * @param blue   color
   */
  public AbstractShape(String name, int x, int y, double width, double height
          , int red, int green, int blue) {

    this.name = name;
    this.width = width;
    this.height = height;
    this.center = new Point2D(x, y);
    this.color = new Color(red, green, blue);
  }

  /**
   * Method to set the size of a shape by setting the width and height.
   *
   * @param width  to set shape to
   * @param height to set shape to
   */
  @Override
  public void setSize(double width, double height) {
    this.checkValidInputs(width, height); // throws IllegalArgumentException if values < 0
    this.width = width;
    this.height = height;
  }

  /**
   * Method to change the center point of the shape.
   *
   * @param x new x position
   * @param y new y position
   */
  @Override
  public void move(int x, int y) {
    this.center.setX(x);
    this.center.setY(y);
  }

  /**
   * Method to set the new color of an object.
   * If values are outside the range 0-255, the color doesn't change.
   *
   * @param red   decimal value
   * @param green decimal value
   * @param blue  decimal value
   */
  public void setColor(int red, int green, int blue) {
    this.color.setColor(red, green, blue);
  }

  /**
   * Method to return the x coordinate of the center of the shape.
   *
   * @return x
   */
  public int getX() {
    return this.center.getX();
  }

  /**
   * Method to return the y coordinate of the center of the shape.
   *
   * @return y
   */
  public int getY() {
    return this.center.getY();
  }

  /**
   * Method to return the shape type.
   *
   * @return shape type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Method to return the shape name.
   *
   * @return shape type
   */
  public String getName() {
    return this.name;
  }

  /**
   * Method to return width (or radius_1) of shape.
   *
   * @return width
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Method to return height (or radius_2) of shape.
   *
   * @return height
   */
  public double getHeight() {
    return this.height;
  }


  /**
   * Method to return the color of a shape.
   *
   * @return color
   */
  @Override
  public Color getColor() {
    return this.color;
  }


  /**
   * Method to clone a shape into a new object. This is particularly useful when taking a snapshot
   * as we want each snapshot to contain a clone of the shapes.
   *
   * @return shape
   */
  @Override
  public IShape clone() {
    return new AbstractShape(this.name, this.center.getX(), this.center.getY(), this.width
            , this.height, this.color.getRed(), this.color.getGreen(), this.color.getBlue());
  }

  /**
   * Method to calculate the area of the shape.
   * Mostly used for testing purposes.
   *
   * @return area
   */
  @Override
  public double area() {
    return this.width * this.height;
  }

  /**
   * Private helper method to check that valid width and height are inputted
   * when changing the size.
   *
   * @param width  to check
   * @param height to check
   */
  private void checkValidInputs(double width, double height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be greater than 0.");
    }
  }
}
