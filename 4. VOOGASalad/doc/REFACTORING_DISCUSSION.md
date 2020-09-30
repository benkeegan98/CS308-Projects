# Refactoring Discussion

* Marc Jabbour (mj196)
* Amber Johnson (ajj18) 
* Xiaoyang Liu (xl149)
* Sumer Vardhan (sv110)
* Michael Castro (mc546)
* Ben Keegan (bk142)
* Ha Nguyen (hn47)
* Emily Otero (eo56)
* Christopher Xu ()



1. List as many design issues as you can in the method associated with the issue (using line numbers to identify the exact code) from large things like (potential) duplicated code or if statements that should be generalized through polymorphism, data structures, or resource files down to medium sized things like poor error handling or long lambdas methods to small things like consistent coding conventions or ignored assignment design requirements (like using Resources instead of magic values).
2. Organize this list of issues based on things that could be fixed together based on a different design choice or using similar refactorings and prioritize these groups based on which would provide the most improvement to the overall code (not just this method).
3. Describe specific overall design changes or refactorings to fix the five most important issues you identified. Note how each change will improve the overall code design, what external changes others will have to make, and what, if any, alternatives you considered. Use SOLID Design Principles to justify the goals of your refactoring efforts.

## Game Authoring Environment
1. Refactored package organization to standardize file type locations across API's (e.g. GAE resources were moved from GAE package to resources package)
2. Package names standardized to match formatting of Game Player and Game Engine
3. Class names standardized to match formatting of Game Player and Game Engine
4. MapConfig class refactored to include borderpane to make the overall gui layout more organized.

## Game Player
1. Removed unused imports and created constants for magic numbers - if this leaves us with a lot of constants, since they are all related UI positioning, we will extract them into a stylesheet.


