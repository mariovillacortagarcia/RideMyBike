package ridemybike.dominio;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Representa un alquiler de una bicicleta realizado en el sistema.
 *
 * @author Mario Villacorta Garcia
 */
public class Alquiler implements Serializable {

    private double precio;
    private LocalDateTime horaInicial;
    private LocalDateTime horaFinal;
    private int codigoAlquiler;
    private String peticion;
    private boolean archivado;
    private String inicio;
    private String fin;

    /**
     * Inicializador de un alquiler vacio.
     */
    public Alquiler() {
        precio = 0;
        horaInicial = null;
        horaFinal = null;
        codigoAlquiler = 0;
        peticion = null;
        archivado = false;
        inicio = null;
        fin = null;

    }

    /**
     * Establece el inicio del alquiler
     *
     * @param inicio: nombre de la ubicacion de inicio del alquiler
     * @throws IllegalArgumentException si {@code getInicio() != null}
     */
    public void setInicio(String inicio) {
        if (inicio != null) {
            throw new IllegalArgumentException("Ya se establecio un inicio");
        }

        this.inicio = inicio;
    }

    /**
     * Obtiene el inicio del alquiler
     *
     * @return nombre de la ubicacion de inicio del alquiler, null si no esta
     * establecida
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * Establece el fin del alquiler
     *
     * @param fin: nombre de la ubicacion de fin del alquiler
     * @throws IllegalArgumentException si {@code getFin() != null}
     */
    public void setFin(String fin) {
        if (inicio != null) {
            throw new IllegalArgumentException("Ya se establecio un inicio");
        }

        this.fin = fin;
    }

    /**
     * Obtiene el fin del alquiler
     *
     * @return nombre de la ubicacion de fin del alquiler, null si no esta
     * establecida
     */
    public String getFin() {
        return fin;
    }

    /**
     * Obtiene el estado de archivo del alquiler.
     *
     * @return true si esta archivado, false si no.
     */
    public boolean getArchivado() {
        return archivado;
    }

    /**
     * Establece el estado de archivo del alquiler.
     *
     * @param archivado : true si esta archivado, false si no.
     * @throws IllegalArgumentExeption si {@code getHoraFinal() == null}
     */
    public void setArchivado(boolean archivado) {
        if (getHoraFinal() == null) {
            throw new IllegalArgumentException("No se puede archivar un viaje no finalizado");
        }
        this.archivado = archivado;
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
    public void setHoraInicial(LocalDateTime horaInicial) {
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
    public LocalDateTime getHoraInicial() {
        return horaInicial;
    }

    /**
     * Establece la hora de finalización del alquiler.
     *
     * @param horaFinal: hora a la que se finaliza el prestamo.
     */
    public void setHoraFinal(LocalDateTime horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * Obtiene la hora de finalizacion del alquiler.
     *
     * @return Hora de finalizacion del prestamo o null si
     * {@code viajeFinalizado() == false}
     */
    public LocalDateTime getHoraFinal() {
        return horaFinal;
    }

    /**
     * Establece la peticion de la que surge el alquiler.
     *
     * @param peticion: id de la peticion del alquiler.
     */
    public void setPeticion(String peticion) {
        if (peticion == null) {
            throw new IllegalArgumentException("La peticion no puede ser nula");
        }
        if (this.peticion != null) {
            throw new IllegalArgumentException("Ya se ha establecido una peticion del alquiler");
        }
        this.peticion = peticion;
    }

    /**
     * Obtiene la peticion de la que surge el alquiler.
     *
     * @return peticion del alquiler, null si no esta establecida.
     */
    public String getPeticion() {
        return peticion;
    }

    public void setCodigoAlquiler(int codigo) {
        codigoAlquiler = codigo;
    }

    public int getCodigoAlquiler() {
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
     * private void setValoracion(Valoracion v){ valoracion = v;
     *
     * }
     *
     * private Valoracion getValoracion(){ return valoracion; }
     *
     * /**
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
