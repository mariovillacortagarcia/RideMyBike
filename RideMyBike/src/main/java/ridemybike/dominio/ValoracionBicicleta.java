package ridemybike.dominio;

/**
 *
 * @author Alberto
 */
public class ValoracionBicicleta extends Valoracion{
    private String codigoBicicleta;
    
    /**
     * Metodo constructor vacio. siguiendo las indicaciones de JavaBeans
     */
    public ValoracionBicicleta(){
        super();
        codigoBicicleta = "";
    }
    /**
     * Metodo constructor con el parametro del codigo de la bicicleta
     * @param codigoBicicleta El String que contiene el codigo de la bicicleta que se esta valorando
     */
    public ValoracionBicicleta(String codigoBicicleta){
        super();
        if(codigoBicicleta == null || codigoBicicleta.equals("")){
            throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valida");
        }
        this.codigoBicicleta = codigoBicicleta;
    }
    /**
     * Metodo para poder dar valor al valor del codigo de la bicicleta que se esta valorando
     * @param codigoBicicleta Es el codigo de la bicicleta que se valora
     */
    public void setCodigoBicicleta(String codigoBicicleta){
        if(codigoBicicleta == null || codigoBicicleta.equals("")){
            throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valida");
        }
        this.codigoBicicleta = codigoBicicleta;
    }
    /**
     * Funcion que retorna el valor del identificador de la bicicleta que se esta valorando
     * @return la cadena que indica el identificador de la bicicleta valorada
     */
    public String getCodigoBicicleta(){
        return codigoBicicleta;
    }
}
