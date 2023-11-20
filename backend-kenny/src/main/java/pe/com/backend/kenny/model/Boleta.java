package pe.com.backend.kenny.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_boleta")
public class Boleta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "num_bol")
	private Integer numBoleta;
	
	@Column(name = "fecha_bol")
	private LocalDate fechaBoleta;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente idCliente;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario idUsuario;
	
	@Column(name = "total_bol")
	private Double totalMonto;
	/*
	@OneToMany(mappedBy = "boleta")
	private List<DetalleBoleta> detalle;*/
}
