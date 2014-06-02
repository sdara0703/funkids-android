package com.appt8.android.apps.funkids;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class NumbersActivity extends ActionBarActivity {
	MediaPlayer mp = null;
	CharSequence message = "";
	Util util;
	TextView txtView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_numbers);
		txtView=(TextView) findViewById(R.id.scroller);
		txtView.setSelected(true);
		
		
		
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		
		if (item.getTitle().toString().equals(getString(R.string.home_tab))) {	
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);	
		} else if (item.getTitle().toString().equals(getString(R.string.alphabets_tab))) {
			Toast.makeText(this,"Show Alphabets",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, AlphabetsActivity.class);
			startActivity(intent);
		} else if (item.getTitle().toString().equals(getString(R.string.numbers_tab))) {
			//Toast.makeText(this,"Show numbers",Toast.LENGTH_LONG).show();
			//this.recreate();
			
		} else if (item.getTitle().toString().equals(getString(R.string.rhymes_tab))) {
			Toast.makeText(this,"Rhymes...",Toast.LENGTH_LONG).show();	
		} else if (item.getTitle().toString().equals(getString(R.string.more_tab))) {
			Toast.makeText(this,"More...",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, MoreActivity.class);
			startActivity(intent);
		}
		
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_numbers,
					container, false);
			return rootView;
		}
	}
		
	public void ImageClicked(View view) {
		
		util = new Util();
		util.playMedia(view.getContext(), R.raw.thumb_click);
/*		this.message=view.getContentDescription();
	   	Runnable runnable = new Runnable() {
	        public void run() {
	        		        	
	        	Message msg = handler.obtainMessage();
    			Bundle bundle = new Bundle();
    			SimpleDateFormat dateformat = 
                             new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US);
    			String dateString = dateformat.format(new Date());
    			bundle.putString("myKey", dateString);
                 msg.setData(bundle);
                 handler.sendMessage(msg);	
	        }
	   	};
	        
      Thread mythread = new Thread(runnable);
      mythread.start();*/
	}
}
