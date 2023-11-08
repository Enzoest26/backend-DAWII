package pe.com.backend.kenny.exception;

/**
 * 
 * Exception consiste cuando no se encuentra un item buscado.
 * <br>
 *	Cuenta con dos constructores, uno que da un mensaje <b>"Item no Encontrado"</b>,
 * 	otro personalizado de tipo {@link String}.
 * */
public class AuthorizationNoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4094582195537614741L;
	
	
	public final static String mensaje = "Falta el header de authorizacion";
	
	
	public AuthorizationNoEncontradoException() {
		super(mensaje);
	}
	public AuthorizationNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
