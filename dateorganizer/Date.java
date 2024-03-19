package dateorganizer;

/**
 * Describes a date on the Gregorian Calendar
 * @author Duncan, Courtney Pham <br>
 * @see DateAPI
 * <pre>
 * File: Date.java<br>
 * Course: csc 3102
 * Project: # 1
 * Instructor: Dr. Duncan
 * </pre>
 */

    public class Date implements DateAPI,Comparable<Date>
    {
    /**
        * the month 
        */
    private int month;
    /**
        * the day
        */
    private int day;
    /**
        * the year
        */
    private int year;
    
    /**
        * creates an object representing January 1, 1970
        */
    public Date()
    {
        month = 1;
        day = 1;
        year = 1970;
    }
    /**
        * creates a valid Gregorian Date
        * @param mm the month
        * @param dd the day
        * @param yyyy the year
        * @throws IllegalArgumentException when <br>
        * <pre>
        * 1. year is before 1583
        * 2. month not in the range 1-12
        * 3. day not in the range 1-31
        * 4. month is September, April, June or November and day is 31
        * 5. month is February, the year is a leap year and day is greater
        *    than 29
        * 6. month is February, the year is not a leap year and the day is
        *    greater than 28
        * </pre>
        */
    public Date(int mm, int dd, int yyyy) throws IllegalArgumentException
    {
        boolean leap = (yyyy % 400 == 0)||(yyyy % 4 == 0 && yyyy % 100 != 0);
        if (yyyy < 1583)
            throw new IllegalArgumentException("Date(int,int,int): only dates after 1582 are allowed.");
        if (mm < 0 || mm > 12)
            throw new IllegalArgumentException("Date(int,int,int): month must be between 1 and 12.");
        if (dd < 0 || dd > 31)
            throw new IllegalArgumentException("Date(int,int,int): day must be between 1 and 31.");    
        if ((mm==9 || mm==4 || mm==6 || mm==11) && dd == 31)
            throw new IllegalArgumentException("Date(int,int,int): this month has only 30 days.");
        if (mm==2)
        {
            if (leap)
            {
                if (dd >29)
                    throw new IllegalArgumentException("Date(int,int,int): February has only 29 days in a leap year.");
            }
            else
            {
                if (dd >28)
                    throw new IllegalArgumentException("Date(int,int,int): February has only 29 days in a leap year.");
            }
        }   
        year = yyyy;
        month = mm;
        day = dd;
    }
    
    /**
        * returns the name of the current month
        * @return the name of the current month
        */
    public String getMonthName()
    {
        String[] names ={"January", "February", "March", "April",
                            "May", "June", "July", "August",
                            "September", "October", "November", "Decenber"};       
        return names[month-1];
    }
    
    /**
        * gives an integer representing the month
        * @return a number representing the month of this date
        */
    public int getMonth()
    {
        return month;
    }
    
    /**
        * gives an integer representing the day
        * @return the day of this date 
        */
    public int getDay()
    {
        return day;
    }
    
    /**
        * gives an integer representing the year
        * @return the year of this date
        */
    public int getYear()
    {
        return year;
    }
    
    /**
        * determines the name of the day of the week of this date
        * @return the name of the day of the week of this date
        */
    public String getDayOfWeek()
    {
        int m;
        boolean leap = (year % 400 == 0)||(year % 4 == 0 && year % 100 != 0);
        if (month == 1)
        {
            if (leap)
                m = 5;
            else
                m = 0;
        }
        else if (month == 2)
        {
            if (leap)
                m = 2;
            else
                m = 3;
        }
        else if (month == 10)
            m = 0;
        else if (month == 5)
            m = 1;
        else if (month == 8)
            m = 2;
        else if (month == 3 || month == 11)
            m = 3;
        else if (month == 6)
            m = 4;
        else if (month == 9 || month == 12)
            m = 5;
        else
            m = 6;
        int v = year % 100;
        String[] weekDay = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return weekDay[(2*(3 - (year / 100 % 4)) + v + v/4 + m + day) % 7];
    }
    
    /**
        * gives the date in the format mm/dd/yyyy
        * @return a string representation of this date in the format mm/dd/yyyy
        */
        @Override
    public String toString()
    {
        return String.format("%02d/%02d/%d",month,day,year);
    }
        
        /**
         * determines whether this date is the same as the specified date
         * @param d a Gregorian date
         * @return true when this date is the same as the specified data; 
         * otherwise, false
         */
        public boolean equals(Date d)
        {
            return month==d.month && day==d.day && year==d.year;
        }
        /**
         * compares this Gregorian date and the specified Gregorian date
         * @param d a Gregorian date
         * @return a number less than 0 when this Gregorian date comes before
         * the specified Gregorian date; a number greater than 0 when this
         * Gregorian date comes after the specified Gregorian date; otherwise,
         * 0.
         */
        public int compareTo(Date d)
        {
            return (10000*year+100*month+day) - (10000*d.year+100*d.month+d.day);
        }
    }