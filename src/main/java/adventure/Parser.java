package adventure;

// imports for reading input

public class Parser{
    
    
    /**
     * 
     * @param userCommand
     * @return the user command as a command object
     * @throws InvalidCommandException
     */
    public Command parseUserCommand(String userCommand) throws InvalidCommandException{
        
        Command commandToReturn;
        try{
            commandToReturn = findWhichToReturn(userCommand);
        }catch(InvalidCommandException xcpt){
            throw xcpt;
        }
        return commandToReturn;
    }

    /**
     * 
     * @param userCommand
     * @return COmmand
     * @throws InvalidCommandException
     */
    public Command findWhichToReturn(String userCommand) throws InvalidCommandException{
        Command commandToReturn;
        
        if (userCommand.split(" ", 2).length == 1) {
            commandToReturn = new Command(userCommand.split(" ", 2)[0]);
        }else{
            commandToReturn = new Command(userCommand.split(" ", 2)[0], userCommand.split(" ", 2)[1]);
        }   
        return commandToReturn;
    }


    /**
     * returns all available commands
     * @return string
     */
    public String allCommands(){
        String stringOfAllCommands = "Available commands\n1. go dir\n2. look\n3. " 
                                    + "look item\n4. take item\n5. inventory\n6. quit";
        return stringOfAllCommands;
    }
}

