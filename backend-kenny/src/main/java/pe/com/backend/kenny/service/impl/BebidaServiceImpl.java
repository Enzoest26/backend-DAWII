package pe.com.backend.kenny.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.com.backend.kenny.exception.ItemNoEncontradoException;
import pe.com.backend.kenny.model.Bebida;
import pe.com.backend.kenny.model.CategoriaBebida;
import pe.com.backend.kenny.model.TamanioBebida;
import pe.com.backend.kenny.model.TipoBebida;
import pe.com.backend.kenny.model.request.BebidaActualizarRequest;
import pe.com.backend.kenny.model.request.BebidaRegistrarRequest;
import pe.com.backend.kenny.model.response.BaseResponse;
import pe.com.backend.kenny.repository.BebidaRepository;
import pe.com.backend.kenny.repository.CategoriaBebidaRepository;
import pe.com.backend.kenny.repository.TamanioBebidaRepository;
import pe.com.backend.kenny.repository.TipoBebidaRepository;
import pe.com.backend.kenny.service.BebidaService;

@Service
public class BebidaServiceImpl implements BebidaService{

	@Autowired
	private BebidaRepository repoBebida;
	
	@Autowired
	private CategoriaBebidaRepository repoCategoria;
	
	@Autowired
	private TipoBebidaRepository repoTipo;
	
	@Autowired
	private TamanioBebidaRepository repoTamanio;
	
	
	@Override
	public List<Bebida> listadoTodasBebidas() {	
		return repoBebida.findAll();
	}

	@Override
	public List<Bebida> listadoBebidasEstadoActivo() {
		return repoBebida.findByEstadoBebida(1);
	}

	@Override
	public Bebida buscarPorId(String idBebida) {
		Bebida bebida = repoBebida.findById(idBebida).orElse(null);
		if(bebida == null) {
			throw new ItemNoEncontradoException("Bebida no encontrada");
		}
		return bebida;
	}
	
	@Override
	public Bebida registrarBebida(BebidaRegistrarRequest request) {
		Bebida bebidaNueva = new Bebida();
		
		CategoriaBebida categoriaBebida = repoCategoria.findById(request.getIdCategoriaBebida()).orElse(null);
		TipoBebida tipoBebida = repoTipo.findById(request.getIdTipoBebida()).orElse(null);
		TamanioBebida tamanioBebida = repoTamanio.findById(request.getIdTamanioBebida()).orElse(null);
		
		if(categoriaBebida == null || tipoBebida == null || tamanioBebida == null) {
			System.out.println(categoriaBebida + " - " + tipoBebida + " - " + tamanioBebida );
			return null;
		}
		
		bebidaNueva.setIdBebida(generarCodigoBebida());
		bebidaNueva.setDescripcionBebida(request.getDescripcionBebida());
		bebidaNueva.setPrecioBebida(request.getPrecioBebida());
		bebidaNueva.setStockBebida(request.getStockBebida());
		bebidaNueva.setEstadoBebida(1);
		bebidaNueva.setCategoriaBebida(categoriaBebida);
		bebidaNueva.setTipoBebida(tipoBebida);
		bebidaNueva.setTamanioBebida(tamanioBebida);
		
		return repoBebida.save(bebidaNueva);
	}
	
	private String generarCodigoBebida() {
		String codigo;
		int contador;

		String ultimoIdRegistrado = repoBebida.getUltimoIdBebida(); //B006
		
		if(ultimoIdRegistrado == null) {
			return codigo = "B001";
		}
		
		String parteNumerica = ultimoIdRegistrado.substring(1); //006
		
		contador = Integer.parseInt(parteNumerica) + 1; //7
			
		codigo = String.format("B%03d", contador);
		contador ++;
		return codigo;
	}

	@Override
	public Bebida actualizarBebida(String idBebida, BebidaActualizarRequest request) {
		Bebida bebida = repoBebida.findById(idBebida).orElse(null);
		CategoriaBebida categoriaBebida = repoCategoria.findById(request.getIdCategoriaBebida()).orElse(null);
		TipoBebida tipoBebida = repoTipo.findById(request.getIdTipoBebida()).orElse(null);
		TamanioBebida tamanioBebida = repoTamanio.findById(request.getIdTamanioBebida()).orElse(null);
		
		if(bebida == null || categoriaBebida == null || tipoBebida == null || tamanioBebida == null) {
			System.out.println(bebida + " - " + categoriaBebida + " - " + tipoBebida + " - " + tamanioBebida );
			System.out.println("Error al actualizar");
			throw new ItemNoEncontradoException("Bebida no encontrada");
		}
		
		bebida.setDescripcionBebida(request.getDescripcionBebida());
		bebida.setPrecioBebida(request.getPrecioBebida());
		bebida.setStockBebida(request.getStockBebida());
		bebida.setEstadoBebida(request.getEstadoBebida());
		bebida.setCategoriaBebida(categoriaBebida);
		bebida.setTipoBebida(tipoBebida);
		bebida.setTamanioBebida(tamanioBebida);
			
		return repoBebida.save(bebida);
	}

	@Override
	public BaseResponse eliminarBebida(String idBebida) {
		Bebida bebida = repoBebida.findById(idBebida).orElse(null);
		
		if(bebida == null) {
			return BaseResponse.builder()
					.codRespuesta("0")
					.msjRespuesta("La bebida no existe")
					.build();
		}
		
		//Eliminación lógica -> Estado = 0 (inactivo)
		bebida.setEstadoBebida(0);
		repoBebida.save(bebida);
		return BaseResponse.builder()
				.codRespuesta("1")
				.msjRespuesta("Se eliminó la bebida con id " + idBebida)
				.build();
	}

	@Override
	public Page<Bebida> listadoBebidasEstadoActivoPaginado(Integer pagina) {
		Pageable pageable = PageRequest.of(pagina - 1, 8);
		return this.repoBebida.findByEstadoBebida(1, pageable);
	}

}
