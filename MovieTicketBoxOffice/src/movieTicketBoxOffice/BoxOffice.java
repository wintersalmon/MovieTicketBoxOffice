package movieTicketBoxOffice;
import java.awt.Color;
import javax.swing.*;

@SuppressWarnings("serial")
public class BoxOffice extends JFrame implements Runnable {
	
	BoxOfficePageManager myPageManager;
	Thread myThread;
	
	BoxOffice() {
		setSize(800,1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.BLUE);
		
		myPageManager = new BoxOfficePageManager();
		this.add(myPageManager);
		
		myThread = new Thread(this);
		myPageManager.bRunning = false;
		
		setVisible(true);
	}
	public void start() {
		myPageManager.bRunning = true;
		myThread.start();
	}
	public void stop() {
		myPageManager.bRunning = false;
	}
	public static void main(String[] args) {
		System.out.println("SystemStart");
		BoxOffice office = new BoxOffice();
		office.start();
	}
	@Override
	public void run() {
		while(myPageManager.bRunning) {
			myPageManager.UpdatePages();
			repaint();
		}
	}
}
