package movieTicketBoxOffice;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PageInput extends PageDefault {
	final static String PAGE_INPUT	= "Page Input";
	
	int row,col;
	JPanel[][] panelHolder;
	JPanel targetPanel;
	
	JLabel lblReservationId;
	JTextField tfReservationId;
	
	JButton btnBack;
	JButton btnNext;
	
	PageInput() {
		super(PAGE_INPUT);
		row = 7;
		col = 7;
		panelHolder = new JPanel[row][col];
		setLayout(new GridLayout(row,col));
		for(int r=0; r<row; r++) {
			for(int c=0; c<col; c++) {
				panelHolder[r][c] = new JPanel();
				add(panelHolder[r][c]);
			}
		}
		
		targetPanel = panelHolder[row/2][col/2];
		targetPanel.setLayout(new GridLayout(4,0));
		
		lblReservationId = new JLabel("예매번호");
		tfReservationId = new JTextField();
		btnBack = new JButton("Back");
		btnNext = new JButton("Next");
		
		targetPanel.add(lblReservationId);
		targetPanel.add(tfReservationId);
		targetPanel.add(btnBack);
		targetPanel.add(btnNext);		
		
		btnBack.addActionListener(this);
		btnNext.addActionListener(this);
	}
	
	protected void PageLoaded() {
		
	}
	protected void PageUnloaded() {
		String resId = tfReservationId.getText();
		if(resId != null && !resId.equals("")) {
			((MoviePageData)pageData).setCurReservationId(resId);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			setTargetPageIndex(0);
		} else if(e.getSource() == btnNext) {
			setTargetPageIndex(1);
		}
	}
}