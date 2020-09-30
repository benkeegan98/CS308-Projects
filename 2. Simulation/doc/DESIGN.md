# Project's Design Goals
Our main goal of this project was to be able to efficiently add new simulations 
that displayed cellular automata with ease. Thus, we focused more on the design 
of the backend/simulation in trying to pass data around safely while giving 
each class a specific purpose and reducing its dependency on other classes.

# High-Level Design
The high level design of our project flows as follows: 
* The Main class handles switching between visual screens and running the 
timeline (each step it calls an update on cells if needed.
* The SimScreen class runs/tells Main to start the simulation.
* The Simulation class has subclasses that specify the rules of each simulation, 
but in general, creates a grid for the backend to use as a way to store cell 
states and locations. 
* By updating the grid in main and then looping through it to update visually on 
the SimScreen, we have a tri-system between the major classes that are in 
constant communication with one another regarding how to update and display 
appropriately.

Please note:  XML parsing and exception handling were not integrated in branch:
master, however, their design can be seen in branch: configuration.

# Assumptions
One assumption is that the user wants the states to be randomized. Because the 
capabiltiy to select between cell state initialization methods was not finsihed,
the XML parser class could not be used to initialize simulations.  As a result, 
only randomization was used to initialize the cell states.  Here, random 
integers in a given range of cell state values based on arbitrary percentages
were assigned to cells.  This method of initilizing the grid was originally for
testing purposes and was supposed to be replaced with a user-based option to chose
between preset initial cell states, completely random cell states, or random 
cell states given a certain proportion.  Additionally, based on the design 
submitted in branch: master, it is assumed that all grids are 50x50.  In branch: 
configuration, the capability to specify the size of the grid in an XML file is 
created, however, this functionality was not ingregated wiht the simulation and 
visualization portions of the project.  

Further, the design submitted in branch: master assumes that all classes and 
methods properly protect their returned items and that these methods check for 
type correctness when accepting arguments.  This sort of checking is not shown 
in branch: master, although basic cases of exception handling can be seen in
branch: configuration.

# Adding New Things
One feature Samantha wanted to add was the cell chart. Although it is currently 
displayed as a blank, all it needs is data to fill it in. Because our Grid class 
is working and already tracks the amount of "0"/empty states, all we would need 
to do is add another arraylist to track the other states and pass it into the 
CellChart class as data and call on an update for the chart each time we update 
the grid. The names will also need to be changed, which can be done in the 
resources folder.

Additionally, exception handling needs to be added to the project design as none
is currently present in branch: master.  In creating effective exception 
handling, one can begin by making sure that input files are of the proper type 
and format, as is shown in branch: configuration, which is directly based off 
of the code provided in spike_simulation.  Moreover, runtime exceptions should 
be added for when getter methods are passing values between classes so as to 
ensure that the correct types are being used.  For example, class gameInfo.java 
has a series of getter methods that can be easily used to access mapped values 
from the XML files.  Should someone in another class try to use the getter 
method to get a value, if they set the returned value of the geter method to an 
incorrect type, ideally there would be an error message that would let the 
individual calling the method know that there is a type mismatch.  Similarly, 
any instance throughout the code where a parameter is being passed, there ought 
to be a runtime exception to check that the types match.  In many cases this is
built in, but including helpful error messages and exceptions that occur as 
close original error as possible can greatly improve the usefulness and 
maintainability of a piece of code.
to the 