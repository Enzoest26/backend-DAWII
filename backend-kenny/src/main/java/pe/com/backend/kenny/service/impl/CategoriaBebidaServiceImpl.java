package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.CategoriaBebida;
import pe.com.backend.kenny.repository.CategoriaBebidaRepository;
import pe.com.backend.kenny.service.ICategoriaBebidaService;

@Service
public class CategoriaBebidaServiceImpl implements ICategoriaBebidaService {

	@Autowired
	private CategoriaBebidaRepository repoCategoriaBebida;
	
	@Override
	public List<CategoriaBebida> categoriasBebida() {
		return repoCategoriaBebida.findAll();
	}

}
