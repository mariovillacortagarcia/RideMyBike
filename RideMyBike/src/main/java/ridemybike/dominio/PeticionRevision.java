/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Peticion que hace un usuario para poder dar de alta la bicicleta que quiere anadir al sistema
 * @author Alberto
 */

public class PeticionRevision {
    private String ciudad;
    private String nombreUsuario;
    private LocalDate fecha;
    private LocalDateTime hora;
    private String codigo;
    private String codigoBicicleta;
    
    /**
     * Metodo constructor que cumple los estandares de JavaBeans sin argumentos
     */
    public PeticionRevision(){
        ciudad = "";
        nombreUsuario = "";
        fecha = null;
        hora = null;
        codigo = "";
        codigoBicicleta = "";
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
    public void setFecha(LocalDate fecha){
        if(fecha == null){
            throw new IllegalArgumentException("Fecha no valida");
        }
        this.fecha = fecha;
    }
    /**
     * Metodo para dar valor a la hora de la reunion
     * @param hora Es la hora de la reunion 
     */
    public void setHora(LocalDateTime hora){
        if(hora == null){
            throw new IllegalArgumentException("Hora no valida");
        }
        this.hora = hora;
    }
    
    /**
     * Metodo para dar valor al codigo identificador de la reunion que se va a realizar 
     * @param codigo Es la cadena identificadora de la reunion
     */
    public void setCodigo(String codigo){
        if(codigo == null || codigo.equals("")){
            throw new IllegalArgumentException("Codigo no valida");
        }
        this.codigo = codigo;
    }
    
    /**
     * Metodo para dar valor al identificador de la bicicleta que se va a anadir al sistema
     * @param codigoBicicleta  Es el identificador de la bicicleta que se esta dando de alta
     */
    public void setCodigoBicicleta(String codigoBicicleta){
        if(codigoBicicleta == null || codigoBicicleta.equals("")){
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
    public LocalDate getFecha(){
        return fecha;
    }
    
    /**
     * Funcion que retorna la hora a la que esta establecida la reunion
     * @return Es la hora de la reunion
     */
    public LocalDateTime getHora(){
        return hora;
    }
    
    /**
     * Funcion que retorna el codigo identificador de la reunion
     * @return La cadena identificadora de la reunion establecida
     */
    public String getCodigo(){
        return codigo;
    }
    
    /**
     * Funcion que retorna el codigo de la bicicleta que se dara de alta en la reunion
     * @return el identificador de la bicicleta que se dara de alta
     */
    public String getCodigoBicicleta(){
        return codigoBicicleta;
    }

}
