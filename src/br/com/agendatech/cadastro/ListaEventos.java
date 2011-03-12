package br.com.agendatech.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.agendatech.modelo.Evento;
import br.com.agendatech.servico.EventoParser;

public class ListaEventos extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.lista_eventos);
		
		((ListView)findViewById(android.R.id.list)).setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long id) {
				Intent intent = new Intent(ListaEventos.this, EventoActivity.class);
				intent.putExtra("Evento", ((Evento)getListAdapter().getItem((int) id)));
				startActivity(intent);
				
			}
			
			
		});
		
		new CarregarListaTask().execute();
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.eventos, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.menu_eventos.botao_novo:
			startActivity(new Intent(this, Formulario.class));
			break;
		default:
			break;
		}
		return true;
	}
	
	private class CarregarListaTask extends AsyncTask<Object, Object, List<Evento>> {
		ProgressDialog progress;
		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(	ListaEventos.this, 
											getString(R.string.aguarde),
											getString(R.string.carregando_eventos), 
											true , true);
		}

		@Override
		protected List<Evento> doInBackground(Object... params) {
			try {
				return new EventoParser().parse();
			} catch (JSONException e) {
				return new ArrayList<Evento>();
			}
		}

		@Override
		protected void onPostExecute(final List<Evento> eventos) {

			final ListaEventosAdapter listaEventosAdapter = new ListaEventosAdapter(ListaEventos.this, eventos);
			setListAdapter(listaEventosAdapter);
			
			progress.dismiss();

		}
	}

}