import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ShaulaLuz on 29/01/2015.
 */

public class GeneradorEstados{

    /*Dimensiones*/
    final int cols = 3; //Número de filas por cara
    final int rows = 3; //Número de columnas por cara
    final int face = 1; /*Número de caras: En caso de ser cubo face=6
                          En caso de ser cuadro mágico o grafos =1
    /*------------*/

    /*Órdenes*/
    public int instruccion;
    /*--------*/

    Random stID = new Random();

    int [][][] initSt = new int [face][rows][cols];
    int [][][] curtSt = new int [face][rows][cols];

    public class State{
        int [][][] State;
        boolean vecinos;
        boolean valido;
        boolean visitado;
        public State(boolean valido, boolean visitado, boolean vecinos, int[][][] State){
            State = State;
            vecinos=vecinos;
            valido=valido;
            visitado=visitado;
        }
    }

    ArrayList<Integer[][]> states = new ArrayList<Integer[][]>();

    /*incializar estado 0*//*
    public void setInitSt() {
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        Random rand = new Random();     //Número al azar para llenar la matriz
        for (int i = 0; i < face; i++){     //Número de cara siendo llenada
            for(int j=0; j<rows; j++){      //Número de fila siendo llenada
                for(int k=0; j<cols;k++){   //Número de columna siendo llenada
                    int r = rand.nextInt(8);    //Se elige un número al azar, entre 0 y 8
                    while(numeros.contains(r)){     //Se revisa que ése número no esté ya asignado
                        r = rand.nextInt(8);
                    }
                    numeros.add(r);
                    initSt[face][rows][cols]= r;
                }
            }
        }
    }
    *//*-------------------*/

    enum instruccion{
       arriba, abajo, izquierda, derecha
    }


    /*Generar todos los estados posibles*/
    public ArrayList genSt(int[][][] initSt, instruccion){

        switch(face){

            case 1: break;

            case 6: break;

            default: break;

        }



        return states;
    }
    /*------------------------------------*/


        public static void main (String[]args){
        System.out.print("Sup nigga'");
    }

}
