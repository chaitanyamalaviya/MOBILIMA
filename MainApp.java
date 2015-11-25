/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.io.*;

/**
 *
 * @author Tram Anh
 */
public class MainApp implements Serializable {
    // public static final int MAX_NO_OF_MOVIES = 10;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        SeatingPlanInterface cinemaSeating[] = new SeatingPlanInterface[400]; --> put as an attribute in TimeSlot class
        //Movie movies[] = new Movie[MAX_NO_OF_MOVIES];
        ArrayList<Movie> movies = new ArrayList<>();
        //Cineplex cineplexes[] = new Cineplex[3];
        ArrayList<Cineplex> cineplexes = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        ArrayList<Integer> ar_seatRowID = new ArrayList<>();
        ArrayList<Integer> ar_seatColID = new ArrayList<>();

        // TicketPrice price = new TicketPrice();

        //initialise cineplexes
        for (int i = 0; i < 3; i++) {
            cineplexes.add(new Cineplex(i));
        }





//        //Create cinema showtime
//        System.out.println("Create cinema showtimes");
//        System.out.println("Enter cineplex to input:");
//        int cineplexID = sc.nextInt();
//        System.out.println("Enter cinema to input:");
//        int cinemaID = sc.nextInt();
//        //cineplexes[cineplexID] = new Cineplex();
//        cineplexes.get(cineplexID).cinemas.get(cinemaID).setToday();
//        cineplexes.get(cineplexID).cinemas.get(cinemaID).checkEmptySlot();
//
//        System.out.println("Enter Day Number and time slot (int: 10, 13, 16,...)");
//        int daysAhead = sc.nextInt();
//        int timeSlot = sc.nextInt();
//
//        cal.add(Calendar.DAY_OF_YEAR, daysAhead);
//        cal.set(Calendar.HOUR_OF_DAY, timeSlot);
//        
//        cineplexes.get(cineplexID).cinemas.get(cinemaID).cinemaBooking(daysAhead, timeSlot, movies.get(0).getName());
////        System.out.println("Seating Plan ID: " + cineplexes.get(cineplexID).cinemas.get(cinemaID).getSeatingPlanID(daysAhead, timeSlot));
//        cineplexes.get(cineplexID).cinemas.get(cinemaID).checkEmptySlot();
////        CineplexFile.writeToFile(cineplexID, movies.get(0), cal, seatingPlan);
//
////        int numTickets = createBooking(cinemaSeating.get(seatingPlan), ar_seatRowID, ar_seatColID);
//        int numTickets = createBooking(cineplexes.get(cineplexID).cinemas.get(cinemaID).getCinemaSeating(daysAhead, timeSlot), ar_seatRowID, ar_seatColID);
    }

//    public static int createBooking(SeatingPlanInterface cinemaSeating, ArrayList<Integer> ar_seatRowID, ArrayList<Integer> ar_seatColID) {
//        System.out.println("Booking seats..");
//
//        cinemaSeating.showSeatLayout();
//
//        System.out.println("How many tickets do you want to buy?");
//        int numTickets = sc.nextInt();
//        for (int i = 0; i < numTickets; i++) {
//            System.out.println("Enter the seat (row, column) for ticket no. " + (i + 1) + ": ");
//            ar_seatRowID.add(sc.nextInt());
//            ar_seatColID.add(sc.nextInt());
//            cinemaSeating.bookSeat(ar_seatRowID.get(i), ar_seatColID.get(i), 0);
//        }
//        cinemaSeating.showSeatLayout();
//
//        return numTickets;
//    }
    public static void serializeCineplexes(ArrayList<Cineplex> cineplexes) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Cineplexes.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(cineplexes);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in Cineplexes.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void serializeMovies(ArrayList<Movie> movies) {
        try {
            FileOutputStream fileOut = new FileOutputStream("Movies.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(movies);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in Movies.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void deserializeCineplexes(ArrayList<Cineplex> cineplexes) {
        try {
            FileInputStream fileIn = new FileInputStream("Cineplexes.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            cineplexes = (ArrayList<Cineplex>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ArrayList<Cineplex> class not found");
            c.printStackTrace();
            return;
        }
    }

    public static void deserializeMovies(ArrayList<Movie> movies) {
        try {
            FileInputStream fileIn = new FileInputStream("Movies.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            movies = (ArrayList<Movie>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("ArrayList<Movie> class not found");
            c.printStackTrace();
            return;
        }
    }

    public static void ReadTicketPrice(String[] args) {

        ArrayList<String> ticketprice = new ArrayList<>();

        try {

            Scanner filein = new Scanner(new File("TicketPrice.txt"));
            String inputLine;
            while (filein.hasNext()) {
                inputLine = filein.nextLine();
                ticketprice.add(inputLine);
            }
            filein.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!"
                    + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void WriteTicketPrice(String[] args) {

        ArrayList<String> ticketprice = new ArrayList<>();

        try {

            FileWriter fwStream = new FileWriter("ticketprice.txt");
            BufferedWriter bwStream = new BufferedWriter(fwStream);
            PrintWriter pwStream = new PrintWriter(bwStream);
            int num;
            for (num = 0; num < ticketprice.size(); num++) {
                pwStream.println(ticketprice.get(num));
            }
            pwStream.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!"
                    + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
    }
}
