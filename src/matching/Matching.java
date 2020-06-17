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

    public Matching() {
    }

    public int bmhs(String T, int n, String P, int m) {
        int cont = 0;
        int MAXCHAR = 255;
        int[] alfabeto = new int[MAXCHAR + 1];
        for (int i = 0; i <= MAXCHAR; i++) {
            alfabeto[i] = m + 1;
        }
        for (int j = 1; j <= m; j++) {
            alfabeto[(int) P.charAt(j - 1)] = m - j + 1;
        }
        int i = m;
        while (i <= n) {
            int k = i;
            int j = m;
            while ((j > 0) && (T.charAt(k - 1) == P.charAt(j - 1))) {
                j--;
                k--;
            }
            if (j == 0) {
                System.out.println("pintar desde: " + (k) + ", hasta: " + ((k) + (m - 1)));
                cont++;
            }
            if ((i + 1) > (n - 1)) {
                break;
            }
            i = i + alfabeto[(int) T.charAt(i)];
        }
        return cont;
    }

    public static void main(String[] args) {

        Matching m = new Matching();
        String f = ".";
        String c = "abc            ljdkjdgjhskjdbfiufbkjrbabcljhfjfjkfkjhabcjlksldklfhjdhk      abc abc" + f;

//                  0123456789012345678901234567890123456789012345678901234567890123
//                           10        20        30        40        50        60
        String p = "abc";
        //m.bmhs(c, c.length(), p, p.length());
    }

}
