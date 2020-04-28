package ridemybike.dominio;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Implementación de una petición
 */
public class Peticion implements Serializable{

    private int codigoPeticion;
    private Timestamp hora;
    private Timestamp horaLimite;
    private int codigoBici;
    private String nombreArrendatario;
    private TipoAlquiler tipo;

    /**
     * Constructor de una petición por defecto, sigue los patrones JavaBeans
     */
    public Peticion() {
        codigoPeticion = -1;
        hora = null;
        horaLimite = null;
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
    public Timestamp getHoraInicio() {
        return hora;
    }

    /**
     * Fija la fecha y hora para la que se realiza una petición.
     * @param hora: fecha y hora para la que se realiza la petición.
     */
    public void setHoraInicio(Timestamp hora) {
        if (hora == null) {
            throw new IllegalArgumentException("La hora está vacía.");
        }
        this.hora = hora;
    }

    /**
     * Devuelve el momento temporal límite para la caducidad de una petición.
     * @return fecha y hora en la que caduca la petición.
     */
    public Timestamp getHoraLimite() {
        return horaLimite;
    }

    /**
     * Fija la fecha y hora para la que caduca una petición.
     * @param tiempoLimite: fecha y hora en la que caduca la petición.
     */
    public void setHoraLimite(Timestamp tiempoLimite) {
        if (tiempoLimite == null) {
            throw new IllegalArgumentException("El tiempo límite está vacío.");
        }
        this.horaLimite = tiempoLimite;
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
