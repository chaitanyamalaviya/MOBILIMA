/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static my.mobilima.MainApp.sc;

/**
 *
 * @author Tram Anh
 */
public class Booking {

    public static int createBooking(ArrayList<Cineplex> cineplexes, ArrayList<Movie> movies) throws FileNotFoundException, ParseException, IOException {
        ArrayList<Integer> ar_seatRowID = new ArrayList<>();
        ArrayList<Integer> ar_seatColID = new ArrayList<>();
        int index, cineplexID, cinemaID, daysFromToday, time, numTickets;
        String movieName;

        CreateUpdateMovie.listMovies(movies);
        System.out.println("Enter the index of movie that you want to see showtime: ");
        String movieStr = sc.next();
        if (isNumeric(movieStr) == false) {
            System.out.println("Invalid input. Function is exiting..");
        } else {
            index = Integer.parseInt(movieStr);
            if (index >= movies.size()) {
                System.out.println("Invalid input. Function is exiting.");
                return -1;
            } else {
                //main function for this case
                movieName = movies.get(index).getName();
                //list all showtimes for the movie input
                if (CreateUpdateMovie.listMovieShowtime(movieName, cineplexes) == false) {
                    System.out.println("There is no showtime appointed for this movie yet. Function is exiting..");
                    return -1;
                } else {
                    System.out.println("Choosing showtime...");
                    System.out.println("Choose the cineplex: ");
                    System.out.println("1. Cineplex 1");
                    System.out.println("2. Cineplex 2");
                    System.out.println("3. Cineplex 3");
                    String cineplexStr = sc.next();
                    if (isNumeric(cineplexStr) == false || Integer.parseInt(cineplexStr) < 1 || Integer.parseInt(cineplexStr) > 3) {
                        System.out.println("Invalid input. Function is exiting..");
                    } else {
                        cineplexID = Integer.parseInt(cineplexStr) - 1;  //menu starts from 1, array of cineplex starts from 0
                        System.out.println("Choose cinema to input:");
                        for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                            //this is to dynamically show cinema menu depending on number of cinemas (not always 3)
                            System.out.println((i + 1) + ". Cinema " + (i + 1) + " (" + cineplexes.get(cineplexID).cinemas.get(i).getCinemaType() + ")");
                        }
                        String cinemaStr = sc.next();
                        if (isNumeric(cinemaStr) == false || Integer.parseInt(cinemaStr) < 1 || Integer.parseInt(cinemaStr) > cineplexes.get(cineplexID).cinemas.size()) {
                            System.out.println("Invalid input. Function is exiting..");
                        } else {
                            cinemaID = Integer.parseInt(cinemaStr) - 1;
                            System.out.println("Enter day number");
                            String dayStr = sc.next();
                            if (isNumeric(dayStr) == false || Integer.parseInt(dayStr) < 0 || Integer.parseInt(dayStr) > 6) {
                                System.out.println("Invalid input. Function is exiting..");
                            } else {
                                daysFromToday = Integer.parseInt(dayStr);
                                System.out.println("Enter time slot:");
                                System.out.println("1. 10am");
                                System.out.println("2. 1pm");
                                System.out.println("3. 4pm");
                                System.out.println("4. 7pm");
                                System.out.println("5. 10pm");
                                String timeStr = sc.next();
                                if (isNumeric(timeStr) == false || Integer.parseInt(timeStr) < 1 || Integer.parseInt(timeStr) > 5) {
                                    System.out.println("Invalid input. Function is exiting..");
                                } else {
                                    time = Integer.parseInt(timeStr) - 1;
                                    if (cineplexes.get(cineplexID).cinemas.get(cinemaID).checkOccupiedSlot(daysFromToday, time) == false) {
                                        System.out.println("Invalid input of day and time. Function is exiting..");
                                        return -1;
                                    }

                                    if(cineplexes.get(cineplexID).cinemas.get(cinemaID).getEmptySeats(daysFromToday, time) == 0){
                                        System.out.println("The movie for this time slot is fully booked. Please book another timing.");
                                        System.out.println("Function is exiting..");
                                        return -1;
                                    }
                                    Calendar movieDate = Calendar.getInstance();
                                    movieDate.add(Calendar.DAY_OF_YEAR, daysFromToday);

                                    movieDate.set(Calendar.HOUR_OF_DAY, 10 + 3 * time);       //algorithm: when time=1, set hour=10, time=2-->hour=13,..
                                    movieDate.set(Calendar.MINUTE, 0);
                                    System.out.println("Booking seats..");
                                    System.out.println("Current Theater seat layout:");
                                    cineplexes.get(cineplexID).cinemas.get(cinemaID).showSeatLayout(daysFromToday, time);

                                    System.out.println("How many tickets do you want to buy?");
                                    String ticketStr = sc.next();
                                    if (isNumeric(ticketStr) == false || Integer.parseInt(ticketStr) < 0) {
                                        System.out.println("Invalid input. Function is exiting..");
                                    } else {
                                        numTickets = Integer.parseInt(ticketStr);
                                        int rowID, colID;
                                        for (int i = 0; i < numTickets; i++) {
                                            System.out.println("Enter the seat (row, column) for ticket no. " + (i + 1) + ": ");
                                            //RMB to do error checking here
                                            rowID = validateRow(cineplexes.get(cineplexID).cinemas.get(cinemaID).getCinemaType());
                                            colID = validateCol(cineplexes.get(cineplexID).cinemas.get(cinemaID).getCinemaType());
                                            ar_seatRowID.add(rowID);
                                            ar_seatColID.add(colID);
                                            cineplexes.get(cineplexID).cinemas.get(cinemaID).bookSeat(rowID, colID, daysFromToday, time);
                                        }
                                        cineplexes.get(cineplexID).cinemas.get(cinemaID).showSeatLayout(daysFromToday, time);

                                        CustomerInfo cust_info = new CustomerInfo(numTickets, movies.get(index), cineplexes.get(cineplexID).cinemas.get(cinemaID).getCinemaType(),
                                                movieDate, cineplexID, cinemaID, ar_seatRowID, ar_seatColID);

                                        printInvoice(cust_info);
                                        TransactionFile.writeToFile(cust_info);
                                        SalesFile.writeToFile(cust_info);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }

    private static void printInvoice(CustomerInfo customer) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        DateFormat dfTID = new SimpleDateFormat("yyyyMMddhhmm");
        System.out.println("Booking invoice...");
        System.out.println("Movie-goer's name: " + customer.getName());
        System.out.println("Movie-goer's email: " + customer.getEmail());
        System.out.println("Mobile Number: " + customer.getMobilePhone());
        System.out.println("Movie: " + customer.getMovieName());
        System.out.println("Movie Date: " + df.format(customer.getMovieDate().getTime()));
        System.out.println("Number of tickets: " + customer.getNumberTickets());
        System.out.println("Number of senior citizens' tickets: " + customer.getSeniorTickets());
        System.out.println("Number of students' tickets: " + customer.getStudentTickets());
        System.out.println("Cineplex: " + (customer.getCineplexID() + 1));
        System.out.println("Cinema: " + (customer.getCinemaID() + 1) + "(" + customer.getCinemaType() + ")");
        for (int i = 0; i < customer.getColID().size(); i++) {
            System.out.println("Seat: Row " + (customer.getRowID().get(i)+1) + "   Column " + (customer.getColID().get(i)+1));
        }
        String cinemaCode = Character.toString((char) (customer.getCineplexID() + 65)) + "0" + (customer.getCinemaID() + 1);

        System.out.println("TID: " + cinemaCode + dfTID.format(customer.getTransDate().getTime()));
        System.out.println("Transaction amount: SGD" + customer.getTransAmount());
        System.out.println("");
    }

    public static void checkBookingStatus() throws FileNotFoundException, IOException {
        System.out.println("Enter the Transaction ID to check for booking status: ");
        String TID = sc.next();

        String firstLine = "TID: " + TID;

        BufferedReader reader = new BufferedReader(new FileReader("Transaction.txt"));

        //check for transaction ID see if it's in the text file
        String currentLine;
        boolean successfulTrans = false;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equals(firstLine)) {
                System.out.println("Transaction " + TID + " is made successful.");
                successfulTrans = true;
            }
        }
        if (successfulTrans == false) //checking the flag
        {
            System.out.println("Transaction " + TID + " is NOT captured in the system.");
        }
        reader.close();
    }

    public static void checkBookingHistory() throws IOException {
        System.out.println("Enter mobile phone to check for booking history: ");
        String mobilePhone = sc.next();

        String firstLine = "Mobile Number: " + mobilePhone;
        BufferedReader reader = new BufferedReader(new FileReader("Transaction.txt"));

        //check for transaction ID see if it's in the text file
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            if (currentLine.equalsIgnoreCase(firstLine) == true) {
                while (!currentLine.equals("")) {
                    System.out.println(currentLine);
                    currentLine = reader.readLine();
                }
            }
        }
        reader.close();
    }

    private static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            System.out.println("Error. Input is not numeric!");
            return false;
        }
        return true;
    }

    private static int validateInput() {
        String intStr = sc.next();
        while (!(intStr instanceof String)) {
            System.out.println("Please re-enter input: ");
            intStr = sc.next();
        }
        String upper = intStr.toUpperCase();
        char character = upper.charAt(0);
        return ((int)(character - 64));

    }

    private static int validateRow(String cinemaType) {
        int seatID = validateInput();
        if (cinemaType.equals("Gold")) {
            while (seatID > SeatingPlan_Gold.ROW_GOLD || seatID < 1) {  //seatID should be from 1 to 10 for eg
                System.out.println("Please enter seat number within the seat layout range. Re-enter:");
                seatID = validateInput();
            }
        } else if (cinemaType.equals("Gold")) {
            while (seatID > SeatingPlan_Normal.ROW_NORMAL || seatID < 1) {  //seatID should be from 1 to 10 for eg
                System.out.println("Please enter seat number within the seat layout range. Re-enter:");
                seatID = validateInput();
            }
        }
        return seatID - 1;    //-1 so that it will return seat from range 0 to 9 (for eg)
    }

    private static int validateCol(String cinemaType) {
        int seatID = validateInput();
        if (cinemaType.equals("Gold")) {
            while (seatID > SeatingPlan_Gold.COL_GOLD || seatID < 1) {  //seatID should be from 1 to 20 for eg
                System.out.println("Please enter seat number within the seat layout range. Re-enter:");
                seatID = validateInput();
            }
        } else if (cinemaType.equals("Gold")) {
            while (seatID > SeatingPlan_Normal.COL_NORMAL || seatID < 1) {  //seatID should be from 1 to 20 for eg
                System.out.println("Please enter seat number within the seat layout range. Re-enter:");
                seatID = validateInput();
            }
        }
        return seatID - 1;    //-1 so that it will return seat from range 0 to 19 (for eg)
    }
}
