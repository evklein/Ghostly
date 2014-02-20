Ghostly
=====

Ghostly is a helicopter clone written using Java with the LibGDX framework. You play as a ghost navigating through a series of infinite walls. The goal of the game is to survive for as long as possible and get the highest score. 

Story 
=====

Henry the Ghost has a big problem. He, unlike his Ghost pals, cannot go through walls! Instead, he must carefully navigate through gaps in the walls without hitting them to ensure that he gets to where he needs to go. Help Henry get to his destination without hitting the slime walls!

Modules & Directories 
====

The reason that the game is split into various modules (Main -> Android, Desktop) is because of LibGDX's cross platform abilities. All of the game's main code, which runs cross platform between each individual system, can be found in the Main module's src directory.

Assets
====

The game's assets can be found in the Android Module's assets directory. 

UI
====

Although LibGDX does have a native UI, I've found that the documentation regarding UI skinning and parson of .json/.pack files to be quite lacking. I've found tutorials written on the subject, but most of these tutorials are written by the people who probably shouldn't be writing tutorials. Because of this native UI issue, I've decided to build my own simple UI using LibGDX sprites. 
