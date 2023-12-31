package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.TipoUsuario;
import pe.com.backend.kenny.repository.ITipoUsuarioRepository;
import pe.com.backend.kenny.service.ITipoUsuarioService;

@Service
public class TipoUsuarioServiceImpl implements ITipoUsuarioService
{
	private @Autowired ITipoUsuarioRepository tipoUsuarioRepository;
	
	@Override
	public TipoUsuario buscarTipoUsuarioPorID(Integer id) 
	{
		return this.tipoUsuarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<TipoUsuario> buscarTodos() {
		// TODO Auto-generated method stub
		return this.tipoUsuarioRepository.findAll();
	}

}
