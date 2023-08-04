package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import snapshot.Snapshot;
import shapes.IShape;

/**
 * Class to render a web view for the Photo Album program.
 * Adheres to the IView interface.
 */
public class HtmlView implements IView {

  public static int WIDTH = 1000;
  public static int HEIGHT = 1000;

  private List<Snapshot> snapshotList;
  private String fileName;

  /**
   * Constructor for the HtmlView class.
   *
   * @param outFile to save web view to.
   */
  public HtmlView(String outFile) {
    this.fileName = outFile;
  }

  /**
   * Constructor for the HtmlView class.
   *
   * @param outFile to save web view to.
   */
  public HtmlView(String outFile, int xmax, int ymax) {
    this.fileName = outFile;
    this.WIDTH = xmax;
    this.HEIGHT = ymax;
  }

  @Override
  public void renderSnapshot(List<Snapshot> snapshots) {
    StringBuilder file = this.buildFile(snapshots);

    try (FileWriter outputFile = new FileWriter(this.fileName)) {
      outputFile.write(file.toString());
    } catch (IOException e){
      System.out.println("Error creating HTML file.");
    }
  }


  @Override
  public void renderSnapshot(Snapshot snapshot) {
    // no op
  }

  /**
   * Private helper method to construct the HTML and SVG for the web view.
   *
   * @param snapshots list of snapshots to render
   *
   * @return StringBuilder of HTML
   */
  private StringBuilder buildFile(List<Snapshot> snapshots) {
    StringBuilder file = new StringBuilder();

    file.append("<!DOCTYPE html>\n" + "<html>\n" + "<body>\n" + "<h1>PhotoAlbum</h1>\n");


    // + "viewport \"0 0 1000 1000\" xmlns=\"http://www.w3.org/2000/svg\" >\n");
    //file.append(this.buildSnapShotSVG(snapshots.get(0)));

    // loop to go through snapshots
    for (int i = 0; i < snapshots.size(); i++) {
      file.append("<div>\n");
      file.append("<svg width=\"" + this.WIDTH + "\" height=\"" + this.HEIGHT +
              "\" xmlns=\"http://www.w3.org/2000/svg\">\n");
      // append
      this.buildSnapShotSVG(snapshots.get(i), file);
      file.append("</svg>\n");
      file.append("</div>\n");
    }
    file.append("</body>\n" + "</html>");
    return file;
  }

  /**
   * Method to build SVG text based on the given snapshot.
   *
   * @param s snapshot to translate into SVG
   * @return SVG text
   */
  private StringBuilder buildSnapShotSVG(Snapshot s, StringBuilder file) {
    //StringBuilder file = new StringBuilder();
    List<IShape> shapes = s.getShapes();


    //file.append("<svg width=\"9cm\" height=\"9cm\">\n"); // viewbox = 0 0 900 900>\n");
    file.append("<h2>" + s.getId() + "</h2>\n");
    if(!s.getDescription().equalsIgnoreCase("")) {
      file.append("<h4>" + s.getDescription() + "</h4>\n");
    }

    // iterate through shapes in snapshot
    for (int i = 0; i<s.getShapes().size(); i++) {
      IShape ss = s.getShapes().get(i);
      if (ss.getType().equalsIgnoreCase("Rectangle")) {
        //file.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");
        file.append("<rect x=\"" + ss.getX() + "\" y=\"" + ss.getY() + "\" width=\"" +
                ss.getWidth() + "\" height=\"" + ss.getHeight() + "\" style=\"fill:rgb" +
                ss.getColor().toString() + "; " + " stroke-width:1;stroke:rgb" +
                ss.getColor().toString() + "\"/>\n");
        //file.append("</svg>\n");
      } else if (ss.getType().equalsIgnoreCase("Oval")) {
        //file.append("<svg xmlns=\"http://www.w3.org/2000/svg\">\n");
        file.append("<ellipse cx=\"" + ss.getX() + "\" cy=\"" + ss.getY() + "\" rx =\"" +
                ss.getWidth() + "\" ry =\"" + ss.getHeight() + "\" style=\"fill:rgb" +
                ss.getColor().toString() + "; " + " stroke-width:1;stroke:rgb" +
                ss.getColor().toString() + "\"/>\n");
        //file.append("</svg>\n");
      }
    }

    return file;
  }

}
