package cufyx.util;

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

		List<View> list = Viewz.asList(layout);

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
}
