/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.appt8.android.apps.funkids;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;


/**
 * A sample showing how to zoom an image thumbnail to full-screen, by animating the bounds of the
 * zoomed image from the thumbnail bounds to the screen bounds.
 *
 * <p>In this sample, the user can touch one of two images. Touching an image zooms it in, covering
 * the entire activity content area. Touching the zoomed-in image hides it.</p>
 */
@SuppressLint("NewApi")
public class AlphabetsActivity extends ActionBarActivity {
    /**
     * Hold a reference to the current animator, so that it can be canceled mid-way.
     */
    private Animator mCurrentAnimator;

    /**
     * The system "short" animation time duration, in milliseconds. This duration is ideal for
     * subtle animations or animations that occur very frequently.
     */
    private int mShortAnimationDuration;
    private Util util = new Util();

    BitmapDrawable bd;
    Bitmap resizedBitmap;
    Display display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabets);
        
        display = getWindowManager().getDefaultDisplay();
        //SOUND
        //Effects.getInstance().init(this);
        
        

        // Hook up clicks on the thumbnail views.
       // tv = (TextView) this.findViewById(R.id.expanded_description);
        final View thumbAView = findViewById(R.id.thumb_a);
        thumbAView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbAView, R.drawable.a);
            	
            }
        });

        final View thumbBView = findViewById(R.id.thumb_b);
        thumbBView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbBView, R.drawable.b);
            }
        });
        
        final View thumbCView = findViewById(R.id.thumb_c);
        thumbCView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbCView, R.drawable.c);
            }
        });
        
        final View thumbDView = findViewById(R.id.thumb_d);
        thumbDView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbDView, R.drawable.d);
            }
        });
        
        final View thumbEView = findViewById(R.id.thumb_e);
        thumbEView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbEView, R.drawable.e);
            }
        });
        
        final View thumbFView = findViewById(R.id.thumb_f);
        thumbFView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbFView, R.drawable.f);
            }
        });
        
        final View thumbGView = findViewById(R.id.thumb_g);
        thumbGView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbGView, R.drawable.g);
            }
        });
        
        final View thumbHView = findViewById(R.id.thumb_h);
        thumbHView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbHView, R.drawable.h);
            }
        });
        
        final View thumbIView = findViewById(R.id.thumb_i);
        thumbIView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbIView, R.drawable.i);
            }
        });
        
        final View thumbJView = findViewById(R.id.thumb_j);
        thumbJView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImageFromThumb(thumbJView, R.drawable.j);
            }
        });
        // Retrieve and cache the system's default "short" animation time.
        mShortAnimationDuration = getResources().getInteger(android.R.integer.config_shortAnimTime);
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

		} else if (item.getTitle().toString().equals(getString(R.string.numbers_tab))) {
			//Toast.makeText(this,"Show numbers",Toast.LENGTH_LONG).show();	
			Intent intent = new Intent(this, NumbersActivity.class);
			startActivity(intent);
			
		} else if (item.getTitle().toString().equals(getString(R.string.rhymes_tab))) {
			//Toast.makeText(this,"Rhymes...",Toast.LENGTH_LONG).show();	
			Intent intent = new Intent(this, DisplayRhymesActivity.class);
			startActivity(intent);
		} else if (item.getTitle().toString().equals(getString(R.string.shapes_tab))) {
			Intent intent = new Intent(this, ShapesActivity.class);
			startActivity(intent);			
		} else if (item.getTitle().toString().equals(getString(R.string.more_tab))) {
			//Toast.makeText(this,"More...",Toast.LENGTH_LONG).show();
			Intent intent = new Intent(this, MoreActivity.class);
			startActivity(intent);
		}
        return super.onOptionsItemSelected(item);
    }

    /**
     * "Zooms" in a thumbnail view by assigning the high resolution image to a hidden "zoomed-in"
     * image view and animating its bounds to fit the entire activity content area. More
     * specifically:
     *
     * <ol>
     *   <li>Assign the high-res image to the hidden "zoomed-in" (expanded) image view.</li>
     *   <li>Calculate the starting and ending bounds for the expanded view.</li>
     *   <li>Animate each of four positioning/sizing properties (X, Y, SCALE_X, SCALE_Y)
     *       simultaneously, from the starting bounds to the ending bounds.</li>
     *   <li>Zoom back out by running the reverse animation on click.</li>
     * </ol>
     *
     * @param thumbView  The thumbnail view to zoom in.
     * @param imageResId The high-resolution version of the image represented by the thumbnail.
     */
    private void zoomImageFromThumb(final View thumbView, int imageResId) {
        // If there's an animation in progress, cancel it immediately and proceed with this one.
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        // Load the high-resolution "zoomed-in" image.
        final ImageView expandedImageView = (ImageView) findViewById(R.id.expanded_image);
        expandedImageView.setImageResource(imageResId);
        
        //Resize the image to window sized image
        expandedImageView.setDrawingCacheEnabled(true);
        try {
    	 	bd = (BitmapDrawable)expandedImageView.getDrawable();
    	 	if (bd == null || bd.equals(null)) {
    	 		Log.i("zoomImageFromThumb", "BitmapDrawable is NULL");
    	 	}
    	 	else {
			        resizedBitmap = util.getWindowSizedBitmap(bd.getBitmap(), display);
			        if (resizedBitmap != null) {
			        	Log.i("zoomImageFromThumb", "resizedBitmap is not null");
			        	Log.i("zoomImageFromThumb", "Resized image pixels - Width:" + resizedBitmap.getWidth() + ",Height:" + resizedBitmap.getHeight());
			        	expandedImageView.setImageBitmap(resizedBitmap);
			        }
		        }
        } catch (Exception ex) {
        	ex.printStackTrace();
        	Log.i("zoomImageFromThumb", ex.getMessage());
        }


        // Calculate the starting and ending bounds for the zoomed-in image. This step
        // involves lots of math. Yay, math.
        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        // The start bounds are the global visible rectangle of the thumbnail, and the
        // final bounds are the global visible rectangle of the container view. Also
        // set the container view's offset as the origin for the bounds, since that's
        // the origin for the positioning animation properties (X, Y).
        thumbView.getGlobalVisibleRect(startBounds);
        findViewById(R.id.container).getGlobalVisibleRect(finalBounds, globalOffset);
        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        // Adjust the start bounds to be the same aspect ratio as the final bounds using the
        // "center crop" technique. This prevents undesirable stretching during the animation.
        // Also calculate the start scaling factor (the end scaling factor is always 1.0).
        float startScale;
        if ((float) finalBounds.width() / finalBounds.height()
                > (float) startBounds.width() / startBounds.height()) {
            // Extend start bounds horizontally
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            // Extend start bounds vertically
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        // Hide the thumb nail and show the zoomed-in view. When the animation begins,
        // it will position the zoomed-in view in the place of the thumb nail.
        //thumbView.setAlpha(0f); //Uncomment to show hide thumb view on click
        expandedImageView.setVisibility(View.VISIBLE);
        
        //SOUND
        //utilities.playMedia(AlphabetsActivity.this, R.raw.thumb_click);
        
        // Set the pivot point for SCALE_X and SCALE_Y transformations to the top-left corner of
        // the zoomed-in view (the default is the center of the view).
        expandedImageView.setPivotX(0f);
        expandedImageView.setPivotY(0f);

        // Construct and run the parallel animation of the four translation and scale properties
        // (X, Y, SCALE_X, and SCALE_Y).
        
        
        //Display height of thumb image
        Log.i("Animation", "Thumb image width:" + thumbView.getWidth() + ", height:" + thumbView.getHeight());
        
        
        
        AnimatorSet set = new AnimatorSet();
        set
                .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left,
                        finalBounds.left))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top+thumbView.getHeight()+5,
                        finalBounds.top+thumbView.getHeight()+5))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(expandedImageView, View.SCALE_Y, startScale, 1f));
        set.setDuration(mShortAnimationDuration);
        set.setInterpolator(new DecelerateInterpolator());
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        set.start();
        mCurrentAnimator = set;

        // Upon clicking the zoomed-in image, it should zoom back down to the original bounds
        // and show the thumbnail instead of the expanded image.
        final float startScaleFinal = startScale;
        expandedImageView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
			@Override
            public void onClick(View view) {
                if (mCurrentAnimator != null) {
                    mCurrentAnimator.cancel();
                }

                // Animate the four positioning/sizing properties in parallel, back to their
                // original values.
                AnimatorSet set = new AnimatorSet();
                set
                        .play(ObjectAnimator.ofFloat(expandedImageView, View.X, startBounds.left))
                        .with(ObjectAnimator.ofFloat(expandedImageView, View.Y, startBounds.top))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_X, startScaleFinal))
                        .with(ObjectAnimator
                                .ofFloat(expandedImageView, View.SCALE_Y, startScaleFinal));
                
               
                set.setDuration(mShortAnimationDuration);
                set.setInterpolator(new DecelerateInterpolator());
                set.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        thumbView.setAlpha(1f); //Change to 1 to transparent
                        expandedImageView.setVisibility(View.GONE);                        
                        mCurrentAnimator = null;
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        thumbView.setAlpha(1f); //Change to 1 to transparent
                        expandedImageView.setVisibility(View.GONE);
                        mCurrentAnimator = null;
                    }
                });
                set.start();
                mCurrentAnimator = set;
            }
        });
    }
}
