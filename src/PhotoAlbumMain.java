import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.PhotoAlbumConcreteController;
import controller.PhotoAlbumController;
import model.IModel;
import model.PhotoAlbumModel;
import view.HtmlView;
import view.IView;
import view.SwingWindow;

/**
 * Class with the Main Entry point for the Photo Album Program. Class parses
 * command line inputs and
 */
public class PhotoAlbumMain {

  private static HashMap<String, String> cArgs = new HashMap<>();
  private static int xmax = 1000;
  private static int ymax = 1000;
  private static boolean xmaxFlag = false; // flag to check xmax / ymax inputs


  /**
   * Private helper method to parse the command line arguments
   *
   * @param args
   */
  private static void parseArgs(String[] args) {
    // check min arg len
    if (args.length < 4) {
      System.out.println("Program requires at least an '-in' and a '-v' specification.");
      System.exit(-1);
    }

    for (int i = 0; i < args.length; i++) {
      if ((args[i].equalsIgnoreCase("-view") || args[i].equalsIgnoreCase(("-v")))
              && i < args.length - 1) {// check to make sure we won't get indexation error
        cArgs.putIfAbsent("view", args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-in") && i < args.length - 1) {
        cArgs.putIfAbsent("in", args[i + 1]);
      } else if (args[i].equalsIgnoreCase("-out") && i < args.length - 1) {
        cArgs.putIfAbsent("out", args[i + 1]);
      } else {
        checkIntegerArgs(args[i]);
      }
    }

    checkValidArgs();
  }

  /**
   * Helper method to parse optional xmax and ymax inputs. Method checks if the arg can be
   * parsed as an int. If so, the method then checks if an integer arg was already specified to
   * determine if the new arg is the xmax or ymax input.
   *
   * @param arg to check
   */
  private static void checkIntegerArgs(String arg) {
    try {
      Integer.parseInt(arg);
      if (!xmaxFlag) { // if xmax was already specified, set ymax
        xmax = Integer.parseInt(arg);
        xmaxFlag = true;
      } else {
        ymax = Integer.parseInt(arg);
      }
    } catch (NumberFormatException e) {
    }
  }

  /**
   * Private helper method to check the command line arguments were inputted correctly.
   * Checks include making sure there is an -in file and a -view specification.
   * Additionally, a check is done to make sure a -out file is specified for web view.
   */
  private static void checkValidArgs() {
    if (!cArgs.containsKey("in") || !cArgs.containsKey("view")) {
      System.out.println("Program requires an '-in' specification and a '-v' specification.");
      System.exit(-1);
    }

    if (cArgs.get("view").equalsIgnoreCase("web") && !cArgs.containsKey("out")) {
      System.out.println("Program requires an '-out' specification for web view rendering.");
      System.exit(-1);
    }
  }

  /**
   * Private helper method to render the view specified by the user.
   * Prints a message and exits program is view is not graphical or web.
   *
   * @param c controller of the Photo Album program
   */
  private static void renderView(PhotoAlbumController c) {
    if (cArgs.get("view").equalsIgnoreCase("graphical")) {
      IView g = new SwingWindow(c, xmax, ymax);
    } else if (cArgs.get("view").equalsIgnoreCase("web")) {
      IView w = new HtmlView(cArgs.get("out"), xmax, ymax);
      w.renderSnapshot(c.getSnapshotList());
    } else {
      System.out.println("Please specify \"graphical\" or \"web\" for the view.");
      System.exit(-1);
    }
  }


  /**
   * Main driver method for the PhotoAlbum program.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    // read in command line args and check to make sure all info is present
    parseArgs(args);

    IModel m = new PhotoAlbumModel();
    PhotoAlbumController c = new PhotoAlbumConcreteController(m);

    // read -in file
    try {
      c.readFile(new FileReader(new File(cArgs.get("in"))));
    } catch (FileNotFoundException e) {
      System.out.println("No file with that name found.");
      System.exit(-1);
    }
    // render view
    renderView(c);
  }
}