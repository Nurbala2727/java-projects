import java.util.ArrayList;

/**
 * Represents a game scenario including connected rooms and items
 * 
 * @author Tang
 * @version 2013.02.16
 * 
 * @author JHutter
 * @date 2015.11.10
 * @modifications
 * Moved room setup to Scenario class
 * 
 * @author JHutter
 * @date 2015.11.17
 * @modifications
 * Changed livingRoom from Room to TransporterRoom
 * Implemented getRandomRoom
 * 
 * @author JHutter
 * @date 2015.12.04
 * @modifications
 * removed items, removed transporter room, removed random room, added zuulDB to set rooms/exits
 */
public class Scenario
{
    private ArrayList<Room> rooms;
    private ZuulDB 			zuulDB;
    //private Room startRoom;

    /**
     * Constructor for objects of class Scenario
     * 
     * @author JHutter
     * @date 2015.12.04
     * @modifications
     * took out manual scenario setup, now all done when zuulDB.readRooms is called
     */
    public Scenario()
    {        
        rooms = new ArrayList<Room>();

        zuulDB = new ZuulDB();
        rooms = zuulDB.readRooms();
    }

    /**
     * @return  the start room for this scenario
     */
    public Room getStartRoom()
    {
        // return hallway
    	return rooms.get(0);
    }
}
