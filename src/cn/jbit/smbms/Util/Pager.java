package cn.jbit.smbms.Util;

import java.util.List;

public class Pager<T> {
	
	private int pageIndex;
	private int pageSize;
	private int totalCount;
	private List<T> list;
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPages() {
		return (this.totalCount%this.pageSize==0)? this.totalCount/this.pageSize : this.totalCount/this.pageSize+1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public boolean isHasPrevPage() {
		return this.pageIndex > 1;
	}
	public boolean isHasNextPage() {
		return this.pageIndex < this.getTotalPages();
	}
	
	
}
