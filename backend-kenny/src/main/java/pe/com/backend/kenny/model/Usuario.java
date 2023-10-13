package pe.com.backend.kenny.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;
	
	@Column(name = "nombre_usuario")
	private String nombre;
	
	@Column(name = "ape_pater_usuario")
	private String apellidoPaterno;
	
	@Column(name = "ape_mater_usuario")
	private String apellidoMaterno;
	
	@Column(name = "email_usuario")
	private String correo;
	
	@Column(name = "clave_usuario")
	private String clave;
	
	@Column(name = "edad_usuario")
	private Integer edad;
	
	@Column(name = "estado_usuario")
	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_usuario")
	private TipoUsuario tipoUsuario;
	
	@Transient
	private Integer idTipoUsuario;
}
