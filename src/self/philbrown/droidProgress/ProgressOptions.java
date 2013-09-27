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

import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import self.philbrown.droidQuery.$;

/**
 * Options for {@link Progress} droidQuery extension
 * @author Phil Brown
 *
 */
public class ProgressOptions 
{
	/**
	 * If {@code true}, progress indicator will be displayed as a spinner. If {@code false}, indicator
	 * will be shown as a progress bar. Default is {@code true}.
	 */
	private boolean indeterminate = true;
	/**
	 * @return whether or not the progress indicator will be shown as a spinner.
	 * If {@code true}, progress indicator will be displayed as a spinner. If {@code false}, indicator
	 * will be shown as a progress bar.
	 */
	public boolean indeterminate() { return indeterminate; }
	/**
	 * Sets whether or not the progress indicator will be shown as a spinner.
	 * If {@code true}, progress indicator will be displayed as a spinner. If {@code false}, indicator
	 * will be shown as a progress bar. Default is {@code true}.
	 * @return {@code this}
	 */
	public ProgressOptions indeterminate(boolean indeterminate)
	{
		this.indeterminate = indeterminate;
		return this;
	}
	
	/**
	 * Maximum progress for determinate progress indicators. Default is 100.
	 */
	private int max = 100;
	/**
	 * @return the maximum progress value for {@code this} determinate Progress indicator
	 */
	public int max() { return max; }
	/**
	 * Sets the maximum progress value for {@code this} determinate Progress indicator
	 * @param max the new max value. Default is 100.
	 * @return {@code this}
	 */
	public ProgressOptions max(int max)
	{
		this.max = max;
		return this;
	}
	
	/**
	 * Default Constructor
	 */
	public ProgressOptions(){}
	
	/**
	 * Constructor. Accepts a <em>JSON</em> String of Options, including {@link #indeterminate()} and
	 * {@link #max()}. For example:<br>
	 * <pre>
	 * new ProgressOptions("{indeterminate: false, max: 100}");
	 * </pre>
	 * @param json the JSON String
	 * @throws JSONException if the String is malformed
	 */
	public ProgressOptions(String json) throws JSONException
	{
		this(new JSONObject(json));
	}
	
	/**
	 * Constructor. Accepts a {@link JSONObject} of Options, including {@link #indeterminate()} and
	 * {@link #max()}.
	 * @param json the JSONObject
	 * @throws JSONException if the JSONObject is malformed
	 */
	public ProgressOptions(JSONObject json) throws JSONException
	{
		this($.map(json));
	}
	
	/**
	 * Constructor. Accepts a {@code Map} of Options, including {@link #indeterminate()} and
	 * {@link #max()}.
	 * @param map the Options Map
	 * @see self.philbrown.droidQuery.QuickMap
	 */
	public ProgressOptions(Map<String, ?> map)
	{
		for (Entry<String, ?> entry : map.entrySet())
		{
			if (entry.getKey().equalsIgnoreCase("indeterminate"))
			{
				this.indeterminate(Boolean.valueOf(entry.getValue().toString()));
			}
			else if (entry.getKey().equalsIgnoreCase("max"))
			{
				this.max(Integer.valueOf(entry.getValue().toString()));
			}
		}
	}
}
