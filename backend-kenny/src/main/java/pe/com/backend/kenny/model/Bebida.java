package pe.com.backend.kenny.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_bebida")
public class Bebida {
	
	@Id
	@Column(name = "id_beb")
	private String idBebida;
	
	@Column(name = "desc_beb")
	private String descripcionBebida;
	
	@Column(name = "prec_beb")
	private double precioBebida;
	
	@Column(name = "stock_beb")
	private int stockBebida;
	
	@Column(name = "estado_beb")
	private int estadoBebida;
	
	@ManyToOne
	@JoinColumn(name = "id_categ_beb")
	private CategoriaBebida categoriaBebida;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_beb")
	private TipoBebida tipoBebida;
	
	@ManyToOne
	@JoinColumn(name = "id_tam_beb")
	private TamanioBebida tamanioBebida;
	
}
