package horoscopeProcessor;

import downloader.AdjustHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class WeeklyHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "Capricorn", "Aquarius", "Pisces"};

    private static final String DAILY_BASE_URL = "https://www.tarot.com/api/daily-horoscope?";

    private static final String[] monthList = {"01","02","03","04","05","06","07","08","09","10","11","12"};

    private static final String[] dayList = {"01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19"
            ,"20","21","22","23","24","25","26","27","28","29","30","31"};

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(90000).setCharset("UTF-8");

    private String key;

    public WeeklyHoroscopeProcessor(String key) {
        this.key = key;
    }

    @Override
    public void process(Page page) {
//
        page.putField("content", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article/"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new WeeklyHoroscopeProcessor("daily horoscope"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        //spider.addPipeline(new pipeLine.DailypipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput"));


        spider.addUrl("https://www.tarot.com/astrology/weekly-love-horoscopes").thread(5).run();

    }
}
