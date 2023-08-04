package view;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import controller.PhotoAlbumController;
import snapshot.Snapshot;
import view.listeners.MyCloseListener;
import view.listeners.MyNextListener;
import view.listeners.MyPreviousListener;
import view.listeners.MySelectListener;

/**
 * Concrete class implementation for the Swing GUI.
 */
public class SwingWindow extends JFrame implements IView {
  public static int WIDTH = 1000;
  public static int HEIGHT = 1000;

  private Container cp;
  private ShapeDrawingArea snapshotBar;
  private JPanel buttonBar;
  private JPanel infoBar;
  private JLabel infoLabel;
  private PhotoAlbumController c;

  /**
   * Constructor for SwingWindow class. Sets up JFrame and adds button functionality.
   *
   * @param c controller connected to view
   */
  public SwingWindow(PhotoAlbumController c) {
    super(); // creates JFrame
    this.c = c;
    this.infoLabel = new JLabel();

    // initial JFrame setup
    this.setUpWindow();
    // render 1st snapshot on startup
    this.renderSnapshot(c.viewSnapshot(0));
    this.pack(); // needed? - trying to dynamically size
    setVisible(true);
  }

  /**
   * Constructor for SwingWindow class. Sets up JFrame and adds button functionality.
   *
   * @param c controller connected to view
   * @param xmax width
   * @param ymax height
   */
  public SwingWindow(PhotoAlbumController c, int xmax, int ymax) {
    super(); // creates JFrame
    this.c = c;
    this.WIDTH = xmax;
    this.HEIGHT = ymax;
    this.infoLabel = new JLabel();

    // initial JFrame setup
    this.setUpWindow();
    // render 1st snapshot on startup
    this.renderSnapshot(c.viewSnapshot(0));
    this.pack(); // needed? - trying to dynamically size
    setVisible(true);
  }

  /**
   * Method render snapshot takes a snapshot and renders it on the GUI, including adding the
   * snapshot id and description.
   *
   * @param snapshot to render
   */
  @Override
  public void renderSnapshot(Snapshot snapshot) {
    this.snapshotBar.getSnapshot(snapshot);
    this.setJLabel(snapshot);
    this.snapshotBar.repaint();
  }

  /**
   * Method renders snapshots, taking in the entire list of snapshots to render.
   *
   * @param snapshots to render
   */
  @Override
  public void renderSnapshot(List<Snapshot> snapshots) {
    // no op
  }

  /********************************************************************
   *********************Private Helper Methods*************************
   ********************************************************************/

  /**
   * Private Helper method to set up the general layout of the Swing Gui.
   */
  private void setUpWindow() {
    super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    super.setSize(super.getPreferredSize());
    setTitle("CS5004 Shapes Photo Album Viewer");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.buttonBar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonBar.setBackground(Color.ORANGE);
    this.infoBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
    infoBar.setBackground(Color.PINK);
    // don't think I need to specify width and height here?
    this.snapshotBar = new ShapeDrawingArea(this.c);


    this.cp = getContentPane();
    BorderLayout border = new BorderLayout();
    this.cp.setLayout(border);
    this.cp.setBackground(new Color(100,149,237));

    // Add buttons and setup functionality
    add(this.buttonBar, BorderLayout.SOUTH);
    add(this.infoBar, BorderLayout.NORTH);
    add(this.snapshotBar);
    this.setUpButtons();
    this.infoBar.add(this.infoLabel);
  }

  /**
   * Private Helper Method to set up the appropriate buttons for the Swing GUI.
   */
  private void setUpButtons() {
    // Prev button
    JButton prevButton = new JButton("<< Prev <<");
    prevButton.addActionListener(new MyPreviousListener(this.cp, this.c,
            this.snapshotBar, this.infoLabel));
    this.buttonBar.add(prevButton);

    // Select button
    JButton selectButton = new JButton("^^ Select ^^");
    selectButton.addActionListener(new MySelectListener(this.cp, this.c,
            this.snapshotBar, this.infoLabel));
    this.buttonBar.add(selectButton);

    // Next button
    JButton nextButton = new JButton(">> Next >>");
    nextButton.addActionListener(new MyNextListener(this.cp, this.c,
            this.snapshotBar, this.infoLabel));
    this.buttonBar.add(nextButton);

    // End Button
    JButton endButton = new JButton("xx Quit xx");
    endButton.addActionListener(new MyCloseListener());
    this.buttonBar.add(endButton);
  }

  /**
   * Private helper method to set the JLabel with snapshot ID
   * and description, if there is one.
   *
   * @param snapshot to use to set label
   */
  private void setJLabel(Snapshot snapshot) {
    String desc = snapshot.getDescription();
    String id = snapshot.getId();

    // if there is a description add it, otherwise only use the snapshot id
    if(desc.equalsIgnoreCase("")) {
      this.infoLabel.setText(id);
    } else {
      this.infoLabel.setText(id + ":\n" + desc);
    }
  }
}