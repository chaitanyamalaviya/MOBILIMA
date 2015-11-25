/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Tram Anh
 */
public class MovieFile {
    public static void writeToFile(Movie movie, int movieID) throws IOException {
        //File movieFile = new File("Movie.txt");
        //PrintWriter writeMovie = new PrintWriter(movieFile);
        //BufferedWriter writeMovie = new BufferedWriter( new FileWriter( "Movie.txt" , true ) );
        PrintWriter writeMovie = new PrintWriter( new FileWriter( "Movie.txt" , true ) ); //USE THIS TO OPEN FILE IN APPEND MODE
        writeMovie.println(movie.getName());
//        writeMovie.println(movieID);
        
        writeMovie.close();
    }
//    You're probably opening, writing, closing the file and then repeating. Each time you open a file for writing, its contents are wiped clean. You can get around this by either:
//    Opening, writing, writing, ..., writing and only closing the file once you've done all the writing.
//    Open the file in append mode, so the old contents aren't erased. <-- use PrintWriter writeMovie = new PrintWriter( new FileWriter( "Movie.txt" , true ) );


//    public static void closeFile(){
//        writeMovie.close();
//    }
}
