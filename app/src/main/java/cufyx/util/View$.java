/*
 * Copyright (c) 2020, LSafer, All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * -You can edit this file (except the header).
 * -If you have change anything in this file. You
 *  shall mention that this file has been edited.
 *  By adding a new header (at the bottom of this header)
 *  with the word "Editor" on top of it.
 */
package cufyx.util;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;

/**
 * Static utils for {@link ViewGroup}.
 *
 * @author LSaferSE
 * @version 2 release (22-Feb-2020)
 * @since 21-Feb-2020
 */
final public class View$ {
	/**
	 * This is a util class. And shall not be instanced as an object.
	 *
	 * @throws AssertionError when called
	 */
	private View$() {
		throw new AssertionError("No instance for you!");
	}

	/**
	 * Get a remote list to control (add, remove, etc..) for the given group.
	 *
	 * @param group to get a remote for
	 * @return a remote list for the children of the given group
	 * @throws NullPointerException if the given group is null
	 */
	public static List<View> listFor(ViewGroup group) {
		Objects.requireNonNull(group, "group");

		return new AbstractList<View>() {
			@Override
			public int size() {
				return group.getChildCount();
			}

			@Override
			public boolean add(View t) {
				group.addView(t);
				return true;
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

			@Override
			public void clear() {
				group.removeAllViews();
			}

			@Override
			public String toString() {
				return this.getClass() + "@" + this.hashCode();
			}

			@Override
			public List<View> subList(int fromIndex, int toIndex) {
				throw new UnsupportedOperationException();
			}

			@Override
			public boolean equals(@Nullable Object o) {
				return this == o;
			}

			@Override
			public int hashCode() {
				return System.identityHashCode(this);
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
			public Spliterator<View> spliterator() {
				throw new UnsupportedOperationException();
			}
		};
	}
}
