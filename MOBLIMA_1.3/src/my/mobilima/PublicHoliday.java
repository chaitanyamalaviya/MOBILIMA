/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobilima;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
//import org.apache.commons.lang3.time.DateUtils; //to compare between 2 dates

/**
 *
 * @author Tram Anh
 */
public class PublicHoliday {

//    public static Calendar publicHol;
    static Scanner sc = new Scanner(System.in);
//
//    public PublicHoliday() {
//        publicHol = Calendar.getInstance();
//        publicHol.add(Calendar.DAY_OF_YEAR, -2);   //this is to set it different from today & holiday eve
//    }

    public static void setPublicHol() throws IOException {
        System.out.println("Set coming public holiday..");
        System.out.println("Enter day [DD]: ");
        int date = sc.nextInt();
        System.out.println("Enter month [MM]: ");
        int month = sc.nextInt();
        System.out.println("Enter year [YYYY]: ");
        int year = sc.nextInt();

        Calendar publicHol = Calendar.getInstance();
        publicHol.set(Calendar.DAY_OF_MONTH, date);
        publicHol.set(Calendar.MONTH, month-1);   //Jan is 0, Dec is 11
        publicHol.set(Calendar.YEAR, year);
        
//        System.out.println(publicHol.getTime());

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        PrintWriter writePublicHol = new PrintWriter(new FileWriter("PublicHolidays.txt", true));
        writePublicHol.println(df.format(publicHol.getTime()));
        writePublicHol.close();
        
        System.out.println("The public holiday has been added successfully. Please check PublicHol.txt for details.");
    }

    public static boolean checkHoliday(Calendar today) throws FileNotFoundException, IOException, ParseException {
        BufferedReader reader = new BufferedReader(new FileReader("PublicHolidays.txt"));

        String currentLine;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal_txt = Calendar.getInstance();
        while ((currentLine = reader.readLine()) != null) {
            cal_txt.setTime(sdf.parse(currentLine)); //cal is the Calendar form, extracted from the PublicHolidays text file
            if(checkIndividualHoliday(cal_txt, today) == true)
                return true;
        }
        reader.close();
        return false;
    }

    private static boolean checkIndividualHoliday(Calendar cal_txt, Calendar today) {
        //check if date is on public holiday or on public holiday eve (date + 1 == public hol)
        if (today.get(Calendar.YEAR) == cal_txt.get(Calendar.YEAR) && (today.get(Calendar.DAY_OF_YEAR) == cal_txt.get(Calendar.DAY_OF_YEAR) || today.get(Calendar.DAY_OF_YEAR) + 1 == cal_txt.get(Calendar.DAY_OF_YEAR))) {
            return true;
        }
        return false;

    }
}
