package view.listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.PhotoAlbumController;
import view.ShapeDrawingArea;

/**
 * Class representing the actionListener for the Previous button.
 */
public class MyPreviousListener implements ActionListener {

  private Container cp;
  private PhotoAlbumController c;
  private ShapeDrawingArea v;
  private JLabel p;

  /**
   * Constructor for the actionListener of the Previous button.
   *
   * @param cp parent container
   * @param c  Controller
   * @param v  ShapeDrawingArea
   * @param p  JLabel
   */
  public MyPreviousListener(Container cp, PhotoAlbumController c, ShapeDrawingArea v, JLabel p) {
    this.cp = cp;
    this.c = c;
    this.v = v;
    this.p = p;
  }

  /**
   * Override of the actionPerformed method. Sets the action to be taken
   * when the Previous button is clicked on.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.c.getCurrentIndex() - 1 >= 0) {
      // get previous snapshot and render
      v.getSnapshot(this.c.viewSnapshot(this.c.getCurrentIndex() - 1));

      // get ID to add to infoBar
      // do not need to sub one from index as c.viewSnapshot will update current snapshot in model
      String id = this.c.getSnapshotList().get(this.c.getCurrentIndex()).getId();
      this.setJLabel(id);

      cp.repaint();
    } else {
      JOptionPane.showMessageDialog(this.cp, "No previous snapshots to view.");
    }
  }

  /**
   * Private helper method to update the JLabel with snapshot ID
   * and description, if there is one.
   *
   * @param id of snapshot
   */
  private void setJLabel(String id) {
    int i = this.c.getSnapshotIndex(id);
    String desc = this.c.getSnapshotList().get(i).getDescription();
    // check if there is a description for the snapshot, include if there is
    if (desc.equalsIgnoreCase("")) {
      p.setText(id);
    } else {
      p.setText(id + ":\n" + desc);
    }
  }
}
