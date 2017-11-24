
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class LifeGameP extends JFrame implements MouseMotionListener{
	private final World world;
	//JButton button = new JButton ("button");
	static JButton location=new JButton();
	public LifeGameP(int rows,int columns)
	{
		world=new World(rows, columns);
		world.setBackground(Color.LIGHT_GRAY);
		new Thread(world).start();
		//add(world);
	}
	public void lunchFrame() {
		addMouseMotionListener(this);
		JPanel control=new JPanel();
		
		
		JPanel modeP=new JPanel();
		JLabel mode=new JLabel("Mode:");	
		JButton random=new JButton("Random");
		random.addActionListener(this.new RandomActionListener());
		JButton choose=new JButton("Add");
		choose.addActionListener(this.new DIYActionListener());
		JButton clean=new JButton("Kill");
		clean.addActionListener(this.new CleanActionListener());
		modeP.add(mode);modeP.add(random);modeP.add(choose);modeP.add(clean);
		
		
		JPanel playP=new JPanel();
		JLabel play=new JLabel("Play:");	
		JButton start=new JButton("Start");
		start.addActionListener(this.new StartActionListener());
		JButton pause=new JButton("Pause");
		pause.addActionListener(this.new PauseActionListener());
		JButton stop=new JButton("Stop");
		stop.addActionListener(this.new StopActionListener());
		playP.add(play);playP.add(start);playP.add(pause);playP.add(stop);
		
		
		JPanel speedP=new JPanel();
		JLabel speed=new JLabel("Speed:");	
		JButton slow=new JButton("Slow");
		slow.addActionListener(this.new SlowActionListener());
		JButton fast=new JButton("Fast");
		fast.addActionListener(this.new FastActionListener());
		JButton hyper=new JButton("Hyper");
		hyper.addActionListener(this.new HyperActionListener());
		speedP.add(speed);speedP.add(slow);speedP.add(fast);speedP.add(hyper);
		
		JPanel otherP=new JPanel();
		JLabel other=new JLabel("Other:");	
		JButton help=new JButton("Help");
		help.addActionListener(this.new HelpActionListener());	
		JButton about=new JButton("About");
		about.addActionListener(this.new AboutActionListener());
		otherP.setLayout(new FlowLayout());
		otherP.add(other);otherP.add(help);otherP.add(about);
		
		
		control.add(modeP);control.add(playP);control.add(speedP);control.add(otherP);
		control.setSize(200, 200);
		control.setLayout(new GridLayout(4,1));
		
		world.setSize(700, 470);
		control.setSize(200, 470);
		add(world);
		add(control);
		pack();
		setLayout(new FlowLayout());
		//about.addActionListener(frame.new AboutActionListener());
		setLocationRelativeTo(null);//�����ͻ�Ĭ����ʾ����Ļ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(900,470);
		
		setLocationRelativeTo(null);
		setTitle("Game of Life");
		setVisible(true);
		setResizable(false);
	}
	
	public static void main(String[]args)
	{
		LifeGameP frame=new LifeGameP(40, 50);
		frame.lunchFrame();
		
		
	}

	class RandomActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.diy=false;
			world.clean=false;
			world.setBackground(Color.LIGHT_GRAY);
			//world.setStop();
			world.setRandom();
		}
	}
	class StartActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.begintime=System.currentTimeMillis();
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setShape();
		}
	}
	class StopActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			//world.time=0;
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setStop();
		}
	}
	class PauseActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setBackground(Color.LIGHT_GRAY);
			world.diy=false;
			world.clean=false;
			world.setPause();
		}
	}
	
	class SpeedActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedSlow();
		}
	}
	class SlowActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedSlow();
		}
	}
	class FastActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedFast();
		}
	}
	class HyperActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.changeSpeedHyper();
		}
	}
	class HelpActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "����������Ϸ!!!\n������Ϸ��Ӣ����ѧ��Լ�����ζ١�������1970�귢����ϸ���Զ���\n "+"1�����һ��ϸ����Χ��3��ϸ��Ϊ�������ϸ��Ϊ��;\n"
												+"2�� ���һ��ϸ����Χ��2��ϸ��Ϊ�������ϸ��������״̬���ֲ���;\n"
												+"3�� ����������£���ϸ��Ϊ����");
		}
	}
	class AboutActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			JOptionPane.showMessageDialog(null, "��Ϸ���ߣ����ȣ��ֿ��ۣ����ͣ�ξʢ��");
		}
	}
	class CleanActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.clean=true;
			world.diy=false;
			world.setBackground(Color.orange);
		}
	}
	class DIYActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			world.setPause();
			world.diy=true;
			world.clean=false;
			world.setBackground(Color.cyan);
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.diy){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-25)/10][x/10]=1;
		world.setDiy();
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(world.clean){
		int x=e.getX();
		int y=e.getY();
		//button.setText("x:"+x+"y:"+y);
		World.pauseshape[(y-25)/10][x/10]=0;
		world.setDiy();
		}
	}
}

