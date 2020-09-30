simulation
====

This project implements a cellular automata simulator.

Names: Amber Johnson, Ben Keegan, Samantha Whitt

### Timeline

Start Date: 15 September 2019

Finish Date: 30 September 2019

Hours Spent: Samantha - 30 hours

### Primary Roles
Samantha was in charge of visualization, which included building resource files, creating screens and new components, 
adding an intuitive GUI, and communicating with backend on how to get colors/shapes from the cells, update visually and 
and backend-wise as well as with configuration regarding accepting certain set parameters like cells amount. Her branch 
is called visualization.

Ben was in charge of simulation, working on all of the backend, Cell classes, Grid class, and each simulation class. His 
branch is called simulation.

### Resources Used
Samantha used StackOverflow for reading resource files and implementing actions on screen components. Java2s, git 
connected, and oracle were also referenced for more complex components like the line charts and drop downs.

### Running the Program

Main class: Main.java

Data files needed: all properties files in the resources folders

Interesting data files: xml files in the xml folder (for each simulation)

Features implemented: play a simulation, change between simulations with buttons, step through a simulation, speed up 
the simulation via slider, show grid lines if desired, 

Assumptions or Simplifications: We assume that there is no user input besides choice of what we give

Known Bugs: 

Extra credit:


### Notes
We couldn't implement xml in our simulation, so all of the initial states are randomized accordingly. The chart and 
polygon class were also not implemented because they required a more functional design, which we prioritized more 
towards the end.

### Impressions
This project was extremely challenging in trying to follow a standard MVC model. We found that despite splitting up the 
work, our parts integrated more than we had imagined. Additionally, this was our first time using abstract classes, as 
taught in class to reduce as many code smells as we could. We found that although this helped in our class length, it 
added another challenging element of figuring out how to correctly, and safely, pass around our data from class to class.
For example, we changed more specific elements like arraylists to lists to better protect. By using this strategy and 
taking string from the resources file with keys/values, a future coder could add more onto the pre-existing elements 
more easily and code-wide, which contributes to a more readable and better designed code.
