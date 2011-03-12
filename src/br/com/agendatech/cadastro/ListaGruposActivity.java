package br.com.agendatech.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import br.com.agendatech.modelo.Evento;
import br.com.agendatech.modelo.Grupo;
import br.com.agendatech.servico.EventoParser;
import br.com.agendatech.servico.GrupoParser;

public class ListaGruposActivity extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.lista_grupos);
		new CarregarListaTask().execute();		
		
	}
	
	
	private class CarregarListaTask extends AsyncTask<Object, Object, List<Grupo>> {
		ProgressDialog progress;
		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(	ListaGruposActivity.this, 
											getString(R.string.aguarde),
											getString(R.string.carregando_grupos), 
											true , true);
		}

		@Override
		protected ArrayList<Grupo> doInBackground(Object... params) {
			try {
				return new GrupoParser().parse();
			} catch (JSONException e) {
				return new ArrayList<Grupo>();
			}
		}

		@Override
		protected void onPostExecute(final List<Grupo> grupos) {

			final ListaGruposAdapter listaGruposAdapter = new ListaGruposAdapter(ListaGruposActivity.this, grupos);
			setListAdapter(listaGruposAdapter);
			
			progress.dismiss();

		}
	}


	
}
