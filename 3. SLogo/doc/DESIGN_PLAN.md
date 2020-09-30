# SLogo - Design
### Team 6
Chloe Gura (cg209@duke.edu)
Ben Keegan (bk142@duke.edu)
Jonah Knapp (jk366@duke.edu)
Junyu Liang (jl751@duke.edu)

## Introduction
* This project aims to create a Java program using OpenJFX that provides an integrated development environment, IDE, that supports users to write SLogo programs. The IDE would allow the user to command the turtle interactively on a per instruction basis. 

* We aim to make the set of input instructions flexible. And the primary structure of the design is separated into two parts: backend and frontend. Backend takes care of parsing and executing the intructions. Frontend takes care of visualizations, and retrieving and passing along user inputs. For both the frontend and backend part, they are subdivided into a set of internal classes, and a set of external classes. 

## Overview
* The design of our SLogo implementation is split up into four basic parts: Backend Internal, Backend External, Frontend Internal, and Frontend External. The functions of each of these components are broken down further in the Design Details section of this document. The reason that we decided to split up our project into these parts was so that each section had very specific, intended purposes. 

* The Frontend section only deals with properties that are unique to the display the interaction with the display. Furthermore, the internal section of the frontend only deals with actions that do not need to be controlled by the backend of the project. This inlcudes behavior such  as keeping track of History, controlling the settings of the program, and maintaining the TurtleView and PenViews of the display. On the other hand, the frontend external contains functions that do need to be able to be controlledb y the backend. This includes Displaying various exceptions and updating the states of the Pen and Turtle.
* The Backend section only deals with behavior that is unique to the computation and parsing of the input received by the frontend. The external component of the Backend deals with Parsing Commands, keeping track of the different types of operators, keeping track of the Pen and Turtle actions, and deciding what to so with user commands that were received. Finally, the Backend internal deals with commands that do not interface iwth the frontend, such as handling commands once they are parsed, creating variables, and setting languages. 

## User Interface
![](https://i.imgur.com/LOD6Oxy.jpg)

* The user interface will be comprised of a Display Screen, Input Box for commands, and a side panel of options (History, Settings, Help, Variables, User Commands) to select which will change which panel is displayed here. When history is clicked/selected from dropdown, the panel will display the history of commands that have been run through the program. Settings will display the options of SLogo, such as changing the pen color, the turtle image, the background color, and language. The default language will be set to English. Help will display all the default commands that the user can use, Variables will display the variables that the user has created and can use, and User Commands will display all of the custom commands that the user has created. 

* The command input panel will have an input form for entering commands/defining variables and commands, and a run button (not shown in image) which will run the command (send it to the parser to be parsed).

* If errors occur, such as unrecognized syntax for commands or variables, this will be shown as an Error Box in the top right of the display screen, which will notify the user of what went wrong.

* Once the user clicks the run button, the parser will receive whatever is in the textbox, and will execute the action to be called on the turtle and pen, and the front end will update with the new turtle position and the pen will have drawn the appropriate lines on the display screen. 


## Design Details
<!-- This section describes each API introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). Describe how each API supports specific features given in the assignment specification, what resources it might use, how it is intended to be used, and how it could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each class introduced with respect to the design's key goals, principles, and abstractions. This section should go into as much detail as necessary to cover all your team wants to say. -->
* There are four APIs in this program: internal and external for both the frontend and backend.
* The frontend internal API is going to control the movement of the turtle and pen tracking on the screen. It will get this information from the frontend external API which will act once the backend completes its tasks. The class for history will store the user's input so that when the history button is chosen from the dropdown menu, the user can see all of its previous inputs. The Settings button also is going to be in the dropdown menu, and will give the user options to change the image of the turtle, background color, pen color, or language that the program interprets in. TurtleView and PenView will track the movements for the turtle and pen trace and be updated once the external frontend API informs it to. They will be responsible for displaying the changes that result from the user's input/commands.
* The frontend external API are things that need to communicate with / be called by the backend in order to function. For example, knowing how and when to update the turtle and pen visualizations will be implemented in this API - once the parser has calculated the action to be made, this update method will be called to update the position of the TurtleView and the lines drawn by the pen. This API will also include a method to display errors, which will be called by the backend when an error occurs, and will be executed on the front end where the error box will be rendered.
* The backend internal API are methods/classes only the backend will need. Here is where the commands get parsed, interpreted, and perform the action specified by the command. 
    *  The backend internal API maintains  `turtle` , `pen`, `userCommands`, `variable` classes to store the status of the program. Everytime when an action is perform (e.g. moving the turtle to a certain location), the classes in the backend internal API is updated. At the same time, it would pass the update information to the frontend external API in order to update the visualizations accordingly. 
* The backend external API communicates with the frontend to know what language commands will be in as well as what variables the user wants to set and need to be stored if they want them displayed. This is also where the frontend will be passing the initial input from the user to the backend.
    * When a command/other user settings input is taken, it would be passed into the backend external API which would then call the corresponding backend internal API methods to react to the user input.


## API as Code
### Backend Internal
* CommandParser
    * parseText
    * doAction
* MathOperator
* Turtle
    * move
    * rotate
    * get position
* Pen
    * hidePen
    * showPen
* UserCommands
    * getCommand
    
### Backend External
* CommandHandler
    * setLanguage
    * inputCommand
* Variables
* SetLanguage

### Frontend Internal
* History
* Settings
    * language
    * turtle image
    * background color
    * pen color
* TurtleView 
* PenView

### Frontend External
* DisplayErrors
    * command not recognized error
    * variable not recognized error 
* Update
    * Pen
    * Turtle

### Use Cases
1. 'fd 50': Frontend passes text to backend parser. Once backend has parsed command, backend updates its Turtle object with move method. Backend's doAction will call Update in the frontend to visually display the changes.
2. 'fw 50': User has a typo in the fd command. An error is thrown by the parser and displayed by the frontend that an unrecognized command has been entered.
3. The user chooses to change the background color: The user selects the settings button on the screen and chooses a new background color. The frontend will implement the "handleOnButtonPressed" method in JavaFX to update the color of the scene. We plan on having color options as buttons so that the user cannot choose an unknown/unrecognized color.
4. The user chooses to select the settings right pane and change the language. This will update which language .properties file that the parser uses to make sense of the commands. The front end will run exactly the same as before, except the user will input commands in a different language. The help pane will also update to show commands that the user can use in the newly selected language.
5. 'TO addXandY [x y] [sum x y]': Frontend passes text to backend `CommandHandler`, which passes the command to `CommandParser` to parse the command. `CommandParser` will call `UserCommand` to store the user defined command.
6. 'addXandY' after 'TO addXandY [x y] [sum x y]' is called: Frontend passes text to backend `CommandHandler`, which passes the command to `CommandParser` to parse the command. `CommandParser` will call `UserCommand` to retrieve the stored command String, then call `MathOperator` to do the calculation (`MathOperator` access `Variables` to get the values for x and y), and return the result.  
7. 'ClearScreen': Frontend passes text to the backend's `CommandHandler`. This command will then be parsed and will call the appropriate type of Operation, which in this case is ClearScreen. The Backend will then call the Frontend to update the view of the screen. 
8.  'Fd ADD 10 100': As with other commands, the frontend will pass the text to `CommandHandler`. The parser will then parse the text section by section to determine the first command to be read. It will first recognize Fd as a command, then realize that ADD is also a command. Since the two inputs after ADD are both constants, it computes the result of ADD 10 100 and returns this to be the input of the previously read Fd command. This is handled by the Forward command, which will update the position of the turtle in the frontend.
9.  Someone clicks on "History" on the side bar: this will display the history of all the commands that have been run by the program so far. This will update each time run is clicked (if there is a command in the input form to be run). 


## Design Considerations
* We debated how errors should be handled and by whom (frontend or backend). We decided in the end what probably makes most sense is for the backend to catch the error, since it is going to be parsing the input and therefore will know if it does not contain a recognized command/variable. With this, the backend can notify the frontend of the error and the frontend will display a pop-up window informing the user of the issue. The advantage of this is that error checking is happening while we parse our commands, so we will never attempt to execute an unknown command. The downside here is that there is increased communication between the frontend and backend. However, had the frontend been checking for errors and inform itself of when an error pop-up window is needed, that would mean the frontend must have knowledge of all commands/variables and how to parse them.

* In terms of representation of the turtle, we debated where to put the class that represents the turtle. In the end we decided to have in the frontend a TurtleView class that is reponsible for the visualization of the turtle, and in the backend a Turtle class, which stores the location status of the turtle, and also is reponsible for performing actions on the turtle.

* The big dependency in our design is that the frontend is assuming the backend reads the commands correctly. With this, we also need to figure out how to know when the backend is done parsing and updating all aspects of the backend before the frontend is able to update the visualization. Because there can be multiple commands inputted at once, we still need to consider if we will update the frontend one by one or after the entire input is handled.


## Team Responsibilities
### Frontend: Chloe and Ben
* Displaying the turtle and its movements and the pen trail.
* Creating the buttons for settings and help as well as buttons that show variables, history, user commands when clicked.
* Flashing errors to user and making the textbox for inputting commands.

### Backend: Jonah and Joey 
* Parse commands in multiple languages based on basic Syntax.
* Recognize Turtle Commands, Queries, Math Operations, Boolean Operations, And Variables/User-Defined Operations. 
* Relay information to the FrontEnd so that the View can be updated. 
