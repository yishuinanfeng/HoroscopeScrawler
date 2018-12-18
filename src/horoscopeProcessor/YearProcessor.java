package horoscopeProcessor;

import main.HoroscopeModel;
import main.SpiderConfig;
import pipeLine.AbstractPipeLine;
import pipeLine.YearPipeLine;
import us.codecraft.webmagic.Page;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class YearProcessor extends AbstractProcessor {
    private static final String YEAR_BASE_URL = "https://www.tarot.com/horoscopes/";

    private HoroscopeModel horoscopeModel;

    public YearProcessor(String horoscope, CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(horoscope, latch);
        this.horoscopeModel = horoscopeModel;
    }

    @Override
    protected void handlePage(Page page) {
        page.putField("content", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article"));
    }

    @Override
    protected String getUrl() {
        Date date = SpiderConfig.isToday ? new Date(new Date().getTime()) : new Date(new Date().getTime() + 24 * 60 * 60 * 1000
                * SpiderConfig.aheadDayCount);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return YEAR_BASE_URL + year + "/" + horoscope;
    }

    @Override
    protected AbstractPipeLine getPiPieLine() {
        return new YearPipeLine(latch, horoscopeModel);
    }
}