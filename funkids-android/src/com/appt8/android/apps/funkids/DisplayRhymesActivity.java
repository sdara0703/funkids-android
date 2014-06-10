package com.appt8.android.apps.funkids;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

public class DisplayRhymesActivity extends Activity implements
		OnItemSelectedListener {

	private TextView startTimeField, endTimeField, rhyme_title, rhyme_content;
	private MediaPlayer mediaPlayer;
	private double startTime = 0;
	private double finalTime = 0;
	private Handler myHandler;
	private Hashtable playlist;
		
	/*
	 * private int forwardTime = 5000; private int backwardTime = 5000;
	 */
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
		// getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
		setContentView(R.layout.fragment_display_rhymes);

		// Populate Spinner Rhymes array
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
		
		//Prepare play list with title, content, audio etc...
		playlistCreate(0);
	}


	public void playNext() {
		
		if (playCountCurrent < playCountSize) {
			Playlist selectedItem = (Playlist) playlist.get(playCountCurrent++);
			selectedItem.init();
			play(mainview);	
		}
	}

	public void play(View view) {

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
				if (isPlayALlRequested && playCountCurrent < playCountSize) {
					playNext();
				} else {
					playCountCurrent = 0;
					isPlayALlRequested = false;
				}
			}
		});
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
		// seekbar = (SeekBar)findViewById(R.id.seekBar1);
		startTime = 0;
		finalTime = 0;
		//oneTimeOnly = 0;
	}

	public void pause(View view) {

		mediaPlayer.pause();
		pauseButton.setEnabled(false);
		playButton.setEnabled(true); 
	}

	public void stop(View view) {
		
		mediaPlayer.stop();
		mediaPlayer.release();
		pauseButton.setEnabled(false);
		playButton.setEnabled(true);
	}

	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		
		Playlist selectedItem;
		reset();
		playlist = new Hashtable();
		switch (pos) {
		case 0:
			isPlayALlRequested = true;
			playCountCurrent = 0;
			mainview  = view;
			playlistCreate(0); //Create all the rhymes
			playNext();
			break;
		case 1:
			playlistCreate(1);			
			selectedItem = (Playlist) playlist.get(0);
			selectedItem.init();
			play(view);
			break;
		case 2:	
			playlistCreate(2);
			selectedItem = (Playlist) playlist.get(1);
			selectedItem.init();
			play(view);
			break;
		case 3:
			playlistCreate(3);			
			selectedItem = (Playlist) playlist.get(2);
			selectedItem.init();
			play(view);
			break;
		case 4:
			playlistCreate(4);			
			selectedItem = (Playlist) playlist.get(3);
			selectedItem.init();
			play(view);
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
		myHandler.removeCallbacks(UpdateSongTime);
		mediaPlayer.stop();
		mediaPlayer.release();
	}

	@Override
	protected void onDestroy() {
		Log.i("RhymeActivity", "OnDestroy()");
		super.onDestroy();
		myHandler.removeCallbacks(UpdateSongTime);
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
		playlist = new Hashtable();
		switch (index) {		
		case 1:
			playlist.put(0, new Playlist(R.string.rhyme_baba_title,R.string.rhyme_baba, Color.YELLOW, R.drawable.rhyme_baba, MediaPlayer.create(this, R.raw.rhyme_baba)));
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
			playlist.put(0, new Playlist(R.string.rhyme_baba_title,R.string.rhyme_baba, Color.YELLOW, R.drawable.rhyme_baba, MediaPlayer.create(this, R.raw.rhyme_baba)));
			playlist.put(1, new Playlist(R.string.rhyme_london_title, R.string.rhyme_london, Color.RED, 0, MediaPlayer.create(this, R.raw.rhyme_london)));
			playlist.put(2, new Playlist(R.string.rhyme_if_you_are_happy_title, R.string.rhyme_if_you_are_happy, Color.GREEN, 0, MediaPlayer.create(this, R.raw.rhyme_happy)));
			playlist.put(3, new Playlist(R.string.rhyme_twinkle_title, R.string.rhyme_twinkle, Color.BLUE, 0, MediaPlayer.create(this, R.raw.rhyme_twinkle)));
			break;
		}
		playCountSize = playlist.size();
	}
}