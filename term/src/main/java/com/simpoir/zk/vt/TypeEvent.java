/*******************************************************************************
 * Copyright (C) 2012  Simon Poirier  <simpoir@gmail.com>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 ******************************************************************************/
package com.simpoir.zk.vt;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;

public class TypeEvent extends Event {

	public TypeEvent(String name, Component target, Object data) {
		super(name, target, data);
	}

	public static final String NAME = "onType";

}
