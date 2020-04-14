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
     *
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
     *
     * @return
     */
    public int getCodigoPeticion() {
        return codigoPeticion;
    }

    /**
     *
     * @param codigoPeticion
     */
    public void setCodigoPeticion(int codigoPeticion) {
        if (codigoPeticion < 0) {
            throw new IllegalArgumentException("Código de petición erroneo.");
        }
        this.codigoPeticion = codigoPeticion;
    }

    /**
     *
     * @return
     */
    public Time getHora() {
        return hora;
    }

    /**
     *
     * @param hora
     */
    public void setHora(Time hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora está vacía.");
        }
        this.hora = hora;
    }

    /**
     *
     * @return
     */
    public Time getTiempoLimite() {
        return tiempoLimite;
    }

    /**
     *
     * @param tiempoLimite
     */
    public void setTiempoLimite(Time tiempoLimite) {
        if (tiempoLimite == null) {
            throw new IllegalArgumentException("El tiempo límite está vacío.");
        }
        this.tiempoLimite = tiempoLimite;
    }

    /**
     *
     * @return
     */
    public int getCodigoBici() {
        return codigoBici;
    }

    /**
     *
     * @param codigoBici
     */
    public void setCodigoBici(int codigoBici) {
        if (codigoBici < 0) {
            throw new IllegalArgumentException("Código de Bici erroneo.");
        }
        this.codigoBici = codigoBici;
    }

    /**
     *
     * @return
     */
    public String getNombreArrendatario() {
        return nombreArrendatario;
    }

    /**
     *
     * @param nombreArrendatario
     */
    public void setNombreArrendatario(String nombreArrendatario) {
        if (nombreArrendatario.equals("")) {
            throw new IllegalArgumentException("El nombre del arrendatario está vacío.");
        }
        this.nombreArrendatario = nombreArrendatario;
    }

    /**
     *
     * @return
     */
    public TipoAlquiler getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(TipoAlquiler tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de alquiler está vacío.");
        }
        this.tipo = tipo;
    }
}
