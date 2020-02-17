package com.oystr;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Oystr helperOystr = new Oystr();

        try {

            String input = args[0];
            Document doc = Jsoup
                    .connect(input)
                    .userAgent("Mozilla/5.0")
                    .timeout(10 * 1000)
                    .get();
            Elements links = doc.select("a[href]");
            HashMap<String, Integer> internalExternal = helperOystr.getInternalExternal(links);

            System.out.printf("\nAnalysing -> (%s)\n",input);
            System.out.printf("Page title -> (%s)\n", doc.title());
            System.out.printf(helperOystr.getHtmlVersion(doc));
            System.out.printf("\nTotal links -> (%d)\n", links.size());
            System.out.printf("Internal links -> (%d)\n", internalExternal.get("internal"));
            System.out.printf("External links -> (%d)\n", internalExternal.get("external"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}