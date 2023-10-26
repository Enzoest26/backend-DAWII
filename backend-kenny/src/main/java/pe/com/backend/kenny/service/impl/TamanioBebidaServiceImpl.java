package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.TamanioBebida;
import pe.com.backend.kenny.repository.TamanioBebidaRepository;
import pe.com.backend.kenny.service.ITamanioBebidaService;

@Service
public class TamanioBebidaServiceImpl implements ITamanioBebidaService {

	@Autowired
	private TamanioBebidaRepository repoTamanioBebida;
	
	@Override
	public List<TamanioBebida> tamaniosBebida() {		
		return repoTamanioBebida.findAll();
	}

}
