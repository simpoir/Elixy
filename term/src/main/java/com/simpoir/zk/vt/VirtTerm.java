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

