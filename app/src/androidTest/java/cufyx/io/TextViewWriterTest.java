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
package cufyx.io;

import android.content.Context;
import android.widget.TextView;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

@SuppressWarnings("JavaDoc")
public class TextViewWriterTest {
	@Test
	public void write() throws IOException {
		Context context = ApplicationProvider.getApplicationContext();
		String source = "abc def ghi jkl mno pqr stu vwx yz0";

		TextView view = new TextView(context);
		TextViewWriter writer = new TextViewWriter(view);

		writer.write(source);

		Assert.assertEquals("Source and product does not match", source, view.getText().toString());
	}
}
