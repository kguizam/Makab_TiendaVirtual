package co.edu.unbosque.ciclo3demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.csvreader.CsvReader;
 
import co.edu.unbosque.ciclo3demo.Productos;
import co.edu.unbosque.ciclo3demo.Proveedores;
import co.edu.unbosque.ciclo3demo.JSON_Proveedores;
import co.edu.unbosque.ciclo3demo.JSON_Productos;

@WebServlet("/controladorProductos")
@MultipartConfig
public class controladorProductos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public controladorProductos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("Usuario");
		System.out.println(nombre);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stubs
		PrintWriter out = response.getWriter();
		System.out.println("Entro Sevlet");
		String nomb = request.getParameter("nombre");
		Part file = request.getPart("archivo");
		String process = request.getParameter("Procesar");
		InputStream inputStream = file.getInputStream();
		Charset charset = Charset.forName("UTF-8");
		
		if (process != null) {
			if (inputStream.available() == 0) {
				inputStream.close();
				System.out.println("1: No hay archivo");
				request.setAttribute("error", 1);	// Error: no se ha seleccionado el archivo
				request.getRequestDispatcher("/productos.jsp").forward(request, response);
			} else {
				if(JSON_Productos.validarExtensionCSV(nomb)) {
					try {
						CsvReader leerproducto = new CsvReader(inputStream, charset);
						leerproducto.readHeaders();
						
						// Guardar los datos de cada producto en una lista //
						List<Productos> productos = new ArrayList<Productos>(); 
						while (leerproducto.readRecord()) {
							String codigo = leerproducto.get(0);
							String nombre = leerproducto.get(1);
							String nit 	  = leerproducto.get(2);
							String compra = leerproducto.get(3);
							String iva 	  = leerproducto.get(4);
							String venta  = leerproducto.get(5);
							
							Productos nuevoProducto = new Productos();
							nuevoProducto.setCodigo_producto(Long.parseLong(codigo));
							nuevoProducto.setNombre_producto(nombre); 
							nuevoProducto.setNitproveedor(Long.parseLong(nit)); 
							nuevoProducto.setPrecio_compra(Double.parseDouble(compra)); 
							nuevoProducto.setIvacompra(Double.parseDouble(iva));
							nuevoProducto.setPrecio_venta(Double.parseDouble(venta));
							
							productos.add(nuevoProducto);
						}
						inputStream.close();
						
						// Valida los datos cargados en relación con los proveedores //
						String proveedoresNoValidos = "";
						ArrayList<Long> provNoValidos = new ArrayList<Long>();
						
						ArrayList<Productos> listaDefinitiva = new ArrayList<Productos>();
						try {
							// Obtiene array con Nit válidos
							ArrayList<Proveedores> proveedores = JSON_Proveedores.getJSON();
							ArrayList<Long> nitProveedores = new ArrayList<>();
							for (Proveedores proveedor : proveedores) {
								long nuevoNit = proveedor.getNitproveedor(); 
								if (!nitProveedores.contains(nuevoNit)) nitProveedores.add(nuevoNit);
							}

							// Itera sobre los productos, comprobando que tengan Nit válidos
							long nitProducto;
							for (Productos producto : productos) {
								nitProducto = producto.getNitproveedor();
								boolean numberInList = nitProveedores.contains(nitProducto);
								if (numberInList) {
									listaDefinitiva.add(producto);
								} else {
									if (!provNoValidos.contains(nitProducto))provNoValidos.add(nitProducto);
								}
							}
							try {
								proveedoresNoValidos = String.valueOf(provNoValidos.get(0));
								for (long provider : provNoValidos) proveedoresNoValidos += ", " + provider;
							} catch (Exception e) {
								System.out.println("It doesn't matter, after all ;) 1");
							}
						} catch (Exception e) {
							System.out.println("Catch");
							e.printStackTrace();
							out.println("Error. Por favor, inténtelo más tarde.");
						}
						
						// Valida los datos cargados en relación con los proveedores //
						String productosNoValidos = "";
						ArrayList<Long> prodNoValidos = new ArrayList<Long>();
						
						ArrayList<Productos> listaproductos = JSON_Productos.getJSON();
						long codigo;
						boolean codigoYaExiste;
						int errores = 0;
						int added = 0;
						int validacion = 0;
						
						for (Productos item : listaDefinitiva) {
							codigo = item.getCodigo_producto();
							codigoYaExiste = false;
							// Recorre los productos existentes buscando si existe otro producto con el mismo código
							for (Productos productoExistente : listaproductos) {
								if (codigo == productoExistente.getCodigo_producto()) {
									codigoYaExiste = true;
									break;
								}
							} 
							// Si no existe producto con el código, lo añade 
							if (codigoYaExiste) {
								if (!prodNoValidos.contains(codigo)) prodNoValidos.add(codigo);
							} else {
								validacion = JSON_Productos.postJSON(item);
								if (validacion == 200) {
									System.out.println("Producto " + codigo + " insertado correctamente;");
									added += 1;
								} else {
									errores += 1;
								}
							}
							try {
								productosNoValidos = String.valueOf(prodNoValidos.get(0));
								for (long product : prodNoValidos) productosNoValidos += ", " + product;
							} catch (Exception e) {
								System.out.println("It doesn't matter, after all ;) 2");
							}
						}
						
						request.setAttribute("anadidos", added); 
						request.setAttribute("enviados", productos.size());
						
						if (productosNoValidos != "" || proveedoresNoValidos != "") {
							System.out.println("Carga existosa, pero sólo " + added + " de " 
									+ productos.size() + " registros añadidos");
							request.setAttribute("productosNoValidos", productosNoValidos); 
							request.setAttribute("proveedoresNoValidos", proveedoresNoValidos);
							request.setAttribute("error", 6); // Carga exitosa con registros no válidos
							request.getRequestDispatcher("/productos.jsp").forward(request, response);
						} else {
							if (errores == 0) {
								System.out.println("Carga exitosa");
								request.setAttribute("error", 0); // Carga exitosa de csv
								request.getRequestDispatcher("/productos.jsp").forward(request, response);
							} else {
								System.out.println("Algunos registros no se cargaron");
								request.setAttribute("error", 4); // Algunos registros no se cargaron
								request.getRequestDispatcher("/productos.jsp").forward(request, response);
							}
						}
					} catch (ConnectException ce) {
						System.out.println("Error: no hay conexión con el servidor");
						request.setAttribute("error", 5); //	Error: conexión
						request.getRequestDispatcher("/productos.jsp").forward(request, response);
					} catch (NumberFormatException nfe) {
						System.out.println("Error: (Posiblemente) archivo NO separado por comas");
						nfe.printStackTrace();
						if (nfe.getMessage().toString().contains(",")) {
							request.setAttribute("error", 7); //	Error: archivo NO separado por comas
						} else {
							request.setAttribute("error", 2); //	Error: datos inválidos
						}
						request.getRequestDispatcher("/productos.jsp").forward(request, response);
					} catch (Exception e){
						System.out.println("Error: datos invalidos");
						e.printStackTrace();
						request.setAttribute("error", 2); //	Error: datos inválidos
						request.getRequestDispatcher("/productos.jsp").forward(request, response);
					} finally {
						inputStream.close();
					}
				} else {
					inputStream.close();
					System.out.println("Error: formato inválido");
					request.setAttribute("error", 3);// Error: formato inválido
					request.getRequestDispatcher("productos.jsp").forward(request, response);
				}
			}		
		}
	}
}