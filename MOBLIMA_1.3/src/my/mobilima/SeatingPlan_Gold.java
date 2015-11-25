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
public class SeatingPlan_Gold implements SeatingPlanInterface, Serializable{
    public static final int ROW_GOLD = 10;
    public static final int COL_GOLD = 10;
    private Seat[][] seatPlan;
    private int numberOfEmptySeats;
//    private boolean available;
    
    public SeatingPlan_Gold(){              //initialise SeatingPlanInterface with seat layout of [10][20]
        seatPlan = new Seat[ROW_GOLD][COL_GOLD];       //Note to self: important to do this step!!!
        for(int i=0; i<ROW_GOLD; i++)
            for(int j=0; j<COL_GOLD; j++){      //NOTE: ALL THE FOR LOOP FOR SEAT LAYOUT MUST START FROM 1!! EASY FOR CUSTOMER
                seatPlan[i][j] = new Seat(i, j);
            }
        numberOfEmptySeats = ROW_GOLD * COL_GOLD;
//        available = false;
    }
    
    @Override
    public int bookSeat(int rowID, int colID)
    //Method to book the seat in normal category
    {
        if(rowID > ROW_GOLD - 1 || colID > COL_GOLD - 1){
            System.out.println("Invalid row and column.");
        return -1;
        }
        else if(seatPlan[rowID][colID].isOccupied() == true){
            System.out.println("The seat is already booked. Please choose another one.");
            return -1;
        }
        else{
            seatPlan[rowID][colID].assignSeat(rowID, colID);
            System.out.println("The seat is booked successfully.");
            numberOfEmptySeats--;
            return 0;
        }
    }
    
    @Override
    public void showSeatLayout(){
         System.out.print("   ");
        for(int j=0; j<COL_GOLD; j++){
        if(j==COL_GOLD/2)
                System.out.print("  ");
                    System.out.print("  "+(j + 1) + " ");}
        
        System.out.println("");
                
        for (int i = 0; i < ROW_GOLD; i++) {                       //START FOR LOOP FROM 1
            for (int j = 0; j < COL_GOLD; j++) {
                if(j==0){
                    System.out.printf("%2d  ", (i+1));
                }
                if (j == COL_GOLD/2) {
                    System.out.print("  ");
                }
                 if(seatPlan[i][j].isOccupied() == true)
                    System.out.print("[X] ");
                else
                    System.out.print("[_] ");
            }
            System.out.println("");
        }
    }
    
    @Override
    public int getNumberOfEmptySeats(){
        return this.numberOfEmptySeats;
    }
    
//    @Override
//    public void setAvailable(){
//        this.available = true;
//    }
//    
//    @Override
//    public void setUnavailable(){
//        this.available = false;
//    }
    
    @Override
    public String getCinemaType(){
        return "Gold";
    }
    
    @Override
    public void flushSeatingPlan(){
         for (int i = 0; i < ROW_GOLD; i++) {                       //START FOR LOOP FROM 1
            for (int j = 0; j < COL_GOLD; j++) {
                if (seatPlan[i][j].isOccupied() == true) {
                    seatPlan[i][j].setEmptySeat();
                }
            }
        }
         numberOfEmptySeats = ROW_GOLD*COL_GOLD;
    }
}
