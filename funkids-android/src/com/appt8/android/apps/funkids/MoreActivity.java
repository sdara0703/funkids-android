package com.appt8.android.apps.funkids;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MoreActivity extends ActionBarActivity {
	private RelativeLayout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);

		layout = (RelativeLayout) findViewById(R.id.container);
		layout.setBackgroundColor(Color.BLACK);
		
		//Rotate logo
/*		RotateAnimation anim = new RotateAnimation(0f,  350f, 15f, 15f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(Animation.INFINITE);
		anim.setDuration(700);
		
		final ImageView splash = (ImageView) findViewById(R.id.more_logo);
		splash.startAnimation(anim);*/
		//splash.setAnimation(null);
		
		
		
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if (item.getTitle().toString().equals(getString(R.string.home_tab))) {	
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);

		} else if (item.getTitle().toString().equals(getString(R.string.alphabets_tab))) {
			//Toast.makeText(this,"Show Alphabets",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, AlphabetsActivity.class);
			startActivity(intent);
			//this.recreate();
		} else if (item.getTitle().toString().equals(getString(R.string.numbers_tab))) {
			//Toast.makeText(this,"Show numbers",Toast.LENGTH_LONG).show();	
			Intent intent = new Intent(this, NumbersActivity.class);
			startActivity(intent);
			
		} else if (item.getTitle().toString().equals(getString(R.string.rhymes_tab))) {
			//Toast.makeText(this,"Rhymes...",Toast.LENGTH_LONG).show();	
			Intent intent = new Intent(this, DisplayRhymesActivity.class);
			startActivity(intent);
			
		} else if (item.getTitle().toString().equals(getString(R.string.more_tab))) {
			//Toast.makeText(this,"More...",Toast.LENGTH_LONG).show();
			
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
			View rootView = inflater.inflate(R.layout.fragment_more, container,
					false);
			return rootView;
		}
	}

}
