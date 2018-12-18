package horoscopeProcessor;

import main.HoroscopeModel;
import main.SpiderConfig;
import pipeLine.AbstractPipeLine;
import pipeLine.MonthPipeLine;
import pipeLine.TodayPipeLine;
import us.codecraft.webmagic.Page;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class MonthProcessor extends AbstractProcessor {
    private static final String[] monthList = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};

    private static final String MONTHLY_BASE_URL = "https://www.tarot.com/horoscopes/";

    private HoroscopeModel horoscopeModel;

    public MonthProcessor(String horoscope, CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(horoscope, latch);
        this.horoscopeModel = horoscopeModel;
    }

    @Override
    protected void handlePage(Page page) {
        page.putField("content", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article"));
    }

    private String getTimeString() {
        Date date = SpiderConfig.isToday ? new Date(new Date().getTime()) : new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * SpiderConfig.aheadDayCount);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int monthIndex = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        return monthList[monthIndex] + "-" + year + "/";
    }

    @Override
    protected String getUrl() {
        String date = getTimeString();
        return MONTHLY_BASE_URL + date + horoscope;
    }

    @Override
    protected AbstractPipeLine getPiPieLine() {
        return new MonthPipeLine(latch, horoscopeModel);
    }
}
