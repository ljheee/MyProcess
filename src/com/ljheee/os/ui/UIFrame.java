package com.ljheee.os.ui;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import com.ljheee.os.core.CmdToolkit;
import com.ljheee.os.core.MyProcessUtil;
import com.ljheee.os.model.ProcessInfo;

/**
 *MyProcess
 * @author ljheee
 *
 */
public class UIFrame {

	private JFrame jf = null;
	private JLabel leftInfo = new JLabel("״̬��:");
	private JLabel pathInfo = new JLabel("  ");
	private JLabel timeInfo = new JLabel("  ");
	
	JMenuItem newProc,exitH, refreshNow,maxLarge,minLarge,aboutItem;
	JButton newProcBtn,killProcBtn;
	private JMenuItem localTaskmgr;
	
	
	JMenu showApp,showProc,showServer,showNet;
	
	ActionHandle handle = new ActionHandle();
	MyMenuHandler menuHandle = new MyMenuHandler();
	
	
	JTable table  = null;
	JPanel showPanel = null;
	
	public UIFrame(){
	
		jf = new JFrame("Task Manager1.0");
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		
		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(new GridLayout(2, 1));
		
		JRootPane rootPane = new JRootPane(); //��panel����Ӳ˵�
		rootPane.setBackground(Color.gray);
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("�ļ�(F)"); 
		JMenu editMenu = new JMenu("ѡ��(O)"); 
		JMenu viewMenu = new JMenu("�鿴(V)"); 
		JMenu winMeun = new JMenu("����(W)"); 
		JMenu helpMenu = new JMenu("����(H)"); 
		
		rootPane.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(winMeun);
		menuBar.add(helpMenu);
		
		refreshNow = new JMenuItem("����ˢ��(F5)");
		localTaskmgr = new JMenuItem("�����������������");
		refreshNow.addActionListener(handle);
		localTaskmgr.addActionListener(handle);
		viewMenu.add(refreshNow);
		viewMenu.add(localTaskmgr);
		
		maxLarge = new JMenuItem("���");
		minLarge = new JMenuItem("��С��");
		maxLarge.addActionListener(handle);
		minLarge.addActionListener(handle);
		winMeun.add(maxLarge);
		winMeun.add(minLarge);
		
		
		
		newProc = new JMenuItem("�½��������У�");
		exitH = new JMenuItem("�˳�");
		newProc.addActionListener(handle);
		exitH.addActionListener(handle);
		
		aboutItem = new JMenuItem("�������������");
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(handle);
		
		//���˵�  ��Ӳ˵���
		fileMenu.add(newProc);
		fileMenu.add(exitH);
		
		JRootPane panel2 = new JRootPane();
		JMenuBar menuBar2 = new JMenuBar();
		
		showApp = new JMenu("Ӧ�ý���");
		showProc = new JMenu("����");
		showServer = new JMenu("����");
		showNet = new JMenu("����");
		
		menuBar2.add(showApp);
		menuBar2.add(showProc);
		menuBar2.add(showServer);
		menuBar2.add(showNet);
		
//		showProc.addActionListener(handle);
		showApp.addMenuListener(menuHandle);
		showProc.addMenuListener(menuHandle);
		showServer.addMenuListener(menuHandle);
		showNet.addMenuListener(menuHandle);
		
		panel2.setJMenuBar(menuBar2);
		
		
		topJPanel.add(rootPane);//����panel :�ļ����༭���鿴
		topJPanel.add(panel2);//���̡���������
		jf.getContentPane().add(topJPanel,BorderLayout.NORTH);
		
		//center
		JPanel centerP = new JPanel();
		jf.getContentPane().add(centerP,BorderLayout.CENTER);
		
		showPanel = new JPanel(new BorderLayout());//
		
		 
		
		//������
		newProcBtn = new JButton("\u65B0\u4EFB\u52A1");
		newProcBtn.addActionListener(handle);
		//��������
		killProcBtn = new JButton("\u7ED3\u675F\u4EFB\u52A1");
		killProcBtn.addActionListener(handle);
		
		GroupLayout gl_centerP = new GroupLayout(centerP);
		gl_centerP.setHorizontalGroup(
			gl_centerP.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_centerP.createSequentialGroup()
					.addGap(314)
					.addComponent(killProcBtn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(newProcBtn)
					.addContainerGap())
				.addComponent(showPanel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
		);
		gl_centerP.setVerticalGroup(
			gl_centerP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_centerP.createSequentialGroup()
					.addComponent(showPanel, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_centerP.createParallelGroup(Alignment.LEADING)
						.addComponent(killProcBtn)
						.addComponent(newProcBtn))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		centerP.setLayout(gl_centerP);
		
		
		
		
		
		
		//south--״̬��
		JToolBar  bottomToolBar = new JToolBar();
		bottomToolBar.setFloatable(false);//����JToolBar�����϶�
				
		bottomToolBar.setPreferredSize(new Dimension(jf.getWidth(), 20));
		bottomToolBar.add(leftInfo);
				
//		bottomToolBar.addSeparator(); //�˷�����ӷָ���  ��Ч
		JSeparator  jsSeparator = new JSeparator(SwingConstants.VERTICAL);
		bottomToolBar.add(jsSeparator);//��ӷָ���
				
		leftInfo.setPreferredSize(new Dimension(200, 20));
		leftInfo.setHorizontalTextPosition(SwingConstants.LEFT);
				
		bottomToolBar.add(pathInfo);
		pathInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		bottomToolBar.add(new JSeparator(SwingConstants.VERTICAL));//��ӷָ���
				
		bottomToolBar.add(timeInfo);
		timeInfo.setPreferredSize(new Dimension(70, 20));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		timeInfo.setText(sdf.format(new Date()));
				
		jf.getContentPane().add(bottomToolBar, BorderLayout.SOUTH);//����--�š�״̬����
		
		
		
		jf.setVisible(true);
	}
	
	
	/**
	 * 
	 * @author ljheee
	 *
	 */
	class ActionHandle implements ActionListener{
		
		Thread t1;

		@Override
		public void actionPerformed(ActionEvent e) {// new process
			if(e.getSource()==newProc||e.getSource()==newProcBtn){  //new Process
				new NewProc();
			}
			
			if(e.getSource()==exitH){//exit application
				System.exit(0);
			}
			
			if(e.getSource()==aboutItem){//show about
				JOptionPane.showMessageDialog(jf, "@Author:ljheee \n 2016");
			}
			
			if(e.getSource()==refreshNow){//viewItem  refresh
				jf.repaint();   
			}
			
			if(e.getSource()==localTaskmgr){//boot local taskmgr,with a new thread
					t1 = new Thread(){
						@Override
						public void run() {
							try {
								CmdToolkit.readConsole("taskmgr", true);
							} catch (IOException e) {
								e.printStackTrace();
							}
						};
					};
					t1.start();
			}
			
			if(e.getSource()==maxLarge){//max size
				jf.setExtendedState( Frame.MAXIMIZED_BOTH ); 
			}
			
			if(e.getSource()==minLarge){//min size
				jf.setExtendedState( Frame.ICONIFIED ); 
			}
			if(e.getSource()==killProcBtn){//kill the process
				new KillProc(); 
			}
			
			
		}
	}
	
	
	class MyMenuHandler implements MenuListener{

		@Override
		public void menuCanceled(MenuEvent e) {}

		@Override
		public void menuDeselected(MenuEvent e) {}

		@Override
		public void menuSelected(MenuEvent e) {
			
			if(e.getSource()==showProc){
				Map map =  MyProcessUtil.processList2();
				String[][] data = new String[map.size()][5];
				String[] title = {"ӳ������","PID","�Ự��","�Ự","�ڴ�"};
				
				
				leftInfo.setText("������:"+map.size());
				//clear
				
				
				//init data
				for (int i = 0; i < map.size(); i++) {
					ProcessInfo pinfo = (ProcessInfo) map.get(i);
					if(pinfo==null)   continue;
 					data[i][0] = pinfo.getName();
					data[i][1] = pinfo.getPid();
					data[i][2] = pinfo.getSessionName();
					data[i][3] = pinfo.getSessionNum();
					data[i][4] = pinfo.getMemory()+"K";
				}
				
				table  = new JTable(data, title);
				table.setEnabled(false);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS );
				showPanel.add(new JScrollPane(table));
				jf.paint(jf.getGraphics());
			}else{
				JOptionPane.showMessageDialog(null, "Wait");
			}
		}
		
	}
	
	public static void main(String[] args) {
		new UIFrame();
	}
}
