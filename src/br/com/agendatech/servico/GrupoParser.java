package br.com.agendatech.servico;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.agendatech.modelo.Grupo;

public class GrupoParser {

	private String gruposJSON;
	JSONArray j = null;
	public GrupoParser() {
		this.gruposJSON = new Sincronismo().buscaGrupos();
	}
	
	public ArrayList<Grupo> parse() throws JSONException {
		ArrayList<Grupo> grupos = new ArrayList<Grupo>();

		j = new JSONArray(gruposJSON);

		for (int i = 0; i < j.length(); i++) {

			Grupo grupo = new Grupo();

			try {

				JSONObject nomeJ = new JSONObject(j.getJSONObject(i).toString());
				
				grupo.setNome(new JSONObject(nomeJ.getString("grupo"))
						.getString("nome"));
				
				grupo.setAprovado(new JSONObject(nomeJ.getString("grupo"))
				.getString("aprovado"));
				
//				
//
//				String niceURL = new JSONObject(nomeJ.getString("evento"))
//						.getString("cached_slug");
//				Calendar c = Calendar.getInstance();
//				evento.setNiceURL("http://www.agendatech.com.br/eventos/tecnologia/"
//						+ c.get(Calendar.YEAR) + "/" + niceURL);
//
//				evento.setLogo("http://s3.amazonaws.com/agendatech_logos/thumb/"
//						+ URLEncoder.encode(new JSONObject(nomeJ
//								.getString("evento"))
//								.getString("logo_file_name")));

				//refactor please...
				if ("true".equals(grupo.getAprovado())) {
					grupos.add(grupo);
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
		return grupos;
	}

}
