package FileStuff;

import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static FileStuff.ReadXml.PathXml;

/**
 * Created by jackthebones on 25/04/15.
 */
public class LocalCrawl {
    public static void GetFiles() throws IOException {
        Files.walk(Paths.get(PathXml)).forEach(filePath -> {
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
}

