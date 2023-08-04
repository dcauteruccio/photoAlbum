import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.PhotoAlbumModel;
import snapshot.Snapshot;

import static org.junit.Assert.*;

/**
 * Class to test the PhotoAlbumModel class.
 */
public class PhotoAlbumTest {
  PhotoAlbumModel p = new PhotoAlbumModel();

  /**
   * Method to test creating a shape to place on the Canvas.
   */
  @Test
  public void TestCreateShape() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.createShape("O", "OVAL", 0, 0, 0, 0, 0, 0, 0); // case insensitive test
    List<String> list = new ArrayList<>();
    p.getScreen().toArray();
    list.add("R");
    list.add("Name: o\n" +
            "Type: Oval\n" +
            "Min Corner: (0, 0)\n" +
            "Radius1: 0.0, Radius2 : 0.0 Color: (0, 0, 0)");

    assertEquals("Shapes not added correctly.", "Name: o\n" +
            "Type: Oval\n" +
            "Min Corner: (0, 0)\n" +
            "Radius1: 0.0, Radius2 : 0.0 Color: (0, 0, 0)\n", p.getScreen().get(1).toString());
  }

  /**
   * Method to test creating an unknown shape. Shape will not be created.
   */
  @Test
  public void testCreateUnknownShape() {
    p.createShape("c", "Circle", 0, 0, 0, 0, 0, 0, 0);

    List<String> list = new ArrayList<>();
    assertEquals("Shape should not have been created", list, p.getScreen());
  }

  /**
   * Method to test that moving a shape on the canvas will update a shape's coordinates.
   */
  @Test
  public void testMoveShape() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.moveShape("r", 450, 3);

    assertEquals("X position incorrect.", 450,
            p.getScreen().get(0).getX(), .005);
    assertEquals("Y position incorrect.", 3,
            p.getScreen().get(0).getY(), .005);
  }

  /**
   * Method to test that moving a shape out of the canvas will result in an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOutOfCanvas() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.moveShape("r", -1, 3);
  }

  /**
   * Method to test that moving a shape ono on the canvas will result in an exception.
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testMoveUnknownShape() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.moveShape("t", 400, 3);
  }

  /**
   * Method to test that moving a shape out of the canvas will result in an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMoveOutOfNegativeCanvas() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.moveShape("r", 45, -451);
  }


  // change size

  /**
   * Method to test that moving a shape on the canvas will update a shape's coordinates.
   */
  @Test
  public void testChangeShapeSize() {
    p.createShape("r", "Rectangle", 0, 0, 1, 1, 0, 0, 0);

    assertEquals("Default Area incorrect.", 1,
            p.getScreen().get(0).area(), .005);
    p.changeSize("r", 400, 3);

    // System.out.println(p.getScreen().size());
    assertEquals("New width incorrect.", 400,
            p.getScreen().get(0).getWidth(), .005);
    assertEquals("New height incorrect.", 3,
            p.getScreen().get(0).getHeight(), .005);
  }

  /**
   * Method to test that creating a shape bigger than the canvas will result in an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeShapeSizeBiggerThanCanvas() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.changeSize("r", 900.01, 3);
  }

  /**
   * Method to test that changing the size of a shape to a value <= 0 will result in an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeSizeNegativeWidth() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.changeSize("r", 0, 3);
  }

  /**
   * Method to test taking a snapshot of the canvas and saving it to the photo album. Method also
   * tests the Snapshot class and it's ability to hold information.
   */
  @Test
  public void takeSnapShot() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.createShape("r2", "rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.moveShape("r2", 10, 10);
    p.changeSize("r", 15, 20);

    p.takeSnapshot("Change Size and position.");
    Snapshot s = p.getFullSnapShotList().get(0);

    // test that snapshot info is saving correctly
    assertEquals("Description incorrect.", "Change Size and position.",
            s.getDescription());
    assertEquals("Incorrect number of shapes.", 2, s.getShapes().size());


    // test that snapshot info will reflect removing shapes
    p.removeShape("r");
    p.takeSnapshot("Remove r.");
    Snapshot s2 = p.getFullSnapShotList().get(1);

    assertEquals("Description incorrect.", "Remove r.",
            s2.getDescription());
    assertEquals("Incorrect number of shapes.", 1, s2.getShapes().size());

    p.clearPhotoAlbum();
  }


  /**
   * Method to test getting the photo album of snapshots.
   */
  @Test
  public void getPhotoAlbum() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.createShape("r2", "rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.takeSnapshot("Initial Snapshot.");
    p.moveShape("r2", 10, 10);
    p.changeSize("r", 15, 20);
    p.changeShapeColor("r", 1, 1, 0);
    p.takeSnapshot("Snapshot after changing size and position.");
    System.out.println(p.getSnapShotList());
  }

  /**
   * Method to test removing shape from screen.
   */
  @Test
  public void testRemoveShape() {
    p.createShape("r", "Rectangle", 0, 0, 0, 0, 0, 0, 0);
    p.createShape("r2", "rectangle", 0, 0, 0, 0, 0, 0, 0);

    p.removeShape("r");

    assertEquals("Size of screen incorrect.", 1, p.getScreen().size());
    assertEquals("Incorrect shape removed.", "r2", p.getScreen().get(0).getName());

    assertFalse(p.removeShape("o")); // test removing a shape that isn't there
  }
}
