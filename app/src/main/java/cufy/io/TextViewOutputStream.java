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
package cufy.io;

import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/**
 * A output-stream that writes directly to the text of a {@link TextView}.
 *
 * @author LSaferSE
 * @version 1 release (22-Feb-2020)
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
	 * @param view to write to
	 * @throws NullPointerException if the given view is null
	 */
	public TextViewOutputStream(TextView view) {
		Objects.requireNonNull(view, "view");
		this.view = view;
	}

	@Override
	public void close() {
		this.closed = true;
	}

	@Override
	public void flush() throws IOException {
		if (this.closed)
			throw new IOException("Writer closed!");
	}

	@Override
	public void write(int c) throws IOException {
		if (this.closed)
			throw new IOException("Writer closed!");

		this.view.append(String.valueOf(c));
	}
}
