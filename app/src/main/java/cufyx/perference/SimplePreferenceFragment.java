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
		} else throw new IllegalArgumentException("Illegal Owner, not implementing EdgeDataFragment.Activity");

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
		 * Returns the xml-resources for the preference-fragment given.
		 *
		 * @param fragment to get the xml-resources of
		 * @return the xml resource for the given fragment
		 * @throws NullPointerException if the given 'fragment' is null
		 */
		@XmlRes
		int getPreferenceResources(SimplePreferenceFragment fragment);

		/**
		 * Returns the data-store for the given fragment.
		 *
		 * @param fragment to get data-store for
		 * @return the data-store for the given fragment
		 * @throws NullPointerException if the given 'fragment' is null
		 */
		PreferenceDataStore getPreferenceDataStore(SimplePreferenceFragment fragment);
	}
}
