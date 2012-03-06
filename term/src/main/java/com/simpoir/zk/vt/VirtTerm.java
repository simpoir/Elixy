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

import org.zkoss.zul.impl.XulElement;

public class VirtTerm extends XulElement {
	private int _cols = -1;
	private int _rows = -1;

	static {
		addClientEvent(VirtTerm.class, TypeEvent.NAME, 0);
	}

	public VirtTerm() {}

	public VirtTerm(int cols, int rows) {
		this._cols = cols;
		this._rows = rows;
	}

	public void setCols(int cols) {
		_cols = cols;
		smartUpdate("cols", _cols);
	}
	public int getCols() {
		return _cols;
	}
	public void setRows(int rows) {
		_rows = rows;
		smartUpdate("rows", _rows);
	}
	public int getRows() {
		return _rows;
	}

	public void setText(String text) {
		smartUpdate("text", text, true);
	}

	//super//
	@Override
	protected void renderProperties(org.zkoss.zk.ui.sys.ContentRenderer renderer)
			throws java.io.IOException {
		super.renderProperties(renderer);

		render(renderer, "rows", _rows);
		render(renderer, "cols", _cols);
	}

	/**
	 * The default zclass is "z-virtterm"
	 */
	@Override
	public String getZclass() {
		return (this._zclass != null ? this._zclass : "z-virtterm");
	}

}

