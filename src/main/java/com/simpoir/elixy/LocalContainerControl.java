package com.simpoir.elixy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LocalContainerControl {

	private final String _name;
	private static final String CMD_PREFIX =
			Controller.getProps().getProperty("LXC_CMD_PREFIX");

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
		Process proc;
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
			state = new BufferedReader(new InputStreamReader(
					proc.getInputStream())).readLine();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		state = state.split("\\W+")[1];
		return State.valueOf(state);
	}

}
