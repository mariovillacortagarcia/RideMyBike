package ridemybike.dominio.db;

/**
 * Conjunto de utilidades y funcionalidades relativas a la gestion de la base de datos
 */
public class UtilitiesDB {
    
    /**
     * Verifica si es posible que la cadena dada realice una inyeccion de codigo SQL
     * 
     * @param cadena la cadena especificada
     * @return true si es posible; false en caso contrario 
     */
    public static boolean posibleInyeccionSQL(String cadena) {
        return cadena.contains("\\") || cadena.contains("\"") || cadena.contains("\'") 
                || cadena.contains("\\x00") || cadena.contains("\\x1") 
                || cadena.contains("'; GO EXEC ") || cadena.contains("' UNION SELECT ") 
                || cadena.contains("' or 1=1-- ") || cadena.contains("' or 1=1/*") 
                || cadena.contains("') or ('1'='1") || cadena.contains("' or 1=1#") 
                || cadena.contains("';SELECT ") || cadena.contains("';UPDATE ")
                || cadena.contains("';DROP ") || cadena.contains("admin'--");
    }
}
