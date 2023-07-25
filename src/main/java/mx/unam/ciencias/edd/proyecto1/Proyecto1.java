package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Proyecto 1: Ordenador Lexicográfico.
 */
public class Proyecto1 {



    /** Nos indica si la entrada estándar tiene la bandera -r. */
    static boolean esReversa = false;
    /** Nos indica si la entrada estándar tiene la bandera -o. */
    static boolean esDestructiva = false;
    /** Bandera -r. */
    static String reversa = "-r";
    /** Bandera -o. */
    static String destructiva = "-o";
    /** Lista en la que agregaremos las lineas recibidas desde la entrada estándar. */
    static Lista<ComparaCadenas> lista = new Lista<ComparaCadenas>();
    /** Para poder usar el método readLine() de BufferedReader. */
    static String cadena = null;
    
    
    /**
     * esEntradaEstandar. Método que determina sí el Ordenador Lexicográfico debe funcionar con entrada estándar. 
     * @param args argumentos recibidos desde la terminal.
     * @param esReversa nos avisa si hay reversa en la terminal.
     * @param esDestructiva Nos avisa si hay destructiva en la terminal.
     * @return si es entrada estándar.
     */
    private static boolean esEntradaEstandar(String[] args,
                                             boolean esReversa,
                                             boolean esDestructiva) {
        boolean opt1 = (args.length == 0) || (args.length == 1 && esReversa);
        boolean opt2 = (args.length == 2 && esDestructiva);
        return opt1 || opt2;
    }

    
    /**
     * reversa. Detecta si hay bandera -r en la terminal.
     * @param args argumentos recibidos desde la terminal.
     */
    private static void reversa(String[] args) {
        for (String cadena : args) {
            if (cadena.equals(reversa)) {
                esReversa = true;
            }
        }
    }


    /**
     * destructiva. Detecta si hay bandera -o en la terminal.
     * @param args argumentos recibidos desde la terminal.
     */
    private static void destructiva(String[] args) {
        for (String cadena : args) {
            if (cadena.equals(destructiva)) {
                esDestructiva = true;
            }
        }
    } 
    public static void main(String[] args) {
        reversa(args);
        destructiva(args);
        boolean resultado = esEntradaEstandar(args, esReversa, esDestructiva);
        if (resultado) { 
            try {
                InputStreamReader flujo = new InputStreamReader(System.in);
                BufferedReader leer = new BufferedReader(flujo);
                while((cadena = leer.readLine()) != null) {
                    lista.agregaFinal(new ComparaCadenas(cadena));
                }
                Lista<ComparaCadenas> ordenada = esReversa ? Lista.mergeSort(lista).reversa() : Lista.mergeSort(lista);
                for (ComparaCadenas linea : ordenada) {
                    System.out.println(linea);
                }    
            } catch (IOException ioe) {
                System.err.println("Ha ocurrido un error");
            }
        } else {
            Proyecto1 proyecto = new Proyecto1();
            proyecto.ordena(args);
        }

    }
    /**
     * ordena.
     * @param args argumentos recibidos desde la terminal
     */
    public void ordena(String [] args) {
        Argumentos argumento = new Argumentos();
        argumento.revisaArgumentos(args);
    }
}
