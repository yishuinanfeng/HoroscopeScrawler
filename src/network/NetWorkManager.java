package network;

import main.HoroscopeModel;
import main.SpiderConfig;
import okhttp3.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建时间： 2018/11/30
 * 作者：yanyinan
 * 功能描述：
 */
public class NetWorkManager {
    private static final String TAG = NetWorkManager.class.getSimpleName();

    private static final String TODAY_FORTUNE = "todayFortune";
    private static final String TOMORROW_FORTUNE = "tomorrowFortune";
    private static final String WEEK_FORTUNE = "weekFortune";
    private static final String MONTH_FORTUNE = "monthFortune";
    private static final String YEAR_FORTUNE = "yearFortune";
    private static final String CONSTELLATION = "constellation";
    private static final String TIMEZONE = "timezone";

    public static void submitHoroscope(HoroscopeModel horoscopeModel) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add(TODAY_FORTUNE, horoscopeModel.getToday())
                .add(TOMORROW_FORTUNE, horoscopeModel.getTomorrow())
                .add(WEEK_FORTUNE, horoscopeModel.getWeek())
                .add(MONTH_FORTUNE, horoscopeModel.getMonth())
                .add(YEAR_FORTUNE, horoscopeModel.getYear())
                .add(CONSTELLATION, horoscopeModel.getConstellation())
                .add(TIMEZONE, horoscopeModel.getTimezone())
                .build();

        Request request = new Request.Builder()
                .url(Api.SUBMIT_HOROSCOPE)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("submitHoroscope onFailure e:" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("submitHoroscope response :" + response.body().string());
            }
        });
    }

    public static void updateHoroscope(HoroscopeModel horoscopeModel) throws IOException {
        OkHttpClient client = new OkHttpClient();

//        RequestBody body = new FormBody.Builder()
//                .add(TODAY_FORTUNE, horoscopeModel.getToday())
//                .add(TOMORROW_FORTUNE, horoscopeModel.getTomorrow())
//                .add(WEEK_FORTUNE, horoscopeModel.getWeek())
//                .add(MONTH_FORTUNE, horoscopeModel.getMonth())
//                .add(YEAR_FORTUNE, horoscopeModel.getYear())
//                .add(CONSTELLATION, horoscopeModel.getConstellation())
//                .add(TIMEZONE, horoscopeModel.getTimezone())
//                .build();

        FormBody.Builder builder = new FormBody.Builder();

        if (horoscopeModel.getToday() != null) {
            builder.add(TODAY_FORTUNE, horoscopeModel.getToday());
        }
        if (horoscopeModel.getTomorrow() != null) {
            builder.add(TOMORROW_FORTUNE, horoscopeModel.getTomorrow());
        }
        if (horoscopeModel.getWeek() != null) {
            builder.add(WEEK_FORTUNE, horoscopeModel.getWeek());
        }
        if (horoscopeModel.getMonth() != null) {
            builder.add(MONTH_FORTUNE, horoscopeModel.getMonth());
        }
        if (horoscopeModel.getYear() != null) {
            builder.add(YEAR_FORTUNE, horoscopeModel.getYear());
        }

        RequestBody body = builder
                .add(CONSTELLATION, horoscopeModel.getConstellation())
                .add(TIMEZONE, horoscopeModel.getTimezone())
                .build();

        Request request = new Request.Builder()
                .url(Api.UPDATE_HOROSCOPE)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("updateHoroscope onFailure" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("updateHoroscope response :" + response.body().string());
            }
        });
    }

    public static void fetchCurrentHoroscope(String constellation) throws IOException {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new FormBody.Builder()
                .add(CONSTELLATION, constellation)
                .add(TIMEZONE, getTimeZone())
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Api.FETCH_HOROSCOPE).newBuilder();
        urlBuilder
                .addQueryParameter(CONSTELLATION, constellation)
                .addQueryParameter(TIMEZONE, getTimeZone());
        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        System.out.println("submitHoroscope request :" + request.toString());

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("submitHoroscope onFailure " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("date:" + getTimeZone() + ", submitHoroscope response :" + response.body().string());
            }
        });
    }

    private static String getTimeZone() {
        Date as = SpiderConfig.isToday ? new Date(new Date().getTime()) : new Date(new Date().getTime() + 24 * 60 * 60 * 1000 * SpiderConfig.aheadDayCount);
        SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
        return matter1.format(as);
    }

}
