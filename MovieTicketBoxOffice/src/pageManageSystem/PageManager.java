package pageManageSystem;

import java.awt.CardLayout;
import java.awt.Component;

import javax.swing.*;

@SuppressWarnings("serial")
public class PageManager extends JPanel {
	public boolean bRunning;
	protected CardLayout itsCardLayout;
	protected PageData itsPageData;
	protected Page curPage;
	protected PageManager() {
		super(new CardLayout());
		itsCardLayout = (CardLayout)this.getLayout();
	}
	public void UpdatePages() {
		if(curPage != null && curPage.getPageStatus() == Page.STATUS.NEXT) {
			itsPageData = curPage.UnloadPage();
			String nextPageName = curPage.getTargetPageName();
			itsCardLayout.show(this, nextPageName);
			curPage = getCurPage();
			System.out.println("Page Changed:" + curPage.getPageName());
			curPage.LoadPage(itsPageData);
		}
	}
	public Page getCurPage() {
		for (Component comp : this.getComponents()) {
			if(comp.isVisible()) {
				return (Page) comp;
			}
		}
		return null;
	}
	public Page getPage(String pageName) {
		for (Component comp : this.getComponents()) {
			Page selectedPage = (Page) comp;
			if(selectedPage.getName().equals(pageName)) {
				return selectedPage;
			}
		}
		return null;
	}
	protected void InsertPage(Page p) {
		if(curPage == null) {
			curPage = p;
		}
		add(p, p.getPageName());
	}
	protected void RemovePage(Page p) {
		Page selectedPage = getPage(p.getName());
		remove(selectedPage);
	}
}
