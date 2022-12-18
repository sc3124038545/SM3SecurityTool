package SM9Toolgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class SM9SecureAnalysisTool {

	private JFrame frmSmsecureanalysistool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SM9SecureAnalysisTool window = new SM9SecureAnalysisTool();
					window.frmSmsecureanalysistool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SM9SecureAnalysisTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSmsecureanalysistool = new JFrame();
		frmSmsecureanalysistool.setTitle("SM9\u5B89\u5168\u5206\u6790\u5DE5\u5177");
		frmSmsecureanalysistool.setBounds(100, 100, 450, 198);
		frmSmsecureanalysistool.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmSmsecureanalysistool.getContentPane().setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u5DF2\u77E5\u5411\u91CF\u5BF9\u6BD4\u6D4B\u8BD5");
		rdbtnNewRadioButton.setBounds(17, 46, 139, 23);
		frmSmsecureanalysistool.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton radioButton = new JRadioButton("\u5B89\u5168\u6027\u6D4B\u8BD5");
		radioButton.setBounds(281, 46, 121, 23);
		frmSmsecureanalysistool.getContentPane().add(radioButton);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("\u4E00\u81F4\u6027\u6D4B\u8BD5");
		rdbtnNewRadioButton_4.setBounds(158, 46, 121, 23);
		frmSmsecureanalysistool.getContentPane().add(rdbtnNewRadioButton_4);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_4);
		group.add(radioButton);
		
		JButton button = new JButton("\u5F00\u59CB\u6D4B\u8BD5");
		button.setBackground(Color.LIGHT_GRAY);
		button.setIcon(null);
		button.setForeground(Color.BLACK);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					SM9TestGui sm9TestGui=new SM9TestGui();
					sm9TestGui.setVisible(true);
				}
				else if(radioButton.isSelected()) {
					SM9SecurityTest securityTest=new SM9SecurityTest();
					securityTest.setVisible(true);
				}
				else if(rdbtnNewRadioButton_4.isSelected()) {
					SignatureDataTest dataTest=new SignatureDataTest();
					dataTest.setVisible(true);
				}
			}
		});
		button.setBounds(158, 98, 96, 30);
		frmSmsecureanalysistool.getContentPane().add(button);
		
	}
	public void showtree() {
	}
}
