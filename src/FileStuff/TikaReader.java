package FileStuff;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Created by jackthebones on 26/04/15.
 */
public class TikaReader {
    public static String ParsedText(String file) throws IOException, SAXException, TikaException {
        InputStream stream = new FileInputStream(file); //Crea el streamread del archivo para su lectura
        AutoDetectParser parser = new AutoDetectParser();//Autodetecta el formato del archivo a parsear
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try {
            parser.parse(stream, handler, metadata);
            String PlainText = handler.toString();
            System.out.println(PlainText);//handler = texto parseado. Se realiza .toString() para imprimir.
            return handler.toString();//Esto de hecho es lo que retorna el String de texto Parseado.
        } finally {
            stream.close();
        }
    }

    public static String ParsedOnlineText(InputStream file) throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();//Autodetecta el formato del archivo a parsear
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        try {
            parser.parse(file, handler, metadata);
            System.out.println(handler.toString());//handler = texto parseado. Se realiza .toString() para imprimir.
            return handler.toString();//Esto de hecho es lo que retorna el String de texto Parseado.
        }finally {
            file.close();//De aqui es posible que salga el error IO que hace que se caiga el programa con
                        //Ciertos PDFs online. La mayoria funciona bien though.
        }
    }
}
