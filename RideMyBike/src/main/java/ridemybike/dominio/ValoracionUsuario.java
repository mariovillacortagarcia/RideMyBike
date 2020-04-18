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
    
    private String usuarioValorado;
    
    public ValoracionUsuario(){
        super();
        super.setDescripcion("");
    }
    
    /**
     * Funcion para dar valor al codigo del usuario que se esta valorando
     * @param usuarioValorado Es el codigo del usuario sobre el que se valora
     */
    public void setUsuarioValorado(String usuarioValorado){
        if (usuarioValorado == null || usuarioValorado.equals("")){throw new IllegalArgumentException("El usuario no puede ser no valido");}
        this.usuarioValorado = usuarioValorado;
    }
    
    /**
     * Metodo que devuelve el codigo del usuario al que se esta valorando
     * @return El codigo del usuario que se esta valorando
     */
    public String getCodigoUsuario(){
        return usuarioValorado;
    }
    
    
    
}
