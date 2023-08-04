package view.listeners;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.PhotoAlbumController;
import model.IModel;
import view.ShapeDrawingArea;

/**
 * ActionListener class for the Select button. When clicked, button will display
 * a dropdown list of snapshots and allow the user to click one to load.
 */
public class MySelectListener implements ActionListener {

  private Container cp;
  private PhotoAlbumController c;
  private ShapeDrawingArea v;
  private JLabel p;


  /**
   * Constructor for the actionListener of the Next button.
   *
   * @param cp parent container
   * @param c  Controller
   * @param v  ShapeDrawingArea
   * @param p  JLabel
   */
  public MySelectListener(Container cp, PhotoAlbumController c, ShapeDrawingArea v, JLabel p) {
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
    if (c.getSnapshotList().size() > 0) {
      // open dropdown list and let user select which snapshot to load
      Object option = JOptionPane.showInputDialog(this.cp, "Choose One", "Menu",
              JOptionPane.QUESTION_MESSAGE, null,
              (Object[]) c.getSnapshotIdList().toArray(), null);

      // cast snapshot id back to string and get index to load new snapshot
      String id = (String) option;
      int i = c.getSnapshotIndex(id);
      v.getSnapshot(c.viewSnapshot(i));

      // Add snapshot id to JLabel
      this.setJLabel(id);

      cp.repaint();

    } else {
      JOptionPane.showMessageDialog(this.cp, "No snapshots available.");
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