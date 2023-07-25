package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedWriter;

/**
 * Clase para determinar que hacer con los archivos recibidos desde la terminal.
 */
public class LeeryEscribir {



    /** Lista que utilizaremos para agregar las líneas de cada archivo */
    Lista<ComparaCadenas> lista = new Lista<ComparaCadenas>();

    /**
     * detecta. Detecta que debe de hacer con los argumentos recibidos desde la terminal.
     * @param esBanderaDestructiva nos indica si debemos de sobreescribir un archivo 
     *                             con la salida ya ordenada.
     * @param esBanderaReversa nos indica si debemos de regresar el/los archivos ya 
     *                         ordenados, en reversa.
     * @param listaDeArchivos lista de archivos que recibimos desde la terminal. 
     * @param rutaDeArchivo La ruta del archivo si en la terminal pasan banderaDestructiva.
     */
    public void detecta(boolean esBanderaDestructiva,
                        boolean esBanderaReversa, 
                        Lista<String> listaDeArchivos, 
                        String rutaDeArchivo) {
        if (esBanderaReversa && !esBanderaDestructiva) {
            leer(listaDeArchivos, esBanderaReversa);
        }
        else if (esBanderaDestructiva) {
            leerDestructivo(listaDeArchivos, esBanderaReversa, rutaDeArchivo);
        }

        else if (esBanderaDestructiva && esBanderaReversa) {
            leerDestructivo(listaDeArchivos, esBanderaReversa, rutaDeArchivo);
        }
        else if (!esBanderaDestructiva && !esBanderaReversa) {
            leer(listaDeArchivos, esBanderaReversa);
        }

    }



    /**
     * leer. Lee cada archivo desde la terminal y lo trata como uno sólo. 
     * @param archivos  lista de archivos que recibimos desde la terminal.
     * @param esBanderaReversa nos indica si debemos de regresar el/los archivos 
     *                         ya ordenados en reversa.
     */
    public void leer(Lista<String> archivos, boolean esBanderaReversa) {
        for (String archivoNombre : archivos) {
            try (BufferedReader leer = new BufferedReader(new FileReader(archivoNombre))){
                String cadena;
                while ((cadena = leer.readLine()) != null) {
                    lista.agregaFinal(new ComparaCadenas(cadena));
                } 
            } catch (IOException ioe) {
                System.err.println("Ha habido un problema");
                System.exit(1);
            }
        }
        Lista<ComparaCadenas> ordenada = ordenaLista(lista, esBanderaReversa);
        for (ComparaCadenas cadena : ordenada) {
            System.out.println(cadena);
        }
    }


    /**
     * leerDestructivo. Lee cada archivo desde la terminal y lo trata como uno sólo.
     * ademas escribe en el archivo después de la bandera -o.
     * @param archivos lista de archivos que recibimos desde la terminal.
     * @param esBanderaReversa nos indica si debemos de regresal el/los archivos de reversa.
     * @param archivo el archivo donde se sobreescribirá la salida ya ordenada.
     */
    public void leerDestructivo(Lista<String> archivos, 
                                boolean esBanderaReversa,
                                String archivo) {
        for (String archivoNombre : archivos) {
            try {
                FileReader fr = new FileReader(archivoNombre);
                BufferedReader leer = new BufferedReader(fr);
                String cadena;
                while ((cadena = leer.readLine()) != null) {
                    lista.agregaFinal(new ComparaCadenas(cadena));
                } 
            } catch (IOException ioe) {
                System.err.println("Ha habido un problema");
                System.exit(1);
            }
        }
        Lista<ComparaCadenas> ordenada = ordenaLista(lista, esBanderaReversa);
        for (ComparaCadenas cadena : ordenada) {
                System.out.println(cadena);
        }
        escribe(ordenada, archivo);
    }



    /**
     * escribe. Escribe en el archivo la salida ordenada.
     * @param ordenada Lista ordenada que se escribirá en el archivo.
     * @param archivo El archivo a sobreescribir la lista ordenada.
     */
    public void escribe(Lista<ComparaCadenas> ordenada, String archivo) {
        try (BufferedWriter escribir = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo)))) {
            for (ComparaCadenas linea : ordenada) {
                escribir.write(linea.toString() + "\n");
            }
            escribir.close();
        } catch (IOException ioe) {
            System.err.println("Ha ocurrido un error");
        }
    }



    /**
     * 
     * @param lista la lista a ordenar.
     * @param esBanderaReversa nos indica si debemos regresar la lista ordenada en reversa.
     * @return la lista ya ordenada.
     */
    public Lista<ComparaCadenas> ordenaLista(Lista<ComparaCadenas> lista,
                                             boolean esBanderaReversa) {
        if (esBanderaReversa) {
            Lista<ComparaCadenas> ordenada = Lista.mergeSort(lista).reversa();
            return ordenada;
        } else {
            Lista<ComparaCadenas> ordenada = Lista.mergeSort(lista);
            return ordenada;
        }
    }

}