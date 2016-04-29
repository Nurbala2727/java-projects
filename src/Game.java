/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  
 *  The setting for the game is the home of a toddler, and the object of 
 *  the game will (after implementation) be to find the toddler in one 
 *  of the rooms before pink glittery havok is wrought.
 *  
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author JHutter
 * @version 2015.09.30
 * @modifications
 * Updated rooms to kitchen, hallway, bathroom, patio, bedroomMaster
 * bedroomToddler, and livingRoom. Changed help message to match new rooms.
 * 
 * @author JHutter
 * @version 2015.10.07
 * @modifications
 * createRooms method
 * printLocationInfo method
 * 
 * 
 * @author JHutter
 * @version 2015.10.12
 * @modifications
 * Updated printLocationInfo to solve double printing issue
 * Added look method (Ex. 6.14)
 * Added listen method (Ex. 6.15)
 * Updated createRoom to setItems (Ex. 6.20)
 * 
 * @author JHutter
 * @version 2015.11.10
 * @modifications
 * Added Scenario to Game constructor
 * Moved room setup to Scenario class
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Scenario scenario;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
    	scenario = new Scenario();
        currentRoom = scenario.getStartRoom();
        parser = new Parser();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        // replaced with printLocationInfo
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     * 
     * @author JHutter
     * @date 2015.10.12
     * @modifications
     * Added "look" (Ex. 6.14) and "listen" (Ex. 6.15) commands
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look"))
        {
            look();
        }
        else if (commandWord.equals("listen"))
        {
            listen();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     * @author JHutter
     * @date 2015.09.30
     * @modifications Updated help script to match new premise of the game.
     * 
     * @author JHutter
     * @date 2015.10.12
     * @modifications Added line to call parser.showCommands (Ex. 6.16)
     * 
     */
    private void printHelp() 
    {
        System.out.println("You are in a toddler's home.");
        System.out.println("You hear the sound of ominous giggling ");
        System.out.println("coming from one of the other rooms.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            //replaced with printLocationInfo
            printLocationInfo();
            
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * Name: printLocationInfo
     * Description: prints the room description and exits
     * @author JHutter
     * @date 2015.10.07
     */
    private void printLocationInfo()
    {
        // moved from original place in printWelcome
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Name: look
     * Description: print room description and item description (Ex. 6.14)
     * @author JHutter
     * @date 2015.10.12
     */
    private void look()
    {
        printLocationInfo();
    }
    
    /**
     * Name: listen
     * Description: print result of listening for toddler (Ex. 6.15)
     * Later in a hypothetical version, this method will have indications of 
     * distance from toddler (ie, next room, farther away) and indirect countdown 
     * (ie, "The giggling has gone silent. The toddler is definitely getting
     * into trouble now. Find her soon!") based on the location of
     * the toddler compared to the currentRoom and turn counter
     * @author JHutter
     * @date 2015.10.12
     */
    private void listen()
    {
        //This will refer to a Toddler method and a counter field in a hypotehtical later version
        System.out.println("You hear the ominous giggling of a toddler.");  
        System.out.println("Find her before she wreaks havok.");
    }
}
