package misc.calendar;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * Author: Abrackadabra
 * Date: 24/11/12
 * Time: 17:47
 */
public class CalendarPrinter {
    public static void main(String[] args) {
        new CalendarPrinter().run(args);
    }

    Calendar calendar = Calendar.getInstance();
    boolean showWeekNumber = false;
    int month = calendar.get(Calendar.MONTH);
    int year = calendar.get(Calendar.YEAR);

    void run(String[] args) {



    }
}
