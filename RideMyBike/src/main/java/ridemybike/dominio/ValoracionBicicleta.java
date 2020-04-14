/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ridemybike.dominio;

/**
 *
 * @author Alberto
 */
public class ValoracionBicicleta extends Valoracion{
    private String codigoBicicleta;
    
    public ValoracionBicicleta(){
        super();
    }
    
    /**
     * Funcion para dar valor al codigo de la bicicleta que se va a valorar
     * @param codigoBicicleta Es el codigo de la bicicleta
     */
    public void setCodigoBicicleta(String codigoBicicleta){
        if(codigoBicicleta == null || codigoBicicleta.equals("")){throw new IllegalArgumentException("El codigo de la bicicleta no puede ser no valido");}
        this.codigoBicicleta=codigoBicicleta;
    }
    
    /**
     * Metodo que returna el codigo de la bicicleta que se esta puntuando
     * @return El codigo de la bicicleta sobre el que se opina
     */
    public String getCodigobicicleta(){
        return codigoBicicleta;
    }
}
