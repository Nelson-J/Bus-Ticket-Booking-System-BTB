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
public class Trip {
    private int tripIdentityNumber;
    private String tripOrigin;
    private String tripDestination;
    private int tripFare;

    public Trip() {
    }
    
    public Trip(int tripIdentityNumber, String tripOrigin, String tripDestination, int tripFare){
        this.tripIdentityNumber = tripIdentityNumber;
        this.tripOrigin = tripOrigin;
        this.tripDestination = tripDestination;
        this.tripFare = tripFare;
    }
    
    public Trip(String tripOrigin, String tripDestination, int tripFare){
        this.tripOrigin = tripOrigin;
        this.tripDestination = tripDestination;
        this.tripFare = tripFare;
    }
    
    //setters
    public void setTripIdentityNumber(int tripIdentityNumber) {
        this.tripIdentityNumber = tripIdentityNumber;
    }
    
    public void setTripOrigin(String tripOrigin) {
        this.tripOrigin = tripOrigin;
    }
    
    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }
    
    public void setTripFare(int tripFare){
        this.tripFare = tripFare;
    }
    
    
    //getters
    
    public int  getTripIdentityNumber() {
        return this.tripIdentityNumber;
    }
    
    public String getTripOrigin() {
        return this.tripOrigin;
    }
    
    public String getTripDestination() {
        return this.tripDestination;
    }
    
    public int getTripFare() {
        return this.tripFare;
    }
}
