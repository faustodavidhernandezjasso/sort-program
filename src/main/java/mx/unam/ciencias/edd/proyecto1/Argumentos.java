package mx.unam.ciencias.edd.proyecto1;

import mx.unam.ciencias.edd.Lista;

/**
 * Clase para argumentos recibidos desde la terminal.
 */
public class Argumentos extends LeeryEscribir {
   
    

    /** Bandera Reversa */
    private static String banderaReversa = "-r";
    /** Bandera Destructiva */
    private static String banderaDestructiva = "-o";
    /** La ruta del archivo si en la terminal pasan banderaDestructiva */
    private String rutaDeArchivo = null;
    /** Lista de archivos recibidos desde la terminal */
    private Lista<String> listaDeArchivos = new Lista<String>();
    /** Nos avisa si hay banderaReversa en la terminal */
    boolean esBanderaReversa = false;
    /** Nos avisa si hay banderaDestructiva en la terminal */
    boolean esBanderaDestructiva = false;



    
    /**
     * revisaArgumentos lee los argumentos desde la terminal,
     * en caso de que no se ejecute la entrada estándar.
     * @param args el arreglo recibido desde la terminal.
     */
    public void revisaArgumentos(String[] args) {
        String auxiliar = "";
	    for (String cadena : args) {
	        if (auxiliar.equals(banderaDestructiva)) {
                auxiliar = cadena;
                rutaDeArchivo = cadena;
                esBanderaDestructiva = true;
	            continue;
	        }
	        auxiliar = cadena;
	        if (cadena.equals(banderaReversa)) {
                esBanderaReversa = true;    
            }
	        if (cadena.equals(banderaDestructiva)) {
                esBanderaDestructiva = true;
                continue;
            }
	        if (!(cadena.equals(banderaReversa))) {
                listaDeArchivos.agregaFinal(cadena);    
            }
        }
        detecta(esBanderaDestructiva, esBanderaReversa, listaDeArchivos, rutaDeArchivo);
    }
}