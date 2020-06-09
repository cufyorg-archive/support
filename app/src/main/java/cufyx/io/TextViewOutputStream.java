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

import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * An output-stream that writes directly to the text of a {@link TextView}.
 *
 * @author lsafer
 * @version 0.1.3
 * @since 22-Feb-2020
 */
public class TextViewOutputStream extends OutputStream {
	/**
	 * The text view to write to.
	 */
	final protected TextView view;
	/**
	 * True if this stream is closed.
	 */
	boolean closed = false;

	/**
	 * Construct a new stream that writes to the given {@link TextView}.
	 *
	 * @param view to write to.
	 * @throws NullPointerException if the given view is null.
	 */
	public TextViewOutputStream(TextView view) {
		Objects.requireNonNull(view, "view");
		this.view = view;
	}

	@Override
	public void write(int c) throws IOException {
		if (this.closed)
			throw new IOException("Writer closed!");

		this.view.append(String.valueOf(c));
	}

	@Override
	public void flush() throws IOException {
		if (this.closed)
			throw new IOException("Writer closed!");
	}

	@Override
	public void close() {
		this.closed = true;
	}
}
