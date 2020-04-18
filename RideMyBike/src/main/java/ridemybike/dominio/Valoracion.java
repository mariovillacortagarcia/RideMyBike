/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

/**
 *
 * @author davidmd
 */
public class Valoracion {
    
    private int codigo;
    private String descripcion;
    private int puntuacion;
    
    public Valoracion(){
        
    }
    
    /**
     * Funcion dar valor al codigo de la valoracion
     * @param codigo Es el codigo numerico identificador de la valoracion
     */
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    /**
     * Funcion para hacer una descripcion de la puntuacion del viaje
     * @param descripcion Es una cadena de texto que sirve para dar la opinion sobre el alquiler
     */
    public void setDescripcion(String descripcion){
        if (descripcion == null){throw new IllegalArgumentException("La descripcion de la puntuacion no puede ser no valida");}
        this.descripcion = descripcion; 
    }
    
    /**
     * Funcion para puntuar del 0 al 5 la experiencia del alquiler
     * @param puntuacion Es el puntuacion dado al alquiler
     */
    public void setPuntuacion(int puntuacion){
        if(puntuacion < 0 || puntuacion > 5){throw new IllegalArgumentException("La puntuacion del alquiler no puede ser un numero no valido ");}
        this.puntuacion = puntuacion;
    }
    
    /**
     * Metodo que returna el codigo identificador del alquiler
     * @return El codigo identificador del alquiler
     */
    public int getCodigo(){
        return codigo;
    }
    
    /**
     * Metodo que returna la descripcion de la puntuacion del alquiler
     * @return La descripcion de la opinion del alquiler
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Metodo que returna la puntuacion dada a la puntuacion del alquiler
     * @return El puntuacion dada al alquiler
     */
    public int getPuntuacion(){
        return puntuacion;
    }
}