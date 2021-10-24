package co.edu.unbosque.ciclo3back.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.edu.unbosque.ciclo3back.dao.ClienteDAO;
import co.edu.unbosque.ciclo3back.model.Cliente;

@RestController 
@RequestMapping("clientes")
public class ClienteAPI {
	
	@Autowired 
	private ClienteDAO clienteDAO;
	
	@PostMapping("/guardar")
	public void guardar(@RequestBody Cliente cliente) {
		clienteDAO.save(cliente);
	}
	
	@GetMapping("/listar")
	public List<Cliente> listar(){
		return clienteDAO.findAll();
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		clienteDAO.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody Cliente cliente) {
		clienteDAO.save(cliente);
	}
}
