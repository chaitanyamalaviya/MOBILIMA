/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Tram Anh
 */
public class CustomerInfo {

    private String mobilePhone;
    private String name;
    private String email;
    private Movie movie;
    private Calendar movieDate;
    private Calendar transDate;
    private int numTickets;
    private int numStudentTickets;
    private int numSeniorTickets;
    private double transAmount;
    private int cineplexID;
    private int cinemaID;
    private ArrayList<Integer> ar_seatRowID;
    private ArrayList<Integer> ar_seatColID;
    private String cinemaType;
    private String cineplexName;
    Scanner sc = new Scanner(System.in);

    public CustomerInfo(int numTickets, Movie movie, String cinemaType, Calendar movieDate,String cineplexName, int cineplexID, int cinemaID, ArrayList<Integer> ar_seatRowID, ArrayList<Integer> ar_seatColID) throws FileNotFoundException, ParseException, IOException {
        this.numTickets = numTickets;
        this.movie = movie;
        this.movieDate = movieDate;
        this.cinemaType = cinemaType;
        this.cineplexName = cineplexName;

        System.out.println("Please enter your name");
        name = sc.nextLine();
        System.out.println("Please enter your email address: ");
        email = sc.nextLine();
        System.out.println("Please enter your mobile phone number: ");
        mobilePhone = sc.next();

        System.out.println("How many student tickets? ");
        numStudentTickets = validateInput();
        while (numStudentTickets > numTickets || numStudentTickets < 0) {
            System.out.println("Invalid input for number of student tickets. Please re-enter number of student tickets"
                    + "that is smaller than total number of tickets and larger than 0.");
            numStudentTickets = validateInput();
        }

        if(numTickets - numStudentTickets == 0)
            numSeniorTickets = 0;
        else{
        System.out.println("How many senior citizens tickets? ");
        numSeniorTickets = validateInput();
        while (numSeniorTickets > (numTickets - numStudentTickets) || numStudentTickets < 0) {
            System.out.println("Invalid input for number of senior tickets. Please re-enter number of senior tickets");
            numSeniorTickets = validateInput();
        }
        }
        transAmount = calculateTransAmount(cinemaType);
//        System.out.println("TransAmount = " + transAmount);
        this.transDate = Calendar.getInstance();

        this.cineplexID = cineplexID;
        this.cinemaID = cinemaID;
        this.ar_seatRowID = ar_seatRowID;
        this.ar_seatColID = ar_seatColID;

    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMovieName() {
        return movie.getName();
    }

    public Calendar getMovieDate() {
        return movieDate;
    }

    public Calendar getTransDate() {
        return transDate;
    }

    public int getNumberTickets() {
        return numTickets;
    }

    public int getSeniorTickets() {
        return numSeniorTickets;
    }

    public int getStudentTickets() {
        return numStudentTickets;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public int getCineplexID() {
        return cineplexID;
    }

    public int getCinemaID() {
        return cinemaID;
    }
    
    public String getCineplexName(){
        return cineplexName;
    }
    
    public String getCinemaType(){
        return cinemaType;
    }

    public ArrayList<Integer> getRowID() {
        return ar_seatRowID;
    }

    public ArrayList<Integer> getColID() {
        return ar_seatColID;
    }

    private double calculateTransAmount(String cinemaType) throws FileNotFoundException, ParseException, IOException {
        final int PUBLIC_HOLIDAY = 8;
        double ticketAmount = 0;
        int dayOfWeek = movieDate.get(Calendar.DAY_OF_WEEK);
        int time = movieDate.get(Calendar.HOUR_OF_DAY);

//        System.out.println("weekday gold cinema 2D ticket price = " + TicketPrice.weekdayGoldCinema2D);
//        System.out.println("Numtickets = " + numTickets);

        if (cinemaType.equalsIgnoreCase("Gold")) {
            if (PublicHoliday.checkHoliday(movieDate) == true) {
                dayOfWeek = PUBLIC_HOLIDAY;
            }
            if (movie.getType().equalsIgnoreCase("digital") == true) {
                switch (dayOfWeek) {
                    case Calendar.MONDAY:
                    case Calendar.TUESDAY:
                    case Calendar.WEDNESDAY:
                    case Calendar.THURSDAY:
                        ticketAmount += TicketPrice.weekdayGoldCinema2D * numTickets;
                        break;
                    case Calendar.FRIDAY:
                    case Calendar.SATURDAY:
                    case Calendar.SUNDAY:
                    case PUBLIC_HOLIDAY:
                        ticketAmount += TicketPrice.weekendGoldCinema2D;
                        break;
                }
            } else { //3D movie for Gold class
                switch (dayOfWeek) {
                    case Calendar.MONDAY:
                    case Calendar.TUESDAY:
                    case Calendar.WEDNESDAY:
                    case Calendar.THURSDAY:
                        ticketAmount += TicketPrice.weekdayGoldCinema3D * numTickets;
                        break;
                    case Calendar.FRIDAY:
                    case Calendar.SATURDAY:
                    case Calendar.SUNDAY:
                    case PUBLIC_HOLIDAY:
                        ticketAmount += TicketPrice.weekendGoldCinema3D;
                        break;
                }
            }
        } else { //movie tickets for Normal cinema
            if (PublicHoliday.checkHoliday(movieDate) == true) {
                numStudentTickets = 0;         //set the exclusion for discounted price during public hol
                numSeniorTickets = 0;
            }
            if (movie.getType().equalsIgnoreCase("digital") == true) {
                switch (dayOfWeek) {
                    case Calendar.MONDAY:
                    case Calendar.TUESDAY:
                    case Calendar.WEDNESDAY:
                        if (time < 18) {
                            ticketAmount += TicketPrice.seniorPrice2D * numSeniorTickets + TicketPrice.studentPrice2D * numStudentTickets + TicketPrice.monWed2D * (numTickets - numStudentTickets - numSeniorTickets);
                        } else {
                            ticketAmount += TicketPrice.monWed2D * numTickets;
                        }
                        break;
                    case Calendar.THURSDAY:
                        if (time < 18) {
                            ticketAmount += TicketPrice.seniorPrice2D * numSeniorTickets + TicketPrice.studentPrice2D * numStudentTickets + TicketPrice.thu2D * (numTickets - numStudentTickets - numSeniorTickets);
                        } else {
                            ticketAmount += TicketPrice.thu2D * numTickets;
                        }
                        break;
                    case Calendar.FRIDAY:
                        if (time < 18) {
                            ticketAmount += TicketPrice.seniorPrice2D * numSeniorTickets + TicketPrice.studentPrice2D * numStudentTickets + TicketPrice.friBefore62D * (numTickets - numStudentTickets - numSeniorTickets);
                        } else {
                            ticketAmount += TicketPrice.friAfter62D * numTickets;
                        }
                        break;
                    case Calendar.SATURDAY:
                    case Calendar.SUNDAY:
                        ticketAmount += TicketPrice.weekend2D * numTickets;
                        break;
                }
            } else if (movie.getType().equalsIgnoreCase("3d") == true) {
                switch (dayOfWeek) {
                    case Calendar.MONDAY:
                    case Calendar.TUESDAY:
                    case Calendar.WEDNESDAY:
                    case Calendar.THURSDAY:
                        if (time < 18) {
                            ticketAmount += TicketPrice.studentPrice3D * numStudentTickets + TicketPrice.monWed3D * (numTickets - numStudentTickets);
                        } else {
                            ticketAmount += TicketPrice.monWed3D * numTickets;
                        }
                        break;
                    case Calendar.FRIDAY:
                        if (time < 18) {
                            ticketAmount += TicketPrice.studentPrice3D * numStudentTickets + TicketPrice.friBefore63D * (numTickets - numStudentTickets);
                        } else {
                            ticketAmount += TicketPrice.friAfter63D * numTickets;
                        }
                        break;
                    case Calendar.SATURDAY:
                    case Calendar.SUNDAY:
                        ticketAmount += TicketPrice.weekend3D * numTickets;
                        break;
                }
            }

            if (movie.getBlockbuster() == true) {
                ticketAmount += numTickets;
            }
        }
        return ticketAmount;
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

    private int validateInput() {
        String intStr = sc.next();
        while (isNumeric(intStr) == false) {
            System.out.println("Please re-enter input: ");
            intStr = sc.next();
        }
        return Integer.parseInt(intStr);

    }
}
