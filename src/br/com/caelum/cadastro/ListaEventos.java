package br.com.caelum.cadastro;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.modelo.Evento;
import br.com.caelum.cadastro.parser.EventoParser;

public class ListaEventos extends Activity {
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista);

		List<Evento> eventos = new EventoParser().parse();
		
		ArrayAdapter<Evento> adapter = new ArrayAdapter<Evento>(this,
				android.R.layout.simple_list_item_1, eventos);

		ListView EventosAlunos = (ListView) findViewById(R.id.listaEventos);
		EventosAlunos.setAdapter(adapter);

		EventosAlunos.setClickable(true);
		EventosAlunos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Toast.makeText(ListaEventos.this,
						"Posição selecionada:" + posicao, Toast.LENGTH_LONG)
						.show();
			}
		});

		EventosAlunos.setLongClickable(true);
		registerForContextMenu(EventosAlunos);
		EventosAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int position, long id) {
				return false;
			}
		});

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