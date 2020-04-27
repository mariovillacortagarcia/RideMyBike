package ridemybike.dominio;

import java.io.Serializable;


/**
 * La valoracion que se realiza sobre los servicios ofrecidos
 */
public abstract class Valoracion implements Serializable{
    
    private int codigoAlquiler;
    private String descripcion;
    private int puntuacion;
    
    /**
     * Metodo constructor, se inicializa todo a valores vacios, ya que se sigue el protocolo JavaBeans
     */
    public Valoracion(){
        codigoAlquiler = 0;
        descripcion = "";
        puntuacion = 0;
    }
    
    /**
     * Metodo dar valor al codigoAlquiler de la valoracion
     * @param codigoAlquiler Es el codigoAlquiler numerico identificador de la valoracion
     */
    public void setCodigo(int codigoAlquiler){
        this.codigoAlquiler = codigoAlquiler;
    }
    
    /**
     * Metodo para hacer una descripcion de la puntuacion del viaje
     * @param descripcion Es una cadena de texto que sirve para dar la opinion sobre el alquiler
     */
    public void setDescripcion(String descripcion){
        if (descripcion == null){throw new IllegalArgumentException("La descripcion de la puntuacion no puede ser no valida");}
        this.descripcion = descripcion; 
    }
    
    /**
     * Metodo para puntuar del 0 al 5 la experiencia del alquiler
     * @param puntuacion Es el puntuacion dado al alquiler
     */
    public void setPuntuacion(int puntuacion){
        if(puntuacion < 0 || puntuacion > 5){throw new IllegalArgumentException("La puntuacion del alquiler no puede ser un numero no valido ");}
        this.puntuacion = puntuacion;
    }
    
    /**
     * Funcion que returna el codigoAlquiler identificador del alquiler
     * @return El codigoAlquiler identificador del alquiler
     */
    public int getCodigo(){
        return codigoAlquiler;
    }
    
    /**
     * Funcion que returna la descripcion de la puntuacion del alquiler
     * @return La descripcion de la opinion del alquiler
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Funcion que returna la puntuacion dada a la puntuacion del alquiler
     * @return El puntuacion dada al alquiler
     */
    public int getPuntuacion(){
        return puntuacion;
    }
}
