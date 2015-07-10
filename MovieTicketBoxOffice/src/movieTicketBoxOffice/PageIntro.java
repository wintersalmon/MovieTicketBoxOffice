package movieTicketBoxOffice;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Date;

import javax.swing.*;

@SuppressWarnings("serial")
public class PageIntro extends PageDefault implements MouseListener {
	final static String PAGE_INTRO	= "Page Intro";
	JButton btnInput;
	
	JPanel board;
	
	//다음 패널에 넘길 데이터 - Integer로 형변환
	String parameter;
	
	PageIntro() {
		super(PAGE_INTRO);
		
		setLayout(new GridLayout(2,1));
		
		btnInput = new JButton("티켓 출력");
		btnInput.addActionListener(this);
		add(btnInput);
		
		board = new JPanel();
		JScrollPane scroll = new JScrollPane(board);
		
		addImage("1.jpg");
		addImage("2.jpg");
		addImage("3.jpg");
		addImage("4.jpg");
		addImage("5.jpg");
		addImage("6.jpg");
		
		add(scroll);
	}

	ImageIcon resizeImage(String param, ImageIcon ii) {
		int h = ii.getIconHeight();
		int w = ii.getIconWidth();
	
		int dh = getHeight() / 2 - 80;
		int dw = getWidth();
		
		double hdh = (double)h/dh;
		double wdw = (double)w/dw;
		double max = 0.0;
		
		if(hdh > wdw){
			max = hdh;
		}else{
			max = wdw;
		}
		
		Image im = ii.getImage().getScaledInstance((int)(w/max), (int)(h/max), Image.SCALE_SMOOTH);
		
		//e.getSource()의 값을 JLabel로 캐스팅하고 toString()하면 값을 가져올 수 있다.
		ImageIcon iic = new ImageIcon(im);
		iic.setDescription(param);
		return iic;
	}
	
	void addImage(String name) {
		ImageIcon ii = new ImageIcon(name);
		ii = resizeImage(name, ii);
		
		JLabel lb = new JLabel(ii);
		lb.addMouseListener(this);
		board.add(lb);
	}
	
	protected void PageLoaded() {
		((MoviePageData)pageData).setCurDate(new Date());
		Vector<Movie> movieList = ((MoviePageData)pageData).getMovieList(new Date());
		
		for(int i=0; i<movieList.size(); i++) {
			//movieList.get(i).toString() + "\n";
		}
	}
	
	protected void PageUnloaded() {
		if(parameter != null)
			((MoviePageData)pageData).setCurMovieId(Integer.parseInt(parameter));
		//while(board.getComponentCount() != 0) {
		//	board.remove(board.getComponent(0));
		//}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInput) {
			setTargetPageIndex(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel l = (JLabel)e.getSource();
		parameter = l.getIcon().toString();
		int idx = parameter.lastIndexOf('.');
		parameter = parameter.substring(0, idx);
		
		//JOptionPane.showMessageDialog(null, parameter);
		setTargetPageIndex(1);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}