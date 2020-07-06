/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhnp.crawler;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import jdk.internal.org.xml.sax.InputSource;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.XMLReader;

/**
 *
 * @author ADMIN
 */
public class CrawlTestHTML {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String path = new File(".").getCanonicalPath();
//        String[] href = {"https://www.fashionnova.com/collections/mens-tops?page=1", "https://www.fashionnova.com/collections/mens-tops?page=2", "https://www.fashionnova.com/collections/mens-tops?page=3", "https://www.fashionnova.com/collections/mens-tops?page=4", "https://www.fashionnova.com/collections/mens-tops?page=5", "https://www.fashionnova.com/collections/mens-tops?page=6", "https://www.fashionnova.com/collections/mens-tops?page=7", "https://www.fashionnova.com/collections/mens-tops?page=8", "https://www.fashionnova.com/collections/mens-tops?page=9", "https://www.fashionnova.com/collections/mens-tops?page=10"};
        String href = "https://www.fashionnova.com/collections/mens-tees-and-tanks?page=1";
        try {
//            if (href != null && href.indexOf("https://www.fashionnova.com/") == 0) {
////        if(href != null && href.indexOf("https://vi.wikipedia.org") == 0){
//                    InputStream httpResult = new URL(href).openConnection().getInputStream();
//                    StreamSource ss = preProcessInputStream(httpResult);
//                    System.out.println("StreamSource: " + ss.toString());
//            }
//            StringBuffer sb = new StringBuffer();
//             InputStream httpResult = new URL(href).openConnection().getInputStream();
//            InputStreamReader isr = new InputStreamReader(httpResult, "UTF-8");
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            sb.append("<?xml version=\"1.0\" enconding=\"UTF-8\"?>\n");
//            while ((line = br.readLine()) != null) {
//                if (line.contains("<html") && !line.contains("xmlns=\"http://www.w3.org/1999/xhtml\"")) {
//                    line = line.replace("<html", "<html xmlns=\"http://www.w3.org/1999/xhtml\"");
//                }
//                if (line.contains("src") || line.contains("href")) {
//                    line = line.replace("&", "&amp;");
//                }
//                line = line.replace("&reg;", "&#174;").replace("&hellip;", "").replace("&nbsp;", "");
//                sb.append(line + "\n");
//            }
//            System.out.println("sb: " + sb.toString());
//            InputStream htmlResult = new ByteArrayInputStream(sb.toString().getBytes());
//            for (int i = 0; i < href.length; i++) {
                Document doc = Jsoup.connect(href).get();
                Elements result = doc.select("div.product-item");
                System.out.println("result: " + result.toString());
                XMLReader tagSoupReader = new org.ccil.cowan.tagsoup.Parser();
                Transformer identityTransformer = TransformerFactory.newInstance().newTransformer();

//        Reader sourceReader = new FileReader(sourceFile);
                org.xml.sax.InputSource sourceInputSource = new org.xml.sax.InputSource(new StringReader(result.toString()));
                Source xmlSource = new SAXSource(tagSoupReader, sourceInputSource);
                File file = new File(path + "/web/WEB-INF/testing.xml");
                Result outputTarget = new StreamResult(path + "/web/WEB-INF/testing.xml");
//                if (file.length() == 0) {
                    identityTransformer.transform(xmlSource, outputTarget);
//                }else {
//                    identityTransformer.transform(xmlSource, outputTarget);
//                    OutputStream writer = new FileOutputStream(file, true);
//                    writer.write('\n');
//                    writer.close();
//                }

//            }
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
//            org.w3c.dom.Document xslt = db.parse(new File(path + "/web/WEB-INF/dataCrawl.xsl"));
            org.w3c.dom.Document xml = db.parse(new File(path + "/web/WEB-INF/testing.xml"));

            xml.appendChild(xml.createElementNS(null, "collections"));
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource styleSource = new StreamSource(new File(path + "/web/WEB-INF/dataCrawl.xsl"));
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
//        try {
//            String path = new File(".").getCanonicalPath();
////            Result outputTarget = new StreamResult(path + "/web/WEB-INF/testing.xml");
//            DOMResult rs = CrawlerUltimate.crawl(path + "/web/WEB-INF/dataCrawl.xml", path + "/web/WEB-INF/dataCrawl.xsl");
//            TransformerFactory factory = TransformerFactory.newInstance();
//            Transformer transformer = factory.newTransformer();
//            StreamResult sr = new StreamResult(new FileOutputStream(path + "/web/WEB-INF/testing.xml"));
//            transformer.transform(new DOMSource(rs.getNode()), sr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

//    public static org.w3c.dom.Document transformXML(org.w3c.dom.Document xml, org.w3c.dom.Document xslt) throws Exception {
//        Source xmlSource = new DOMSource(xml);
//        Source xsltSource = new DOMSource(xslt);
//        DOMResult result = new DOMResult();
//
//        System.out.println("xmlSource: " + xmlSource.toString());
//
//        // the factory pattern supports different XSLT processors
//        TransformerFactory transFact
//                = TransformerFactory.newInstance();
//   
//        Transformer trans = transFact.newTransformer(xsltSource);
//
//        trans.transform(xmlSource, result);
//
//        org.w3c.dom.Document resultDoc = (org.w3c.dom.Document) result.getNode();
//
//        return resultDoc;
//    }
//    public static StreamSource preProcessInputStream(InputStream httpResult) throws IOException {
//        StringBuffer sb = new StringBuffer();
//        InputStreamReader isr = new InputStreamReader(httpResult, "UTF-8");
//        BufferedReader br = new BufferedReader(isr);
//        String line;
//        sb.append("<?xml version=\"1.0\" enconding=\"UTF-8\"?>\n");
//        while ((line = br.readLine()) != null) {
//            if (line.contains("<html") && !line.contains("xmlns=\"http://www.w3.org/1999/xhtml\"")) {
//                line = line.replace("<html", "<html xmlns=\"http://www.w3.org/1999/xhtml\"");
//            }
//            if (line.contains("src") || line.contains("href")) {
//                line = line.replace("&", "&amp;");
//            }
//            line = line.replace("&reg;", "&#174;").replace("&hellip;", "").replace("&nbsp;", "");
//            sb.append(line + "\n");
//        }
//        System.out.println("sb: " + sb.toString());
//        InputStream htmlResult = new ByteArrayInputStream(sb.toString().getBytes());
//        return new StreamSource(htmlResult);
//    }
}
