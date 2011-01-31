package br.com.agendatech.cadastro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.agendatech.cadastro.R;
import br.com.agendatech.modelo.Evento;
import br.com.agendatech.servico.EventoParser;

public class ListaEventos extends ListActivity implements OnItemLongClickListener {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_eventos);

		getListView().setOnItemLongClickListener(this);
	}
	
	
	
	@Override
	protected void onStart() {
		super.onStart();
		findViewById(android.R.id.empty).setVisibility(View.GONE);
		new CarregarListaTask().execute();
	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		Toast.makeText(ListaEventos.this, getString(R.string.detalhe_evento_proxima_versao) , Toast.LENGTH_LONG).show();
		//startActivity(new Intent(this, Formulario.class));
	}
	
	@Override
	public boolean onItemLongClick(AdapterView<?> adapter, View view, int position, long id) {
		Toast.makeText(ListaEventos.this, getString(R.string.detalhe_evento_proxima_versao) , Toast.LENGTH_LONG).show();
		return true;
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
											true );
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
			LayoutInflater li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			final List<View> views = new ArrayList<View>();	
			for (int i = 0; i < eventos.size() ; i++) {
				View view = li.inflate(R.layout.item, null);
				LinearLayout ll = (LinearLayout)view.findViewById(R.id.linha);
				
				Evento evento = eventos.get(i);
				
				if(i%2 == 0){
					ll.setBackgroundColor(0xFFf9f9f9);
					
				}else{
					ll.setBackgroundColor(0xFFe6e6e6);
				}
					
				
				
				TextView texto = (TextView) view.findViewById(R.id.texto);
				texto.setText(evento.getNome() + "\n" + evento.getData());
				ImageView iv = (ImageView) view.findViewById(R.id.logo);
				try {
					Bitmap b = BitmapFactory.decodeStream(new URL(evento.getLogo()).openStream());
					iv.setImageBitmap(b);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				views.add(view);
 			}
			
			
			setListAdapter(new ArrayAdapter<Evento>(ListaEventos.this, android.R.layout.simple_list_item_1, eventos){
				@Override
				public View getView(int position, View convertView,
						ViewGroup parent) {
					return views.get(position);
				}
			});
			progress.dismiss();
			if(!retrive) {
				TextView tv = (TextView) findViewById(android.R.id.empty);
				tv.setText(getString(R.string.erro_buscar_evento));
			}
		}
	}
}