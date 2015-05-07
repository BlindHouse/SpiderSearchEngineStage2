package FileStuff;

import org.apache.tika.exception.TikaException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;

import static FileStuff.ReadXml.PathXml;

/**
 * Created by jackthebones on 25/04/15.
 */
public class Crawl {

    public static Queue OnlinePathQueue = new LinkedList<>();
    public static Queue<Object> ParsedLinks = new LinkedList<>();

    public static void GetFilesOnline(Queue PathQueue) throws IOException, TikaException, SAXException {
        while (!PathQueue.isEmpty()){
            String link  = (String) PathQueue.remove();
            ParsedLinks.add(link);
            System.out.println("------"+link);

            if(link.endsWith("pdf") || link.endsWith("txt") || link.endsWith("docx")
                    || link.endsWith("odt")){

                try {
                URL archivoOnline = new URL(link);
                InputStream x = archivoOnline.openStream();
                TikaReader.ParsedOnlineText(x);
                } catch (IOException e) {
                    e.printStackTrace();
                    continue;
                } catch (SAXException e) {
                    e.printStackTrace();
                    continue;
                } catch (TikaException e) {
                    e.printStackTrace();
                    continue;
                }
            }

            Document doc = Jsoup.connect(link)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true)
                    .ignoreContentType(true)
                    .timeout(5000)
                    .get();


            Elements links = doc.select("a[href]");
            //Elements txts = doc.select("a[href\\$=.zip]");
            //System.out.println()
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

    public static void GetFilesLocally() throws IOException {
        Files.walk(Paths.get(PathXml)).forEach(filePath -> { //Crawling process (Using Java 8)
            if (Files.isRegularFile(filePath)) {
                if (filePath.toString().endsWith(".pdf") || filePath.toString().endsWith(".docx") ||
                        filePath.toString().endsWith(".txt")){
                    try {
                        TikaReader.ParsedText(filePath.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (TikaException e) {
                        e.printStackTrace();
                    }
                    System.out.println(filePath);
                }
            }
        });
    }

    public static void GetFiles() throws IOException, TikaException, SAXException {
        if (PathXml.startsWith("/")){
            GetFilesLocally();
        }
        else{
            OnlinePathQueue.add(PathXml);
            GetFilesOnline(OnlinePathQueue);
        }
    }
}

