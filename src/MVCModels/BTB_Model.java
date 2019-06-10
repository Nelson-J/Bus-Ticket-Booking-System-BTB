/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class BTB_Model {
    
    //info required to connect to database
    String url = "jdbc:mysql://localhost:3306/ticket_booking_system";
    String user = "root";
    String password = "";
    
    //This will hold database return values from the appropriate requested tables
    private ArrayList rowValues = new ArrayList(); 
    private ArrayList rowValues2 = new ArrayList(); 
    
    public Connection connect() throws SQLException{
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,user,password);
            System.out.println("Connected to database successfully!!!");
            }catch ( SQLException e) {
                System.err.println("Error: " +  e.getMessage());
                }
        return con;
    }
    
    //  ================= CREATION OPERATIONS ===============================
   //1. Vehicle
    public String insertVehicle(Vehicle vehicle){
        int affectedRows = 0;
        
        String SQL = "INSERT INTO tblvehicle"
                    +"(VEHICLE_NUMBER, NUMBER_OF_SEATS, AVAILABLE_SEATS)"
                    +"VALUES (?,?,?)";
        String result;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setString(1, vehicle.getVehicleNumber());
            pstmt.setInt(2, vehicle.getVehicleNumberOfSeats());
            pstmt.setInt(3, vehicle.getVehicleAvailableSeats());
            
            affectedRows = pstmt.executeUpdate();
            
             //check the affected rows
            if(affectedRows>0){
               result = "Success";
               return result;
            }else{
                return (result = "Failed");
            }
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            return result;
        }
    } 
        
    //2. Trip
     // Inserts a new trip and returns the id of the last inserted item
       public Integer insertTrip(Trip trip){
        int affectedRows = 0;
        
        String SQL2 = "INSERT INTO tbltrip (TRIP_ORIGIN, TRIP_DESTINATION, TRIP_FARE) "
                        + "SELECT * FROM (SELECT ?, ?, ?) AS tmp "
                        + "WHERE NOT EXISTS ("
                        + "SELECT TRIP_ORIGIN, TRIP_DESTINATION, TRIP_FARE "
                        + "FROM tbltrip "
                        + "WHERE TRIP_ORIGIN = ? "
                        + "AND TRIP_DESTINATION = ?)";

        
        String result;
        Integer id=0;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL2, Statement.RETURN_GENERATED_KEYS);//second attribute required
                                                                                             //to get last inserted item's id
            
            pstmt.setString(1, trip.getTripOrigin());
            pstmt.setString(2, trip.getTripDestination());
            pstmt.setInt(3, trip.getTripFare());
            
            pstmt.setString(4, trip.getTripOrigin());
            pstmt.setString(5, trip.getTripDestination());
         //   pstmt.setInt(6, trip.getTripFare());
            
            affectedRows = pstmt.executeUpdate();
            
            //this holds the id of the last inserted element
            ResultSet rs = pstmt.getGeneratedKeys();
            
             //check the affected rows : if trip was added successfully
            if(affectedRows>0){
                
               if(rs.next()){
                   //get the id of the last inserted element
                   id = rs.getInt(1);
                }
               System.out.println(id);
            }
            
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            System.err.println(result);
        }
        return id;
    }
       
    //3. Time
         public String insertTime(DepartureTime departureTime){
        int affectedRows = 0;
        
        String SQL = "INSERT IGNORE INTO tbltime"   //inserts value only if it is not found in the table tbltime
                    +"(DEPARTURETIME)"              //i.e does not even execute the SQL statement
                    +"VALUES (?)";
        String result;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            //pstmt.setInt(1, trip.getTripIdentityNumber());
            pstmt.setTime(1, departureTime.getDepartureTime());
            
            affectedRows = pstmt.executeUpdate();
            
             //check the affected rows
            if(affectedRows>0){
               result = "Add time success";
               System.out.println(result);
               return result;
            }else{
                result = "Add time Failed";
                System.out.println(result);
                return result;
            }            
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            return result;
        }
        
    }
     
    //4. 
      //method that does the actual linking of a trip to a departure time
     public String insertIntoDepartsAt(int tripIdentityNumber, Time departureTime){
        int affectedRows = 0;
        
        String SQL = "INSERT INTO lnkdeparts_at"   //inserts value only if it is not found in the table tbltime
                    +"( TRIP_IDENTITY_NUMBER, DEPARTURETIME )"              //i.e does not even execute the SQL statement
                    +"VALUES (?,?)";
        String result;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setInt(1, tripIdentityNumber);
            pstmt.setTime(2, departureTime);
            
            affectedRows = pstmt.executeUpdate();
            
             //check the affected rows
            if(affectedRows>0){
               result = "Create link success";
               System.out.println(result);
               return result;
            }else{
                result = "Create link Failed";
                System.out.println(result);
                return result;
            }            
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            return result;
        } 
    }
     
    //5. Assign a partitular trip to a vehicle 
     //method that links a vehicle to a trip
     public String InsertLinkVehicleAndTrip(int tripIdentityNumber, String vehicleNumber){
        int affectedRows = 0;
        
        String SQL = "INSERT IGNORE INTO lnkinvolves"   //avoid duplicate entries
                    +"(TRIP_IDENTITY_NUMBER, VEHICLE_NUMBER )"
                    +"VALUES (?,?)";
        String result;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setInt(1, tripIdentityNumber);
            pstmt.setString(2, vehicleNumber);
            
            affectedRows = pstmt.executeUpdate();
            
             //check the affected rows
            if(affectedRows>0){
               result = "Success";
               System.out.println(result);
               return result;
            }else{
                result = "Failed";
                System.out.println(result);
                return result;
            }            
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            return result;
        } 
    }
    
    //6. Client
    // Insert a new client and return that client's id to be used to link client with other tables
    //Insert Client iff they do not exist yet. If they do, return id of the client.
       public String insertClient(Client client){
        int affectedRows = 0;
        
        //We should not insert two clients with the same first names and last names, even if they have same IDs
        String SQL = "INSERT INTO tblclient (CLIENT_ID_CARD_NO, CLIENT_FIRST_NAME, CLIENT_LAST_NAME) "
                    +"SELECT * FROM (SELECT ?, ?, ?) AS tmp " 
                    +"WHERE NOT EXISTS ("
                    +"SELECT CLIENT_FIRST_NAME, CLIENT_LAST_NAME " 
                    +"FROM tblclient "
                    +"WHERE CLIENT_FIRST_NAME = ? "
                    +"AND CLIENT_LAST_NAME = ?)";
        
        String result = "";
        Integer id=0;
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setInt(1, client.getClientIDCardNumber());
            pstmt.setString(2, client.getClientFirstName());
            pstmt.setString(3, client.getClientLastName());
            pstmt.setString(4, client.getClientFirstName());
            pstmt.setString(5, client.getClientLastName());
            
            affectedRows = pstmt.executeUpdate();
            
             //check the affected rows : client added succesfully
            if(affectedRows>0){
                
               System.out.println("Client Added");
               result = "Client Added";
            }else
                result = "Client already exists...";
            
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            System.err.println(result);
        }
        return result;
    }
       
    //9. Client books a seat
    //The heart of the application
       
    public String clientBooksTrip(int clientIDCardNumber, int tripIdentityNumber, java.sql.Date tripDate, int numberOfSeatsBooked){
        
        String SQL = "INSERT INTO lnkbooks (CLIENT_ID_CARD_NO, TRIP_IDENTITY_NUMBER, DATE_TRIP_BOOKED, TRIPDATE) "
                    +"VALUES ( ?, ?, CURRENT_DATE , ?)";
        //Get the vehicle number involved and and reduce the available number of seats based on the user entered
        //no-of-seats-to-book
        String SQL2 = "UPDATE tblvehicle " 
                        + "SET tblvehicle.AVAILABLE_SEATS = tblvehicle.AVAILABLE_SEATS - ? " 
                        + "WHERE tblvehicle.VEHICLE_NUMBER = (" 
                        +   "SELECT VEHICLE_NUMBER "
                        +       "FROM lnkinvolves WHERE lnkinvolves.TRIP_IDENTITY_NUMBER = ?)";
        int affectedRows = 0;
        int affectedRows2 = 0;
        
        String result = "";
        
         try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            PreparedStatement pstmt1 = con.prepareStatement(SQL2);
            
            pstmt.setInt(1, clientIDCardNumber);
            pstmt.setInt(2, tripIdentityNumber);
            pstmt.setDate(3, tripDate);
            affectedRows = pstmt.executeUpdate();
            
            pstmt1.setInt(1, numberOfSeatsBooked);
            pstmt1.setInt(2, tripIdentityNumber);
            affectedRows2 = pstmt1.executeUpdate();

             //check the affected rows : client added succesfully
            if(affectedRows > 0 && affectedRows2 > 0){
               System.out.println("Trip Booked with success!!!");
               result = "Success!";
            }else{
                System.out.println("Couldn't book trip!!!");
                result = "Failed";
            }
        }catch(SQLException e){
            result = "Error : " + e.getMessage();
            System.err.println(result);
        }
         return result;
    }
    

    //  ================= READ OPERATIONS ===============================
     
    //1. Get Vehicles
    //select all vehicles, to be displayed for choosing while adding a trip
    public String[] getAllVehicles(){
        
        String SQL = "SELECT VEHICLE_NUMBER "
                     +"FROM tblvehicle";
        
        String[] vehicles = null; //hold vehicle numbers that are going to be returned.
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            ResultSet rs = pstmt.executeQuery();
            
            //insert the vehicle numbers in a list of strings
           rowValues.removeAll(rowValues);
            while(rs.next()){
               rowValues.add(rs.getString("VEHICLE_NUMBER"));
            }
            
            //put the list items back into an array
            vehicles = (String[]) rowValues.toArray(new String[rowValues.size()]);

        }catch(SQLException e){
            System.err.println( e.getClass().getName()+" : " + e.getMessage());
        }
        return vehicles;
    }
    
    //2. Get User search results: for availables trips, vehicles that are  involved in the trip and trip fare
    public ArrayList <ClientSearchResult> userSearchResults(String tripOrigin, String tripDestination){
        //this method poses a problem, the Array list rowvalues will only accept variables of one data type;
        //a cliencsearc class is thus created that will hold the data directly from the database, 
        //an the arraylist will hold these objects of type clientSearchResult, to be transmitted to the controller

        ArrayList <ClientSearchResult> searchResultRowValues = new ArrayList<>();
        
        String SQL = "SELECT lnkinvolves.VEHICLE_NUMBER, "
                    +"tbltrip.TRIP_FARE, tblvehicle.AVAILABLE_SEATS, lnkdeparts_at.DEPARTURETIME "
                    +"FROM lnkdeparts_at, lnkinvolves, tbltrip, tblvehicle "
                    +"WHERE tbltrip.TRIP_IDENTITY_NUMBER = lnkinvolves.TRIP_IDENTITY_NUMBER "
                    +"AND tblvehicle.VEHICLE_NUMBER = lnkinvolves.VEHICLE_NUMBER "
                    +"AND tbltrip.TRIP_IDENTITY_NUMBER = lnkdeparts_at.TRIP_IDENTITY_NUMBER "
                    +"AND tbltrip.TRIP_ORIGIN = ? AND tbltrip.TRIP_DESTINATION = ?";
        
        String[] searchResults = null;
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            //set the values that will be used to execute the query
            pstmt.setString(1, tripOrigin);
            pstmt.setString(2, tripDestination);
            
            ResultSet rs = pstmt.executeQuery();
            
            //insert the requested info in an list of strings
            while(rs.next()){
                //create new currentbooking instance variable in which database values are goint to be inserted
                //This ensure that values that are inserted into Arraylist are different
                ClientSearchResult searchResult = new ClientSearchResult();
                
                searchResult.setBusIdentityNumber(rs.getString("VEHICLE_NUMBER"));
                searchResult.setTripFare(rs.getInt("TRIP_FARE"));
                searchResult.setBusNumberOfAvailableSeats(rs.getInt("AVAILABLE_SEATS"));
                searchResult.setTripDepartureTime(rs.getTime("DEPARTURETIME"));
                searchResultRowValues.add(searchResult);
               System.out.println("\nNext\n");
            }
            
            for (int i = 0; i < searchResultRowValues.size(); i++){
                System.out.println("Values : "+searchResultRowValues.get(i).getBusIdentityNumber());
                System.out.println("Values : "+searchResultRowValues.get(i).getTripFare());
                System.out.println("Values : "+searchResultRowValues.get(i).getBusNumberOfAvailableSeats());
                System.out.println("Values : "+searchResultRowValues.get(i).getTripDepartureTime());
            }

        }catch(SQLException e){
            System.err.println( e.getClass().getName()+" : " + e.getMessage());
        }
        return searchResultRowValues;
    }
    
    
    //4. Get Trip origins and destinations that are going to be inserted as options in client search jcomboboxes
    
    public ArrayList getTripOriginAndTripDestination(){
        
        String SQL = "SELECT DISTINCT TRIP_ORIGIN "
                     +"FROM tbltrip "
                     +"ORDER BY TRIP_ORIGIN ASC";
        
        String SQL2 = "SELECT DISTINCT TRIP_DESTINATION "
                     +"FROM tbltrip "
                     +"ORDER BY TRIP_DESTINATION ASC";
        
        
        String[] tripOrigin = null; //holds the values that are going to be returned.
        String[] tripDestination = null; //holds the values that are going to be returned.
        
        ArrayList merger = new ArrayList();
        ArrayList rowValues2 = new ArrayList();
                
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            PreparedStatement pstmt2 = con.prepareStatement(SQL2);
            
            ResultSet rs = pstmt.executeQuery();
            ResultSet rs2 = pstmt2.executeQuery();
            
            int count = 0; //used to  get return and insert in array of results
            
            while(rs.next()){
               rowValues.add(rs.getString("TRIP_ORIGIN"));
            }
            
            while(rs2.next()){
               rowValues2.add(rs2.getString("TRIP_DESTINATION"));
            }
            
            //put the list items back into an array
            tripOrigin = (String[]) rowValues.toArray(new String[rowValues.size()]);
            tripDestination = (String[]) rowValues2.toArray(new String[rowValues2.size()]);
            
            merger.add(tripOrigin);
            merger.add(tripDestination);
            
            
        }catch(SQLException e){
            System.err.println( e.getClass().getName()+" : " + e.getMessage());
        }
        return merger;  
    }
    
    //5.Get trip ID
    
    public int getTripID(String tripOrigin, String tripDestination){
        
        String SQL = "SELECT TRIP_IDENTITY_NUMBER "
                    +"FROM tbltrip "
                    +"WHERE TRIP_ORIGIN = ? AND TRIP_DESTINATION = ?";
        
        int tripID = 0; //to hold returned value from the database
        
         try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            pstmt.setString(1, tripOrigin);
            pstmt.setString(2, tripDestination);
            
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
               tripID = rs.getInt("TRIP_IDENTITY_NUMBER");
            }
            
            if(tripID <= 0)
                System.out.println("Id : " + tripID);
            
        }catch(SQLException e){
            System.err.println( e.getClass().getName()+" : " + e.getMessage());
        }
     return tripID;
    }
    
    //Get all current bookings
    public ArrayList<currentBooking> getCurrentBookings(){
        //similar to userSearchResults
        //this variable will hold a list of all bookings taken from the database, to be transmitted to the controller
        ArrayList <currentBooking> currentBookingsList = new ArrayList<>();
        
        
        String SQL = "SELECT tblclient.CLIENT_ID_CARD_NO, tblclient.CLIENT_FIRST_NAME, tblclient.CLIENT_LAST_NAME, "
                    +"tbltrip.TRIP_ORIGIN, tbltrip.TRIP_DESTINATION, tbltrip.TRIP_FARE, lnkbooks.TRIPDATE " 
                    +"FROM tblclient, tbltrip, lnkbooks "
                    +"WHERE lnkbooks.CLIENT_ID_CARD_NO = tblclient.CLIENT_ID_CARD_NO "
                    +"AND lnkbooks.TRIP_IDENTITY_NUMBER = tbltrip.TRIP_IDENTITY_NUMBER";
        
        try{
            Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(SQL);
            
            ResultSet rs = pstmt.executeQuery();
            
            //insert the requested info in an list of currentBookings
            while(rs.next()){
                //create new currentbooking instance variable in which database values are goint to be inserted
                //This ensure that values that are inserted into Arraylist are different (It took me dayS to find that out)
                currentBooking currentBookings = new currentBooking();
                
                currentBookings.setClientIdentityNumber(rs.getInt("CLIENT_ID_CARD_NO"));
                currentBookings.setClientFirstName(rs.getString("CLIENT_FIRST_NAME"));
                currentBookings.setClientLastName(rs.getString("CLIENT_LAST_NAME"));
                currentBookings.setTripOrigin(rs.getString("TRIP_ORIGIN"));
                currentBookings.setTripDestination(rs.getString("TRIP_DESTINATION"));
                currentBookings.setTripFare(rs.getInt("TRIP_FARE"));
                currentBookings.setTripDate(rs.getDate("TRIPDATE"));
                
                //add the booking into the list
                currentBookingsList.add(currentBookings);
                System.out.println("Added"+ rs.getString("TRIP_ORIGIN"));
            }
            for(int t= 0; t<2;t++){
                System.out.println(currentBookingsList.get(t).getClientLastName());
            }
            
            for (currentBooking booking : currentBookingsList ){
               // System.out.println("Number : " + i);
                System.out.println("Values : "+ booking.getClientIdentityNumber());
                System.out.println("Values : "+ booking.getClientFirstName());
                System.out.println("Values : "+ booking.getClientLastName());
                System.out.println("Values : "+ booking.getTripOrigin());
                System.out.println("Values : "+ booking.getTripDestination());
                System.out.println("Values : "+ booking.getTripFare());
                System.out.println("Values : "+ booking.getTripDate());
            }

        }catch(SQLException e){
            System.err.println( e.getClass().getName()+" : " + e.getMessage());
        }
    return currentBookingsList;
    }
}
