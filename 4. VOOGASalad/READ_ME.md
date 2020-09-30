# voogasalad

Game Authoring and Player Project

## Team Members & Roles
* Amber Johnson
* Chris Xu
* Sumer Vardhan
* Ha Nguyen
* Marc Jabbour
* Xiaoyang Liu
* Ben Keegan
* Michael Castro
* Emily Otero

## Timeline Data
**Date started:** November 11

**Date finished:** December 9

**Estimated number of hours worked:** ~800 hours

## Resources Used

## Running Notes
**Files necessary to start the project (classes containing main):** src.voogasalad.Main

**Files used to test:** all files in src.testing

* Updated codes are on another branch BranchForXMLLinking. Please use the code there to ensure all xml files run.
### Errors that can be handled without crashing
#### GAME ENGINE EXCEPTIONS

##### Engine API Exceptions
LiveGameEditingRequestUnsuccessfullyProcessed=Live game editing process cannot be processed. Likely cause: EditActionType XML root specifies an unsupported action.

UnsuccessfulLiveGameEditingOnGameLevel=Live game editing failed on the game level. Likely cause: EditActionType and EditableObject mismatch or unsupported EditableObject.

UnsuccessfulLiveGameEditingOnLevelLevel=Live game editing failed on the level level. Likely cause: EditActionType and EditableObject mismatch or unsupported EditableObject.

UnsuccessfulLiveGameEditingOnPrototypeLevel=Live game editing failed on the prototype level. Likely cause: EditActionType and EditableObject mismatch or unsupported EditableObject.

##### Configurator Exceptions
NoLevelsSpecified=Game initialization failed because no levels were specified.

SpriteProductionFailedDueToStrategyTags=Sprite production failed due to bad strategy tags. Check strategy node names and values.

SpriteProductionFailedDueToStrategyParameters=Sprite production failed due to bad values passed into strategy parameters.

InvalidPrototypeInWave=Tried to specify a wave with a prototype that is invalid, does not exist, or is not available for this level.

##### Exception messages

SpriteProductionFailed=Game failed to initialize due to code developer error in Sprite interface implementation or selection due to: 

SpriteManagerProductionFailed=Game failed to initialize due to code developer error in SpriteManager interface implementation or selection due to: 

SpriteMovementInitializationFailed=Game failed to initialize sprite's movement due to: 

SpriteHealthInitializationFailed=Game failed to initialize sprite's health due to:

SpriteRotationInitializationFailed=Game failed to initialize sprite's rotation due to:

SpriteAttackInitializationFailed=Game failed to initialize sprite's attack due to:

SpriteEffectInitializationFailed=Game failed to initialize sprite's effect due to:

InvalidValueInStrategyInitialization=Invalid value passed in for constructor when initializing one of sprite's strategy due to:

ConditionsInitializationFailed=Level failed to initialize conditions due to: 

AttemptedToCreateSpriteWithInvalidPrototypeId=Cannot create sprite with non-numerical prototype-id; please check game configuration document.

ConfigurationFailedXML=Game configuration failed due to XML Error. Cause: 

UploadedWrongFileType=Wrong file type selected for upload: select XML
### Resource files required (including format of non-standard files)
* all properties files in src.resources.engine
* all properties files in src.resources.gae
* all non-xml files in src.resources.player

**Notes on running the program (i.e., required resource files, key inputs, interesting example data files, or easter eggs):** Click and drag towers to place. Click to add points when creating a path - cannot drag to create path.

**Notes on handling conflicting or ambiguous requirements:** N/A

**Known bugs:** Cannot handle issues with XML parsing without crashing (ie. trying to use an attack strategy that does not exist)

**Extra features:** Live game editing, level / game conditions, highly dynamic construction of sprites through use of strategies 

## Impressions on the Project
> to help improve it in the future
* Very tight timetable with little time to sort out design issues with a big group after the planning / design stage.