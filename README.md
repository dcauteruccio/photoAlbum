# photoAlbum
Java based Photo Album of Randomly Drawn Pictures

The Photo Album MVC system consists of three main parts:
    1. The Model, which follows the IModel interface and is implemented in the concrete
       PhotoAlbumModel class. Refactoring done from Homework 8 includes adding a helper Snapshot
       class and changing the storage of the shapes currently on screen from a Hashmap to an array.
       The reason for this change was to make the rendering of shapes easier. Having the shapes in a
       list allows the model to keep the order in which the shapes were created, so that if two
       shapes overlap the most recently created shape will be layered on top. While this will make
       referencing shapes for methods like resize, move, and change color go from O(1) to O(n)
       complexity, the number of shapes on the screen at a given time will mostly be small so the
       impact on performance should be unnoticeable. If we were to scale this program, it might
       make sense to revisit this decision and see if we can implement a hash map while keeping the
       order.
    2. The Controller, which follows the PhotoAlbumController interface and is implemented in the
       concrete PhotoAlbumConcreteController class. The Controller takes in an IModel at the start
       so it can easily reference data in the model. The Controller also becomes a parameter for
       the View classes, so the view can use the Controller's functionality to know what data will
       need to be rendered.
    3. The View, which follows the IView interface and is implemented in the two concrete classes,
       ShapeWindow (graphical view) and HtmlView (web view). As mentioned above, the views take in
       a controller as a parameter so they can know what to render and have the controller
       communicate back with the model.

In addition there are a number of helper classes to separate responsibilities:
    1. The Model:
        - With the model, the helper classes are more for storing data in appropriate object
          representations. Most of this is the same from homework 8, with the one addition of the
          Snapshot helper class (described above).
    2. The Controller:
        - The main helper class for the Controller is the FileParser class. This class takes a file
          with directions for the photo album and passes that information into the model.
    3. The View:
        - For the view the main helper classes were built to assist the graphical SwingWindow class.
          One class is the ShapeDrawingArea class, which is the JPanel where shapes get rendered in
          the GUI. The other helper classes are the listener classes, which are there to update the
          GUI when a user clicks on a button at the bottom of the GUI.

Finally, the PhotoAlbumMain class is the front door to our program. This class handles command line
arguments to help specify the data and view to use in the mvc program. In addition to the main
method, there are a few helper classes to parse the command line arguments and make sure the
information was entered fully.
