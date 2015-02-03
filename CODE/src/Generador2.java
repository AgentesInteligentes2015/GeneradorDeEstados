import java.util.*;

/**
 * Created by ShaulaLuz on 29/01/2015.
 */

public class Generador2
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

    Random stID = new Random();     //Identificador de estado

    int initSt[][][] = new int[face][rows][cols];   //Estado incial
    int [][][] curInfo;                             //Posición actual(?)

    public class State
    {
        int info[][][];                             //Información del éstado (Número almacenado en x, y, z posición)
        HashMap<String, State> vecinos = new HashMap<String, State>();      //Lista de vecinos e instrucción para llegar a ellos desde el estado actual
        boolean valido;                             //Bit de validez
        boolean visitado;                           //Bit de visitado

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

    ListIterator<State> iter = states.listIterator();


    static List<State> states = new LinkedList<State>();

    static State initState=null;

    public enum instrucciones {       //Instrucciones de movimiento
        arriba, abajo, izquierda, derecha
    }

    /*incializar estado 0*/
    public State setInitSt(){
        System.out.print("Estado incial:\n");

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
        //states.add(initState);
        return initState;
    }

    public State repeated(State st) {
        int i;

        for(i=0; i<states.size(); i++){
            State meh = states.get(i);
            if(meh.info == st.info){
                System.out.print("\n\nEstado existente\n\n");
                return st;
            }
        }
        return null;
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
                for(int i =0; i<rows;i++)
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
                for(int i =0; i<rows;i++)
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
        State nxtSt =null;
        //nxtSt.info = estado.info;
        int [][][] toswap =new int[face][rows][cols];
        int [] place0= new int [3];

        switch (inst) {
            case arriba:
                boolean val = checkvalidity(estado, inst);
                if (val)
                {

                    place0=find0(estado);
                    toswap=estado.info;
                    int swap= estado.info[place0[0]][place0[1]+1][place0[2]];

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]+1][place0[2]]=0;
                    nxtSt=new State(toswap);

                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        System.out.print("\nESTADO NUEVO\n");
                        estado.vecinos.put("arriba", nxtSt);
                        nxtSt.vecinos.put("abajo", estado);
                        nxtSt.valido = true;
                        System.out.print("\n ARRIBA \n _________ \n" + nxtSt.toString());
                    }else
                        System.out.print("\nREPEATED ARRIBA\n");
                }else return null;

                break;

            case abajo:
                val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);
                    toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]-1][place0[2]];

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]-1][place0[2]]=0;
                    nxtSt=new State(toswap);

                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        System.out.print("\nESTADO NUEVO\n");

                        estado.vecinos.put("abajo", nxtSt);
                        nxtSt.vecinos.put("arriba", estado);
                        nxtSt.valido = true;
                        System.out.print("\n ABAJO \n _________ \n" + nxtSt.toString());
                    }else
                        System.out.print("\nREPEATED ABAJO\n");
                }else return null;
                break;
            case izquierda:
                val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);

                    toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]][place0[2]+1];

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]][place0[2]+1]=0;
                    nxtSt=new State(toswap);
                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        System.out.print("\nESTADO NUEVO\n");

                        estado.vecinos.put("izquierda", nxtSt);
                        nxtSt.vecinos.put("derecha", estado);
                        nxtSt.valido = true;
                        System.out.print("\n IZQUIERDA \n _________ \n" + nxtSt.toString());
                    }else
                        System.out.print("\nREPEATED IZQUIERDA\n");
                }else return null;

                break;

            case derecha:
                val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);
                    toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]][place0[2]-1];

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]][place0[2]-1]=0;
                    nxtSt=new State(toswap);
                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        System.out.print("\nESTADO NUEVO\n");

                        estado.vecinos.put("derecha", nxtSt);
                        nxtSt.vecinos.put("izquierda", estado);
                        nxtSt.valido = true;
                        System.out.print("\n DERECHA \n _________ \n" + nxtSt.toString());
                    }else
                        System.out.print("\nREPEATED DERECHA\n");

                }else return null;
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

        Generador2 solve = new Generador2();
        State add = null;
        solve.setInitSt();
        states.add(initState);

        ListIterator<State> iter = states.listIterator();

        add=solve.genSt(initState, instrucciones.arriba);
        if(add!=null){
            System.out.print("Added state iU: \n");
            iter.add(add);
        }

       add=solve.genSt(initState, instrucciones.derecha);
        if(add!=null){
            System.out.print("Added state iR: \n");
            iter.add(add);
        }

        add=solve.genSt(initState, instrucciones.abajo);
        if(add!=null){
            System.out.print("Added state iD: \n");
            iter.add(add);
        }

        add=solve.genSt(initState, instrucciones.izquierda);
        if(add!=null){
            System.out.print("Added state iL: \n");
            iter.add(add);
        }

        iter.next();


        while(iter.hasPrevious())
        {
            State e = iter.previous();

            /*e = solve.repeated(e);
            while (states.contains(e) && iter.hasPrevious()){
                e = iter.previous();
            }*/

            add=solve.genSt(e, instrucciones.arriba);
            if(add!=null){
                iter.add(add);
            }


            add=solve.genSt(e, instrucciones.derecha);
            if(add!=null){
                iter.add(add);
            }

            add=solve.genSt(e, instrucciones.abajo);
            if(add!=null){
                iter.add(add);
            }

            add=solve.genSt(e, instrucciones.izquierda);
            if(add!=null){
                iter.add(add);
            }

        }


    }
}