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
public class ValoracionUsuario extends Valoracion{
    
    private String codigoUsuario;
    
    public ValoracionUsuario(){
        super();
        super.setDescripcion("");
    }
    
    /**
     * Funcion para dar valor al codigo del usuario que se esta valorando
     * @param codigoUsuario Es el codigo del usuario sobre el que se valora
     */
    public void setCodigoUsuario(String codigoUsuario){
        if (codigoUsuario == null || codigoUsuario.equals("")){throw new IllegalArgumentException("El codigo del usuario no puede ser no valido");}
        this.codigoUsuario = codigoUsuario;
    }
    
    /**
     * Metodo que devuelve el codigo del usuario al que se esta valorando
     * @return El codigo del usuario que se esta valorando
     */
    public String getCodigoUsuario(){
        return codigoUsuario;
    }
    
    
    
}
