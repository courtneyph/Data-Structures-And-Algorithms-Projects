package dateorganizer;

/**
 * Specifies operations on Gregorian Dates
 * @author Duncan, Courtney Pham <br>
 * <pre>
 * File: DateAPI.java<br>
 * Course: csc 3102
 * Project: # 1
 * Instructor: Dr. Duncan
 * </pre>
 */
 
public interface DateAPI
{
    /**
     * returns the name of the current month
     * @return the name of the current month
     */
    String getMonthName();
   /**
    * gives an integer representing the month
    * @return a number representing the month of this date
    */
    
   int getMonth();
   
   /**
    * gives an integer representing the day
    * @return the day of this date 
    */
   int getDay();
   
   /**
    * gives an integer representing the year
    * @return the year of this date
    */
   int getYear();
   
   /**
    * determines the name of the day of the week of this date
    * @return the name of the day of the week of this date
    */
   String getDayOfWeek();
   
   /**
    * gives the date in the format mm/dd/yyyy
    * @return a string representation of this date in the format mm/dd/yyyy
    */
    @Override
   String toString();
    
    /**
     * determines whether this date is the same as the specified date
     * @param d a Gregorian date
     * @return true when this date is the same as the specified data; 
     * otherwise, false
     */
    boolean equals(Date d);
    /**
     * compares this Gregorian date and the specified Gregorian date
     * @param d a Gregorian date
     * @return a number less than 0 when this Gregorian date comes before
     * the specified Gregorian date; a number greater than 0 when this
     * Gregorian date comes after the specified Gregorian date; otherwise,
     * 0.
     */
    public int compareTo(Date d);
}
