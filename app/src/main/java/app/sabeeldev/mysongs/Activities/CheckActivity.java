package app.sabeeldev.mysongs.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.sabeeldev.mysongs.R;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException {
        // URL
        URL youtubeUrl = new URL("https://www.youtube.com/watch?v=VPp3fudw69w");

        // Open URL
        BufferedReader reader = new BufferedReader(new InputStreamReader(youtubeUrl.openStream()));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        String content = builder.toString();

        // Parse URLs
        String[] list = content.split("url=");
        HashSet<String> results = new HashSet<String>();
        for (String hits : list) {
            if (hits.startsWith("https")) {
                String link = hits.split(",")[0];
                results.add(link);
            }
        }

        String url = null;
        String itag;
        String type;

        for (String set : results) {
            set = set.replace("\\", "\\\\");

            for (String sdata : set.split("\\\\")) {
                if (sdata.startsWith("https"))
                    url = sdata;
            }

            if (url != null) {
                if (url.startsWith("https")) {
                    url = url.split(" ")[0];
                    url = URLDecoder.decode(url, "UTF-8");

                    System.out.println("Stream-URL: " + url.toString());

                    if (url.contains("mime=")) {
                        type = (url.split("mime=")[1]).split("&")[0];
                        type = URLDecoder.decode(type, "UTF-8");
                        System.out.println("Mime-Type: " + type);
                    }

                    if (url.contains("itag")) {
                        itag = (url.split("itag=")[1]).split("&")[0];
                        itag = URLDecoder.decode(itag, "UTF-8");
                        System.out.println("itag: " + itag);
                    }

                    Pattern p = Pattern.compile("<title>(.*?)</title>");
                    Matcher m = p.matcher(content);
                    while (m.find() == true) {
                        String htmlTitle = m.group(1);
                        htmlTitle = htmlTitle.substring(0, htmlTitle.length() - 10);
                        htmlTitle = URLDecoder.decode(htmlTitle);
                        System.out.println("Title: " + htmlTitle);
                        break;
                    }
                }
            }
            System.out.println();
        }
    }
}

