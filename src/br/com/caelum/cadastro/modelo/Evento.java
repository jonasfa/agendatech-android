package br.com.caelum.cadastro.modelo;

import org.json.JSONException;
import org.json.JSONStringer;

public class Evento {

	private String nome;
	private String estado;
	private String data;
	private String dataTermino;
	private String descricao;
	private String site;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(String dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}
	
	@Override
	public String toString() {
		return data + " - " + nome;
	}

	public String toJSON() throws JSONException {
		JSONStringer j = new JSONStringer();
		j.object().key("nome").value(nome).key("estdo").value(estado)
				.key("data").value(data).key("data_termino").value(dataTermino)
				.key("descricao").value(site).key("descricao").value(site)
				.endObject();
		return j.toString();
	}

}
