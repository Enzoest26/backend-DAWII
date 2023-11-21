package pe.com.backend.kenny.model.request;

import lombok.Data;

@Data
public class ComidaRegistrarRequest {
	
	//private String idComida; se autogenera

	private String descComida;
	
	private double precioComida;
	
	private int stockComida;
	
	private String tipoComida;
	
	private int idTipoPostre;
	
	private byte[] imagen;

	//private int estadoComida; por defecto 1 al registrar
}
