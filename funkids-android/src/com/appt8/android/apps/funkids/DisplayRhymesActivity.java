package com.appt8.android.apps.funkids;

import java.util.concurrent.TimeUnit;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;


public class DisplayRhymesActivity extends Activity implements OnItemSelectedListener {	
	    
   public TextView startTimeField,endTimeField,rhyme_title,rhyme_content;
   public MediaPlayer mediaPlayer;
   public double startTime = 0;
   public double finalTime = 0;
   private Handler myHandler = new Handler();
   public int forwardTime = 5000; 
   public int backwardTime = 5000;
   public SeekBar seekbar;
   public ImageButton playButton,pauseButton;
   public static int oneTimeOnly = 0;   
   
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      //Remove title bar
      this.requestWindowFeature(Window.FEATURE_NO_TITLE);
      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
      setContentView(R.layout.fragment_display_rhymes);

      
		//Populate Spinner Rhymes array
      	Spinner spinner = (Spinner) findViewById(R.id.rhymes_spinner);
      	// Create an ArrayAdapter using the string array and a default spinner layout
      	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	           R.array.rhymes_array, android.R.layout.simple_spinner_item);
      	// Specify the layout to use when the list of choices appears
      	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      	// Apply the adapter to the spinner
      	spinner.setAdapter(adapter);

      	spinner.setOnItemSelectedListener(this);
       //Set rhyme_content height according to display size of device
      	//Set underline and bold for title
       rhyme_title = (TextView)findViewById(R.id.txtRhymeTitle);	
       SpannableString spanString = new SpannableString(rhyme_title.getText());
       spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
       spanString.setSpan(new StyleSpan(Typeface.BOLD), 0, spanString.length(), 0);
       spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0, spanString.length(), 0);       
       rhyme_title.setText(spanString);
       
	   rhyme_content = (TextView)findViewById(R.id.txtRhymeContent);
	   rhyme_content.setMovementMethod(new ScrollingMovementMethod());
	      
	   //songName = (TextView)findViewById(R.id.txtRhymeTitle);
	   mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_baba);
	   playButton = (ImageButton)findViewById(R.id.imagePlay);
	   pauseButton = (ImageButton)findViewById(R.id.imagePause);
	   startTimeField =(TextView)findViewById(R.id.txtInitialTime);
  	   endTimeField =(TextView)findViewById(R.id.txtTotalTime);
  	   seekbar = (SeekBar)findViewById(R.id.seekBar1);
       seekbar.setClickable(false);
       pauseButton.setEnabled(false);
   }
   
   public void play(View view) {
	  /* Toast.makeText(getApplicationContext(), "Playing sound", 
	   Toast.LENGTH_SHORT).show();*/
	   mediaPlayer.start();
	   finalTime = mediaPlayer.getDuration();
	   startTime = mediaPlayer.getCurrentPosition();
	   if(oneTimeOnly == 0){
		   seekbar.setMax((int) finalTime);
		   oneTimeOnly = 1;
	   } 
	   endTimeField.setText(String.format("%d min, %d sec", 
	         TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
	         TimeUnit.MILLISECONDS.toSeconds((long) finalTime) - 
	         TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
	         toMinutes((long) finalTime)))
	      );
	      startTimeField.setText(String.format("%d min, %d sec", 
	         TimeUnit.MILLISECONDS.toMinutes((long) startTime),
	         TimeUnit.MILLISECONDS.toSeconds((long) startTime) - 
	         TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
	         toMinutes((long) startTime)))
	      );
	      seekbar.setProgress((int)startTime);
	      myHandler.postDelayed(UpdateSongTime,1000);
		 
	      pauseButton.setEnabled(true);
	      playButton.setEnabled(false);
	      
	      mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
	          public void onCompletion(MediaPlayer mp1) {	        	  
	              //mp1.stop();
	        	  pauseButton.setEnabled(false);
	              playButton.setEnabled(true);
	              oneTimeOnly = 0;
	              startTime = 0;
	          }
	      });
		      
	}
   
   private Runnable UpdateSongTime = new Runnable()  {
	   	public void run() {		   
	   		//Do activity only if song is playing
	   		if (pauseButton.isEnabled()) {
		    	startTime = mediaPlayer.getCurrentPosition();
		        startTimeField.setText(String.format("%d min, %d sec", 
		        TimeUnit.MILLISECONDS.toMinutes((long) startTime),
		        TimeUnit.MILLISECONDS.toSeconds((long) startTime) - 
		        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
		        toMinutes((long) startTime)))
		        );
		        seekbar.setProgress((int)startTime);
	   		}
	        myHandler.postDelayed(this, 1000);
	   	}
   };
   
   public void reset(View view) {
	  if (mediaPlayer.isPlaying()) {
		  mediaPlayer.stop();
	  }
	  pauseButton.setEnabled(false);
      playButton.setEnabled(true);
      startTimeField.setText(R.string.initial_Time);
  	  endTimeField.setText(R.string.initial_Time);
  	  //seekbar = (SeekBar)findViewById(R.id.seekBar1);
      startTime = 0;
      oneTimeOnly = 0;
   }
   
   public void pause(View view){
     /* Toast.makeText(getApplicationContext(), "Pausing sound", 
      Toast.LENGTH_SHORT).show();*/

      mediaPlayer.pause();
      pauseButton.setEnabled(false);
      playButton.setEnabled(true);
   }
   
   public void stop(View view){
	     /* Toast.makeText(getApplicationContext(), "Pausing sound", 
	      Toast.LENGTH_SHORT).show();*/

	      mediaPlayer.stop();
	      //mediaPlayer.release();
	      pauseButton.setEnabled(false);
	      playButton.setEnabled(true);
	      
	      //threadPool.shutdown();
	   }
   
    public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
/*    	
    	Toast.makeText(getApplicationContext(), "Item selected " + pos, 
    		      Toast.LENGTH_SHORT).show();*/
    	reset(view);
    	switch (pos) {
    	case 0:
    		rhyme_title.setText(R.string.rhyme_baba_title);
    		rhyme_content.setText(R.string.rhyme_baba);
    		rhyme_content.setBackgroundColor(Color.YELLOW);
    		mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_baba);
    		break;
    	case 1:
    		rhyme_title.setText(R.string.rhyme_london_title);
    		rhyme_content.setText(R.string.rhyme_london);
    		rhyme_content.setBackgroundColor(Color.RED);
    		mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_london);
    		break;
    	case 2:
    		rhyme_title.setText(R.string.rhyme_if_you_are_happy_title);    		
    		rhyme_content.setText(R.string.rhyme_if_you_are_happy);
    		rhyme_content.setBackgroundColor(Color.GREEN);
    		mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_happy);
    		break;
    	case 3:
    		rhyme_title.setText(R.string.rhyme_twinkle_title);
    		rhyme_content.setText(R.string.rhyme_twinkle);
    		rhyme_content.setBackgroundColor(Color.BLUE);
    		mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_twinkle);
    		break;
    	}
    	
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    
    @Override
    protected void onStop() {
    	super.onStop();
    	Log.i("RhymeActivity", "OnStop()");
    	//threadPool.shutdown();
    	myHandler.removeCallbacks(UpdateSongTime);
    	mediaPlayer.stop();
    	mediaPlayer.release();
        
    }
/*    
    @Override
    protected void onDestroy() {
    	Log.i("RhymeActivity", "OnDestroy()");
    	super.onDestroy();
    }*/
}