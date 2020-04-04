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
import java.io.Writer;
import java.util.Objects;

/**
 * A writer that writes directly to the text of a {@link TextView}.
 *
 * @author LSaferSE
 * @version 1 release (22-Feb-2020)
 * @since 22-Feb-2020
 */
public class TextViewWriter extends Writer {
	/**
	 * The text view to write to.
	 */
	final protected TextView view;
	/**
	 * True if this writer is closed.
	 */
	boolean closed = false;

	/**
	 * Construct a new writer that writes to the given {@link TextView}.
	 *
	 * @param view to write to
	 * @throws NullPointerException if the given view is null
	 */
	public TextViewWriter(TextView view) {
		super(view);
		Objects.requireNonNull(view, "view");
		this.view = view;
	}
	/**
	 * Construct a new writer that writes to the given {@link TextView}.
	 *
	 * @param view to write to
	 * @param lock the lock to synchronize to
	 * @throws NullPointerException if the given view is null
	 */
	public TextViewWriter(TextView view, Object lock) {
		super(lock);
		this.view = view;
	}

	@Override
	public void close() {
		synchronized (super.lock) {
			this.closed = true;
		}
	}

	@Override
	public void flush() throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");
		}
	}

	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");

			this.view.append(String.valueOf(cbuf, off, len));
		}
	}
	@Override
	public void write(char[] cbuf) throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");

			this.view.append(String.valueOf(cbuf));
		}
	}
	@Override
	public void write(String str, int off, int len) throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");

			this.view.append(str, off, len);
		}
	}
	@Override
	public void write(int c) throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");

			this.view.append(String.valueOf(c));
		}
	}
	@Override
	public void write(String str) throws IOException {
		synchronized (super.lock) {
			if (this.closed)
				throw new IOException("Writer closed!");

			this.view.append(str);
		}
	}
}
