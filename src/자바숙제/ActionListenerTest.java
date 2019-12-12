package 자바숙제;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;

 public class ActionListenerTest extends JFrame implements ActionListener,Runnable{
	JButton button1 = new JButton("시작");
	JButton button2 = new JButton("일시정지");
	JButton button3 = new JButton("초기화");
	
	static ActionListenerTest window;
	
	long start, end;
	boolean flag = true;
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
	
	public ActionListenerTest() {
		setTitle("StopWatch");
		setBounds(800, 100, 300, 400);
		
		GridLayout grid = new GridLayout(3,1);
		setLayout(grid);
		
		add(button1);
		add(button2);
		add(button3);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		
		button2.setEnabled(false);
		button3.setEnabled(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Thread thread = new Thread(window);
		switch(e.getActionCommand()) {
		case "시작": case "재시작":
			flag = true;
			thread.start();
			button1.setEnabled(false);
			button2.setEnabled(true);
			button3.setEnabled(false);
			break;
		case "일시정지" :
			flag = false;
			button1.setEnabled(true);
			button2.setEnabled(false);
			button3.setEnabled(true);
			button1.setText("재시작");
			break;
			
		case "초기화":
			flag = false;
			start = 0;
			setTitle(sdf.format(-32400000));
			button1.setEnabled(true);
			button2.setEnabled(false);
			button3.setEnabled(false);
			
		}
		
	}
	public static void main(String[] args) {
		window = new ActionListenerTest();
	}
	@Override
	public void run() {
		if(start == 0) {	
			start = System.currentTimeMillis();
			end = start;
		}
		while(true) {
			long time = end++ -start - 32400000;
			setTitle(sdf.format(time));
			
			if(!flag) {
				break;
			}
			try {
				Thread.sleep(1);				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	  

}

