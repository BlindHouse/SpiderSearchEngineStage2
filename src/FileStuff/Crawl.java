package FileStuff;

import Estructuras2.Nodo;
import org.apache.tika.exception.TikaException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;
import Estructuras.ABBX;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import static FileStuff.ReadXml.PathXml;
import static FileStuff.ReadXml.PesoXml;

/**
 * Created by jackthebones on 25/04/15.
 * Clase encargada de hacer Crawling en los directorios
 * e identificar los archivos aptos a ser parseados.
 */
public class Crawl {

    //Queue que contiene los links a ser parseados, es la cola que alimenta al crawl.
    public static Queue OnlinePathQueue = new LinkedList<>();
    //Queue que contiene los links que ya han sido parseados, para de esta manera no parsearlos nuevamente.
    public static Queue<Object> ParsedLinks = new LinkedList<>();
    //Arbol contenedor de los links y el peso
    public static ABBX LinkStorage= new ABBX();

    /**
     * Analiza un url dado, para saber si
     * dentro de este url, se encuentran
     * archivos que cumplen la condicion
     * de que se les pueda extraer texto,
     * y devulve el path o uri de cada uno
     * para ser insertadas en el arbol de
     * direcciones, ademas recibe el peso,
     * de dicho path, para que en caso de tener
     * varios archivos, estos heredan el peso
     * del uri que los contiene.
     * @param PathQueue
     * @param Peso
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    public static void GetFilesOnline(Queue PathQueue, int Peso) throws IOException, TikaException, SAXException {
        while (!PathQueue.isEmpty()){
            String link  = (String) PathQueue.remove();
            ParsedLinks.add(link);

            if(link.endsWith("pdf") || link.endsWith("txt") || link.endsWith("docx")
                    || link.endsWith("odt")){

                LinkStorage.insertar(new Nodo(link, Peso));
            }

            Document doc = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .get();


            Elements links = doc.select("a[href]");
            for (Element link1 : links){
                if(link1.attr("abs:href").contains(link)){
                    String absUrl = link1.attr("abs:href");
                    if (absUrl == null || absUrl.length() == 0) {
                        continue;
                    }
                    else{
                        if(!ParsedLinks.contains(absUrl)){
                            PathQueue.add(absUrl);//DE AQUI PUEDO SACAR UNO A UNO LOS LINKS PARA LA FUNCION QUE WILSON DIJO
                        }
                    }
                }
            }
        }
    }

    /**
     * Funcion que hace el crawl localmente que encuentra los
     * tipos de archivo a los que se le puede extraer texto,
     * y devuelve las direcciones o paths de los archivos,
     * para ser insertados en el arbol que almacena las direcciones.
     * @param Peso
     * @throws IOException
     */
    public static void GetFilesLocally(int Peso) throws IOException {
        Files.walk(Paths.get(PathXml)).forEach(filePath -> { //Crawling process (Using Java 8)
            if (Files.isRegularFile(filePath)) {
                if (filePath.toString().endsWith(".pdf") || filePath.toString().endsWith(".docx") ||
                        filePath.toString().endsWith(".txt")){
                    LinkStorage.insertar(new Nodo(filePath, Peso));
                }
            }
        });
    }

    /**
     * Funcion que detecta el tipo de uri dada por el xml,
     * y que a su vez, decide cual metodo utilizar para realizar
     * el proceso de extraccion de texto. (Local u Online)
     * @throws IOException
     * @throws TikaException
     * @throws SAXException
     */
    public static void GetFiles() throws IOException, TikaException, SAXException {
        if (PathXml.startsWith("/")){
            GetFilesLocally(PesoXml);
        }
        else{
            OnlinePathQueue.add(PathXml);
            GetFilesOnline(OnlinePathQueue, PesoXml);
        }
    }
}

