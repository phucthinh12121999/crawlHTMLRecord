/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhnp.crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;
import thinhnp.uri.UltimateURIResolver;

/**
 *
 * @author ADMIN
 */
public class CrawlerUltimate {

    public static DOMResult crawl(String configPath, String xslPath) throws SAXException, IOException, ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {

        try {
            StreamSource xslCate = new StreamSource(xslPath);
            System.out.println("streamScource" + xslCate.toString());
            InputStream is = new FileInputStream(configPath);
            System.out.println("inputStream" + is.toString());
            TransformerFactory factory = TransformerFactory.newInstance();
            DOMResult domRs = new DOMResult();
            UltimateURIResolver resolver = new UltimateURIResolver();
            System.out.println("resolver: " + resolver.toString());
            factory.setURIResolver(resolver);
            Transformer transformer = factory.newTransformer(xslCate);
            transformer.transform(new StreamSource(is), domRs);
            System.out.println("domRs: " + domRs.toString());
            return domRs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
