package com.appt8.android.apps.funkids;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity {
	public final static String EXTRA_MESSAGE = "com.apps.appt8.MESSAGE";
	DrawView drawView = null;
	Util util;// = new Util();
	//public String welcomeMsg = "Welcome to AppT8!!!";
	class DrawView extends View {
        Paint paint = new Paint();
        String picName = "";
        public DrawView(Context context) {
            super(context);           
        }
        
        @SuppressLint("NewApi")
		public void displayReport(Canvas canvas) {
        	Display display = getWindowManager().getDefaultDisplay();
        	Point point = new Point();
        	display.getSize(point);
        	//Log.d("MyAppt8...","X:"+ point.x + ",Y:" + point.y );
        	//paint.setColor(Color.GREEN);
        	
        	
        			
        	//canvas.drawText(getString(R.string.MyTitle), 50, 1200, paint);
        	//canvas.drawText("Canvas boundaries:" + canvas.getHeight() + "," + canvas.getWidth(), 50, 1250, paint);
        	//canvas.drawText("Display boundaries:" + point.x + "," + point.y, 50, 1300, paint);
        	//Output X:1080, Y:1776	
        }
        
        @Override
        public void onDraw(Canvas canvas) {
             super.onDraw(canvas);
             
            // ArrayList colors = new ArrayList(Color.RED, Color.GREEN, Color.BLUE,Color.GRAY);
             //Draw rectangle and put picture in it.
             paint.setColor(Color.BLUE);
             canvas.drawRect(50, 50, canvas.getWidth()-50, canvas.getHeight()-50, paint);
             paint.setColor(Color.YELLOW);
             canvas.drawRect(100, 100, canvas.getWidth()-100, canvas.getHeight()-100, paint);
             paint.setColor(Color.GRAY);
             canvas.drawRect(150, 150, canvas.getWidth()-150, canvas.getHeight()-150, paint);
             
             //canvas.drawRect(10, 10, 1000, 1200, paint);
             //Bitmap mypic = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
             //canvas.drawColor(Color.BLACK);
             //canvas.drawBitmap(mypic,20,20,null);
             
             //Draw square and put picture in it with red color
            // paint.setColor(Color.RED);
             //canvas.drawRect(300, 300, 490, 490, paint);
             //mypic = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
             //canvas.drawBitmap(mypic, 110, 110, null);
             
             //paint.setColor(Color.GREEN);
             
          	 paint.setTextSize(44);
          	 
             //Generate display report
             displayReport(canvas);
          	 
             //canvas.drawText("Welcome AppT8!!! + canvas boundaries:" + canvas.getHeight() + "," + canvas.getWidth(), 40, 1200, paint);
             
             
             	/*for (int i=10;i<=400;i+=10) {
             		canvas.drawLine(i,i+10,i+20,i+30,paint);
             		
             	}*/
             	/*canvas.drawLine(10, 20, 30, 40, paint);
                canvas.drawLine(20, 10, 50, 20, paint);
                
                canvas.drawText("Hurray...", 60, 80, paint);*/
                

        }
}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//drawView = new DrawView(this);
	    //drawView.setBackgroundColor(Color.WHITE);
	    
	    //Change the default content view
	    //setContentView(drawView);
		
		//Below is the default view
	    
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		setContentView(R.layout.activity_main);
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
		//Log.d("MainActivity", "ID:" + id);// (getString(id));
		
		TextView tv = (TextView) this.findViewById(R.id.maintextview);
		if (id == R.id.action_settings) {
			return true;
		}
		
		//ImageView iv = null;
		//Bitmap mypic = null;
		//Bitmap resizedPic = null;
		if (item.getTitle().equals("Home")) {
			this.recreate();
			//mypic = BitmapFactory.decodeResource(getResources(),R.drawable.kids);
			//resizedPic = util.getResizedBitmap(mypic,500,596);	        		
			//iv = (ImageView) this.findViewById(R.id.mainimage);
			//iv.setImageBitmap(mypic);			
		} else if (item.getTitle().equals("Thumb Images")) {
			//tv.setText("Thumb Images");
			Intent intent = new Intent(this, ThumbActivity.class);
			/*EditText editText = (EditText) findViewById(R.id.edit_message);
			String message = editText.getText().toString();
			intent.putExtra(EXTRA_MESSAGE, message);*/
			startActivity(intent);
			//mypic = BitmapFactory.decodeResource(getResources(),R.drawable.us);
			//resizedPic = util.getResizedBitmap(mypic,500,596);	        		
			//iv = (ImageView) this.findViewById(R.id.mainimage);
			//iv.setImageBitmap(mypic);	
		} else if (item.getTitle().equals("Scroll Images")) {
			tv.setText("You should upgrade to see this feature, it is only $100, wait for next version!!!");
			//mypic = BitmapFactory.decodeResource(getResources(),R.drawable.kids);
			//resizedPic = util.getResizedBitmap(mypic,500,596);	        		
			//iv = (ImageView) this.findViewById(R.id.mainimage);
			//iv.setImageBitmap(mypic);			
		} else if (item.getTitle().equals("Where Am I")) {
			Intent intent = new Intent(this, DisplayAddressActivity.class);
			startActivity(intent);
			//tv.setText("Under construction...");
			//mypic = BitmapFactory.decodeResource(getResources(),R.drawable.us);
			//resizedPic = util.getResizedBitmap(mypic,500,596);	        		
			//iv = (ImageView) this.findViewById(R.id.mainimage);
			//iv.setImageBitmap(mypic);
			
		}
		
		//tv.setText("You selected: " + item.getItemId() + item.getTitle());
		//ImageView iv = (ImageView) this.findViewById(R.id.mainimage);
		//Bitmap mypic = BitmapFactory.decodeResource(getResources(),R.drawable.bharathi);
        //Bitmap resizedPic = util.getResizedBitmap(mypic,500,596);
        //message = mypic.getWidth() + ":" + mypic.getHeight() + "," + resizedPic.getWidth() + ":" + resizedPic.getHeight();
		
		//iv.setImageBitmap(resizedPic);
		
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	/** Called when the user clicks the Send button */
	public void showSlideActvity(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}
}
