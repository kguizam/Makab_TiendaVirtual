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

public class JSON_Ventas {
	
	private static URL url;
	private static String sitio = "http://localhost:5000/";

	public static int postJSON(Ventas venta) throws IOException {
		url = new URL(sitio+"ventas/guardar");
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
		+ "\"codigo_venta\":\""+ String.valueOf(venta.getCodigo_venta())
		+"\",\"cedula_cliente\": \""+String.valueOf(venta.getCedula_cliente())
		+"\",\"cedula_usuario\": \""+String.valueOf(venta.getCedula_usuario())
		+"\",\"iva_venta\":\""+String.valueOf(venta.getIva_venta())
		+"\",\"total_venta\":\""+String.valueOf(venta.getTotal_venta())
		+"\",\"valor_venta\":\""+String.valueOf(venta.getValor_venta())
		+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}


}
