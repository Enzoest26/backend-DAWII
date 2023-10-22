package pe.com.backend.kenny.exception;

/**
 * 
 * Exception consiste cuando no se encuentra un item buscado.
 * <br>
 *	Cuenta con dos constructores, uno que da un mensaje <b>"Item no Encontrado"</b>,
 * 	otro personalizado de tipo {@link String}.
 * */
public class ItemNoEncontradoException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4094582195537614741L;
	
	
	public final static String mensaje = "Item no encotrado";
	
	
	public ItemNoEncontradoException() {
		super(mensaje);
	}
	public ItemNoEncontradoException(String mensaje) {
		super(mensaje);
	}

}
