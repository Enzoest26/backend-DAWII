package pe.com.backend.kenny.controller.publico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Bebida;
import pe.com.backend.kenny.service.BebidaService;

@RestController
@RequestMapping("/publico/bebida")
public class BebidaPublicoController {
	
	@Autowired
	private BebidaService bebidaService;
	
	@GetMapping("/activos")
	public Page<Bebida> listarBebidasActivosYPaginado(Integer pagina)
	{
		return this.bebidaService.listadoBebidasEstadoActivoPaginado(pagina);
	}
	
	@GetMapping("/{id}")
	public Bebida buscarBebidaPorId(@PathVariable String id) {
		return this.bebidaService.buscarPorId(id);
	}
	
}
