/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Peticion que hace un usuario para poder dar de alta la bicicleta que quiere anadir al sistema
 */

public class PeticionRevision implements Serializable{
    private String ciudad;
    private String nombreUsuario;
    private Timestamp fecha;
    private int codigoBicicleta;
    
    /**
     * Metodo constructor que cumple los estandares de JavaBeans sin argumentos
     */
    public PeticionRevision(){
        ciudad = "";
        nombreUsuario = "";
        fecha = null;
        codigoBicicleta = 0;
    }
    
    /**
     * Metodo para dar el valor a la ciudad donde se va a hacer la "reunion" para verificar la bicicleta
     * @param ciudad Es la cadena que nos da el valor con el que se incializa la ciudad de la reunion
     */
    public void setCiudad(String ciudad){
        if(ciudad == null || ciudad.equals("")){
            throw new IllegalArgumentException("Ciudad no valida");
        }
        this.ciudad = ciudad;
    }
    
    /**
     * Metodo para dar valor al nombre de ususario que quiere registrar una nueva bicicleta en el sistema
     * @param nombreUsuario Es el string identificador del usuario dueno de la bicicleta que quiere dar de alta
     */
    public void setCodigoUsuario(String nombreUsuario){
        if(nombreUsuario == null || nombreUsuario.equals("")){
            throw new IllegalArgumentException("Nombre de usuario no valido");
        }
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Metodo para dar el valor a la fecha de la reunion 
     * @param fecha Es la fecha de la reunion
     */
    public void setFecha(Timestamp fecha){
        if(fecha == null){
            throw new IllegalArgumentException("Fecha no valida");
        }
        this.fecha = fecha;
    }
    

    
    /**
     * Metodo para dar valor al identificador de la bicicleta que se va a anadir al sistema
     * @param codigoBicicleta  Es el identificador de la bicicleta que se esta dando de alta
     */
    public void setCodigoBicicleta(int codigoBicicleta){
        if(codigoBicicleta <= 0){
            throw new IllegalArgumentException("El codigo de la bicicleta no es valido");
        }
        this.codigoBicicleta = codigoBicicleta;
    }
    
    /**
     * Funcion que retorna el valor de la ciudad elegida para la reunion
     * @return La ciudad de la reunion
     */
    public String getCiudad(){
        return ciudad;
    }
    
    /**
     * Funcion que retorna el valor del nombre de usuario que quiere dar de alta a una bicicleta en el sistema
     * @return La cadena que es el identificador del usuario que quiere dar de alta la bicicleta en el sistema
     */
    public String getNombreUsuario(){
        return nombreUsuario;
    }
    
    /**
     * Funcion que retorna la fecha a la que esta programada la reunion
     * @return La Fecha establecida de la reunion
     */
    public Timestamp getFecha(){
        return fecha;
    }
   

    
    /**
     * Funcion que retorna el codigo de la bicicleta que se dara de alta en la reunion
     * @return el identificador de la bicicleta que se dara de alta
     */
    public int getCodigoBicicleta(){
        return codigoBicicleta;
    }

}
