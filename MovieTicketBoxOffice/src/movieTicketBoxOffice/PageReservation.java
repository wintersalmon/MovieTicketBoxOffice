package movieTicketBoxOffice;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class PageReservation extends PageDefault {
	final static String PAGE_RESERVATION = "Page Reservation";
	JButton btnBack;
	JButton btnNext;
	
	JPanel top, p;
	TopLeft topleft;
	TopRight topright;
	Bottom bottom;
	
	JCheckBox[][] cb;
	int seatRow = 20;
	int seatCol = 13;
	
	int movieId;
	Vector<Schedule> scheduleList;
	
	public Vector<Integer> vec;
	
	PageReservation() {
		super(PAGE_RESERVATION);
		
		setLayout(new GridLayout(2,1));
		
		top = new JPanel(new GridLayout(1,2));
		topleft = new TopLeft();
		top.add(topleft);
		topright = new TopRight();
		//테이블 데이터 추가 함수
		
		
		top.add(topright);
		topright.addMouseListener(new TopRightClickAdapter());
		add(top);
		
		bottom = new Bottom();
		
		btnBack = new JButton("뒤로");
		btnNext = new JButton("확인");
		
		btnBack.addActionListener(this);
		btnNext.addActionListener(this);
		
		bottom.addButton(btnBack);
		bottom.addButton(btnNext);
		
		vec = new Vector<Integer>();
		
		add(bottom);
	}
	
	public void PageLoaded() {
		movieId =  ((MoviePageData)pageData).getCurMovieId();
		scheduleList = ((MoviePageData)pageData).getScheduleList(movieId);
		for(int i=0; i<scheduleList.size(); i++) {
			Schedule s = scheduleList.get(i);
			String movieName = ((MoviePageData)pageData).getMovieName(s.getMovie_id());
			int totalCount = ((MoviePageData)pageData).getTotalSeatCount(s.getScreen_id());
			int reservedCount = ((MoviePageData)pageData).getReservedSeatCount(s.getId());
			topright.addRow( s.getScreen_id() + "", s.getId() + "", movieName, s.getTime() + "", totalCount + "", totalCount - reservedCount + "");
		}
	}
	public void PageUnloaded() {
		String temp = getRow(1);
		int scheduleId = 0 ;
		if(!temp.equals(""))
			scheduleId = Integer.parseInt(temp);
		((MoviePageData)pageData).setCurScheduleId(scheduleId);
		((MoviePageData)pageData).setCurSeatList(vec);
		topright.removeAllRow();
		if(p != null){
			for(int i = 0; i < p.getComponentCount(); i++){
				p.remove(p.getComponent(i));
			}
			
			bottom.remove(p);
			p = null;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnBack) {
			setTargetPageIndex(0);
		} else if(e.getSource() == btnNext) {
			boolean f = false;
			
			String mv = getRow(0);
			
			if(!mv.equals("")){
				vec.removeAllElements();
				for(int i = 0; i < seatRow; i++){
					for(int j = 0; j < seatCol; j++){
						if(cb[i][j].isSelected()){
							f = true;
							vec.addElement(i);
							vec.addElement(j);
						}
					}
				}
			}
			
			if(mv.equals("")){
				JOptionPane.showConfirmDialog(null, "영화를 선택하세요.");
			}else if(!f){
				JOptionPane.showConfirmDialog(null, "좌석을 선택하세요.");
			}else{
				
				System.out.print(getRow(0));
				
				setTargetPageIndex(1);
			}
		}
	}
	
	//테이블 요소값 가져오기
	String getRow(int col) {
		if(topright.table.getSelectedRow() != -1){
			if(0 <= col && col < topright.table.getColumnCount())
				return topright.table.getValueAt(topright.table.getSelectedRow(), col).toString();
			else
				return "";
		}else{
			return "";
		}
	}
	
	//좌석 예약 가능 여부 설정
	void checkEnabled(int row, int col, boolean b) {
		if(0 <= row && row <= seatRow && 0 <= col && col <= seatCol){
			cb[row][col].setEnabled(b);
		}
	}
	
	class TopRightClickAdapter implements MouseListener {

		TopRightClickAdapter() {
			
		}
		
		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			Screen s = ((MoviePageData)pageData).getScreen(Integer.parseInt( getRow(0) ));
			seatRow = s.getRow();
			seatCol = s.getCol();
			System.out.println(seatRow + "," + seatCol);
			
			//TopLeft
			//영화 제목 클릭시 이벤트 할당
			topleft.addImage(((MoviePageData)pageData).getCurMovieId() + ".jpg");
		
			if(p != null){
				for(int i = 0; i < p.getComponentCount(); i++){
					p.remove(p.getComponent(i));
				}
				
				bottom.remove(p);
				p = null;
			}
			p = new JPanel();
			bottom.addPanel(p);

			p.setLayout(new GridLayout(seatRow+1, seatCol+1));
			
			cb = new JCheckBox[seatRow][seatCol];
			
			p.add(new JLabel(""));
			for(int i = 0; i < seatCol; i++){
				p.add(new JLabel(i + ""));
			}
			
			for(int i = 0; i < seatRow; i++){
				cb[i] = new JCheckBox[seatCol];
				
				for(int j = 0; j < seatCol + 1; j++){
					if(j == 0){
						p.add(new JLabel(i + ""));
					}else{
						cb[i][j-1] = new JCheckBox();
						p.add(cb[i][j-1]);
					}
				}
			}
			ScheduleReservation info = ((MoviePageData)pageData).getScheduleReservationInfo( Integer.parseInt(getRow(1)) );
			for(int i=0; i<info.getSeatCount(); i++) {
				checkEnabled(info.getRow()[i], info.getCol()[i], false);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}