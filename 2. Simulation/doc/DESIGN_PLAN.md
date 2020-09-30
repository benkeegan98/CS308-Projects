## Introduction

Cellular Automata runs with a simple structure where there is a grid and each cell is updated based on the state of its 
neighbors according to certain rules. Thus, our team wants to create a modular program that does 5 simulations of these 
with the goal of having the least amount of duplicated code as well as having flexible and readable code for future 
coders to add new simulations to or review. These goals also tie into the fact that in the near future we will have to 
add more complicated features to our program. For our high-level design, we're basing it off of composition and 
inheritance following a standard MVC model. The entire program will run off a main class that shows the player the 
different simulation options and calls on a display to constantly populate what the viewer sees. This display will show 
the grid via simulation by running and updating said grid corresponding to the appropriate rules per simulation. For our 
primary architecture, we have have an open/closed system in that we have different layers that handle separate functions 
and may be tested independently. We can then alternate the game without impacting every class except for the controller, 
making the rest open.

### Overview

As mentioned in the introduction, we want to split the program up by composition and how the simulation flows from top 
down. The classes in this design are: Main, Visual, Simulation, Life (for Game of Life), Segregation, PredatorPray (for 
Predator-Prey), Fire, Percolation, and Cell. The Main class runs the entire program (switches between games, handles 
the stage, GUI interface, takes user input) and calls on the Simulation class to populate a 2D grid. The Simulation 
class initializes the cell states based on the rules established in one of the five subclasses specified by Main that 
inherit from Simulation. Therefore, Simulation is the superclass of the five simulation subclasses. In addition to 
initializing, it updates the cells as Main calls on and and runs per step in the timeline. Main then takes the cells 
from Simulation and gives the new grid to the Visual to show the user the current grid also per step. 

### User Interface

The user interface is relatively simple in that there will be 5 tabs with the title of each simulation. When the user 
clicks on each tab (or remains on the first tab), a button will say "Start Simulation", and when clicked, the simulation 
will run accordingly. Due to the way we designed our game without user input besides clicks on the game or tab, we found 
that there were no erroneous situations that would need to be reported to the user based on the user's input. *See 
simulation_team02_UserInterface picture

### Design Details

We decided to separate frontend with backend by having a main class that calls other classes to run the program so that 
if someone said we needed to create a new simulation in 5 minutes, all the coder would have to do is add a subclass 
extending from the Simulation class with the java rules of the game. If the person wanted a bigger grid, they would add 
to the xml file that's already read into the program smoothly, and in general, the design was to reduce the amount of 
"hard-coding" to non-existent. In this way, the program is organized by a composition hierarchy. The next component 
is the five subclasses, which comes from the fact that each simulation needs its own class that establishes the basic 
structure of Cell Automata. However, there is similar code for each, which is why they are all abstracted from the super 
class, Simulation. Simulation handles the shared process of iterating through a 2D grid and updating each cell based on 
its neighbors, getting the rules from the subclasses to do so. By having a separate class to handle this logic, it 
simplifies the requirements for all the classes, shortens the Simulation class, and prevents code smells by taking out 
what would be duplicated logic and code. Another component is the Cell class. This functions as an object that holds its 
current state and a method to update the state for the simulation to call on. The last important component is the 
Visual class. Knowing what simulation the user is on from the Main class, it takes the corresponding XML file and shows 
the grid based on the cell values from Main taken from Simulation. Because we needed a way to have a relationship 
between what the user sees and what the program determines, the Visual class merely takes from XML and Main to properly 
display. 

Regarding use cases, applying rules to cells relies on the Simulation class because the size of the grid is stated in 
each game's subclass in addition to the rules that the iterating method references. That iterating method within 
Simulation knows whether or not the cell is in the middle or the edge because of the parameters of the grid size 
provided by the subclass. Main runs each generation via a step function that calls Simulation to determine the new grid 
with updated values based on the current states established in the Cell class. Because the simulation parameter is 
specific to each game, it is read and listed in its respective subclass that is taken from Main given by the XML file.
For switching simulations, we create separate tabs which tell Main, thus telling Simulation, what subclass to call from 
to update cells appropriately.

### Design Considerations

The alternative designs revolved mainly on how the visual would communicate with the backend, or how to design the 
controller. We considered putting the Simulation class "on top" of the Display class, where the Display would inherit 
values and be updated correctly. Therefore, the step() function would be located in the Simulation class as well as the 
backend grid. We decided against this though because it placed a lot of responsibility on the Simulation class and made 
Main essentially null. Additionally, it blended the lines between interface and backend, which went against our original 
MVC model while worsening our program's flexibility. With our current program, we divided the responsibilities more and 
treat our Main class as the controller that's on the same level with the Visual class (Visualizer) and the Simulation 
class (Model). Because of this triad relationship, the three important classes depend on each other in order to run the 
game (as well as the Simulation class depending on the subclasses rules). Though we currently have no external 
dependencies, we do assume that all the XML files are correct and have usable values for our classes.

### Team Responsibilities

As a team, we decided to split up based on MVC with Ben working on starting the fundamentals for the Simulation class 
and the inheritance relationship (backend), Sam working on Visual and Cell classes with setting up Main to have tabs, 
buttons, and taking XML files, and Amber working on tags for XML and Main class in trying to figure out how to pass 
around needed values between Visual and Simulation. Overall though, we wanted to meet multiple times a week to learn 
each other's work so that we can better our software implementation, knowing that these roles are not independent and 
are open for input from each team member.