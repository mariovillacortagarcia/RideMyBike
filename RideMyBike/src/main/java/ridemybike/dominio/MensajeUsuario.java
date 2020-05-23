package ridemybike.dominio;

import java.sql.Timestamp;

/**
 * Implementacion de un mensaje recibido del usuario
 */
public class MensajeUsuario {
    private String nombreUsuario;
    private String codigo;
    private String asunto;
    private String descripcion;
    private Timestamp fechaCreacion;
    private Timestamp fechaAtencion;
    
    /**
     * Inicializador
     */
    public MensajeUsuario(){
        nombreUsuario = null;
        asunto = null;
        descripcion = null;
        fechaCreacion = null;
        fechaAtencion = null;
        codigo = null;
    }
    
    /**
     * Establece el nombre de usuario que envia el mensaje
     * 
     * @param usuario el nombre de usuario
     */
    public void setNombreUsuario(String usuario){
        nombreUsuario = usuario;
    }
    
    /**
     * Establece el codigo del mensaje
     * 
     * @param codigo el codigo
     */
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    
    /**
     * Establece el asunto del mensaje
     * 
     * @param asunto el asunto del mensaje
     */
    public void setAsunto(String asunto){
        this.asunto = asunto;
    }
    
    /**
     * Establece el cuerpo del mensaje
     * 
     * @param descripcion el cuerpo del mensaje
     */
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    /**
     * Establece la fecha de creacion del mensaje
     * 
     * @param fechaCreacion la fecha
     */
    public void setFechaCreacion(Timestamp fechaCreacion){
        this.fechaCreacion = fechaCreacion;
    }
    
    /**
     * Establece la fecha en la que se atendio el mensaje
     * 
     * @param fechaAtencion la fecha
     */
    public void setFechaAtencion(Timestamp fechaAtencion){
        this.fechaAtencion = fechaAtencion;
    }
    
    /**
     * Devuelve el nombre de usuario que envio el mensaje
     * 
     * @return un String con el nombre de usuario
     */
    public String getNombreUsuario(){
        return nombreUsuario;
    }
    
    /**
     * Devuelve el asunto del mensaje
     * 
     * @return un String con el asunto
     */
    public String getAsunto(){
        return asunto;
    }
    
    /**
     * Devuelve la descripcion del mensaje
     * 
     * @return un String con la descripcion
     */
    public String getDescripcion(){
        return descripcion;
    }
    
    /**
     * Devuelve la fecha de creacion del mensaje
     * 
     * @return un Timestamp con la fecha
     */
    public Timestamp getFechaCreacion(){
        return fechaCreacion;
    }
    
    /**
     * Devuelve la fecha de atencion del mensaje
     * 
     * @return un Timestamp con la fecha
     */
    public Timestamp getFechaAtencion(){
        return fechaAtencion;
    }
    
    /**
     * Devuelve el codigo del mensaje
     * 
     * @return un String con el codigo
     */
    public String getCodigo(){
        return codigo;
    }
}
