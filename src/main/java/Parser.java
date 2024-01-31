import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final String URL = "https://www.kinopoisk.ru/film/326/reviews/ord/rating/status/all/perpage/200/page/";
    private static final int PAGENUMBER = 3;

    public List<Response> goodResponses(){
        return createResponse(Grade.GOOD);
    }
    public List<Response> neutralResponses(){
        return createResponse(Grade.NEUTRAL);
    }
    public List<Response> badResponses(){
        return createResponse(Grade.BAD);
    }

    private List<Response> createResponse(Grade grade){
        List<Response> responses = new ArrayList<>();
        for (Elements elements : showResponses(grade)){
            for (Element element : elements){
                responses.add(new Response(
                        element.select("a[itemprop=name]").text(),
                        element.select("p[class=sub_title]").text(),
                        element.select("span[class=_reachbanner_]").text(),
                        grade
                ));
            }
        }
        return responses;
    }

    private List<Elements> showResponses(Grade grade){
        List<Elements> elements = new ArrayList<>();
        for (Document document : showPage()){
            Elements element = document.select("div.response." + grade.getName());
            elements.add(element);
        }
        return elements;
    }


    private List<Document> showPage(){
        List<Document> documents = new ArrayList<>();
        for (int i = 1; i <= PAGENUMBER; i++) {
            Document document;
            try {
                document = Jsoup.connect("https://www.kinopoisk.ru/film/326/reviews/ord/rating/status/all/perpage/200/page/" + i).get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            documents.add(document);
        }
        return documents;
    }

}
