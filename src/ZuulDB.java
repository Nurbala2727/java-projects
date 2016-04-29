import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ZuulDB {
	private static final String DB_NAME = "z233j_JHutter";
	private static final String DB_URL = "jdbc:jtds:sqlserver://cisdbss.pcc.edu/" + DB_NAME;
	private static final String USERNAME = "z233j_JHutter";
	private static final String PASSWORD = "nr4Pe2@ro";
	private static final String SQL_GET_ROOMS = "SELECT roomName, roomDesc "
												+ "FROM ROOM "
												+ "where scenarioName = 'zuul-toddler'"
												+ "order by startRoom DESC"; // startroom will be first, only one with value 1 in table
	
	private static final String SQL_GET_EXITS = "SELECT direction, exitRoom "
												+ "FROM ROOM_EXITS "
												+ "WHERE scenarioName = 'zuul-toddler' "
												+ "and roomName = ?";
	
	private ArrayList<Room> rooms;
	

	public ZuulDB() {
		rooms = new ArrayList<Room>();
	}

	/**
	 * Create and return a connection to the database
	 * @return connection to the countries database
	 */
	private Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		return connection;
	}
	
	/**
	 * @author JHutter
	 * @date 2015.12.04
	 * @return an arraylist of rooms, filled by calls to the database
	 */
	public ArrayList<Room> readRooms(){
		try (
		        Connection connection = getConnection();
		        PreparedStatement stmt1 = connection.prepareStatement(SQL_GET_ROOMS);
		    ) {	
	    	    ResultSet rs1 = stmt1.executeQuery();  //execute the query
	    	    //loop over the result set and add each room and description to the array list
	    	    while (rs1.next()) 
	    	    {
	    	    	rooms.add(new Room(rs1.getString("roomDesc"), rs1.getString("roomName")));
	    	    }
	    	    rs1.close();
	    	    
	    	    // assign exits
	    	    PreparedStatement stmt2 = connection.prepareStatement(SQL_GET_EXITS);
	    	    for (Room room : rooms) {
		    	    stmt2.setString(1, room.getName());
		    	    ResultSet rs2 = stmt2.executeQuery();
	    	    
	    	    	//iterate over results, setExit
		    	    while (rs2.next()) {
		    	    	room.setExit(rs2.getString("direction"), findMatchingRoom(rs2.getString("exitRoom"), rooms));
		    	    }
		    	}
		        
		    }
		    catch (SQLException e) 
		    {
				System.err.println("ERROR: " + e.getMessage());
				e.printStackTrace();
		    }
		return rooms;
	}
	
	/**
	 * @author JHutter
	 * @date 2015.12.04
	 * @return the room in the array list with the matching name
	 * Only call to set roomexits from database info
	 */
	public Room findMatchingRoom(String searchStr, ArrayList<Room> roomList) {
		for (Room room : roomList) {
			if (searchStr.equals(room.getName())) {
				return room;
			}
		}
		return new Room("error, invalid room", "error, invalid room"); 	// code will not get here because of foreign key 
																		//restraint on room_exits
	}
}
