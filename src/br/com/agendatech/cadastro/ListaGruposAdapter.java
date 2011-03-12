package br.com.agendatech.cadastro;

import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import br.com.agendatech.modelo.Grupo;

public class ListaGruposAdapter extends ArrayAdapter<Grupo>{

	public ListaGruposAdapter(Context context, List<Grupo> grupos) {
		super(context, R.layout.grupo_linha, R.id.nome_grupo, grupos);
	}
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Grupo grupo = getItem(position);

		View pai = super.getView(position, convertView, parent);

		final ImageView logo = (ImageView) pai.findViewById(R.id.logo);
		
		logo.setImageResource(R.drawable.ic_launcher_bw);

		AsyncTask<String, ProgressDialog, Bitmap> imageLoad = new BitmapGenerator(logo);
//		imageLoad.execute(grupo.getLogo());

//		TextView data = (TextView) pai.findViewById(R.id.data_cidade_evento);
//		data.setText(grupo.getData() + "-" +grupo.getEstado());

		return pai;
	}

	
}
