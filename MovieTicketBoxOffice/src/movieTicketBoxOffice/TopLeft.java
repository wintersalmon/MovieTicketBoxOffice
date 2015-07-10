package movieTicketBoxOffice;

import java.awt.Image;

import javax.swing.*;

@SuppressWarnings("serial")
public class TopLeft extends JPanel {

	JLabel label;
	
	TopLeft() {
	}
	void addImage(String name){
		if(label != null)
			remove(label);
		
		ImageIcon ii = new ImageIcon(name);
		int h = ii.getIconHeight();
		int w = ii.getIconWidth();
	
		int dh = getHeight();
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
		label = new JLabel(iic);
		
		add(label);
		revalidate();
		repaint();
	}
}
