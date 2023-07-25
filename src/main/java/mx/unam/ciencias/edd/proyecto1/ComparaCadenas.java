package mx.unam.ciencias.edd.proyecto1;

import java.text.Normalizer;
import java.text.Normalizer.Form;

/**
 * Clase para comparar cadenas.
 */
public class ComparaCadenas implements Comparable<ComparaCadenas> {
    /** Cadena a normalizar */
    private String cadena;
    /** Cadena normalizada  */
    private String cadenaCompara;


    /**
     * ComparaCadenas. Constructor.
     * @param cadena cadena a ser normalizada.
     */
    public ComparaCadenas(String cadena) {
        this.cadena = cadena;
        this.cadenaCompara = cadenaComparable(cadena);
    }



    /**
     * cadenaComparable. normaliza la cadena.
     * @param cadena cadena a normalizar.
     * @return cadena normalizada.
     */
    private String cadenaComparable(String cadena) {
        String s = Normalizer.normalize(cadena, Form.NFD).replaceAll("[^A-Za-z0-9]", "").
        replaceAll("\\p{InCombiningDiacriticalMarks}", "").toLowerCase();
        return s;
    }



    /**
     * toString. Regresa una representación de la cadena.
     */
    @Override public String toString() {
        return cadena;
    }



    /**
     * Compara cadenas dos cadenas.
     */
    @Override public int compareTo(ComparaCadenas cadena) {
        String cadenaComparable = cadena.cadenaCompara;
        return this.cadenaCompara.compareTo(cadenaComparable);
    }

}