/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Tram Anh
 */
public class TicketPrice {

    public static double seniorPrice2D;
//  public double seniorPrice3D;
    public static double studentPrice2D;
    public static double studentPrice3D;
    public static double monWed2D;
    public static double monWed3D;
    public static double thu2D;
    public static double thu3D;
    public static double friBefore62D;
    public static double friBefore63D;
    public static double friAfter62D;
    public static double friAfter63D;
    public static double weekend2D;
    public static double weekend3D;
    public static double weekdayGoldCinema2D;
    public static double weekdayGoldCinema3D;
    public static double weekendGoldCinema2D;
    public static double weekendGoldCinema3D;
    private PublicHoliday publicHol;

//    public TicketPrice(ArrayList<String> ticketprice) {
////        seniorPrice2D = 4.0;
////        studentPrice2D = 7.0;
////        studentPrice3D = 8.0;
////        monWed2D = 8.50;
////        monWed3D = 10.0;
////        thu2D = 8.50;
////        thu3D = 10.0;
////        friBefore62D = 9.50;
////        friBefore63D = 13.0;
////        friAfter62D = 11.0;
////        friAfter63D = 13.00;
////        weekend2D = 11.0;
////        weekend3D = 13.0;
////
////        weekdayGoldCinema2D = 28.0;
////        weekdayGoldCinema3D = 35.0;
////        weekendGoldCinema2D = 38.0;
////        weekendGoldCinema3D = 45.0;
//        setTicketPrice(ticketprice);
//        publicHol = new PublicHoliday();
//    }

    public static void changeTicketPrice(ArrayList<String> ticketprice) 
    // Sets the ticket price according to different categories
    {
        int counter;
        Scanner sc = new Scanner(System.in);
        System.out.println("Choose option to change:");
        System.out.println("1. Senior price for digital movies - " + seniorPrice2D);
//        System.out.println("2. Senior price for 3D movies");
        System.out.println("2. Student's price for digital movies - " + studentPrice2D);
        System.out.println("3. Student's price for 3D movies - " + studentPrice3D);
        System.out.println("4. Monday - Wednesday ticket price for Digital movies - "+ monWed2D);
        System.out.println("5. Monday - Wednesday ticket price for 3D movies - "+ monWed3D);
        System.out.println("6. Thursday ticket price for Digital - "+thu2D);
        System.out.println("7. Thursday ticket ptice for 3D - "+thu3D);
        System.out.println("8. Friday (before 6pm) for Digital - "+friBefore62D);
        System.out.println("9. Friday (before 6pm) for 3D - "+friBefore63D);
        System.out.println("10. Friday (after 6pm) for Digital - "+friAfter62D);
        System.out.println("11. Friday (after 6pm) for 3D - "+friAfter63D);
        System.out.println("12. Saturday/Sunday ticket price for Digital - "+weekend2D);
        System.out.println("13. Saturday/Sunday ticket price for 3D - "+weekend3D);
        System.out.println("14. Price for Gold Cinema (Mon-Thu) for Digital - "+weekdayGoldCinema2D);
        System.out.println("15. Price for Gold Cinema (Mon-Thu) for 3D - "+weekdayGoldCinema3D);
        System.out.println("16. Price for Gold Cinema (Fri-Sun) for Digital - "+weekendGoldCinema2D);
        System.out.println("17. Price for Gold Cinema (Fri-Sun) for 3D - "+weekendGoldCinema3D);

        int choice = sc.nextInt();

        System.out.println("Enter the new price");
        double newPrice = sc.nextDouble();
        String StrnewPrice = String.valueOf(newPrice);
        switch (choice) {
            case 1:
                seniorPrice2D = newPrice;
                counter = ticketprice.indexOf("seniorPrice2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 2:
                studentPrice2D = newPrice;
                counter = ticketprice.indexOf("studentPrice2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 3:
                studentPrice3D = newPrice;
                counter = ticketprice.indexOf("studentPrice3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 4:
                monWed2D = newPrice;
                counter = ticketprice.indexOf("monWed2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 5:
                monWed3D = newPrice;
                counter = ticketprice.indexOf("monWed3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 6:
                thu2D = newPrice;
                counter = ticketprice.indexOf("thu2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 7:
                thu3D = newPrice;
                counter = ticketprice.indexOf("thu3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 8:
                friBefore62D = newPrice;
                counter = ticketprice.indexOf("friBefore62D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 9:
                friBefore63D = newPrice;
                counter = ticketprice.indexOf("friBefore63D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 10:
                friAfter62D = newPrice;
                counter = ticketprice.indexOf("friAfter62D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 11:
                friAfter63D = newPrice;
                counter = ticketprice.indexOf("friAfter63D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 12:
                weekend2D = newPrice;
                counter = ticketprice.indexOf("weekend2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 13:
                weekend3D = newPrice;
                counter = ticketprice.indexOf("weekend3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 14:
                weekdayGoldCinema2D = newPrice;
                counter = ticketprice.indexOf("weekdayGoldCinema2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 15:
                weekdayGoldCinema3D = newPrice;
                counter = ticketprice.indexOf("weekdayGoldCinema3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 16:
                weekendGoldCinema2D = newPrice;
                counter = ticketprice.indexOf("weekendGoldCinema2D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;
            case 17:
                weekendGoldCinema3D = newPrice;
                counter = ticketprice.indexOf("weekendGoldCinema3D");
                ticketprice.set(counter + 1, StrnewPrice);
                break;

        }
        System.out.println("The ticket price has been changed. Please check TicketPrice.txt for new price.");

    }

//    public boolean isHol(Calendar date) {
//        return publicHol.checkHoliday(date);
//    }

    public static void setTicketPrice(ArrayList<String> ticketprice) 
    // Set the ticket price for different categories
    {
        int num, i = 0;
        String value;

        double[] tempprice = new double[20];

        for (num = 1; num <= ticketprice.size(); num = num + 2) {
            value = ticketprice.get(num);
            double dvalue = Double.parseDouble(value);
            tempprice[i] = (dvalue);
            i++;
        }
        seniorPrice2D = tempprice[0];
        studentPrice2D = tempprice[1];
        studentPrice3D = tempprice[2];
        monWed2D = tempprice[3];
        monWed3D = tempprice[4];
        thu2D = tempprice[5];
        thu3D = tempprice[6];
        friBefore62D = tempprice[7];
        friBefore63D = tempprice[8];
        friAfter62D = tempprice[9];
        friAfter63D = tempprice[10];
        weekend2D = tempprice[11];
        weekend3D = tempprice[12];

        weekdayGoldCinema2D = tempprice[13];
        weekdayGoldCinema3D = tempprice[14];
        weekendGoldCinema2D = tempprice[15];
        weekendGoldCinema3D = tempprice[16];
    }
}
