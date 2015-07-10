package movieTicketBoxOffice;

import pageManageSystem.*;

@SuppressWarnings("serial")
public class BoxOfficePageManager extends PageManager{
	public BoxOfficePageManager() {
		itsPageData = new MoviePageData();
		
		Page pageIntro = new PageIntro();
		Page pageReservation = new PageReservation();
		Page pagePayment = new PagePayment();
		Page pagePrint = new PagePrint();
		Page pageInput = new PageInput();
		

		InsertPage(pageIntro);
		pageIntro.addTargetPageName(pageInput.getPageName());
		pageIntro.addTargetPageName(pageReservation.getPageName());
		
		InsertPage(pageReservation);
		pageReservation.addTargetPageName(pageIntro.getPageName());
		pageReservation.addTargetPageName(pagePayment.getPageName());
		
		InsertPage(pagePayment);
		pagePayment.addTargetPageName(pageReservation.getPageName());
		pagePayment.addTargetPageName(pagePrint.getPageName());
		
		InsertPage(pagePrint);
		pagePrint.addTargetPageName(pageIntro.getPageName());
		
		InsertPage(pageInput);
		pageInput.addTargetPageName(pageIntro.getPageName());
		pageInput.addTargetPageName(pagePrint.getPageName());
		
		add(pageIntro, pageIntro.getPageName());
		add(pageReservation, pageReservation.getPageName());
		add(pagePayment, pagePayment.getPageName());
		add(pagePrint, pagePrint.getPageName());
		add(pageInput, pageInput.getPageName());
		
		curPage.LoadPage(itsPageData);
	}
}
