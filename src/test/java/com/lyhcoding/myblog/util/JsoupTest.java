package com.lyhcoding.myblog.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

public class JsoupTest {
    String html = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "  <title>Document</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "  <div id=\"weather\">10도</div>\n" +
            "  <div class=\"loc\">서울</div>\n" +
            "</body>\n" +
            "</html>";

    @Test
    public void jsoup_test(){
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("#weather");
        System.out.println(elements.get(0).text());

        Elements elements2 = doc.select(".loc");
        System.out.println(elements2.get(0).text());
    }
}
