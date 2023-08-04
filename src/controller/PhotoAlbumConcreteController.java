package controller;

import java.util.List;
import java.util.Objects;
import java.io.*;

import fileParser.FileParser;
import model.IModel;
import snapshot.Snapshot;


/**
 * Concrete Controller class for the PhotoAlbum Program.
 */
public class PhotoAlbumConcreteController implements PhotoAlbumController {

  private IModel model;
  private Appendable out;
  private FileParser parser;

  /**
   * Constructor for the PhotoAlbum Concrete controller class. Takes in a photoAlbum model as
   * its parameter. Also creates a FileParser instance to help parse input files into the model.
   *
   * @param photoAlbum model
   */
  public PhotoAlbumConcreteController(IModel photoAlbum) {
    Objects.requireNonNull(photoAlbum);
    this.model = photoAlbum;
    this.parser = new FileParser(this.model);
  }

  /**
   * Method to read in a file to the IModel. Method utilizes the FileParser to add data into
   * the model.
   *
   * @param readable
   */
  @Override
  public void readFile(Readable readable) {
    try {
      this.parser.readFile(readable);
    } catch (FileNotFoundException e) {
      System.out.println("Could not parse file, please try again.");
    }
  }

  /**
   * Method to view a specific snapshot and render it on the given view.
   */
  public Snapshot viewSnapshot(int i) {
    return this.model.loadNewSnapshot(i);
  }

  /**
   * Method to return the snapshot index for the given snapshotID from the model.
   *
   * @param id of snapshot
   * @return index
   */
  @Override
  public int getSnapshotIndex(String id) {
    return this.model.getSnapshotIndex(id);
  }

  /**
   * Method to return the list of snapshots available in the model.
   *
   * @return list of snapshots
   */
  @Override
  public List<Snapshot> getSnapshotList() {
    return this.model.getFullSnapShotList();
  }

  /**
   * Method to return the list of snapshots available in the model.
   *
   * @return list of snapshots
   */
  @Override
  public List<String> getSnapshotIdList() {
    return this.model.getSnapShotList();
  }

  /**
   * Method to return the index of the current snapshot being displayed.
   *
   * @return index
   */
  @Override
  public int getCurrentIndex() {
    return this.model.getCurrentSnapshot();
  }

}
