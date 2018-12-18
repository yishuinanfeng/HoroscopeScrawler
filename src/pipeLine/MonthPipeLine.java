package pipeLine;

import main.HoroscopeModel;

import java.util.concurrent.CountDownLatch;

public class MonthPipeLine extends AbstractPipeLine {
    public MonthPipeLine(CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(latch, horoscopeModel);
    }

    @Override
    protected void handleData(String monthHoroscope) {
        horoscopeModel.setMonth(monthHoroscope);
    }
}
