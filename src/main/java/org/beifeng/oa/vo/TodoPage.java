package org.beifeng.oa.vo;

import java.util.List;

public class TodoPage {

	private int totalcount;
	private int totalpage;
	private int currentpage;
	private boolean hasnext;
	private boolean haslast;
	private List<TodoVO> context;
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public boolean isHasnext() {
		return hasnext;
	}
	public void setHasnext(boolean hasnext) {
		this.hasnext = hasnext;
	}
	public boolean isHaslast() {
		return haslast;
	}
	public void setHaslast(boolean haslast) {
		this.haslast = haslast;
	}
	public List<TodoVO> getContext() {
		return context;
	}
	public void setContext(List<TodoVO> context) {
		this.context = context;
	}
}
