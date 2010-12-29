package br.com.agendatech.cadastro;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;
import br.com.caelum.cadastro.modelo.Evento;

public class Sincronismo {

//	private String novoEvento = "http://10.0.2.2:3000/mobile/novo_evento?";
//	private String buscaEventos = "http://10.0.2.2:3000/mobile/eventos";

	private String novoEvento = "http://www.agendatech.com.br/mobile/novo_evento?";
	private String buscaEventos = "http://www.agendatech.com.br/mobile/eventos";
	
	public String buscaEventos() {
				
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

	public String enviarDado(Evento evento) {

		HttpClient httpclient = new DefaultHttpClient();

		String encode = novoEvento
				+ "nome=" + URLEncoder.encode(evento.getNome()) + "&descricao="
						+ URLEncoder.encode(evento.getDescricao()) + "&estado="
						+ URLEncoder.encode(evento.getEstado()) + "&site=" + URLEncoder.encode(evento.getSite())
						+ "&data=" + URLEncoder.encode(evento.getData());

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
		} catch (ClientProtocolException e) {
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
}
