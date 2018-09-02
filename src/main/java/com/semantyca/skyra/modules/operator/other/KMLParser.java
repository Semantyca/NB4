package com.semantyca.skyra.modules.operator.other;

import com.semantyca.skyra.modules.operator.model.embedded.PointCoordiantes;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class KMLParser {
    private static final String PATH_TO_DATA = "/kml/Document/Folder/Placemark/LineString/coordinates";

    public List<PointCoordiantes> process(String kmlFilePath) {
        List<PointCoordiantes> coordiantesList = new ArrayList<>();
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(kmlFilePath);

            XPath xPath = XPathFactory.newInstance().newXPath();
            Node nl = (Node) xPath.evaluate(PATH_TO_DATA, dDoc, XPathConstants.NODE);
            String coordsAsString = nl.getTextContent();
            // System.out.println(coordsAsString);
            String[] coords = coordsAsString.split(",");
            for (int i = 0; i < coords.length - 1; i++) {
                PointCoordiantes coordiantes = new PointCoordiantes(coords[i], coords[i = i + 1]);
                coordiantesList.add(coordiantes);
            }
            //  System.out.println(coordiantesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordiantesList;
    }

    public List<PointCoordiantes> process(byte[] bytes) {
        List<PointCoordiantes> coordiantesList = new ArrayList<>();
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(new ByteArrayInputStream(bytes));

            XPath xPath = XPathFactory.newInstance().newXPath();
            Node nl = (Node) xPath.evaluate(PATH_TO_DATA, dDoc, XPathConstants.NODE);
            String coordsAsString = nl.getTextContent();
            // System.out.println(coordsAsString);
            String[] coords = coordsAsString.split(",");
            for (int i = 0; i < coords.length - 1; i++) {
                PointCoordiantes coordiantes = new PointCoordiantes(coords[i], coords[i = i + 1]);
                coordiantesList.add(coordiantes);
            }
            //  System.out.println(coordiantesList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordiantesList;
    }

    private static boolean isLat(String valAsText) {
        float val = Float.parseFloat(valAsText);
        if (val > 49 && val < 51) {
            return true;
        }
        return false;
    }

    private static boolean isLng(String valAsText) {
        float val = Float.parseFloat(valAsText);
        if (val > 73 && val < 74) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        new KMLParser().process("/home/aida/Downloads/map/кар3.kml");

    }


}
