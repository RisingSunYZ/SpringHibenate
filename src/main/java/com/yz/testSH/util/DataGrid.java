package com.yz.testSH.util;

import java.util.List;

public class DataGrid<T> {

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	private long total;
	
	private List<T> rows;

	public DataGrid(long total, List<T> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	
	
	
}
