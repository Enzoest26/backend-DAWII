package pe.com.backend.kenny.model.request;

import lombok.Data;

@Data
public class DetalleCompraRequest {
	
	private String idProducto;
	
	private Integer cantidad;
	
	private Double precio;
}
