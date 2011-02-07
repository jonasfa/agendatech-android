package br.com.agendatech.cadastro;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;
import br.com.agendatech.modelo.Evento;
import br.com.agendatech.servico.EventoParser;

public class ListaEventos extends ExpandableListActivity implements OnItemLongClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_eventos);

		getExpandableListView().setOnItemLongClickListener(this);
		
		getExpandableListView().setGroupIndicator(null) ;
        
        ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.logo) ;
		getExpandableListView().addHeaderView(imageView);
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		new CarregarListaTask().execute();
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
		private boolean retrive = false;

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
				retrive = true;
				return new EventoParser().parse();
			} catch (JSONException e) {
				retrive = false;
				return new ArrayList<Evento>();
			}
		}

		@Override
		protected void onPostExecute(List<Evento> eventos) {
			setListAdapter(new ListaEventosAdapter(ListaEventos.this, eventos));
			progress.dismiss();
			if(!retrive) {
				TextView tv = (TextView) findViewById(android.R.id.empty);
				tv.setText(getString(R.string.erro_buscar_evento));
			}
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		return false;
	}
	
}