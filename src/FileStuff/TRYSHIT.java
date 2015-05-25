package FileStuff;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by jackthebones on 25/05/15.
 */
public class TRYSHIT {
    public static void main(String [] args) throws IOException {
        File htmlFile = new File("\"/home/jackthebones/Documents/PARSE PATH/\"");
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
