package pipeLine;

import com.alibaba.fastjson.JSON;
import main.HoroscopeModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.webmagic.selector.Selectable;
import us.codecraft.webmagic.utils.FilePersistentBase;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractPipeLine extends FilePersistentBase implements Pipeline {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private CountDownLatch latch;
    protected HoroscopeModel horoscopeModel;

    public AbstractPipeLine(CountDownLatch latch, HoroscopeModel horoscopeModel) {
        this.latch = latch;
        this.horoscopeModel = horoscopeModel;
    }

    public void process(ResultItems resultItems, Task task) {
//        String path = this.path + PATH_SEPERATOR + task.getUUID() + PATH_SEPERATOR;


        //保存JSON.toJSONString(resultItems.getAll()到缓存的类对象中

        // String jsonStr = JSON.toJSONString(resultItems.getAll());
        Map contentMap = resultItems.getAll();
        if (contentMap.get("content") instanceof HtmlNode) {
            HtmlNode content = (HtmlNode) resultItems.getAll().get("content");
            String s = content.toString();
            Document doc = Jsoup.parse(s);
            //删除logged-out和logged-in标签
            doc.select("logged-out").remove();
            doc.select("logged-in").remove();
            doc.select("a").remove();

            //删除p标签中的a标签
//            Elements elements = doc.select("p");
//            for (Element element:elements) {
//                element.select("a").remove();
//            }

            System.out.println("process latch count" + latch.getCount());
//            Elements elements = doc.getElementsByTag("p");
//            for (Element element:elements) {
//                if (element.select("logged-out") != null){
//                    element.select("logged-out").remove();
//                }
//            }
            contentMap.put("content", doc.html());

         //   System.out.println("doc.html:" + doc.html());
        }

      //  handleData(JSON.toJSONString(resultItems.getAll()));
        handleData(resultItems.getAll().get("content").toString());

        System.out.println("process latch count" + latch.getCount());
        System.out.println("content: " + resultItems.getAll().get("content").toString());

        latch.countDown();

//        try {
//            String s[] = resultItems.getRequest().getUrl().split("/");
//            String fileName = s[s.length - 1];
//            PrintWriter printWriter = new PrintWriter(new FileWriter(this.getFile(path + fileName +".json")));
//            printWriter.write(JSON.toJSONString(resultItems.getAll()));
//            printWriter.close();
//        } catch (IOException var5) {
//            this.logger.warn("write file error", var5);
//        }
    }

    protected abstract void handleData(String resultItems);
}
