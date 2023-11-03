package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Bebida;
import pe.com.backend.kenny.model.CategoriaBebida;
import pe.com.backend.kenny.model.TamanioBebida;
import pe.com.backend.kenny.model.TipoBebida;
import pe.com.backend.kenny.model.request.BebidaActualizarRequest;
import pe.com.backend.kenny.model.request.BebidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.service.BebidaService;
import pe.com.backend.kenny.service.ICategoriaBebidaService;
import pe.com.backend.kenny.service.ITamanioBebidaService;
import pe.com.backend.kenny.service.ITipoBebidaService;

@RestController
@RequestMapping("/bebida")
public class BebidaController {

	@Autowired
	private BebidaService bebidaService;
	@Autowired
	private ITipoBebidaService tipoBebidaService;
	@Autowired
	private ITamanioBebidaService tamanioBebidaService;
	@Autowired
	private ICategoriaBebidaService categoriaBebidaService;
	
	@GetMapping("/lista-general")
	public List<Bebida> listadoTodasBebidas() {
		return bebidaService.listadoTodasBebidas();
	}
	
	@GetMapping("/estado-activo")
	public List<Bebida> listadoBebidasEstadoActivo() {
		return bebidaService.listadoBebidasEstadoActivo();
	}
	
	@PostMapping("/registrar")
	public Bebida registrarBebida(@RequestBody BebidaRegistrarRequest bebida) {
		return bebidaService.registrarBebida(bebida);
	}
	
	@PutMapping("/actualizar")
	public Bebida actualizarBebida(@RequestParam String idBebida, @RequestBody BebidaActualizarRequest request) {
		return bebidaService.actualizarBebida(idBebida, request);
	}
	
	@DeleteMapping("/eliminar/{idBebida}")
	public BaseResponse eliminarBebida(@PathVariable String idBebida) {
		return bebidaService.eliminarBebida(idBebida);
	}
	
	@GetMapping("/tipos")
	public List<TipoBebida> listarTiposBebida() {
		return tipoBebidaService.tiposBebida();
	}
	
	@GetMapping("/tamaños")
	public List<TamanioBebida> listarTamaniosBebida() {
		return tamanioBebidaService.tamaniosBebida();
	}
	
	@GetMapping("/categorias")
	public List<CategoriaBebida> listarCategoriasBebida() {
		return categoriaBebidaService.categoriasBebida();
	}
}
