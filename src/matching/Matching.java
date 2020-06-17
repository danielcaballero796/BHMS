/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matching;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 *
 * @author Acer
 */
public class Matching {

    Document document = new Document();
    
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
    public String generar(String T, int n, String P, int m) {

        try {
            File f = new File("BMHS.pdf");

            try {
                PdfWriter.getInstance(this.document, new FileOutputStream(f));
            } catch (FileNotFoundException fileNotFoundException) {
                System.out.println("(No se encontró el fichero para generar el pdf)" + fileNotFoundException);
            }

            document.addTitle("Metodos de Matching Boyer Moore Horspool Sunday(BMHS)");
            document.addSubject("PDF con los pasos del Metodo de Matching Boyer Moore Horspool Sunday(BMHS)");
            document.addAuthor("Daniel José Caballero Sánchez y Juan Sebastian Amaya Ovalle");
            document.addCreator("Daniel José Caballero Sánchez y Juan Sebastian Amaya Ovalle estudiantes de la UFPS");
            document.open();

            document.add(new Paragraph("PDF con los pasos del Metodo de Matching Boyer Moore Horspool Sunday(BMHS)", FontFactory.getFont("arial", 18, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Estudiantes: \nDaniel José Caballero Sánchez: 1151267 \nJuan Sebastian Amaya Ovalle: 1151772", FontFactory.getFont("arial", 14, Font.BOLD, BaseColor.BLACK)));
            document.add(new Paragraph("Repositorio de Gitlab: https://gitlab.com/juanse50/bhms", FontFactory.getFont("arial", 12, Font.BOLD, BaseColor.BLACK)));

            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Texto Inicial: ", FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(T, FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.RED)));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Patron a buscar: ", FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph(P, FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.RED)));
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
                    //System.out.println("pintar desde: " + (k) + ", hasta: " + ((k) + (m - 1)));
                    document.add(new Paragraph("Patron encontrado desde la posición: " + (k) + ", hasta la posición: " + ((k) + (m - 1)), FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK)));
                    cont++;

                }
                if ((i + 1) > (n - 1)) {
                    break;
                }
                i = i + alfabeto[(int) T.charAt(i)];
            }
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("El tamaño de la cadena es: " + T.length(), FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("El patrón fue encontrado " + cont + " veces", FontFactory.getFont("arial", 12, Font.NORMAL, BaseColor.BLACK)));
            document.add(new Paragraph("\n"));
            document.close();

            System.out.println("¡Se ha generado el PDF!");
            return f.getAbsolutePath();
        } catch (DocumentException documentException) {
            System.out.println("Se ha producido un error al generar un documento: " + documentException);
        }
        return null;
    }

}
