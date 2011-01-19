package br.com.agendatech.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import br.com.agendatech.modelo.Evento;
import br.com.agendatech.servico.Sincronismo;

public class Formulario extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.formulario);

		Button botao = (Button) findViewById(R.id.botao);
		botao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText nome = (EditText) findViewById(R.id.editNome);
				EditText descricao = (EditText) findViewById(R.id.editDescricao);
				EditText estado = (EditText) findViewById(R.id.editEstado);
				EditText site = (EditText) findViewById(R.id.editSite);
				DatePicker data = (DatePicker) findViewById(R.id.editData);

				Evento evento = new Evento();
				evento.setNome(nome.getEditableText().toString());
				evento.setDescricao(descricao.getEditableText().toString());
				evento.setSite(site.getEditableText().toString());
				evento.setData(data.getDayOfMonth()+"/"+data.getMonth()+"/"+data.getYear());
				evento.setEstado(estado.getEditableText().toString());

				new EnviarTask().execute(evento); // Envia o evento em outra thread
			}
		});
	}
	
	private class EnviarTask extends AsyncTask<Evento, Object, Object> {
		ProgressDialog progress;
		
		@Override
		protected void onPreExecute() {
			progress = ProgressDialog.show(Formulario.this, "Aguarde...",
					"Enviando dados para a web...", true);
		}

		@Override
		protected Object doInBackground(Evento... params) {
			new Sincronismo().enviarDado(params[0]);
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			progress.dismiss();
			Toast.makeText(Formulario.this,
					"Seu evento aparecer‡¡ na lista em breve!",
					Toast.LENGTH_LONG).show();
			finish();
		}
	}
}
