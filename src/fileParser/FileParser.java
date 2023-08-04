package fileParser;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

import model.IModel;

import static java.lang.Integer.parseInt;

/**
 * Helper class to read in a text file with photo album instructions
 * and save the data to the model.
 */
public class FileParser {

  private IModel model;


  /**
   * Constructor for the FileReader class.
   *
   * @param model model to read files into
   */
  public FileParser(IModel model) {
    Objects.requireNonNull(model);
    this.model = model;
  }

  /**
   * Method to read in a .txt file and save the data in an IModel to be used for rendering.
   *
   * @param reader file to read
   * @throws FileNotFoundException
   */
  public void readFile(Readable reader) throws FileNotFoundException {
    Objects.requireNonNull(reader);
    Scanner scan = new Scanner(reader);

    String line;
    String[] lineArray;
    // go through file
    while (scan.hasNextLine()) {
      // remove excess whitespace
      line = scan.nextLine().trim().replaceAll("\\s+", " ");
      if (line.length() == 0) {
        continue;
      }
      lineArray = line.split(" ");
      // if line is a comment
      if (lineArray[0].equals("#")) {
        continue;
      }
      // check what the line is asking to do and perform action
      this.lineAction(lineArray, this.model);
    }
  }


  /**
   * Private Helper method to take commands from text file and perform the appropriate action
   * in the model.
   *
   * @param lineArray  command to parse
   * @param photoAlbum model to execute command
   */
  private void lineAction(String[] lineArray, IModel photoAlbum) {
    // create shape
    if (lineArray[0].equalsIgnoreCase("shape") && lineArray.length == 10) {
      photoAlbum.createShape(lineArray[1], lineArray[2], parseInt(lineArray[3]),
              parseInt(lineArray[4]), parseInt(lineArray[5]), parseInt(lineArray[6]),
              parseInt(lineArray[7]), parseInt(lineArray[8]), parseInt(lineArray[9]));
      // move shape
    } else if (lineArray[0].equalsIgnoreCase("move")) {
      photoAlbum.moveShape(lineArray[1], parseInt(lineArray[2]), parseInt(lineArray[3]));
      // change color
    } else if (lineArray[0].equalsIgnoreCase("color")) {
      photoAlbum.changeShapeColor(lineArray[1], parseInt(lineArray[2])
              , parseInt(lineArray[3]), parseInt(lineArray[4]));
      // resize
    } else if (lineArray[0].equalsIgnoreCase("resize")) {
      photoAlbum.changeSize(lineArray[1], parseInt(lineArray[2]), parseInt(lineArray[3]));
      // remove shape
    } else if (lineArray[0].equalsIgnoreCase("remove")) {
      photoAlbum.removeShape(lineArray[1]);
      // take snapshot
    } else if (lineArray[0].equalsIgnoreCase("snapshot")) {
      if (lineArray.length > 1) { // if there is a description
        String[] description = Arrays.copyOfRange(lineArray, 1, lineArray.length); //subArray
        photoAlbum.takeSnapshot(String.join(" ", description)); // parse rest of line
      } else { // else no description
        photoAlbum.takeSnapshot("");
      }
    }
  }

}
