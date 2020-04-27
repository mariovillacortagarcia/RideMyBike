package ridemybike.dominio;

import java.io.Serializable;
import javax.servlet.http.Part;

/**
 * Implementacion de un usuario
 */
public class Usuario implements Serializable {
    private String nombre;
    private String apellidos;
    private String dni;
    private String email;
    private long tlf;
    private String nombreUsuario;
    private String tarjetaCredito;
    private String hashPasswd;
    private Part fotoPerfil;
    private String direccion;
    
    /**
     * Inicializador segun los patrones JavaBeans
     */
    public Usuario(){
        nombre = null;
        apellidos = null;
        dni = null;
        email = null;
        tlf = -1;
        nombreUsuario = null;
        tarjetaCredito = null;
        hashPasswd = null;  
        fotoPerfil = null;
        direccion = null;
    }
    
    /**
     * Establece una direccion de domicilio para el usuario
     * 
     * @param direccion la direccion de residencia
     * @throws IllegalArgumentException si la direccion es igual a null
     */
    public void setDireccion(String direccion){
        if(direccion == null){
            throw new IllegalArgumentException("Direccion de domicilio igual a null");
        }
        this.direccion = direccion;
    }
    
    /**
     * Devuelve la direccion de residencia del usuario
     * 
     * @return un String con la direccion
     */
    public String getDireccion(){
        return direccion;
    }
    
    /**
     * Establece un nuevo nombre para el usuario
     * 
     * @param nombre el nombre del usuario
     * @throws IllegalArgumentException si el nombre es igual a null
     */
    public void setNombre(String nombre){
        if(nombre == null){
            throw new IllegalArgumentException("Nombre de usuario igual a null");
        }
        this.nombre = nombre;
    }
    
    /**
     * Establece los apellidos del usuario
     * 
     * @param apellidos los apellidos del usuario
     * @throws IllegalArgumentException si los apellidos son iguales a null
     */
    public void setApellidos(String apellidos){
        if(apellidos == null){
            throw new IllegalArgumentException("Apellidos de usuario igual a null");
        }
        this.apellidos = apellidos;
    }
    
    /**
     * Establece el DNI del usuario
     * 
     * @param dni el DNI del usuario
     * @throws IllegalArgumentException si el DNI es igual a null
     */
    public void setDni(String dni){
        if(dni == null){
            throw new IllegalArgumentException("DNI de usuario igual a null");
        }
        this.dni = dni;
    }
    
    /**
     * Establece el email del usuario
     * 
     * @param email el email del usuario
     * @throws IllegalArgumentException si el email es igual a null
     */
    public void setEmail(String email){
        if(email == null){
            throw new IllegalArgumentException("E-mail de usuario igual a null");
        }
        this.email = email;
    }
    
    /**
     * Establece el telefono del usuario
     * 
     * @param tlf el telefono del usuario
     * @throws IllegalArgumentException si el telefono dado es negativo
     */
    public void setTlf(long tlf){
        if(tlf < 0){
            throw new IllegalArgumentException("Telefono de usuario negativo");
        }
        this.tlf = tlf;
    }
    
    /**
     * Establece un nuevo nombre de usuario para el usuario
     * 
     * @param nombreUsuario el nombre del usuario
     * @throws IllegalArgumentException si el nombre es igual a null
     */
    public void setNombreUsuario(String nombreUsuario){
        if(nombreUsuario == null){
            throw new IllegalArgumentException("Nick de usuario igual a null");
        }
        this.nombreUsuario = nombreUsuario;
    }
    
    /**
     * Establece una nueva tarjeta de credito para el usuario
     * 
     * @param tarjetaCredito el numero de tarjeta del usuario
     * @throws IllegalArgumentException si el numero de tarjeta dado es igual a null
     */
    public void setTarjetaCredito(String tarjetaCredito){
        if(tarjetaCredito == null){
            throw new IllegalArgumentException("Tarjeta de usuario igual a null");
        }
        this.tarjetaCredito = tarjetaCredito;
    }
    
    /**
     * Establece el hash de la contraseña del usuario
     * 
     * @param hashPasswd el hash de la contraseña del usuario
     * @throws IllegalArgumentException si el hash dado es igual a null
     */
    public void setHashPasswd(String hashPasswd){
        if(hashPasswd == null){
            throw new IllegalArgumentException("Hash de contraseña igual a null");
        }
        this.hashPasswd = hashPasswd;
    }
    
    /**
     * Devuelve el nombre del usuario
     * 
     * @return un String con el nombre
     */
    public String getNombre(){
        return nombre;
    }
    
    /**
     * Devuelve el nick del usuario
     * 
     * @return un String con el nick
     */
    public String getNickName(){
        return nombreUsuario;
    }
    
    /**
     * Devuelve los apellidos del usuario
     * 
     * @return un String con los apellidos
     */
    public String getApellidos(){
        return apellidos;
    }
    
    /**
     * Devuelve el DNI del usuario
     * 
     * @return un String con el DNI
     */
    public String getDNI(){
        return dni;
    }
    
    /**
     * Devuelve el email del usuario
     * 
     * @return un String con el email
     */
    public String getEmail(){
        return email;
    }
    
    /**
     * Devuelve el telefono del usuario
     * 
     * @return un long con el telefono
     */
    public long getTlf(){
        return tlf;
    }
    
    /**
     * Devuelve el numero de tarjeta del usuario
     * 
     * @return un String con el numero de tarjeta
     */
    public String getTarjetaCredito(){
        return tarjetaCredito;
    }
    
    /**
     * Devuelve el hash de la contraseña del usuario
     * 
     * @return un String con el hash
     */
    public String getHashPasswd(){
        return hashPasswd;
    }
    
    /**
     * Establece la foto de perfil del usuario
     * 
     * @param foto la foto de perfil
     * @throws IllegalArgumentException si la foto es igual a null
     */
    public void setFotoPerfil(Part foto){
        if(foto == null){
            throw new IllegalArgumentException("Foto de perfil de usuario igual a null");
        }
        fotoPerfil = foto;
    }
    
    /**
     * Devuelve la foto de perfil del usuario
     * 
     * @return un Part con la foto
     */
    public Part getFotoPerfil(){
        return fotoPerfil;
    }
}
