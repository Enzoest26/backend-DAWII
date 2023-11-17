package pe.com.backend.kenny.model.dto;


import lombok.Data;
import pe.com.backend.kenny.model.Postre;
import pe.com.backend.kenny.model.Sandwich;

@Data
public class DtoComidaCatalogo 
{
	private String idComida;
	
	private String descComida;
	
	private double precioComida;
	
	private int stockComida;
	
	private String tipoComida;
	
	private int estadoComida;
	
	private Postre postre;
	
	private Sandwich sandwich;
}
