package main;

import horoscopeProcessor.MonthProcessor;
import horoscopeProcessor.TodayProcessor;
import horoscopeProcessor.TomorrowProcessor;
import horoscopeProcessor.YearProcessor;
import network.NetWorkManager;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class FetchUtil {
    private static final String[] horoscopeList = {"aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius"
            , "capricorn", "aquarius", "pisces"};

    public static void main(String[] args) {

        for (int i = 0; i < horoscopeList.length; i++) {
            try {
                NetWorkManager.fetchCurrentHoroscope(horoscopeList[i]);
            } catch (IOException e) {
                System.out.println("FetchUtil" + e.getMessage());
            }
        }
    }
}
