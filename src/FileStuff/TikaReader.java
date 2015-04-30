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
        //InputStream stream = OnlinePathConnection.StartConnection();
        AutoDetectParser parser = new AutoDetectParser();//Autodetecta el formato del archivo a parsear
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try {
            parser.parse(stream, handler, metadata);
            System.out.println(handler.toString());//handler = texto parseado. Se realiza .toString() para imprimir.
            return handler.toString();//Esto de hecho es lo que retorna el String de texto Parseado.
        } finally {
            stream.close();
        }
    }
}
