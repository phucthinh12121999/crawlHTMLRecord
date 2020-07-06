/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhnp.uri;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author ADMIN
 */
public class UltimateURIResolver implements URIResolver {

    @Override
    public Source resolve(String href, String base) throws TransformerException {
        System.out.println("href: " + href);
        System.out.println("base: " + base);
        if (href != null && href.indexOf("https://www.fashionnova.com/") == 0) {
//        if(href != null && href.indexOf("https://vi.wikipedia.org") == 0){
            try {
                InputStream httpResult = new URL(href).openConnection().getInputStream();
                StreamSource ss = preProcessInputStream(httpResult);
                System.out.println("StreamSource: " + ss.toString());
                return ss;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private StreamSource preProcessInputStream(InputStream httpResult) throws IOException {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = new InputStreamReader(httpResult, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        sb.append("<?xml version=\"1.0\" enconding=\"UTF-8\"?>\n");
        while ((line = br.readLine()) != null) {
            if (line.contains("<html") && !line.contains("xmlns=\"http://www.w3.org/1999/xhtml\"")) {
                line = line.replace("<html", "<html xmlns=\"http://www.w3.org/1999/xhtml\"");
            }
            if (line.contains("src") || line.contains("href")) {
                line = line.replace("&", "&amp;");
            }
            line = line.replace("&reg;", "&#174;").replace("&hellip;", "").replace("&nbsp;", "");
            sb.append(line + "\n");
        }
        System.out.println("sb: " + sb.toString());
        InputStream htmlResult = new ByteArrayInputStream(sb.toString().getBytes());
        return new StreamSource(htmlResult);
    }

}
