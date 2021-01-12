package hr.katarina.library.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addDays(Date startDate, int numberOfDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }
}
