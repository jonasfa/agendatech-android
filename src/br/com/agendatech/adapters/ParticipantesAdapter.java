package br.com.agendatech.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import br.com.agendatech.cadastro.BitmapGenerator;
import br.com.agendatech.cadastro.R;
import br.com.agendatech.modelo.Evento;

public class ParticipantesAdapter extends ArrayAdapter<String>{
		
		private Context eventoActivity;
		private final Evento evento;
		private static String[] images = {"http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg",
				"http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg",
				"http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg",
				"http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg",
				"http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg",
				};

		public ParticipantesAdapter(Context eventoActivity, Evento evento) {
			super(eventoActivity, R.layout.participante_item, 0, images);
			this.eventoActivity = eventoActivity;
			this.evento = evento;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView==null) {
				convertView = (ImageView) ((LayoutInflater)eventoActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.participante_item, parent, false);
			}
			
			((ImageView) convertView).setImageResource(R.drawable.default_user);
			
//			AsyncTask<String, ProgressDialog, Bitmap> imageLoad = new BitmapGenerator((ImageView) convertView);
//			imageLoad.execute("http://s3.amazonaws.com/twitter_images/assuncaorafael.jpg");

			return convertView;
		}
		
}
