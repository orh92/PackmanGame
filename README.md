#  About the project: 
This is a packman game! You can play it on a JFrame and on a google earth!
So how it works? 
Run the game, and by the menu bar you can start.
Choose the way that you want to play.
In the item "options" you can add packman's and fruits  as much as you want and also restart as it looks in the picture:

![github-small](https://raw.githubusercontent.com/orh92/PackmanGame/master/1.bmp)


For starting the game press on "Run" in the same item.

In the item "Start game from a file" you can upload a CSV file (as much as you want) to get preset locations:
![github-small](https://raw.githubusercontent.com/orh92/PackmanGame/master/2.bmp)

An example of how a CSV file should look:
![github-small](https://raw.githubusercontent.com/orh92/PackmanGame/master/3.bmp)

And you can also mix your packman's and fruits with a CSV files.
Also, you can save your information as a CSV file and / or as a KML file in order to see the path in Google Earth in the same item!

How the game look like:
![github-small](https://raw.githubusercontent.com/orh92/PackmanGame/master/4.bmp)

As soon as the Run button is pressed, the Packmans will move toward the fruit, and each Pacman will run to the nearest fruit and eat it. The goal of all the Packmen is to finish all the fruits that are on the map.




#  How is the project built?
The project built from the next packages: 

  - Geom:
A package of geometry that includes points, lines, paths, circles, squares, etc.

*POINT3D- implements  Geom_element. representing a point in space or on map using both polar coordinates or cartezian. 

*MyCoords -implements coords_converter. Used to calculate mathematical functions.

  - Game:

*Game class- Contains an array of objects related to the game.

*MoveManager- This class implements THREADS. Promotes each Pacman according to the allotted time, and synchronizes all of them.

*PackmanMove- This class implements THREADS. Manages the thread of each Pacman.

 - GameObjects:
 
 *Fruit-A class that represents a "target" in a known geographic location.

*GameObjects class-Contains information of location, speed, radius of the objects.

*Packman- A class that represents a "robot" with a location, orientation and ability to move (The speed Is defined).

 - Packman:

*main- by this class you can start the game.

 - Path:
 
 *path class- This class calculates the length of the route that Pacman passed through.

*Path2KML- This class converts the information of the Pacman path into a KML file in order to see the path in Google Earth.

*PathListComperator-Sorting the length of the paths to the fruits.

*ShortestPathAlgo- This class contains the algorithm in which each Pacman searches for the nearest fruit. The class contains a set of fruits and packmans, and moves along the arrays respectively, matching the fruit to its closest packman and collecting him.
 
 - Map:
 
 *Map class-This class takes the coordinates of the map in Google Earth and the pixels of the image and converts from pixels to coordinates and vice versa.

*Csvload-This class allows you to upload a prepared CSV file from your computer and use its data to display it on the map.

*Csvsave-This class allows you to save the game's data as a CSV file on your computer.

 - GUI:
 
 *MyFrame- This class uses a GUI, produces a window, a menu, uploads the images of the Pacman, fruit and map. In addition, the class implements a MouseListener and ComponentListener with which you can use the mouse and create as many Pacman and Fruit as you want and redesigned the pictures if we increased or reduced the window. 
 Also contains the paths to the picture of the map,fruit and packman. So make sure you writing the correct path.
 
 That's it! Have fun :)
 
 
#  Author: Guy Ankri & Or Hadar
