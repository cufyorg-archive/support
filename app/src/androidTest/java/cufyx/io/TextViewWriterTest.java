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
