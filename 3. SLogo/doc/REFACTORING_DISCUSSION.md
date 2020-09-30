# REFACTORING_DISCUSSION.md

### Team 06
**Members:**
Chloe Gura (cg209@duke.edu)
Ben Keegan (bk142@duke.edu)
Jonah Knapp (jk366@duke.edu)
Junyu Liang (jl751@duke.edu)

## Design Issues Ranked by Priority 
### Magic values in front end
* Make a CSS file and read in the parameters
* Do not hard code any values for setting up the view 

### Duplication in settingsPane 
* Remove any duplicates into a separate method 

### Tree Builder if Statment 
* Eliminate the if statment within the treeBuilder. It is the largest method within the project and has an if statement that is unnecessarily long.
* Use the design patterns discussed in class to allow the parsing to be done through reflection 
* Identify similarities between the different if statements and use this to create a general call. 
### Further Backend Turtle Abstraction
* Abstract the concept of the turtle to eventually allow for multiple turtles (backend)
* Make the turtle obejct in the backend refer to a turtle controller/handler, rather than an actual turtle. This will lead to an efficient/easy way to implement Turtles 
* Use the composite design pattern that was described in class 

### More Substantial Error Checking
* Test the program extensively to ensure that all possible errors are caught and handled approprately 
* Address issue of "preserving original error", as described on the design.cs website. 

### Light Duplication in Commands
* Ensure there is no duplication in any of the commands. There are a few isolated instances where last minute changes were made and not refactored.
* Easy fix that is unique to individual commands
* Go through and look for duplication (facilitated by the design website)

### Control Structure Curly Braces 
* Fix instances in the code where curly braces do not frame the content of if statments 

