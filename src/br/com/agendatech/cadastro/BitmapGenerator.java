package br.com.agendatech.cadastro;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

public class BitmapGenerator extends AsyncTask<String, ProgressDialog, Bitmap>{
	
	private final ImageView logo;

	public BitmapGenerator(ImageView logo) {
		this.logo = logo;
		
	}

	private static HashMap<String, Bitmap> imagens = new HashMap<String, Bitmap>() ;

	@Override
	protected Bitmap doInBackground(String... params) {
		if ( imagens.containsKey(params[0]) ) {
			return imagens.get(params[0]) ;
		}
		InputStream is = null;
		try {
			
			is = new URL(params[0]).openStream();
			Bitmap decodeStream = BitmapFactory.decodeStream(is);
			if (decodeStream == null)
				throw new Exception("decodeStream returned null");
			imagens.put(params[0], decodeStream);
			return decodeStream;			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		Log.d("agendatech", String.valueOf(result));
		if (result != null)	logo.setImageBitmap(result);
	}
	
}