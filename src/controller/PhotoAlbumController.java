package controller;

import java.util.List;

import snapshot.Snapshot;

/**
 * Interface for the PhotoAlbumController.
 */
public interface PhotoAlbumController {

  /**
   * Method to parse a readable file and store the data in an IModel.
   *
   * @param reader to parse
   */
  void readFile(Readable reader);

  /**
   * Method to view a specific snapshot and render it on the given view.
   */
  public Snapshot viewSnapshot(int i);

  /**
   * Method to return the snapshot index for the given snapshotID from the model.
   *
   * @param id of snapshot
   * @return index
   */
  public int getSnapshotIndex(String id);

  /**
   * Method to return the list of snapshots available in the model.
   *
   * @return list of snapshots
   */
  public List<Snapshot> getSnapshotList();

  /**
   * Method to return the list of snapshots available in the model.
   *
   * @return list of snapshots
   */
  public List<String> getSnapshotIdList();

  /**
   * Method to return the index of the current snapshot being displayed.
   *
   * @return index
   */
  public int getCurrentIndex();
}
