package pe.com.backend.kenny.model.request;

import lombok.Data;

@Data
public class BebidaActualizarRequest {
	
	//private String idBebida;
	
	private String descripcionBebida;
	
	private double precioBebida;
	
	private int stockBebida;
	
	private int estadoBebida;
	
	private int idCategoriaBebida;
	
	private int idTipoBebida;

	private int idTamanioBebida;
	
	private byte[] imagen;
	
}
