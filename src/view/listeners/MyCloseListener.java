package view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Method to drive the quit button on the Swing GUI.
 */
public class MyCloseListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.exit(0);
  }
}