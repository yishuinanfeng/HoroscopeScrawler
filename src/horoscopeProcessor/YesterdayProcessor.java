//package horoscopeProcessor;
//
//import pipeLine.AbstractPipeLine;
//import pipeLine.TodayPipeLine;
//import us.codecraft.webmagic.Page;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.concurrent.CountDownLatch;
//
//public class YesterdayProcessor extends AbstractProcessor {
//    private static final String DAILY_BASE_URL = "https://www.tarot.com/api/daily-horoscope?";
//
//    public YesterdayProcessor(String horoscope, CountDownLatch latch) {
//        super(horoscope, latch);
//    }
//
//    @Override
//    protected void handlePage(Page page) {
//
//    }
//
//    private String getTimeString() {
//        Date as = new Date(new Date().getTime() - 24 * 60 * 60 * 1000);
//        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
//        return matter1.format(as);
//    }
//
//    @Override
//    protected String getUrl() {
//        String date = getTimeString();
//        return DAILY_BASE_URL + "date=" + date + "&sign=" + horoscope + "&type=horoscopes_daily";
//    }
//
//    @Override
//    protected AbstractPipeLine getPiPieLine() {
//        return new TodayPipeLine(latch);
//    }
//}
