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
package cufy.util;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@SuppressWarnings("JavaDoc")
public class View$Test {
	@Test
	public void remoteList() {
		Context context = ApplicationProvider.getApplicationContext();
		LinearLayout layout = new LinearLayout(context);

		View view1 = new TextView(context);
		View view2 = new TextView(context);
		View view3 = new TextView(context);

		//----------

		List<View> list = View$.listFor(layout);

		layout.addView(view1);

		Assert.assertEquals("Views unreachable", view1, list.get(0));
		Assert.assertEquals("Index can't be gotten", 0, list.indexOf(view1));

		list.remove(view1);

		Assert.assertFalse("Can't remove the view", list.contains(view1));
		Assert.assertEquals("View not actually removed", 0, layout.getChildCount());

		list.add(view1);

		Assert.assertEquals("Can't add the view", 1, layout.getChildCount());
		Assert.assertEquals("Added the wrong view", view1, layout.getChildAt(0));

		list.set(0, view2);

		Assert.assertEquals("Can't replace views", view2, layout.getChildAt(0));
	}
//	@Test
//	public void remoteAdaptersList() {
//		Context context = ApplicationProvider.getApplicationContext();
//		class TestAdapter implements ViewAdapter {
//			private View view;
//
//			public TestAdapter(View view) {
//				this.view = view;
//			}
//
//			@Override
//			public View getView() {
//				return this.view;
//			}
//		}
//
//		LinearLayout layout = new LinearLayout(context);
//
//		View view1 = new TextView(context);
//		View view2 = new TextView(context);
//		View view3 = new TextView(context);
//
//		ViewAdapter adapter1 = new TestAdapter(view1);
//		ViewAdapter adapter2 = new TestAdapter(view2);
//		ViewAdapter adapter3 = new TestAdapter(view3);
//
//		//----
//
//		List<ViewAdapter> adapters = ViewGroup$.remoteAdaptersList(layout);
//
//		layout.addView(view1);
//
//		adapters.remove(adapter1);
//
//		Assert.assertFalse("Can't remove the view", adapters.contains(adapter1));
//		Assert.assertEquals("View not actually removed", 0, layout.getChildCount());
//
//		adapters.add(adapter1);
//
//		Assert.assertEquals("Can't add the view", 1, layout.getChildCount());
//		Assert.assertEquals("Added the wrong view", view1, layout.getChildAt(0));
//
//		adapters.set(0, adapter2);
//
//		Assert.assertEquals("Can't replace views", view2, layout.getChildAt(0));
//	}
}
