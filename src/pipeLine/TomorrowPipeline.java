package pipeLine;

import main.HoroscopeModel;

import java.util.concurrent.CountDownLatch;

public class TomorrowPipeline extends AbstractPipeLine {
    public TomorrowPipeline(CountDownLatch latch, HoroscopeModel horoscopeModel) {
        super(latch, horoscopeModel);
    }

    @Override
    protected void handleData(String tomorrowHoroscope) {
        horoscopeModel.setTomorrow(tomorrowHoroscope);
    }
}
