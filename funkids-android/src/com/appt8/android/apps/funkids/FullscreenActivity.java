package com.appt8.android.apps.funkids;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
 
public class FullscreenActivity extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    TextView txtBottomInfo;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        setContentView(R.layout.activity_fullscreen);
        txtBottomInfo = (TextView) findViewById(R.id.bottom_info);
        String bottom_info = "Loading....";
        
		try {
			PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			bottom_info += "v"+pInfo.versionName;
			
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        txtBottomInfo.setText(bottom_info);
        
		//Rotate logo
		RotateAnimation anim = new RotateAnimation(0f,  350f, 15f, 15f);
		anim.setInterpolator(new LinearInterpolator());
		anim.setRepeatCount(Animation.INFINITE);
		anim.setDuration(700);
		
		final ImageView splash = (ImageView) findViewById(R.id.appt8_logo);
		splash.startAnimation(anim);
		//splash.setAnimation(null);
		
        new Handler().postDelayed(new Runnable() {
 
        	
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
            	Intent i = new Intent(FullscreenActivity.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}