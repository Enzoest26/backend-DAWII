package pe.com.backend.kenny.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cliente")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int id_cliente;

    @Column(name = "nombre_cliente")
    private String nombre_cliente;

    @Column(name = "apellidos_cliente")
    private String apellidos_cliente;

    @Column(name = "dni_cliente")
    private String dni_cliente;

    @Column(name = "fec_nac_cliente")
    private String fec_nac_cliente;

    @Column(name = "telefono_cliente")
    private String telefono_cliente;

    @Column(name = "edad_cliente")
    private int edad_cliente;

    @Column(name = "email_cliente")
    private String correo;

    @Column(name = "clave_cliente")
    private String clave;

    @Column(name = "estado_cliente")
    private int estado_cliente;
}
