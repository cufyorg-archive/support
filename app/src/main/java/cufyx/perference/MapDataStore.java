/*
 *	Copyright 2020 Cufy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	    http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package cufyx.perference;

import androidx.annotation.Nullable;
import androidx.preference.PreferenceDataStore;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A data-store that uses a map as its storage.
 *
 * @author lsafer
 * @version 0.1.3
 * @since 25-May-20
 */
public class MapDataStore extends PreferenceDataStore {
	/**
	 * To get or put data from or to it.
	 */
	final protected Map map;

	/**
	 * Create a new data-set that uses the given map as its storage.
	 *
	 * @param map the storage map
	 */
	public MapDataStore(Map map) {
		this.map = map;
	}

	@Override
	public void putString(String key, @Nullable String value) {
		this.map.put(key, value);
	}

	@Override
	public void putStringSet(String key, @Nullable Set<String> values) {
		this.map.put(key, values);
	}

	@Override
	public void putInt(String key, int value) {
		this.map.put(key, value);
	}

	@Override
	public void putLong(String key, long value) {
		this.map.put(key, value);
	}

	@Override
	public void putFloat(String key, float value) {
		this.map.put(key, value);
	}

	@Override
	public void putBoolean(String key, boolean value) {
		this.map.put(key, value);
	}

	@Nullable
	@Override
	public String getString(String key, @Nullable String defValue) {
		Object object = this.map.get(key);

		if (object instanceof CharSequence) {
			return object.toString();
		} else {
			return defValue;
		}
	}

	@Nullable
	@Override
	public Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
		Object object = this.map.get(key);

		if (object instanceof Set) {
			return (Set<String>) object;
		} else if (object instanceof Collection) {
			return new HashSet<>((Collection) object);
		} else {
			return defValues;
		}
	}

	@Override
	public int getInt(String key, int defValue) {
		Object object = this.map.get(key);

		if (object instanceof Number) {
			return ((Number) object).intValue();
		} else {
			return defValue;
		}
	}

	@Override
	public long getLong(String key, long defValue) {
		Object object = this.map.get(key);

		if (object instanceof Number) {
			return ((Number) object).longValue();
		} else {
			return defValue;
		}
	}

	@Override
	public float getFloat(String key, float defValue) {
		Object object = this.map.get(key);

		if (object instanceof Number) {
			return ((Number) object).floatValue();
		} else {
			return defValue;
		}
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		Object object = this.map.get(key);

		if (object instanceof Boolean) {
			return (Boolean) object;
		} else {
			return defValue;
		}
	}
}
