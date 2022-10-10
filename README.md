## Author Information

- Name: Desmond Gilmour
- Email: desmondgilmour@gmail.com

## How to operate your program

### Running from the command line (without maven)

#### Command Line

1. Compile the program using mvn assembly:assembly, this will return a jar file
2. (a) to Run the program type java -cp [target jar folder] adventure.Game
   (b) You can add flags such as -a [filename] or -a without a file name to use the default file. or you can load a previous
   game using -l [saved game name]. The saved game name is the name that you entered at the beginning of the game.
2. The system will prompt for input (help, take item, look, look item ,go dir)
3. To quit the game input quit
4. You will have the option to save

ex. java -cp target/2430_A2-1.0-jar-with-dependencies.jar adventure.Game -a

#### GUI

1. Compile the program using mvn assembly:assembly, this will return a jar file
2. to Run the program type java -jar [target jar folder]
2. The GUI will appear
3. To quit the game input quit

### Instructions for using the program

#### Command Line Instructions

1. Choose whether to load a saved game or start new
2. If a new game is started you will have the option to choose your adventure
3. play the game using inputs (help, take item, look, look item ,go dir)
4. quit game and choose whether to save 

#### GUI Instructions

1. Enter your name, this will also be the used when saving 
2. Choose whether to load a saved game or start new just by entering filename
3. you are able to save this adventure but you MUST ONLY HIT ENTER
4. play the game using inputs (help, take item, look, look item ,go dir)
5. quit game 

### Known limitation and assumptions

- I was able to check the noun in the command class but I have methods in room that check that the direction entered is valid and in adventure that check that the items entered are valid
- All commands only returns general command that are not specific to the room
- I was unable to change the save to button 
