package br.com.agendatech.servico;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import br.com.agendatech.cadastro.DrawableManager;
import br.com.agendatech.modelo.Evento;

public class EventoParser {

	String eventosJSON;

	JSONArray j = null;

	public EventoParser() {
		this.eventosJSON = new Sincronismo().buscaEventos();
	}

	public List<Evento> parse() throws JSONException {
		ArrayList<Evento> eventos = new ArrayList<Evento>();

		j = new JSONArray(eventosJSON);

		for (int i = 0; i < j.length(); i++) {

			Evento evento = new Evento();

			try {

				JSONObject nomeJ = new JSONObject(j.getJSONObject(i).toString());

				evento.setNome(new JSONObject(nomeJ.getString("evento"))
						.getString("nome"));
				
				evento.setDescricao(new JSONObject(nomeJ.getString("evento"))
				.getString("descricao"));

				String data = new JSONObject(nomeJ.getString("evento"))
						.getString("data");
				evento.setData(data.substring(8, 10) + "/"
						+ data.substring(5, 7));
				
				String site = new JSONObject(nomeJ.getString("evento"))
						.getString("site");
				evento.setSite(site);

				String estado = new JSONObject(nomeJ.getString("evento"))
						.getString("estado");
				evento.setSite(estado);
				
				String niceURL = new JSONObject(nomeJ.getString("evento"))
				.getString("cached_slug");
				Calendar c = Calendar.getInstance() ;
				evento.setNiceURL("http://www.agendatech.com.br/eventos/tecnologia/" + c.get(Calendar.YEAR) + "/" + niceURL);
				
				evento.setLogo("http://s3.amazonaws.com/agendatech_logos/thumb/" + URLEncoder.encode(new JSONObject(nomeJ.getString("evento"))
				.getString("logo_file_name")));
	
//				InputStream is = null ;
//				try {
//					is = new URL(evento.getLogo()).openStream();
//				} catch (MalformedURLException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				Bitmap bm = BitmapFactory.decodeStream(is) ;
//				evento.setBitmap(bm) ;
				
				eventos.add(evento);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return eventos;
	}

}
