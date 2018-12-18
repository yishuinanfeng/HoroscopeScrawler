package main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getTimeZone() {
        Date as = SpiderConfig.isToday ? new Date(new Date().getTime()) : new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * SpiderConfig.aheadDayCount);
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(as);
    }
}
