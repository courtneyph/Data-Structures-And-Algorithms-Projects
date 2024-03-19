package dateorganizer;

/**
 * A testbed for a binary heap implementation of a priority queue using 
 * various comparators to sort Gregorian dates
 * @author Duncan, Courtney Pham
 * @see Date, PQueueAPI, PQueue
 * <pre>
 * Date: 9-25-23
 * Course: csc 3102
 * File: DateOrganizer.java
 * Instructor: Dr. Duncan
 * </pre>
 */
 
import java.io.IOException;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Comparator; 

public class DateOrganizer
{
    /**
     * Gives the integer value equivalent to the day of the
     * week of the specified date 
     * @param d a date on the Gregorian Calendar
     * @return 0->Sunday, 1->Monday, 2->Tuesday, 3->Wednesday,
     * 4->Thursday, 5->Friday, 6->Saturday; otherwise, -1
     */
    public static int getDayNum(Date d)
    {
        String day = d.getDayOfWeek();
        switch (day) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
        }
        return -1;
    }
	public static void main(String[] args) throws IOException, PQueueException
    {
        String usage = "DateOrganizer <date-file-name> <sort-code>%n";
        usage += "sort-code: -2 -month-day-year%n";
        usage += "           -1 -year-month-day%n";
        usage += "            0 +weekDayNumber+monthName+day+year%n";
        usage += "            1 +year+month+day%n";
        usage += "            2 +month+day+year";
        if (args.length != 2) {
            System.out.println("Invalid number of command line arguments");
            System.out.printf(usage + "%n");
            System.exit(1);
        }
        String dateFile = args[0];
        int sortCode = Integer.parseInt(args[1]);
        String formatUsage = "";
        Comparator<Date> sort = (obj1, obj2) -> obj1.compareTo(obj2);
        switch (sortCode) {
            case -2:
                formatUsage = "-month-day-year";
                sort = (date1,date2) -> {
                    if (date1.getMonth() != date2.getMonth()) {
                        return Integer.compare(date1.getMonth(), date2.getMonth());
                    } else if (date1.getDay() != date2.getDay()) {
                        return Integer.compare(date1.getDay(), date2.getDay());
                    } else {
                        return Integer.compare(date1.getYear(),date2.getYear());
                    }
                };
                break;
            case -1:
                formatUsage = "-year-month-day";
                sort = (date1,date2) -> {
                    if (date1.getYear() != date2.getYear()) {
                        return Integer.compare(date1.getYear(), date2.getYear());
                    } else if (date1.getMonth() != date2.getMonth()) {
                        return Integer.compare(date1.getMonth(), date2.getMonth());
                    } else {
                        return Integer.compare(date1.getDay(),date2.getDay());
                    }
                };
                break;
            case 0:
                formatUsage = "+weekDayNumber+monthName+day+year";
                sort = (date1,date2) -> {
                    if (getDayNum(date1) != getDayNum(date2)) {
                        return Integer.compare(getDayNum(date2), getDayNum(date1));
                    } else if (date1.getMonth() != date2.getMonth()) {
                        if (date2.getMonthName().length() > 
                                date1.getMonthName().length())
                            return 1;
                        else if (date2.getMonthName().length() < 
                                date1.getMonthName().length())
                            return -1;
                        return 0;
                    } else if (date1.getDay() != date2.getDay()) {
                        return Integer.compare(date2.getDay(),date1.getDay());
                    } else {
                        return Integer.compare(date2.getYear(), date1.getYear());
                    }
                };
                break;
            case 1:
                formatUsage = "+year+month+day";
                sort = (date1,date2) -> {
                    if (date1.getYear() != date2.getYear()) {
                        return Integer.compare(date2.getYear(), date1.getYear());
                    } else if (date1.getMonth() != date2.getMonth()) {
                        return Integer.compare(date2.getMonth(), date1.getMonth());
                    } else {
                        return Integer.compare(date2.getDay(),date1.getDay());
                    }
                };
                break;
            case 2:
                formatUsage = "+month+day+year";
                sort = (date1,date2) -> {
                    if (date1.getMonth() != date2.getMonth()) {
                        return Integer.compare(date2.getMonth(), date1.getMonth());
                    } else if (date1.getDay() != date2.getDay()) {
                        return Integer.compare(date2.getDay(),date1.getDay());
                    } else {
                        return Integer.compare(date2.getYear(), date1.getYear());
                    }
                };
                break;
        }
        Scanner scanner = new Scanner(new FileReader(dateFile));
        PQueue pqueue = new PQueue(sort);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] dates = line.split("/");
            int month = Integer.parseInt(dates[0]);
            int day = Integer.parseInt(dates[1]);
            int year = Integer.parseInt(dates[2]);
            Date date = new Date(month,day,year);
            pqueue.insert(date);
        }
        System.out.printf("Dates from %s in %s Order%n%n", dateFile, formatUsage);
        while (!pqueue.isEmpty()) {
            Date temp = (Date) pqueue.remove();
            System.out.printf("%s, %s %d, %d%n", temp.getDayOfWeek(),
                    temp.getMonthName(),temp.getDay(),temp.getYear());
            
        }
    }
}