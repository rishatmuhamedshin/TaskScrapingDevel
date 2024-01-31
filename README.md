# TaskScrapingDevel
Test task (Getting feedback about a movie)



Это простой парсер Кинопоиска на Java <span style=color:red>(Выполнение ТЗ)</span>.


<h2>Тех. Задание</h2>  На языке Scala/Java/Python необходимо написать программу, которая собирает все рецензии зрителей на фильм “Побег из Шоушенка” с сайта Кинопоиск https://www.kinopoisk.ru/film/326/ и сохраняет в файл формата CSV/TSV всю полезную информацию по ним.


___________________________________

Для решения задачи я воспользовался библиотекой <span style="color:orange">Jsoup</span>.

<h2> Структура</h2>

1. Класс <span style="color:blue">Response</span> предаставляет POJO (Plain old Java Object), некая абстракция рецензии, с сохранением автора, оценки и сообщения.

2. Перечисление <span style="color:blue">Grade</span> представляет саму оценку рецензии.

3. В классе <span style="color:blue">Parser</span> зашита сама логика обхода страниц.

```java
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
```
Здесь я просто получаю все страницы сайта с рецензией о фильме.


```java
  List<Elements> elements = new ArrayList<>();
        for (Document document : showPage()){
            Elements element = document.select("div.response." + grade.getName());
            elements.add(element);
        }
```
Выделяю каждый интересующий меня элемент <span style="color:blue">(div)</span>
```java
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
```

Проходясь по каждому Элементу создаю Ответы на основе тегов.

4. В классе <span style="color:blue">KinoPoiskApp</span> создал три потока для каждой оценки рецензии. И сохраняю Ответы в файлах csv.