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
public class TimeSlot implements Serializable{
    private String movieName;
//    private int seatingPlanID;
    private boolean available;
    private SeatingPlanInterface cinemaSeating;

    public TimeSlot(String cinemaType)
    // Constructor initialises movieName and cinemaSeating
    {
        movieName = "";
//        seatingPlanID = -1;
        available = true;
        
        if(cinemaType.equalsIgnoreCase("Normal") == true)
            cinemaSeating = new SeatingPlan_Normal();
        else
            cinemaSeating = new SeatingPlan_Gold();
    }
    
    public void setMovieName(String movieName)
    //Sets movie name
    {
        this.movieName = movieName;
    }
    
//    public void setseatingPlanID(int seatingPlanID){
//        this.seatingPlanID = seatingPlanID;
//    }
    
    public void setOccupied()
    //Sets the timing slot as occupied by the showtime
    {
        available = false;
    }
    
    public void setAvailable()
    //Sets the timing slot as available
    {
        available = true;
        cinemaSeating.flushSeatingPlan();   //to delete all current booking of seats
    }
    
    public String getMovieName()
    //Returns movie name
    {
        return movieName;
    }
    
//    public int getSeatingPlanID(){
//        return seatingPlanID;
//    }
    
    public boolean getAvailable()
    {
        return available;
    }
    
    public boolean getOccupied()
    // 
    {
        return !available;
    }
    
    public SeatingPlanInterface getCinemaSeating(){
        return cinemaSeating;
    }
    
    public void showSeatLayout(){
        cinemaSeating.showSeatLayout();
    }
    
    public int bookSeat(int rowID, int colID){
        return cinemaSeating.bookSeat(rowID, colID);
    }
    
    public int getEmptySeats(){
        return cinemaSeating.getNumberOfEmptySeats();
    }
}
