package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.exception.ItemNoEncontradoException;
import pe.com.backend.kenny.model.Comida;
import pe.com.backend.kenny.model.Postre;
import pe.com.backend.kenny.model.Sandwich;
import pe.com.backend.kenny.model.TipoPostre;
import pe.com.backend.kenny.model.request.ComidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.IComidaRepository;
import pe.com.backend.kenny.repository.IPostreRepository;
import pe.com.backend.kenny.repository.ISandwichRepository;
import pe.com.backend.kenny.repository.ITipoPostreRepository;
import pe.com.backend.kenny.service.IComidaService;

@Service
public class ComidaServiceImpl implements IComidaService {
	
	@Autowired
	private IComidaRepository repoComida;
	@Autowired
	private ISandwichRepository repoSandwich;
	@Autowired
	private IPostreRepository repoPostre;
	@Autowired
	private ITipoPostreRepository repoTipoPostre;

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
			nuevoSandwich.setIdComida(comidaGuardada.getIdComida());
			repoSandwich.save(nuevoSandwich);
		}
		
		if (objComida.getTipoComida().equals("Postre")) {
			TipoPostre tipoPostre = repoTipoPostre.findById(objComida.getIdTipoPostre()).orElse(null);
			Postre nuevoPostre = new Postre();
			nuevoPostre.setIdComida(comidaGuardada.getIdComida());
			nuevoPostre.setTipoPostre(tipoPostre);
			repoPostre.save(nuevoPostre);
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
	public Comida actualizarComida(Comida objComida) {
		Comida objComidaAct = repoComida.findById(objComida.getIdComida()).orElse(null);
		if (repoComida.existsById(objComida.getIdComida())) {
			objComidaAct.setDescComida(objComida.getDescComida());
			objComidaAct.setPrecioComida(objComida.getPrecioComida());
			objComidaAct.setStockComida(objComida.getStockComida());
			objComidaAct.setTipoComida(objComida.getTipoComida());
			objComidaAct.setEstadoComida(objComida.getEstadoComida());
			return repoComida.save(objComidaAct);
			/*return BaseResponse.builder()
					.codRespuesta("1")
					.msjRespuesta("Se actualizó correctamente")
					.build();*/
		}
		throw new ItemNoEncontradoException("Comida no encontrada");
		/*return BaseResponse.builder()
				.codRespuesta("0")
				.msjRespuesta("Comida no existente")
				.build();*/
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
