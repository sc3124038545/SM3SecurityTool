package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SM9TestGui extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SM9TestGui frame = new SM9TestGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SM9TestGui() {
		setTitle("\u5DF2\u77E5\u5411\u91CF\u5BF9\u6BD4\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 430, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("\u7B7E\u540D\u4E0E\u9A8C\u8BC1\u6D4B\u8BD5");
		rdbtnNewRadioButton.setBounds(54, 45, 141, 23);
		contentPane.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("\u5BC6\u94A5\u4EA4\u6362\u6D4B\u8BD5");
		rdbtnNewRadioButton_1.setBounds(227, 45, 127, 23);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\u5C01\u88C5\u4E0E\u89E3\u5C01\u6D4B\u8BD5");
		rdbtnNewRadioButton_2.setBounds(54, 108, 121, 23);
		contentPane.add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("\u52A0\u5BC6\u4E0E\u89E3\u5BC6\u6D4B\u8BD5");
		rdbtnNewRadioButton_3.setBounds(227, 108, 121, 23);
		contentPane.add(rdbtnNewRadioButton_3);
		
		ButtonGroup group=new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);
		
		JButton btnNewButton = new JButton("\u5F00\u59CB\u6D4B\u8BD5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNewRadioButton.isSelected()) {
					SM9SignatureTest sTest=new SM9SignatureTest();
					sTest.setVisible(true);
				}
				else if(rdbtnNewRadioButton_1.isSelected()) {
					SM9KeyExchangeTest kTest=new SM9KeyExchangeTest();
					kTest.setVisible(true);
				}
				else if(rdbtnNewRadioButton_2.isSelected()) {
					SM9EncapsulateTest eTest=new SM9EncapsulateTest();
					eTest.setVisible(true);
				}
				else if(rdbtnNewRadioButton_3.isSelected()) {
					SM9EncryptTest encryptTest=new SM9EncryptTest();
					encryptTest.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(158, 157, 93, 23);
		contentPane.add(btnNewButton);
	}
}
