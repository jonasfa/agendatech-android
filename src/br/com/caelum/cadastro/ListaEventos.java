package br.com.caelum.cadastro;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.modelo.Evento;
import br.com.caelum.cadastro.parser.EventoParser;

public class ListaEventos extends ListActivity {
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);

		List<Evento> eventos = new EventoParser().parse();
		
		ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,
				android.R.layout.simple_list_item_1, eventos);

		setListAdapter(adapter);

		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				Toast.makeText(ListaEventos.this,
						"Posição segurada:" + position, Toast.LENGTH_LONG)
						.show();
				return true;
			}
		});

	}
	
	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		Toast.makeText(ListaEventos.this,
				"Posição selecionada:" + position, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem novoAluno = menu.add(0, 0, 0, "Cadastrar Evento");
		novoAluno.setIcon(R.drawable.icon);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {
			startActivity(new Intent(this, Formulario.class));
		}

		return false;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}
}