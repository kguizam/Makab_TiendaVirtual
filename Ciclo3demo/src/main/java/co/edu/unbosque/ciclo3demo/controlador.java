package co.edu.unbosque.ciclo3demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class controlador
 */
@WebServlet("/controlador")
public class controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//--------------- Variables --------------------
	
	long subTotal = 0, totalAPagar = 0,  numFac = 0, codProducto = 0, precio = 0, valorIva = 0, iva = 0, subTotalIva = 0, acuSubTotal = 0;
	int cantidad = 0, item = 0;
	String descripcion, cedulaCliente;
	Usuarios usuarios = new Usuarios();
	List<DetalleVenta> listaVentas = new ArrayList<>();
	DetalleVenta detalleVenta = new DetalleVenta();
	
	//-----------------------------------------------

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public controlador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//-------------------- Metodos -------------------------
	
	public void buscarCliente(String id, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		try {
			ArrayList<Cliente> listaCliente = JSON_Clientes.getJSON();
			for (Cliente clientes : listaCliente) {
				if (clientes.getCedula_cliente() == Long.parseLong(id)) {
					request.setAttribute("clienteSeleccionado", clientes);				
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();	
		}
	}
	
	public void buscarProducto(String id, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		try {
			ArrayList<Productos> listaProductos = JSON_Productos.getJSON();
			for (Productos producto:listaProductos) {
				if (producto.getCodigo_producto() == Long.parseLong(id)) {
					request.setAttribute("productoSeleccionado", producto);					
				}
			}
		} catch (Exception e) {			
			e.printStackTrace();	
		}
	}
	
	public void mostrarFactura(long nuFac, HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		if (nuFac == 0) {
			numFac = 1;
		} else {
			numFac = nuFac+1;
		}
		request.setAttribute("numeroFactura", numFac);
	}
	
	//------------------------------------------------------

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String menu = request.getParameter("menu");
		String accion = request.getParameter("accion");
		
		//--------------- recibir cedula usuario ----------------
		
		//String cedulaUsuarioActivo =  request.getParameter("UsuarioActivo");
		//usuarios.setCedula_usuario(Integer.parseInt(cedulaUsuarioActivo));
		//request.setAttribute("usuarioSeleccionado", usuarios);			
		
		//-------------------------------------------------------
		switch (menu) {
			case "Usuarios":
		    	doGetUsuarios(accion, request, response);
		    	break;
		    case "Clientes":
		    	doGetClientes(accion, request, response);
		    	break;	  
		    case "Proveedores":
			   	doGetProveedores(accion, request, response);
			   	break;
		    case "Productos":
			   	doGetProductos(accion, request, response);
			   	break;	
		    case "Ventas":
			   	doGetVentas(accion, request, response);
			   	break;
		    case "Reportes":
			   	doGetReportes(accion, request, response);
			    break;
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void doGetUsuarios(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
		}
		
		// ---- Trae de vuelta un ArrayList con los usuarios ---- //
		else if (accion.equals("Listar")) {
			try {
				ArrayList<Usuarios> listaUsuarios = JSON_Usuarios.getJSON();				        
				request.setAttribute("listaUsuarios", listaUsuarios);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			request.getRequestDispatcher("/resultadoUsuarios.jsp").forward(request, response);
		}
		
		// ---- Agrega un usuario a la base de datos ---- //
		else if (accion.equals("Agregar")) {
		    Usuarios usuario = new Usuarios();
		    
		    usuario.setCedula_usuario(Long.parseLong(request.getParameter("txtcedula")));
		    usuario.setNombre_usuario(request.getParameter("txtnombre"));
		    usuario.setEmail_usuario(request.getParameter("txtemail"));
		    usuario.setUsuario(request.getParameter("txtusuario"));
		    usuario.setPassword(request.getParameter("txtpassword"));
					
            int respuesta = 0;
            
            try {
            	respuesta = JSON_Usuarios.postJSON(usuario);
            	if (respuesta == 200) {
            		request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request, response);
            	}
            }catch(Exception e) {
            	e.printStackTrace();
            }
    	}
		
		// ---- Elimina al usuario con la cédula indicada ---- //
    	else if (accion.equals("Eliminar")) {
    		Long id = Long.parseLong(request.getParameter("txtcedula"));
    		int respuesta = 0;
    		try {
    			respuesta = JSON_Usuarios.deleteJSON(id);
    			
    			if (respuesta == 200) {
    				request.getRequestDispatcher("controlador?menu=Usuarios&accion=Listar").forward(request, response);
    			} else {
    				System.out.println("Error al eliminar usuario: " + respuesta);
    			}
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}
    	
    	// ---- Carga la información del cliente indicado ---- //
    	else if (accion.equals("Cargar")) {
    		Long id = Long.parseLong(request.getParameter("txtcedula"));
		  	try {
		  		ArrayList<Usuarios> lista = JSON_Usuarios.getJSON();
		  		System.out.println("Parametro: " + id);						
		  		for (Usuarios usuario : lista) {
		  			if (usuario.getCedula_usuario() == id) {
		  				request.setAttribute("usuarioSeleccionado", usuario);	
		  				break;
		  			}
			    }
			 } catch (Exception e) {
				 System.out.println("Error al cargar usuario: " + e);
			 }
		}
    	request.getRequestDispatcher("/Usuarios.jsp").forward(request, response);
	}
	
	private void doGetClientes(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		}
		
		// <----------------------> //
		if (accion.equals("Agregar")) {				     
			Cliente cliente = new Cliente();
				cliente.setCedula_cliente(Long.parseLong(request.getParameter("txtcedula")));
				cliente.setEmail_cliente(request.getParameter("txtemail"));
				cliente.setNombre_cliente(request.getParameter("txtnombre"));
				cliente.setDireccion_cliente(request.getParameter("txtdireccion"));
				cliente.setTelefono_cliente(request.getParameter("txttelefono"));
				
	            int respuesta = 0;
		    try {
		    	respuesta = JSON_Clientes.postJSON(cliente);
				if (respuesta == 200) {
					request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		// <----------------------> //
	   	if (accion.equals("Listar")) {
	   		try {
	   			ArrayList<Cliente> lista = JSON_Clientes.getJSON();
		    	request.setAttribute("lista", lista);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	   		request.getRequestDispatcher("/resultadoClientes.jsp").forward(request, response);
	   	}
	   	// <----------------------> //
	   	if (accion.equals("Eliminar")) {
	   		Long id = Long.parseLong(request.getParameter("txtcedula"));
			int respuesta = 0;
			try {
			   respuesta = JSON_Clientes.deleteJSON(id);
			   if (respuesta == 200) {
				   request.getRequestDispatcher("controlador?menu=Clientes&accion=Listar").forward(request, response);
			   } else {
				   System.out.println("Error al eliminar cliente: " + respuesta);
			   }
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	   	// <----------------------> //
		if (accion.equals("Cargar")) {
			Long id = Long.parseLong(request.getParameter("txtcedula"));
			try {
				ArrayList<Cliente> lista = JSON_Clientes.getJSON();
				System.out.println("Parametro: " + id);						
				for (Cliente cliente : lista){
					if (cliente.getCedula_cliente() == id) {
					   request.setAttribute("clienteSeleccionado", cliente);
					   break;
					}
				}
			} catch (Exception e) {
				 System.out.println("Error al cargar cliente: " + e);
			}
			request.getRequestDispatcher("/Clientes.jsp").forward(request, response);
		}
	}
	
	private void doGetProveedores(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
		}
		
		// <----------------------> //
	   	if (accion.equals("Listar")) {
	   		try {
   				ArrayList<Proveedores> lista = JSON_Proveedores.getJSON();
   				request.setAttribute("listaProveedores", lista);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   		request.getRequestDispatcher("/resultadoProveedores.jsp").forward(request, response);
	   	}
	   	 
	   	// <----------------------> //
	   	if (accion.equals("Agregar")) {				        
	   		Proveedores proveedor = new Proveedores();
	   		proveedor.setNitproveedor(Long.parseLong(request.getParameter("txtnit")));
	   		proveedor.setNombre_proveedor(request.getParameter("txtnombre"));
	   		proveedor.setCiudad_proveedor(request.getParameter("txtciudad"));		    		
	   		proveedor.setDireccion_proveedor(request.getParameter("txtdireccion"));
	   		proveedor.setTelefono_proveedor(request.getParameter("txttelefono"));
	   		
	   		int respuesta = 0;
   			try {
   				respuesta = JSON_Proveedores.postJSON(proveedor);					   
				if (respuesta == 200) {
					request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request, response);
				}
   			} catch(Exception e) {
   				e.printStackTrace();
			}
	   	}
	   	 
	   	// <----------------------> //
	   	if (accion.equals("Eliminar")) {
	   		Long id = Long.parseLong(request.getParameter("txtnit"));
			int respuesta = 0;
			try {
				respuesta = JSON_Proveedores.deleteJSON(id);
				if (respuesta==200) {
					request.getRequestDispatcher("controlador?menu=Proveedores&accion=Listar").forward(request, response);
				} else {
					System.out.println("Error al eliminar proveedor: " + respuesta);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
	   	}
	   	 
	   	// <----------------------> //
	   	if (accion.equals("Cargar")) {
	   		Long id = Long.parseLong(request.getParameter("txtnit"));
	   		try {							
	   			ArrayList<Proveedores> lista = JSON_Proveedores.getJSON();
	   			System.out.println("Parametro: " + id);						
	   			for (Proveedores proveedor : lista){
	   				if (proveedor.getNitproveedor() == id) {
	   					request.setAttribute("proveedorSeleccionado", proveedor);
	   					break;
	   				}
	   			}
	   		} catch (Exception e) {
	   			System.out.println("Error al cargar proveedor: " + e);
	   		}
	   		request.getRequestDispatcher("/Proveedores.jsp").forward(request, response);
	   	}
	}
	
	private void doGetProductos(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/productos.jsp").forward(request, response);
		}
		
		// <----------------------> //
	   	if (accion.equals("Listar")) {
	   		try {
	   			ArrayList<Productos> lista = JSON_Productos.getJSON();
				request.setAttribute("listaProductos", lista);
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    request.getRequestDispatcher("/resultadoProductos.jsp").forward(request, response);
		}
	   	
	   	// <----------------------> //
	   	if (accion.equals("Agregar")) {
	   		Productos producto = new Productos();
	   		producto.setCodigo_producto(Long.parseLong(request.getParameter("txtcodigo")));
	   		producto.setNombre_producto(request.getParameter("txtnombre"));
	   		producto.setPrecio_compra(Long.parseLong(request.getParameter("txtcompra")));		    		
	   		producto.setIvacompra(Long.parseLong(request.getParameter("txtiva"))  );
	   		producto.setPrecio_venta( Long.parseLong(request.getParameter("txtventa"))  );						
	   		producto.setNitproveedor( Long.parseLong(request.getParameter("txtproveedor")) );	
	   		
	   		int respuesta = 0;
	   		try {
	   			respuesta = JSON_Productos.postJSON(producto);					   
	   			if (respuesta == 200) {
	   				request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request, response);
				}
	   		}catch(Exception e) {
	   			e.printStackTrace();
	   		}
	   	}
	   	 
	   	// <----------------------> //
	   	if (accion.equals("Eliminar")) {
			Long id = Long.parseLong(request.getParameter("txtcodigo"));
			int respuesta = 0;
			try {
				respuesta = JSON_Productos.deleteJSON(id);
			   
				if (respuesta == 200) {
					request.getRequestDispatcher("controlador?menu=Productos&accion=Listar").forward(request, response);
				} else {
					System.out.println("Error al eliminar producto: " + respuesta);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
   		}
	}
	
	private void doGetVentas(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// <----------------------> //
		request.setAttribute("usuarioSeleccionado", usuarios);
    	request.setAttribute("numeroFactura", numFac);
		    	
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
		}
		
    	else if (accion.equals("Buscar")) {
    		String id = request.getParameter("cedulaCliente");
    		String cd = request.getParameter("codigoProducto");
    		if (!id.equals("")) this.buscarCliente(id, request, response);
    		if (!cd.equals("")) this.buscarProducto(cd, request, response);
    	}
    	
    	else if (accion.equals("AgregarProducto")) {
    		try {
    			String id = request.getParameter("cedulaCliente");
    		    this.buscarCliente(id, request, response);
    		    
    		    detalleVenta = new DetalleVenta();
    		    item++;
    		    totalAPagar = 0;
    		    subTotal = 0;
    		    valorIva = 0;
    		    cantidad = 0;
    		    acuSubTotal = 0;
    		    subTotalIva = 0;
    		    
    		    cantidad = Integer.parseInt(request.getParameter("cantidadProducto"));
    		    codProducto = Long.parseLong(request.getParameter("codigoProducto"));
    		    descripcion = request.getParameter("nombreProducto");
    		    precio = Long.parseLong(request.getParameter("valorProducto"));
    		    iva = Long.parseLong(request.getParameter("iva"));
    		    
    		    subTotal = (precio * cantidad);
    		    valorIva = (subTotal * iva) / 100;
    		    
    		    detalleVenta.setCodigo_detalle_venta(item);
    		    detalleVenta.setCodigo_producto(codProducto);
    		    detalleVenta.setDescripcion_producto(descripcion);
    		    detalleVenta.setPrecio_producto(precio);
    		    detalleVenta.setCantidad_producto(cantidad);
    		    detalleVenta.setCodigo_venta(numFac);
    		    detalleVenta.setValor_iva(valorIva);
    		    detalleVenta.setValor_venta(subTotal);
    		    
    		    listaVentas.add(detalleVenta);
    		    
    		    for (int i = 0; i<listaVentas.size(); i++) {
    		 	   acuSubTotal += listaVentas.get(i).getValor_venta();
    		 	   subTotalIva += listaVentas.get(i).getValor_iva();		    			 
    		    }
    		    
    		    totalAPagar = acuSubTotal + subTotalIva;
    		    detalleVenta.setValor_total(totalAPagar);
    		    
    		    request.setAttribute("listaventas", listaVentas);
    		    request.setAttribute("totalsubtotal", acuSubTotal);
    		    request.setAttribute("totaliva", subTotalIva);
    		    request.setAttribute("totalapagar", totalAPagar);
    		    
    		    int respuesta = 0;
    		    try {
    			    respuesta = JSON_DetalleVentas.postJSON(detalleVenta);
    			    
    			    PrintWriter write = response.getWriter();
    			    if (respuesta == 200) {
    			 	    write.println("Grabacion exitosa");		
    			 	    request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
    			    } else {
    			 	    write.println("Error al grabar" + respuesta);	
    			    }
    			    write.close();
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		} catch (NumberFormatException nfe) {
    			String id = request.getParameter("cedulaCliente");
        		String cd = request.getParameter("codigoProducto");
        		if (!id.equals("")) this.buscarCliente(id, request, response);
        		if (!cd.equals("")) this.buscarProducto(cd, request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}

    	else if (accion.equals("GenerarVenta")){
    		cedulaCliente = request.getParameter("cedulaCliente");
    		String numFact = request.getParameter("numeroFactura");
    		
    		Ventas ventas = new Ventas();
    		ventas.setCodigo_venta(Long.parseLong(numFact));
    		ventas.setCedula_cliente(Long.parseLong(cedulaCliente));
    		ventas.setCedula_usuario(usuarios.getCedula_usuario());
    		ventas.setIva_venta(subTotalIva);
    		ventas.setValor_venta(acuSubTotal);
    		ventas.setTotal_venta(totalAPagar);
    		
    		int respuesta = 0;
    		
    		try {
    			respuesta = JSON_Ventas.postJSON(ventas);
    			
    			PrintWriter write = response.getWriter();
    			if(respuesta == 200) {
    				write.println("Grabacion exitosa");		
    				item = 0;
		    		totalAPagar = 0;
		    		acuSubTotal = 0;
	    			subTotalIva = 0;
	    			listaVentas.clear();
	    			//long factura = numFac;
		    		//this.mostrarFactura(factura, request, response);
    			} else {
    				write.println("Error al grabar: " + respuesta);	
    			}
    			write.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}	    		 
    	 } else {
    	 }
    	 request.getRequestDispatcher("/Ventas.jsp").forward(request, response);
	} 
	
	private void doGetReportes(String accion, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (accion.equals("Inicio")) {
			request.getRequestDispatcher("/resultado.jsp").forward(request, response);
		}
		
		if (accion.equals("ListaUsuarios")) {
	   		try {
				ArrayList<Usuarios> listaUsuarios = JSON_Usuarios.getJSON();				        
				request.setAttribute("listaUsuarios", listaUsuarios);
			} catch (Exception e) {
				e.printStackTrace();
			}		    	 
		    request.getRequestDispatcher("/resultadoUsuarios.jsp").forward(request, response);	    		 
		}
   	 
	   	if (accion.equals("ListaClientes")) {
	   		try {
	   			ArrayList<Cliente> lista = JSON_Clientes.getJSON();
	   			request.setAttribute("lista", lista);
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
	   		request.getRequestDispatcher("/resultadoClientes.jsp").forward(request, response);	    		 
	   	 }
	   	 
	   	if (accion.equals("ListaProveedores")) {
	   		try {
	   			ArrayList<Proveedores> lista = JSON_Proveedores.getJSON();
	   			request.setAttribute("listaProveedores", lista);
	   		} catch (Exception e) {
	   			e.printStackTrace();
			}
	   		request.getRequestDispatcher("/resultadoProveedores.jsp").forward(request, response);
		}
	   	 
	   	if (accion.equals("ListaProductos")) {
	   		try {
	   			ArrayList<Productos> lista = JSON_Productos.getJSON();
	   			request.setAttribute("listaProductos", lista);
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
	   		request.getRequestDispatcher("/resultadoProductos.jsp").forward(request, response);
	   	}
	   	 
	   if (accion.equals("ListaDetalleVentas")) {
	   		try {
	   			ArrayList<DetalleVenta> listaDe = JSON_DetalleVentas.getJSON();
	   			request.setAttribute("listaDetalleVentas", listaDe);
	   		} catch (Exception e) {
	   			e.printStackTrace();
	   		}
	   		request.getRequestDispatcher("/resultadoDetalleVentas.jsp").forward(request, response);
	   }
	}
}