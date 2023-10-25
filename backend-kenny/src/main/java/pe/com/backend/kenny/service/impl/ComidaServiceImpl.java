package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.Sandwich;
import pe.com.backend.kenny.model.request.ComidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IComidaRepository;
import pe.com.backend.kenny.repository.ISandwichRepository;
import pe.com.backend.kenny.service.IComidaService;

@Service
public class ComidaServiceImpl implements IComidaService {
	
	@Autowired
	private IComidaRepository repoComida;
	@Autowired
	private ISandwichRepository repoSandwich;

	@Override
	public List<Comida> listarComida() {
		return repoComida.findAll();
	}

	@Override
	public Comida obtenerComida(String idComida) {
		return repoComida.findById(idComida).orElse(null);
	}

	@Override
	public Comida insertarComida(ComidaRegistrarRequest objComida) {
		Comida comidaNueva = new Comida();
		
		comidaNueva.setIdComida(generarCodigoComida());
		comidaNueva.setDescComida(objComida.getDescComida());
		comidaNueva.setPrecioComida(objComida.getPrecioComida());
		comidaNueva.setStockComida(objComida.getStockComida());
		comidaNueva.setTipoComida(objComida.getTipoComida());
		comidaNueva.setEstadoComida(1);
		
		Comida comidaGuardada = repoComida.save(comidaNueva);
		
		if (objComida.getTipoComida().equals("Sandwich")) {
			Sandwich nuevoSandwich = new Sandwich();
			String codigoComida = comidaNueva.getIdComida();
			nuevoSandwich.setIdComida(codigoComida);
			repoSandwich.save(nuevoSandwich);
		}
		return comidaGuardada;
	}

	private String generarCodigoComida() {
		String codigo;
		int contador;
		
		String ultimoIdRegistrado = repoComida.getUltimoIdComida();
		
		if(ultimoIdRegistrado == null) {
			return codigo = "C001";
		}
		
		String parteNumerica = ultimoIdRegistrado.substring(1);
		contador = Integer.parseInt(parteNumerica) + 1;
		
		codigo = String.format("C%03d", contador);
		contador++;
		return codigo;
		
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
