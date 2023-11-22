package pe.com.backend.kenny.model.request;

import lombok.Data;

@Data
public class BebidaRegistrarRequest {

	//private String idBebida; se autogenera
	
	private String descripcionBebida;
	
	private double precioBebida;
	
	private int stockBebida;
	
	//private int estadoBebida; por defecto es 1
	
	private int idCategoriaBebida;
	
	private int idTipoBebida;

	private int idTamanioBebida;
	
	private byte[] imagen;
}
