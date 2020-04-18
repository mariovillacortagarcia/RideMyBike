/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

import java.sql.Time;

/**
 * Implementación de una petición
 */
public class Peticion {

    private int codigoPeticion;
    private Time hora;
    private Time tiempoLimite;
    private int codigoBici;
    private String nombreArrendatario;
    private TipoAlquiler tipo;

    /**
     * Constructor de una petición por defecto.
     */
    public Peticion() {
        codigoPeticion = -1;
        hora = null;
        tiempoLimite = null;
        codigoBici = -1;
        nombreArrendatario = "";
        tipo = null;
    }

    /**
     * Devuelve el código de identificación de una petición.
     * @return código numerico identificativo de la petición.
     */
    public int getCodigoPeticion() {
        return codigoPeticion;
    }

    /**
     * Fija el código identificativo y único de una petición.
     * @param codigoPeticion: código numerico identificativo de la petición.
     */
    public void setCodigoPeticion(int codigoPeticion) {
        if (codigoPeticion < 0) {
            throw new IllegalArgumentException("Código de petición erroneo.");
        }
        this.codigoPeticion = codigoPeticion;
    }

    /**
     * Devuelve el momento temporal para el que se ha realizado una petición.
     * @return fecha y hora para la que se realiza la petición.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Fija la fecha y hora para la que se realiza una petición.
     * @param hora: fecha y hora para la que se realiza la petición.
     */
    public void setHora(Time hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora está vacía.");
        }
        this.hora = hora;
    }

    /**
     * Devuelve el momento temporal límite para la caducidad de una petición.
     * @return fecha y hora en la que caduca la petición.
     */
    public Time getTiempoLimite() {
        return tiempoLimite;
    }

    /**
     * Fija la fecha y hora para la que caduca una petición.
     * @param tiempoLimite: fecha y hora en la que caduca la petición.
     */
    public void setTiempoLimite(Time tiempoLimite) {
        if (tiempoLimite == null) {
            throw new IllegalArgumentException("El tiempo límite está vacío.");
        }
        this.tiempoLimite = tiempoLimite;
    }

    /**
     * Devuelve el código de identificación de una bicicleta.
     * @return código numerico identificativo de una bicicleta.
     */
    public int getCodigoBici() {
        return codigoBici;
    }

    /**
     * Fija el código de identificación de una bicicleta.
     * @param codigoBici: código numerico identificativo de una bicicleta.
     */
    public void setCodigoBici(int codigoBici) {
        if (codigoBici < 0) {
            throw new IllegalArgumentException("Código de Bici erroneo.");
        }
        this.codigoBici = codigoBici;
    }

    /**
     * Devuelve el nombre del arrendatario propietario de la bicicleta solicitada.
     * @return nombre identificativo del propietario de la bicicleta solicitada.
     */
    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    /**
     * Fija el nombre del arrendatario propietario de la bicicleta solicitada.
     * @param nombreArrendatario: nombre identificativo del propietario de la bicicleta solicitada.
     */
    public void setNombreArrendatario(String nombreArrendatario) {
        if (nombreArrendatario.equals("")) {
            throw new IllegalArgumentException("El nombre del arrendatario está vacío.");
        }
        this.nombreArrendatario = nombreArrendatario;
    }

    /**
     * Devuelve el tipo de alquiler solicitado en la petición.
     * @return tipo de alquiler solicitado.
     */
    public TipoAlquiler getTipo() {
        return tipo;
    }

    /**
     * Fija el tipo de alquiler que se solicita en la petición.
     * @param tipo tipo de alquiler solicitado.
     */
    public void setTipo(TipoAlquiler tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de alquiler está vacío.");
        }
        this.tipo = tipo;
    }
}
