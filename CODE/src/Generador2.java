import com.rits.cloning.Cloner;
 
import java.util.*;
 
/**
 * Created by ShaulaLuz on 29/01/2015.
 */
 
public class Generador2
{
 
    /*Dimensiones*/
    final static int cols = 2; //NÃºmero de filas por cara
    final static int rows = 2; //NÃºmero de columnas por cara
    final static int face = 1; /*NÃºmero de caras: En caso de ser cubo face=6
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
<<<<<<< HEAD
        boolean visitadop;
        boolean visitados;
 
=======
        public boolean equals(State s)
        {
        	boolean iguales=true;
        	for(int i=0;i<face;i++)
        	{
        		for (int j=0;j<rows;j++)
        		{
        			for (int k=0;k<cols;k++)
        			{
        				if(this.info[i][j][k]!=s.info[i][j][k])
        					iguales=false;
        			}
        		}
        	}
        	return iguales;
        }
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
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
            this.visitadop=false;
            this.visitados=false;
        }
        public State clone()
        {
        	return new State(this.info);
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
<<<<<<< HEAD
 
    public State repeated(State st) {
=======

    public static State repeated(State st) {
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
        int i;
 
        for(i=0; i<states.size(); i++){
            State meh = states.get(i);
 
            if (states.size()<=1){
                return st;
            }
 
            if(meh.info == st.info){
            	st.visitado=true;
                return null;
            }
        }
        
        return st;
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
<<<<<<< HEAD
 
    public boolean checkvalidity(State estado, instrucciones inst)
=======

    public static boolean checkvalidity(State estado, instrucciones inst)
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
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
    public static int[] find0(State estado)
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
<<<<<<< HEAD
    public State genSt(State estado, instrucciones inst) {
        Cloner cloner = new Cloner();
        curInfo = cloner.deepClone(estado.info);
 
        State nxtSt = new State(curInfo);
 
        //int [][][] toswap =new int[face][rows][cols];
=======
    public static State genSt(State estado, instrucciones inst, Generador2 gen) {
        State nxtSt =(State) estado.clone();
        //nxtSt.info = estado.info;
        int [][][] toswap =new int[face][rows][cols];
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
        int [] place0= new int [3];
 
        switch (inst) {
            case arriba:
                boolean val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);
                    //toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]+1][place0[2]];
<<<<<<< HEAD
 
                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]+1][place0[2]]=0;
                    //nxtSt=new State(toswap);
 
=======

                    //toswap[place0[0]][place0[1]][place0[2]]=swap;
                    //toswap[place0[0]][place0[1]+1][place0[2]]=0;
                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]+1][place0[2]]=0;
                    //nxtSt=gen.new State(toswap);

>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        estado.vecinos.put("arriba", nxtSt);
                        nxtSt.vecinos.put("abajo", estado);
                        nxtSt.valido = true;
                        estado.visitado=true;
                        System.out.print("\n ARRIBA \n _________ \n" + nxtSt.toString());
                        System.out.print("INICIAL: "+nxtSt.toString()+"\n\n");
                    }else
                        System.out.print("\nREPEATED ARRIBA\n");
                }else return null;
                break;
 
            case abajo:
                val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);
                    //toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]-1][place0[2]];
<<<<<<< HEAD
 
                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]-1][place0[2]]=0;
                    //nxtSt=new State(toswap);
 
=======

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]-1][place0[2]]=0;
                    nxtSt=gen.new State(toswap);

>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        estado.vecinos.put("abajo", nxtSt);
                        nxtSt.vecinos.put("arriba", estado);
                        nxtSt.valido = true;
                        estado.visitado=true;
                        System.out.print("\n ABAJO \n _________ \n" + nxtSt.toString());
                        System.out.print("INICIAL: "+nxtSt.toString()+"\n\n");
                    }else
                        System.out.print("\nREPEATED ABAJO\n");
                }else return null;
                break;
 
            case izquierda:
                val = checkvalidity(estado, inst);      //Revisa validez del estado
                if (val)
                {
                    place0=find0(estado);
 
                    //toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]][place0[2]+1];
<<<<<<< HEAD
 
                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;       //Realiza movimiento de números
                    nxtSt.info[place0[0]][place0[1]][place0[2]+1]=0;
                    //nxtSt=new State(toswap);
=======

                    toswap[place0[0]][place0[1]][place0[2]]=swap;       //Realiza movimiento de números
                    toswap[place0[0]][place0[1]][place0[2]+1]=0;
                    nxtSt=gen.new State(toswap);
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
                    nxtSt = repeated(nxtSt);                            //Asegura que el nuevo estado no sea repetido
                    if(nxtSt!=null) {
                        estado.vecinos.put("izquierda", nxtSt);         //Completa información del nuevo estado
                        nxtSt.vecinos.put("derecha", estado);
                        nxtSt.valido = true;
                        estado.visitado=true;
                        System.out.print("\n IZQUIERDA \n _________ \n" + nxtSt.toString());
                        System.out.print("INICIAL: "+nxtSt.toString()+"\n\n");
                    }else
                        System.out.print("\nREPEATED IZQUIERDA\n");
                }else return null;
 
                break;
 
            case derecha:
                val = checkvalidity(estado, inst);
                if (val)
                {
                    place0=find0(estado);
                    //toswap=estado.info.clone();
                    int swap= estado.info[place0[0]][place0[1]][place0[2]-1];
<<<<<<< HEAD
 
                    nxtSt.info[place0[0]][place0[1]][place0[2]]=swap;
                    nxtSt.info[place0[0]][place0[1]][place0[2]-1]=0;
                    //nxtSt=new State(toswap);
=======

                    toswap[place0[0]][place0[1]][place0[2]]=swap;
                    toswap[place0[0]][place0[1]][place0[2]-1]=0;
                    nxtSt=gen.new State(toswap);
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
                    nxtSt = repeated(nxtSt);
                    if(nxtSt!=null) {
                        estado.vecinos.put("derecha", nxtSt);
                        nxtSt.vecinos.put("izquierda", estado);
                        nxtSt.valido = true;
                        estado.visitado=true;
                        System.out.print("\n DERECHA \n _________\n" + nxtSt.toString());
                        System.out.print("INICIAL: "+nxtSt.toString()+"\n\n");
                    }else
                        System.out.print("\nREPEATED DERECHA\n");
 
                }else return null;
                break;
 
            default:
                System.out.print("InstrucciÃ³n invÃ¡lida");
        }
        curInfo=null;
 
        return nxtSt;       //Regresa estado generado
    }
 
    /*------------------------------------*/
<<<<<<< HEAD
    public static ArrayList<State> buscarpilaConArray(State finalstate) {
       // ArrayList<State> toreturn = new ArrayList<State>();
        ArrayList<State> al = new ArrayList<State>();
        ListIterator<State> iter = al.listIterator();
 
        al.add(initState);
 
        State currState;
        currState = initState;
 
        while (iter.hasNext())                  //Itera la lista de estados de derecha a izquierda
        {
            currState = al.get(0);
            al.remove(0);
            currState.visitados=true;
 
            if(currState.vecinos.containsValue(finalstate)){
            break;
            }
 
            if(currState.vecinos.containsKey("arriba"))
            {
            	if(currState.vecinos.get("arriba").visitados)
				{
					
				}
            	else
            	{
            		al.add(currState.vecinos.get("arriba"));
            	}
            }
            if(currState.vecinos.containsKey("abajo"))
            {
            	if(currState.vecinos.get("abajo").visitados)
				{
					
				}
            	else
            	{
            		al.add(currState.vecinos.get("abajo"));
            	}
            }
            if(currState.vecinos.containsKey("izquierda"))
            {
            	if(currState.vecinos.get("izquierda").visitados)
				{
				}
            	else
            	{
            		al.add(currState.vecinos.get("izquierda"));
            	}
            }
            if(currState.vecinos.containsKey("derecha"))
            {
            	if(currState.vecinos.get("derecha").visitados)
				{
				}
            	else
            	{
            		al.add(currState.vecinos.get("derecha"));
            	}
            }
        }
 
            return al;
    }
 
 
=======
    static State stoSearch;

>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
    public static void main(String[] args)
    {
 
        Generador2 solve = new Generador2();
        State add = null;
        solve.setInitSt();
        states.add(initState);
 
        ListIterator<State> iter = states.listIterator();
<<<<<<< HEAD
 
        add=solve.genSt(initState, instrucciones.arriba);       //Crea los primeros cuatro estados generados del estado inicial
        if(add!=null){
            iter.add(add);
        }
 
        add=solve.genSt(initState, instrucciones.derecha);
        if(add!=null){
            iter.add(add);
        }
 
        add=solve.genSt(initState, instrucciones.abajo);
        if(add!=null){
            iter.add(add);
        }
 
        add=solve.genSt(initState, instrucciones.izquierda);
=======

        add=Generador2.genSt(initState, instrucciones.arriba,solve);       //Crea los primeros cuatro estados generados del estado inicial
        if(add!=null){
            iter.add(add);
        }

       add=Generador2.genSt(initState, instrucciones.derecha,solve);
        if(add!=null){
            iter.add(add);
        }

        add=Generador2.genSt(initState, instrucciones.abajo,solve);
        if(add!=null){
            iter.add(add);
        }

        add=Generador2.genSt(initState, instrucciones.izquierda,solve);
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
        if(add!=null){
            iter.add(add);
        }
 
        iter.next();
 
 
        while(iter.hasPrevious())                  //Itera la lista de estados de derecha a izquierda
        {
            State e = iter.previous();          //Toma un elemento de la lista
 
            if(e.visitado){                     //Asegura que no haya sido ya procesado y salta a la siguiente posición en caso contrario
                continue;
            }
<<<<<<< HEAD
 
            add=solve.genSt(e, instrucciones.arriba);       //Instrucciones de movimientos
            if(add!=null){                                  //Si el estado no tuvo ningún conflicto de validación se agrega a la lista de estados
                iter.add(add);
            }else continue;
 
            add=solve.genSt(e, instrucciones.derecha);
            if(add!=null){
                iter.add(add);
            }else continue;
 
            add=solve.genSt(e, instrucciones.abajo);
            if(add!=null){
                iter.add(add);
            }else continue;
 
            add=solve.genSt(e, instrucciones.izquierda);
            if(add!=null){
                iter.add(add);
            }else continue;
        }
 

        
=======

            add=Generador2.genSt(e, instrucciones.arriba,solve);       //Instrucciones de movimientos
            if(add!=null)
            {                                  //Si el estado no tuvo ningún conflicto de validación se agrega a la lista de estados
                iter.add(add);
            }
            else continue;
            add=Generador2.genSt(e, instrucciones.derecha,solve);
            if(add!=null)
            {
                iter.add(add);
            }
            else continue;

            add=Generador2.genSt(e, instrucciones.abajo,solve);
            if(add!=null)
            {
                iter.add(add);
            }
            else continue;

            add=Generador2.genSt(e, instrucciones.izquierda,solve);
            if(add!=null)
            {
                iter.add(add);
            }
            else continue;
        }
        //Here Starts What I Added -Daniel 
        System.out.print("\n FINISHED ADDING STATES\n_____________________\n\n");
        System.out.print("\n searching through Stack: \n");
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
        int tosearch[][][]=new int [face][rows][cols];
        tosearch[0][0][0]=1;
        tosearch[0][0][1]=2;
        tosearch[0][1][0]=3;
        tosearch[0][1][1]=0;
<<<<<<< HEAD
        State stoSearch=solve.new State(tosearch);
        ArrayList<State> camino = buscarpilaConArray(stoSearch);
        System.out.print("\nRuta al estado final con list: " + camino);
        camino=buscarpilas.buscarpila(stoSearch);
        System.out.print("\nRuta al estado final con stack: " + camino);
 
    }    
=======
        stoSearch=solve.new State(tosearch);
       
        System.out.print(buscarpilas.buscarpila(stoSearch).toString()+"\nFINISHED SEARCHING\n_____________________\n\n");
    }
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
    public static class buscarpilas
    {
    	public static ArrayList<State> buscarpila(State finalstate)
    	{
    		ArrayList<State> toreturn = new ArrayList<State>();
    		Stack<State>mystack=new Stack<State>();
    		mystack.push(initState);
    		State currState;
    		currState=null;
    		do
    		{
    			currState=mystack.pop();
<<<<<<< HEAD
    			System.out.print("ESTADO ACTUAL:\n"+currState.toString());
    			currState.visitadop=true;
    			toreturn.add(currState);
    			if(currState.vecinos.containsKey("arriba"))
    			{
    				if(currState.vecinos.get("arriba").visitadop)
    				{
    				}
    				else
    				{
    					mystack.push(currState.vecinos.get("arriba"));
    				}
    			}
    			if(currState.vecinos.containsKey("abajo"))
    			{
    				if( currState.vecinos.get("abajo").visitadop)
    				{
    					
    				}
    				else
    				{
    					mystack.push(currState.vecinos.get("abajo"));
    				}
    			}
    			if(currState.vecinos.containsKey("izquierda"))
    			{
    				if( currState.vecinos.get("izquierda").visitadop)
    				{
    				
    				}
    				else
    				{
    					mystack.push(currState.vecinos.get("izquierda"));
    				}
    			}
    			if(currState.vecinos.containsKey("derecha"))
    			{
    				if(currState.vecinos.get("derecha").visitadop)
    				{
    				
    				}
    				else
    				{
    					mystack.push(currState.vecinos.get("derecha"));
    				}
=======
    			System.out.print("ESTADO ACTUAL:\n"+currState.toString()+currState.visitado);
    			currState.visitado=true;
    			toreturn.add(currState);
    			if(currState.vecinos.containsKey("arriba"))
    			{
    				mystack.push(currState).vecinos.get("arriba");
    			}
    			if(currState.vecinos.containsKey("abajo"))
    			{
    				mystack.push(currState).vecinos.get("abajo");
    			}
    			if(currState.vecinos.containsKey("izquierda"))
    			{
    				mystack.push(currState).vecinos.get("izquierda");
    			}
    			if(currState.vecinos.containsKey("derecha"))
    			{
    				mystack.push(currState).vecinos.get("derecha");
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
    			}
    		}while(!mystack.isEmpty() && !currState.equals(finalstate) /*&& !currState.visitado*/);
    		for(State e: toreturn)
    		{
<<<<<<< HEAD
    			System.out.print(e.toString());
=======
    			e.toString();
>>>>>>> 61463a9dbfdee31f0afb2700204a216457cac9dd
    		}
    		return toreturn;
    	}
    }

}
