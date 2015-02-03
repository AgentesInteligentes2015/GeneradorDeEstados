import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by ShaulaLuz on 29/01/2015.
 */

public class Generador3
{

    /*Dimensiones*/
    final int cols = 2; //NÃºmero de filas por cara
    final int rows = 2; //NÃºmero de columnas por cara
    final int face = 1; /*NÃºmero de caras: En caso de ser cubo face=6
                          En caso de ser cuadro mÃ¡gico o grafos =1
    /*------------

    int rs = 1;    //Valores para indexar las matrices
    int cl = 1;
    int fc = 0;
    */

    Random stID = new Random();

    int initSt[][][] = new int[face][rows][cols];
    int [][][] curInfo;

    public class State
    {
        int info[][][];
        HashMap<String, State> vecinos = new HashMap<String, State>();
        boolean valido;
        boolean visitado;
        public String toString ()
        {
            String ret= new String (" ");
            for(int i=0;i<face;i++)
            {
                for (int j=0;j<rows;j++)
                {
                    ret=ret+"\n";
                    for (int k=0;k<cols;k++)
                    {
                        ret=ret+" "+info[i][j][k]+" ";
                    }
                }
            }
            return ret;

        }
        public State (int[][][] info)
        {
            this.info=info;
            this.valido=true;
            this.visitado=false;
        }
    }

    static List<State> states = new ArrayList<State>();
    State initState;

    public enum instrucciones {       //Instrucciones de movimiento
        arriba, abajo, izquierda, derecha
    }

    /*incializar estado 0*/
    public State setInitSt(){
        ArrayList<Integer> numeros = new ArrayList<Integer>();
        int r;
        Random rand = new Random();     //NÃºmero al azar para llenar la matriz

        for (int i = 0; i < face; i++)
        {     //NÃºmero de cara siendo llenada
            for(int j=0; j<rows; j++)
            {      //NÃºmero de fila siendo llenada
                System.out.print("\n");

                for(int k=0; k<cols;k++)
                {   //NÃºmero de columna siendo llenada
                    //Se revisa que Ã©se nÃºmero no estÃ© ya asignado
                    r = rand.nextInt(face*rows*cols);

                    while (numeros.contains(r))
                    {
                        r = rand.nextInt(face*rows*cols);
                    }

                    initSt[i][j][k] = r;
                    System.out.print(" " + initSt[0][j][k] + " ");
                    numeros.add(r);
                }
            }
        }
        initState=new State(initSt);
        states.add(initState);
        return initState;
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

    public boolean checkvalidity(State estado, instrucciones inst)
    {
        int limit;
        boolean found = false;
        switch (inst)
        {
            case arriba:
            {
                limit=rows-1;
                for(int i =0; i<cols;i++)
                {
                    if(estado.info[0][limit][i]==0)
                    {
                        found=true;
                    }
                }
                break;
            }
            case abajo:
            {
                limit=0;
                for(int i =0; i<cols;i++)
                {
                    if(estado.info[0][limit][i]==0)
                    {
                        found=true;
                    }
                }
                break;
            }
            case derecha:
            {
                limit=0;
                for(int i =0; i<cols;i++)
                {
                    if(estado.info[0][i][limit]==0)
                    {
                        found=true;
                    }
                }
                break;
            }
            case izquierda:
            {
                limit=cols-1;
                for(int i =0; i<cols;i++)
                {
                    if(estado.info[0][i][limit]==0)
                    {
                        found=true;
                    }
                }
                break;
            }
        }
        return !found;
    }
    public int[] find0(State estado)
    {
        int [] posicion= new int [3];
        for(int i =0;i<face;i++)
        {
            for (int j=0;j<rows;j++)
            {
                for (int k=0;k<cols;k++)
                {
                    if(estado.info[i][j][k]==0)
                    {
                        posicion[0]=i;
                        posicion[1]=j;
                        posicion[2]=k;
                        return posicion;
                    }
                }
            }
        }
        return null;

    }

    /*Generar todos los estados posibles*/
    public State genSt(State estado, instrucciones inst) {
        State nxtSt;
        curInfo = estado.info;
        nxtSt=new State(curInfo);
        switch (inst) {

            case izquierda:
                if (checkvalidity(estado,inst))
                {
                    int [] place0= new int [3];
                    place0=find0(estado);
                    int swap= estado.info[place0[0]][place0[1]][place0[2]+1];

                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]][place0[2]+1]=0;
                    if(states.contains(nxtSt)){

                    }else{

                        estado.vecinos.put("izquierda", nxtSt);
                        nxtSt.vecinos.put("derecha",estado);
                        nxtSt.valido=true;
                        System.out.print("\n IZQUIERDA \n _________ \n"+nxtSt.toString());
                    }
                }

                break;

            case derecha:
                if (checkvalidity(estado,inst))
                {
                    int [] place0= new int [3];
                    place0=find0(estado);
                    int swap= estado.info[place0[0]][place0[1]][place0[2]-1];

                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]][place0[2]-1]=0;
                    if(states.contains(nxtSt)){

                    }else{

                        estado.vecinos.put("derecha", nxtSt);
                        nxtSt.vecinos.put("izquierda",estado);
                        nxtSt.valido=true;
                        System.out.print("\n DERECHA \n _________ \n"+nxtSt.toString());
                    }

                }
                break;
            case arriba:
                if (checkvalidity(estado,inst))
                {
                    int [] place0= new int [3];
                    place0=find0(estado);
                    int swap= estado.info[place0[0]][place0[1]+1][place0[2]];

                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]+1][place0[2]]=0;
                    if(states.contains(nxtSt)){

                    }else{

                        estado.vecinos.put("arriba", nxtSt);
                        nxtSt.vecinos.put("abajo",estado);
                        nxtSt.valido=true;
                        System.out.print("\n ARRIBA \n _________ \n"+nxtSt.toString());
                    }
                }

                break;

            case abajo:
                if (checkvalidity(estado,inst))
                {
                    int [] place0= new int [3];
                    place0=find0(estado);
                    int swap= estado.info[place0[0]][place0[1]-1][place0[2]];

                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]-1][place0[2]]=0;
                    if(states.contains(nxtSt)){

                    }else{

                        estado.vecinos.put("abajo", nxtSt);
                        nxtSt.vecinos.put("arriba",estado);
                        nxtSt.valido=true;
                        System.out.print("\n ABAJO \n _________ \n"+nxtSt.toString());
                    }
                }
                break;

            default:
                System.out.print("InstrucciÃ³n invÃ¡lida");
        }
        //estado = nxtSt;

        return nxtSt;
    }

    /*------------------------------------*/


    public static void main(String[] args)
    {


        Generador3 solve = new Generador3();
        State add;
        solve.setInitSt();
        for (State e:states)
        {
            add=solve.genSt(e, instrucciones.arriba);
            states.add(add);
            add=solve.genSt(e, instrucciones.abajo);
            states.add(add);
            add=solve.genSt(e, instrucciones.derecha);
            states.add(add);
            add=solve.genSt(e, instrucciones.izquierda);
            states.add(add);
        }


    }
}