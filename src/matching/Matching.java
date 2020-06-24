/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matching;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import static com.itextpdf.kernel.pdf.PdfName.ColorSpace;
import static com.itextpdf.kernel.pdf.PdfName.Colors;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JOptionPane;
import ufps.util.colecciones_seed.ListaCD;

/**
 *
 * @author Acer
 */
public class Matching {

    //Document document = new Document(new PdfDocument(1));
    
    public Matching() {
    }

    /**
     * metodo boyer moore horspool sunday para buscar el patron en el texto
     *
     * @param T Texto
     * @param n tamaño del texto
     * @param P Patron
     * @param m tamaño del patron
     * @return la cantidad de veces que se repite el patron
     */
    public int bmhs(String T, int n, String P, int m) {
        int cont = 0;
        int MAXCHAR = 255;
        int[] alfabeto = new int[MAXCHAR + 1];
        //se llena el vector con el patron a buscar
        for (int i = 0; i <= MAXCHAR; i++) {
            alfabeto[i] = m + 1;
        }
        //procesamiento para indicar de mayor a menor las letras del patron
        for (int j = 1; j <= m; j++) {
            alfabeto[(int) P.charAt(j - 1)] = m - j + 1;
        }
        int i = m;
        while (i <= n) {//comparacion de caracteres del patron vs el texto
            int k = i;
            int j = m;
            while ((j > 0) && (T.charAt(k - 1) == P.charAt(j - 1))) {
                j--;
                k--;
            }
            if (j == 0) {//si entra quiere decir que encontro el patron
                cont++;
            }
            if ((i + 1) > (n - 1)) {
                break;
            }
            //incremento de los desplazamientos del caracter de la der en base al patron
            i = i + alfabeto[(int) T.charAt(i)];
        }
        return cont;
    }

    /**
     * Metodo para generar el pdf con la libreria iTextPdf5.5.1
     * @param T texto
     * @param n tamaño del texto
     * @param P patron 
     * @param m tamaño del patron
     * @return un pdf en la carpeta del proyecto
     */
    public void abrirPdf(String archivo){
     
        int abrir=JOptionPane.showConfirmDialog(null, "¿Desea abrir el pdf?","Con los pasos del bhms", JOptionPane.YES_NO_OPTION);
        
        if(abrir==0){
           
            File f=new File(archivo + ".pdf");
            try{
            Desktop.getDesktop().open(f);
            }catch(Exception e){
                System.out.println("error" + e); 
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "No se va a abrir el documento");
        }
        
    }
    
    public ListaCD generar(String T, int n, String P, int m) throws FileNotFoundException {

        ListaCD indices=new ListaCD();
        String nombre=JOptionPane.showInputDialog(null, "Escriba el nombre del archivo");
       FileOutputStream f = new FileOutputStream(nombre+ ".pdf");
        PdfWriter writer = new PdfWriter(f);
        PdfDocument pdfDoc = new PdfDocument(writer);

        Document document = new Document(pdfDoc, PageSize.A1);

            try{
            document.add(new Paragraph("PDF con los pasos del Metodo de Matching Boyer Moore Horspool Sunday(BMHS)").setFontSize(20));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Estudiantes: \nDaniel José Caballero Sánchez: 1151267 \nJuan Sebastian Amaya Ovalle: 1151772").setFontSize(20));
            Paragraph par= new Paragraph();
            par.add("Repositorio de Gitlab: https://gitlab.com/juanse50/bhmsfinal");
            par.setFont(PdfFontFactory.createFont(FontConstants.HELVETICA));
            par.setFontSize(20);
            document.add(par);

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Texto Inicial: ").setFontSize(20));
            }catch(Exception e){
                System.out.println("Hubo un error " + e); 
            }
            document.add(new Paragraph("\n"));
            int cont = 0;
            int MAXCHAR = 255;
            int[] alfabeto = new int[MAXCHAR + 1];
            for (int i = 0; i <= MAXCHAR; i++) {
                alfabeto[i] = m + 1;
            }

            for (int j = 1; j <= m; j++) {
                alfabeto[(int) P.charAt(j - 1)] = m - j + 1;
            }
            int i = m;//tam patron
            while (i <= n) {//tam texto
                int k = i;
                int j = m;
                while ((j > 0) && (T.charAt(k - 1) == P.charAt(j - 1))) {
                    j--;
                    k--;
                }
                if (j == 0) {                   
                    cont++;
                    int v=k;
                    indices.insertarAlFinal(v);
                }
                if ((i + 1) > (n - 1)) {
                    break;
                }
                i = i + alfabeto[(int) T.charAt(i)];
            }
            String x [] = T.split(P);
            String p="";
            Paragraph pa = new Paragraph();
            indices.insertarAlFinal(cont);
            for(int y=0; y<x.length; y++){
              
                 pa.add(x[y]);
                 if(y!=x.length-1){
                 pa.add(new Paragraph(P).setFontColor(ColorConstants.ORANGE));
                 }
            }
             pa.setFontSize(20);
            document.add(pa);
            document.add(new Paragraph("\n").setFontSize(20));
            document.add(new Paragraph("El tamaño de la cadena es: " + T.length()).setFontSize(20));
            document.add(new Paragraph("El patrón fue encontrado " + cont + " veces").setFontSize(20));
            document.add(new Paragraph("\n"));
            document.close();
            

            System.out.println("¡Se ha generado el PDF!");
            this.abrirPdf(nombre);
            return indices;
        
        
    }

}
