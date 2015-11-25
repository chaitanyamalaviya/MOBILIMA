/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.util.ArrayList;
import java.util.Calendar;
import static my.mobilima.MainApp.sc;

/**
 *
 * @author Tram Anh
 */
public class CreateUpdateShowtime {

    public static int createShowtime(ArrayList<Cineplex> cineplexes, ArrayList<Movie> movies) {
//        Calendar cal = Calendar.getInstance();
        String movieName;
        int cineplexID, cinemaID, index, daysFromToday, time;
        System.out.println("Create cinema showtimes...");
//        int cineplexID = sc.nextInt();
        System.out.println("Choose the cineplex: ");
        System.out.println("1. Cineleisure Orchard");
        System.out.println("2. The Cathay Cineplex");
        System.out.println("3. Causeway Point");
        String cineplexStr = sc.next();
        if (isNumeric(cineplexStr) == false || Integer.parseInt(cineplexStr) < 1 || Integer.parseInt(cineplexStr) > 3) {
            System.out.println("Invalid input. Function is exiting..");
            return -1;
        } else {
            cineplexID = Integer.parseInt(cineplexStr) - 1;
            System.out.println("Choose cinema to input:");
            for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                //this is to dynamically show cinema menu depending on number of cinemas (not always 3)
                System.out.println((i + 1) + ". Cinema " + (i + 1) + " (" + cineplexes.get(cineplexID).cinemas.get(i).getCinemaType() + ")");
            }
            String cinemaStr = sc.next();
            if (isNumeric(cinemaStr) == false || Integer.parseInt(cinemaStr) < 1 || Integer.parseInt(cinemaStr) > cineplexes.get(cineplexID).cinemas.size()) {
                System.out.println("Invalid input. Function is exiting..");
                return -1;
            } else {
                cinemaID = Integer.parseInt(cinemaStr) - 1;

//                sc.nextLine();
                CreateUpdateMovie.listMovies(movies);
                System.out.println("Enter the index of the movie to create showtime: ");
                String movieStr = sc.next();
                if (isNumeric(movieStr) == false) {
                    System.out.println("Invalid input. Function is exiting..");
                    return -1;
                } else {
                    index = Integer.parseInt(movieStr) - 1;
                    if (index >= movies.size() || index < 0) {
                        System.out.println("Invalid input. Function is exiting.");
                        return -1;
                    } else {
                        //main function for this case
                        movieName = movies.get(index).getName();
//                        movieType = movies.get(index).getType();
//                       if (validateMovie(movies, movieName, movieType) == false) {
//                            System.out.println("The movie and movie type are not in database. Please create movie first!");
//                            return -1;
//                        } else {
                        System.out.println("Current empty timeslot for Cineplex " + (cineplexID + 1) + "- Cinema " + (cinemaID + 1));
                        cineplexes.get(cineplexID).cinemas.get(cinemaID).checkEmptySlot();
                        System.out.println("Enter day number");
                        String dayStr = sc.next();
                        if (isNumeric(dayStr) == false || Integer.parseInt(dayStr) < 0 || Integer.parseInt(dayStr) > 6) {
                            System.out.println("Invalid input. Function is exiting..");
                            return -1;
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
                                return -1;
                            } else {
                                time = Integer.parseInt(timeStr) - 1;
                                //        cal.add(Calendar.DAY_OF_YEAR, daysAhead);
                                //        cal.set(Calendar.HOUR_OF_DAY, timeSlot);

                                cineplexes.get(cineplexID).cinemas.get(cinemaID).cinemaBooking(daysFromToday, time, movieName);
                                System.out.println("Current empty timeslot: ");
                                cineplexes.get(cineplexID).cinemas.get(cinemaID).checkEmptySlot();
                                //        CineplexFile.writeToFile(cineplexID, movies.get(0), cal, seatingPlan);
                                return 0;
                            }
                        }

                    }
                }
            }
        }
    }

    public static int updateShowtime(ArrayList<Cineplex> cineplexes) {
        int cineplexID, cinemaID, daysFromToday, time;
        System.out.println("Choose the cineplex: ");
        System.out.println("1. Cineleisure Orchard");
        System.out.println("2. The Cathay Cineplex");
        System.out.println("3. Causeway Point");
        String cineplexStr = sc.next();
        if (isNumeric(cineplexStr) == false || Integer.parseInt(cineplexStr) < 1 || Integer.parseInt(cineplexStr) > 3) {
            System.out.println("Invalid input. Function is exiting..");
            return -1;
        } else {
            cineplexID = Integer.parseInt(cineplexStr) - 1;
            System.out.println("Choose cinema to input:");
            for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                //this is to dynamically show cinema menu depending on number of cinemas (not always 3)
                System.out.println((i + 1) + ". Cinema " + (i + 1) + " (" + cineplexes.get(cineplexID).cinemas.get(i).getCinemaType() + ")");
            }
            String cinemaStr = sc.next();
            if (isNumeric(cinemaStr) == false || Integer.parseInt(cinemaStr) < 1 || Integer.parseInt(cinemaStr) > cineplexes.get(cineplexID).cinemas.size()) {
                System.out.println("Invalid input. Function is exiting..");
                return -1;
            } else {
                cinemaID = Integer.parseInt(cinemaStr) - 1;

                System.out.println("Current occupied showtime for Cineplex " + (cineplexID + 1) + "- " + cineplexes.get(cineplexID).getCineplexName() + "- Cinema " + (cinemaID + 1));
                if (cineplexes.get(cineplexID).cinemas.get(cinemaID).showOccupiedSlot() == false) {
                    System.out.println("There is no showtime for this cinema. Function is exiting..");
                    return -1;
                }

                System.out.println("Enter day number");
                String dayStr = sc.next();
                if (isNumeric(dayStr) == false || Integer.parseInt(dayStr) < 0 || Integer.parseInt(dayStr) > 6) {
                    System.out.println("Invalid input. Function is exiting..");
                    return -1;
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
                        return -1;
                    } else {
                        time = Integer.parseInt(timeStr) - 1;
                        System.out.println("Please choose:");
                        System.out.println("1. To update the movie name at that particular timeslot");
                        System.out.println("2. To change the showtime to another showtime and delete the old timing");
                        String choice = sc.next();

                        switch (choice) {
                            case "1":
                                System.out.println("Current movie name: " + cineplexes.get(cineplexID).cinemas.get(cinemaID).getMovieName(daysFromToday, time));
                                sc.nextLine();
                                System.out.println("New movie name: ");
                                String newMovieName = sc.nextLine();
                                cineplexes.get(cineplexID).cinemas.get(cinemaID).setMovieName(daysFromToday, time, newMovieName);
                                break;
                            case "2":
                                String movieName = cineplexes.get(cineplexID).cinemas.get(cinemaID).getMovieName(daysFromToday, time);
                                cineplexes.get(cineplexID).cinemas.get(cinemaID).setTimeSlotAvailable(daysFromToday, time);  //delete the timeslot <-- setting it availanle

                                System.out.println("Current empty slots:");
                                cineplexes.get(cineplexID).cinemas.get(cinemaID).checkEmptySlot();
                                System.out.println("Enter day number");
                                dayStr = sc.next();
                                if (isNumeric(dayStr) == false || Integer.parseInt(dayStr) < 0 || Integer.parseInt(dayStr) > 6) {
                                    System.out.println("Invalid input. Function is exiting..");
                                    return -1;
                                } else {
                                    daysFromToday = Integer.parseInt(dayStr);
                                    System.out.println("Enter time slot:");
                                    System.out.println("1. 10am");
                                    System.out.println("2. 1pm");
                                    System.out.println("3. 4pm");
                                    System.out.println("4. 7pm");
                                    System.out.println("5. 10pm");
                                    timeStr = sc.next();
                                    if (isNumeric(timeStr) == false || Integer.parseInt(timeStr) < 1 || Integer.parseInt(timeStr) > 5) {
                                        System.out.println("Invalid input. Function is exiting..");
                                        return -1;
                                    } else {
                                        time = Integer.parseInt(timeStr) - 1;

                                        cineplexes.get(cineplexID).cinemas.get(cinemaID).cinemaBooking(daysFromToday, time, movieName);
                                        System.out.println("Current booking: ");
                                        cineplexes.get(cineplexID).cinemas.get(cinemaID).showOccupiedSlot();
                                    }
                                }
                                break;
                            default:
                                System.out.println("Invalid input. Function is exiting..");
                        }
                    }
                }
            }
        }
        return 0;
    }

    public static int deleteShowtime(ArrayList<Cineplex> cineplexes, ArrayList<Movie> movies) {
        int cineplexID, cinemaID, daysFromToday, time, index;
        String movieName;

        System.out.println("Choose the cineplex to delete the showtime from: ");
        System.out.println("1. Cineleisure Orchard");
        System.out.println("2. The Cathay Cineplex");
        System.out.println("3. Causeway Point");
        String cineplexStr = sc.next();
        if (isNumeric(cineplexStr) == false || Integer.parseInt(cineplexStr) < 1 || Integer.parseInt(cineplexStr) > 3) {
            System.out.println("Invalid input. Function is exiting..");
            return -1;
        } else {
            cineplexID = Integer.parseInt(cineplexStr) - 1;

            System.out.println("Please choose following option");
            System.out.println("1. Delete showtime by movie");
            System.out.println("2. Delete showtime by cinema and timing");
            String choice = sc.next();

            switch (choice) {
                case "1":
                    CreateUpdateMovie.listMovies(movies);
                    ///error checking here
                    System.out.println("Enter the index of the movie to create showtime: ");
                    String movieStr = sc.next();
                    if (isNumeric(movieStr) == false) {
                        System.out.println("Invalid input. Function is exiting..");
                        return -1;
                    } else {
                        index = Integer.parseInt(movieStr) - 1;
                        if (index >= movies.size() || index < 0) {
                            System.out.println("Invalid input. Function is exiting.");
                            return -1;
                        } else {
                            //main function for this case
                            movieName = movies.get(index).getName();

                            //show showtime of that particular movieName
                            for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                                System.out.println("Cinema " + (i+1) + ":");
                                cineplexes.get(cineplexID).cinemas.get(i).showOccupiedSlot(movieName);
                            }
                        }
                    }
                    break;

                case "2":
                    for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                        System.out.println("Cinema " + (i+1) + ":");
                        cineplexes.get(cineplexID).cinemas.get(i).showOccupiedSlot();
                    }
            }

            System.out.println("Choose cinema to input:");
            //error checking here
            for (int i = 0; i < cineplexes.get(cineplexID).cinemas.size(); i++) {
                //this is to dynamically show cinema menu depending on number of cinemas (not always 3)
                System.out.println((i + 1) + ". Cinema " + (i + 1) + " (" + cineplexes.get(cineplexID).cinemas.get(i).getCinemaType() + ")");
            }
            String cinemaStr = sc.next();
            if (isNumeric(cinemaStr) == false || Integer.parseInt(cinemaStr) < 1 || Integer.parseInt(cinemaStr) > cineplexes.get(cineplexID).cinemas.size()) {
                System.out.println("Invalid input. Function is exiting..");
                return -1;
            } else {
                cinemaID = Integer.parseInt(cinemaStr) - 1;


                if(cineplexes.get(cineplexID).cinemas.get(cinemaID).showOccupiedSlot() == false){
                    System.out.println("There is no showtime for the cinema. Function is exiting..");
                    return -1;
                }
                    
                System.out.println("Enter day number");
                String dayStr = sc.next();
                if (isNumeric(dayStr) == false || Integer.parseInt(dayStr) < 0 || Integer.parseInt(dayStr) > 6) {
                    System.out.println("Invalid input. Function is exiting..");
                    return -1;
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
                        return -1;
                    } else {
                        time = Integer.parseInt(timeStr) - 1;
                        cineplexes.get(cineplexID).cinemas.get(cinemaID).setTimeSlotAvailable(daysFromToday, time);
                    }
                }
            }
        }
        return 0;
    }

//    private static boolean validateMovie(ArrayList<Movie> movies, String movieName, String movieType) {
//        //check if movie name and movie type is in the database
//        //if yes, return true
//        //else, return false
//        for (int i = 0; i < movies.size(); i++) {
//            if (movieName.equalsIgnoreCase(movies.get(i).getName()) == true && movieType.equalsIgnoreCase(movies.get(i).getType()) == true) {
//                return true;
//            }
//        }
//        return false;
//    }
    private static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
