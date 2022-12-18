package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AuxiliaryAlgorithm.SM3;
import SM9.G1KeyPair;
import SM9.KGC;
import SM9.KGCWithStandardTestKey;
import SM9.MasterKeyPair;
import SM9.MasterPrivateKey;
import SM9.MasterPublicKey;
import SM9.PrivateKey;
import SM9.PrivateKeyType;
import SM9.ResultCipherText;
import SM9.ResultEncapsulate;
import SM9.ResultKeyExchange;
import SM9.ResultSignature;
import SM9.SM9;
import SM9.SM9Curve;
import SM9.SM9Utils;
import SM9.SM9WithStandardTestKey;
import Utils.Serialize;
import Utils.Timer;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.math.BigInteger;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Button;

public class SignatureDataTest extends JFrame {

	private JPanel contentPane;
	private JTextField textField_msk;
	private JTextField textField_ida;
	private JTextField textField_msg;
	private JButton btnNewButton_2;
	private JTextField textField_ra;
	private JTextField textField_sign;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField textField_ska;
	private JButton button_ska;
	private JLabel label;
	private JTextField textField_skb;
	private JTextField textField_idb;
	private JTextField textField_exa;
	private JLabel lbla;
	private JLabel lblNewLabel_8;
	private JTextField textField_exb;
	private JButton button_exb;
	private JLabel label_1;
	private JTextField textField_enp;
	private JButton button_enp;
	private JLabel label_2;
	private JTextField textField_cip;
	private JButton button_cip;
	private JLabel lblNewLabel_9;
	private JTextField textField_rb;
	private JButton button_rb;
	private JButton btnNewButton_9;
	private JButton button_4;
	private JLabel label_3;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignatureDataTest frame = new SignatureDataTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public SignatureDataTest() {
		setTitle("\u4E00\u81F4\u6027\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 873, 573);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_msk = new JTextField();
		textField_msk.setEnabled(false);
		textField_msk.setBounds(67, 41, 281, 21);
		contentPane.add(textField_msk);
		textField_msk.setColumns(10);
		
		textField_ida = new JTextField();
		textField_ida.setBounds(67, 383, 281, 21);
		contentPane.add(textField_ida);
		textField_ida.setColumns(10);
		
		textField_msg = new JTextField();
		textField_msg.setBounds(67, 436, 281, 21);
		contentPane.add(textField_msg);
		textField_msg.setColumns(10);
		
		JButton button_msk = new JButton("...");
		button_msk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_msk)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_msk.setText(file.getAbsolutePath());
				};
			}
		});
		button_msk.setBounds(358, 42, 38, 23);
		contentPane.add(button_msk);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(415, 34, 440, 423);
		contentPane.add(textArea);
		
		
		textField_ra = new JTextField();
		textField_ra.setEnabled(false);
		textField_ra.setBounds(67, 76, 281, 21);
		contentPane.add(textField_ra);
		textField_ra.setColumns(10);
		
		JButton button_ra = new JButton("...");
		button_ra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_ra)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_ra.setText(file.getAbsolutePath());
				};
			}
		});
		button_ra.setBounds(358, 75, 38, 23);
		contentPane.add(button_ra);
		
		textField_sign = new JTextField();
		textField_sign.setEnabled(false);
		textField_sign.setBounds(67, 219, 281, 21);
		contentPane.add(textField_sign);
		textField_sign.setColumns(10);
		
		JButton button_sign = new JButton("...");
		button_sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_sign)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_sign.setText(file.getAbsolutePath());
				};
			}
		});
		button_sign.setBounds(358, 218, 38, 23);
		contentPane.add(button_sign);
		
		lblNewLabel_1 = new JLabel("\u4E3B\u79C1\u94A5\uFF1A");
		lblNewLabel_1.setBounds(10, 44, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\u968F\u673A\u6570rA\uFF1A");
		lblNewLabel_2.setBounds(10, 79, 70, 15);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("\u7B7E\u540D\u503C\uFF1A");
		lblNewLabel_3.setBounds(10, 222, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("\u6807\u8BC6A:");
		lblNewLabel_4.setBounds(10, 386, 45, 15);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("\u6D88\u606F:");
		lblNewLabel_5.setBounds(10, 439, 45, 15);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("\u79C1\u94A5A\uFF1A");
		lblNewLabel_6.setBounds(10, 154, 54, 15);
		contentPane.add(lblNewLabel_6);
		
		textField_ska = new JTextField();
		textField_ska.setEnabled(false);
		textField_ska.setText("");
		textField_ska.setBounds(67, 151, 281, 21);
		contentPane.add(textField_ska);
		textField_ska.setColumns(10);
		
		button_ska = new JButton("...");
		button_ska.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_ska)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_ska.setText(file.getAbsolutePath());
				};
			}
		});
		button_ska.setBounds(358, 150, 38, 23);
		contentPane.add(button_ska);
		
		label = new JLabel("\u6D4B\u8BD5\u7ED3\u679C\uFF1A");
		label.setBounds(415, 13, 82, 15);
		contentPane.add(label);
		
		textField_skb = new JTextField();
		textField_skb.setEnabled(false);
		textField_skb.setBounds(67, 188, 281, 18);
		contentPane.add(textField_skb);
		textField_skb.setColumns(10);
		
		JLabel lblb = new JLabel("\u79C1\u94A5B\uFF1A");
		lblb.setBounds(10, 190, 54, 15);
		contentPane.add(lblb);
		
		JButton button_skb = new JButton("...");
		button_skb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_skb)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_skb.setText(file.getAbsolutePath());
				};
			}
		});
		button_skb.setBounds(358, 185, 38, 23);
		contentPane.add(button_skb);
		
		JLabel lblNewLabel_7 = new JLabel("\u6807\u8BC6B:");
		lblNewLabel_7.setBounds(10, 411, 54, 15);
		contentPane.add(lblNewLabel_7);
		
		textField_idb = new JTextField();
		textField_idb.setBounds(67, 408, 281, 21);
		contentPane.add(textField_idb);
		textField_idb.setColumns(10);
		
		JButton button_exa = new JButton("...");
		button_exa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_exa)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_exa.setText(file.getAbsolutePath());
				};
			}
		});
		button_exa.setBounds(358, 259, 38, 23);
		contentPane.add(button_exa);
		
		textField_exa = new JTextField();
		textField_exa.setEnabled(false);
		textField_exa.setBounds(67, 260, 281, 22);
		contentPane.add(textField_exa);
		textField_exa.setColumns(10);
		
		lbla = new JLabel("\u4EA4\u6362\u503CA:");
		lbla.setBounds(10, 263, 54, 15);
		contentPane.add(lbla);
		
		lblNewLabel_8 = new JLabel("\u4EA4\u6362\u503CB:");
		lblNewLabel_8.setBounds(10, 295, 54, 15);
		contentPane.add(lblNewLabel_8);
		
		textField_exb = new JTextField();
		textField_exb.setEnabled(false);
		textField_exb.setBounds(67, 292, 281, 21);
		contentPane.add(textField_exb);
		textField_exb.setColumns(10);
		
		button_exb = new JButton("...");
		button_exb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_exb)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_exb.setText(file.getAbsolutePath());
				};
			}
		});
		button_exb.setBounds(358, 291, 38, 23);
		contentPane.add(button_exb);
		
		label_1 = new JLabel("\u5C01\u88C5\u503C\uFF1A");
		label_1.setBounds(10, 323, 54, 15);
		contentPane.add(label_1);
		
		textField_enp = new JTextField();
		textField_enp.setEnabled(false);
		textField_enp.setBounds(67, 321, 281, 18);
		contentPane.add(textField_enp);
		textField_enp.setColumns(10);
		
		button_enp = new JButton("...");
		button_enp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_enp)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_enp.setText(file.getAbsolutePath());
				};
			}
		});
		button_enp.setBounds(358, 319, 38, 23);
		contentPane.add(button_enp);
		
		label_2 = new JLabel("\u52A0\u5BC6\u503C\uFF1A");
		label_2.setBounds(10, 355, 54, 15);
		contentPane.add(label_2);
		
		textField_cip = new JTextField();
		textField_cip.setEnabled(false);
		textField_cip.setBounds(67, 352, 281, 21);
		contentPane.add(textField_cip);
		textField_cip.setColumns(10);
		
		button_cip = new JButton("...");
		button_cip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_cip)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_cip.setText(file.getAbsolutePath());
				};
			}
		});
		button_cip.setBounds(358, 351, 38, 23);
		contentPane.add(button_cip);
		
		lblNewLabel_9 = new JLabel("\u968F\u673A\u6570rB\uFF1A");
		lblNewLabel_9.setBounds(10, 115, 70, 15);
		contentPane.add(lblNewLabel_9);
		
		textField_rb = new JTextField();
		textField_rb.setEnabled(false);
		textField_rb.setBounds(67, 112, 281, 18);
		contentPane.add(textField_rb);
		textField_rb.setColumns(10);
		
		button_rb = new JButton("...");
		button_rb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser=new JFileChooser();
				chooser.setFileSelectionMode(0);
				if(chooser.showOpenDialog(button_rb)==JFileChooser.APPROVE_OPTION) {
					File file=chooser.getSelectedFile();
					textField_rb.setText(file.getAbsolutePath());
				};
			}
		});
		button_rb.setBounds(358, 108, 38, 23);
		contentPane.add(button_rb);
		
		Timer timer=new Timer();
		
		btnNewButton_2 = new JButton("\u7B7E\u540D-\u9A8C\u7B7E\u6D4B\u8BD5");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timer.start(0);double tt=0;
					if(textField_ida.getText().trim().length()<1 || textField_msg.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有填写标识或消息！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_msk.getText().trim().length()<1 || textField_ra.getText().trim().length()<1 || textField_sign.getText().trim().length()<1 || textField_ska.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有选择检测数据（主私钥、随机数rA、私钥skA、签名值）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						textArea.setText("");
						String mskpath=textField_msk.getText();
			        	String id_A=textField_ida.getText();
			        	String msg=textField_msg.getText();
			        	String rpath=textField_ra.getText();
			        	String skpath=textField_ska.getText();
			        	String sign=textField_sign.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	if(!(Serialize.deserializeMsk(mskpath))) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(主密钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	else {KGCWithStandardTestKey.k=Serialize.msk;}
			        	MasterKeyPair signMasterKeyPair=kgc.genSignMasterKeyPair();
			        	textArea.append("\n签名主私钥 ks:");
				        textArea.append(signMasterKeyPair.getPrivateKey().toString());
				        textArea.append("\n签名主公钥 Ppub-s:");
				        textArea.append(signMasterKeyPair.getPublicKey().toString());
				        if(!(Serialize.deserializeR(rpath))) {
				        	tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(随机数r)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
				        else {
				        	SM9WithStandardTestKey.r=Serialize.r;
				        	textArea.append("\n所给随机值：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(Serialize.r)));
				        }
				        if(!(Serialize.deserializeSk(skpath))) {
				        	tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(私钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
				        else {
				        	PrivateKey sk=Serialize.sk;
				        	sk=kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), sk.getId(), PrivateKeyType.KEY_SIGN);
				        	textArea.append("\n所给私钥ID："+sk.getId());
				        	textArea.append("\n所给私钥："+sk.toString());
				        	if(!sk.getId().equals(id_A)) {
				        		tt+=timer.stop(0);
				        		JOptionPane.showMessageDialog(button_sign,"私钥不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
				        	}
				        	else {
				        		PrivateKey sk2= kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
				        		if(!(Serialize.deserializeResign(sign))) {
				        			tt+=timer.stop(0);
					        		JOptionPane.showMessageDialog(button_sign,"导入数据(签名值)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
					        	}
				        		else {
				        			ResultSignature signature=Serialize.rs;
						        	ResultSignature signature2=sm9.sign(signMasterKeyPair.getPublicKey(), sk2, msg.getBytes());
						        	textArea.append("\n所给签名值：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(signature.geth())));
						        	textArea.append("\n输入消息的签名值：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(signature2.geth())));
						        	if(!signature.geth().equals(signature2.geth())) {
						        		tt+=timer.stop(0);
						        		JOptionPane.showMessageDialog(button_sign,"签名值不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
						        	}
						        	else {
						        		tt+=timer.stop(0);
						        		JOptionPane.showMessageDialog(button_sign,"验证成功！","提示",JOptionPane.PLAIN_MESSAGE);
						        	}
				        		}
				        	}
				        }
					}
					lblNewLabel.setText("(耗时："+String.format("%.2f", tt)+"ms)");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(86, 467, 125, 23);
		contentPane.add(btnNewButton_2);
		
		btnNewButton_9 = new JButton("\u5BC6\u94A5\u4EA4\u6362\u6D4B\u8BD5");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timer.start(0);double tt=0;
					if(textField_ida.getText().trim().length()<1 || textField_idb.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有填写双方标识（A、B）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_msk.getText().trim().length()<1 || textField_ra.getText().trim().length()<1 || textField_rb.getText().trim().length()<1 ||
							textField_ska.getText().trim().length()<1 || textField_skb.getText().trim().length()<1 || textField_exa.getText().trim().length()<1 || textField_exb.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有选择检测数据（主私钥、随机数rA、随机数rB、私钥A、私钥B、交换值A、交换值B）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						textArea.setText("");
						String mskpath=textField_msk.getText();
			        	String id_A=textField_ida.getText();
			        	String id_B=textField_idb.getText();
			        	String skapath=textField_ska.getText();
			        	String skbpath=textField_skb.getText();
			        	String rapath=textField_ra.getText();
			        	String rbpath=textField_rb.getText();
			        	String exapath=textField_exa.getText();
			        	String exbpath=textField_exb.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	if(!(Serialize.deserializeMsk(mskpath))) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(主密钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	else {KGCWithStandardTestKey.k=Serialize.msk;}
			        	MasterKeyPair masterKeyPair = kgc.genEncryptMasterKeyPair();
			        	textArea.append("\n主私钥 ke:");
				        textArea.append(masterKeyPair.getPrivateKey().toString());
				        textArea.append("\n签名主公钥 Ppub-e:");
				        textArea.append(masterKeyPair.getPublicKey().toString());
				        //PrivateKey aPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_KEY_EXCHANGE);
			        	//PrivateKey bPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_KEY_EXCHANGE);
			        	if(!(Serialize.deserializeSk(skapath))) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(私钥A)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	PrivateKey ska=Serialize.sk;
			        	ska=kgc.genPrivateKey(masterKeyPair.getPrivateKey(), ska.getId(), PrivateKeyType.KEY_KEY_EXCHANGE);
			        	textArea.append("\n所给私钥id_A："+ska.getId());
			        	textArea.append("\n所给私钥A："+ska.toString());
			        	if(!ska.getId().equals(id_A)) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"私钥A不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	else {
			        		if(!(Serialize.deserializeSk(skbpath))) {
			        			tt+=timer.stop(0);
				        		JOptionPane.showMessageDialog(button_sign,"导入数据(私钥B)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
				        	}
				        	PrivateKey skb=Serialize.sk;
				        	skb=kgc.genPrivateKey(masterKeyPair.getPrivateKey(), skb.getId(), PrivateKeyType.KEY_KEY_EXCHANGE);
				        	textArea.append("\n所给私钥id_B："+skb.getId());
				        	textArea.append("\n所给私钥B："+skb.toString());
				        	if(!skb.getId().equals(id_B)) {
				        		tt+=timer.stop(0);
				        		JOptionPane.showMessageDialog(button_sign,"私钥B不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
				        	}
				        	else {
				        		if(!(Serialize.deserializeR(rapath))) {
				        			tt+=timer.stop(0);
					        		JOptionPane.showMessageDialog(button_sign,"导入数据(随机数rA)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
					        	}
						        else {
						        	SM9WithStandardTestKey.r=Serialize.r;
						        	textArea.append("\n所给随机值rA：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(Serialize.r)));
						        	G1KeyPair aTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_B);
						        	if(!(Serialize.deserializeR(rbpath))) {
						        		tt+=timer.stop(0);
						        		JOptionPane.showMessageDialog(button_sign,"导入数据(随机数rB)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
						        	}
							        else {
							        	SM9WithStandardTestKey.r=Serialize.r;
							        	textArea.append("\n所给随机值rB：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(Serialize.r)));
							        	G1KeyPair bTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_A);
							        	ResultKeyExchange bAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), false,
							                    id_B, id_A, skb, bTempKeyPair, aTempKeyPair.getPublicKey(), 16);
							            ResultKeyExchange aAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), true,
							                    id_A, id_B, ska, aTempKeyPair, bTempKeyPair.getPublicKey(), 16);
							            textArea.append("输入标识A的SA: "+SM9Utils.toHexString(aAgreementKey.getSA2()));
							            textArea.append("输入标识A的S1: "+SM9Utils.toHexString(aAgreementKey.getSB1()));
							            textArea.append("输入标识A的SK_A: "+SM9Utils.toHexString(aAgreementKey.getSK()));
							            textArea.append("输入标识B的S2: "+SM9Utils.toHexString(bAgreementKey.getSA2()));
							            textArea.append("输入标识B的SB: "+SM9Utils.toHexString(bAgreementKey.getSB1()));
							            textArea.append("输入标识B的SK_B: "+SM9Utils.toHexString(bAgreementKey.getSK()));
							            if(!(Serialize.deserializeRekeyexchange(exapath))) {
							            	tt+=timer.stop(0);
							            	JOptionPane.showMessageDialog(button_sign,"导入数据(交换值A)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
							            }
							            else {
							            	ResultKeyExchange aexchange=Serialize.rk;
							            	textArea.append("所给SA: "+SM9Utils.toHexString(aAgreementKey.getSA2()));
								            textArea.append("所给S1: "+SM9Utils.toHexString(aAgreementKey.getSB1()));
								            textArea.append("所给SK_A: "+SM9Utils.toHexString(aAgreementKey.getSK()));
								            if(!(SM9Utils.byteEqual(aAgreementKey.getSA2(), aexchange.getSA2())) || !(SM9Utils.byteEqual(aAgreementKey.getSB1(), aexchange.getSB1())) || !(SM9Utils.byteEqual(aAgreementKey.getSK(), aexchange.getSK()))) {
								            	tt+=timer.stop(0);
								            	JOptionPane.showMessageDialog(button_sign,"交换值A不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
								            }
								            else {
								            	if(!(Serialize.deserializeRekeyexchange(exbpath))) {
								            		tt+=timer.stop(0);
									            	JOptionPane.showMessageDialog(button_sign,"导入数据(交换值B)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
									            }
									            else {
									            	ResultKeyExchange bexchange=Serialize.rk;
									            	textArea.append("所给S2: "+SM9Utils.toHexString(bAgreementKey.getSA2()));
										            textArea.append("所给SB: "+SM9Utils.toHexString(bAgreementKey.getSB1()));
										            textArea.append("所给SK_B: "+SM9Utils.toHexString(bAgreementKey.getSK()));
										            if(!(SM9Utils.byteEqual(bAgreementKey.getSA2(), bexchange.getSA2())) || !(SM9Utils.byteEqual(bAgreementKey.getSB1(), bexchange.getSB1())) || !(SM9Utils.byteEqual(bAgreementKey.getSK(), bexchange.getSK()))) {
										            	tt+=timer.stop(0);
										            	JOptionPane.showMessageDialog(button_sign,"交换值B不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
										            }
										            else {
										            	tt+=timer.stop(0);
										            	JOptionPane.showMessageDialog(button_sign,"验证成功！","提示",JOptionPane.PLAIN_MESSAGE);
										            }
									            }
								            }
							            }
							        }
						        }
				        	}
			        	}
					}
					lblNewLabel.setText("(耗时："+String.format("%.2f", tt)+"ms)");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_9.setBounds(285, 467, 120, 23);
		contentPane.add(btnNewButton_9);
		
		button_4 = new JButton("\u5C01\u88C5-\u89E3\u5C01\u6D4B\u8BD5");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timer.start(0);double tt=0;
					if(textField_idb.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有填写标识B！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_msk.getText().trim().length()<1 || textField_rb.getText().trim().length()<1 || textField_enp.getText().trim().length()<1 || textField_skb.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有选择检测数据（主私钥、随机数rB、私钥skB、封装值）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						textArea.setText("");
						String mskpath=textField_msk.getText();
			        	String id_B=textField_idb.getText();
			        	String rbpath=textField_rb.getText();
			        	String skbpath=textField_skb.getText();
			        	String enppath=textField_enp.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	if(!(Serialize.deserializeMsk(mskpath))) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(主密钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	else {
			        		KGCWithStandardTestKey.k=Serialize.msk;
			        	}
			        	MasterKeyPair encryptMasterKeyPair=kgc.genEncryptMasterKeyPair();
			        	textArea.append("\n封装主私钥 ke:");
				        textArea.append(encryptMasterKeyPair.getPrivateKey().toString());
				        textArea.append("\n封装主公钥 Ppub-e:");
				        textArea.append(encryptMasterKeyPair.getPublicKey().toString());
				        if(!(Serialize.deserializeR(rbpath))) {
				        	tt+=timer.stop(0);
				        	JOptionPane.showMessageDialog(button_sign,"导入数据(随机数r)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
				        else {
				        	SM9WithStandardTestKey.r=Serialize.r;
				        	textArea.append("\n所给随机值：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(Serialize.r)));
				        	if(!(Serialize.deserializeSk(skbpath))) {
				        		tt+=timer.stop(0);
				        		JOptionPane.showMessageDialog(button_sign,"导入数据(私钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
				        	}
					        else {
					        	PrivateKey sk=Serialize.sk;
					        	sk=kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), sk.getId(), PrivateKeyType.KEY_ENCRYPT);
					        	textArea.append("\n所给私钥ID："+sk.getId());
					        	textArea.append("\n所给私钥："+sk.toString());
					        	if(!sk.getId().equals(id_B)) {
					        		tt+=timer.stop(0);
					        		JOptionPane.showMessageDialog(button_sign,"私钥不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
					        	}
					        	else {
					        		//PrivateKey sk2= kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_ENCRYPT);
					        		if(!(Serialize.deserializeReencap(enppath))) {
					        			tt+=timer.stop(0);
						        		JOptionPane.showMessageDialog(button_sign,"导入数据(封装值)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
						        	}
					        		else {
					        			ResultEncapsulate re=Serialize.re;
					        			ResultEncapsulate re2=sm9.keyEncapsulate(encryptMasterKeyPair.getPublicKey(), id_B, 32);
					        			textArea.append("\n所给封装值：\n"+SM9Utils.toHexString(re.getK()));
							        	textArea.append("\n输入标识的封装值：\n"+SM9Utils.toHexString(re2.getK()));
							        	if(!SM9Utils.byteEqual(re.getK(), re2.getK())) {
							        		tt+=timer.stop(0);
							        		JOptionPane.showMessageDialog(button_sign,"封装值不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
							        	}
							        	else {
							        		tt+=timer.stop(0);
							        		JOptionPane.showMessageDialog(button_sign,"验证成功！","提示",JOptionPane.PLAIN_MESSAGE);
							        	}
					        		}
					        	}
					        }
				        }
					}
					lblNewLabel.setText("(耗时："+String.format("%.2f", tt)+"ms)");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_4.setBounds(508, 467, 125, 23);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("\u52A0\u5BC6-\u89E3\u5BC6\u6D4B\u8BD5");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					timer.start(0);double tt=0;
					if(textField_idb.getText().trim().length()<1 || textField_msg.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有填写标识B和消息！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_msk.getText().trim().length()<1 || textField_rb.getText().trim().length()<1 || textField_cip.getText().trim().length()<1 || textField_skb.getText().trim().length()<1) {
						tt+=timer.stop(0);
						JOptionPane.showMessageDialog(button_sign,"没有选择检测数据（主私钥、随机数rB、私钥skB、加密值）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						textArea.setText("");
						String mskpath=textField_msk.getText();
			        	String id_B=textField_idb.getText();
			        	String rbpath=textField_rb.getText();
			        	String skbpath=textField_skb.getText();
			        	String cippath=textField_cip.getText();
			        	String msg=textField_msg.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	if(!(Serialize.deserializeMsk(mskpath))) {
			        		tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(主密钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
			        	else {KGCWithStandardTestKey.k=Serialize.msk;}
			        	MasterKeyPair encryptMasterKeyPair=kgc.genEncryptMasterKeyPair();
			        	textArea.append("\n加密主私钥 ke:");
				        textArea.append(encryptMasterKeyPair.getPrivateKey().toString());
				        textArea.append("\n加密主公钥 Ppub-e:");
				        textArea.append(encryptMasterKeyPair.getPublicKey().toString());
				        if(!(Serialize.deserializeR(rbpath))) {
				        	tt+=timer.stop(0);
			        		JOptionPane.showMessageDialog(button_sign,"导入数据(随机数r)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
			        	}
				        else {
				        	SM9WithStandardTestKey.r=Serialize.r;
				        	textArea.append("\n所给随机值：\n"+SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(Serialize.r)));
				        	if(!(Serialize.deserializeSk(skbpath))) {
				        		tt+=timer.stop(0);
				        		JOptionPane.showMessageDialog(button_sign,"导入数据(私钥)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
				        	}
					        else {
					        	PrivateKey sk=Serialize.sk;
					        	sk=kgc.genPrivateKey(encryptMasterKeyPair.getPrivateKey(), sk.getId(), PrivateKeyType.KEY_ENCRYPT);
					        	textArea.append("\n所给私钥ID："+sk.getId());
					        	textArea.append("\n所给私钥："+sk.toString());
					        	if(!sk.getId().equals(id_B)) {
					        		tt+=timer.stop(0);
					        		JOptionPane.showMessageDialog(button_sign,"私钥不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
					        	}
					        	else {
					        		if(!(Serialize.deserializeRecipher(cippath))) {
					        			tt+=timer.stop(0);
					        			JOptionPane.showMessageDialog(button_sign,"导入数据(加密值)不符合规范！","提示",JOptionPane.ERROR_MESSAGE);
					        		}
					        		else {
					        			ResultCipherText rc=Serialize.rc;
					        			ResultCipherText rc2=sm9.encrypt(encryptMasterKeyPair.getPublicKey(), id_B, msg.getBytes(), false, SM3.DIGEST_SIZE);
					        			textArea.append("\n所给加密值：\n"+SM9Utils.toHexString(rc.getC3()));
							        	textArea.append("\n输入消息的加密值：\n"+SM9Utils.toHexString(rc2.getC3()));
							        	if(!SM9Utils.byteEqual(rc.getC3(), rc2.getC3())) {
							        		tt+=timer.stop(0);
							        		JOptionPane.showMessageDialog(button_sign,"加密值不同，验证失败！","提示",JOptionPane.ERROR_MESSAGE);
							        	}
							        	else {
							        		tt+=timer.stop(0);
							        		JOptionPane.showMessageDialog(button_sign,"验证成功！","提示",JOptionPane.PLAIN_MESSAGE);
							        	}
					        		}
					        	}
					        }
					    }
					}
					lblNewLabel.setText("(耗时："+String.format("%.2f", tt)+"ms)");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_5.setBounds(687, 467, 120, 23);
		contentPane.add(button_5);
		
		JLabel lblNewLabel_10 = new JLabel("\u4E3B\u79C1\u94A5\uFF0C\u968F\u673A\u6570rA\uFF0C\u79C1\u94A5A");
		lblNewLabel_10.setBounds(78, 492, 154, 21);
		contentPane.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("\u7B7E\u540D\u503C\uFF0C\u6807\u8BC6A\uFF0C\u6D88\u606F");
		lblNewLabel_11.setBounds(86, 510, 139, 15);
		contentPane.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("\u4E3B\u79C1\u94A5\uFF0C\u968F\u673A\u6570rA\uFF0C\u968F\u673A\u6570rB\uFF0C\u79C1\u94A5A\uFF0C\u79C1\u94A5B");
		lblNewLabel_12.setBounds(238, 495, 277, 15);
		contentPane.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("\u4EA4\u6362\u503CA,\uFF0C\u4EA4\u6362\u503CB\uFF0C\u6807\u8BC6A\uFF0C\u6807\u8BC6B");
		lblNewLabel_13.setBounds(259, 510, 206, 15);
		contentPane.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("\u4E3B\u79C1\u94A5\uFF0C\u968F\u673A\u6570rB\uFF0C\u79C1\u94A5B");
		lblNewLabel_14.setBounds(499, 495, 154, 15);
		contentPane.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("\u5C01\u88C5\u503C\uFF0C\u6807\u8BC6B");
		lblNewLabel_15.setBounds(537, 510, 96, 15);
		contentPane.add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel("\u4E3B\u79C1\u94A5\uFF0C\u968F\u673A\u6570rB\uFF0C\u79C1\u94A5B");
		lblNewLabel_16.setBounds(674, 495, 161, 15);
		contentPane.add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel("\u52A0\u5BC6\u503C\uFF0C\u6807\u8BC6B\uFF0C\u6D88\u606F");
		lblNewLabel_17.setBounds(687, 510, 132, 15);
		contentPane.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("\u6D4B\u8BD5\u5185\u5BB9\uFF1A");
		lblNewLabel_18.setBounds(10, 498, 70, 15);
		contentPane.add(lblNewLabel_18);
		
		label_3 = new JLabel("\u6570\u636E\u6E90");
		label_3.setBounds(10, 13, 54, 15);
		contentPane.add(label_3);
		
		lblNewLabel = new JLabel("(\u8017\u65F6\uFF1A0ms)");
		lblNewLabel.setBounds(486, 13, 116, 15);
		contentPane.add(lblNewLabel);
		
		
	}
}
