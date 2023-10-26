package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.TipoPostre;
import pe.com.backend.kenny.repository.ITipoPostreRepository;
import pe.com.backend.kenny.service.ITipoPostreService;

@Service
public class TipoPostreServiceImpl implements ITipoPostreService {

	@Autowired
	private ITipoPostreRepository repoTipoPostre;
	
	@Override
	public List<TipoPostre> tiposPostre() {
		return repoTipoPostre.findAll();
	}

}
