package model;

import java.util.List;

import shapes.IShape;
import snapshot.Snapshot;

/**
 * Interface for the Model powering the photoAlbum MVC.
 */
public interface IModel {
  /**
   * Method to create a new shape and place it on canvas.
   * If a shape with the given name already exists, no new shape will be made.
   *
   * @param shape to create
   * @param name  of new shape
   */
  public void createShape(String name, String shape, int x, int y, double width, double height
          , int red, int green, int blue);

  /**
   * Method to return the List representing what is currently on the screen.
   *
   * @return String representing the screen
   */
  public String takeSnapshot(String description);


  /**
   * Method loads a new snapshot based on the specified index of the snapshotList.
   * Adds the snapshot's shape list to the currentScreen and sets currentSnapshot to index.
   *
   * @param index of snapshot
   * @return shapes in snapshot
   */
  public Snapshot loadNewSnapshot(int index);

  /**
   * Method to return the index of the current snapshot in the snapshotList array.
   *
   * @return index of current snapshot
   */
  public int getCurrentSnapshot();

  /**
   * Return the screen object containing all the shapes.
   * Used mostly for testing purposes.
   *
   * @return screen object
   */
  public List<IShape> getScreen();

  /**
   * Method to reset the current screen to a blank canvas.
   */
  public void resetScreen();

  /**
   * Method to clear all snapshots out of the photo album.
   */
  public void clearPhotoAlbum();

  /**
   * Method to return the photo album of snapshots.
   *
   * @return list of snapshots
   */
  public List<String> getSnapShotList();

  /**
   * Method to return the photo album of snapshots as snapshot objects.
   *
   * @return unmodifiable list of snapshots
   */
  public List<Snapshot> getFullSnapShotList();

  /**
   * Method to return the snapshot object from the snapshotList
   * at the given index.
   *
   * @return snapshot object
   */
  public Snapshot getSnapShot(int index);

  /**
   * Method to set the width of the Canvas for model.
   *
   * @param canvasWidth to set to
   */
  public void setCanvasWidth(int canvasWidth);

  /**
   * Method to set the height of the Canvas for model.
   *
   * @param canvasHeight to set to
   */
  public void setCanvasHeight(int canvasHeight);

  /**
   * Method to move the center of the specified shape to the new x,y coordinates.
   *
   * @param shapeName to move
   * @param x         coordinate to move center of shape to
   * @param y         coordinate to move center of shape to
   */
  public void moveShape(String shapeName, int x, int y);

  /**
   * Method to change the size of a given shape.
   *
   * @param shapeName to change
   * @param side1     value1 (width for rectangle, radius 1 for oval)
   * @param side2     value2 (height for rectangle, radius 2 for oval)
   */
  public void changeSize(String shapeName, double side1, double side2);

  /**
   * Method to change the color for the specified shape.
   *
   * @param shapeName to change
   * @param red       decimal value
   * @param green     decimal value
   * @param blue      decimal value
   */
  public void changeShapeColor(String shapeName, int red, int green, int blue);

  /**
   * Method to remove shape with the given name
   * from the current screen.
   *
   * @param name of shape to remove
   * @return true if removed, false if not
   */
  public boolean removeShape(String name);

  /**
   * Method to return the index of the snapshot in the snapshot list.
   *
   * @param id of snapshot
   * @return index of snapshot
   */
  public int getSnapshotIndex(String id);
}
