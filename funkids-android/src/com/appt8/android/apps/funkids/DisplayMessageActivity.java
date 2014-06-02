package com.appt8.android.apps.funkids;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class DisplayMessageActivity extends ActionBarActivity {

	DrawView drawView = null;
	String message = "";
	class DrawView extends View {
        Paint paint = new Paint();
        String picName = "";
        public DrawView(Context context) {
            super(context);
            
        }
        @Override
        public void onDraw(Canvas canvas) {
             super.onDraw(canvas);
                          
             //Bitmap mypic = BitmapFactory.decodeResource(getResources(),R.drawable.a);
             //Bitmap resizedPic = getResizedBitmap(mypic,1000,1296);
             //message = mypic.getWidth() + ":" + mypic.getHeight() + "," + resizedPic.getWidth() + ":" + resizedPic.getHeight();
             
             //Display boundaries        -   Width 1080 Height 1776
             //Original Image Dimensions -   Width 1936 Height 1296             	
             //Resized image dimensions  -   Width 1000 Height 1296
             
            /* canvas.drawBitmap(mypic,20,20,null);
             paint.setTextSize(44);
             paint.setColor(Color.GREEN);*/
             Intent intent = getIntent();
             message = intent.getStringExtra(MainActivity.MY_MESSAGE);          
             canvas.drawText("Welcome " + message + "...!!!", 50, 500, paint);
             
        }
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		    
		    //setContentView(textView);
		    //showTextContent();
		    showImageContent();
		}
	
	public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm,0,0,width,height,matrix,false);
		return resizedBitmap;
	}
	public void showImageContent() {
		drawView = new DrawView(this);
	    drawView.setBackgroundColor(Color.WHITE);
	    setContentView(drawView);	    
	}

	public void showTextContent() {
	    Intent intent = getIntent();
	    message = intent.getStringExtra(MainActivity.MY_MESSAGE);
		TextView textView = new TextView(this);
	    textView.setTextSize(40);
	    textView.setText(message);
	    setContentView(textView);
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
			Toast.makeText(this,"Show numbers",Toast.LENGTH_LONG).show();		
		} else if (item.getTitle().toString().equals(getString(R.string.rhymes_tab))) {
			Toast.makeText(this,"Rhymes...",Toast.LENGTH_LONG).show();	
		} else if (item.getTitle().toString().equals(getString(R.string.more_tab))) {
			Toast.makeText(this,"More...",Toast.LENGTH_LONG).show();
			this.recreate();
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
			View rootView = inflater.inflate(R.layout.fragment_display_message,
					container, false);
			return rootView;
		}
	}
}
