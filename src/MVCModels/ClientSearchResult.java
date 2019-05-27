/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVCModels;

import java.sql.Time;

/**
 *
 * @author user
 */
public class ClientSearchResult {
    
    private String busIdentityNumber;
    private int busNumberOfAvailableSeats;
    private int clientNumberOfSeatsToBook;
    private Time tripDepartureTime;
    private int tripFare;

    public ClientSearchResult() {
    }
    
    public ClientSearchResult(String busIdentityNumber, int busNumberOfAvailableSeats, int clientNumberOfSeatsToBook, Time tripDepartureTime, int tripFare) {
        this.busIdentityNumber = busIdentityNumber;
        this.busNumberOfAvailableSeats = busNumberOfAvailableSeats;
        this.clientNumberOfSeatsToBook = clientNumberOfSeatsToBook;
        this.tripDepartureTime = tripDepartureTime;
        this.tripFare = tripFare;
    }

    public String getBusIdentityNumber() {
        return busIdentityNumber;
    }

    public void setBusIdentityNumber(String busIdentityNumber) {
        this.busIdentityNumber = busIdentityNumber;
    }

    
    public int getBusNumberOfAvailableSeats() {
        return busNumberOfAvailableSeats;
    }

    public void setBusNumberOfAvailableSeats(int busNumberOfAvailableSeats) {
        this.busNumberOfAvailableSeats = busNumberOfAvailableSeats;
    }

    
    public int getClientNumberOfSeatsToBook() {
        return clientNumberOfSeatsToBook;
    }

    public void setClientNumberOfSeatsToBook(int clientNumberOfSeatsToBook) {
        this.clientNumberOfSeatsToBook = clientNumberOfSeatsToBook;
    }

    
    public Time getTripDepartureTime() {
        return tripDepartureTime;
    }

    public void setTripDepartureTime(Time tripDepartureTime) {
        this.tripDepartureTime = tripDepartureTime;
    }

    
    public int getTripFare() {
        return tripFare;
    }

    public void setTripFare(int tripFare) {
        this.tripFare = tripFare;
    }
    
}
