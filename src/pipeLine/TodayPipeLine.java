package pipeLine;

import main.HoroscopeModel;

import java.util.concurrent.CountDownLatch;

public class TodayPipeLine extends AbstractPipeLine {
    public TodayPipeLine(CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(latch, horoscopeModel);
    }

    @Override
    protected void handleData(String todayHoroscope) {
        horoscopeModel.setToday(todayHoroscope);
    }
}
