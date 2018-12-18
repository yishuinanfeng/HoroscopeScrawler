package horoscopeProcessor;

import downloader.AdjustHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class YearHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius"
            , "Capricorn", "Aquarius", "Pisces"};

    //private static final String YEAR_BASE_URL = "https://www.ganeshaspeaks.com/2019-horoscope/";
    private static final String YEAR_BASE_URL = "https://www.tarot.com/horoscopes/2019/";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(90000);

    private String key;

    public YearHoroscopeProcessor(String key) {
        this.key = key;
    }

    @Override
    public void process(Page page) {
       // page.putField("title", page.getHtml().xpath("//*[@id=\"ctl00_CenterColumn_PublicationView1\"]/div[1]/div[2]/h3/text()"));
       // page.putField("title", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article"));
        page.putField("title", page.getHtml().xpath("/html/body/main/row[2]/column[1]/article"));

//        page.putField("p1", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[1]/text()"));
//        page.putField("p2", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[2]/text()"));
//        page.putField("p3", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[3]/text()"));
//        page.putField("p4", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[4]/text()"));
//        page.putField("p5", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[5]/text()"));
//        page.putField("p6", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/p[6]/text()"));


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new YearHoroscopeProcessor("yearly horoscope"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        //spider.addPipeline(new pipeLine.YearHoroscopePipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput"));

        for (int i = 0; i < horoscopeList.length; i++) {

           // String url = YEAR_BASE_URL + horoscopeList[i] + "-horoscope-2019";
            String url = YEAR_BASE_URL + horoscopeList[i];
            spider.addUrl(url).thread(5).run();

        }


        //spider.addUrl("https://www.tarot.com/horoscopes/2019/aries").thread(5).run();

    }
}
