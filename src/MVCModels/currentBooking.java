/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

import java.sql.Date;

/**
 *
 * @author user
 */
public class currentBooking {
    private int clientIdentityNumber;
    private String clientFirstName;
    private String clientLastName;
    private String tripOrigin;
    private String tripDestination;
    private int tripFare;
    private Date tripDate;

    public currentBooking() {
    }

    public currentBooking(int clientIdentityNumber, String clientFirstName, String clientLastName, String tripOrigin, String tripDestination, int tripFare, Date tripDate) {
        this.clientIdentityNumber = clientIdentityNumber;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.tripOrigin = tripOrigin;
        this.tripDestination = tripDestination;
        this.tripFare = tripFare;
        this.tripDate = tripDate;
    }

    public int getClientIdentityNumber() {
        return clientIdentityNumber;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientLastName() {
        return clientLastName;
    }

    public String getTripOrigin() {
        return tripOrigin;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public int getTripFare() {
        return tripFare;
    }

    public Date getTripDate() {
        return tripDate;
    }

    public void setClientIdentityNumber(int clientIdentityNumber) {
        this.clientIdentityNumber = clientIdentityNumber;
    }

    public void setClientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
    }

    public void setClientLastName(String clientLastName) {
        this.clientLastName = clientLastName;
    }

    public void setTripOrigin(String tripOrigin) {
        this.tripOrigin = tripOrigin;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setTripFare(int tripFare) {
        this.tripFare = tripFare;
    }

    public void setTripDate(Date tripDate) {
        this.tripDate = tripDate;
    }
}
