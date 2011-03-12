package br.com.agendatech.cadastro;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TabWidget extends TabActivity{
@Override
protected void onCreate(Bundle savedInstanceState) {
	 super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab);

	    Resources res = getResources(); 
	    TabHost tabHost = getTabHost();  
	    TabHost.TabSpec spec;  
	    Intent intent; 

	    // Eventos tab
	    intent = new Intent().setClass(this, ListaEventos.class);
	    spec = tabHost.newTabSpec("eventos").setIndicator("Eventos",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Grupos Tab
	    intent = new Intent().setClass(this, ListaGruposActivity.class);
	    spec = tabHost.newTabSpec("grupos").setIndicator("Grupos",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Profile Tab
	    intent = new Intent().setClass(this, ProfileActivity.class);
	    spec = tabHost.newTabSpec("profile").setIndicator("Profile",
	                      res.getDrawable(R.drawable.ic_tab_artists))
	                  .setContent(intent);
	    tabHost.addTab(spec);

}

}
