package br.com.agendatech.cadastro;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.agendatech.modelo.Evento;

public class ListaEventosAdapter extends BaseExpandableListAdapter {

	private List<Evento> eventos;
	private Context context;
	private LayoutInflater inflater;
	
	public ListaEventosAdapter(Context context, List<Evento> eventos) {
		super() ;
		this.eventos = eventos;
		this.context = context;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		final Evento evento = eventos.get(groupPosition) ;
		
		View filho = inflater.inflate(R.layout.filho, null) ;
        TextView titulo = (TextView) filho.findViewById(R.id.titulo) ;
        titulo.setText(evento.getNome()) ;
        TextView cidade = (TextView) filho.findViewById(R.id.cidade) ;
        cidade.setText(evento.getEstado()) ;
        TextView descricao = (TextView) filho.findViewById(R.id.descricao) ;
        descricao.setText(evento.getDescricao()) ;
        final Button euvou = (Button) filho.findViewById(R.id.euvou) ;
        euvou.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(evento.getNiceURL()));
				context.startActivity(intent) ;
			}
		}) ;
        
		return filho;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return eventos.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		
		Evento evento = eventos.get(groupPosition) ;
		
		View pai = inflater.inflate(R.layout.pai, null) ;
//		LinearLayout fundo = (LinearLayout) pai.findViewById(R.id.fundo_pai) ;
		ImageView logo = (ImageView) pai.findViewById(R.id.logo) ;
		logo.setImageBitmap(evento.getBitmap()) ;
		TextView nome = (TextView) pai.findViewById(R.id.nome_evento) ;
		nome.setText(evento.getNome()) ;
		TextView data = (TextView) pai.findViewById(R.id.data_evento) ;
		data.setText(evento.getData()) ;
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