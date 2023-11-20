package pe.com.backend.kenny.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_detalle_boleta")
public class DetalleBoleta {
	

	@Column(name = "num_bol")
	private Integer numBoleta;
	
	@ManyToOne
	@JoinColumn(name = "id_comida")
	private Comida comida;
	
	@ManyToOne
	@JoinColumn(name = "id_bebida")
	private Bebida bebida;
	
	@Column(name = "cantidad_comida")
	private Integer cantidadBebida;
	
	@Column(name = "cantidad_bebida")
	private Integer cantidadComida;
	
	@Column(name = "precio")
	private Double precio;
	
	@Column(name = "importe")
	private Double importe;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/*
	@MapsId("boleta")
    @ManyToOne
    @JsonIgnore
	private Boleta boleta;*/
}
