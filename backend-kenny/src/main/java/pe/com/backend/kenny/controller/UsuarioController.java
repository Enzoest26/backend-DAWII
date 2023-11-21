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
import org.springframework.web.bind.annotation.RestController;

import pe.com.backend.kenny.model.Usuario;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.service.IUsuarioService;

@RestController
@RequestMapping("/intranet/usuario")
public class UsuarioController {
	
	private @Autowired IUsuarioService usuarioService;
	
	@GetMapping("/buscarPorId/{id}")
	public Usuario buscarPorId(@PathVariable Integer id){
		return this.usuarioService.buscarPorId(id);
	}
	
	@GetMapping("/buscarPorEmail/{email}")
	public Usuario buscarPorEmail(@PathVariable String email){
		return this.usuarioService.buscarClientePorEmail(email).get(0);
	}
	
	@GetMapping("/buscarTodos")
	public List<Usuario> listarTodos(){
		return this.usuarioService.listarUsuarios();
	}
	
	@PostMapping("/registrar")
	public Usuario registrarUsuario(@RequestBody Usuario usuario){
		return this.usuarioService.registrarUsuario(usuario);
	}
	
	@PutMapping("/actualizar")
	public Usuario actualizarUsuario(@RequestBody Usuario usuario){
		return this.usuarioService.actualizarUsuario(usuario);
	}
	
	@DeleteMapping("/eliminar/{idUsuario}")
	public BaseResponse eliminarUsuario(@PathVariable("idUsuario") Integer idUsuario){
		return this.usuarioService.eliminarUsuario(idUsuario);
	}
}
