package horoscopeProcessor;

import downloader.AdjustHttpClientDownloader;
import pipeLine.AbstractPipeLine;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.JsonPathSelector;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(5).setSleepTime(200).setTimeOut(120000).setCharset("UTF-8");

    protected String horoscope;
    protected CountDownLatch latch;

    public AbstractProcessor(String horoscope, CountDownLatch latch) {
        this.horoscope = horoscope;
        this.latch = latch;
    }

    @Override
    public void process(Page page) {
//        page.putField("title", page.getHtml().xpath("/html/body/main/section/div/div/div[1]/div/div[2]/div[1]/h2[1]"));

        //拿到scope_txt内容
//        page.putField("content", page.getHtml().xpath("//body/text()"));
        // page.putField("content", new JsonPathSelector("$.data.content").select(page.getRawText()));
        //page.putField("content", new JsonPathSelector("$.scope_txt").select(page.getRawText()));

        handlePage(page);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public void startCrawling() {
        Spider spider = new Spider(this);
        spider.setDownloader(new AdjustHttpClientDownloader());
        spider.addPipeline(getPiPieLine());

        System.out.println("AbstractProcessor" + getUrl());

        spider.addUrl(getUrl()).thread(5).run();


        //https://www.tarot.com/api/daily-horoscope?date=2018-11-23&sign=taurus&type=horoscopes_daily
        //spider.addUrl("https://www.tarot.com/api/daily-horoscope?date=2018-11-23&sign=taurus&type=horoscopes_daily").thread(5).run();
    }

    protected abstract void handlePage(Page page);

    protected abstract String getUrl();

    protected abstract AbstractPipeLine getPiPieLine();


}
