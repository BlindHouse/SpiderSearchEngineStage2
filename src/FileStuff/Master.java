package FileStuff;

import Estructuras.ABBX;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;
import static FileStuff.Crawl.LinkStorage;
import static Estructuras.ABBX.FinalTree;
import static Estructuras.ArbolAVL.FinalBinaryTree;

import java.io.IOException;

/**
 * Created by jackthebones on 25/04/15.
 */
public class Master {
    public static void main(String args[]) throws IOException, TikaException, SAXException {
        ReadXml.getXmlURL();
        ABBX.ReadParseLoop(LinkStorage);

        //FUNCION DE BOTON DE BUSQUEDA
        FinalTree.Buscar("and");
        FinalBinaryTree.DeMayor_Menor();
    }
}
