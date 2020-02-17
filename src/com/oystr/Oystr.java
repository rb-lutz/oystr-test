package com.oystr;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.List;

public class Oystr {

    public String getHtmlVersion(Document doc) {
        String htmlType = "";
        List<Node>nods = doc.childNodes();
        for (Node node : nods) {
            if (node instanceof DocumentType) {
                DocumentType documentType = (DocumentType)node;
                boolean html5 = documentType.toString().indexOf("<!doctype html>") !=-1? true: false;
                documentType.toString().indexOf("<!doctype html>");
                if (html5) {
                    htmlType = "HTML version -> HTML5 " + documentType.toString() + ")";
                    break;
                } else {
                    htmlType = "HTML version -> HTML4 (" + documentType.toString() + ")";
                    break;
                }
            } else {
                htmlType =  "HTML version -> UNKNOWN";
            }
        }
        return htmlType;
    }

    public HashMap getInternalExternal(Elements links) {
        HashMap<String, Integer> internalExternal = new HashMap<>();
        int internalLinks = 0;
        int externalLinks = 0;
        for (Element link : links){
            if (link.attr("href").contains("//")) {
                externalLinks++;
            } else {
                internalLinks++;
            }
        }
        internalExternal.put("internal", internalLinks);
        internalExternal.put("external", externalLinks);
        return internalExternal;
    }

}
