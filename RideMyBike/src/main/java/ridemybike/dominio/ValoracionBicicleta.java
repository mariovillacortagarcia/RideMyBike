package ridemybike.dominio;

import ridemybike.dominio.Valoracion;

/**
 * Valoracion realizada sobre la bicicleta tras finalizar el alquiler de una bicicleta
 */
public class ValoracionBicicleta extends Valoracion{
    private int codigoBicicleta;
    
    /**
     * Metodo constructor vacio. siguiendo las indicaciones de JavaBeans
     */
    public ValoracionBicicleta(){
        super();
        codigoBicicleta = 0;
    }
    /**
     * Metodo constructor con el parametro del codigo de la bicicleta
     * @param codigoBicicleta El String que contiene el codigo de la bicicleta que se esta valorando
     */
    public ValoracionBicicleta(int codigoBicicleta){
        super();
        if(codigoBicicleta <= 0){
            throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valida");
        }
        this.codigoBicicleta = codigoBicicleta;
    }
    /**
     * Metodo para poder dar valor al valor del codigo de la bicicleta que se esta valorando
     * @param codigoBicicleta Es el codigo de la bicicleta que se valora
     */
    public void setCodigoBicicleta(int codigoBicicleta){
        if(codigoBicicleta <= 0){
            throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valida");
        }
        this.codigoBicicleta = codigoBicicleta;
    }
    /**
     * Funcion que retorna el valor del identificador de la bicicleta que se esta valorando
     * @return la cadena que indica el identificador de la bicicleta valorada
     */
    public int getCodigoBicicleta(){
        return codigoBicicleta;
    }
}
