package ridemybike.dominio;

import java.io.Serializable;
import javax.servlet.http.Part;

/**
 * Bicicleta que se regista en el sistema para ser alquilada
 */
public class Bicicleta implements Serializable{
  private int codigoBici;
  private String descripcion;
  private String modelo;
  private double tamCuadro;
  private Part imagen;
  private String marca;
  private Freno freno;
  private double latitud;
  private double longitud;
  private String usuarioPropietario;
  private EstadoBicicleta estado;
  private String codigoActivacion;
  private int eliminada;

  /**
  * Metodo constructor creado a partir de los estandares JavaBeans
  */
  public Bicicleta(){
      codigoBici = 0;
      descripcion = "";
      modelo = "";
      tamCuadro = 0;
      imagen = null;
      marca = "";
      freno = null;
      latitud = 41.652740; 
      longitud = -4.762530;
      usuarioPropietario = "";
      estado = null;
      codigoActivacion = "";
      eliminada = -1;
  }
  /**
  * Metodo para dar un valor al codigo de la bicicleta
  * @param codigoBici es una cadena de texto que contiene el valor que se le da como identificador a la bicicleta
  */
  public void setCodigoBici(int codigoBici){
    if(codigoBici <= 0 ){
      throw new IllegalArgumentException("El codigo de la bicicleta no puede ser menor que 0");
    }
    this.codigoBici = codigoBici;
  }
  /**
  * Metodo para dar un valor a la descripcion de la bicicleta
  * @param descripcion es una cadena de texto que contiene el valor que se le da como descripcion a la bicicleta
  */
  public void setDescripcion(String descripcion){
    if(descripcion == null || descripcion.equals("")){
      throw new IllegalArgumentException("La descripcion de la bicicleta no puede ser vacia o nula");
    }
    this.descripcion = descripcion;
  }
  /**
  * Metodo para dar un valor al modelo de la bicicleta
  * @param modelo es una cadena de texto que contiene el valor que se le da como modelo a la bicicleta
  */
  public void setModelo(String modelo){
    if(modelo == null || modelo.equals("")){
      throw new IllegalArgumentException("El modelo de la bicicleta no puede ser vacia o nula");
    }
    this.modelo = modelo;
  }
  /**
  * Metodo para dar un valor al tamano de cudro de la bicicleta
  * @param tamCuadro es un numero entero positivo que contiene el valor que se le da al tamano del cuadro de la bicicleta
  */
  public void setTamCuadro(double tamCuadro){
    if(tamCuadro <= 0){
      throw new IllegalArgumentException("El tamano del cuadro de la bicicleta no puede ser un numero negativo o 0");
    }
    this.tamCuadro = tamCuadro;
  }
  /**
  * Metodo para dar un valor a la imagen de la bicicleta
  * @param imagen es una cadena de texto que contiene el valor que se le da al link donde se situa la imagen de la bicicleta
  */
  public void setImagen(Part imagen){
    if(imagen == null){
      throw new IllegalArgumentException("La imagen de la bicicleta no puede ser nula");
    }
    this.imagen = imagen;
  }
  /**
  * Metodo para dar un valor a la marca de la bicicleta
  * @param marca es una cadena de texto que contiene el valor que se le da como marca a la bicicleta
  */
  public void setMarca(String marca){
    if(marca == null || marca.equals("")){
      throw new IllegalArgumentException("La marca de la bicicleta no puede ser vacia o nula");
    }
    this.marca = marca;
  }
  /**
  * Metodo para dar un valor al freno de la bicicleta
  * @param freno es una tipo de freno que contiene el valor que se le da al freno de la bicicleta
  */
  public void setFreno(Freno freno){
    if(freno == null){
      throw new IllegalArgumentException("El freno debe tener un valor entre los que estan permitidos");
    }
    this.freno = freno;
  }
  /**
  * Metodo para dar un valor a la latitud de la posicion de la bicicleta
  * @param latitud es un numero entero que contiene el valor que se le da a la latitud de las coordenadas de la bicicleta
  */
  public void setLatitud(double latitud){

    this.latitud = latitud;
  }
  /**
  * Metodo para dar un valor a la longitud de la posicion de la bicicleta
  * @param longitud es un numero entero que contiene el valor que se le da a la longitud de las coordenadas de la bicicleta
  */
  public void setLongitud(double longitud){

    this.longitud = longitud;
  }
  /**
  * Metodo para dar un valor al id del usuario propietario de la bicicleta
  * @param usuarioPropietario es una cadena de texto que contiene el valor que se le da al id del usuario propietario de la bicicleta
  */
  public void setUsuarioPropietario(String usuarioPropietario){
    if(usuarioPropietario == null || usuarioPropietario.equals("")){
      throw new IllegalArgumentException("El identificador del usuario propietario de la bicicleta no puede ser vacio o nulo");
    }
    this.usuarioPropietario = usuarioPropietario;
  }
  /**
   * Metodo para dar un valor al estado de la bicicleta
   * @param estado es el estado de la situacion de la bicicleta
   */
  public void setEstado(EstadoBicicleta estado){
      if(estado == null){
          throw new IllegalArgumentException("El estado de la bicicleta no puede ser null");
      }
      this.estado=estado;
  }
  /**
   * Metodo para dar valor al codigo de activacion que tiene la bicicleta para introducirla en el sistema
   * @param codigoActivacion es el codigo para darle de alta en el sistema
   */
  public void setCodigoActivacion(String codigoActivacion){
      if(codigoActivacion == null || codigoActivacion.equals("")){
      throw new IllegalArgumentException("El codigo de activacion de la bicicleta no puede ser vacio o nulo");
      }
      this.codigoActivacion=codigoActivacion;
  }
  
  /**
  * Funcion que devuelve el valor del codigo de la bicileta
  * @return El codigo identificador de la bicicleta
  */
  public int getcodigoBici(){
    return codigoBici;
  }
  /**
  * Funcion que devuelve el valor de la descripcion de la bicicleta
  * @return El mensaje usado para la descripcion de la bicicleta
  */
  public String getDescripcion(){
    return descripcion;
  }
  /**
   * Funcion que devuelve el modelo de la bicicleta
   * @return La cadena que representa la informacion sobre el modelo de la bicicleta
   */
  public String getModelo(){
      return modelo;
  }
  /**
  * Funcion que devuelve la imagen almacenada de la bicicleta
  * @return El link usado para localizar la imagen de la bicicleta
  */
  public Part getImagen(){
    return imagen;
  }
  /**
  * Funcion que devuelve el valor de la marca almacenada de la bicicleta
  * @return La cadena que muestra la marca de la bicicleta
  */
  public String getMarca(){
    return marca;
  }
  /**
  * Funcion que devuelve el valor del identificador del usuario propietario de la bicicleta
  * @return El identificador del usuario propietario de la bicicleta
  */
  public String getUsuarioPropietario(){
    return usuarioPropietario;
  }
  /**
  * Funcion que devuelve el valor almacenado del tamano del cuadro
  * @return El valor numerico que expresa el tamaño del cuadro de la bicicleta
  */
  public double getTamCuadro(){
    return tamCuadro;
  }
  /**
  * Funcion que devuelve el valor de la longitud de las coordenadas de la bicicleta almacenada
  * @return El valor numerico que indica la longitud de las coordenadas de la bicicleta
  */
  public double getLongitud(){
    return longitud;
  }
  /**
  * Funcion que devuelveel valor de la latitud de las coordenadas de la bicicleta almacenada
  * @return El valor numerico que indica la latitud de las coordenadas de la bicicleta
  */
  public double getLatitud(){
    return latitud;
  }
  /**
  * Funcion que devuelve el tipo del freno que tiene la bicicleta
  * @return El tipo de freno que usa la bicicleta
  */
  public Freno getFreno(){
    return freno;
  }
  /**
   * Funcion que devuelve el estado actual de la bicicleta
   * @return El estado actual de la bicicleta
   */
  public EstadoBicicleta getEstado(){
      return estado;
  }
  /**
   * Funcion que devuelve el codigo de activacion de la bicicleta
   * @return El codigo de activacion de la bicicleta
   */
  public String getCodigoActivacion(){
      return codigoActivacion;
  }

    public void setEliminada(int n) {
        eliminada = n;
    }
  
}
