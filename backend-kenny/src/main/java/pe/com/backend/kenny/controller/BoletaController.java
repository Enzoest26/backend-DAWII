package pe.com.backend.kenny.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import pe.com.backend.kenny.model.Boleta;
import pe.com.backend.kenny.service.IBoletaService;

@RestController
@RequestMapping("/intranet/boleta")
public class BoletaController {

	private @Autowired IBoletaService boletaService;

	@GetMapping("/listar")
	public List<Boleta> listarBoletas(){
		return boletaService.listarBoletas();
	}
	
	@GetMapping("/buscarPorFiltros")
	public List<Boleta> listarPorFiltros(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, @RequestParam Integer idCliente){
		return this.boletaService.listarPorFiltros(fechaInicio, fechaFin, idCliente);
	}
	
	@GetMapping("/exportarExcel")
	public void exportarExcelPorCriterios(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, @RequestParam Integer idCliente, HttpServletResponse response) {
		this.exportarExcelPorCriterios(fechaInicio, fechaFin, idCliente, response);
	}

}