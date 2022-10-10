package adventure;

import java.util.Arrays;

public class Command {

    private String action;
    private String noun;

    private final String[] cmdList = { "help", "look", "inventory", 
                                        "take", "go", "quit", "toss", "eat", "wear", "read" };

    /**
     * Create a command object with default values. both instance variables are set
     * to null
     * 
     */
    public Command() throws InvalidCommandException {
        this(null, null);
    }

    /**
     * Create a command object given only an action. this.noun is set to null
     *
     * @param command The first word of the command.
     * 
     */
    public Command(String command) throws InvalidCommandException {

        if (!Arrays.asList(cmdList).contains(command)) {
            throw new InvalidCommandException(
                    "Error - The first word of the input is invalid. Please re-enter your input");
        }
        this.action = command;
        this.noun = null;
    }

    /**
     * Create a command object given both an action and a noun
     *
     * @param command The first word of the command.
     * @param what    The second word of the command.
     */
    public Command(String command, String what) throws InvalidCommandException {

        if (!Arrays.asList(cmdList).contains(command)) {
            throw new InvalidCommandException(
                    "Error - The first word of the input is invalid. Please re-enter your input");
        }

        this.action = command;
        this.noun = what;

    }

    /**
     * Return the command word (the first word) of this command. If the command was
     * not understood, the result is null.
     *
     * @return The command word.
     */
    public String getActionWord() {
        return this.action;
    }

    /**
     * @return The second word of this command. Returns null if there was no second
     *         word.
     */
    public String getNoun() {
        return this.noun;
    }

    /**
     * @return true if the command has a second word.
     */
    public boolean hasSecondWord() {
        return (noun != null);
    }
}

