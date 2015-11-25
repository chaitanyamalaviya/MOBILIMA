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
public class Movie implements Serializable{
    public static final int NUMBER_OF_CINEPLEXES = 3;
    private String name;
    private String type;     //Digital/3D
    private boolean blockbuster;
    private String rating;        //PG, M16, ..
    private String status;     //Coming Soon, Now Showing, End of showing
    private double[] sales;
    
    public Movie(String movieName){
        name = movieName;
        type = "";
        rating = "";
        status = "Now Showing";
        sales = new double[NUMBER_OF_CINEPLEXES];
        for(int i=0; i< NUMBER_OF_CINEPLEXES; i++){
            sales[i] = 0;
        }
        blockbuster = false;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getType(){
        return type;
    }
    
    public void setType(String type){
        this.type = type;
    }
    
    public String getRating(){
        return rating;
    }
    
    public void setRating(String rating){
        this.rating = rating;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public boolean getBlockbuster(){
        return blockbuster;
    }
    
    public double getTotalSales(){
        double totalSales = 0;
        for(int i=0; i< NUMBER_OF_CINEPLEXES; i++){
            totalSales += sales[i];
        }
        return totalSales;
    }
    
    public void setMovieProperty(String type, String rating, String status){
        this.type = type;
        this.rating = rating;
        this.status = status;
    }
    
    public void setComingSoon(){
        this.status = "Coming Soon";
    }
    
    public void setNowShowing(){
        this.status = "Now Showing";
    }
    
    public void setEndShowing(){
        this.status = "End Of Showing";
    }
    
    public void setBlockbuster(boolean blockbuster){
        this.blockbuster = blockbuster;
    }
    
    public void setSales(double ticketSales, int cineplexID){
        sales[cineplexID] += ticketSales; 
    }

}
