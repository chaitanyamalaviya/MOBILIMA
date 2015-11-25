/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Tram Anh
 */
public class Cinema implements Serializable {

    //TIming is fixed for all cinemas, everyday. 10am, 1pm, 4pm, 7pm, 10pm 
    //-->  index 0 = 10am
    //           1 = 1pm
    //           2 = 4pm
    //           3 = 7pm
    //           4 = 10pm
    private static final int DAYS = 7;
    private static final int TIME = 5;
    private TimeSlot[][] timing;
    private int today;
    private String cinemaType;

    public Cinema(String cinemaType) {
        this.cinemaType = cinemaType;
        timing = new TimeSlot[7][5];
        for (int i = 0; i < DAYS; i++) {
            for (int j = 0; j < TIME; j++) {
                timing[i][j] = new TimeSlot(cinemaType);          //default: empty slots = false;
            }
        }
//        setToday();
        Calendar cal = Calendar.getInstance();
        today = cal.get(Calendar.DAY_OF_WEEK);     //get Sunday = 1, Monday = 2, ..., Saturday = 7
        today = today - 1;
    }

    public String getCinemaType() {
        return cinemaType;
    }

    public void cinemaBooking(int daysFromToday, int time, String movieName) {
        //For eg, if today = 4, daysFromToday = 3 ==> day = 0
        // ==> timing[0][..] = true
        
        //THis function will book for the given day and timeslot for a paticular cinema
        int day = (today + daysFromToday) % 7;
        if (!timing[day][time].getAvailable()) //check if the timeslot has been booked
        {
            System.out.println("The timeslot is already booked. Please choose another timeslot.");
        } else {
            timing[day][time].setOccupied();
            timing[day][time].setMovieName(movieName);
//        timing[day][time].setseatingPlanID(seatingPlanID);
        }
    }

    public void checkEmptySlot() {
        //This function will check for empty slot from this cinema
        System.out.println("Note: Day 0 is today. Day 1 is tomorrow, etc..");
        int day;
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        for (int i = 0; i < DAYS; i++) {
            System.out.print("Day " + i + ": (" + df.format(cal.getTime()) + ") - ");
            day = (today + i) % 7;
            for (int j = 0; j < TIME; j++) {              //j from 0 to 4 <-- 10am to 10pm 
                if (timing[day][j].getAvailable() == true) {
                    switch (j) {
                        case 0:
                            System.out.print("1000 ");
                            break;
                        case 1:
                            System.out.print("1300 ");
                            break;
                        case 2:
                            System.out.print("1600 ");
                            break;
                        case 3:
                            System.out.print("1900 ");
                            break;
                        case 4:
                            System.out.print("2200 ");
                            break;
                    }
                } else {
                    switch (j) {
                        case 0:
                            System.out.print("     ");
                            break;
                        case 1:
                            System.out.print("     ");
                            break;
                        case 2:
                            System.out.print("     ");
                            break;
                        case 3:
                            System.out.print("     ");
                            break;
                        case 4:
                            System.out.print("     ");
                            break;
                    }
                }
            }
            cal.add(Calendar.DATE, 1);
            System.out.println("");
        }
    }

    public void setTimeSlotAvailable(String movieName) {
        for (int day = 0; day < 7; day++) {
            for (int time = 0; time < 5; time++) {
                if (timing[day][time].getMovieName().equalsIgnoreCase(movieName)) {
                    timing[day][time].setAvailable();
                }
            }
        }
    }

    public void setTimeSlotAvailable(int daysFromToday, int time) {
        int day = (today + daysFromToday) % 7;
//        time = convertTime(time);
        timing[day][time].setAvailable();
        timing[day][time].setMovieName("");

//        cinemaSeating.remove(timing[day][time].getSeatingPlanID());  //remove the seating plan from this time slot

    }

    public String getMovieName(int daysFromToday, int time) {
        int day = (today + daysFromToday) % 7;
        return timing[day][time].getMovieName();
    }

    public void setMovieName(int daysFromToday, int time, String movieName) {
        int day = (today + daysFromToday) % 7;
        timing[day][time].setMovieName(movieName);
    }

    public boolean showOccupiedSlot(String movieName) {
        int day;
        boolean lineFlag; //flag if there is occupied slot for the day --> Enter to a new line
        boolean cinemaFlag = false;  //flag if there is occupied slot for the cinema at all
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        for (int i = 0; i < DAYS; i++) {
            lineFlag = false;
            day = (today + i) % 7;
            for (int j = 0; j < TIME; j++) {
                //j from 0 to 4 <-- 10am to 10pm 
                if (timing[day][j].getMovieName().equalsIgnoreCase(movieName) == true) {
                    cinemaFlag = true;
                    lineFlag = true;
                    System.out.print("  Day " + i + ": (" + df.format(cal.getTime()) + ") - ");
                    switch (j) {
                        case 0:
                            System.out.print("10am ");
                            break;
                        case 1:
                            System.out.print("1pm ");
                            break;
                        case 2:
                            System.out.print("4pm ");
                            break;
                        case 3:
                            System.out.print("7pm ");
                            break;
                        case 4:
                            System.out.print("10pm");
                            break;
                    }
                }
            }
            cal.add(Calendar.DATE, 1);
            if (lineFlag == true) {
                System.out.println("");
            }
        }
        return cinemaFlag;
    }

    public boolean showOccupiedSlot() {
        int day;
        boolean lineFlag; //flag if there is occupied slot for the day --> Enter to a new line
        boolean cinemaFlag = false;  //flag if there is showtime for this cinema at all
        Calendar cal = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd MMM yyyy");
        for (int i = 0; i < DAYS; i++) {
            lineFlag = false;
            day = (today + i) % 7;
            for (int j = 0; j < TIME; j++) {              //j from 0 to 4 <-- 10am to 10pm 
                if (timing[day][j].getAvailable() == false) {
                    lineFlag = true;
                    cinemaFlag = true;
                    System.out.print("Day " + i + ": (" + df.format(cal.getTime()) + ") - ");
                    switch (j) {
                        case 0:
                            System.out.print("10am ");
                            break;
                        case 1:
                            System.out.print("1pm ");
                            break;
                        case 2:
                            System.out.print("4pm ");
                            break;
                        case 3:
                            System.out.print("7pm ");
                            break;
                        case 4:
                            System.out.print("10pm");
                            break;
                    }
                }
            }
            cal.add(Calendar.DATE, 1);
            if (lineFlag == true) {
                System.out.println("");
            }
        }
        return cinemaFlag;
    }

    public void showSeatLayout(int daysFromToday, int time) {
        int day = (today + daysFromToday) % 7;
//        time = convertTime(time);

        timing[day][time].showSeatLayout();
    }

    public int bookSeat(int rowID, int colID, int daysFromToday, int time) {
        int day = (today + daysFromToday) % 7;
//        time = convertTime(time);

        return timing[day][time].bookSeat(rowID, colID);

    }
    
    public boolean checkOccupiedSlot(int daysFromToday, int time){
        int day = (today + daysFromToday) % 7;
        return timing[day][time].getOccupied();
    }

    public int getEmptySeats(int daysFromToday, int time){
         int day = (today + daysFromToday) % 7;
         return timing[day][time].getEmptySeats();
    }
}
