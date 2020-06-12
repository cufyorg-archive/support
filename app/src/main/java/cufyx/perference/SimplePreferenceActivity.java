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

import androidx.annotation.IdRes;
import androidx.annotation.XmlRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;

import java.util.Objects;

/**
 * <br/> TODO.
 *
 * @author lsafer
 * @version 0.1.5
 * @since 12-Jun-2020
 */
public abstract class SimplePreferenceActivity extends AppCompatActivity implements SimplePreferenceFragment.OwnerActivity {
	/**
	 * The resources to be used by the preference-fragment of this.
	 */
	@XmlRes
	private int res;
	/**
	 * The store to be used by the preference-fragment of this.
	 */
	private PreferenceDataStore store;

	@Override
	public PreferenceDataStore getPreferenceDataStore(SimplePreferenceFragment fragment) {
		return this.store;
	}

	@Override
	public int getPreferenceResources(SimplePreferenceFragment fragment) {
		return this.res;
	}

	public <F extends Fragment> F findFragmentById(@IdRes int id) {
		return (F) this.getSupportFragmentManager().findFragmentById(id);
	}

	public <P extends Preference> P findPreferenceByKey(@IdRes int fragmentId, CharSequence key) {
		return (P) this.<PreferenceFragmentCompat>findFragmentById(fragmentId).findPreference(key);
	}

	/**
	 * Set the preference-data-store of the preference-fragment of this.
	 *
	 * @param store to be set.
	 * @throws NullPointerException if the given 'store' is null.
	 */
	public void setPreferenceDataStore(PreferenceDataStore store) {
		Objects.requireNonNull(store, "store");
		this.store = store;
	}

	/**
	 * Set the preference layout of the preference-fragment of this.
	 *
	 * @param res the new resources to be set.
	 */
	public void setPreferenceLayout(@XmlRes int res) {
		this.res = res;
	}
}
