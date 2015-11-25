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
public class SeatingPlan_Normal implements SeatingPlanInterface, Serializable {

    public static final int ROW_NORMAL = 10;
    public static final int COL_NORMAL = 20;
    private Seat[][] seatPlan;
    private int numberOfEmptySeats;
//    private boolean available;

    public SeatingPlan_Normal() {              //initialise SeatingPlanInterface with seat layout of [10][20]
        seatPlan = new Seat[ROW_NORMAL][COL_NORMAL];       //Note to self: important to do this step!!!
        for (int i = 0; i < ROW_NORMAL; i++) {
            for (int j = 0; j < COL_NORMAL; j++) {      //NOTE: ALL THE FOR LOOP FOR SEAT LAYOUT MUST START FROM 1!! EASY FOR CUSTOMER
                seatPlan[i][j] = new Seat(i, j);
            }
        }
        numberOfEmptySeats = ROW_NORMAL * COL_NORMAL;
//        available = false;
    }

    @Override
    public int bookSeat(int rowID, int colID) 
    //Method to book the seat in normal category
    {
        if (rowID > ROW_NORMAL - 1 || colID > COL_NORMAL - 1) {
            System.out.println("Invalid row and column.");
            return -1;
        } else if (seatPlan[rowID][colID].isOccupied() == true) {
            System.out.println("The seat is already booked. Please choose another one.");
            return -1;
        } else {
            seatPlan[rowID][colID].assignSeat(rowID, colID);
            System.out.println("The seat is booked successfully.");
            numberOfEmptySeats--;
            return 0;
        }
    }

    @Override
    public void showSeatLayout() {
        System.out.print("   ");
        for (int j = 0; j < COL_NORMAL; j++) {
            if (j == ROW_NORMAL) {
                System.out.print("  ");
            }
            if(j >= COL_NORMAL/2)
                System.out.print(" " +(j+1) +" ");
            else
                System.out.print("  "+(j + 1) + " ");
        }

        System.out.println("");

        for (int i = 0; i < ROW_NORMAL; i++) {                       //START FOR LOOP FROM 1
            for (int j = 0; j < COL_NORMAL; j++) {
                if (j == 0) {
                    System.out.printf("%2d  ", (i+1));
//                    System.out.println("");
                }
//                if (j == COL_NORMAL/2) {
//                    System.out.print("  ");
//                }
//                if (seatPlan[i][j].isOccupied() == true) {
//                    if (j >= COL_NORMAL / 2) {
//                        System.out.print("[X]   ");
//                    } else {
//                        System.out.print("[X]  ");
//                    }
//                } else {
//                    if (j >= COL_NORMAL / 2) {
//                        System.out.print("[_]   ");
//                    } else {
//                        System.out.print("[_]  ");
//                    }
//                }
                if (j == COL_NORMAL/2) {
                    System.out.print("  ");
                }
                 if(seatPlan[i][j].isOccupied() == true)
                        System.out.print("[X] ");
                else
//                    System.out.print("[_] ");
                        System.out.print("[_] ");
            }
            System.out.println("");
        }
    
    }

    @Override
    public int getNumberOfEmptySeats() {
        return this.numberOfEmptySeats;
    }

//    @Override
//    public void setAvailable(){
//        this.available = true;
//    }
//    @Override
//    public void setUnavailable(){
//        this.available = false;
//    }
    @Override
    public String getCinemaType() {
        return "Normal";
    }

    @Override
    public void flushSeatingPlan() {
        for (int i = 0; i < ROW_NORMAL; i++) {                       //START FOR LOOP FROM 1
            for (int j = 0; j < COL_NORMAL; j++) {
                if (seatPlan[i][j].isOccupied() == true) {
                    seatPlan[i][j].setEmptySeat();
                }
            }
        }
        numberOfEmptySeats = ROW_NORMAL*COL_NORMAL;
    }
}
