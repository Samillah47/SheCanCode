import java.io.*;
import java.net.*;
import java.util.*;

class URLFetcher implements Runnable {

    private String urlString;

    public URLFetcher(String urlString) {
        this.urlString = urlString;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(
                            "Thread [" + Thread.currentThread().getName() +
                            "] from " + urlString + ": " + line);
                }
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error fetching " + urlString);
            e.printStackTrace();
        }
    }
}