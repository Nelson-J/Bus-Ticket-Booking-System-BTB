/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

/**
 *
 * @author user
 */
public class Vehicle {
    private String vehicleNumber;
    private int vehicleNumberOfSeats;
    private int vehicleAvailableSeats;
    
    public Vehicle(){   
    }
    
    public Vehicle(String vehicleNumber, int vehicleNumberOfSeats){
        this.vehicleNumber = vehicleNumber;
        this.vehicleNumberOfSeats = vehicleNumberOfSeats;
        this.vehicleAvailableSeats = vehicleNumberOfSeats;
    }
    
    //getters
    public String getVehicleNumber(){
        return this.vehicleNumber;
    }
    
    public int getVehicleNumberOfSeats(){
        return this.vehicleNumberOfSeats;
    }
    
    public int getVehicleAvailableSeats(){
        return this.vehicleAvailableSeats;
    }
    
    //setters
    public void setVehicleNumber( String vehicleNumber){
         this.vehicleNumber = vehicleNumber;
    }
    
    public void setVehicleNumberOfSeats(int vehicleNumberOfSeats){
        this.vehicleNumberOfSeats = vehicleNumberOfSeats;
    }
    
    public void setVehicleAvailableSeats(int vehicleAvailableSeats){
        this.vehicleAvailableSeats = vehicleAvailableSeats;
    }
}
