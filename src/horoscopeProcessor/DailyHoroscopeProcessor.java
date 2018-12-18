package horoscopeProcessor;

import pipeLine.DailypipeLine;
import downloader.AdjustHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

public class DailyHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

    private static final String DAILY_BASE_URL = "https://www.tarot.com/api/daily-horoscope?";

    private static final String[] monthList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    private static final String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
            , "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(120000).setCharset("UTF-8");

    private String dayType;

    public DailyHoroscopeProcessor(String dayType) {
        this.dayType = dayType;
    }

    @Override
    public void process(Page page) {
//        page.putField("title", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/h2[1]"));

        //拿到scope_txt内容
//        page.putField("content", page.getHtml().xpath("//body/text()"));
        // page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));
        page.putField("content", new JsonPathSelector("$.scope_txt").select(page.getRawText()));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new DailyHoroscopeProcessor("daily horoscope"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        spider.addPipeline(new DailypipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput\\www.tarot.com\\daily_2018"));

        for (int k = 0; k < horoscopeList.length; k++) {
            for (int i = 0; i < monthList.length; i++) {
                for (int j = 0; j < dayList.length; j++) {
                    String dateUrl = "date=2018-" + monthList[i] + "-" + dayList[j] + "&sign=" + horoscopeList[k] + "&type=horoscopes_daily";
                    spider.addUrl(DAILY_BASE_URL + dateUrl).thread(5).run();
                }
            }
        }
        //https://www.tarot.com/api/daily-horoscope?date=2018-11-23&sign=taurus&type=horoscopes_daily
        //spider.addUrl("https://www.tarot.com/api/daily-horoscope?date=2018-11-23&sign=taurus&type=horoscopes_daily").thread(5).run();

    }
}
