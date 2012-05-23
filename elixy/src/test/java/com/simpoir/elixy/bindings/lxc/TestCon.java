package com.simpoir.elixy.bindings.lxc;

import java.io.*;

/**
 * Tests console binding using liblxc
 */
public class TestCon {
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("expected container name argument");
			System.exit(-1);
		}

		String name = args[0];

		LxcConsole con = Liblxc.getConsole(name);
		if (con == null) {
			System.out.println("Container seems to be stopped or an error occured");
			return;
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			try {
				String val = in.readLine();
				con.write(val.getBytes());
				con.write((byte)13);
			} catch (IOException e) {
				break;
			}
			if (con.ready()) {
				System.out.println(con.read());
			}
		}
		con.close();
	}
}