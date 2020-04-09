/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

import java.io.Serializable;
import java.util.Date;

/**
 * Representa un alquiler de una bicicleta realizado en el sistema.
 * @author Mario Villacorta Garcia
 */
public class Alquiler implements Serializable{

    private double precio;
    private Date horaInicial;
    private Date horaFinal;
    private String codigoAlquiler;
    private boolean enMano;
    private Peticion peticion;
    /**
     * Inicializador de un alquiler vacio.
     */
    public Alquiler() {
        precio = 0;
        horaInicial = null;
        horaFinal = null;
        codigoAlquiler = null;
        enMano = false;
        peticion = null;
        
    }

    /**
     * Establece el precio del alquiler.
     *
     * @param precio : nuevo precio
     * @throws IllegalArgumentException si el precio es negativo
     */
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }

        this.precio = precio;
    }

    /**
     * Obtiene el precio del alquiler.
     *
     * @return precio del alquiler
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece la hora de inicio del alquiler.
     *
     * @param horaInicial: hora a la que se inicia el prestamo, distinta de null
     * @throws IllegalArgumentException si {@code horaInicial == null}
     */
    public void setHoraInicial(Date horaInicial) {
        if (horaInicial == null) {
            throw new IllegalArgumentException("La hora inicial no puede estar vacia");
        }
        this.horaInicial = horaInicial;
    }

    /**
     * Obtiene la hora de inicio del alquiler.
     *
     * @return Hora de inicio del prestamo o null si
     * {@code viajeIniciado() == false}
     */
    public Date getHoraInicial() {
        return horaInicial;
    }

    /**
     * Establece la hora de finalización del alquiler.
     *
     * @param horaFinal: hora a la que se finaliza el prestamo.
     */
    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Obtiene la hora de finalizacion del alquiler.
     *
     * @return Hora de finalizacion del prestamo o null si
     * {@code viajeFinalizado() == false}
     */
    public Date getHoraFinal() {
        return horaFinal;
    }
    /**
     * Establece si se trata de un alquiler en mano o no.
     * @param enMano: true si el alquiler es en mano, false si es un alquiler estandar
     */
    public void setenMano(boolean enMano){
        this.enMano = enMano;
    }
    /**
     * Comprueba si el alquiler es en mano o no.
     * @return true si el alquiler es en mano, false si es un alquiler estandar
     */
    public boolean getenMano(){
        return enMano;
    }
    /**
     * Establece la peticion de la que surge el alquiler.
     * @param peticion: peticion del alquiler.
     */
    public void setPeticion(Peticion peticion){
        if(peticion == null){
            throw new IllegalArgumentException("La peticion no puede ser nula");
        }
        if(this.peticion != null){
            throw new IllegalArgumentException("Ya se ha establecido una peticion del alquiler");
        }
        this.peticion = peticion;
    }
    /**
     * Obtiene la peticion de la que surge el alquiler.
     * @return peticion del alquiler, null si no esta establecida.
     */
    public Peticion getPeticion(){
        return peticion;
    }
    
    public void setCodigoAlquiler(String codigo){}
    
    public String getCodigoAlquiler(){
        return codigoAlquiler;
    }
    /**
     * Comprueba si el viaje se ha iniciado.
     *
     * @return true si el viaje se ha iniciado, false si no.
     */
    public boolean viajeIniciado() {
        if (getHoraInicial() == null) {
            return false;
        }
        return true;
    }

    /**
     * Comprueba si el viaje se ha finalizado
     *
     * @return true si el viaje se ha finalizado tras ser iniciado, false si no
     */
    public boolean viajeFinalizado() {
        if (viajeIniciado() && getHoraFinal() != null) {
            return true;
        }
        return false;
    }

}
