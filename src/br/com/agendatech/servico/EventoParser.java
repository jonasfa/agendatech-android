package br.com.agendatech.servico;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

				String data = new JSONObject(nomeJ.getString("evento"))
						.getString("data");
				evento.setData(data.substring(8, 10) + "/"
						+ data.substring(5, 7));
				eventos.add(evento);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return eventos;
	}

}
