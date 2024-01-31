import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class KinoPoiskApp {

    public static void main(String[] args)  {
        Runnable runnable = () -> {
            List<Response> responses = new Parser().goodResponses();
            File cvsFile = new File("goodReview.csv");
            PrintWriter out;
            try {
                out = new PrintWriter(cvsFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (Response response : responses){
                out.println(response);
            }
            out.close();
        };

        Runnable runnable2 = () -> {
            List<Response> responses = new Parser().badResponses();
            File cvsFile = new File("badReview.csv");
            PrintWriter out;
            try {
                out = new PrintWriter(cvsFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (Response response : responses){
                out.println(response);
            }
            out.close();
        };

        Runnable runnable3 = () -> {
            List<Response> responses = new Parser().neutralResponses();
            File cvsFile = new File("neutralReview.csv");
            PrintWriter out;
            try {
                out = new PrintWriter(cvsFile);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            for (Response response : responses){
                out.println(response);
            }
            out.close();
        };


        Thread t = new Thread(runnable);
        Thread t2 = new Thread(runnable2);
        Thread t3 = new Thread(runnable3);

        t.start();
        t2.start();
        t3.start();



    }

}
