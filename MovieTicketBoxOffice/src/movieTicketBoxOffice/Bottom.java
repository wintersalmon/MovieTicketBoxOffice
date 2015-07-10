package movieTicketBoxOffice;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class Bottom extends JPanel {
	
	JPanel bot;
	JButton cancle, ok;
	
	Bottom() {
		
		setLayout(new BorderLayout());
		
		bot = new JPanel();
		
		add(bot, BorderLayout.SOUTH);
	}
	
	@Override
	public synchronized void addMouseListener(MouseListener l) {
		// TODO Auto-generated method stub
		cancle.addMouseListener(l);
		ok.addMouseListener(l);
	}
	
	public void addPanel(JPanel p) {
		add(p, BorderLayout.CENTER);
	}
	
	public void addButton(JButton b) {
		bot.add(b);
	}
}
