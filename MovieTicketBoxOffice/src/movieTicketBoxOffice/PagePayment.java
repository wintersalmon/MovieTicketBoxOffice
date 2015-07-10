package movieTicketBoxOffice;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PagePayment extends PageDefault  {
	final static String PAGE_PAYMENT= "Page Payment";
	int row,col;
	JPanel[][] panelHolder;
	JPanel targetPanel;
	JLabel lblMovieName;
	JLabel lbMovieDate;
	JLabel lblMovieTime;
	JLabel lblMovieScreenNumber;
	JLabel lblMovieSeatCount;
	JLabel lblMoviePrice;
	
	JButton btnBack;
	JButton btnNext;
	
	PagePayment() {
		super(PAGE_PAYMENT);
		row = 3;
		col = 3;
		panelHolder = new JPanel[row][col];
		setLayout(new GridLayout(row,col));
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				panelHolder[r][c] = new JPanel();
				add(panelHolder[r][c]);
			}
		}
		targetPanel = panelHolder[1][1];
		targetPanel.setLayout(new GridLayout(8,0));
		
		lblMovieName = new JLabel();
		lbMovieDate = new JLabel();
		lblMovieTime = new JLabel();
		lblMovieScreenNumber = new JLabel();
		lblMovieSeatCount = new JLabel();
		lblMoviePrice = new JLabel();
		btnBack = new JButton("Cancel");
		btnNext = new JButton("Buy");
		
		targetPanel.add(lblMovieName);
		targetPanel.add(lbMovieDate);
		targetPanel.add(lblMovieTime);
		targetPanel.add(lblMovieScreenNumber);
		targetPanel.add(lblMovieSeatCount);
		targetPanel.add(lblMoviePrice);
		JPanel tempPanel = new JPanel(new GridLayout(0,2));
		targetPanel.add(tempPanel);
		tempPanel.add(btnBack);
		tempPanel.add(btnNext);

		btnBack.addActionListener(this);
		btnNext.addActionListener(this);
	}
	
	protected void PageLoaded() {
		int scheduleId = ((MoviePageData)pageData).getCurScheduleId();
		Schedule s = ((MoviePageData)pageData).getSchedule(scheduleId);
		int movieId = s.getMovie_id();
		Movie m = ((MoviePageData)pageData).getMovie(movieId);
		Vector<Integer> vec = ((MoviePageData)pageData).getCurSeatList();
		
		lblMovieName.setText(m.getName());
		lbMovieDate.setText(s.getDate() + "");
		lblMovieTime.setText(s.getTime() + "");
		lblMovieScreenNumber.setText(s.getScreen_id() +"관");
		lblMovieSeatCount.setText(vec.size()/2 + "석 ");
		for(int i=0; i<vec.size(); i += 2) {
			lblMovieSeatCount.setText(lblMovieSeatCount.getText() + "[" + vec.get(i) + ":" + vec.get(i+1) + "]");
		}
		int totalPrice = vec.size()/2 * s.getPrice();
		lblMoviePrice.setText(vec.size()/2 +  " X " + s.getPrice() + " = " + totalPrice);
		((MoviePageData)pageData).getCurReservationId();
	}
	protected void PageUnloaded() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			setTargetPageIndex(0);
		} else if(e.getSource() == btnNext) {
			String resId = ((MoviePageData)pageData).CreateReservation( );
			if( resId != null) {
				((MoviePageData)pageData).setCurReservationId(resId);
				setTargetPageIndex(1);
			}
		}
	}
}