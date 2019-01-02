package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MoikrugStrategy implements Strategy {
  //  private static final String URL_FORMAT = "http://javarush.ru/testdata/big28data.html";
     private static final String URL_FORMAT = "https://moikrug.ru/vacancies?q=java+%s&page=%d";

    public List<Vacancy> getVacancies(String searchString){

        List<Vacancy> result = new ArrayList<>();
        int page = 0;
        try {
            for (Document html = getDocument(searchString, page); html != null; html = getDocument(searchString, ++page)) {
                Elements elements = html.select("[id^=job_]");
                if (elements.size() == 0) break;

                for (Element element : elements) {

                    Vacancy vacancy = new Vacancy();

                    vacancy.setSiteName("https://moikrug.ru/");

                    Elements el;

                    el = element.getElementsByClass("title").first().getElementsByTag("a");
                    vacancy.setTitle(el.first().text());
                    vacancy.setUrl(vacancy.getSiteName() + el.attr("href").substring(1));

                    el = element.getElementsByClass("salary");
                    vacancy.setSalary(el.size() != 0 ? el.first().getElementsByTag("div").first().text() : "");

                    el = element.getElementsByClass("location");
                    vacancy.setCity(el.size() != 0 ? el.first().getElementsByTag("a").first().text() : "");

                    el = element.getElementsByClass("company_name").first().getElementsByTag("a");
                    vacancy.setCompanyName(element.getElementsByAttributeValue("class", "company_name").text());

                    result.add(vacancy);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        String url = String.format(URL_FORMAT, searchString, page);
        Document document = Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36")
                .referrer("none")
                .get();

        return document;
    }
}