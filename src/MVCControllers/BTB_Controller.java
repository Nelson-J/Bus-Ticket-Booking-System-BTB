/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCControllers;

import MVCModels.*;
import MVCViews.*;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author user
 */
public class BTB_Controller {
    
    //Getting a model to be used in the controller
    BTB_Model model = new BTB_Model();
    String result = "";
    
    public  String addVehicle(String vehicleNumber, int vehicleNumberOfSeats){
        Vehicle vehicle = new Vehicle(vehicleNumber,vehicleNumberOfSeats);
        
        
        result = model.insertVehicle(vehicle);
        
        return result;
    }
    
    //Add a trip THEN do all necessary linking
    public  String addTrip(String tripOrigin, String tripDestination, int tripFare, Time departTime, String vehicleNumber){
        
        //create the trip to be inserted
        Trip trip = new Trip(tripOrigin, tripDestination, tripFare);
        
        //Inserts the trip
        //gets the id of last inserted element
        int returnedValue = model.insertTrip(trip);
        
        //if the trip was inserted successfully i.e it has an id of last inserted elt
        if(returnedValue != 0 ){
            
            //insert departure time to table departure time
            addDepartureTime(departTime);
                
            //then link trip to departure time
            model.insertIntoDepartsAt(returnedValue, departTime);
            
            //link a vehicle to a trip
            if ((model.InsertLinkVehicleAndTrip(returnedValue, vehicleNumber)).equals("False")){
                result = "False";    
                return result;
            }
            
        result = "Success";
        }    
        else
            result = "Failed";
        return result;
    }
    
    //Add a departure time
    public int addDepartureTime(Time departTime) {
        DepartureTime departureTime = new DepartureTime(departTime);
        
        result = model.insertTime(departureTime);
         
        if (result.equals("success"))
              return 1;  
        else
            return 0;
    }
    
    //Add a client
    public String addClient(int clientIDCardNumber, String clientFirstName, String clientLastName){
        Client client = new Client(clientIDCardNumber, clientFirstName, clientLastName);
        
        result = model.insertClient(client);
        return result;
    }
    
    //Book trip for client
    //for that we need: client ID number, trip ID number, and date of trip
    public String bookTrip(int clientIDCardNumber, String clientFirstName, String clientLastName, 
                            String tripOrigin, String tripDestination, String tripDate, int numberOfSeatsBooked) throws ParseException{
        int clientId = 0, tripId = 0;
        String result = "";
        
        //First ensure that client has been created successfully
     //   clientId = 
     result = addClient(clientIDCardNumber, clientFirstName, clientLastName);
        
        //if  client was addded successfully
  if(result.equals("Client Added") ){
           //Next get the trip ID from the trip source and trip destination
           tripId = model.getTripID(tripOrigin, tripDestination);
           
           if(tripId > 0){
               java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(tripDate);
               java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
              //Insert these two together with the trip Date into the database
              result = model.clientBooksTrip(clientIDCardNumber, tripId, sqlDate, numberOfSeatsBooked);
              System.out.println(Integer.toString(tripId));
           }
        }
    else
            result = "Failed";

        return result;               
    }
    
    // ============== READING OPERATIONS ======================================
    
    //Get vehicles from which admin will choose which one is involved in which trip
    public String[] getAllVehicles(){
        return model.getAllVehicles();
    }
    
    //Get Trip Origins from which client is going to choose, in order to do a search
    public String[] getAllTripOrigins(){
        
        String[] tripOrigins;
        
        //model returns List of Array of Strings
        //get the first object (trip Origin) Array
        tripOrigins = (String[]) model.getTripOriginAndTripDestination().get(0);
        
        return tripOrigins;
    }
    
    //Get Trip Destinations from which client is going to choose, in order to do a search
    public String[] getAllTripDestinations(){
        
        String[] tripDestinations;
        
        //model returns List of Array of Strings
        //get the second object (trip Destination) Array
        tripDestinations = (String[]) model.getTripOriginAndTripDestination().get(1);
        
        return tripDestinations;
    }
    
    //Get results of client search based on Trip Origin and Trip destination
    public ArrayList <ClientSearchResult> getUserSearchResults(String tripOrigin, String tripDestination){
        
        return model.userSearchResults(tripOrigin, tripDestination);
    }
    
    public ArrayList <currentBooking> getCurrentBookings(){
        return model.getCurrentBookings();
    }
    
}
