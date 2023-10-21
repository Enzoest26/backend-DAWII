package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IComidaRepository;
import pe.com.backend.kenny.service.IComidaService;

@Service
public class ComidaServiceImpl implements IComidaService {
	
	@Autowired
	private IComidaRepository repoComida;

	@Override
	public List<Comida> listarComida() {
		return repoComida.findAll();
	}

	@Override
	public Comida obtenerComida(String idComida) {
		return repoComida.findById(idComida).orElse(null);
	}

	@Override
	public Comida insertarComida(Comida objComida) {
		return repoComida.save(objComida);
	}

	@Override
	public BaseResponse actualizarComida(Comida objComida) {
		Comida objComidaAct = repoComida.findById(objComida.getIdComida()).orElse(null);
		if (repoComida.existsById(objComida.getIdComida())) {
			objComidaAct.setDescComida(objComida.getDescComida());
			objComidaAct.setPrecioComida(objComida.getPrecioComida());
			objComidaAct.setStockComida(objComida.getStockComida());
			objComidaAct.setTipoComida(objComida.getTipoComida());
			objComidaAct.setEstadoComida(objComida.getEstadoComida());
			repoComida.save(objComidaAct);
			return BaseResponse.builder()
					.codRespuesta("1")
					.msjRespuesta("Se actualizó correctamente")
					.build();
		}
		return BaseResponse.builder()
				.codRespuesta("0")
				.msjRespuesta("Comida no existente")
				.build();
	}

	@Override
	public BaseResponse eliminarComida(String idComida) {
		Comida comida = repoComida.findById(idComida).orElse(null);
		if (comida == null) {
			return BaseResponse.builder()
					.codRespuesta("0")
					.msjRespuesta("Comida no existente")
					.build();
		}
		comida.setEstadoComida(0);
		repoComida.save(comida);
		return BaseResponse.builder()
				.codRespuesta("1")
				.msjRespuesta("Se eliminó correctamente")
				.build();
	}

}
