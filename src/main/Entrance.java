package main;

import horoscopeProcessor.*;
import network.NetWorkManager;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Entrance {
    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

    private static int retryCount = 5;

    public static void main(String[] args) {
        //要设置本地抓包代理打开
//        System.setProperty("http.proxyHost", "localhost");
//        System.setProperty("http.proxyPort", "8888");
//        System.setProperty("https.proxyHost", "localhost");
//        System.setProperty("https.proxyPort", "8888");


//        if (args == null||args.length == 0){
//            System.out.println("please enter args(ChromeDriver path)");
//            return;
//        }

        try {
            fetchHoroscopeData();
        } catch (Exception e) {
            if (retryCount > 0) {
                retryCount--;
                fetchHoroscopeData();
            } else {
                System.out.println("fetch horoscope fail: " + e.getMessage());
            }
        }

    }

    private static void fetchHoroscopeData() {
        for (int i = 0; i < horoscopeList.length; i++) {
            CountDownLatch latch = new CountDownLatch(4);
            HoroscopeModel horoscopeModel = new HoroscopeModel();
            horoscopeModel.setTimezone(DateUtil.getTimeZone());

            System.out.println(DateUtil.getTimeZone());

            horoscopeModel.setConstellation(horoscopeList[i]);

            TodayProcessor todayProcessor = new TodayProcessor(horoscopeList[i], latch, horoscopeModel);
            todayProcessor.startCrawling();
            TomorrowProcessor tomorrowProcessor = new TomorrowProcessor(horoscopeList[i], latch, horoscopeModel);
            tomorrowProcessor.startCrawling();
            MonthProcessor monthProcessor = new MonthProcessor(horoscopeList[i], latch, horoscopeModel);
            monthProcessor.startCrawling();
            YearProcessor yearProcessor = new YearProcessor(horoscopeList[i], latch, horoscopeModel);
            yearProcessor.startCrawling();

            WeekCrawler.fetchWeekData(horoscopeList[i], latch, horoscopeModel);

            // horoscopeModel.setWeek(WeekContentProvider.getWeekByHoroscope(i));

            try {
                //超时时间，防止程序死锁
                latch.await(180, TimeUnit.SECONDS);
                System.out.println("Entrance main horoscopeModel " + horoscopeModel.toString());

                NetWorkManager.submitHoroscope(horoscopeModel);
                //NetWorkManager.updateHoroscope(horoscopeModel);
            } catch (InterruptedException e) {
                System.out.println("Entrance" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
