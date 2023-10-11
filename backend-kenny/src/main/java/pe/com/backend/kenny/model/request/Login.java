package pe.com.backend.kenny.model.request;

import lombok.Data;

@Data
public class Login {
    private String idUsuario;
    private String clave;
}
