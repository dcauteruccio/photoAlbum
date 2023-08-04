import org.junit.Test;

import java.io.*;

import controller.PhotoAlbumConcreteController;
import controller.PhotoAlbumController;
import model.IModel;
import model.PhotoAlbumModel;
import snapshot.Snapshot;
import view.HtmlView;
import view.IView;
import view.SwingWindow;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the photoAlbumController class.
 */
public class PhotoAlbumControllerTest {
  IModel m = new PhotoAlbumModel();


  /**
   * Method to test properly reading in a file and passing to the model.
   *
   * @throws FileNotFoundException
   */
  @Test
  public void testInput() throws FileNotFoundException {
    File input = new File("buildings.txt");
    Readable reader = new FileReader(input);
    PhotoAlbumController c = new PhotoAlbumConcreteController(m);
    c.readFile(reader);

    assertEquals("Incorrect number of snapshots.", 3, c.getSnapshotList().size());
    assertEquals("Wrong snapshot loaded on default.", 0, c.getCurrentIndex());
    assertEquals("Incorrect snapshot ID.", m.getSnapShot(0).getId(),
            c.getSnapshotIdList().get(0));

    //test rendering a new snapshot and having model update appropriately
    c.viewSnapshot(1);
    assertEquals("Current snapshot not updated in model.", 1, c.getCurrentIndex());
  }

  /**
   * Method to test the Controller's connection to the view and its ability to render
   * a snapshot.
   *
   * @throws FileNotFoundException
   */
  @Test
  public void testRendering() throws FileNotFoundException {
    File input = new File("buildings.txt");
    Readable reader = new FileReader(input);
    PhotoAlbumController c = new PhotoAlbumConcreteController(m);
    c.readFile(reader);
    c.viewSnapshot(0);
    Snapshot l = m.loadNewSnapshot(0);

    IView view = new HtmlView("testOut.html");

    view.renderSnapshot(c.getSnapshotList());
  }

  // tests to run -
  // check model is collecting all the shapes
  // check that colors are changing
  // check that shapes are moving
  // check that snapshots are taken
  // build in bad data handling

}
