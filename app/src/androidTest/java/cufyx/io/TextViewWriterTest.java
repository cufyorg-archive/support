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
