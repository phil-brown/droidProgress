/*
 * Copyright 2013 Phil Brown
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

package self.philbrown.droidProgress;

import self.philbrown.droidQuery.$;
import self.philbrown.droidQuery.$Extension;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

/**
 * Progress Spinner/Bar extension for <a href="https://github.com/phil-brown/droidQuery">droidQuery</a>. Usage:
 * <pre>
 * $.extend("progress", "self.philbrown.droidProgress.Progress");
 * final Progress progress = (Progress) $.with(this).ext("progress", new ProgressOptions().indeterminate(true));
 * progress.start();
 * $.timeout(new Function() {
 * 	public void invoke(Object... args)
 * 	{
 * 		MyActivity.this.runOnUiThread(progress.stop());
 * 	}
 * }, 1000);
 * </pre>
 * Note that for this plugin, {@link $#with(Context)} will produce a full-screen progress bar/spinner,
 * whereas {@link $#with(View)} will produce one bound within the given view.
 * @author Phil Brown
 *
 */
public class Progress extends $Extension 
{
	/** Provides a reference to the context and the view where the progress is shown. */
	private $ droidQuery;
	
	/**
	 * Progress Options
	 */
	private ProgressOptions options;
	/**
	 * The progress indicator
	 */
	private ProgressBar bar;
	/**
	 * The parent view
	 */
	private RelativeLayout progressContainer;

	/**
	 * Constructor
	 * @param droidQuery
	 */
	public Progress($ droidQuery) 
	{
		super(droidQuery);
		this.droidQuery = droidQuery;
	}

	/**
	 * Initiates the progress, and sets up its location in the layout.
	 */
	@Override
	protected void invoke(Object... args) 
	{
		//new Progress Instance
		options = (ProgressOptions) args[0];
		Context context = droidQuery.context();
		
		bar = new ProgressBar(context);
		bar.setIndeterminate(options.indeterminate());
		bar.setMax(options.max());
		
		progressContainer = new RelativeLayout(context);
		progressContainer.setBackgroundColor(Color.parseColor("#999999"));
		progressContainer.setAlpha(0.2f);
		progressContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.CENTER_IN_PARENT);
		bar.setLayoutParams(params);
		progressContainer.addView(bar);
		
		if (droidQuery.length() == 1 && droidQuery.view(0) instanceof ViewGroup)
		{
			//add as subview
			((ViewGroup) droidQuery.view(0)).addView(progressContainer);
		}
		else
		{
			//add over top of view
			
			if (droidQuery.view(0).getParent() != null && droidQuery.view(0).getParent() instanceof ViewGroup)
			{
				Point position = droidQuery.position();
				int width = droidQuery.width();
				int height = droidQuery.height();
				progressContainer.setLayoutParams(new ViewGroup.LayoutParams(width, height));
				((ViewGroup) droidQuery.view(0).getParent()).addView(progressContainer);
				progressContainer.setX(position.x);
				progressContainer.setY(position.y);
				progressContainer.setVisibility(View.GONE);
			}
			else
			{
				//if that fails, just show over the whole screen.
				final Point offset = droidQuery.offset();
				final int width = droidQuery.width();
				final int height = droidQuery.height();
				try
				{
					View v = $.with(context).id(android.R.id.content).view(0);
					ViewGroup root = (ViewGroup) v.getRootView();
					int adjustedWidth = width;
					WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
					Display display = wm.getDefaultDisplay();
					if (width == 0)
					{
						adjustedWidth = display.getWidth();
					}
					int adjustedHeight = height;
					if (height == 0)
					{
						adjustedHeight = display.getHeight();
					}
					progressContainer.setLayoutParams(new ViewGroup.LayoutParams(adjustedWidth, adjustedHeight));
					root.addView(progressContainer);
					progressContainer.setX(offset.x);
					progressContainer.setY(offset.y);
					progressContainer.setVisibility(View.GONE);
					
				}
				catch (Throwable t)
				{
					//could not add progress element
				}
			}
		}
		
	}
	
	/**
	 * Show the progress indicator
	 */
	public void start()
	{
		progressContainer.setVisibility(View.VISIBLE);
	}
	
	/**
	 * Hide the progress indicator
	 */
	public void stop()
	{
		progressContainer.setVisibility(View.GONE);
	}
	
	/**
	 * Update the progress indicator (useful for determinate progress indicators)
	 */
	public void update(int progress)
	{
		bar.setProgress(progress);
	}

}
