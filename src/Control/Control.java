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

    public String abrir() {
        String cad = "";
        if (f.showDialog(null, "abrir") == JFileChooser.APPROVE_OPTION) {
            txt = f.getSelectedFile();
            if (txt.canRead()) {

                if (txt.getName().endsWith("txt")) {

                    texto = this.leer(txt);
                    m.setTexto(texto);
                    cad = texto;
                    System.out.println("largo de la cadena" + texto.length());
                    System.out.println("Archivo cargado correctamente");                    

                }

            } else {
                cad = "Seleccione un archivo valido";
            }
        }
        return cad;
    }
    
    public String leer(File archivo){
     
        String d="";
        
        try{
            FileInputStream fl= new FileInputStream(archivo);
            int ascii;
            while((ascii=(int)fl.read())!=-1){
               char c=(char)ascii;
               d+=c;
            }
            
            
        }catch(Exception e){
            
        }
        return d;
    }
    
    public int BHMS(String p){
        return m.getBHMS(p);
    }

}
