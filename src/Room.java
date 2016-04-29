import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 * 
 * @author JHutter
 * @version 2015.10.07
 * @modifications
 * Added HashMap to Room constructor (Ex. 6.8)
 * setExit method
 * getExit method
 * getExitString method
 * getLongDescription method
 * 
 * @author JHutter
 * @version 2015.10.12
 * @modifications
 * Fixed NullPointerException in Room constructor
 * Added Item roomItem to Room (Ex. 6.20)
 * Added getItemDescription, used by getLongDescription (Ex. 6.20)
 * Added setItem (Ex. 6.20)
 * Changed description field to private (Ex. 6.6)
 * 
 * @author JHutter
 * @version 2015.11.17
 * @modifications
 * Changed getLongDescription to account for rooms without items
 * 
 * @author JHutter
 * @version 2015.12.04
 * @modifications
 * Removed item from Room
 * Removed setItem, getItemDescription, other item references
 * Added name field and getter
 */
public class Room 
{
    private String description;
    private String name;
    // changed implementation to Hashmap instead of list of Rooms for exits
    // per Ex. 6.8
    private HashMap<String, Room> exits;


    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * 
     * @author JHutter
     * @date 2015.10.12
     * @modifications
     * Added line to create new HashMap object to fix previous NullPointerException
     * Added line to create new Item (one item per room, added when Room is constructed)
     * 
     * @author JHutter
     * @date 2015.12.04
     * @modifications
     * Added name string to class for zuulDB processing
     */
    public Room(String description, String name) 
    {
        this.description = description;
        this.name = name;
        exits = new HashMap<String, Room>(); 
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param String direction
     * @param Room neighborRoom
     * @author JHutter
     * @date 2015.10.07
     * @modifications Implemented HashMap for exits
     */
    public void setExit(String direction, Room neighborRoom) 
    {
        exits.put(direction, neighborRoom);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
     /**
     * Name: getExit
     * Description: Return a room based on string direction
     * @param A String direction
     * @return A room in the direction given
     * @author JHutter
     * @date 2015.10.07
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room’s exits,
     * for example, "Exits: north west".
     * @return A description of the available exits.
     * @author JHutter
     * @date 2015.10.07
     */
    public String getExitString()
    {
        String output = "Exits: ";
        Set<String> keys = exits.keySet();
        for (String exit : keys)
        {
            output = output.concat(" " + exit);
        }
        return output;
    }
    
    /**
     * Name: getLongDescription
     * Description: return the long description
     * @author JHutter
     * @date 2015.10.07
     * @return String with full description of room and item in the room
     * 
     * @author JHutter
     * @date 2015.10.12
     * @modifications
     * Added getLongDescription to string to return
     * 
     * @author JHutter
     * @date 2015.11.17
     * @modifications
     * Changed method return to fix nullPtrException for rooms with no items
     * 
     * @author JHutter
     * @date 2015.12.04
     * @modifications
     * Removed item check from method
     */
    public String getLongDescription()
    {
    	return "You are " + description + "\n" + getExitString();
    }
    
    /**
     * @author JHutter
     * @date 2015.12.04
     * @return name field
     */
    public String getName() {
    	return name;
    }
    
}