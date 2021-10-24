package co.edu.unbosque.ciclo3back.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ciclo3back.dao.DetalleVentaDAO;
import co.edu.unbosque.ciclo3back.model.DetalleVenta;



@RestController 
@RequestMapping("detalleventa")
public class DetalleVentaAPI {
	
	@Autowired 
	private DetalleVentaDAO detalleventaDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody DetalleVenta detalleVenta) {
		detalleventaDAO.save(detalleVenta);
	}
	
	@GetMapping("/listar")
	public List<DetalleVenta> listar(){
		return detalleventaDAO.findAll();
	}
	

}
