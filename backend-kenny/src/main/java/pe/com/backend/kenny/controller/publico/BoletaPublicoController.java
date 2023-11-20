package pe.com.backend.kenny.controller.publico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.request.OrdenCompraRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.service.IBoletaService;

/**
 * Controler con solo uso de generar la boleta para el cliente
 * */
@RestController
@RequestMapping("/publico/boleta")
public class BoletaPublicoController {

	private @Autowired IBoletaService boletaService;
	
	@PostMapping
	public BaseResponse generarBoleta(@RequestBody OrdenCompraRequest compraRequest)
	{
		return this.boletaService.registrarOrden(compraRequest);
	}
}
