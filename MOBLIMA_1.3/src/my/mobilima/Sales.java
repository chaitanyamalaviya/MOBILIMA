package my.mobilima;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.text.DateFormatSymbols;
import java.util.ArrayList;

public class Sales {

    public static void generateSales(ArrayList<Cineplex> cineplexes) {

        int choice1, choice2, numDate1, numMonth;
        int i, j, k, movieShowed;
        double thatSale, unitTotal=0, sumTotal=0;
        int maxMovie = 20, numCinp = 3;
        String currentLine;
        Scanner sc = new Scanner(System.in);
        Calendar cal = Calendar.getInstance();
        DateFormatSymbols dateFormat = new DateFormatSymbols();
        String[] monthNames = dateFormat.getMonths();



        do {
            System.out.println("Enter your choice:");
            System.out.println("(1)By movie");
            System.out.println("(2)By cineplex");
            System.out.println("(3)Quit");
            choice1 = sc.nextInt();

            switch (choice1) {

                case 1: {
                    System.out.println("Enter your choice for period selection : ");
                    System.out.println("(1) By day--first day of the month till current day");
                    System.out.println("(2) By month--from January till current month");
                    choice2 = sc.nextInt();

                    switch (choice2) {

                        case 1: {                                          // Prints out sales revenue report by movie by day
                            numDate1 = cal.get(Calendar.DAY_OF_MONTH);
                            double[][][] parSale = new double[numDate1][maxMovie][numCinp];
                            String[] movName = new String[maxMovie];
                            String[] actDate = new String[numDate1];
                            String[] cinName = new String[numCinp];
                       //     cinName[0] = "Cineplex: 1";
                       //     cinName[1] = "Cineplex: 2";
                       //     cinName[2] = "Cineplex: 3";
                            
                            for(i =0; i<3; i++){
                                cinName[i] = cineplexes.get(i).getCineplexName();
                            }
                            
                            movieShowed = 0;

                            for (i = 0; i < numDate1; i++) {
//						actDate[i] = (Integer.toString(date1 + i)) + "/" + "11";
                                actDate[i] = (Integer.toString(i + 1)) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                            }

                            for (i = 0; i < numDate1; i++) {
                                for (j = 0; j < maxMovie; j++) {
                                    for (k = 0; k < numCinp; k++) {
                                        parSale[i][j][k] = 0;
                                    }
                                }
                            }
                            File file = new File("Sales.txt");

                            try {
                                Scanner scStream = new Scanner(file);
                                while (scStream.hasNext()) {
                                    currentLine = scStream.nextLine();

                                    for (i = 0; i < numDate1; i++) {
                                        if(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()).equalsIgnoreCase(actDate[i])) {//for a particular date
                                            currentLine = scStream.nextLine();

                                            for (j = 0; j < maxMovie; j++) {//for a particular movie
                                                if (movName[j] == null) {//if no entry of that movie in the array and that movie has not been stored
                                                    movName[j] = currentLine;
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            //thatSale = Double.parseDouble(currentLine);
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    movieShowed++;

                                                    break;
                                                } 
                                                else if (currentLine.equalsIgnoreCase(movName[j])) {//if that movie has been stored in the list
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            //thatSale = Double.parseDouble(currentLine);
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    break;

                                                }
                                               

                                            }
                                            break;
                                            
                                           
                                                                                                       
                                                                                
                                        }
                                        
                                       
                                    }
                                    currentLine = scStream.nextLine();
                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                            for (i = 0; i < movieShowed; i++) {
                                System.out.println("Movie " + (i+1) + ":" + movName[i]);
                                System.out.println("");
                                sumTotal = 0;
                                for (j = 0; j < numDate1; j++) {
                                    System.out.println("On " + actDate[j] + ":");
                                    unitTotal = 0;
                                    for (k = 0; k < numCinp; k++) {
                                        System.out.println("Cineplex: " + cineplexes.get(k).getCineplexName() + ", the sale is " + parSale[j][i][k] + " dollars.");
                                        unitTotal += parSale[j][i][k];
                                    }
                                    System.out.println("Total sale on this day: " + unitTotal);
                                    System.out.println("");
                                    sumTotal += unitTotal;
                                }
                                System.out.println("Total sales for this movie: " + sumTotal);
                                System.out.println("");
                                System.out.println("");
                            }
                            break;
                        }

                        case 2: {											// Prints out the sales revenue report by movie by month
                            numMonth = cal.get(Calendar.MONTH) + 1;
                            double[][][] parSale = new double[numMonth][maxMovie][numCinp];
                            String[] movName = new String[maxMovie];
                            String[] actMonth = new String[numMonth];
                            String[] cinName = new String[numCinp];
                           // cinName[0] = "Cineplex: 1";
                           // cinName[1] = "Cineplex: 2";
                           // cinName[2] = "Cineplex: 3";
                            
                            for(i =0; i<3; i++){
                                cinName[i] = cineplexes.get(i).getCineplexName();
                            }
                            for (i = 0; i < numMonth; i++) {
                                actMonth[i] = (Integer.toString(i + 1)) + "/" + cal.get(Calendar.YEAR);
                            }
                            for (i = 0; i < numMonth; i++) {
                                for (j = 0; j < maxMovie; j++) {
                                    for (k = 0; k < numCinp; k++) {
                                        parSale[i][j][k] = 0;
                                    }
                                }
                            }
                            movieShowed = 0;
                            File file = new File("Sales.txt");
                            try {
                                Scanner scStream = new Scanner(file);
                                while (scStream.hasNext()) {
                                    currentLine = scStream.nextLine();
                                    for (i = 0; i < numMonth; i++) {
                                        if(currentLine.substring(currentLine.indexOf("/")+1, currentLine.length()).equalsIgnoreCase(actMonth[i])) {//for a particular date
                                            currentLine = scStream.nextLine();

                                            for (j = 0; j < maxMovie; j++) {//for a particular movie
                                                if (movName[j] == null) {//if no entry of that movie in the array and that movie has not been stored
                                                    movName[j] = currentLine;
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            //thatSale = Double.parseDouble(currentLine);
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    movieShowed++;

                                                    break;
                                                } else if (currentLine.equalsIgnoreCase(movName[j])) {//if that movie has been stored in the list
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            //thatSale = Double.parseDouble(currentLine);
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    break;

                                                }

                                            }
                                            
                                                         
                                          break;  
                                        }
                                        
                                    }
                                    currentLine = scStream.nextLine();
                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            for (i = 0; i < movieShowed; i++) {
                                System.out.println("Movie " + (i+1) + ":" + movName[i]);
                                System.out.println("");
                                sumTotal = 0;
                                for (j = 0; j < numMonth; j++) {
                                    System.out.println("In " + monthNames[j] + " " + cal.get(Calendar.YEAR));
                                    unitTotal = 0;
                                    for (k = 0; k < numCinp; k++) {
                                        System.out.println("Cineplex: " + cineplexes.get(k).getCineplexName() + ", the sale is " + parSale[j][i][k] + " dollars.");
                                        unitTotal += parSale[j][i][k];
                                    }
                                    System.out.println("Total sales on this month: " + unitTotal);
                                    System.out.println("");
                                    sumTotal += unitTotal;
                                }
                                System.out.println("Total Sales for this movie: " + sumTotal);
                                System.out.println("");
                                System.out.println("");
                            }
                            break;
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("Enter your choice for period selection:");
                    System.out.println("(1)By day--first day of the month till current day");
                    System.out.println("(2)By month--from January till current month");
                    choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1: {                                             // Prints out the sales revenue report by cineplex by day
                            numDate1 = cal.get(Calendar.DAY_OF_MONTH);
                            double[][][] parSale = new double[numDate1][maxMovie][numCinp];
                            String[] movName = new String[maxMovie];
                            String[] actDate = new String[numDate1];
                            String[] cinName = new String[numCinp];
                           // cinName[0] = "Cineplex: 1";
                           // cinName[1] = "Cineplex: 2";
                           // cinName[2] = "Cineplex: 3";
                            
                            for(i =0; i<3; i++){
                                cinName[i] = cineplexes.get(i).getCineplexName();
                            }
                            for (i = 0; i < numDate1; i++) {
//						actDate[i] = (Integer.toString(date1 + i)) + "/" + "11";
                                actDate[i] = (Integer.toString(i + 1)) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
                            }
                            for (i = 0; i < numDate1; i++) {
                                for (j = 0; j < maxMovie; j++) {
                                    for (k = 0; k < numCinp; k++) {
                                        parSale[i][j][k] = 0;
                                    }
                                }
                            }
                            movieShowed = 0;
                            File file = new File("Sales.txt");
                            try {
                                Scanner scStream = new Scanner(file);
                                while (scStream.hasNext()) {
                                    currentLine = scStream.nextLine();
                                    for (i = 0; i < numDate1; i++) {
                                        if(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()).equalsIgnoreCase(actDate[i])) {//for a particular date
                                            currentLine = scStream.nextLine();

                                            for (j = 0; j < maxMovie; j++) {//for a particular movie
                                                if (movName[j] == null) {//if no entry of that movie in the array and that movie has not been stored
                                                    movName[j] = currentLine;
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    movieShowed++;

                                                    break;
                                                } else if (currentLine.equalsIgnoreCase(movName[j])) {//if that movie has been stored in the list
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    break;

                                                }

                                            }
                                           
                                          break;  
                                        }
                                    }
                                    currentLine = scStream.nextLine();
                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            for (i = 0; i < numCinp; i++) {
                                System.out.println(cineplexes.get(i).getCineplexName() + ":");
                                System.out.println("");
                                sumTotal = 0;
                                for (j = 0; j < numDate1; j++) {
                                    System.out.println("On " + actDate[j] + ":");
                                    unitTotal = 0;
                                    for (k = 0; k < movieShowed; k++) {
                                        System.out.println("For " + movName[k] + ", the sale is " + parSale[j][k][i] + " dollars.");
                                        unitTotal += parSale[j][k][i];
                                        }
                                        System.out.println("Total sale on this day: " + unitTotal);
                                        System.out.println("");
                                        sumTotal += unitTotal;
                                    }
                                    System.out.println("Total sales for this cineplex: " + sumTotal);
                                    System.out.println("");
                                    System.out.println("");
                                }

                            break;
                        }

                        case 2: {														// // Prints out the sales revenue report by cineplex by month
                            numMonth = cal.get(Calendar.MONTH) + 1;
                            double[][][] parSale = new double[numMonth][maxMovie][numCinp];
                            String[] movName = new String[maxMovie];
                            String[] actMonth = new String[numMonth];
                            String[] cinName = new String[numCinp];
                           // cinName[0] = "Cineplex: 1";
                           // cinName[1] = "Cineplex: 2";
                           // cinName[2] = "Cineplex: 3";
                            
                            for(i =0; i<3; i++){
                                cinName[i] = cineplexes.get(i).getCineplexName();
                            }
                            
                            for (i = 0; i < numMonth; i++) {
                                actMonth[i] = (Integer.toString(i + 1)) + "/" + cal.get(Calendar.YEAR);
                            }
                            for (i = 0; i < numMonth; i++) {
                                for (j = 0; j < maxMovie; j++) {
                                    for (k = 0; k < numCinp; k++) {
                                        parSale[i][j][k] = 0;
                                    }
                                }
                            }
                            movieShowed = 0;
                            File file = new File("Sales.txt");
                            try {
                                Scanner scStream = new Scanner(file);
                                while(scStream.hasNext()) {
                                    currentLine = scStream.nextLine();
                                    for (i = 0; i < numMonth; i++) {
                                        if(currentLine.substring(currentLine.indexOf("/")+1, currentLine.length()).equalsIgnoreCase(actMonth[i])) {//for a particular date
                                            currentLine = scStream.nextLine();

                                            for (j = 0; j < maxMovie; j++) {//for a particular movie
                                                if (movName[j] == null) {//if no entry of that movie in the array and that movie has not been stored
                                                    movName[j] = currentLine;
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    movieShowed++;

                                                    break;
                                                } else if (currentLine.equalsIgnoreCase(movName[j])) {//if that movie has been stored in the list
                                                    currentLine = scStream.nextLine();
                                                    for (k = 0; k < numCinp; k++) {
                                                        if (currentLine.equalsIgnoreCase(cinName[k])) {
                                                            currentLine = scStream.nextLine();
                                                            thatSale = Double.parseDouble(currentLine.substring(currentLine.lastIndexOf(" ")+1, currentLine.length()));
                                                            parSale[i][j][k] += thatSale;
                                                            break;
                                                        }
                                                    }
                                                    break;

                                                }

                                            }
                                            
                                           break; 
                                        }
                                    }
                                    currentLine = scStream.nextLine();
                                }

                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            for (i = 0; i < numCinp; i++) {
                                System.out.println(cineplexes.get(i).getCineplexName() + ":");
                                System.out.println("");
                                sumTotal = 0;
                                for (j = 0; j < numMonth; j++) {
                                    System.out.println("In " + monthNames[j] + " " + cal.get(Calendar.YEAR));
                                    unitTotal = 0;
                                    for (k = 0; k < movieShowed; k++) {
                                        System.out.println("For " + movName[k] + ", the sale is " + parSale[j][k][i] + " dollars.");
                                        unitTotal += parSale[j][k][i];
                                        }
                                        System.out.println("Total sale on this month: " + unitTotal);
                                        System.out.println("");
                                        sumTotal += unitTotal;
                                    }
                                    System.out.println("Total sales for this cineplex: " + sumTotal);
                                    System.out.println("");
                                    System.out.println("");
                                }
                            break;
                        }
                    }
                }
                break;

            }


        } while (choice1 != 3);



    }
}
