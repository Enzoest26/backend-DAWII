package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.TipoPostre;
import pe.com.backend.kenny.service.ITipoPostreService;

@RestController
@RequestMapping("/postre")
public class PostreController {
	
	@Autowired
	private ITipoPostreService tipoPostreService;
	
	@GetMapping("/tipos")
	public List<TipoPostre> listarTiposPostre() {
		return tipoPostreService.tiposPostre();
	}
}
