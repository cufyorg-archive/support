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
package cufyx.util;

import android.view.View;
import android.view.ViewGroup;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * Static utils for {@link ViewGroup}.
 *
 * @author lsafer
 * @version 0.1.3
 * @since 21-Feb-2020
 */
final public class Viewz {
	/**
	 * This is a util class. And shall not be instanced as an object.
	 *
	 * @throws AssertionError when called
	 */
	private Viewz() {
		throw new AssertionError("No instance for you!");
	}

	/**
	 * Get a remote list to control (add, remove, etc..) for the given group.
	 *
	 * @param group to get a remote for
	 * @return a remote list for the children of the given group
	 * @throws NullPointerException if the given group is null
	 */
	public static List<View> asList(ViewGroup group) {
		Objects.requireNonNull(group, "group");
		return new AbstractList<View>() {
			@Override
			public boolean add(View t) {
				group.addView(t);
				return true;
			}

			@Override
			public View get(int index) {
				return group.getChildAt(index);
			}

			@Override
			public View set(int index, View element) {
				View old = group.getChildAt(index);
				group.removeViewAt(index);
				group.addView(element, index);
				return old;
			}

			@Override
			public void add(int index, View element) {
				group.addView(element, index);
			}

			@Override
			public View remove(int index) {
				View old = group.getChildAt(index);
				group.removeViewAt(index);
				return old;
			}

			@Override
			public int indexOf(Object o) {
				return group.indexOfChild((View) o);
			}

			@Override
			public void clear() {
				group.removeAllViews();
			}

			@Override
			public int size() {
				return group.getChildCount();
			}

			@Override
			public boolean contains(Object o) {
				return this.indexOf(o) != -1;
			}

			@Override
			public boolean remove(Object o) {
				int i = this.indexOf(o);
				if (i == -1) {
					return false;
				} else {
					group.removeView((View) o);
					return true;
				}
			}
		};
	}
}
