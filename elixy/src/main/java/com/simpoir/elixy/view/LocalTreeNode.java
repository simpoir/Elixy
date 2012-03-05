package com.simpoir.elixy.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zul.SimpleTreeNode;

import com.simpoir.elixy.Controller;

/**
 * this class is a test wrapper around simple tree node
 * @deprecated this class is meant to be replaced by some JPA-bound config
 */
@Deprecated
public class LocalTreeNode extends SimpleTreeNode {

	private static List<SimpleTreeNode> _containers;

	static {
		_containers = new ArrayList<SimpleTreeNode>();
		File lxcDir = new File(Controller.getProps().getProperty("LXC_PREFIX"));
		for (File file : lxcDir.listFiles()) {
			if (!file.isDirectory()) {
				continue;
			}

			_containers.add(new SimpleTreeNode(
					file.getName(),
					new ArrayList<SimpleTreeNode>()));
		}
	}

	public LocalTreeNode() {
		super("Localhost", _containers);
	}
}
