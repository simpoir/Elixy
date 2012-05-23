package com.simpoir.elixy.bindings.lxc;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

import java.security.AccessControlException;

public class Liblxc {
	private static lxc lib;
	static {
		lib = (lxc) Native.loadLibrary("lxc-0.8.0-rc1", lxc.class);
	}

	public static class lxc_request extends Structure {
		public int type;
		public int data;
	}

	public static class lxc_response extends Structure {
		public int fd;
		public int ret;
		public int pid;
	}

	public static class lxc_command extends Structure {
		public lxc_request request;
		public lxc_response response;
	}

	public interface lxc extends Library {
		/**
		 * Executes a command on a specific container.
		 *
		 * @param name the name of the container
		 * @param command a pointer to a lxc_command to pass to the container
		 * @param stopped a pointer to an int for storing whether this container
		 *                is already stopped or not.
		 * @return the number of bytes received, or -1 if an error occurred.
		 */
		public int lxc_command_connected(String name, lxc_command[] command, int[] stopped);
	}

	/**
	 * Executes a command on a specific container.
	 *
	 * @param name the name of the container
	 * @param command a lxc_command to pass to the container
	 * @return true if the command passed, false if the container is stopped
	 * @throws AccessControlException if command is denied
	 */
	public static boolean command_connected(String name, lxc_command command)
			throws AccessControlException {
		int[] stopped = new int[]{0};
		lib.lxc_command_connected(
				name,
				new lxc_command[]{command},
				stopped);
		return stopped[0] == 0;
	}

	public static LxcConsole getConsole(String name) {
		lxc_command command = new lxc_command();
		command.request.type = 0;
		command.request.data = 0; // request tty0
		command_connected(name, command);

		if (command.response.fd > 0) {
			return new LxcConsole(command.response.fd);
		}
		return null;
	}

	public static lxc get() {
		return lib;
	}
}
