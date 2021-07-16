package ru.job4j.html;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = doc.select(".postslisttopic");//полечаем нужный тег
        int count = 0;
        for (Element td : row) {
            Element href = td.child(0);
            System.out.println(href.attr("href"));
            System.out.println(href.text());
            System.out.println(doc.getElementsByAttribute("style").select(".altCol").get(count).text());
            count ++;
            //System.out.println(td.parent().child(5).text()); второй вариант получения даты
            System.out.println();
        }
    }
}

