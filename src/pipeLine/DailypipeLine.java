package pipeLine;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class DailypipeLine extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public DailypipeLine() {
        this.setPath("/data/webmagic");
    }

    public DailypipeLine(String path) {
        this.setPath(path);
    }

    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;

        try {
            //https://www.tarot.com/api/daily-horoscope?date=2018-11-23&sign=taurus&type=horoscopes_daily

            String date = resultItems.getRequest().getUrl().substring(47, 57);
            String s[] = resultItems.getRequest().getUrl().split("&");
            String horoscopeSign = s[1];
            String horoscope = horoscopeSign.split("=")[1];

            String fileName = horoscope + "_" + date;

//            Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
//            Matcher matcher = pattern.matcher(url);
//            String s = "";
//            if (matcher.matches()) {
//                s = matcher.group();
//            }

            PrintWriter printWriter = new PrintWriter(new FileWriter(this.getFile(path + fileName + ".json")));
            printWriter.write(JSON.toJSONString(resultItems.getAll()));
            printWriter.close();
        } catch (IOException var5) {
            this.logger.warn("write file error", var5);
        }

    }
}
