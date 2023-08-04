package view;

import java.util.List;

import snapshot.Snapshot;


/**
 * Interface for views of the Photo Album program to adhere to.
 */
public interface IView {


  /**
   * Method to render a snapshot based on a single snapshot instance.
   *
   * @param snapshot to render
   */
  public void renderSnapshot(Snapshot snapshot);

  /**
   * Method to render a snapshot based on a list of snapshots.
   *
   * @param snapshots to render
   */
  public void renderSnapshot(List<Snapshot> snapshots);
}
