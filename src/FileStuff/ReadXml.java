package FileStuff;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by jackthebones on 27/04/15.
 */


public class ReadXml extends DefaultHandler {

    public static String PathXml;

    public static void getXmlURL() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler defaultHandler = new DefaultHandler() {

                String targetsTag = "close";
                String URLTag = "close";

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("TARGETS")) {
                        targetsTag = "open";
                    }
                    if (qName.equalsIgnoreCase("URL")) {
                        URLTag = "open";
                    }
                }

                public void characters(char ch[], int start, int length)
                        throws SAXException {
                    if (URLTag.equals("open")) {
                        String Sitios = new String(ch, start, length);
                        PathXml = Sitios;
                    }
                }

                public void endElement(String uri, String localName, String qName)
                        throws SAXException {
                    if (qName.equalsIgnoreCase("targets")) {
                        targetsTag = "close";
                    }
                    if (qName.equalsIgnoreCase("URL")) {
                        URLTag = "close";
                    }
                }
            };
            saxParser.parse("/home/jackthebones/Documents/PATHS.xml", defaultHandler);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    /**
     *
     */
    /*public static void getXmlConfig(){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();

            SAXParser saxParser = saxParserFactory.newSAXParser();

            DefaultHandler defaultHandler = new DefaultHandler(){

                String spiderTag="close";
                String recursivityTag="close";

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("SPIDER")) {
                        spiderTag = "open";
                    }
                    if (qName.equalsIgnoreCase("RECURSIVITY")) {
                        recursivityTag = "open";
                    }
                }
                public void characters(char ch[], int start, int length)
                        throws SAXException {

                    if (recursivityTag.equals("open")) {
                        String NumeroMaxRecur = new String(ch, start, length);
                        MaxRecursivityInt = Integer.parseInt(NumeroMaxRecur);
                    }
                }

                public void endElement(String uri, String localName, String qName)
                        throws SAXException {

                    if (qName.equalsIgnoreCase("SPIDER")) {
                        spiderTag = "close";
                    }
                    if (qName.equalsIgnoreCase("RECURSIVITY")) {
                        recursivityTag = "close";
                    }
                }
            };

            saxParser.parse("/home/disoji/Documents/SpiderConfig.xml", defaultHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

