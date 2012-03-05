package com.simpoir.zk.vt;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

public class TypeEvent extends Event {

	public TypeEvent(String name, Component target, Object data) {
		super(name, target, data);
	}

	public static final String NAME = "onType";

}
