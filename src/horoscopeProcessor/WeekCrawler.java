package horoscopeProcessor;

import main.DateUtil;
import main.HoroscopeModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * 周数据爬取
 */
public class WeekCrawler {
    private static ChromeDriverService service;
    private static int retryCount = 3;
    private static String DRIVER_PATH = "D:\\projects\\library\\chromedriver_win32\\chromedriver.exe";

    private static WebDriver getChromeDriver() throws IOException {
        // System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        // 创建一个 ChromeDriver 的接口，用于连接 Chrome（chromedriver.exe 的路径可以任意放置，只要在newFile（）的时候写入你放的路径即可）
        service = new ChromeDriverService.Builder().usingDriverExecutable(new File(DRIVER_PATH))
                .usingAnyFreePort().build();
        service.start();

        ChromeOptions chromeOptions = new ChromeOptions();
//        设置为 headless 模式 （必须）
        chromeOptions.addArguments("--headless");
        // 创建一个 Chrome 的浏览器实例
        return new RemoteWebDriver(service.getUrl(), chromeOptions);

    }

    /**
     * 仅为单纯调试周数据爬取
     * @param args
     */
    public static void main(String[] args) {
        fetchWeekData("aries", new CountDownLatch(1), new HoroscopeModel());
    }

    public static void fetchWeekData(String horoscope, CountDownLatch latch, HoroscopeModel horoscopeModel) {

        try {
            WebDriver driver = WeekCrawler.getChromeDriver();
            String url = "https://www.tarot.com/astrology/weekly-love-horoscopes/" + horoscope + "/" + DateUtil.getTimeZone();
            driver.get(url);

            System.out.println(url);

//            DesiredCapabilities dcaps = new DesiredCapabilities();

            System.out.println(" Page title is: " + driver.getTitle());
            WebElement element = driver.findElement(By.xpath("/html/body/main/row[2]/column[1]/article/weekly-love-horoscope/p[1]"));
            System.out.println(" element is: " + element.getText());
            horoscopeModel.setWeek(element.getText());

            // 关闭浏览器
            driver.quit();
            // 关闭 ChromeDriver 接口
            service.stop();

            latch.countDown();

        } catch (IOException e) {
            System.out.println("fetch week horoscope fail: " + e.getMessage());
        } catch (Exception e) {
            if (retryCount > 0) {
                retryCount--;
                fetchWeekData(horoscope, latch, horoscopeModel);
            } else {
                System.out.println("fetch week horoscope fail: " + e.getMessage());
            }
        }

    }
}
