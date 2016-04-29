/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author JHutter
 * @version 2015.10.12
 * @modifications
 * Added commands "look" (Ex. 6.14) to validCommands
 * Added "listen" (Ex. 6.15) to validCommands
 * Added showAll method
 * Changed showAll to getCommandList, changed return type to String
 */


public class CommandWords
{
    // a constant array that holds all valid command words
    // look and listen commands added 2015.10.12
    private static final String[] validCommands = {
        "go", "quit", "help", "look", "listen"
    };

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
    
    /**
     * Name: getCommandList
     * Description: print all valid commandWords, Ex. 6.16
     * @author JHutter
     * @date 2015.10.12
     * @modifications
     * changed name from showAll to getCommandList
     * changed return type from void to String
     */
    public String getCommandList()
    {
        String output = "";
        for(String command : validCommands)
        {
            output = output.concat(command + " ");
        }
        output = output.concat("\n");
        return output;
    }
}
