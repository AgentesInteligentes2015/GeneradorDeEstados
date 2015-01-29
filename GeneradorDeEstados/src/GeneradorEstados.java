import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ShaulaLuz on 29/01/2015.
 */
public class GeneradorEstados{

    final int cols = 3;
    final int rows = 3;
    final int face = 1;

    Random stID = new Random();

    int [][][] initSt = new int [face][rows][cols];
    ArrayList<Integer[][][]> states = new ArrayList<Integer[][][]>();
    /*incializar estado 0*/

    public void setInitSt() {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        Random rand = new Random();
        for (int i = 0; i < face; i++){
            for(int j=0; j<rows; j++){
                for(int k=0; j<cols;k++){
                   initSt[face][rows][cols]= rand.nextInt(8);
                }

            }
        }
    }

    /*-------------------*/


        public static void main (String[]args){
        System.out.print("Sup nigga'");
    }

}
