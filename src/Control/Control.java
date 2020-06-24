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
import ufps.util.colecciones_seed.ListaCD;

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

    /**
     * Metodo para leer el archivo
     * @param archivo
     * @return una cadena con el texto del archivo que se ha leido
     */
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

    /**
     * Metodo de la conexion con el negocio para la busqueda el patron y generacion del pdf
     * @param t texto
     * @param p patron
     * @return la cantidad de veces que se repite el patron
     */
    public ListaCD BHMS(String t, String p) {
        ListaCD l= new ListaCD();
        try{
        l =m.generar(t, t.length(), p, p.length());
        }catch(Exception e){
            System.out.println("No se pudo " + e); 
        }
        return l;
    }

}
