package pe.com.backend.kenny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.TipoUsuario;
import pe.com.backend.kenny.service.ITipoUsuarioService;

@RestController
@RequestMapping("/intranet/tipo-usuario")
public class TipoUsuarioController {
	
	private @Autowired ITipoUsuarioService tipoUsuarioService;
	
	
	@GetMapping("/buscarTodos")
	public List<TipoUsuario> listarTodos(){
		return this.tipoUsuarioService.buscarTodos();
	}
}
