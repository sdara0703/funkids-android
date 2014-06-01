package com.appt8.android.apps.funkids;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;

public class Utilities extends Activity {

	private MediaPlayer mp = null;
	Point point;
	Matrix matrix;
	
	public void playMedia(Context context, int id) {
        mp = MediaPlayer.create(context, id);
        mp.start();
        mp.setOnCompletionListener(new OnCompletionListener() {
        public void onCompletion(MediaPlayer mp1) {
            mp1.stop();
            mp1.release();
        }
    });
	}
	
	@SuppressLint("NewApi")
	public Bitmap getWindowSizedBitmap(Bitmap bm, Display display) {
		try {
			
	        if (bm == null || bm.equals(null)) {
	        	Log.i("getWindowSizedBitmap", "Bitmap is NULL");
	        	return null;
	        	
	        } else {
		        Log.i("getWindowSizedBitmap", "Bitmap is not NULL");
	        }

	        if (display == null || display.equals(null)) {
	        	Log.i("getWindowSizedBitmap", "Display is NULL");
	        } else {
		        Log.i("getWindowSizedBitmap", "Display object:" + display.toString());
	        }
	        
	    	point = new Point();
	    	display.getSize(point);
	    	int newWidth = point.x;
	    	int newHeight = point.y;
	    	
			int width = bm.getWidth();
			int height = bm.getHeight();
			float scaleWidth = ((float) newWidth) / width;
			float scaleHeight = ((float) newHeight) / height;
			matrix = new Matrix();
			matrix.postScale(scaleWidth, scaleHeight);
			return Bitmap.createBitmap(bm,0,0,width,height,matrix,false);
			} catch (Exception ex) {
				Log.i("getWindowSizedBitmap", ex.getMessage());
				return null;
			}
	}
	
	
}
