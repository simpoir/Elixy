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
package ctrl;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;

import com.simpoir.zk.vt.VirtTerm;

public class DemoWindowComposer extends GenericForwardComposer {

	private VirtTerm myComp;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	}

	public void onClick$myComp (ForwardEvent event) {
		MouseEvent mouseEvent = (MouseEvent) event.getOrigin();
		myComp.setText("Hello World! ");

	}

}
