import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> urls = Arrays.asList(
            "https://auca.ac.rw",
                "https://www.google.com"
        );

        for (String url : urls) {
            Thread thread = new Thread(new URLFetcher(url));
            thread.start();
        }

        System.out.println("Main thread continues running...");
    }
}