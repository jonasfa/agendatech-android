package br.com.agendatech.cadastro;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.agendatech.modelo.Evento;

public class ListaEventosAdapter extends ArrayAdapter<Evento> {

	private List<Evento> eventos;
	private Context context;
	private int resource;
	private List<View> views;
	
	public ListaEventosAdapter(Context context, int resource, List<Evento> eventos) {
		super(context, resource, eventos);
		this.eventos = eventos;
		this.context = context;
		this.resource = resource;
		// Pre-load list to get event image
		this.views = montaViews();
	}

	@Override
	public Evento getItem(int position) {
		return eventos.get(position);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return views.get(position);
	}
	
	private List<View> montaViews() {
		
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		List<View> views = new ArrayList<View>();
		for (int i = 0; i < eventos.size() ; i++) {
			
			View view = layoutInflater.inflate(resource, null);
			
			Evento evento = eventos.get(i);
			
			LinearLayout ll = (LinearLayout) view.findViewById(R.item_lista_evento.linha);

			if(i % 2 == 0) {
				ll.setBackgroundColor(context.getResources().getColor(R.color.backgroud_branco));
			} else{
				ll.setBackgroundColor(context.getResources().getColor(R.color.background_cinza));
			}
			
			TextView texto = (TextView) view.findViewById(R.item_lista_evento.texto);
			texto.setText(evento.getNome() + "\n" + evento.getData());
			
			ImageView imageEvento = (ImageView) view.findViewById(R.item_lista_evento.logo);
			try {
				Bitmap b = BitmapFactory.decodeStream(new URL(evento.getLogo()).openStream());
				imageEvento.setImageBitmap(b);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			
			views.add(view);
			
		}
		
		return views;
	}
	
}