package view.listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.PhotoAlbumController;
import model.IModel;
import view.ShapeDrawingArea;

/**
 * Class representing the actionListener for the Next button.
 */
public class MyNextListener implements ActionListener {

  private Container cp;
  private PhotoAlbumController c;
  private ShapeDrawingArea v;
  private JLabel p;

  /**
   * Constructor for the actionListener of the Next button.
   *
   * @param cp parent container
   * @param c  controller
   * @param v  ShapeDrawingArea
   * @param p  JLabel
   */
  public MyNextListener(Container cp, PhotoAlbumController c, ShapeDrawingArea v, JLabel p) {
    this.cp = cp;
    this.c = c;
    this.v = v;
    this.p = p;
  }

  /**
   * Override of the actionPerformed method. Sets the action to be taken
   * when the Next button is clicked on.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {

    if (this.c.getCurrentIndex() + 1 < this.c.getSnapshotList().size()) {
      // get the next snapshot in list and render on screen
      v.getSnapshot(this.c.viewSnapshot(this.c.getCurrentIndex() + 1));

      // get ID to add to info bar
      // do not need to add one to index as c.viewSnapshot will update current snapshot in model
      String id = this.c.getSnapshotList().get(this.c.getCurrentIndex()).getId();
      this.setJLabel(id);

      // repaint
      cp.repaint();

    } else {
      JOptionPane.showMessageDialog(this.cp, "No more snapshots to view.");
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