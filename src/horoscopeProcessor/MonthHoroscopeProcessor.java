package horoscopeProcessor;

import downloader.AdjustHttpClientDownloader;
import pipeLine.MonthlyPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class MonthHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

    private static final String MONTHLY_BASE_URL = "https://www.tarot.com/horoscopes/";

    private static final String[] monthList = {"january", "february", "march", "april", "may", "june", "july", "august", "september", "october", "november", "december"};

    private static final String[] dayList = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19"
            , "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(90000).setCharset("UTF-8");

    private String key;

    public MonthHoroscopeProcessor(String key) {
        this.key = key;
    }

    @Override
    public void process(Page page) {
        //暂时找不到排除无用标签的方法，所以暂时固定截取若干段
        page.putField("p1", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article"));
//        page.putField("p2", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/section[1]/article/div/p[2]"));
//        page.putField("p3", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/section[1]/article/div/p[3]"));
//        page.putField("p4", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/section[1]/article/div/p[4]"));
//        page.putField("p5", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/section[1]/article/div/p[5]"));
//        page.putField("p6", page.getHtml().xpath("/html/body/div[2]/div/div[1]/div/section[1]/article/div/p[6]"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new MonthHoroscopeProcessor("daily horoscope"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        spider.addPipeline(new MonthlyPipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput"));

        //"https://www.tarot.com/horoscopes/november-2018/aries"
        //spider.addUrl("https://www.tarot.com/horoscopes/november-2018/aries").thread(5).run();

        for (int i = 0; i < horoscopeList.length; i++) {
            String horoscope = horoscopeList[i];
            for (int j = 0; j < monthList.length; j++) {
                String month = monthList[j];
                String url = MONTHLY_BASE_URL + month+ "-2018/" + horoscope;
                System.out.println(url);
                spider.addUrl(url).thread(5).run();
            }
        }
    }
}
