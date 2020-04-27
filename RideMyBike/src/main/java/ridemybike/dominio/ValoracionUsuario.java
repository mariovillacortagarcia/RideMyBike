package ridemybike.dominio;

/**
 * La valoracion que se le hace al ususario tanto si es el usuario que alquila como si es el dueno de la bicicleta
 */
public class ValoracionUsuario extends Valoracion{
    
    private String usuarioValorado;
    
    /**
     * Metodo constructor de la valoracion, sigue los patrones JavaBeans
     */
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
