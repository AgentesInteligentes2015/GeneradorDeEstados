import javax.swing.*;
import javax.swing.text.StringContent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by ShaulaLuz on 29/01/2015.
 */

public class GeneradorEstados {

    /*Dimensiones*/
    final int cols = 3; //Número de filas por cara
    final int rows = 3; //Número de columnas por cara
    final int face = 1; /*Número de caras: En caso de ser cubo face=6
                          En caso de ser cuadro mágico o grafos =1
    /*------------*/

    int rs = 1;    //Valores para indexar las matrices
    int cl = 1;
    int fc = 0;

    Random stID = new Random();

    int initSt[][][] = new int[face][rows][cols];
    int [][][] curInfo;

    public class State {
        int info[][][];
        HashMap vecinos = new HashMap<String, State>();
        boolean valido;
        boolean visitado;
    }

    ArrayList<State> states = new ArrayList<State>(9);
    State initState = new State();

    public enum instrucciones {       //Instrucciones de movimiento
        arriba, abajo, izquierda, derecha
    }

    /*incializar estado 0*/
    public State setInitSt(){
       ArrayList<Integer> numeros = new ArrayList<Integer>();
        int r;
        Random rand = new Random();     //Número al azar para llenar la matriz

        for (int i = 0; i < face; i++){     //Número de cara siendo llenada
            for(int j=0; j<rows; j++){      //Número de fila siendo llenada
                System.out.print("\n");

                for(int k=0; k<cols;k++){   //Número de columna siendo llenada
                                                //Se revisa que ése número no esté ya asignado
                    r = rand.nextInt(9);

                    while (numeros.contains(r)) {
                        r = rand.nextInt(9);
                    }

                    initSt[0][j][k] = r;
                    System.out.print(" " + initSt[0][j][k] + " ");
                    numeros.add(r);
                }
            }
        }
        initState.info = initSt;
        initState.valido = true;
        initState.visitado = false;
        states.add(initState);
        return initState;
    }
    /*-------------------*/

    public boolean validate(State st){
        if(cl <0 || cl > cols || rs <0 || rs >rows) {
            return false;
        }
        int space = curInfo[0][rs][cl];
        if (space!=0){
            return false;
        }
        return true;
    }

    public State repeated(State st) {
        int i;
        for(i=0; i<states.size(); i++){
            if(states.get(i).equals(st)){
                return st;
            }
        }

        return states.get(i);
    }

    public boolean visited(State st){
        int i;
        for(i=0; i<states.size(); i++){
            if(states.get(i).equals(st)){
                return true;
            }
        }

        return false;
    }



    /*Generar todos los estados posibles*/
    public ArrayList genSt(State estado, instrucciones inst) {
        curInfo = estado.info;
        State nxtSt = new State();

        switch (inst) {
            case arriba:
                rs--;
                nxtSt.visitado= visited(nxtSt);
                if(visited(nxtSt)){
                    nxtSt = repeated(nxtSt);
                    estado = nxtSt;
                    return states;
                }else{
                    states.add(nxtSt);
                }
                nxtSt.valido = validate(estado);
                if(!validate(estado)){
                    rs++;
                }
                nxtSt.info = curInfo;
                estado.vecinos.put("arriba", nxtSt);
                nxtSt.vecinos.put("abajo",estado);
                break;

            case abajo:
                rs++;
                nxtSt.visitado= visited(nxtSt);
                if(visited(nxtSt)){
                    nxtSt = repeated(nxtSt);
                    break;
                }else{
                    states.add(nxtSt);
                }
                nxtSt.valido = validate(estado);
                if(!validate(estado)){
                    rs--;
                }
                nxtSt.info[fc][rs][cl] = curInfo[fc][rs][cl];
                estado.vecinos.put("arriba", nxtSt);
                nxtSt.vecinos.put("abajo",estado);
                break;

            case izquierda:
                cl--;
                nxtSt.visitado= visited(nxtSt);
                if(visited(nxtSt)){
                    nxtSt = repeated(nxtSt);
                }else{
                    states.add(nxtSt);
                }
                nxtSt.valido = validate(estado);
                if(!validate(estado)){
                    cl++;
                }
//                nxtSt.info[fc][rs][cl] = curInfo[fc][rs][cl];
                estado.vecinos.put("arriba", nxtSt);
                nxtSt.vecinos.put("abajo",estado);
                break;

            case derecha:
                nxtSt = new State();
                rs++;
                nxtSt.visitado= visited(nxtSt);
                if(visited(nxtSt)){
                   nxtSt = repeated(nxtSt);
                }else{
                    states.add(nxtSt);
                }
                nxtSt.valido = validate(estado);
                if(!validate(estado)){
                    rs--;
                }
//                nxtSt.info[fc][rs][cl] = curInfo[fc][rs][cl];
                estado.vecinos.put("arriba", nxtSt);
                nxtSt.vecinos.put("abajo",estado);
                break;

            default:
                System.out.print("Instrucción inválida");
        }
        estado = nxtSt;
        states.add(nxtSt);
        return states;
    }

    /*------------------------------------*/


    public static void main(String[] args) {

        int i = 0;

        GeneradorEstados solve = new GeneradorEstados();

        State inicial = solve.setInitSt();

        while (i<9) {
            String orden = JOptionPane.showInputDialog("Ingrese instrucción:\n   1.- Arriba           2.-Abajo\n   3.- Izquierda     4.-Derecha");
            instrucciones generar = null;
            int ins = Integer.parseInt(orden);
            switch (ins) {
                case 1:
                    generar = instrucciones.arriba;
                    break;
                case 2:
                    generar = instrucciones.abajo;
                    break;
                case 3:
                    generar = instrucciones.izquierda;
                    break;
                case 4:
                    generar = instrucciones.derecha;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Instrucción inválida");
                    return;
            }

            ArrayList<State> fnlSt = solve.genSt(inicial, generar);

            for(int x=0; i<fnlSt.size(); i++){
                System.out.print(fnlSt.get(x).toString()+ "\n");
            }
        i++;
        }
    }
}
