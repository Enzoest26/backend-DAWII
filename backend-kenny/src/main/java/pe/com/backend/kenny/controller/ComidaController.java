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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.service.IComidaService;

@RestController
@RequestMapping("/comida")
public class ComidaController {
	
	@Autowired
	private IComidaService servicioComida;
	
	@GetMapping("/listar")
	public List<Comida>listarComida(){
		return servicioComida.listarComida();
	}
	
	@GetMapping("/{idComida}")
	public Comida obtenerComida(@PathVariable String idComida) {
		return servicioComida.obtenerComida(idComida);
	}
	
	@PostMapping("/registrar")
	@ResponseBody
	public Comida insertarComida(@RequestBody Comida comida) {
		return servicioComida.insertarComida(comida);
	}
	
	@PutMapping("/actualizar")
	@ResponseBody
	public BaseResponse actualizarComida(@RequestBody Comida comida) {
		return servicioComida.actualizarComida(comida);
	}
	
	@DeleteMapping("/eliminar/{idComida}")
	public BaseResponse eliminarComida(@PathVariable String idComida) {
		return servicioComida.eliminarComida(idComida);
	}

}
