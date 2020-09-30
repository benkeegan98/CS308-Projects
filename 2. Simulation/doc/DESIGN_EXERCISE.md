### RPS Game
*See image of CRC cards

### Review Other's Code
For smw81 code, I could split up the app.java class by associating certain methods with their respective classes. For
example, setting the ball's behavior in the ball class as opposed to doing so in the main class. Focusing on the 
collide method, also I should split this up and distribute appropriately by creating more methods to pass and return 
whatever instance variables are needed. Regarding inheritance, in the Bricks class I set new bricks based on an integer
 parameter, which instead could be replaced by creating new subclasses that inherit from the super Bricks class. This 
 goes the same for the power ups or anywhere in the code where I use types to define a new type.

### Design Ideas for Cell Society
Instead of having a class for each game, we were thinking of implementing a design where there is one class that sets 
the grid by iterating through a 2D array of objects representing cells. Each cell will be its own object that contains the state of itself. In the
main class, it will check each cell by checking its neighbors and update appropriately based on the rules. The rules 
will be fed into the game class and stated from a superclass that has the rules, how many cells, and what color/how the
cells should be initially populated. Simulation is the super class where each type of simulation (like fire, percolation
, etc) will inherit from it. Setting the simulation parameter comes from the xml files which will also hold what each 
simulation has for its rules. The graphical view of the simulation updates based on the GUI by taking the new simulation
and displaying that once the simulation returns that each of the cells have updated appropriately.