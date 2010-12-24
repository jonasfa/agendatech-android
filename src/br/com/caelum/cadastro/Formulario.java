package br.com.caelum.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import br.com.caelum.cadastro.modelo.Aluno;

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
				EditText telefone = (EditText) findViewById(R.id.editTelefone);
				EditText site = (EditText) findViewById(R.id.editSite);
				RatingBar nota = (RatingBar) findViewById(R.id.editNota);
				EditText endereco = (EditText) findViewById(R.id.editEndereco);

				Aluno aluno = new Aluno();
				aluno.setNome(nome.getEditableText().toString());
				aluno.setTelefone(telefone.getEditableText().toString());
				aluno.setSite(site.getEditableText().toString());
				aluno.setNota(nota.getRating());
				aluno.setEndereco(endereco.getEditableText().toString());

//				AlunoDAO dao = new AlunoDAO(Formulario.this);
//				dao.adicionar(aluno);
//				dao.close() ;

				startActivity(new Intent(Formulario.this, ListaEventos.class));
			}
		});
	}
}
