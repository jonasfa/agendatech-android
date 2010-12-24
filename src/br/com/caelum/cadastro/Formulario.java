package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import br.com.caelum.cadastro.modelo.Evento;

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

//				AlunoDAO dao = new AlunoDAO(Formulario.this);
//				dao.adicionar(aluno);
//				dao.close() ;

				startActivity(new Intent(Formulario.this, ListaEventos.class));
			}
		});
	}
}
