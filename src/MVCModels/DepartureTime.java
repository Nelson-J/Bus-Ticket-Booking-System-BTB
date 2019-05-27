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
public class DepartureTime {
    
    private Time departureTime;
    
    public DepartureTime(Time departureTime) {
        this.departureTime = departureTime;
    }
    
    //getter
    public Time getDepartureTime() {
        return this.departureTime;
    }
}
