package com.appt8.android.apps.funkids;

import javax.security.auth.callback.Callback;

import android.app.Activity;
import android.content.ClipData.Item;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public final class Util  {
	public static String welcomeMsg = "";
	
	/*public void setWelcomemsg(String msg) {
		this.welcomeMsg = msg;
	}
	public String getWelcomemsg() {
		return this.welcomeMsg;
	}*/
	
	public static final Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap resizedBitmap = Bitmap.createBitmap(bm,0,0,width,height,matrix,false);
		return resizedBitmap;
	}
	
	
	public static final void setMarquee(TextView txtView, CharSequence message) {
		txtView.setText((CharSequence) message);
		txtView.setSelected(true);
	}

}
