package horoscopeProcessor;

import main.HoroscopeModel;
import main.SpiderConfig;
import pipeLine.AbstractPipeLine;
import pipeLine.TodayPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class TodayProcessor extends AbstractProcessor {
    private static final String DAILY_BASE_URL = "https://www.tarot.com/api/daily-horoscope?";
    private HoroscopeModel horoscopeModel;

    public TodayProcessor(String horoscope, CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(horoscope, latch);
        this.horoscopeModel = horoscopeModel;
    }

    @Override
    protected void handlePage(Page page) {
        page.putField("content", new JsonPathSelector("$.scope_txt").select(page.getRawText()));
        //page.putField("content",page.getHtml().xpath("/html/body/main/row[2]/column[1]/article/daily-horoscope/p[1]"));
    }

    private String getTimeString() {
        Date as = new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * SpiderConfig.aheadDayCount);
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(as);
    }

    @Override
    protected String getUrl() {
        String date = getTimeString();
        return DAILY_BASE_URL + "date=" + date + "&sign=" + horoscope + "&type=horoscopes_daily";
    }

    @Override
    protected AbstractPipeLine getPiPieLine() {
        return new TodayPipeLine(latch, horoscopeModel);
    }


}
