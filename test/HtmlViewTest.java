import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import controller.PhotoAlbumConcreteController;
import controller.PhotoAlbumController;
import model.PhotoAlbumModel;
import view.HtmlView;
import view.IView;

/**
 * Test class for the Html View.
 * Tests are performed by trying different parameters to see output.
 */
public class HtmlViewTest {

  public static void main(String[] args) throws FileNotFoundException {

    // test rendering buildings.txt
    PhotoAlbumController c = new PhotoAlbumConcreteController(new PhotoAlbumModel());
    IView html = new HtmlView("testBuildingsOut.html");
    c.readFile(new FileReader(new File("buildings.txt")));
    html.renderSnapshot(c.getSnapshotList());

    // test demo_input.txt
    PhotoAlbumController c2 = new PhotoAlbumConcreteController(new PhotoAlbumModel());
    IView html2 = new HtmlView("testDemoOut.html");
    c2.readFile(new FileReader(new File("demo_input.txt")));
    html2.renderSnapshot(c2.getSnapshotList());

    //html files are being rendered, but html/svg is off and not showing up properly
  }

}
