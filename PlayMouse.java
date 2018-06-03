package mymouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayMouse extends JFrame implements Runnable {

	private int num = 0;
	private JLabel back;
	private JLabel[] mouses;
	private JLabel df;
	private ImageIcon imgMouse;
	
	// ��ʼ��-���沼��
	public PlayMouse() {
		setResizable(false);
		setTitle("�����");
		setIconImage(new ImageIcon("tubiao.png").getImage());
		setLayout(null);
		setBounds(500, 200, 849, 640);
	
		// �������ͼ��
		setCursor(Toolkit.getDefaultToolkit().
					createCustomCursor(Toolkit.getDefaultToolkit().getImage("cz.png"), new Point(), "Cz"));
		
		// ���ñ���ͼ
		back = new JLabel();
		ImageIcon icon = new ImageIcon("back.png");
		back.setIcon(icon);
		back.setBounds(0, 0, 849, 640);
		
		// ���õ���ͼƬ
		imgMouse = new ImageIcon("ms.png");
		imgMouse.setImage(imgMouse.getImage().getScaledInstance(100, 122, Image.SCALE_DEFAULT));
		mouses = new JLabel[9];
		for (int i = 0; i < mouses.length; i++) {
			mouses[i] = new JLabel();
			mouses[i].setSize(100, 122);
			
			// ��ӵ���¼�
			mouses[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					Object obj = e.getSource();
					if( obj instanceof JLabel) {
						JLabel label = (JLabel)obj;
						if( label .getIcon() != null ) {
							repaint();
							num++;
							label.setIcon(null);
							df.setText("���ĵ÷���: "+ num+ "��");
						}
					}
				}
			});
			add(mouses[i]);
		}
		
		// ���õ���λ��
		mouses[0].setLocation(205, 92);
		mouses[1].setLocation(372, 92);
		mouses[2].setLocation(542, 92);

		mouses[3].setLocation(175, 217);
		mouses[4].setLocation(370, 217);
		mouses[5].setLocation(565, 217);

		mouses[6].setLocation(150, 357);
		mouses[7].setLocation(370, 357);
		mouses[8].setLocation(588, 357);
		
		// ���õ÷����
		df = new JLabel();
		df.setText("���ĵ÷��ǣ� ��");
		df.setFont(new Font("΢���ź�", Font.BOLD, 25));
		df.setForeground(Color.blue);
		df.setBounds(542, 15, 300, 50);
		add(df);
		
		add(back);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		PlayMouse p = new PlayMouse();
		Thread t = new Thread(p);
		t.start();
	}
	
	// ���߳�
	public void run() {
		while(true) {
			try {
				Thread.sleep(500);   				// ������ʾ���ʱ��
				int idx = (int)(Math.random()*9);
				if( mouses[idx].getIcon() == null ) {
					mouses[idx].setIcon(imgMouse);
					Thread.sleep(800);  			// �������ͣ��ʱ��
					if( mouses[idx].isShowing() ) {
						mouses[idx].setIcon(null);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
