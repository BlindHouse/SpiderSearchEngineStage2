package FileStuff;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Created by jackthebones on 26/04/15.
 * Clase encargada de leer y extraer texto de los archivos,
 * asignados de manera local u online.
 */
public class TikaReader {

    /**
     * Funcion que parsea archivos locales, abriendolos
     * y crando un stream de lectura que sera asignado a
     * un string que a su vez sera partido en palabras
     * las cuales se asignaran a un String[].
     * @param file
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public static String ParsedText(String file) throws IOException, SAXException, TikaException {
        InputStream stream = new FileInputStream(file); //Crea el streamread del archivo para su lectura
        AutoDetectParser parser = new AutoDetectParser();//Autodetecta el formato del archivo a parsear
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        try {
            parser.parse(stream, handler, metadata);
            String Palabras = handler.toString().replaceAll(",", "")
                    .replaceAll("-", "").replaceAll("","").replaceAll("\"","")
                    .replaceAll("\\.","").replaceAll("™","");
            return Palabras;//Esto de hecho es lo que retorna el String de texto Parseado.
        } finally {
            stream.close();
        }
    }

    /**
     * Funcion que parsea archivos online, recibiendo
     * un inputStream de manera que no hay necesidad
     * de crear un stream para poder leer el texto.
     * Una vez leido, el texto se asigna a un string,
     * que a su vez sera partido en palabras las cuales
     * se asignaran a un String[].
     * @param file
     * @return
     * @throws IOException
     * @throws SAXException
     * @throws TikaException
     */
    public static String ParsedOnlineText(InputStream file) throws IOException, SAXException, TikaException {
        AutoDetectParser parser = new AutoDetectParser();//Autodetecta el formato del archivo a parsear
        BodyContentHandler handler = new BodyContentHandler(-1);
        Metadata metadata = new Metadata();
        try {
            parser.parse(file, handler, metadata);
            String Palabras = handler.toString().replaceAll(",", "")
                    .replaceAll("-", "").replaceAll("","").replaceAll("\"","")
                    .replaceAll("\\.","").replaceAll("™","");
            return Palabras;//Esto de hecho es lo que retorna el String de texto Parseado.
        }finally {
            file.close();//De aqui es posible que salga el error IO que hace que se caiga el programa con
                        //Ciertos PDFs online. La mayoria funciona bien though.
        }
    }
}
