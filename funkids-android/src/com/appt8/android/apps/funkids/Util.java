package com.appt8.android.apps.funkids;

import android.graphics.Bitmap;
import android.graphics.Matrix;

public final class Util {
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
	
}
