package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.TipoBebida;
import pe.com.backend.kenny.repository.TipoBebidaRepository;
import pe.com.backend.kenny.service.ITipoBebidaService;

@Service
public class TipoBebidaServiceImpl implements ITipoBebidaService {

	@Autowired
	private TipoBebidaRepository repoTipoBebida;
	
	@Override
	public List<TipoBebida> tiposBebida() {		
		return repoTipoBebida.findAll();
	}
	
	

}
