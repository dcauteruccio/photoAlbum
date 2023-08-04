import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;


import controller.PhotoAlbumConcreteController;
import controller.PhotoAlbumController;
import model.IModel;
import model.PhotoAlbumModel;
import view.HtmlView;
import view.IView;
import view.SwingWindow;


/**
 * Sample main class to test html and swing rendering for different inputs.
 */
public class SwingWindowMain {
  public static void main(String[] args) throws FileNotFoundException {
    File input = new File("teris_wallpaper.txt");
    Readable reader = new FileReader(input);
    PhotoAlbumController c = new PhotoAlbumConcreteController(new PhotoAlbumModel());
    c.readFile(reader);

    IView html = new HtmlView("terisOut.html");
    html.renderSnapshot(c.getSnapshotList());


    File input2 = new File("buildings.txt");
    Readable reader2 = new FileReader(input2);
    PhotoAlbumController c2 = new PhotoAlbumConcreteController(new PhotoAlbumModel());
    c2.readFile(reader2);
    IView html2 = new HtmlView("buildingsOut.html");
    html2.renderSnapshot(c2.getSnapshotList());

    IView window = new SwingWindow(c2);
  }
}
