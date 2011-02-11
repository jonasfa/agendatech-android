package br.com.agendatech.cadastro;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.agendatech.modelo.Evento;

public class ListaEventosAdapter extends BaseExpandableListAdapter {

	private List<Evento> eventos;
	private Context context;
	private LayoutInflater inflater;
	
	private HashMap<String, Bitmap> imagens = new HashMap<String, Bitmap>() ;
	
	public ListaEventosAdapter(Context context, List<Evento> eventos) {
		super();
		this.eventos = eventos;
		this.context = context;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return getChild(groupPosition, childPosition);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Evento evento = eventos.get(groupPosition);

		View filho = inflater.inflate(R.layout.filho, null);
		TextView titulo = (TextView) filho.findViewById(R.id.titulo);
		titulo.setText(evento.getNome());
		TextView cidade = (TextView) filho.findViewById(R.id.cidade);
		cidade.setText(evento.getEstado());
		WebView descricao = (WebView) filho.findViewById(R.id.descricao);
		descricao.loadData(evento.getDescricao(), "text/html", "utf-8");
		final Button euvou = (Button) filho.findViewById(R.id.euvou);
		euvou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(evento
						.getNiceURL()));
				context.startActivity(intent);
			}
		});

		return filho;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return getGroup(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return eventos.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		final Evento evento = eventos.get(groupPosition);

		View pai = inflater.inflate(R.layout.pai, null);
		final ImageView logo = (ImageView) pai.findViewById(R.id.logo);

		AsyncTask<String, ProgressDialog, Bitmap> a = new AsyncTask<String, ProgressDialog, Bitmap>() {

			@Override
			protected Bitmap doInBackground(String... params) {
				Bitmap bm = null;
				if ( imagens.containsKey(params[0]) ) {
					bm = imagens.get(params[0]) ;
				} else {
					try {
						InputStream is = null;
						try {
							is = new URL(params[0]).openStream();
						} catch (MalformedURLException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						bm = BitmapFactory.decodeStream(is);
					} catch (Exception e) {
						e.printStackTrace();
					}
					imagens.put(params[0], bm) ;
				}
				return bm;
			}

			@Override
			protected void onPostExecute(Bitmap result) {
				super.onPostExecute(result);
				if (result != null)	logo.setImageBitmap(result);
			}

		};

		a.execute(evento.getLogo());

		// DrawableManager dm = new DrawableManager() ;
		// dm.fetchDrawableOnThread(evento.getLogo(), logo) ;

		TextView nome = (TextView) pai.findViewById(R.id.nome_evento);
		nome.setText(evento.getNome());
		TextView data = (TextView) pai.findViewById(R.id.data_evento);
		data.setText(evento.getData());
		return pai;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}