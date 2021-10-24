package co.edu.unbosque.ciclo3demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_DetalleVentas {
	
	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	public static ArrayList<DetalleVenta> parsingDetalleVenta(String json) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		ArrayList<DetalleVenta> lista = new ArrayList<DetalleVenta>();
		JSONArray detalleVenta = (JSONArray) jsonParser.parse(json);
		Iterator i = detalleVenta.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			
			DetalleVenta detalleVentas = new DetalleVenta();
			
			detalleVentas.setCantidad_producto(Integer.parseInt(innerObj.get("cantidad_producto").toString()));
			detalleVentas.setCodigo_detalle_venta(Integer.parseInt(innerObj.get("codigo_detalle_venta").toString()));			
			detalleVentas.setCodigo_producto(Integer.parseInt(innerObj.get("codigo_producto").toString()));	
			detalleVentas.setCodigo_venta(Integer.parseInt(innerObj.get("codigo_venta").toString()));			
			detalleVentas.setValor_iva(Integer.parseInt(innerObj.get("valor_iva").toString()));
			detalleVentas.setValor_total(Integer.parseInt(innerObj.get("valor_total").toString()));
			detalleVentas.setValor_venta(Integer.parseInt(innerObj.get("valor_venta").toString()));
			
			//detalleVentas.setPrecio_producto(Integer.parseInt(innerObj.get("precio_producto").toString()));
			
			
			lista.add(detalleVentas);
		}
		return lista;
	}

	public static ArrayList<DetalleVenta> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"detalleventa/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<DetalleVenta> lista = new ArrayList<DetalleVenta>();
		lista = parsingDetalleVenta(json);
		http.disconnect();
		return lista;
	}
	
	
	

	public static int postJSON(DetalleVenta detalleVenta) throws IOException {
		url = new URL(sitio+"detalleventa/guardar");
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
		+ "\"codigo_detalle_venta\":\""+ String.valueOf(detalleVenta.getCodigo_detalle_venta())
		+"\",\"cantidad_producto\": \""+String.valueOf(detalleVenta.getCantidad_producto())
		+"\",\"codigo_producto\": \""+String.valueOf(detalleVenta.getCodigo_producto())
		+"\",\"codigo_venta\":\""+String.valueOf(detalleVenta.getCodigo_venta())
		+"\",\"valor_total\":\""+String.valueOf(detalleVenta.getValor_total())
		+"\",\"valor_venta\":\""+String.valueOf(detalleVenta.getValor_venta())
		+"\",\"valor_iva\":\""+String.valueOf(detalleVenta.getValor_iva())
		+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	


}
