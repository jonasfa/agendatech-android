package br.com.caelum.cadastro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONStringer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import br.com.caelum.cadastro.modelo.Aluno;
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
		MenuItem novoAluno = menu.add(0, 0, 0, "Novo Aluno");
		novoAluno.setIcon(R.drawable.icon);

		MenuItem sincronizar = menu.add("Sincronizar");
		sincronizar.setIcon(R.drawable.icon);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 0) {
			startActivity(new Intent(this, Formulario.class));
		}

		if (item.getTitle().equals("Sincronizar")) {

			final ProgressDialog progress = ProgressDialog.show(
					ListaEventos.this, "Aguarde...",
					"Enviando dados para a web!!!", true);

			final Toast aviso = Toast.makeText(ListaEventos.this,
					"Dados enviados com sucesso!!!", Toast.LENGTH_LONG);


//			new Thread(new Runnable() {
//				String retorno = "";
//
//				@Override
//				public void run() {
//					try {
//						Thread.sleep(2000);
//
//						AlunoDAO dao = new AlunoDAO(ListaEventos.this);
//						List<Aluno> lista = dao.getLista();
//
//						JSONStringer j = new JSONStringer();
//						j.object().key("alunos").array();
//						for (Aluno aluno : lista) {
//							j.value(aluno.toJSON());
//						}
//						j.endArray().endObject();
//
//						dao.close();
//
//						Sincronismo s = new Sincronismo();
//						retorno = s.enviarDado(j.toString());
//
//						Log.i("Retorno: ", retorno);
//
//					} catch (JSONException e) {
//						Log.e("Erro", "JSON", e);
//					} catch (ClientProtocolException e) {
//						Log.e("Erro", "Protocolo", e);
//					} catch (IOException e) {
//						Log.e("Erro", "Leitura", e);
//					} catch (InterruptedException e) {
//						Log.e("Erro", "Thread", e);
//					} catch (URISyntaxException e) {
//						e.printStackTrace();
//					}
//					aviso.show();
//					progress.dismiss();
//				}
//			}).start();
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