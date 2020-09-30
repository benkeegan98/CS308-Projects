 - what are the project's design goals, specifically what kinds of new features did you want to make easy to add
 - describe the high-level design of your project, focusing on the purpose and interaction of the core classes
 - what assumptions or decisions were made to simplify your project's design, especially those that affected adding required features
 - describe, in detail, how to add new features to your project, especially ones you were not able to complete by the deadline
 
 
 In terms of design goal, I thought that it was necessary for each class I created to be its own class.
 I created the Ball class because this was such a key object in the game that it needed to be its own class. I thought it was
 important to have a Brick class to define the features of a Brick in the game, such as having a life counter for each brick
 object. I didn't think it was necessary to create a paddle class, because there is one paddle in the entire game that doesnt go anywhere, 
 and the rules for having the ball bounce off the paddle would be written into the Ball class. For the paddle, I just created
 a function to initialize an ImageView object paddle at the start of the game.
 
 My original idea for the bricks was to have a football like formation of Player bricks, that would be in the same formation every game
 and then I would populate each round with more filler bricks that had less lives. For this reason, I created a function "populateWithBricks" 
 for automatically populating the field with a general formation of Player bricks each round. I originally built each round as its own scene, but had
  a lot of duplicate code, and so I shrunk it down to an automatic process of updating the one game scene as each level's bricks were cleared.
 I ran out of time on the project, and so I couldn't add the different types of bricks, hence my populateWithBricks function became 
 the primary method of populating the screen with bricks - since I couldn't have the different formation of bricks
 for each round, I ended up just making each round more difficult by increasing the lives on the bricks by 1 every time I repopulated 
 the scene with bricks. 
 
 All of the ball interactions are handled inside the ball class. I thought that if I were creating a Ball object to exist in
 the game, then the Ball should know itself inherently what its velocity is, hence the velX and velY instance variables, and how to behave, such as knowing that it's velocity should change if it
 came into contact with the paddle, the edges of the screen, and the bricks. I also updated the bricks on the screen in the ball class,
 as I figured that working with the brickList would be easier in order to access each brick. If I were to go back, because a brick
 disappearing is a behavior of the brick, I would probably define this method in the Brick class, to be called on an individual brick, updating an individual
 brick rather than the list of bricks. I also had Ball extend the ImageView class, since the Ball is in effect just an image of a ball, so I thought it would be easier to add to the scenes in the
 main game, rather than converting the ball into an ImageView object. 
 
 In the main game class, when I initially had different scenes for each level, I removed the duplicate code by creating helper functions to initialize the paddle, the ball, setting the background etc.,
 but since I have only one level() scene now, I could also just write them in the scene rather than needing the helper functions. However, I am happy with the function of these helper methods.
 I also decided the use a Timeline to run a game loop, and inside this timeline I would update the position of the gameBall, and I would call the functions from the Ball class on the gameBall to check if it had collided with
 any object in its updated state each KeyFrame. I also wrote 2 if statements inside the TimeLine, one to check if the gameBricks list was empty (if so the scene would repopulate with bricks by calling the function), and 
 another to check if the gameBall went outside of the bottom bounds (if so, the ball would reset its position and velocity, and a life would be removed. If lives run out, the window would set the Game Over scene).
 
 I decided
 
 