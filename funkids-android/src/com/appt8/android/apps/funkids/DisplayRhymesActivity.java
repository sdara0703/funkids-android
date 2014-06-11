package com.appt8.android.apps.funkids;

import java.util.Hashtable;
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

public class DisplayRhymesActivity extends Activity implements
		OnItemSelectedListener {

	private TextView startTimeField, endTimeField, rhyme_title, rhyme_content;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler;
	private Hashtable<Object, Object> playlist;

	private SeekBar seekbar;
	private ImageButton playButton, pauseButton;
	private static int playCountCurrent = 0;
	private static int playCountSize = 0;
	private static boolean isPlayALlRequested = true;
	private View mainview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.fragment_display_rhymes);
		
		Spinner spinner = (Spinner) findViewById(R.id.rhymes_spinner);
		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.rhymes_array,
				android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);

		spinner.setOnItemSelectedListener(this);
		// Set rhyme_content height according to display size of device
		// Set underline and bold for title
		rhyme_title = (TextView) findViewById(R.id.txtRhymeTitle);
		SpannableString spanString = new SpannableString(rhyme_title.getText());
		spanString.setSpan(new UnderlineSpan(), 0, spanString.length(), 0);
		spanString.setSpan(new StyleSpan(Typeface.BOLD), 0,
				spanString.length(), 0);
		spanString.setSpan(new StyleSpan(Typeface.ITALIC), 0,
				spanString.length(), 0);
		rhyme_title.setText(spanString);

		rhyme_content = (TextView) findViewById(R.id.txtRhymeContent);
		rhyme_content.setMovementMethod(new ScrollingMovementMethod());

		mediaPlayer = MediaPlayer.create(this, R.raw.rhyme_baba);
		playButton = (ImageButton) findViewById(R.id.imagePlay);
		pauseButton = (ImageButton) findViewById(R.id.imagePause);
		startTimeField = (TextView) findViewById(R.id.txtInitialTime);
		endTimeField = (TextView) findViewById(R.id.txtTotalTime);
		seekbar = (SeekBar) findViewById(R.id.seekBar1);
		seekbar.setClickable(false);
		pauseButton.setEnabled(false);
		myHandler = new Handler();
		
		//Total songs will be size of rhymes array length - 1;
		playCountSize = getResources().getStringArray(R.array.rhymes_array).length - 1;
		//Prepare play list with title, content, audio etc...
		playlistCreate(0);
	}

	public void play(View view) {

		try {
			mediaPlayer.start();
			finalTime = mediaPlayer.getDuration();
			startTime = mediaPlayer.getCurrentPosition();
			
			seekbar.setMax((int) finalTime);
			
			endTimeField.setText(String.format(
					"%d min, %d sec",
					TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
					TimeUnit.MILLISECONDS.toSeconds((long) finalTime)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes((long) finalTime))));
			startTimeField.setText(String.format(
					"%d min, %d sec",
					TimeUnit.MILLISECONDS.toMinutes((long) startTime),
					TimeUnit.MILLISECONDS.toSeconds((long) startTime)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
									.toMinutes((long) startTime))));
			seekbar.setProgress((int) startTime);
			myHandler.postDelayed(UpdateSongTime, 1000);
			pauseButton.setEnabled(true);
			playButton.setEnabled(false);
	
	    		
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp1) {
					reset();
					playCountCurrent++;
					if (isPlayALlRequested && playCountCurrent < playCountSize) {
						Playlist selectedItem = (Playlist) playlist.get(playCountCurrent);
						selectedItem.init();
						play(mainview);
					} else {
						playCountCurrent = 0;
						isPlayALlRequested = false;
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			this.recreate();
		}
	}

	private Runnable UpdateSongTime = new Runnable() {
		public void run() {
			// Do activity only if song is playing
			if (pauseButton.isEnabled()) {
				startTime = mediaPlayer.getCurrentPosition();
				startTimeField.setText(String.format(
						"%d min, %d sec",
						TimeUnit.MILLISECONDS.toMinutes((long) startTime),
						TimeUnit.MILLISECONDS.toSeconds((long) startTime)
								- TimeUnit.MINUTES
										.toSeconds(TimeUnit.MILLISECONDS
												.toMinutes((long) startTime))));
				seekbar.setProgress((int) startTime);
			}
			myHandler.postDelayed(this, 1000);
		}
	};


	public void reset() {
		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
		pauseButton.setEnabled(false);
		playButton.setEnabled(true);
		startTimeField.setText(R.string.initial_Time);
		endTimeField.setText(R.string.initial_Time);
		startTime = 0;
		finalTime = 0;
	}

	public void pause(View view) {
		try {
			mediaPlayer.pause();
			pauseButton.setEnabled(false);
			playButton.setEnabled(true); 			
		} catch (Exception e) {
			e.printStackTrace();
			this.recreate();
		}
	}

	public void stop(View view) {
		try {
			mediaPlayer.stop();
			mediaPlayer.release();
			pauseButton.setEnabled(false);
			playButton.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
			this.recreate();
		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {		
		Playlist selectedItem;
		reset();
		playlistCreate(pos);
		if (pos == 0) { //Play all songs
			isPlayALlRequested = true;
			playCountCurrent = 0;
			mainview  = view;
			selectedItem = (Playlist) playlist.get(pos);
		} else { //Play selected song
			selectedItem = (Playlist) playlist.get(pos-1);				
		}
		selectedItem.init();
		play(view);	
	}

	public void onNothingSelected(AdapterView<?> parent) {
		// Another interface callback
	}

	@Override
	protected void onStop() {
		super.onStop();		
		Log.i("RhymeActivity", "OnStop()");
		try {
			myHandler.removeCallbacks(UpdateSongTime);
			mediaPlayer.stop();
			mediaPlayer.release();
		} catch (Exception e) {
			e.printStackTrace();
			this.recreate();
		}
	}

	@Override
	protected void onDestroy() {	
		super.onDestroy();
		Log.i("RhymeActivity", "OnDestroy()");
		try {
			myHandler.removeCallbacks(UpdateSongTime);
		} catch (Exception e) {
			e.printStackTrace();
			this.recreate();
		}
	}
	
	class Playlist {
		int title;
		int content;
		int color;
		int backgroundsrc; 
		MediaPlayer player;

		Playlist(int title, int content, int color, int backgroundsrc, MediaPlayer player) {
			this.title = title;
			this.content = content;
			this.color = color;
			this.backgroundsrc = backgroundsrc;
			this.player = player;
		}
		public void init() {
			rhyme_title.setText(title);
			rhyme_content.setText(content);
			rhyme_content.setBackgroundColor(color);
			mediaPlayer = player;			
		}
	}
	
	public void playlistCreate(int index) {
		playlist = new Hashtable<>();		
		int size = getResources().getStringArray(R.array.rhymes_array).length - 1;
		if(index == 0){ //add all songs to list
			for(int i = 1; i < size; i++) {
				addToPlayList(i);
			}
		} else { //add selected song to list
			addToPlayList(index);
		}
	}
	
	public void addToPlayList(int index) {	
		//Total count should be equal to size of rhymes array - rhymes_array from strings section.
		switch (index) {		
		case 1:
			playlist.put(0, new Playlist(R.string.rhyme_baba_title,R.string.rhyme_baba, Color.YELLOW, 0, MediaPlayer.create(this, R.raw.rhyme_baba)));
			break;
		case 2:
			playlist.put(1, new Playlist(R.string.rhyme_london_title, R.string.rhyme_london, Color.RED, 0, MediaPlayer.create(this, R.raw.rhyme_london)));
			break;
		case 3:
			playlist.put(2, new Playlist(R.string.rhyme_if_you_are_happy_title, R.string.rhyme_if_you_are_happy, Color.GREEN, 0, MediaPlayer.create(this, R.raw.rhyme_happy)));
			break;
		case 4:
			playlist.put(3, new Playlist(R.string.rhyme_twinkle_title, R.string.rhyme_twinkle, Color.BLUE, 0, MediaPlayer.create(this, R.raw.rhyme_twinkle)));
			break;
		default:
			break;
		}
	}
}