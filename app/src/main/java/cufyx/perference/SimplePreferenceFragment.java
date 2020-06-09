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

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.XmlRes;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;

/**
 * A simple preference fragment to reduce the number of preference-fragments in an application.
 *
 * @author lsafer
 * @version 0.1.5
 * @since 04-Jun-20
 */
public class SimplePreferenceFragment extends PreferenceFragmentCompat {
	/**
	 * The xml-resource of this preference-fragment.
	 */
	@XmlRes
	protected int res;

	/**
	 * The preference-data-store of this fragment.
	 */
	protected PreferenceDataStore store;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		Activity activity = this.getActivity();

		if (activity instanceof OwnerActivity) {
			this.res = ((OwnerActivity) activity).getPreferenceResources(this);
			this.store = ((OwnerActivity) activity).getPreferenceDataStore(this);
		} else throw new IllegalArgumentException("Illegal Owner, not implementing " + OwnerActivity.class.getSimpleName());

		super.onCreate(savedInstanceState);

	}

	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		this.getPreferenceManager().setPreferenceDataStore(this.store);
		this.setPreferencesFromResource(this.res, rootKey);
	}

	/**
	 * An activity that uses a simple preference-fragment.
	 */
	public interface OwnerActivity {
		/**
		 * Returns the data-store for the given fragment.
		 *
		 * @param fragment to get data-store for.
		 * @return the data-store for the given fragment.
		 * @throws NullPointerException if the given 'fragment' is null.
		 */
		PreferenceDataStore getPreferenceDataStore(SimplePreferenceFragment fragment);
		/**
		 * Returns the xml-resources for the preference-fragment given.
		 *
		 * @param fragment to get the xml-resources of.
		 * @return the xml resource for the given fragment.
		 * @throws NullPointerException if the given 'fragment' is null.
		 */
		@XmlRes
		int getPreferenceResources(SimplePreferenceFragment fragment);
	}
}
