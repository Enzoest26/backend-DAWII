package pe.com.backend.kenny.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_comida")
@Data
@NoArgsConstructor
public class Comida {
	@Id
	@Column(name = "id_comida")
	private String idComida;
	
	@Column(name = "desc_comida")
	private String descComida;
	
	@Column(name = "precio_comida")
	private double precioComida;
	
	@Column(name = "stock_comida")
	private int stockComida;
	
	@Column(name = "tipo_comida")
	private String tipoComida;
	
	@Column(name = "estado_comida")
	private int estadoComida;
	
	@Column(name = "imagen")
	@Lob
	private byte[] imagen;

}
