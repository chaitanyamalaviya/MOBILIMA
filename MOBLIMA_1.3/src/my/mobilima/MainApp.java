/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Tram Anh
 */
public class MainApp implements Serializable {
    // public static final int MAX_NO_OF_MOVIES = 10;
//    private static final long serialVersionUID = 6529685098267757690L;

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, FileNotFoundException, ParseException {
        //retrieving info from serializable files
        //here we serialize Movie array and Cineplex array

//       // initialization when there's no object to deserialize from, 
//        especially when there's a change in classes that are serialized
//        ArrayList<Cineplex> cineplexes = new ArrayList<>();
////        for (int i = 0; i < 3; i++) {
////            cineplexes.add(new Cineplex(i));
////        }

//        ArrayList<Cineplex> cineplexes = new ArrayList<>();
//        cineplexes.add(new Cineplex(0, "Cineleisure Orchard"));
//        cineplexes.add(new Cineplex(1, "The Cathay Cineplex"));
//        cineplexes.add(new Cineplex(2, "Causeway Point"));
//        ArrayList<Movie> movies = new ArrayList<>();

        ArrayList<Cineplex> cineplexes;
        ArrayList<Movie> movies;
        cineplexes = deserializeCineplexes();
        movies = deserializeMovies();

        ArrayList<String> ar_ticketPrice = readTicketPrice();
        TicketPrice.setTicketPrice(ar_ticketPrice);

        System.out.println("Are you a ");
        System.out.println("1. Customer");
        System.out.println("2. Cinema staff");
        String userChoice = sc.next();
        switch (userChoice) {
            case "1":
                customerMenu(movies, cineplexes);
                break;
            case "2":
                staffMenu(movies, cineplexes, ar_ticketPrice);
                break;
            default:
                System.out.println("Invalid input. System is exiting..");
        }




        serializeCineplexes(cineplexes);
        serializeMovies(movies);
        writeTicketPrice(ar_ticketPrice);

//        //testing calculating of ticket price
//        Calendar movieDate = Calendar.getInstance();
//        System.out.println("Enter num of tickets: ");
//        int numTic = sc.nextInt();
//        String cinemaType = "Normal";
//        ArrayList<Integer> ar_seatRowID = new ArrayList<>();
//        ArrayList<Integer> ar_seatColID = new ArrayList<>();
//        CustomerInfo cust_info = new CustomerInfo(numTic,movies.get(0), cinemaType, movieDate, 1, 1, ar_seatRowID, ar_seatColID );

    }

    public static void serializeCineplexes(ArrayList<Cineplex> cineplexes) {
        try {
            try (FileOutputStream fileOut = new FileOutputStream("Cineplexes.ser"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(cineplexes);
            }
            System.out.printf("Serialized data is saved in Cineplexes.ser");
        } catch (IOException i) {
        }
    }

    public static void serializeMovies(ArrayList<Movie> movies) {
        try {
            try (FileOutputStream fileOut = new FileOutputStream("Movies.ser"); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(movies);
            }
            System.out.printf("Serialized data is saved in Movies.ser");
        } catch (IOException i) {
        }
    }

    public static ArrayList<Cineplex> deserializeCineplexes() {
        try {
            ArrayList<Cineplex> cineplexes;
            try (FileInputStream fileIn = new FileInputStream("Cineplexes.ser"); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                cineplexes = (ArrayList<Cineplex>) in.readObject();
            }
            return cineplexes;
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("ArrayList<Cineplex> class not found");
            return null;
        }
    }

    public static ArrayList<Movie> deserializeMovies() {
        try {
            ArrayList<Movie> movies;
            try (FileInputStream fileIn = new FileInputStream("Movies.ser"); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                movies = (ArrayList<Movie>) in.readObject();
            }
            return movies;
        } catch (IOException i) {
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("ArrayList<Movie> class not found");
            return null;
        }
    }

    public static ArrayList<String> readTicketPrice() 
    // Reads the ticket price from the TicketPrice class 
    
    {
        ArrayList<String> ticketprice = new ArrayList<>();
        try {
            try (Scanner filein = new Scanner(new File("TicketPrice.txt"))) {
                String inputLine;
                while (filein.hasNext()) {
                    inputLine = filein.nextLine();
                    ticketprice.add(inputLine);
                }
            }
            return ticketprice;
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            System.exit(0);
            return null;
        }
    }

    public static void writeTicketPrice(ArrayList<String> ticketprice) 
    // Writes the ticket price to a text file TicketPrice.txt
    
    {
        try {
            FileWriter fwStream = new FileWriter("TicketPrice.txt");
            BufferedWriter bwStream = new BufferedWriter(fwStream);
            try (PrintWriter pwStream = new PrintWriter(bwStream)) {
                int num;
                for (num = 0; num < ticketprice.size(); num++) {
                    pwStream.println(ticketprice.get(num));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the input file!"
                    + e.getMessage());
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IO Error!" + e.getMessage());
            System.exit(0);
        }
    }
    //serialize and deserialize TicketPrice --> change to text file

    public static void customerMenu(ArrayList<Movie> movies, ArrayList<Cineplex> cineplexes) throws FileNotFoundException, IOException, ParseException 
 // Displays the main menu for the movie-goers
    
    {
        String choice;
        do {
            System.out.println("");
            System.out.println("---------------CUSTOMER MENU--------------------|");
            System.out.println("|Please choose an option:                       |");
            System.out.println("|1. View Coming Soon movies                     |");
            System.out.println("|2. Search movie for showtime & Create booking  |");
            System.out.println("|3. Check booking status                        |");
            System.out.println("|4. Check booking history                       |");
            System.out.println("|5. View GUI for search/list movie showtime     |");
            System.out.println("|6. Quit                                        |");
            System.out.println("|-----------------------------------------------|");

            choice = sc.next();
            switch (choice) {
                case "1":
                    CreateUpdateMovie.listUpcomingMovies(movies);
                    break;
                case "2":
                    Booking.createBooking(cineplexes, movies);
                    break;
                case "3":
                    Booking.checkBookingStatus();
                    break;
                case "4":
                    Booking.checkBookingHistory();
                    break;
                case "5":
                    System.out.println("Please see the pop-up window..");
                    try {
                        GUI_SearchShowtime frame = new GUI_SearchShowtime(movies, cineplexes);
                        frame.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    System.out.println("System is exiting");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (choice.equals("6") == false);


    }

    public static void staffMenu(ArrayList<Movie> movies, ArrayList<Cineplex> cineplexes, ArrayList<String> ar_ticketPrice) throws IOException 
    // Displays the main menu for the cinema staff
    
    {
        String choice;
        do {
            System.out.println("");
            System.out.println("--------------STAFF MENU-------------|");
            System.out.println("|Please choose the following options:|");
            System.out.println("|1. View current movie listing       |");
            System.out.println("|2. Create movie listing             |");
            System.out.println("|3. Update movie listing             |");
            System.out.println("|4. Remove movie listing             |");
            System.out.println("|5. Create cinema showtime           |");
            System.out.println("|6. Update cinema showtime           |");
            System.out.println("|7. Remove cinema showtime           |");
            System.out.println("|8. Print sales revenue report       |");
            System.out.println("|9. Update ticket price              |");
            System.out.println("|10.Set upcoming holiday             |");
            System.out.println("|11.Quit                             |");
            System.out.println("|----------------------------------------|");
            choice = sc.next();
            switch (choice) {
                case "1":
                    CreateUpdateMovie.listMovies(movies);
                    break;
                case "2":
                    CreateUpdateMovie.createMovie(movies);
                    break;
                case "3":
                    CreateUpdateMovie.updateMovieDetails(movies, cineplexes);
                    break;
                case "4":
                    CreateUpdateMovie.deleteMovie(movies, cineplexes);
                    break;
                case "5":
                    CreateUpdateShowtime.createShowtime(cineplexes, movies);
                    break;
                case "6":
                    CreateUpdateShowtime.updateShowtime(cineplexes);
                    break;
                case "7":
                    CreateUpdateShowtime.deleteShowtime(cineplexes, movies);
                    break;
                case "8":
                    Sales.generateSales(cineplexes);
                    break;
                case "9":
                    TicketPrice.changeTicketPrice(ar_ticketPrice);
                    break;
                case "10":
                    PublicHoliday.setPublicHol();
                    break;
                case "11":
                    System.out.println("System is exiting..");
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
            }
        } while (choice.equals("11") == false);
    }
}