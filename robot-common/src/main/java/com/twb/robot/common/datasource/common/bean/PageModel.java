package com.twb.robot.common.datasource.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 */
@SuppressWarnings("serial")
public class PageModel<T> implements Serializable {

	private int pageNumber = 0;// 第几页
	private int pageSize = 0;// 每页多少数据
	private int pageCount = 0;// 共几页
	private int total = 0;// 共多少数据

	private List<T> rows;// 分页数据

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getRows() {
		if (rows == null) {
			rows = new ArrayList<T>();
		}

		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public void addRow(T element) {
		if (element != null) {
			getRows().add(element);
		}
	}
}