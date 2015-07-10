package pageManageSystem;

import javax.swing.*;

import java.util.*;

@SuppressWarnings("serial")
public class Page extends JPanel {
	public static enum STATUS {STOP, RUNNING, NEXT};
	protected int curTargetPageIndex;
	protected List<String> targetPageNameList;
	protected String pageName;
	protected STATUS pageStatus;
	protected PageData pageData;
	
	protected Page() {
		this(100,100,"Default");
	}
	protected Page(int width, int height, String _pageName) {
		setPageName(_pageName);
		setPageStatus(STATUS.STOP);
		
		targetPageNameList = new ArrayList<String>();
		curTargetPageIndex = -1;
		
		pageData = null;
		
		setSize(width,height);
		setVisible(true);
	}
	
	private void setPageName(String name) {
		pageName = name;
	}
	public String getPageName() {
		return pageName;
	}
	
	public void addTargetPageName(String name) {
		targetPageNameList.add(name);
	}
	public String getTargetPageName() {
		
		if(targetPageNameList.size() > 0 && curTargetPageIndex != -1) {
			return targetPageNameList.get(curTargetPageIndex);
		}
		return null;
	}
	public boolean setTargetPageIndex(int idx) {
		if(idx<0 || idx>targetPageNameList.size()) {
			return false;
		}
		curTargetPageIndex = idx;
		setPageStatus(STATUS.NEXT);
		return true;
	}
	
	public STATUS getPageStatus() {
		return pageStatus;
	}
	protected void setPageStatus(STATUS status) {
		pageStatus = status;
	}
	
	public void LoadPage(PageData args) {
		setPageStatus(STATUS.RUNNING);
		pageData = args;
		//args.clear();
		PageLoaded();
	}
	public PageData UnloadPage() {
		PageUnloaded();
		setPageStatus(STATUS.STOP);
		return pageData;
		//PageData resultArguments = pageData;
		//pageData = null;
		//return resultArguments;
	}
	protected void PageLoaded() {
		
	}
	protected void PageUnloaded() {
		
	}
}
