package ridemybike.dominio;

import java.io.Serializable;

/**
 * Implementacion de una incidencia
 */
public class Incidencia implements Serializable {
    private String descripcion;
    private int codigoIncidencia;
    private int codigoPeticion;
    private GradoIncidencia gravedad;
    
    /**
     * Inicializador
     */
    public Incidencia(){
        descripcion = "";
        codigoIncidencia = -1;
        codigoPeticion = -1;
        gravedad = null;
    }
    
    /**
     * Devuelve el codigo de la incidencia
     * 
     * @return un int con el codigo
     */
    public int getCodigoIncidencia(){
        return codigoIncidencia;
    }
    
    /**
     * Devuelve el codigo de la peticion asociada a la incidencia
     * 
     * @return un int con el codigo
     */
    public int getCodigoPeticion(){
        return codigoPeticion;
    }
    
    /**
     * Devuelve la descripcion de la incidencia
     * 
     * @return un String con la descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Devuelve la gravedad de la incidencia
     * 
     * @return un GradoIncidencia con el grado de gravedad
     */
    public GradoIncidencia getGravedad(){
        return gravedad;
    }
    
    /**
     * Establece el codigo de la incidencia
     * 
     * @param codigoIncidencia el codigo de la incidencia
     * @throws IllegalArgumentException si el codigo es negativo
     */
    public void setCodigoIncidencia(int codigoIncidencia){
        if(codigoIncidencia < 0){
            throw new IllegalArgumentException("Codigo de incidencia negativo");
        }
        this.codigoIncidencia = codigoIncidencia;
    }
    
    /**
     * Establece el codigo de la peticion asociada a la incidencia
     * 
     * @param codigoPeticion el codigo de la peticion
     * @throws IllegalArgumentException si el codigo es negativo
     */
    public void setCodigoPeticion(int codigoPeticion){
        if(codigoPeticion < 0){
            throw new IllegalArgumentException("Codigo de peticion negativo");
        }
        this.codigoPeticion = codigoPeticion;
    }
    
    /**
     * Establece la descripcion de la incidencia
     * 
     * @param descripcion la descripcion de la incidencia
     * @throws IllegalArgumentException si la descripcion es igual a null
     */
    public void setDescripcion(String descripcion){
        if(descripcion == null){
            throw new IllegalArgumentException("Descripcion de incidencia igual a null");
        }
        this.descripcion = descripcion;
    }
    
    /**
     * Establece la gravedad de la incidencia
     * 
     * @param gravedad la gravedad de la incidencia
     * @throws IllegalAccessException si la gravedad es igual a null
     */
    public void setGravedad(GradoIncidencia gravedad){
        if(gravedad == null){
            throw new IllegalArgumentException("Gravedad de incidencia igual a null");
        }
        this.gravedad = gravedad;
    }
}
