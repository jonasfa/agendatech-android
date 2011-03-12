package br.com.agendatech.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.TextView;
import android.widget.Toast;
import br.com.agendatech.adapters.ParticipantesAdapter;
import br.com.agendatech.modelo.Evento;

public class EventoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.evento_visualizar);
		final Evento evento = (Evento) getIntent().getSerializableExtra("Evento");
		
		Gallery grid = (Gallery) findViewById(R.id.galeria) ;
		
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(EventoActivity.this, "Indo pro site", Toast.LENGTH_LONG).show() ;
			}
		}) ;
		
		grid.setAdapter(new ParticipantesAdapter(this, evento)) ;
		
		((TextView)findViewById(R.id.titulo)).setText(evento.getNome());;
		((TextView) findViewById(R.id.cidade)).setText(evento.getEstado());
		((WebView) findViewById(R.id.descricao)).loadData(evento.getDescricao(), "text/html", "utf-8");
		
		final Button euvou = (Button) findViewById(R.id.euvou);
		euvou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(evento
						.getNiceURL()));
				EventoActivity.this.startActivity(intent);
			}
		});

	}

}
