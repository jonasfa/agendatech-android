package br.com.caelum.cadastro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Scanner;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.util.Log;

public class Sincronismo {

	private String novoEvento = "http://10.0.2.2:3000/mobile/novo_evento?nome=";
	private String buscaEventos = "http://10.0.2.2:3000/mobile/eventos";

	public String buscaEventos()  {
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(buscaEventos);
		HttpResponse response = null;
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		try {
			try {
				response = httpclient.execute(httpget);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				is = entity.getContent();
				Scanner s = new Scanner(is);
				while (s.hasNext()) {
					sb.append(s.next());
				}
			}
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return sb.toString();
		
		
	}

	public String enviarDado(String dado) throws ClientProtocolException,
			IOException, JSONException, URISyntaxException {

		HttpClient httpclient = new DefaultHttpClient();
		String encode = novoEvento + URLEncoder.encode(dado);
		Log.i("envio", encode);
		HttpGet httpget = new HttpGet(encode);
		HttpResponse response;
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				is = entity.getContent();
				Scanner s = new Scanner(is);
				while (s.hasNext()) {
					sb.append(s.next());
				}
			}
		} finally {
			if (is != null)
				is.close();
		}

		return sb.toString();
	}
}
