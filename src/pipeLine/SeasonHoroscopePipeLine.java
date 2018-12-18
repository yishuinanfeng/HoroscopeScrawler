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

public class SeasonHoroscopePipeLine extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public SeasonHoroscopePipeLine() {
        this.setPath("/data/webmagic");
    }

    public SeasonHoroscopePipeLine(String path) {
        this.setPath(path);
    }

    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;

        try {
            String s[] = resultItems.getRequest().getUrl().split("/");
            String fileName = s[s.length - 1];
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.getFile(path + fileName + ".json")));
            printWriter.write(JSON.toJSONString(resultItems.getAll()));
            printWriter.close();
        } catch (IOException var5) {
            this.logger.warn("write file error", var5);
        }
    }
}
