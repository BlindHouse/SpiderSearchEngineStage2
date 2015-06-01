/*
 * Copyright (C) 2015 jackthebones
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Results {
    public static String linkedlinks = "No results";
    public static JEditorPane myEditorPane;


    public void init() {
        myEditorPane = new JEditorPane("text/html","");
        myEditorPane.setEditable(false);

        myEditorPane.setText(linkedlinks);
        myEditorPane.addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (Desktop.isDesktopSupported()) {

                    if(e.getURL().toString().startsWith("file")){
                        System.out.println("LOCAL");
                        try {
                            Desktop.getDesktop().open(new File(e.getURL().getFile()).getParentFile());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    else{
                        System.out.println("ONLINE");
                        try {
                            Desktop.getDesktop().browse(e.getURL().toURI());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });


        JScrollPane scroller = new JScrollPane();
        scroller.setViewportView(myEditorPane);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JFrame frame = new JFrame("Resultados");

        JPanel northPanel = new JPanel();


        frame.getContentPane().add(northPanel, "North");
        frame.getContentPane().add(scroller);


        frame.addWindowListener(new MyWindowAdapter());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        frame.show();

    }
    class MyWindowAdapter extends WindowAdapter
    {
    }
    public static void main(String args[])
    {
        Results demo = new Results();
        demo.init();
    }


} 