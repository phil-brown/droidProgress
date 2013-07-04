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
 * Options for Progress droidQuery extension
 * @author Phil Brown
 *
 */
public class ProgressOptions 
{

	private boolean indeterminate = true;
	public boolean indeterminate() { return indeterminate; }
	public ProgressOptions indeterminate(boolean indeterminate)
	{
		this.indeterminate = indeterminate;
		return this;
	}
	
	private int max = 100;
	public int max() { return max; }
	public ProgressOptions max(int max)
	{
		this.max = max;
		return this;
	}
	
	
	public ProgressOptions()
	{
		
	}
	
	public ProgressOptions(String json) throws JSONException
	{
		this(new JSONObject(json));
	}
	
	public ProgressOptions(JSONObject json) throws JSONException
	{
		this($.map(json));
	}
	
	public ProgressOptions(Map<String, Object> map)
	{
		for (Entry<String, Object> entry : map.entrySet())
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
