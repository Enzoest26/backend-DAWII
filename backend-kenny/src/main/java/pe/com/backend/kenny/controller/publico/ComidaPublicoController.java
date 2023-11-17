package pe.com.backend.kenny.controller.publico;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.backend.kenny.model.Comida;

import pe.com.backend.kenny.service.IComidaService;

@RestController
@RequestMapping("/publico/comida")
public class ComidaPublicoController {
	
	@Autowired
	private IComidaService servicioComida;
	
	@GetMapping("/activos")
	public Map<String, Object> listarComida(@RequestParam("pagina") Integer pagina){
		return servicioComida.listarComidasCatalogo(pagina);
	}
	
	@GetMapping("/{id}")
	public Comida obtenerComida(@PathVariable String id)
	{
		return servicioComida.obtenerComida(id);
	}
	

}
