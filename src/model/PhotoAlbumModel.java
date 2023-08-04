package model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shapes.IShape;
import shapes.Rectangle;
import shapes.Oval;
import snapshot.Snapshot;

/**
 * PhotoAlbumModel class for managing the data and functionality of a photo album.
 * Functionality includes placing shapes on a canvas, moving and altering them,
 * and taking snapshots of the canvas to save to a photo album.
 */
public class PhotoAlbumModel implements IModel {

  private int canvasWidth = 900;
  private int canvasHeight = 900;

  private List<IShape> screenList;
  private List<Snapshot> snapshotList; // list of snapshots in order
  private int currentSnapshot; // index of snapshot currently being shown (0 on startup)

  /**
   * Constructor for the PhotoAlbumModel.
   */
  public PhotoAlbumModel() {
    //this.screen = new HashMap<>();
    this.screenList = new ArrayList<>();
    this.snapshotList = new ArrayList<>();
    this.currentSnapshot = 0;
  }

  /**
   * Method to create a new shape and place it on canvas.
   * If a shape with the given name already exists, no new shape will be made.
   *
   * @param shape to create
   * @param name  of new shape
   */
  public void createShape(String name, String shape, int x, int y, double width, double height
          , int red, int green, int blue) {
    if (this.checkShapeIndex(name) < 0) {
      if (shape.equalsIgnoreCase("Rectangle")) {
        //this.screen.putIfAbsent(name.toUpperCase(), new Rectangle(name, x, y, width, height, red, green, blue));
        this.screenList.add(new Rectangle(name.toLowerCase(), x, y, width, height, red, green, blue));
      }
      if (shape.equalsIgnoreCase("Oval")) {
        //this.screen.putIfAbsent(name.toUpperCase(), new Oval(name, x, y, width, height, red, green, blue));
        this.screenList.add(new Oval(name.toLowerCase(), x, y, width, height, red, green, blue));
      }
    }

  }

  /**
   * Method takes a snapshot of the screen. Method puts a new instance of the Snapshot class in
   * the snapshotList array. The snapshot instance has clones of the shapes currently on screen.
   *
   * @return String representing the new snapshot
   */
  public String takeSnapshot(String description) {
    List<IShape> shapeCopies = this.cloneScreen();

    Snapshot snapshot = new Snapshot(this.getSnapshotID(), this.getTimestamp(),
            description, shapeCopies);

    this.snapshotList.add(snapshot); // add to photoAlbum
    return snapshot.toString();
  }

  /**
   * Method loads a new snapshot based on the specified index of the snapshotList.
   * Adds the snapshot's shape list to the currentScreen and sets currentSnapshot to index.
   *
   * @param index of snapshot
   */
  public Snapshot loadNewSnapshot(int index) {
    if (this.snapshotList.size() > 0) {
      this.resetScreen();
      Snapshot s = this.snapshotList.get(index);
      this.currentSnapshot = index;

      for (int i = 0; i < s.getShapes().size(); i++) {
        this.screenList.add(s.getShapes().get(i));
      }

      return this.snapshotList.get(index);
    }
    return null;
  }

  /**
   * Method to return the index of the current snapshot in the snapshotList array.
   *
   * @return index of current snapshot
   */
  public int getCurrentSnapshot() {
    return this.currentSnapshot;
  }

  /**
   * Return the screen object containing all the shapes.
   * Used mostly for testing purposes.
   *
   * @return screen object
   */
  public List<IShape> getScreen() {
    return Collections.unmodifiableList(this.screenList);
  }

  /**
   * Method to reset the current screen to a blank canvas.
   */
  public void resetScreen() {

    this.screenList.clear();
  }

  /**
   * Method to clear all snapshots out of the photo album.
   */
  public void clearPhotoAlbum() {
    this.snapshotList.clear();
  }

  /**
   * Method to return the photo album of snapshots.
   *
   * @return list of snapshot ids
   */
  public List<String> getSnapShotList() {
    List<String> ids = new ArrayList<>();
    for (int i = 0; i < this.snapshotList.size(); i++) {
      ids.add(this.snapshotList.get(i).getId());
    }
    return ids;
  }

  /**
   * Method to return the photo album of snapshots as snapshot objects.
   *
   * @return unmodifiable list of snapshots
   */
  public List<Snapshot> getFullSnapShotList() {
    return Collections.unmodifiableList(this.snapshotList);
  }

  /**
   * Method to return the snapshot object from the snapshotList
   * at the given index.
   *
   * @return snapshot object
   */
  public Snapshot getSnapShot(int index) {
    if (index > this.snapshotList.size()) {
      throw new IndexOutOfBoundsException("No snapshot at that index.");
    }
    return this.snapshotList.get(index);
  }

  /**
   * Method to set the width of the Canvas for model.
   *
   * @param canvasWidth to set to
   */
  public void setCanvasWidth(int canvasWidth) {
    this.canvasWidth = canvasWidth;
  }

  /**
   * Method to set the height of the Canvas for model.
   *
   * @param canvasHeight to set to
   */
  public void setCanvasHeight(int canvasHeight) {
    this.canvasHeight = canvasHeight;
  }


  /**
   * Method to move the center of the specified shape to the new x,y coordinates.
   *
   * @param shapeName to move
   * @param x         coordinate to move center of shape to
   * @param y         coordinate to move center of shape to
   */
  public void moveShape(String shapeName, int x, int y) {
    this.checkValidPosition(x, y);
    // make sure shape is on screen
    int index = this.checkShapeIndex(shapeName);

    // move if it is
    if (index >= 0) {
      this.screenList.get(index).move(x, y);
    } else {
      throw new IndexOutOfBoundsException("No shape with that name exists on screen");
    }
  }

  /**
   * Method to change the size of a given shape.
   *
   * @param shapeName to change
   * @param width     value1 (width for rectangle, radius 1 for oval)
   * @param height    value2 (height for rectangle, radius 2 for oval)
   */
  public void changeSize(String shapeName, double width, double height) {
    this.checkValidSize(width, height);
    int index = this.checkShapeIndex(shapeName);
    // make sure shape is on screen, change size if it is
    if (index >= 0) {
      this.screenList.get(index).setSize(width, height);
    } else {
      System.out.println("No shape with that name exists on screen.");
      //throw new IndexOutOfBoundsException("No shape with that name exists on screen");
    }
  }

  /**
   * Method to change the color for the specified shape.
   *
   * @param shapeName to change
   * @param red       decimal value
   * @param green     decimal value
   * @param blue      decimal value
   */
  public void changeShapeColor(String shapeName, int red, int green, int blue) {
    int index = this.checkShapeIndex(shapeName);

    // make sure shape is on screen, change size if it is
    if (index >= 0) {
      this.screenList.get(index).setColor(red, green, blue);
    } else {
      System.out.println("No shape with that name exists on screen.");
      //throw new IndexOutOfBoundsException("No shape with that name exists on screen");
    }
  }

  /**
   * Method to remove shape with the given name
   * from the current screen.
   *
   * @param name of shape to remove
   * @return true if removed, false if not
   */
  public boolean removeShape(String name) {

    int index = this.checkShapeIndex(name);
    // make sure shape is on screen, change size if it is
    if (index >= 0) {
      this.screenList.remove(index);
      return true;
    }
    return false;
  }

  /**
   * Method to return the index of the snapshot in the snapshot list.
   *
   * @param id of snapshot
   * @return index of snapshot
   */
  public int getSnapshotIndex(String id) {
    for (int i = 0; i < this.snapshotList.size(); i++) {
      if (this.snapshotList.get(i).getId().equalsIgnoreCase(id)) {
        return i;
      }
    }
    // if id isn't in list, return current snapshot index
    return this.currentSnapshot;
  }


  /********************************************************************
   *********************Private Helper Methods*************************
   ********************************************************************/


  /**
   * Method to return the timestamp in string form of the current time.
   * Used to create a snapshot ID for a snapshot.
   *
   * @return SnapshotID in string format
   */
  private String getSnapshotID() {
    LocalDateTime ldt = LocalDateTime.now();
    return ldt.toString();
  }

  /**
   * Helper method to return the timestamp of current time.
   *
   * @return String representation of timestamp
   */
  private String getTimestamp() {
    // formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
    String date = formatter.format(LocalDateTime.now().toLocalDate()); // get current date
    LocalTime time = LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
    return date + " " + time;
  }

  /**
   * Helper method to check that position inputs are valid
   * before moving a shape.
   *
   * @param x to check
   * @param y to check
   */
  private void checkValidPosition(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("New coordinates must be within the canvas.");
    }
  }

  /**
   * Helper method to check that size inputs are valid (are not bigger than the canvas).
   *
   * @param side1 to check
   * @param side2 to check
   */
  private void checkValidSize(double side1, double side2) {
    if (Math.abs(side1) > (this.canvasWidth) || Math.abs(side2) > (this.canvasHeight)
            || side1 <= 0 || side2 <= 0) {
      throw new IllegalArgumentException("Side lengths invalid.");
    }
  }

  /**
   * Private helper method to clone the shapes on the screen into a new list. The new list
   * will be stored in the new snapshot.
   *
   * @return list of cloned shapes
   */
  private List<IShape> cloneScreen() {
    List<IShape> shapeCopies = new ArrayList<>();
    for (int i = 0; i < this.screenList.size(); i++) {
      IShape s = this.screenList.get(i);
      if (s.getType().equalsIgnoreCase("Rectangle")) {
        IShape r = new Rectangle(s.getName(), s.getX(), s.getY(), s.getWidth(),
                s.getHeight(), s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue());
        shapeCopies.add(r);
      }
      if (s.getType().equalsIgnoreCase("Oval")) {
        IShape r = new Oval(s.getName(), s.getX(), s.getY(), s.getWidth(),
                s.getHeight(), s.getColor().getRed(), s.getColor().getGreen(), s.getColor().getBlue());
        shapeCopies.add(r);
      }
    }
    return shapeCopies;
  }

  /**
   * Private helper method to check the index on the screen of the given shape.
   *
   * @param shapeName to check
   * @return index, -1 if it doesn't exist
   */
  private int checkShapeIndex(String shapeName) {
    int index = -1;
    for (int i = 0; i < this.screenList.size(); i++) {
      if (this.screenList.get(i).getName().equalsIgnoreCase(shapeName)) {
        index = i;
      }
    }
    return index;
  }

}
