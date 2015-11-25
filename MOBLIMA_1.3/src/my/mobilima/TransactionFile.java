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
import java.util.Calendar;

/**
 *
 * @author Tram Anh
 */
public class TransactionFile {

    public static void writeToFile(CustomerInfo customer) throws IOException 
    // Writes the customer information and transaction details to Transaction.txt
    
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        DateFormat dfTID = new SimpleDateFormat("yyyyMMddHHmm");
        PrintWriter writeTransaction = new PrintWriter(new FileWriter("Transaction.txt", true));
        writeTransaction.println("Mobile Number: " + customer.getMobilePhone());
        writeTransaction.println("Movie-goer's name: " + customer.getName());
        writeTransaction.println("Movie-goer's email: " + customer.getEmail());
        writeTransaction.println("Movie: " + customer.getMovieName());
//        writeTransaction.println("Movie Date: " + customer.getMovieDate().get(Calendar.DAY_OF_MONTH) + " " + (customer.getMovieDate().get(Calendar.MONTH)+1) + " " + customer.getMovieDate().get(Calendar.YEAR));
        writeTransaction.println("Movie Date: " + df.format(customer.getMovieDate().getTime()));
        writeTransaction.println("Number of tickets: " + customer.getNumberTickets());
        writeTransaction.println("Number of senior citizens' tickets: " + customer.getSeniorTickets());
        writeTransaction.println("Number of students' tickets: " + customer.getStudentTickets());
        writeTransaction.println("Cineplex: " + (customer.getCineplexID()+1));
        writeTransaction.println("Cineplex Name: " + customer.getCineplexName());
        writeTransaction.println("Cinema: " + (customer.getCinemaID()+1) +"("+customer.getCinemaType()+")");
        for(int i=0; i<customer.getColID().size(); i++){
            System.out.println("Seat "+(i+1)+": Row " + (customer.getRowID().get(i)+1) + "   Column " + (customer.getColID().get(i)+1));
        }
         String cinemaCode = Character.toString((char) (customer.getCineplexID() + 65)) + "0" + (customer.getCinemaID()+1);
        writeTransaction.println("TID: " + cinemaCode + dfTID.format(customer.getTransDate().getTime()));
        writeTransaction.println("Transaction amount: SGD" + customer.getTransAmount());
        writeTransaction.println("");
        
        writeTransaction.close();
    }
}
