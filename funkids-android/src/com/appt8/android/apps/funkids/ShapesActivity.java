package com.appt8.android.apps.funkids;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ShapesActivity extends ActionBarActivity {

	public static String SHAPE = "SHAPE";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_shapes);
		
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(0xFF00FF00); // Changes this drawbale to use a single color instead of a gradient
        gd.setCornerRadius(5);
        gd.setStroke(1, 0xFF000000);
        Button btn = (Button) findViewById(R.id.buttonOval);
        btn.setBackground(gd);
        

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void showDialog(View view) {
		Intent intent = new Intent(this, ImageDialogActivity.class);
		intent.putExtra(SHAPE, view.getContentDescription());
		startActivity(intent);
		
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

		} else if (item.getTitle().toString()
				.equals(getString(R.string.alphabets_tab))) {
			// Toast.makeText(this,"Show Alphabets",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, AlphabetsActivity.class);
			startActivity(intent);
			// this.recreate();
		} else if (item.getTitle().toString()
				.equals(getString(R.string.numbers_tab))) {
			// Toast.makeText(this,"Show numbers",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, NumbersActivity.class);
			startActivity(intent);

		} else if (item.getTitle().toString()
				.equals(getString(R.string.rhymes_tab))) {
			// Toast.makeText(this,"Rhymes...",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, DisplayRhymesActivity.class);
			startActivity(intent);
		} else if (item.getTitle().toString()
				.equals(getString(R.string.shapes_tab))) {

		} else if (item.getTitle().toString()
				.equals(getString(R.string.more_tab))) {
			// Toast.makeText(this,"More...",Toast.LENGTH_LONG).show();
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
			View rootView = inflater.inflate(R.layout.fragment_shapes,
					container, false);
			return rootView;
		}
	}

}
