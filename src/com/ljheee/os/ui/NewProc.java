package com.ljheee.os.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;

import com.ljheee.os.core.CmdToolkit;

public class NewProc extends JFrame{

	JFrame jf = null;
	JTextField inputC = null;
	JButton okBtn,cancleBtn;
	String input;
	
	public NewProc(){
	
		jf = new JFrame("�½�����");
		jf.setSize(200,100);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		
		inputC = new JTextField(20);
		okBtn = new JButton("ȷ��");
		cancleBtn = new JButton("ȡ��");
		
		JPanel btnPanel = new JPanel();
		btnPanel.add(okBtn);
		btnPanel.add(cancleBtn);
		
		jf.add(inputC,BorderLayout.CENTER);
		jf.add(btnPanel,BorderLayout.SOUTH);
		
		
		//
		okBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				input = inputC.getText().trim();
				if(input==null||input.equals("")){
					JOptionPane.showMessageDialog(null, "����Ϊ��");
					return;
				}
				Thread t = new Thread(){
					@Override
					public void run() {
						try {
							CmdToolkit.readConsole(input, true);
						} catch (IOException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(jf, "�쳣"+e1.getMessage());
						}finally{}
						
						}
				};
				t.start();
				jf.dispose();//�߳��������ٹرյ�ǰ���JFrame
			}
		});
		
		
		//
		cancleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					jf.dispose();
				} catch (Throwable e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		jf.setVisible(true);
	}
	
	public static void main(String[] args) {
		new NewProc();
	}
	
}
