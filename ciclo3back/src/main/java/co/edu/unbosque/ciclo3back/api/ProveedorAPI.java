package co.edu.unbosque.ciclo3back.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ciclo3back.dao.ProveedorDAO;
import co.edu.unbosque.ciclo3back.model.Proveedores;

@RestController 
@RequestMapping("proveedor")
public class ProveedorAPI {
	
	@Autowired 
	private ProveedorDAO proveedoresDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Proveedores proveedores) {
		proveedoresDAO.save(proveedores);
	}
	
	@GetMapping("/listar")
	public List<Proveedores> listar(){
		return proveedoresDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		proveedoresDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Proveedores proveedores) {
		proveedoresDAO.save(proveedores);
	}
}
