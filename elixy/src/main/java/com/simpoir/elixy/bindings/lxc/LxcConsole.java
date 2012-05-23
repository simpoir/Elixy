package com.simpoir.elixy.bindings.lxc;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class LxcConsole {
	private static libc lib;
	private int fd;
	boolean isOpen;

	public LxcConsole(int fd) {
		this.fd = fd;
		this.isOpen = true;
	}

	public void close() {
		if (!isOpen) {
			return;
		}

		lib.close(fd);
		isOpen = false;
	}

	public String read() {
		byte[] buf = new byte[1024];
		int len = lib.read(fd, buf, buf.length);
		return new String(buf, 0, len);
	}

	public void write(byte[] bytes) {
		lib.write(fd, bytes, bytes.length);
	}

	public void write(byte val) {
		lib.write(fd, new byte[]{val}, 1);
	}

	public boolean ready() {
		return 0 != lib.select(fd+1, new int[]{fd}, new int[0], new int[0], new long[4]);
	}

	static {
		lib = (libc) Native.loadLibrary("c",libc.class);
	}

	public static interface libc extends Library {
		public int read(int fd, byte[] buf, int count);
		public int write(int fd, byte[] buf, int count);
		public int close(int fd);
		public int select(int nfds, int[] readfds, int[] writefds, int[] exceptfds, long[] timeout);
	}

}
