package horoscopeProcessor;

import pipeLine.MySimplePipeLine;
import downloader.AdjustHttpClientDownloader;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;


public class MatchHoroscopeProcessor implements PageProcessor {

    private static final String[] horoscopeList = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius"
            , "Capricorn", "Aquarius", "Pisces"};

    private static final String[] monthList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private static final String LOVE_BASE_URL = "https://www.ganeshaspeaks.com/zodiac-signs/compatibility/";

    private Site site = Site.me().setRetryTimes(3).setSleepTime(200).setTimeOut(90000);

    private String key;

    public MatchHoroscopeProcessor(String key) {
        this.key = key;
    }

    @Override
    public void process(Page page) {
        page.putField(key, page.getHtml().xpath("/html/body/main/section[1]/div/div/div[1]/div/div[2]/div[2]/div[1]"));

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider spider = new Spider(new MatchHoroscopeProcessor("loveMatch"));
        spider.setDownloader(new AdjustHttpClientDownloader());
        spider.addPipeline(new MySimplePipeLine("D:\\projects\\githubSample\\SpiderDemo\\spideroutput"));

        for (int i = 0; i < horoscopeList.length; i++) {
            for (int j = 0; j < horoscopeList.length; j++) {
                //"https://www.ganeshaspeaks.com/zodiac-signs/compatibility/capricorn-man-capricorn-woman"
                String url = LOVE_BASE_URL + horoscopeList[i] + "-man-" + horoscopeList[j] + "-woman";
                spider.addUrl(url).thread(5).run();
            }
        }
    }
}

