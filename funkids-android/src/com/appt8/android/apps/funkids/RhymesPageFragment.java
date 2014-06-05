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

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * A fragment representing a single step in a wizard. The fragment shows a dummy title indicating
 * the page number, along with some dummy text.
 *
 * <p>This class is used by the {@link CardFlipActivity} and {@link
 * ScreenSlideActivity} samples.</p>
 */
public class RhymesPageFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;

    TextView txtContent;
    
    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static RhymesPageFragment create(int pageNumber) {
    	RhymesPageFragment fragment = new RhymesPageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public RhymesPageFragment() {
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);             
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_rhymes, container, false);

        // Set the title view to show the page number.
        //txtContent = (TextView) rootView.findViewById(R.id.rhymes_content);
        //txtContent.setText(Html.fromHtml(getString(R.string.rhymes_index, mPageNumber + 1)));
        //txtContent.setMovementMethod(new ScrollingMovementMethod());       
        WebView webView = (WebView) rootView.findViewById(R.id.web_content);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(getString(R.string.rhymes_index, mPageNumber + 1), "text/html", "utf-8");
        
        
        switch(mPageNumber) {
        case 1:
        	webView.loadData(getString(R.string.rhymes_content_1, mPageNumber + 1), "text/html", "utf-8");
        	break;        	
        case 2:
        	webView.loadData(getString(R.string.rhymes_content_2, mPageNumber + 1), "text/html", "utf-8");
        	break;
        case 3:
        	webView.loadData(getString(R.string.rhymes_content_3, mPageNumber + 1), "text/html", "utf-8");
        	//txtContent.setText(Html.fromHtml(getString(R.string.rhymes_content_3, mPageNumber + 1)));
        	//txtContent.setBackgroundColor(Color.parseColor("#FFA340"));
        	break;
        case 4:
        	webView.loadData(getString(R.string.rhymes_content_4, mPageNumber + 1), "text/html", "utf-8");
        	//txtContent.setText(Html.fromHtml(getString(R.string.rhymes_content_3, mPageNumber + 1)));
        	//txtContent.setBackgroundColor(Color.parseColor("#FFA340"));
        	break;        	
        }
        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}
