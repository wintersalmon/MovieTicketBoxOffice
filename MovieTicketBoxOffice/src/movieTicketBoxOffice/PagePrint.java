package movieTicketBoxOffice;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PagePrint extends PageDefault {
	final static String PAGE_PRINT	= "Page Print";

	int row,col;
	JPanel[][] panelHolder;
	JPanel targetPanel;
	
	JLabel lblReservationId;
	JLabel lbMovieName;
	JLabel lblMovieDate;
	JLabel lblMovieTime;
	JLabel lblMovieScreenNumber;
	JLabel lblMovieSeatCount;
	JButton btnOkay;

	PagePrint() {
		super(PAGE_PRINT);

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
		targetPanel.setLayout(new GridLayout(7,0));
		
		lblReservationId = new JLabel();
		lbMovieName = new JLabel();
		lblMovieDate = new JLabel();
		lblMovieTime = new JLabel();
		lblMovieScreenNumber = new JLabel();
		lblMovieSeatCount = new JLabel();
		btnOkay = new JButton("OKAY");
		
		targetPanel.add(lblReservationId);
		targetPanel.add(lbMovieName);
		targetPanel.add(lblMovieDate);
		targetPanel.add(lblMovieTime);		
		targetPanel.add(lblMovieScreenNumber);
		targetPanel.add(lblMovieSeatCount);
		targetPanel.add(btnOkay);

		

		btnOkay.addActionListener(this);
	}
	
	protected void PageLoaded() {
		
		String reservation_id =  ((MoviePageData)pageData).getCurReservationId();
		if(reservation_id == null || reservation_id.equals("")) {
			return;
		}
		Reservation reserve = ((MoviePageData)pageData).getReservation(reservation_id);
		if(reserve == null) {
			return;
		}
		
		Schedule schedule = ((MoviePageData)pageData).getSchedule(reserve.getSchedule_id());
		Movie movie = ((MoviePageData)pageData).getMovie(schedule.getMovie_id());
		
		lblReservationId.setText(reservation_id + "");
		lbMovieName.setText(movie.getName());
		lblMovieDate.setText(schedule.getDate() +"");
		lblMovieTime.setText(schedule.getTime() +"");
		lblMovieScreenNumber.setText(schedule.getScreen_id() + "T");
		lblMovieSeatCount.setText(reserve.getSeatCount() + "C");
		for(int i=0; i<reserve.getSeatCount(); i++) {
			lblMovieSeatCount.setText(lblMovieSeatCount.getText() + "[" + reserve.getRow()[i] + ":" + reserve.getCol()[i] + "]");
		}
	}
	protected void PageUnloaded() {
		 ((MoviePageData)pageData).reset();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnOkay) {
			
			setTargetPageIndex(0);
		}
	}
}