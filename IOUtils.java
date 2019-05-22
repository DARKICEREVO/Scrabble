import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Class that provides static functions for doing terminal input
 *
 * Created by Sally Goldin, 30 April 2014, for CPE113 Use to simplify Java lab
 * exercises.
 */
public class IOUtils {
    /**
     * Asks for a string and returns it as the value of the function
     * 
     * @param prompt String to print, telling which coordinate
     * @return The string the user entered (maximum 100 chars long)
     */
    public static String getString(String prompt) 
    {
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        GameDisplay.displayMessageInline(prompt);
        try {
            readBytes = System.in.read(buffer, 0, 200);
        } catch (IOException ioe) {
            GameDisplay.displayMessageInline("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        int pos = inputString.indexOf("\r\n");
        if (pos > 0)
            inputString = inputString.substring(0, pos);
        return inputString;
    }
    public static String getYesOrNo(String prompt) 
    {
        String inputString = getString(prompt);
        while (!inputString.matches("[YNyn]")) 
        {
            GameDisplay.displayMessage("Please input Y or N");
            inputString = getString(prompt);
        }
        return inputString;
    }
    /**
     * Asks for an integer and returns it as the value of the function
     * 
     * @param prompt String to print, telling which coordinate
     * @return value entered. If not an integer, prints an error message and returns
     *         -999
     */
    public static int getInteger(String prompt) {
        int value = -999;
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        GameDisplay.displayMessageInline(prompt);
        try {
            readBytes = System.in.read(buffer, 0, 200);
        } catch (IOException ioe) {
            GameDisplay.displayMessageInline("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        try {
            int pos = inputString.indexOf("\r\n");
            if (pos > 0)
                inputString = inputString.substring(0, pos);
            value = Integer.parseInt(inputString);
        } catch (NumberFormatException nfe) {
            GameDisplay.displayMessageInline("Bad number entered");
        }
        return value;
    }
     /**
     * Asks for an integer that is between min and maxand returns it as the value of the function
     * 
     * @param prompt String to print, telling which coordinate
     * @param min    Minimum of input
     * @param max    Maximum of input
     * @return value entered. If not an integer, prints an error message and returns
     *         -999
     */
    public static int getIntegerInRange(String prompt,int min,int max)
    {
        int userInput = getInteger(prompt);
        while(userInput < min || userInput > max)
        {   
            GameDisplay.displayMessageInline("Input out of bound (min = "+min+" max = "+max+" )");
            userInput = getInteger(prompt);
        }
        return userInput;
    }
    /**
     * Reads a string and returns it as the value of the function, without any
     * prompt. Remove the newline before returning.
     * 
     * @param prompt String to print, telling which coordinate
     * @return The string the user entered (maximum 100 chars long)
     */
    public static String getBareString() {
        String inputString;
        int readBytes = 0;
        byte buffer[] = new byte[200];
        try {
            readBytes = System.in.read(buffer, 0, 200);
        } catch (IOException ioe) {
            GameDisplay.displayMessageInline("Input/output exception - Exiting");
            System.exit(1);
        }
        inputString = new String(buffer);
        int pos = inputString.indexOf("\n");
        if (pos > 0)
            inputString = inputString.substring(0, pos);
        return inputString;
    }

    /**
     * Creates and returns a string with the current date and time, to use as a time
     * stamp.
     * 
     * @return date/time string in the form "yyyy-mm-dd hh:mm:ss"
     */
    public static String getDateTime() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(now);
    }
}