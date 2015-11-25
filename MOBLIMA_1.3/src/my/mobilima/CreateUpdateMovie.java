/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.util.ArrayList;
import static my.mobilima.MainApp.sc;

/**
 *
 * @author Tram Anh
 */
public class CreateUpdateMovie {

    
    public static void listMovies(ArrayList<Movie> movies) {
        //list all movies that are showing at the moment
        //It will not print the ones that are not showing anymore (status == "End Of Showing"
        System.out.println("List of movies: ");
        if (!movies.isEmpty()) {
            for (int i = 0; i < movies.size(); i++) {
                if (!movies.get(i).getStatus().equalsIgnoreCase("End Of Showing")) {
                    System.out.print((i+1) + ". " + movies.get(i).getName());
                    if(movies.get(i).getBlockbuster())
                        System.out.print("* ");
                    System.out.println("(" + movies.get(i).getType() + ") - " + movies.get(i).getStatus());
                }
            }
            System.out.println("");
        } else {
            System.out.println("There is no movie in the database");
        }
    }

    public static void listUpcomingMovies(ArrayList<Movie> movies) {
        //List all the movies that have the status of "Coming soon"
        System.out.println("List of movies: ");
        if (!movies.isEmpty()) {
            for (int i = 0; i < movies.size(); i++) {
                if (movies.get(i).getStatus().equalsIgnoreCase("Coming soon")) {
                    System.out.println(i + ". " + movies.get(i).getName() + " (" + movies.get(i).getType() + ") - " + movies.get(i).getStatus());
                }
            }
        } else {
            System.out.println("There is no movie in the database");
        }
    }

    
    public static boolean listMovieShowtime(String movieName, ArrayList<Cineplex> cineplexes) {
        //list all the showtime of a particular movie
        //return boolean so that we will know if there is any showtime craeted for this movie at all
        boolean showtimeFlag = false;
        boolean cineplexFlag = false;  //flag to see if there is showtime for a cineplex at all (UI purpose)
        for (int i = 0; i < 3; i++) {
            System.out.println("Cineplex " + (i + 1)+". "+ cineplexes.get(i).getCineplexName());
            for (int j = 0; j < cineplexes.get(i).cinemas.size(); j++) {
                if (cineplexes.get(i).cinemas.get(j).showOccupiedSlot(movieName)) {
                    System.out.println("    Cinema " + (j + 1) + " (" + cineplexes.get(i).cinemas.get(j).getCinemaType() + ")");
                    showtimeFlag = true;
                    cineplexFlag = true;
                }
            }
            if(cineplexFlag == false)
                System.out.println("No showtime for this cineplex.");
            else
                cineplexFlag = false;
            System.out.println("");
        }
        return showtimeFlag;
    }

    public static int createMovie(ArrayList<Movie> movies) {
        //This function will create a new movie to the ArrayList of Movie objects
        listMovies(movies);
        sc.nextLine();
        System.out.println("Enter the movie name to input: ");
        String movieName = sc.nextLine();
        movies.add(new Movie(movieName));

        System.out.println("Choose movie type: ");
        System.out.println("1. Digital");
        System.out.println("2. 3D");

        String typeChoice = sc.next();
        String movieType;
        switch (typeChoice) {
            case "1":
                movieType = "Digital";
                break;
            case "2":
                movieType = "3D";
                break;
            default:
                System.out.println("Wrong input. Function exiting..");
                movies.remove(movies.size() - 1);
                return -1;
        }

        if (!movies.isEmpty() && validateMovie(movies, movieName, movieType) == false) {
            System.out.println("The movie is already in database");
            System.out.println("Function is exiting..");
            return -1;
        } else {
            System.out.println("Choose movie rating (PG, M16, M18, R):");
            System.out.println("1. G (General)");
            System.out.println("2. PG (Parental Guidance)");
            System.out.println("3. PG13");
            System.out.println("4. NC16");
            System.out.println("5. M18");
            System.out.println("6. R21");
            String ratingChoice = sc.next();
            String movieRating;
            switch (ratingChoice) {
                case "1":
                    movieRating = "G";
                    break;
                case "2":
                    movieRating = "PG";
                    break;
                case "3":
                    movieRating = "PG13";
                    break;
                case "4":
                    movieRating = "NC16";
                    break;
                case "5":
                    movieRating = "M18";
                    break;
                case "6":
                    movieRating = "R21";
                    break;
                default:
                    System.out.println("Wrong input. Function is exiting..");
                    movies.remove(movies.size() - 1);
                    return -1;
            }

            System.out.println("Enter movie status: ");
            System.out.println("1. Coming Soon");
            System.out.println("2. Now Showing");
            System.out.println("3. End of Showing");
            String statusChoice = sc.next();
            String movieStatus;
            switch (statusChoice) {
                case "1":
                    movieStatus = "Coming Soon";
                    break;
                case "2":
                    movieStatus = "Now Showing";
                    break;
                case "3":
                    movieStatus = "End of Showing";
                    break;
                default:
                    System.out.println("Invalid input. Function is exiting..");
                    movies.remove(movies.size() - 1);
                    return -1;
            }

            if (movieStatus.equalsIgnoreCase("End of showing") == true) {
                movies.remove(movies.size() - 1);
                System.out.println("Movie is not added because movie status is End of Showing.");
                return -1;
            } else {
                System.out.println("Is this movie a blockbuster? (y/n)");
                System.out.println("1. Yes");
                System.out.println("2. No");
                String bbChoice = sc.next();
                switch (bbChoice) {
                    case "1":
                        movies.get(movies.size() - 1).setBlockbuster(true);
                        break;
                    case "2":
                        movies.get(movies.size() - 1).setBlockbuster(false);
                        break;
                    default:
                        System.out.println("Invalid input. Function is exiting..");
                        movies.remove(movies.size() - 1);
                        return -1;
                }
                movies.get(movies.size() - 1).setMovieProperty(movieType, movieRating, movieStatus);
            }
            sc.nextLine();
            return 0;
        }

    }

    public static int deleteMovie(ArrayList<Movie> movies, ArrayList<Cineplex> cineplexes) {
        //This function will delete a movie from the ArrayList of Movie objects
        //as well as deleting all the showtimes in the database that is showing this movie
        listMovies(movies);

        System.out.println("Enter the index of movie that you want to remove: ");
        String removeStr = sc.next();
        String movieNameToRemove;
        int remove;
        try {
            remove = Integer.parseInt(removeStr) - 1;
            if (remove >= movies.size()) {
                System.out.println("Invalid input.");
                return -1;
            } else {
                movieNameToRemove = movies.get(remove).getName();
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Function is exiting.");
            return -1;
        }
        //delete from cinema showtime
        for (int i = 0; i < cineplexes.size(); i++) {
            for (int j = 0; j < cineplexes.get(i).cinemas.size(); j++) {
                cineplexes.get(i).cinemas.get(j).setTimeSlotAvailable(movieNameToRemove);
            }
        }

        //remove from ArrayList
        movies.remove(remove);
        System.out.println("The movie is successfully deleted.");
        return 0;
    }

    public static int updateMovieDetails(ArrayList<Movie> movies, ArrayList<Cineplex> cineplexes) {
        //based on Movie class, allow users to change the attributes
        listMovies(movies);
        System.out.println("Enter the index of the movie that you want to update");
        String indexStr = sc.next();
        int index;
        try {
            index = Integer.parseInt(indexStr) - 1;
            if (index >= movies.size()) {
                System.out.println("Invalid input.");
                return -1;
            } else {
                System.out.println("Current movie properties:");
                System.out.println("1. Name: " + movies.get(index).getName());
                System.out.println("2. Type: " + movies.get(index).getType());
                System.out.println("3. Status: " + movies.get(index).getStatus());
                System.out.println("4. Rating: " + movies.get(index).getRating());
                System.out.println("5. Blockbuster: " + movies.get(index).getBlockbuster());
                System.out.println("Enter 1-5 to update the movie property (only 1 index at a time):");
                String movieProp = sc.next();
                switch (movieProp) {
                    case "1":
                        System.out.println("Enter the new name: ");
                        String newName = sc.nextLine();
                        movies.get(index).setName(newName);
                        break;
                    case "2":
                        System.out.println("Choose new movie type");
                        System.out.println("1. Digital");
                        System.out.println("2. 3D");

                        String typeChoice = sc.next();
                        String movieType;
                        switch (typeChoice) {
                            case "1":
                                movieType = "Digital";
                                break;
                            case "2":
                                movieType = "3D";
                                break;
                            default:
                                System.out.println("Wrong input. Function exiting..");
                                return -1;
                        }
                        movies.get(index).setType(movieType);
                        break;
                    case "3":
                        System.out.println("Choose new move status: ");
                        System.out.println("1. Coming Soon");
                        System.out.println("2. Now Showing");
                        System.out.println("3. End of Showing (Note: this will delete movie from movie listing as well as all "
                                + "current timeslot of this movie.");
                        String statusChoice = sc.next();
                        String movieStatus;
                        switch (statusChoice) {
                            case "1":
                                movieStatus = "Coming Soon";
                                break;
                            case "2":
                                movieStatus = "Now Showing";
                                break;
                            case "3":
                                movieStatus = "End of Showing";
                                //delete from cinema showtime
                                for (int i = 0; i < cineplexes.size(); i++) {
                                    for (int j = 0; j < cineplexes.get(i).cinemas.size(); j++) {
                                        cineplexes.get(i).cinemas.get(j).setTimeSlotAvailable(movies.get(index).getName());
                                    }
                                }
                                //remove from ArrayList
                                movies.remove(index);
                                System.out.println("The movie is successfully deleted.");
                                break;
                            default:
                                System.out.println("Invalid input. Function is exiting..");
                                return -1;
                        }
                        movies.get(index).setStatus(movieStatus);
                        break;
                    case "4":
                        System.out.println("Choose new rating: ");
                        System.out.println("1. G (General)");
                        System.out.println("2. PG (Parental Guidance)");
                        System.out.println("3. PG13");
                        System.out.println("4. NC16");
                        System.out.println("5. M18");
                        System.out.println("6. R21");
                        String ratingChoice = sc.next();
                        String movieRating;
                        switch (ratingChoice) {
                            case "1":
                                movieRating = "G";
                                break;
                            case "2":
                                movieRating = "PG";
                                break;
                            case "3":
                                movieRating = "PG13";
                                break;
                            case "4":
                                movieRating = "NC16";
                                break;
                            case "5":
                                movieRating = "M18";
                                break;
                            case "6":
                                movieRating = "R21";
                                break;
                            default:
                                System.out.println("Wrong input. Function is exiting..");
                                return -1;
                        }
                        movies.get(index).setRating(movieRating);
                        break;
                    case "5":
                        System.out.println("Set new blockbuster status: ");
                        System.out.println("1. Blockbuster");
                        System.out.println("2. Not blockbuster");
                        String bbChoice = sc.next();
                        switch (bbChoice) {
                            case "1":
                                movies.get(index).setBlockbuster(true);
                                break;
                            case "2":
                                movies.get(index).setBlockbuster(false);
                                break;
                            default:
                                System.out.println("Invalid input. Function is exiting..");
                                return -1;
                        }
                        break;
                    default:
                        System.out.println("Invalid input. Function is exiting..");
                        return -1;
                }
                return 0;
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input. Function is exiting.");
            return -1;
        }
    }

    public static boolean validateMovie(ArrayList<Movie> movies, String movieName, String movieType) {
        //This function will validate if a given movie is in the database or not
        for (int i = 0; i < movies.size(); i++) {
            if (movieName.equalsIgnoreCase(movies.get(i).getName()) == true && movieType.equalsIgnoreCase(movies.get(i).getType()) == true) {
//                System.out.println("The movie is already in database.");
                return false;
            }
        }
        return true;
    }
}
