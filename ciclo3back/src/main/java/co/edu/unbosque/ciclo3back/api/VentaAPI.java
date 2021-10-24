package co.edu.unbosque.ciclo3back.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ciclo3back.dao.VentasDAO;
import co.edu.unbosque.ciclo3back.model.Ventas;


@RestController 
@RequestMapping("ventas")
public class VentaAPI {
	
	@Autowired 
	private VentasDAO ventasDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Ventas ventas) {
		ventasDAO.save(ventas);
	}
	
	

}
