package pe.com.backend.kenny.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse {

    @JsonInclude(value = Include.NON_NULL)
    private String codRespuesta;

    @JsonInclude(value = Include.NON_NULL)
    private String msjRespuesta;
    
    @JsonInclude(value = Include.NON_NULL)
	public String descripcion;
}
