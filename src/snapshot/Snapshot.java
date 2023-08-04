package snapshot;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import shapes.IShape;

/**
 * Class to represent a snapshot of the photo album. Class stores information about the snapshot
 * such as the ID, the description, and the list of shapes on the screen.
 */
public class Snapshot {
  private String id;
  private String timestamp;
  private String description;
  private List<IShape> shapes;

  /**
   * Constructor for the Snapshot class. Given that this is used in the IModel, there is the
   * assumption that most inputs are good.
   *
   * @param id          of snapshot
   * @param description of snapshot
   * @param shapes      in snapshot
   */
  public Snapshot(String id, String timestamp, String description, List<IShape> shapes) {
    this.id = id;
    this.timestamp = timestamp;
    this.description = description;
    this.shapes = shapes;
  }

  /**
   * Method to return the Snapshot ID.
   *
   * @return snapshotID
   */
  public String getId() {
    return this.id;
  }

  /**
   * Method to return the snapshot description.
   *
   * @return snapshot description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Method to return the shapes in the snapshot.
   *
   * @return snapshot shapes
   */
  public List<IShape> getShapes() {
    return this.shapes;
  }

  /**
   * ToString method override to represent snapshot as a string.
   *
   * @return string representation of snapshot
   */
  public String toString() {
    String snapshot = "Snapshot ID: " + this.id
            + "\nTimestamp: " + this.timestamp
            + "\nDescription: " + this.description
            + "\nShape Information:\n";

    for (int i = 0; i < this.shapes.size(); i++) {
      snapshot = snapshot.concat(this.shapes.get(i).toString());
    }
    return snapshot;
  }

}
