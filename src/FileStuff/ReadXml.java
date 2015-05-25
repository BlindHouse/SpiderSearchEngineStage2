package FileStuff;

import org.apache.tika.exception.TikaException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by jackthebones on 27/04/15.
 * Clase encargada de leer parametros
 * dentro de los archivos de configuracion
 * XML.
 */


public class ReadXml extends DefaultHandler {

    //String que almacena el path dado
    public static String PathXml;
    public static int PesoXml;

    /**
     * Funcion encargada de leer el uri
     * dado en el archivo de configuracion
     * "paths.xml" y asignar el uri a una
     * variable. Ademas de asignarle el peso
     * al uri, y en caso de ser un directorio,
     * asignarle ese peso a todos los archivos
     * contenidos dentro de el.
     */
    public static void getXmlURL() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            DefaultHandler defaultHandler = new DefaultHandler() {

                String targetsTag = "close";
                String URLTag = "close";
                String PesoTag = "close";

                public void startElement(String uri, String localName, String qName,
                                         Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("TARGETS")) {
                        targetsTag = "open";
                    }
                    if (qName.equalsIgnoreCase("URL")) {
                        URLTag = "open";
                    }
                    if (qName.equalsIgnoreCase("PESO")) {
                        PesoTag = "open";
                    }
                }

                public void characters(char ch[], int start, int length)
                        throws SAXException {
                    if (URLTag.equals("open")) {
                        String Sitios = new String(ch, start, length);
                        PathXml = Sitios;
                        System.out.println("TRY XML : " + PathXml);

                    }
                    if(PesoTag.equals("open")){
                        String NumeroMaxRecur = new String(ch, start, length);
                        PesoXml = Integer.parseInt(NumeroMaxRecur);
                        System.out.println("TRY XML Peso : " + PesoXml);

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
                    if (qName.equalsIgnoreCase("PESO")) {
                        PesoTag = "close";
                        try {
                            Crawl.GetFiles();
                        } catch (IOException | TikaException e) {
                            e.printStackTrace();
                        }
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

