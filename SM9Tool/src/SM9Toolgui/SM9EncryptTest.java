package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AuxiliaryAlgorithm.SM3;
import AuxiliaryAlgorithm.SM4;
import SM9.MasterKeyPair;
import SM9.PrivateKey;
import SM9.PrivateKeyType;
import SM9.ResultCipherText;
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
import java.awt.TextArea;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SM9EncryptTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SM9EncryptTest frame = new SM9EncryptTest();
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
	public SM9EncryptTest() {
		setTitle("\u52A0\u5BC6\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 971, 789);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton radioButton = new JRadioButton("\u6807\u51C6\u6D4B\u8BD5");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("Bob");
				textField_1.setText("Chinese IBE standard");
			}
		});
		radioButton.setBounds(617, 6, 94, 23);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u666E\u901A\u6D4B\u8BD5");
		radioButton_1.setBounds(718, 6, 101, 23);
		contentPane.add(radioButton_1);
		
		ButtonGroup group =new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 293, 462, 457);
		contentPane.add(textArea);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(478, 293, 468, 457);
		contentPane.add(textArea_1);
		
		textField = new JTextField();
		textField.setBounds(78, 7, 224, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblms = new JLabel("(\u8017\u65F6:0ms)");
		lblms.setBounds(55, 272, 169, 15);
		contentPane.add(lblms);
		
		JLabel lblms_1 = new JLabel("(\u8017\u65F6:0ms)");
		lblms_1.setBounds(517, 272, 181, 15);
		contentPane.add(lblms_1);
		
		JLabel lblms_2 = new JLabel("(\u8017\u65F6:0ms)");
		lblms_2.setBounds(55, 60, 123, 15);
		contentPane.add(lblms_2);
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setBounds(11, 81, 935, 185);
		contentPane.add(textArea_2);
		
		Timer timer=new Timer();
		
		JButton button = new JButton("\u5F00\u59CB\u6D4B\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(radioButton.isSelected()) && !(radioButton_1.isSelected())) {
						JOptionPane.showMessageDialog(button,"没有选择测试类型！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField.getText().trim().length()<1 || textField_1.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写标识或消息！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						double entime=0;double detime=0;double init;
						String id_B =textField.getText();
						String msg =textField_1.getText();
						if(radioButton.isSelected()) {
							init=0;entime=0;detime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
				        	KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
				        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
				        	KGCWithStandardTestKey.k= new BigInteger("01EDEE3778F441F8DEA3D9FA0ACC4E07EE36C93F9A08618AF4AD85CEDE1C22", 16);
				            MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
				            init+=timer.stop(0);
				            lblms_2.setText("(耗时："+String.format("%.2f", init)+"ms)");
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
				            textArea.append("\n待加密消息 M:");
				            textArea.append(msg);
				            textArea.append("\nM的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(msg.getBytes()));
				            textArea.append("\n消息M的长度: "+msg.length() + " bytes\n");
				            textArea.append("K1_len: "+ SM4.KEY_BYTE_LENGTH + " bytes\n");
				            int macKeyByteLen = SM3.DIGEST_SIZE;
				            textArea.append("K2_len: "+ SM3.DIGEST_SIZE + " bytes\n");
				            SM9WithStandardTestKey.r = new BigInteger("AAC0541779C8FC45E3E2CB25C12B5D2576B2129AE8BB5EE2CBE5EC9E785C", 16);
				            boolean isBaseBlockCipher = false;
				            for(int i=0; i<2; i++)
				            {
				                if(isBaseBlockCipher)
				                	textArea.append("\n加密明文的方法为分组密码算法 测试:\n");
				                else
				                	textArea.append("\n加密明文的方法为基于KDF的序列密码 测试:\n");
				                textArea.append("\n加密算法步骤A1-A8中的相关值:\n");
				                timer.start(0);
				                ResultCipherText resultCipherText = sm9.encrypt(encryptMasterKeyPair.getPublicKey(), id_B, msg.getBytes(), isBaseBlockCipher, macKeyByteLen);
				                double tten=timer.stop(0);
				                entime+=tten;
				                textArea.append(sm9.sb_encrypt.toString());
				                textArea.append("\n密文 C=C1||C3||C2:\n");
				                textArea.append(SM9Utils.toHexString(resultCipherText.toByteArray()));
				                textArea.append("加密耗时："+tten+"ms\n");
				                textArea_1.append("\n解密算法步骤B1-B5中的相关值:\n");
				                timer.start(0);
				                byte[] msgd = sm9.decrypt(resultCipherText, encryptPrivateKey, id_B, isBaseBlockCipher, macKeyByteLen);
				                double ttde=timer.stop(0);
				                detime+=ttde;
				                textArea_1.append(sm9.sb_decrypt.toString());
				                textArea_1.append("\n解密后的明文M':");
				                textArea_1.append(new String(msgd));
				                if (SM9Utils.byteEqual(msg.getBytes(), msgd)) {
				                	textArea_1.append("\n加解密成功!\n");
				                } else {
				                	textArea_1.append("\n加解密失败!\n");
				                }
				                textArea_1.append("解密耗时："+ttde+"ms\n");
				                isBaseBlockCipher = true;
				            }
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
				            lblms_2.setText("(耗时："+String.format("%.2f", init)+"ms)");
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
				            textArea.append("\n待加密消息 M:");
				            textArea.append(msg);
				            textArea.append("\nM的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(msg.getBytes()));
				            textArea.append("\n消息M的长度: "+msg.length() + " bytes\n");
				            textArea.append("K1_len: "+ SM4.KEY_BYTE_LENGTH + " bytes\n");
				            int macKeyByteLen = SM3.DIGEST_SIZE;
				            textArea.append("K2_len: "+ SM3.DIGEST_SIZE + " bytes\n");
				            boolean isBaseBlockCipher = false;
				            for(int i=0; i<2; i++)
				            {
				                if(isBaseBlockCipher)
				                	textArea.append("\n加密明文的方法为分组密码算法 测试:\n");
				                else
				                	textArea.append("\n加密明文的方法为基于KDF的序列密码 测试:\n");
				                textArea.append("\n加密算法步骤A1-A8中的相关值:\n");
				                timer.start(0);
				                ResultCipherText resultCipherText = sm9.encrypt(encryptMasterKeyPair.getPublicKey(), id_B, msg.getBytes(), isBaseBlockCipher, macKeyByteLen);
				                double tten=timer.stop(0);
				                entime+=tten;
				                textArea.append(sm9.sb_encrypt.toString());
				                textArea.append("\n密文 C=C1||C3||C2:\n");
				                textArea.append(SM9Utils.toHexString(resultCipherText.toByteArray()));
				                textArea.append("加密耗时："+tten+"ms\n");
				                textArea_1.append("\n解密算法步骤B1-B5中的相关值:\n");
				                timer.start(0);
				                byte[] msgd = sm9.decrypt(resultCipherText, encryptPrivateKey, id_B, isBaseBlockCipher, macKeyByteLen);
				                double ttde=timer.stop(0);
				                detime+=ttde;
				                textArea_1.append(sm9.sb_decrypt.toString());
				                textArea_1.append("\n解密后的明文M':");
				                textArea_1.append(new String(msgd));
				                if (SM9Utils.byteEqual(msg.getBytes(), msgd)) {
				                	textArea_1.append("\n加解密成功!\n");
				                } else {
				                	textArea_1.append("\n加解密失败!\n");
				                }
				                textArea_1.append("解密耗时："+ttde+"ms\n");
				                isBaseBlockCipher = true;
				            }
				            lblms.setText("(耗时："+String.format("%.2f", entime)+"ms)");
				            lblms_1.setText("(耗时："+String.format("%.2f", detime)+"ms)");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		button.setBounds(825, 6, 109, 23);
		contentPane.add(button);
		
		JLabel lblb = new JLabel("\u5B9E\u4F53B\u6807\u8BC6\uFF1A");
		lblb.setBounds(10, 10, 83, 15);
		contentPane.add(lblb);
		
		JLabel label = new JLabel("\u52A0\u5BC6\uFF1A");
		label.setBounds(10, 272, 54, 15);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(412, 7, 199, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u660E\u6587\u6D88\u606F\uFF1A");
		label_1.setBounds(327, 10, 75, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u89E3\u5BC6\uFF1A");
		label_2.setBounds(478, 272, 54, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u521D\u59CB\u5316\uFF1A");
		label_3.setBounds(10, 60, 54, 15);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("\u9009\u62E9\u6570\u636E\u5BFC\u51FA\u8DEF\u5F84\uFF1A");
		label_4.setBounds(10, 35, 133, 15);
		contentPane.add(label_4);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(122, 32, 247, 23);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton button_1 = new JButton("...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				String filepath= fileChooser.getSelectedFile().getAbsolutePath();
				textField_2.setText(filepath);
				}
			}
		});
		button_1.setBounds(379, 31, 42, 23);
		contentPane.add(button_1);
		
		JButton btnNewButton = new JButton("\u6570\u636E\u5BFC\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().length()<1 || textField_1.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写标识或消息！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(button,"没有选择导出路径！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String savepath=textField_2.getText();
						String id_B=textField.getText();
			        	String msg=textField_1.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	KGCWithStandardTestKey.k= new BigInteger("01EDEE3778F441F8DEA3D9FA0ACC4E07EE36C93F9A08618AF4AD85CEDE1C22", 16);
			        	MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
			        	PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
			        	SM9WithStandardTestKey.r = new BigInteger("AAC0541779C8FC45E3E2CB25C12B5D2576B2129AE8BB5EE2CBE5EC9E785C", 16);
			        	ResultCipherText cipherText = sm9.encrypt(encryptMasterKeyPair.getPublicKey(), id_B, msg.getBytes(), false, SM3.DIGEST_SIZE);
			        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/enc_msk.out");
			        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/enc_random.out");
			        	Serialize.serializeSk(encryptPrivateKey, savepath+"/enc_sk.out");
			        	Serialize.serializeRecipher(cipherText, savepath+"/enc.out");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(431, 31, 93, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u6279\u91CF\u6570\u636E\u5BFC\u51FA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(button,"没有选择导出路径！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String savepath=textField_2.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	for(int i=0;i<5;i++) {
			        		String id_B=i+"";String msg=i+"";
			        		KGCWithStandardTestKey.k= SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	MasterKeyPair encryptMasterKeyPair = kgc.genEncryptMasterKeyPair();
				        	PrivateKey encryptPrivateKey = kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
				        	SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	ResultCipherText cipherText = sm9.encrypt(encryptMasterKeyPair.getPublicKey(), id_B, msg.getBytes(), false, SM3.DIGEST_SIZE);
				        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/enc_msk/msk"+i+".out");
				        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/enc_random/random"+i+".out");
				        	Serialize.serializeSk(encryptPrivateKey, savepath+"/enc_sk/sk"+i+".out");
				        	Serialize.serializeRecipher(cipherText, savepath+"/enc_data/enc"+i+".out");
			        	}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(546, 31, 123, 23);
		contentPane.add(btnNewButton_1);
	}

}
