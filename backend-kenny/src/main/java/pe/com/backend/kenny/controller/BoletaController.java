package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Boleta;
import pe.com.backend.kenny.service.IBoletaService;

@RestController
@RequestMapping("/intranet/boleta")
public class BoletaController {

	private @Autowired IBoletaService boletaService;

	@GetMapping("/listar")
	public List<Boleta> listarBoletas(){
		return boletaService.listarBoletas();
	}

}