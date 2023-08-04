package shapes;

import java.util.ArrayList;
import java.util.List;

import shapes.IShape;

/**
 * Class to represent the Rectangle shape.
 * Rectangle implements the IShape interface.
 */
public class Rectangle extends AbstractShape {

    /**
     * Constructor for the Rectangle class.
     * Sets the width, height, center, and color.
     */
    public Rectangle(String name, int x, int y, double width, double height
            , int red, int green, int blue) {
        super(name, x, y, width, height
          , red, green, blue);
        super.type = "Rectangle";
    }

    /**
     * ToString override for the Rectangle class.
     *
     * @return string representation of the Rectangle.
     */
    @Override
    public String toString() {
        return  "Name: " + this.name +
                "\nType: Rectangle\n" +
                "Min Corner: (" + (this.center.getX() - (this.width / 2)) + ", "
                + (this.center.getY() - (this.height / 2)) + ")"
                + "\nWidth: " + this.width + ", Height: " + this.height
                + " Color: " + this.color.toString() + "\n"
                ;
    }
}
