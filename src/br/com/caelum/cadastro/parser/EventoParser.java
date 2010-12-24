package br.com.caelum.cadastro.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.caelum.cadastro.Sincronismo;
import br.com.caelum.cadastro.modelo.Evento;

public class EventoParser {

	String eventosJSON;

	JSONArray j = null;

	public EventoParser() {
		this.eventosJSON = new Sincronismo().buscaEventos();
	}

	public List<Evento> parse() {
		ArrayList<Evento> eventos = new ArrayList<Evento>();

		try {
			j = new JSONArray(eventosJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < j.length(); i++) {

			Evento evento = new Evento();

			try {

				JSONObject nomeJ = new JSONObject(j.getJSONObject(i).toString());

				evento.setNome(new JSONObject(nomeJ.getString("evento"))
						.getString("nome"));

				eventos.add(evento);

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return eventos;
	}

}
