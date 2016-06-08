package com.lionxxw.hibernate.twolevelcache.hibernate.seesion;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	static String url = "http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html";

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
//		 bolgBody();
//		blog();
//		article();
		downloads();
	}

	/**
	 * 获取指定HTML 文档指定的body
	 * 
	 * @throws IOException
	 */
	private static void bolgBody() throws IOException {
		// 直接从字符串中输入 HTML 文档
		String html = "<html><head><title> 开源中国社区 </title></head>"
				+ "<body><p> 这里是 jsoup 项目的相关文章 </p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc.body());

		// 从 URL 直接加载 HTML 文档
		Document doc2 = Jsoup.connect(url).get();
		String body = doc2.body().toString();
		System.out.println(body);
	}

	/**
	 * 获取博客上的文章标题和链接
	 */
	public static void article() {
		Document doc;
		try {
			doc = Jsoup.connect("http://www.cnblogs.com/zyw-205520/").get();
			Elements ListDiv = doc.getElementsByAttributeValue("class","postTitle");
			for (Element element : ListDiv) {
				Elements links = element.getElementsByTag("a");
				for (Element link : links) {
					String linkHref = link.attr("href");
					String linkText = link.text().trim();
					System.out.println(linkHref);
					System.out.println(linkText);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取指定博客文章的内容
	 */
	public static void blog() {
		Document doc;
		try {
			doc = Jsoup
					.connect(
							"http://www.cnblogs.com/zyw-205520/archive/2012/12/20/2826402.html")
					.get();
			Elements ListDiv = doc.getElementsByAttributeValue("class",
					"postBody");
			for (Element element : ListDiv) {
				System.out.println(element.html());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void downloads(){
		String down_url = "http://android.myapp.com/myapp/detail.htm?apkName=com.wonders.communitycloud";
		Document doc;
		try {
			doc = Jsoup.connect(down_url).get();
			System.out.println(doc.body().text());
			Elements ListDiv = doc.getElementsByClass("det-ins-num");
			System.out.println(ListDiv.text());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}