/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import matching.Matching;

/**
 *
 * @author danis
 */
public class Control {

    JFileChooser f;
    File txt;
    FileInputStream in;
    String texto;
    Matching m = new Matching();

    public Control() {
    }

    public String leer(File archivo) {
        String d = "";

        try {
            FileInputStream fl = new FileInputStream(archivo);
            int ascii;
            while ((ascii = (int) fl.read()) != -1) {
                char c = (char) ascii;
                d += c;
            }

        } catch (Exception e) {

        }
        return d;
    }

    public int BHMS(String t, String p) {
        return m.bmhs(t, t.length(), p, p.length());
    }

}
