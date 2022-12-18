package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SM9.G1KeyPair;
import SM9.MasterKeyPair;
import SM9.PrivateKey;
import SM9.PrivateKeyType;
import SM9.ResultKeyExchange;
import SM9.ResultSignature;
import SM9.SM9Utils;

import SM9.KGC;
import SM9.KGCWithStandardTestKey;
import SM9.SM9;
import SM9.SM9Curve;
import SM9.SM9WithStandardTestKey;
import Utils.Serialize;
import Utils.Timer;

import javax.swing.JRadioButton;
import java.awt.TextArea;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigInteger;

public class SM9KeyExchangeTest extends JFrame {

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
					SM9KeyExchangeTest frame = new SM9KeyExchangeTest();
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
	public SM9KeyExchangeTest() {
		setTitle("\u5BC6\u94A5\u4EA4\u6362\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1000, 803);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton radioButton = new JRadioButton("\u6807\u51C6\u6D4B\u8BD5");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("Alice");
				textField_1.setText("Bob");
			}
		});
		radioButton.setBounds(671, 6, 94, 23);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u666E\u901A\u6D4B\u8BD5");
		radioButton_1.setBounds(765, 6, 99, 23);
		contentPane.add(radioButton_1);
		
		ButtonGroup group=new ButtonGroup();
		group.add(radioButton);
		group.add(radioButton_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 348, 472, 407);
		contentPane.add(textArea);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(494, 348, 480, 407);
		contentPane.add(textArea_1);
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setBounds(10, 91, 964, 230);
		contentPane.add(textArea_2);
		
		JLabel lblms = new JLabel("(\u8017\u65F6\uFF1A0ms)");
		lblms.setBounds(58, 70, 139, 15);
		contentPane.add(lblms);
		
		JLabel lblNewLabel = new JLabel("(\u8017\u65F6\uFF1A0ms)");
		lblNewLabel.setBounds(58, 327, 156, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("(\u8017\u65F6\uFF1A0ms)");
		lblNewLabel_1.setBounds(545, 327, 144, 15);
		contentPane.add(lblNewLabel_1);
		
		Timer timer=new Timer();
		
		JButton button = new JButton("\u5F00\u59CB\u6D4B\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(radioButton.isSelected()) && !(radioButton_1.isSelected())) {
						JOptionPane.showMessageDialog(button,"没有选择测试类型！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField.getText().trim().length()<1 || textField_1.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写双方标识！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						double init=0;double atime=0;double btime=0;
						String myId = textField.getText();
				        String othId = textField_1.getText();
						if(radioButton.isSelected()) {
							init=0;atime=0;btime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
							KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
				        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
				        	KGCWithStandardTestKey.k = new BigInteger("02E65B0762D042F51F0D23542B13ED8CFA2E9A0E7206361E013A283905E31F", 16);
				            MasterKeyPair masterKeyPair = kgc.genEncryptMasterKeyPair();
				            init+=timer.stop(0);
				            lblms.setText("(耗时："+String.format("%.2f", init)+"ms)");
				            textArea_2.append("\n加密主私钥 ke:\n");
				            textArea_2.append(masterKeyPair.getPrivateKey().toString());
				            textArea_2.append("\n加密主公钥 Ppub-e:\n");
				            textArea_2.append(masterKeyPair.getPublicKey().toString());
				            textArea.append("\n实体A的标识IDA:");
				            textArea.append(myId);
				            textArea.append("\nIDA的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(myId.getBytes()));
				            timer.start(0);
				            PrivateKey myPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), myId, PrivateKeyType.KEY_KEY_EXCHANGE);
				            atime+=timer.stop(0);
				            textArea.append("\n实体A的加密私钥 de_A:\n");
				            textArea.append(myPrivateKey.toString());
				            textArea_1.append("\n实体B的标识IDB:");
				            textArea_1.append(othId);
				            textArea_1.append("\nIDB的16进制表示:\n");
				            textArea_1.append(SM9Utils.toHexString(othId.getBytes()));
				            timer.start(0);
				            PrivateKey othPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), othId, PrivateKeyType.KEY_KEY_EXCHANGE);
				            btime+=timer.stop(0);
				            textArea_1.append("\n实体B的加密私钥 de_B:\n");
				            textArea_1.append(othPrivateKey.toString());
				            int keyByteLen = 16;
				            textArea.append("\n密钥交换的长度: " + keyByteLen + " bytes\n");
				            textArea_1.append("\n密钥交换的长度: " + keyByteLen + " bytes\n");
				            textArea.append("\n密钥交换步骤A1-A4中的相关值:\n");
				            timer.start(0);
				            SM9WithStandardTestKey.r = new BigInteger("5879DD1D51E175946F23B1B41E93BA31C584AE59A426EC1046A4D03B06C8", 16);
				            G1KeyPair myTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), othId);
				            atime+=timer.stop(0);
				            textArea.append(sm9.sb_keyExchangeInit.toString());
				            textArea_1.append("\n密钥交换步骤B1-B4中的相关值:\n");
				            timer.start(0);
				            SM9WithStandardTestKey.r = new BigInteger("018B98C44BEF9F8537FB7D071B2C928B3BC65BD3D69E1EEE213564905634FE", 16);
				            G1KeyPair othTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), myId);
				            textArea_1.append(sm9.sb_keyExchangeInit.toString());
				            textArea_1.append("\n密钥交换步骤B5-B8中的相关值:\n");
				            ResultKeyExchange othAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), false,
				                    othId, myId, othPrivateKey, othTempKeyPair, myTempKeyPair.getPublicKey(), keyByteLen);
				            btime+=timer.stop(0);
				            textArea_1.append(sm9.sb_keyExchange.toString());
				            textArea.append("\n密钥交换步骤A5-A8中的相关值:\n");
				            timer.start(0);
				            ResultKeyExchange myAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), true,
				                    myId, othId, myPrivateKey, myTempKeyPair, othTempKeyPair.getPublicKey(), keyByteLen);
				            atime+=timer.stop(0);
				            textArea.append(sm9.sb_keyExchange.toString());
				            textArea.append("SA: "+SM9Utils.toHexString(myAgreementKey.getSA2()));
				            textArea.append("S1: "+SM9Utils.toHexString(myAgreementKey.getSB1()));
				            textArea.append("SK: "+SM9Utils.toHexString(myAgreementKey.getSK()));
				            textArea_1.append("S2: "+SM9Utils.toHexString(othAgreementKey.getSA2()));
				            textArea_1.append("SB: "+SM9Utils.toHexString(othAgreementKey.getSB1()));
				            textArea_1.append("SK: "+SM9Utils.toHexString(othAgreementKey.getSK()));
				            boolean isSuccess = true;
				            if(SM9Utils.byteEqual(myAgreementKey.getSA2(), othAgreementKey.getSA2())) 
				            	textArea.append("\nSA = S2\n");
				            else {
				            	textArea.append("SA != S2");
				                isSuccess = false;
				            }
				            if(SM9Utils.byteEqual(myAgreementKey.getSB1(), othAgreementKey.getSB1()))
				            	textArea_1.append("\nS1 = SB\n");
				            else {
				            	textArea_1.append("S1 != SB");
				                isSuccess = false;
				            }
				            if(SM9Utils.byteEqual(myAgreementKey.getSK(), othAgreementKey.getSK())) {
				            	textArea.append("\nSK_A = SK_B\n");textArea_1.append("\nSK_A = SK_B\n");
				            }
				            else {
				            	textArea.append("SK_A != SK_B");textArea_1.append("SK_A != SK_B");
				                isSuccess = false;
				            }
				            if(isSuccess) {
				            	textArea.append("密钥交换成功！");textArea_1.append("密钥交换成功！");
				            }
				            else {
				            	textArea.append("密钥交换失败！");textArea_1.append("密钥交换失败！");
				            }
				            lblNewLabel.setText("(耗时："+String.format("%.2f", atime)+"ms)");
				            lblNewLabel_1.setText("(耗时："+String.format("%.2f", btime)+"ms)");
						}
						else if(radioButton_1.isSelected()) {
							init=0;atime=0;btime=0;
							textArea.setText("");
							textArea_1.setText("");
							textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
							KGC kgc = new KGC(sm9Curve);
				        	SM9 sm9 = new SM9(sm9Curve);
				        	MasterKeyPair masterKeyPair = kgc.genEncryptMasterKeyPair();
				        	init+=timer.stop(0);
				            textArea_2.append("\n加密主私钥 ke:\n");
				            textArea_2.append(masterKeyPair.getPrivateKey().toString());
				            textArea_2.append("\n加密主公钥 Ppub-e:\n");
				            textArea_2.append(masterKeyPair.getPublicKey().toString());
				            lblms.setText("(耗时："+String.format("%.2f", init)+"ms)");
				            textArea.append("\n实体A的标识IDA:");
				            textArea.append(myId);
				            textArea.append("\nIDA的16进制表示:\n");
				            textArea.append(SM9Utils.toHexString(myId.getBytes()));
				            timer.start(0);
				            PrivateKey myPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), myId, PrivateKeyType.KEY_KEY_EXCHANGE);
				            atime+=timer.stop(0);
				            textArea.append("\n实体A的加密私钥 de_A:\n");
				            textArea.append(myPrivateKey.toString());
				            textArea_1.append("\n实体B的标识IDB:");
				            textArea_1.append(othId);
				            textArea_1.append("\nIDB的16进制表示:\n");
				            textArea_1.append(SM9Utils.toHexString(othId.getBytes()));
				            timer.start(0);
				            PrivateKey othPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), othId, PrivateKeyType.KEY_KEY_EXCHANGE);
				            btime+=timer.stop(0);
				            textArea_1.append("\n实体B的加密私钥 de_B:\n");
				            textArea_1.append(othPrivateKey.toString());
				            int keyByteLen = 16;
				            textArea.append("\n密钥交换的长度: " + keyByteLen + " bytes\n");
				            textArea_1.append("\n密钥交换的长度: " + keyByteLen + " bytes\n");
				            textArea.append("\n密钥交换步骤A1-A4中的相关值:\n");
				            timer.start(0);
				            G1KeyPair myTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), othId);
				            atime+=timer.stop(0);
				            textArea.append(sm9.sb_keyExchangeInit.toString());
				            textArea_1.append("\n密钥交换步骤B1-B4中的相关值:\n");
				            timer.start(0);
				            G1KeyPair othTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), myId);
				            textArea_1.append(sm9.sb_keyExchangeInit.toString());
				            textArea_1.append("\n密钥交换步骤B5-B8中的相关值:\n");
				            ResultKeyExchange othAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), false,
				                    othId, myId, othPrivateKey, othTempKeyPair, myTempKeyPair.getPublicKey(), keyByteLen);
				            btime+=timer.stop(0);
				            textArea_1.append(sm9.sb_keyExchange.toString());
				            textArea.append("\n密钥交换步骤A5-A8中的相关值:\n");
				            timer.start(0);
				            ResultKeyExchange myAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), true,
				                    myId, othId, myPrivateKey, myTempKeyPair, othTempKeyPair.getPublicKey(), keyByteLen);
				            atime+=timer.stop(0);
				            textArea.append(sm9.sb_keyExchange.toString());
				            textArea.append("SA: "+SM9Utils.toHexString(myAgreementKey.getSA2()));
				            textArea.append("S1: "+SM9Utils.toHexString(myAgreementKey.getSB1()));
				            textArea.append("SK: "+SM9Utils.toHexString(myAgreementKey.getSK()));
				            textArea_1.append("S2: "+SM9Utils.toHexString(othAgreementKey.getSA2()));
				            textArea_1.append("SB: "+SM9Utils.toHexString(othAgreementKey.getSB1()));
				            textArea_1.append("SK: "+SM9Utils.toHexString(othAgreementKey.getSK()));
				            boolean isSuccess = true;
				            if(SM9Utils.byteEqual(myAgreementKey.getSA2(), othAgreementKey.getSA2()))
				            	textArea.append("\nSA = S2\n");
				            else {
				            	textArea.append("SA != S2");
				                isSuccess = false;
				            }
				            if(SM9Utils.byteEqual(myAgreementKey.getSB1(), othAgreementKey.getSB1()))
				            	textArea_1.append("\nS1 = SB\n");
				            else {
				            	textArea_1.append("S1 != SB");
				                isSuccess = false;
				            }
				            if(SM9Utils.byteEqual(myAgreementKey.getSK(), othAgreementKey.getSK())) {
				            	textArea.append("\nSK_A = SK_B\n");
				            	textArea_1.append("\nSK_A = SK_B\n");
				            }
				            else {
				            	textArea.append("SK_A != SK_B");
				            	textArea_1.append("SK_A != SK_B");
				                isSuccess = false;
				            }
				            if(isSuccess) {
				            	textArea.append("\n密钥交换成功!");textArea_1.append("\n密钥交换成功!");
				            }
				            else {
				            	textArea.append("\n密钥交换失败!");textArea_1.append("\n密钥交换失败!");
				            }
				            lblNewLabel.setText("(耗时："+String.format("%.2f", atime)+"ms)");
				            lblNewLabel_1.setText("(耗时："+String.format("%.2f", btime)+"ms)");
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button.setBounds(870, 6, 104, 23);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(85, 7, 252, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(420, 7, 232, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lbla = new JLabel("\u5B9E\u4F53A\u6807\u8BC6\uFF1A");
		lbla.setBounds(10, 10, 77, 15);
		contentPane.add(lbla);
		
		JLabel lblb = new JLabel("\u5B9E\u4F53B\u6807\u8BC6\uFF1A");
		lblb.setBounds(347, 10, 75, 15);
		contentPane.add(lblb);
		
		JLabel lbla_1 = new JLabel("\u5B9E\u4F53A\uFF1A");
		lbla_1.setBounds(10, 327, 54, 15);
		contentPane.add(lbla_1);
		
		JLabel lblb_1 = new JLabel("\u5B9E\u4F53B\uFF1A");
		lblb_1.setBounds(493, 327, 54, 15);
		contentPane.add(lblb_1);
		
		JLabel label = new JLabel("\u521D\u59CB\u5316\uFF1A");
		label.setBounds(10, 70, 54, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("\u9009\u62E9\u6570\u636E\u5BFC\u51FA\u8DEF\u5F84\uFF1A");
		lblNewLabel_2.setBounds(10, 38, 126, 15);
		contentPane.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(125, 38, 252, 21);
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
		button_1.setBounds(387, 38, 37, 23);
		contentPane.add(button_1);
		
		JButton btnNewButton = new JButton("\u6570\u636E\u5BFC\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField.getText().trim().length()<1 || textField_1.getText().trim().length()<1) {
						JOptionPane.showMessageDialog(button,"没有填写双方标识（A、B）！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else if(textField_2.getText().trim().length()<1){
						JOptionPane.showMessageDialog(button,"没有选择导出路径！","错误",JOptionPane.ERROR_MESSAGE);
					}
					else {
						String savepath=textField_2.getText();
						String id_A=textField.getText();
						String id_B=textField_1.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	KGCWithStandardTestKey.k = new BigInteger("02E65B0762D042F51F0D23542B13ED8CFA2E9A0E7206361E013A283905E31F", 16);
			        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/exc_msk.out");
			        	MasterKeyPair masterKeyPair = kgc.genEncryptMasterKeyPair();
			        	PrivateKey aPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_KEY_EXCHANGE);
			        	PrivateKey bPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_KEY_EXCHANGE);
			        	Serialize.serializeSk(aPrivateKey, savepath+"/exc_ska.out");
			        	Serialize.serializeSk(bPrivateKey, savepath+"/exc_skb.out");
			        	SM9WithStandardTestKey.r = new BigInteger("5879DD1D51E175946F23B1B41E93BA31C584AE59A426EC1046A4D03B06C8", 16);
			        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/exc_ra.out");
			            G1KeyPair aTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_B);
			            SM9WithStandardTestKey.r = new BigInteger("018B98C44BEF9F8537FB7D071B2C928B3BC65BD3D69E1EEE213564905634FE", 16);
			            Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/exc_rb.out");
			            G1KeyPair bTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_A);
			            ResultKeyExchange bAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), false,
			                    id_B, id_A, bPrivateKey, bTempKeyPair, aTempKeyPair.getPublicKey(), 16);
			            ResultKeyExchange aAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), true,
			                    id_A, id_B, aPrivateKey, aTempKeyPair, bTempKeyPair.getPublicKey(), 16);
			            Serialize.serializeRekeyexchange(bAgreementKey, savepath+"/excb.out");
			            Serialize.serializeRekeyexchange(aAgreementKey, savepath+"/exca.out");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(442, 38, 93, 23);
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
			        		String id_A=i+"";String id_B=i+"";
			        		KGCWithStandardTestKey.k = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/exc_msk/msk"+i+".out");
				        	MasterKeyPair masterKeyPair = kgc.genEncryptMasterKeyPair();
				        	PrivateKey aPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_KEY_EXCHANGE);
				        	PrivateKey bPrivateKey = kgc.genPrivateKey(masterKeyPair.getPrivateKey(), id_B, PrivateKeyType.KEY_KEY_EXCHANGE);
				        	Serialize.serializeSk(aPrivateKey, savepath+"/exc_ska/ska"+i+".out");
				        	Serialize.serializeSk(bPrivateKey, savepath+"/exc_skb/skb"+i+".out");
				        	SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/exc_ra/ra"+i+".out");
				            G1KeyPair aTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_B);
				            SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				            Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/exc_rb/rb"+i+".out");
				            G1KeyPair bTempKeyPair = sm9.keyExchangeInit(masterKeyPair.getPublicKey(), id_A);
				            ResultKeyExchange bAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), false,
				                    id_B, id_A, bPrivateKey, bTempKeyPair, aTempKeyPair.getPublicKey(), 16);
				            ResultKeyExchange aAgreementKey = sm9.keyExchange(masterKeyPair.getPublicKey(), true,
				                    id_A, id_B, aPrivateKey, aTempKeyPair, bTempKeyPair.getPublicKey(), 16);
				            Serialize.serializeRekeyexchange(bAgreementKey, savepath+"/excb_data/excb"+i+".out");
				            Serialize.serializeRekeyexchange(aAgreementKey, savepath+"/exca_data/exca"+i+".out");
			        	}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(559, 38, 130, 23);
		contentPane.add(btnNewButton_1);
	}
}
