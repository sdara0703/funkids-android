package com.appt8.android.apps.funkids;

import com.appt8.android.apps.funkids.DisplayMessageActivity.DrawView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.FillType;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;

public class ImageDialogActivity extends Activity {
	DrawView drawView = null;
	@SuppressLint("DrawAllocation")
	class DrawView extends View {
        Paint paint = new Paint();
        String shape = "";
        public DrawView(Context context) {
            super(context);
            
        }
        @Override
        public void onDraw(Canvas canvas) {
             super.onDraw(canvas);
            
             Intent intent = getIntent();
             shape = intent.getStringExtra(ShapesActivity.SHAPE); 
             setTitle(shape);
             
             if (shape.equals("CIRCLE")) {
	             drawCircles(canvas, paint);
             } else  if (shape.equals("RECTANGLE")) {
                 paint.setColor(Color.BLUE);
                 canvas.drawRect(50, 50, canvas.getWidth()-50, canvas.getHeight()-50, paint);
                 paint.setColor(Color.YELLOW);
                 canvas.drawRect(100, 100, canvas.getWidth()-100, canvas.getHeight()-100, paint);
                 paint.setColor(Color.GRAY);
                 canvas.drawRect(150, 150, canvas.getWidth()-150, canvas.getHeight()-150, paint);
             } else if (shape.equals("OVAL")) {
            	 paint.setColor(Color.RED);
            	 /*RectF oval = new RectF();
            	 oval.set(150, 150, 120, 140);
            	 Paint p = new Paint();
            	 p.setAntiAlias(true);
     			p.setColor(Color.RED);
     			p.setStyle(Paint.Style.STROKE); 
     			p.setStrokeWidth(4.5f);*/
            	 //canvas.drawCircle(canvas.getWidth()/2, 600, 100, paint);    
            	 //canvas.drawOval(oval, paint);
             } else if (shape.equals("SQUARE")) {
            	 paint.setColor(Color.RED);
            	 canvas.drawRect(200,200, 400, 400, paint);
             } else if (shape.equals("TRIANGLE")) {
            	 /*paint.setColor(Color.CYAN);
            	 canvas.drawLine(100, 100, 200, 100, paint);
            	 paint.setColor(Color.GREEN);
            	 canvas.drawLine(100, 100, 150, 150, paint);
            	 paint.setColor(Color.RED);
            	 canvas.drawLine(200, 200, 150, 150, paint);*/
            	 
           

            	    paint.setColor(android.graphics.Color.BLACK);
            	    canvas.drawPaint(paint);

            	    paint.setStrokeWidth(4);
            	    paint.setColor(android.graphics.Color.RED);
            	    paint.setStyle(Paint.Style.FILL_AND_STROKE);
            	    paint.setAntiAlias(true);

            	    Point a = new Point(0, 0);
            	    Point b = new Point(0, 100);
            	    Point c = new Point(87, 50);

            	    Path path = new Path();
            	    path.setFillType(FillType.EVEN_ODD);
            	    path.lineTo(b.x, b.y);
            	    path.lineTo(c.x, c.y);
            	    path.lineTo(a.x, a.y);
            	    path.close();

            	    canvas.drawPath(path, paint);
            	 
             } else if (shape.equals("PENTAGON")) {
             } else if (shape.equals("OCTOGON")) {
             }
             paint.setTextSize(44);
             paint.setColor(Color.GREEN); 
             setTitle(shape);
                      
             canvas.drawText("This is a " + shape, 50, canvas.getHeight()-50, paint);
        }
	}
	
	public void drawCircles(Canvas canvas, Paint paint) {
		int maxWidth = canvas.getWidth();
		int maxHeight = canvas.getHeight();
		//Display size for the canvas - Width:884, Height:1467
		paint.setColor(Color.BLUE);
        canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 200, paint);        
        
        paint.setColor(Color.BLACK);
        canvas.drawCircle(canvas.getWidth()/4, canvas.getHeight()/4, 50, paint);

/*        for (int i=maxHeight/10,j=10; i<=maxHeight/2; i+=50,j+=10) {
        	paint.setColor(Color.YELLOW);
        	canvas.drawCircle(canvas.getWidth()/2, i, j, paint); 	
        }
        for (int i=10; i<=500; i+=50) {
        	paint.setColor(Color.RED);
        	canvas.drawCircle((canvas.getWidth()/2)+i, canvas.getHeight()/2, 25, paint);        	
        }  */      
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawView = new DrawView(this);
	    drawView.setBackgroundColor(Color.WHITE);
	    setContentView(drawView);

	    
	    //View contentView = (View)findViewById(R.id.container);
	    this.setFinishOnTouchOutside(true);
	   
	    // Make us non-modal, so that others can receive touch events.
	    getWindow().setFlags(LayoutParams.FLAG_NOT_TOUCH_MODAL, LayoutParams.FLAG_NOT_TOUCH_MODAL);

	    // ...but notify us that it happened.
	    getWindow().setFlags(LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);
	       
	}
	

	
	 @Override
	  public boolean onTouchEvent(MotionEvent event) {
	      finish();
	      return true;
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.image_dialog, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_image_dialog,
					container, false);
			return rootView;
		}
	}
	
	
	
}
