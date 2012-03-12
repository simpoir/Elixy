package com.simpoir.elixy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class LocalContainerControl {
	private interface POSIX extends Library {
		public int posix_openpt(int flags);
	}

	private final String _name;
	private static final String CMD_PREFIX =
			Controller.getProps().getProperty("LXC_CMD_PREFIX");
	private static final String CMD_PTYFY =
			Controller.getProps().getProperty("PTYFY_PATH");

	public enum State {
		RUNNING,
		STOPPED
	}

	public LocalContainerControl(String host, String name) {
		_name = name;
	}

	public void start() {
		try {
			Runtime.getRuntime().exec(new String[] {
					"sudo",
					CMD_PREFIX+"lxc-start",
					"--name",
					_name,
					"--daemon"
			}).wait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			Process proc = Runtime.getRuntime().exec(new String[] {
					"sudo",
					CMD_PREFIX+"lxc-stop",
					"--name",
					_name
			});
			proc.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public State getState() {
		BufferedReader reader = null;
		Process proc = null;
		String state;
		try {
			proc = Runtime.getRuntime().exec(new String[] {
					"sudo",
					CMD_PREFIX+"lxc-info",
					"--name",
					_name,
					"--state"
			});
			proc.waitFor();
			InputStream is = proc.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is));
			state = reader.readLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {}
			}
			if (proc != null) {
				proc.destroy();
			}
		}
		state = state.split("\\W+")[1];
		return State.valueOf(state);
	}

	public Process getConsole() {
		Process proc;
		String state;
		try {
			POSIX posix = (POSIX) Native.loadLibrary("c", POSIX.class);
			int pt = posix.posix_openpt(0400|02);
			proc = Runtime.getRuntime().exec(new String[] {
					"python",
					CMD_PTYFY,
					"--name",
					_name
			}, new String[] {"LXC_CMD_PREFIX="+CMD_PREFIX});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return proc;
	}

}
