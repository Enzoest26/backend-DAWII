package pe.com.backend.kenny.model.request;

import java.util.List;

import lombok.Data;

@Data
public class OrdenCompraRequest {
	
	private Integer idUsuario;
	
	private List<DetalleCompraRequest> detalleCompra;
}
