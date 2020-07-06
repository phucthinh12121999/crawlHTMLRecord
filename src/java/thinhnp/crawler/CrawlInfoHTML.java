/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhnp.crawler;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.XMLReader;

/**
 *
 * @author ADMIN
 */
public class CrawlInfoHTML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
         String path = new File(".").getCanonicalPath();
//        String[] href = {"https://www.fashionnova.com/collections/mens-tops?page=1", "https://www.fashionnova.com/collections/mens-tops?page=2", "https://www.fashionnova.com/collections/mens-tops?page=3", "https://www.fashionnova.com/collections/mens-tops?page=4", "https://www.fashionnova.com/collections/mens-tops?page=5", "https://www.fashionnova.com/collections/mens-tops?page=6", "https://www.fashionnova.com/collections/mens-tops?page=7", "https://www.fashionnova.com/collections/mens-tops?page=8", "https://www.fashionnova.com/collections/mens-tops?page=9", "https://www.fashionnova.com/collections/mens-tops?page=10"};
        String href = "https://www.fashionnova.com/collections/mens-tees-and-tanks/products/kelvin-short-sleeve-tee-pink";
        try {
                Document doc = Jsoup.connect(href).get();
                                Elements result = doc.select("body");
//                Elements result = doc.select("div[id$=main-page-content]");
                System.out.println("result: " + result.toString());
                XMLReader tagSoupReader = new org.ccil.cowan.tagsoup.Parser();
                Transformer identityTransformer = TransformerFactory.newInstance().newTransformer();


                org.xml.sax.InputSource sourceInputSource = new org.xml.sax.InputSource(new StringReader(result.toString()));
                Source xmlSource = new SAXSource(tagSoupReader, sourceInputSource);

                Result outputTarget = new StreamResult(path + "/web/WEB-INF/testing.xml");
                    identityTransformer.transform(xmlSource, outputTarget);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
//            org.w3c.dom.Document xslt = db.parse(new File(path + "/web/WEB-INF/dataCrawl.xsl"));
            org.w3c.dom.Document xml = db.parse(new File(path + "/web/WEB-INF/testing.xml"));

            xml.appendChild(xml.createElementNS(null, "item"));
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource styleSource = new StreamSource(new File(path + "/web/WEB-INF/dataCrawlInfo.xsl"));
            Transformer t = tf.newTransformer(styleSource);
            DOMSource source = new DOMSource(xml);
            StreamResult result1 = new StreamResult(path + "/web/WEB-INF/dataCrawl1.xml");
            t.transform(source, result1);
//            System.out.println(result1.getDocumentElement().getTextContent());
//            LSSerializer serializer = ((DOMImplementationLS) xml.getImplementation()).createLSSerializer();
//            System.out.println(serializer.writeToString(result1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
