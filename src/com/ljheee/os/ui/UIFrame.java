package com.ljheee.os.ui;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;

import com.ljheee.os.core.CmdToolkit;
import com.ljheee.os.core.MyProcessUtil;
import com.ljheee.os.model.ProcessInfo;

/**
 * MyProcess
 * 
 * @author ljheee
 *
 */
public class UIFrame {

	private JFrame jf = null;
	private JLabel leftInfo = new JLabel("状态栏:");
	private JLabel pathInfo = new JLabel("  ");
	private JLabel timeInfo = new JLabel("  ");

	JMenuItem newProc, exitH, refreshNow, maxLarge, minLarge, aboutItem;
	JButton newProcBtn, killProcBtn;
	private JMenuItem localTaskmgr;

	JMenu showApp, showProc, showServer, showNet;

	ActionHandle handle = new ActionHandle();
	MyMenuHandler menuHandle = new MyMenuHandler();

	JTable table = null;
	JPanel showPanel = null;

	public UIFrame() {

		jf = new JFrame("Task Manager1.0");
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);

		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(new GridLayout(2, 1));

		JRootPane rootPane = new JRootPane(); // 此panel，添加菜单
		rootPane.setBackground(Color.gray);
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("文件(F)");
		JMenu editMenu = new JMenu("选项(O)");
		JMenu viewMenu = new JMenu("查看(V)");
		JMenu winMeun = new JMenu("窗口(W)");
		JMenu helpMenu = new JMenu("帮助(H)");

		rootPane.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(viewMenu);
		menuBar.add(winMeun);
		menuBar.add(helpMenu);

		refreshNow = new JMenuItem("立即刷新(F5)");
		localTaskmgr = new JMenuItem("启动本地任务管理器");
		refreshNow.addActionListener(handle);
		localTaskmgr.addActionListener(handle);
		viewMenu.add(refreshNow);
		viewMenu.add(localTaskmgr);

		maxLarge = new JMenuItem("最大化");
		minLarge = new JMenuItem("最小化");
		maxLarge.addActionListener(handle);
		minLarge.addActionListener(handle);
		winMeun.add(maxLarge);
		winMeun.add(minLarge);

		newProc = new JMenuItem("新建任务（运行）");
		exitH = new JMenuItem("退出");
		newProc.addActionListener(handle);
		exitH.addActionListener(handle);

		aboutItem = new JMenuItem("关于任务管理器");
		helpMenu.add(aboutItem);
		aboutItem.addActionListener(handle);

		// 给菜单 添加菜单项
		fileMenu.add(newProc);
		fileMenu.add(exitH);

		JRootPane panel2 = new JRootPane();
		JMenuBar menuBar2 = new JMenuBar();

		showApp = new JMenu("应用进程");
		showProc = new JMenu("进程");
		showServer = new JMenu("服务");
		showNet = new JMenu("网络");

		menuBar2.add(showApp);
		menuBar2.add(showProc);
		menuBar2.add(showServer);
		menuBar2.add(showNet);

		// showProc.addActionListener(handle);
		showApp.addMenuListener(menuHandle);
		showProc.addMenuListener(menuHandle);
		showServer.addMenuListener(menuHandle);
		showNet.addMenuListener(menuHandle);

		panel2.setJMenuBar(menuBar2);

		topJPanel.add(rootPane);// 工具panel :文件、编辑、查看
		topJPanel.add(panel2);// 进程、服务、网络
		jf.getContentPane().add(topJPanel, BorderLayout.NORTH);

		// center
		JPanel centerP = new JPanel();
		jf.getContentPane().add(centerP, BorderLayout.CENTER);

		showPanel = new JPanel(new BorderLayout());//

		// 新建任务
		newProcBtn = new JButton("\u65B0\u4EFB\u52A1");
		newProcBtn.addActionListener(handle);
		// 结束任务
		killProcBtn = new JButton("\u7ED3\u675F\u4EFB\u52A1");
		killProcBtn.addActionListener(handle);

		GroupLayout gl_centerP = new GroupLayout(centerP);
		gl_centerP.setHorizontalGroup(gl_centerP.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_centerP.createSequentialGroup().addGap(314).addComponent(killProcBtn)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(newProcBtn).addContainerGap())
				.addComponent(showPanel, GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE));
		gl_centerP
				.setVerticalGroup(
						gl_centerP.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_centerP.createSequentialGroup()
										.addComponent(showPanel, GroupLayout.PREFERRED_SIZE, 352,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(gl_centerP.createParallelGroup(Alignment.LEADING)
												.addComponent(killProcBtn).addComponent(newProcBtn))
										.addContainerGap(15, Short.MAX_VALUE)));
		centerP.setLayout(gl_centerP);

		// south--状态栏
		JToolBar bottomToolBar = new JToolBar();
		bottomToolBar.setFloatable(false);// 设置JToolBar不可拖动

		bottomToolBar.setPreferredSize(new Dimension(jf.getWidth(), 20));
		bottomToolBar.add(leftInfo);

		// bottomToolBar.addSeparator(); //此方法添加分隔符 无效
		JSeparator jsSeparator = new JSeparator(SwingConstants.VERTICAL);
		bottomToolBar.add(jsSeparator);// 添加分隔符

		leftInfo.setPreferredSize(new Dimension(200, 20));
		leftInfo.setHorizontalTextPosition(SwingConstants.LEFT);

		bottomToolBar.add(pathInfo);
		pathInfo.setHorizontalTextPosition(SwingConstants.LEFT);
		bottomToolBar.add(new JSeparator(SwingConstants.VERTICAL));// 添加分隔符

		bottomToolBar.add(timeInfo);
		timeInfo.setPreferredSize(new Dimension(70, 20));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		timeInfo.setText(sdf.format(new Date()));

		jf.getContentPane().add(bottomToolBar, BorderLayout.SOUTH);// 下面--放“状态栏”

		jf.setVisible(true);
	}

	/**
	 * 主界面菜单--事件监听
	 * @author ljheee
	 *
	 */
	class ActionHandle implements ActionListener {

		Thread t1;

		@Override
		public void actionPerformed(ActionEvent e) {// new process
			if (e.getSource() == newProc || e.getSource() == newProcBtn) { // new
				new NewProc();
			}

			if (e.getSource() == exitH) {// exit application
				System.exit(0);
			}

			if (e.getSource() == aboutItem) {// show about
				JOptionPane.showMessageDialog(jf, "@Author:ljheee \n 2016");
			}

			if (e.getSource() == refreshNow) {// viewItem refresh
				jf.repaint();
			}

			if (e.getSource() == localTaskmgr) {// boot local taskmgr,with a new
												// thread
				t1 = new Thread() {
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

			if (e.getSource() == maxLarge) {// max size
				jf.setExtendedState(Frame.MAXIMIZED_BOTH);
			}

			if (e.getSource() == minLarge) {// min size
				jf.setExtendedState(Frame.ICONIFIED);
			}
			if (e.getSource() == killProcBtn) {// kill the process
				new KillProc();
			}

		}
	}

	/**
	 * Menu事件监听
	 * @author ljheee
	 *
	 */
	class MyMenuHandler implements MenuListener {

		@Override
		public void menuCanceled(MenuEvent e) {
		}
		@Override
		public void menuDeselected(MenuEvent e) {
		}

		@Override
		public void menuSelected(MenuEvent e) {

			if (e.getSource() == showProc) {//显示进程
				Map map = new MyProcessUtil().processList2();
				String[][] data = new String[map.size()][5];
				String[] title = { "映像名称", "PID", "会话名", "会话", "内存" };

				leftInfo.setText("进程数:" + map.size());

				// init data
				for (int i = 0; i < map.size(); i++) {
					ProcessInfo pinfo = (ProcessInfo) map.get(i);
					if (pinfo == null)
						continue;
					data[i][0] = pinfo.getName();
					data[i][1] = pinfo.getPid();
					data[i][2] = pinfo.getSessionName();
					data[i][3] = pinfo.getSessionNum();
					data[i][4] = pinfo.getMemory() + "K";
				}

				table = new JTable();
				DefaultTableModel model = new DefaultTableModel(data, title);
				table.setModel(model);
				// table.setEnabled(false);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
				showPanel.add(new JScrollPane(table));
				jf.paint(jf.getGraphics());

				new Thread(new Runnable() {//右击选择一列，结束进程
					String killP = null;
					int selectRow = 0;
					
					
					@Override
					public void run() {

						JPopupMenu popup = new JPopupMenu();
						JMenuItem killproc = new JMenuItem("结束进程");
						killproc.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								String input = "taskkill /f /im  " + killP;
								try {
									CmdToolkit.readConsole(input, true);// 执行结束进程
									model.removeRow(selectRow);
								} catch (IOException e1) {
									e1.printStackTrace();
									JOptionPane.showMessageDialog(jf, "异常" + e1.getMessage());
								}
							}
						});

						popup.add(new JMenuItem("打开文件位置"));
						popup.add(killproc);
						popup.add("属性");

						table.addMouseListener(new MouseAdapter() {

							@Override
							public void mouseReleased(MouseEvent e) {
								// 判断是否是鼠标右击
								if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0 && !e.isControlDown()
										&& !e.isShiftDown()) {
									selectRow = table.getSelectedRow();
									killP = table.getModel().getValueAt(table.getSelectedRow(), 0) + "";
									popup.show(table, e.getX(), e.getY());
								}
							}
						});
					}
				}).start();
				jf.paint(jf.getGraphics());
			} else {
				JOptionPane.showMessageDialog(null, "Wait \n 有待开发");
			}
		}
		

	}
	
	

	public static void main(String[] args) {
		new UIFrame();
	}
}
