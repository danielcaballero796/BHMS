/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matching;

/**
 *
 * @author Acer
 */
public class Matching {

 
    private String texto;

    public Matching() {
    }

    public Matching(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    
    
    public int getBHMS(String p){
        int c=0;
        int n= texto.length();
        int m= p.length();
        //int ascii=255;
        int [] ascii=new int [255];
        for(int i=ascii.length-1; i>0; i--){
           ascii[i]=m+1; 
        }
        int x=1;
       while(x<=m){
           
           ascii[(int) p.charAt(x-1)]= (m-x)+1;
           
         x++;  
       }
       System.out.println(ascii[110]);
       int i=m;
       int j=m;
       
       while(i<=n){
         // System.out.println(i + " este es i"); 
           while(i<n && j>0 && texto.charAt(i-1)==p.charAt(j-1)){
             i--;
             j--;
           }
           if(j==0){
             
               c++;
               i+=m;
             //  System.out.println(i + " este es i");
           }
           //System.out.println(c + "cantidad");
           j=m;
          // System.out.println(i + " este es i");
           
           if((i+1) > (n-1)){
              break; 
           }
           
           i+=ascii[(int)texto.charAt(i)];
         //  System.out.println(i + " este es i");
       }
       return c;
        
    }
    
    public static void main(String [] args){
       
        Matching m= new Matching();
        String c="cordial saludo \n" +
"\n" +
"\n" +
"f2of2ingo34no3nvjn3ngo\n" +
"\n" +
"\n" +
"juan\n" +
"\n" +
"\n" +
"oj2enfoi2nvoi3noi3inobnoiv2juanifj24inf3iu4ngiu344i2unrjngotjh3i4ngong2\n" +
"\n" +
"juan";
        String p="juan";
       // System.out.println(c.charAt(25-1));
        m.setTexto(c);
        System.out.println(m.getBHMS(p));
    }
    
    
}
