/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

/**
 *
 * @author davidmd
 */
public class Bicicleta {
  private String codigoBici;
  private String descripcion;
  private double tamCuadro;
  private String imagen;
  private String marca;
  private Freno freno;
  private double latitud;
  private double longitud;
  private String usuarioPropietario;

  /**
  * Funcion constructor, vacio ya que se isntaciaran los atributos posteriormente
  */
  public Bicicleta(){

  }
  /**
  * Funcion para dar un valor al codigo de la bicicleta
  * @param codigoBici es una cadena de texto que contiene el valor que se le da como identificador a la bicicleta
  */
  public void setCodigoBici(String codigoBici){
    if((codigoBici == null) || (codigoBici.equals(""))){
      throw new IllegalArgumentException("El codigo de la bicicleta no puede ser vacio o nulo");
    }
    this.codigoBici = codigoBici;
  }
  /**
  * Funcion para dar un valor a la descripcion de la bicicleta
  * @param descripcion es una cadena de texto que contiene el valor que se le da como descripcion a la bicicleta
  */
  public void setDescripcion(String descripcion){
    if(descripcion == null || descripcion.equals("")){
      throw new IllegalArgumentException("La descripcion de la bicicleta no puede ser vacia o nula");
    }
    this.descripcion = descripcion;
  }
  /**
  * Funcion para dar un valor al tamano de cudro de la bicicleta
  * @param tamCuadro es un numero entero positivo que contiene el valor que se le da al tamano del cuadro de la bicicleta
  */
  public void setTamCuadro(double tamCuadro){
    if(tamCuadro <= 0){
      throw new IllegalArgumentException("El tamano del cuadro de la bicicleta no puede ser un numero negativo o 0");
    }
    this.tamCuadro = tamCuadro;
  }
  /**
  * Funcion para dar un valor a la cadena que da el valor a la imagen de la bicicleta
  * @param imagen es una cadena de texto que contiene el valor que se le da al link donde se situa la imagen de la bicicleta
  */
  public void setImagen(String imagen){
    if(imagen == null || imagen.equals("")){
      throw new IllegalArgumentException("El link a la imagen de la bicicleta no puede ser vacia o nula");
    }
    this.imagen = imagen;
  }
  /**
  * Funcion para dar un valor a la marca de la bicicleta
  * @param marca es una cadena de texto que contiene el valor que se le da como marca a la bicicleta
  */
  public void setMarca(String marca){
    if(marca == null || marca.equals("")){
      throw new IllegalArgumentException("La marca de la bicicleta no puede ser vacia o nula");
    }
    this.marca = marca;
  }
  /**
  * Funcion para dar un valor al freno de la bicicleta
  * @param freno es una tipo de freno que contiene el valor que se le da al freno de la bicicleta
  */
  public void setFreno(Freno freno){
    if(freno == null){
      throw new IllegalArgumentException("El freno debe tener un valor entre los que estan permitidos");
    }
    this.freno = freno;
  }
  /**
  * Funcion para dar un valor a la latitud de la posicion de la bicicleta
  * @param latitud es un numero entero que contiene el valor que se le da a la latitud de las coordenadas de la bicicleta
  */
  public void setLatitud(double latitud){

    this.latitud = latitud;
  }
  /**
  * Funcion para dar un valor a la longitud de la posicion de la bicicleta
  * @param longitud es un numero entero que contiene el valor que se le da a la longitud de las coordenadas de la bicicleta
  */
  public void setLongitud(double longitud){

    this.longitud = longitud;
  }
  /**
  * Funcion para dar un valor al id del usuario propietario de la bicicleta
  * @param usuarioPropietario es una cadena de texto que contiene el valor que se le da al id del usuario propietario de la bicicleta
  */
  public void setUsuarioPropietario(String usuarioPropietario){
    if(usuarioPropietario == null || usuarioPropietario.equals("")){
      throw new IllegalArgumentException("El identificador del usuario propietario de la bicicleta no puede ser vacio o nulo");
    }
    this.usuarioPropietario = usuarioPropietario;
  }
  /**
  * Metodo que devuelve el valor del codigo de la bicileta
  * @return El codigo identificador de la bicicleta
  */
  public String getcodigoBici(){
    return codigoBici;
  }
  /**
  * Metodo que devuelve el valor de la descripcion de la bicicleta
  * @return El mensaje usado para la descripcion de la bicicleta
  */
  public String getDescripcion(){
    return descripcion;
  }
  /**
  * Metodo que devuelve el valor del link de la imagne almacenada de la bicicleta
  * @return El link usado para localizar la imagen de la bicicleta
  */
  public String getImagen(){
    return imagen;
  }
  /**
  * Metodo que devuelve el valor de la marca almacenada de la bicicleta
  * @return La cadena que muestra la marca de la bicicleta
  */
  public String getMarca(){
    return marca;
  }
  /**
  * Metodo que devuelve el valor del identificador del usuario propietario de la bicicleta
  * @return El identificador del usuario propietario de la bicicleta
  */
  public String getUsuarioPropietario(){
    return usuarioPropietario;
  }
  /**
  * Metodo que devuelve el valor almacenado del tamano del cuadro
  * @return El valor numerico que expresa el tamaño del cuadro de la bicicleta
  */
  public double getTamCuadro(){
    return tamCuadro;
  }
  /**
  * Metodo que devuelve el valor de la longitud de las coordenadas de la bicicleta almacenada
  * @return El valor numerico que indica la longitud de las coordenadas de la bicicleta
  */
  public double getLongitud(){
    return longitud;
  }
  /**
  * Metodo que devuelveel valor de la latitud de las coordenadas de la bicicleta almacenada
  * @return El valor numerico que indica la latitud de las coordenadas de la bicicleta
  */
  public double getLatitud(){
    return latitud;
  }
  /**
  * Metodo que devuelve el tipo del freno que tiene la bicicleta
  * @return El tipo de freno que usa la bicicleta
  */
  public Freno getFreno(){
    return freno;
  }
}
