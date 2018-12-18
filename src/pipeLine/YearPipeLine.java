package pipeLine;

import main.HoroscopeModel;

import java.util.concurrent.CountDownLatch;

public class YearPipeLine extends AbstractPipeLine {
    public YearPipeLine(CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(latch,horoscopeModel);
    }

    @Override
    protected void handleData(String yearHoroscope) {
        horoscopeModel.setYear(yearHoroscope);
    }
}
