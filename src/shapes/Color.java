package shapes;

/**
 * Class to represent a color in decimal format (red, green, blue).
 * Valid decimals range from 0-255.
 */
public class Color {
  private int red;
  private int green;
  private int blue;
  private int minRange = 0;
  private int maxRange = 255;

  /**
   * Constructor for the color class.
   *
   * @param red decimal
   * @param green decimal
   * @param blue decimal
   */
  public Color(int red, int green, int blue) {
    this.checkValidInputs(red, green, blue);
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Method to return the red decimal value.
   *
   * @return red decimal
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Method to return the green decimal value.
   *
   * @return green decimal
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Method to return the blue decimal value.
   *
   * @return blue decimal
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Method to set the new color of an object.
   * If values are outside the range 0-255, the color doesn't change.
   *
   * @param red decimal value
   * @param green decimal value
   * @param blue decimal value
   */
  public void setColor(int red, int green, int blue) {
    this.checkValidInputs(red, green, blue);
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Method to override the toString method. Represents color as a string.
   *
   * @return string of color
   */
  @Override
  public String toString() {
    return "(" + this.red + ", " + this.green + ", " + this.blue + ")";
  }

  /**
   * Helper method to check that inputs are within range of valid color decimals.
   *
   * @param red decimal
   * @param green decimal
   * @param blue decimal
   */
  private void checkValidInputs(int red, int green, int blue) {
    if (red < minRange || green < minRange || blue < minRange ||
            red > maxRange || green > maxRange || blue > maxRange) {
      throw new IllegalArgumentException("Color decimals must be within 0-255");
    }
  }
}
