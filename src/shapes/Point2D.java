package shapes;

/**
 * Class to represent the point of a shape object.
 */
public class Point2D {
  private int x;
  private int y;

  /**
   * Constructor for the Point class.
   *
   * @param x coordinate
   * @param y coordinate
   */
  public Point2D(int x, int y){
    this.x = x;
    this.y = y;
  }

  /**
   * Method to return x coordinate.
   *
   * @return x
   */
  public int getX(){ return this.x; }

  /**
   * Method to return y coordinate.
   *
   * @return y
   */
  public int getY(){ return this.y; }

  /**
   * Method to set x coordinate.
   *
   */
  public void setX(int x){ this.x = x; }

  /**
   * Method to set y coordinate.
   */
  public void setY(int y){ this.y = y; }
}
