package shapes;

import java.util.Objects;

/**
 * Class to represent the oval shape.
 */
public class Oval extends AbstractShape {

  public Oval(String name, int x, int y, double width, double height
          , int red, int green, int blue) {
    super(name, x, y, width, height
            , red, green, blue);
    super.type = "Oval";
  }

  /**
   * ToString override for the Oval class.
   *
   * @return string representation of the Oval.
   */
  @Override
  public String toString() {
    return "Name: " + this.name +
            "\nType: Oval\n" +
            "Min Corner: (" + (this.center.getX()) + ", "
            + (this.center.getY()) + ")"
            + "\nRadius1: " + this.width + ", Radius2 : " + this.height
            + " Color: " + this.color.toString() + "\n"
            ;
  }
}
