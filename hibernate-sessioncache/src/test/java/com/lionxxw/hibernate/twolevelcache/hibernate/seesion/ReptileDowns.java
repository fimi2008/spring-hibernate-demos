package com.lionxxw.hibernate.twolevelcache.hibernate.seesion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: 抓取app在各个商城中的下载量 </p>
 * api:http://www.open-open.com/jsoup/
 *
 * maven配置
 * <dependency>
 *    <groupId>org.jsoup</groupId>
 *    <artifactId>jsoup</artifactId>
 *    <version>1.8.3</version>
 * </dependency>
 *
 * @author wangxiang
 * @version 1.0
 * @time 16/6/7 上午10:27
 */
public class ReptileDowns {
    private static List<DownUrl> urls = new ArrayList<DownUrl>();

    static{
        DownUrl url1 = new DownUrl("应用宝", "http://android.myapp.com/myapp/detail.htm?apkName=com.wonders.communitycloud", "det-ins-num");
        DownUrl url2 = new DownUrl("豌豆荚", "http://www.wandoujia.com/apps/com.wonders.communitycloud", "item");
        DownUrl url3 = new DownUrl("360手机助手", "http://zhushou.360.cn/detail/index/soft_id/3259832", "desc");
        url3.setParentClass("info");
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
    }

    public static void main(String[] args) {
        for (DownUrl url : urls){
            downloads(url);
        }
    }

    public static void downloads(DownUrl url){
        Document doc;
        try {
            doc = Jsoup.connect(url.getUrl()).get();
            // System.out.println(doc.body()); // 如果不确定返回内容,则打印出body信息进行调试
            // 如果需要用父样式进行定位时,给parentClass赋值
            if (null != url.getParentClass() && !"".equals(url.getParentClass())){
                Elements ListDiv = doc.getElementsByClass(url.getParentClass());
                for (Element element : ListDiv){
                    element.getElementsByClass(url.getClassName());
                    System.out.println(url.getStore() + "下载量:" + element.text());
                }
            }else{
                Elements ListDiv = doc.getElementsByClass(url.getClassName());
                System.out.println(url.getStore()+"下载量:"+ListDiv.text());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class DownUrl{
        private String store; // 下载商城
        private String url; // 下载路径
        private String parentClass; // 上级class名称
        private String className; // 下载量标识class名称

        public DownUrl() {
        }

        public DownUrl(String store, String url, String className) {
            this.store = store;

            this.url = url;
            this.className = className;
        }

        public String getStore() {
            return store;
        }

        public void setStore(String store) {
            this.store = store;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getParentClass() {
            return parentClass;
        }

        public void setParentClass(String parentClass) {
            this.parentClass = parentClass;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
