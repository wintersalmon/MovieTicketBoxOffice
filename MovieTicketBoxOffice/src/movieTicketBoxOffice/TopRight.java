package movieTicketBoxOffice;

import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class TopRight extends JScrollPane {
	//테이블 상단 이름
	String[] colNames = new String[]{"상영관", "번호","영화","시간","총좌석","잔여좌석"};

	JTable table;
	DefaultTableModel model;

	TopRight() {
		table = new JTable();
		
		model = new DefaultTableModel(null, colNames){
			public boolean isCellEditable(int row, int col){
				return false;
			}
		};
		table.setModel(model);
		//table.addMouseListener(new TopRightClickAdapter());
		this.setViewportView(table);
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		// TODO Auto-generated method stub
		table.addMouseListener(l);
	}
	
	
	//좌측상단 테이블에 데이터 추가하는 함수, 데이터들은 필요에 따라 변경 가능
	//위쪽 colNames의 이름과 갯수만 맞추면 가능
	void addRow(String id,String mid, String movieName, String time, String total, String can) {
		//model.getRowCount()
		model.addRow(new Object[]{id, mid, movieName, time, total, can});
	}
	void removeAllRow() {
		while( model.getRowCount() != 0) {
			model.removeRow(0);
		}
	}
	
}
