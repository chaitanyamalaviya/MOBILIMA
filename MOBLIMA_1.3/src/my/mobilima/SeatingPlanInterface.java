/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.Serializable;

/**
 *
 * @author Tram Anh
 */
public interface SeatingPlanInterface{
    
    public abstract int bookSeat(int rowID, int colID);
    public abstract void showSeatLayout();
    public abstract int getNumberOfEmptySeats();
//    public abstract void setAvailable();
//    public abstract void setUnavailable();
    public abstract String getCinemaType();
    public abstract void flushSeatingPlan();
   
    
    
}
