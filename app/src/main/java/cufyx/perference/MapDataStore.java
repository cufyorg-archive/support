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

import androidx.preference.PreferenceDataStore;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * A data-store that uses a map as its storage.
 *
 * @author lsafer
 * @version 0.1.5
 * @since 25-May-20
 */
public class MapDataStore extends PreferenceDataStore {
	/**
	 * A collection of listeners to be called when any change occurred in the map of this by this map-data-store.
	 * The listener well be called after the value get stored!.
	 */
	final protected Collection<OnDataChangeListener> onDataChangeListeners = new HashSet<>();
	/**
	 * A collection of listeners to be called when any data request occurred in this map-data-store.
	 * The listener well be called before the data is gotten from the map.
	 */
	final protected Collection<OnDataRequestListener> onDataRequestListeners = new HashSet<>();
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
	public void putString(String key, String value) {
		this.put(key, value);
	}

	@Override
	public void putStringSet(String key, Set<String> values) {
		this.put(key, values);
	}

	@Override
	public void putInt(String key, int value) {
		this.put(key, value);
	}

	@Override
	public void putLong(String key, long value) {
		this.put(key, value);
	}

	@Override
	public void putFloat(String key, float value) {
		this.put(key, value);
	}

	@Override
	public void putBoolean(String key, boolean value) {
		this.put(key, value);
	}

	/**
	 * Put the given object to the map of this.
	 *
	 * @param key   to be put
	 * @param value to be put
	 */
	public void put(Object key, Object value) {
		Object old = this.map.put(key, value);
		this.notifyOnDataChangeListeners(key, old, value);
	}

	@Override
	public String getString(String key, String defValue) {
		Object object = this.get(key);

		if (object instanceof CharSequence) {
			return object.toString();
		} else {
			return defValue;
		}
	}

	@Override
	public Set<String> getStringSet(String key, Set<String> defValues) {
		Object object = this.get(key);

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
		Object object = this.get(key);

		if (object instanceof Number) {
			return ((Number) object).intValue();
		} else {
			return defValue;
		}
	}

	@Override
	public long getLong(String key, long defValue) {
		Object object = this.get(key);

		if (object instanceof Number) {
			return ((Number) object).longValue();
		} else {
			return defValue;
		}
	}

	@Override
	public float getFloat(String key, float defValue) {
		Object object = this.get(key);

		if (object instanceof Number) {
			return ((Number) object).floatValue();
		} else {
			return defValue;
		}
	}

	@Override
	public boolean getBoolean(String key, boolean defValue) {
		Object object = this.get(key);

		if (object instanceof Boolean) {
			return (Boolean) object;
		} else {
			return defValue;
		}
	}

	/**
	 * Get the value associated to the given key in the map of this map-data-store.
	 *
	 * @param key the key to get the value of
	 * @return the value associated to the given key
	 */
	public Object get(Object key) {
		this.notifyOnDataRequestListeners(key);
		return this.map.get(key);
	}

	/**
	 * Register the given listener to be called when any change occurred in the data of this data-store.
	 *
	 * @param listener to be registered
	 * @throws NullPointerException     if the given 'listener' is null
	 * @throws IllegalArgumentException if the given 'listener' is already registered
	 */
	public void registerOnDataChangeListener(OnDataChangeListener listener) {
		Objects.requireNonNull(listener, "listener");
		if (this.onDataChangeListeners.contains(listener))
			throw new IllegalArgumentException("listener already registered");

		synchronized (this.onDataChangeListeners) {
			this.onDataChangeListeners.add(listener);
		}
	}

	/**
	 * Unregister the given listener.
	 *
	 * @param listener to be unregistered
	 * @throws NullPointerException     if the given 'listener' is null
	 * @throws IllegalArgumentException if the given 'listener' is not registered
	 */
	public void unregisterOnDataChangeListener(OnDataChangeListener listener) {
		Objects.requireNonNull(listener, "listener");
		if (!this.onDataChangeListeners.contains(listener))
			throw new IllegalArgumentException("listener not registered");

		synchronized (this.onDataChangeListeners) {
			this.onDataChangeListeners.remove(listener);
		}
	}

	/**
	 * Register teh given listener to be called when any data request occurred in this map-data-store.
	 *
	 * @param listener to be registered
	 * @throws NullPointerException     if the given 'listener' is null
	 * @throws IllegalArgumentException if the given 'listener' already registered
	 */
	public void registerOnDataRequestListener(OnDataRequestListener listener) {
		Objects.requireNonNull(listener, "listener");
		if (this.onDataRequestListeners.contains(listener))
			throw new IllegalArgumentException("listener already registered");

		synchronized (this.onDataRequestListeners) {
			this.onDataRequestListeners.add(listener);
		}
	}

	/**
	 * Unregister the given listener.
	 *
	 * @param listener to be unregistered
	 * @throws NullPointerException     if the given 'listener' is null
	 * @throws IllegalArgumentException if the given 'listener' is not registered
	 */
	public void unregisterOnDataRequestListener(OnDataRequestListener listener) {
		Objects.requireNonNull(listener, "listener");
		if (!this.onDataRequestListeners.contains(listener))
			throw new IllegalArgumentException("listener not registered");

		synchronized (this.onDataRequestListeners) {
			this.onDataRequestListeners.remove(listener);
		}
	}

	/**
	 * Notify all the 'data-change' listeners registered at this map-data-store that a change in data has occurred.
	 *
	 * @param key      the key changed
	 * @param oldValue the old value
	 * @param newValue the new value
	 */
	protected void notifyOnDataChangeListeners(Object key, Object oldValue, Object newValue) {
		synchronized (this.onDataChangeListeners) {
			this.onDataChangeListeners.forEach(l -> l.onDataChange(this, key, oldValue, newValue));
		}
	}

	/**
	 * Notify all the 'data-request' listeners registered at this map-data-store that a data got requested.
	 *
	 * @param key the requested key
	 */
	protected void notifyOnDataRequestListeners(Object key) {
		synchronized (this.onDataRequestListeners) {
			this.onDataRequestListeners.forEach(l -> l.onDataRequest(this, key));
		}
	}

	/**
	 * Listens to any change in a map-data-store. Should be registered using {@link MapDataStore#registerOnDataChangeListener}.
	 */
	public interface OnDataChangeListener {
		/**
		 * Get called when a change happen on a store that this listener is registered at.
		 *
		 * @param store    the store that the change occurred at
		 * @param key      the key that have been changed
		 * @param oldValue the old value
		 * @param newValue the new value
		 * @throws NullPointerException if the given 'store' is null
		 */
		void onDataChange(MapDataStore store, Object key, Object oldValue, Object newValue);
	}

	/**
	 * Listens to any data-request in a map-data-store. Should be registered using {@link MapDataStore}
	 */
	public interface OnDataRequestListener {
		/**
		 * Get called when a data is requested on a store that this listener is registered at.
		 *
		 * @param store the store that the request is occurred at
		 * @param key   the key that have been requested
		 * @throws NullPointerException if the given 'store' is null
		 */
		void onDataRequest(MapDataStore store, Object key);
	}
}
