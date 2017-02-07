package com.framework.core.page;

import java.util.ArrayList;
import java.util.List;

/**
 *负责分页
 */
public class Pagination<T> {
	//查询的页数
	private int pageSize = 20;
	//查询的页码
	private int pageNumber = 1;
	//最大页数
	private int maxPages;
	//最大记录量
	private int maxElements;
	//列表
	private List<T> list = new ArrayList<T>(0);
	/**
	 * 构造方法
	 */
	public Pagination() {
	}

	// 设置最大记录数
	public void setMaxElements(int maxElements) {
		this.maxElements = maxElements;
		//设置页数
		setMaxPages();
	}

	// 设置最大页码数
	private void setMaxPages() {
		if (maxElements != 0 && (maxElements % pageSize == 0)) {
			maxPages = maxElements / pageSize;
		} else {
			maxPages = maxElements / pageSize + 1;
		}
		// 判断当前页码是否超出范围
		if (pageNumber > maxPages) {
			this.pageNumber = maxPages;
		}
	}
	/**
	 * 记录总数
	 * 
	 * @return
	 */
	public int getMaxElements() {
		return maxElements;
	}

	/**
	 * 最大页数
	 * 
	 * @return
	 */
	public int getMaxPages() {
		return maxPages;
	}

	/**
	 * 当前页码
	 * 
	 * @return 当前页码
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	/**
	 * 每页大小
	 * 
	 * @return 页大小
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * 设置页数
	 * 
	 * @param pageSize 页总数
	 */
	public void setPageSize(int pageSize) {
		if(pageSize > 0){
			this.pageSize = pageSize;
		}		
		// 重新设置最大页数
		setMaxPages();
	}

	// 返回集合
	public List<T> getList() {
		return list;
	}
}
