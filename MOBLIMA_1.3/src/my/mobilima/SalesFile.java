/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tram Anh
 */
public class SalesFile {

    public static void writeToFile(CustomerInfo customer) throws IOException 
    // Writes the customer transaction information to the Sales.txt file
    
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        PrintWriter writeTransaction = new PrintWriter(new FileWriter("Sales.txt", true));
        writeTransaction.println("Movie Date: " + df.format(customer.getMovieDate().getTime()));
        writeTransaction.println("Movie: " + customer.getMovieName());
        writeTransaction.println(customer.getCineplexName());
        writeTransaction.println("Transaction amount: SGD " + customer.getTransAmount());
        writeTransaction.println("");

        writeTransaction.close();
    }
}
