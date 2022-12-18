package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SM9.MasterKeyPair;
import SM9.PrivateKey;
import SM9.PrivateKeyType;
import SM9.ResultEncapsulate;
import SM9.ResultEncapsulateCipherText;
import SM9.ResultSignature;
import SM9.SM9;
import SM9.SM9Utils;
import SM9.KGC;
import SM9.KGCWithStandardTestKey;
import SM9.SM9Curve;
import SM9.SM9WithStandardTestKey;
import Utils.Serialize;
import Utils.Timer;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.awt.Label;

public class SM9EncapsulateTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SM9EncapsulateTest frame = new SM9EncapsulateTest();
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
	public SM9EncapsulateTest() {
		setTitle("\u5BC6\u94A5\u5C01\u88C5\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 953, 698);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton radioButton = new JRadioButton("\u6807\u51C6\u6D4B\u8BD5");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("Bob");
			}
		});
		radioButton.setBounds(370, 6, 93, 23);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u666E\u901A\u6D4B\u8BD5");
		radioButton_1.setBounds(483, 6, 100, 23);
		contentPane.add(radioButton_1);
		
		ButtonGroup group=new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 277, 453, 373);
		contentPane.add(textArea);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(469, 277, 465, 373);
		contentPane.add(textArea_1);	
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setBounds(10, 81, 924, 169);
		contentPane.add(textArea_2);
		
		JLabel lblms = new JLabel("(\u8017\u65F6:0ms)");
		lblms.setBounds(47, 256, 148, 15);
		contentPane.add(lblms);
		
		JLabel lblms_1 = new JLabel("(\u8017\u65F6:0ms)");
		lblms_1.setBounds(515, 256, 141, 15);
		contentPane.add(lblms_1);
		
		Label label_3 = new Label("(\u8017\u65F6:0ms\uFF09");
		label_3.setBounds(58, 60, 144, 23);
		contentPane.add(label_3);
		
		Timer timer=new Timer();
		
		JButton button = new JButton("\u5F00\u59CB\u6D4B\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(radioButton.isSelected()) && !(radioButton_1.isSelected())) {
						JOptionPane.showMessageDialog(button,"没有选择测试类型！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写标识！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						double entime=0;double detime=0;double init;
						String id_B =textField.getText();
						if(radioButton.isSelected()) {
							init=0;entime=0;detime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
				        	KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
				        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
				        	KGCWithStandardTestKey.k = new BigInteger("01EDEE3778F441F8DEA3D9FA0ACC4E07EE36C93F9A08618AF4AD85CEDE1C22", 16);
				            MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
				            init+=timer.stop(0);
				            label_3.setText("(耗时："+String.format("%.2f", init)+"ms)");
				            textArea_2.append("\n加密主私钥 ke:");
				            textArea_2.append(encryptMasterKeyPair.getPrivateKey().toString());
				            textArea_2.append("\n加密主公钥 Ppub-e:");
				            textArea_2.append(encryptMasterKeyPair.getPublicKey().toString());
				            textArea.append("\n实体B的标识IDB:");
				            textArea.append(id_B);
				            textArea.append("\nIDB的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(id_B.getBytes()));
				            timer.start(0);
				            PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
				            entime+=timer.stop(0);
				            textArea.append("\n加密私钥 de_B:");
				            textArea.append(encryptPrivateKey.toString());
				            int keyByteLen = 32;
				            textArea.append("\n密钥封装的长度: " + keyByteLen + " bytes\n");
				            textArea.append("密钥封装步骤A1-A7中的相关值:");
				            SM9WithStandardTestKey.r = new BigInteger("74015F8489C01EF4270456F9E6475BFB602BDE7F33FD482AB4E3684A6722", 16);
				            ResultEncapsulate keyEncapsulation = sm9.keyEncapsulate(encryptMasterKeyPair.getPublicKey(), id_B, keyByteLen);
				            textArea.append(sm9.sb_keyEncapsulate.toString());
				            textArea_1.append("解封装步骤B1-B4中的相关值:");
				            ResultEncapsulateCipherText cipherText = ResultEncapsulateCipherText.fromByteArray(sm9.getCurve(), keyEncapsulation.getC().toByteArray());
				            timer.start(0);
				            byte[] K = sm9.keyDecapsulate(encryptPrivateKey, id_B, keyByteLen, cipherText);
				            detime+=timer.stop(0);
				            textArea_1.append(sm9.sb_keyDecapsulate.toString());
				            if(SM9Utils.byteEqual(keyEncapsulation.getK(), K))
				            	textArea_1.append("测试成功！");
				            else
				            	textArea_1.append("测试失败！");
				            lblms.setText("(耗时："+String.format("%.2f", entime)+"ms)");
				            lblms_1.setText("(耗时："+String.format("%.2f", detime)+"ms)");
						}
						else if(radioButton_1.isSelected()) {
							init=0;entime=0;detime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
				        	KGC kgc = new KGC(sm9Curve);
				        	SM9 sm9 = new SM9(sm9Curve);
				        	MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
				        	init+=timer.stop(0);
				            label_3.setText("(耗时："+String.format("%.2f", init)+"ms)");
				            textArea_2.append("\n加密主私钥 ke:");
				            textArea_2.append(encryptMasterKeyPair.getPrivateKey().toString());
				            textArea_2.append("\n加密主公钥 Ppub-e:");
				            textArea_2.append(encryptMasterKeyPair.getPublicKey().toString());
				            textArea.append("\n实体B的标识IDB:");
				            textArea.append(id_B);
				            textArea.append("\nIDB的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(id_B.getBytes()));
				            PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
				            textArea.append("\n加密私钥 de_B:");
				            textArea.append(encryptPrivateKey.toString());
				            int keyByteLen = 32;
				            textArea.append("\n密钥封装的长度: " + keyByteLen + " bytes\n");
				            textArea.append("密钥封装步骤A1-A7中的相关值:");
				            timer.start(0);
				            ResultEncapsulate keyEncapsulation = sm9.keyEncapsulate(encryptMasterKeyPair.getPublicKey(), id_B, keyByteLen);
				            entime+=timer.stop(0);
				            textArea.append(sm9.sb_keyEncapsulate.toString());
				            textArea_1.append("解封装步骤B1-B4中的相关值:");
				            ResultEncapsulateCipherText cipherText = ResultEncapsulateCipherText.fromByteArray(sm9.getCurve(), keyEncapsulation.getC().toByteArray());
				            timer.start(0);
				            byte[] K = sm9.keyDecapsulate(encryptPrivateKey, id_B, keyByteLen, cipherText);
				            detime+=timer.stop(0);
				            textArea_1.append(sm9.sb_keyDecapsulate.toString());
				            if(SM9Utils.byteEqual(keyEncapsulation.getK(), K))
				            	textArea_1.append("\n测试成功!");
				            else
				            	textArea_1.append("\n测试失败!");
				            lblms.setText("(耗时："+String.format("%.2f", entime)+"ms)");
				            lblms_1.setText("(耗时："+String.format("%.2f", detime)+"ms)");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button.setBounds(602, 6, 116, 23);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(94, 7, 263, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblb = new JLabel("\u5B9E\u4F53B\u6807\u8BC6\uFF1A");
		lblb.setBounds(10, 10, 80, 15);
		contentPane.add(lblb);
		
		JLabel label = new JLabel("\u5C01\u88C5\uFF1A");
		label.setBounds(10, 256, 54, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u89E3\u5C01\uFF1A");
		label_1.setBounds(469, 256, 54, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u521D\u59CB\u5316\uFF1A");
		label_2.setBounds(10, 60, 54, 15);
		contentPane.add(label_2);
		
		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u6570\u636E\u5BFC\u51FA\u8DEF\u5F84\uFF1A");
		lblNewLabel.setBounds(10, 35, 116, 15);
		contentPane.add(lblNewLabel);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(125, 32, 253, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				String filepath= fileChooser.getSelectedFile().getAbsolutePath();
				textField_1.setText(filepath);
				}
			}
		});
		button_1.setBounds(395, 31, 33, 23);
		contentPane.add(button_1);
		
		JButton btnNewButton = new JButton("\u6570\u636E\u5BFC\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写标识！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_1.getText().trim().length()<1){
						JOptionPane.showMessageDialog(button,"没有选择导出路径！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String savepath=textField_1.getText();
						String id_B=textField.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	KGCWithStandardTestKey.k = new BigInteger("01EDEE3778F441F8DEA3D9FA0ACC4E07EE36C93F9A08618AF4AD85CEDE1C22", 16);
			        	MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
			        	PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
			        	SM9WithStandardTestKey.r = new BigInteger("74015F8489C01EF4270456F9E6475BFB602BDE7F33FD482AB4E3684A6722", 16);
			        	ResultEncapsulate keyEncapsulation = sm9.keyEncapsulate(encryptMasterKeyPair.getPublicKey(), id_B, 32);
			        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/encap_msk.out");
			        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/encap_random.out");
			        	Serialize.serializeSk(encryptPrivateKey, savepath+"/encap_sk.out");
			        	Serialize.serializeReencap(keyEncapsulation, savepath+"/encap.out");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(449, 31, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6279\u91CF\u6570\u636E\u5BFC\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().trim().length()<1){
						JOptionPane.showMessageDialog(button,"没有选择导出路径！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String savepath=textField_1.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	for(int i=0;i<5;i++) {
			        		String id_B=i+"";
			        		KGCWithStandardTestKey.k = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
				        	PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
				        	SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	ResultEncapsulate keyEncapsulation = sm9.keyEncapsulate(encryptMasterKeyPair.getPublicKey(), id_B, 32);
				        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/encap_msk/msk"+i+".out");
				        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/encap_random/random"+i+".out");
				        	Serialize.serializeSk(encryptPrivateKey, savepath+"/encap_sk/sk"+i+".out");
				        	Serialize.serializeReencap(keyEncapsulation, savepath+"/encap_data/encap"+i+".out");
			        	}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(567, 31, 130, 23);
		contentPane.add(btnNewButton_1);


	}
}
