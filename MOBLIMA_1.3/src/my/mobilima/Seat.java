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
public class Seat implements Serializable{
    private int rowID;
    private int colID;
    private boolean occupied;
//    private int customerID;
    
//    public Seat(){
//        rowID = -1;
//        colID = -1;
//        occupied = false;
//        customerID = -1;
//    }
    
    public Seat(int rowID, int colID){
        this.rowID = rowID;
        this.colID = colID;
        this.occupied = false;
//        this.customerID = -1;
    }
    
    public int getRowID(){
        return rowID;
    }
    
    public int getColID(){
        return colID;
    }
    
    public boolean isOccupied(){
        return occupied;
    }
    
//    public int getCustomerID(){
//        return customerID;
//    }
    
    public void assignSeat(int rowID, int colID){
        this.rowID = rowID;
        this.colID = colID;
        this.occupied = true;
//        this.customerID = customerID;
    }
    
    public void setEmptySeat(){
        this.occupied = false;
    }
}
