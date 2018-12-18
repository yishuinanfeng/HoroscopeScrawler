package horoscopeProcessor;

import pipeLine.SeasonHoroscopePipeLine;
import downloader.AdjustHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SeasonalHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

  //  private static final String SEASON_BASE_URL = "http://astro.velida.net/astrology/seasonal-horoscope/";
    private static final String SEASON_BASE_URL = "https://www.tarot.com/astrology/";

    private static final String[] monthList = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};


    private static final String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
            , "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(90000).setCharset("UTF-8");

    private String key;

    public SeasonalHoroscopeProcessor(String key) {
        this.key = key;
    }

    @Override
    public void process(Page page) {
        page.putField("content", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/article/div/div[2]"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new SeasonalHoroscopeProcessor("daily horoscope"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        spider.addPipeline(new SeasonHoroscopePipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput"));

        //"https://www.tarot.com/horoscopes/november-2018/aries"
//        spider.addUrl("https://www.tarot.com/astrology/scorpio-season-horoscope").thread(5).run();

        for (int i = 0; i < horoscopeList.length; i++) {
            String horoscope = horoscopeList[i];
                String url = SEASON_BASE_URL + horoscope + "-season-horoscope";
                System.out.println(url);
                spider.addUrl(url).thread(5).run();
        }
    }
}
