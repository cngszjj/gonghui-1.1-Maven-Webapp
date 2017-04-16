package com.jojoz.gh.dto;

public class UnionRsult<T> {
	
	private int total;
	private T rows;
	
	
	
	public UnionRsult(int total, T rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public T getRows() {
		return rows;
	}
	public void setRows(T rows) {
		this.rows = rows;
	}
	
	
	

}
