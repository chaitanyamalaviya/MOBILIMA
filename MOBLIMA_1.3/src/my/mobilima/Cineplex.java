/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tram Anh
 */
public class Cineplex implements Serializable{
//    public Cinema[] cinemas;
    public ArrayList<Cinema> cinemas;
    private int cineplexID;
    private String cineplexName;
    
    public Cineplex(int cineplexID, String cineplexName){
        cinemas = new ArrayList<>();
        for(int i=0; i<2; i++){                     //default is 3 cinemas per cineplex
            cinemas.add(new Cinema("Normal"));
        }
        cinemas.add(new Cinema("Gold"));          //2 Normal cinema, 1 Gold Suite
        this.cineplexID = cineplexID;
        this.cineplexName = cineplexName;
    }
    
    public int getCineplexID(){
        return cineplexID;
    }
    public void createMoreCinema(String cinemaType){
        cinemas.add(new Cinema(cinemaType));
    }
    
    public String getCineplexName(){
        return cineplexName;
    }
    
}
