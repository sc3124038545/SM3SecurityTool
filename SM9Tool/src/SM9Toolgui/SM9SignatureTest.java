package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SM9.SM9Utils;
import SM9.SM9WithStandardTestKey;
import SM9.PrivateKey;
import SM9.PrivateKeyType;
import SM9.ResultSignature;

import SM9.MasterKeyPair;

import SM9.SM9Curve;
import SM9.KGC;
import SM9.KGCWithStandardTestKey;
import SM9.SM9;
import Utils.Serialize;
import Utils.Timer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.TextArea;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Label;


public class SM9SignatureTest extends JFrame {

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
					SM9SignatureTest frame = new SM9SignatureTest();
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
	public SM9SignatureTest() {
		setTitle("\u7B7E\u540D\u6D4B\u8BD5");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 1004, 759);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton radioButton = new JRadioButton("\u6807\u51C6\u6D4B\u8BD5");
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("Alice");
				textField_1.setText("Chinese IBS standard");
			}
		});
		radioButton.setBounds(568, 6, 90, 23);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u666E\u901A\u6D4B\u8BD5");
		radioButton_1.setBounds(672, 6, 90, 23);
		contentPane.add(radioButton_1);
		
		ButtonGroup g=new ButtonGroup();
		g.add(radioButton);
		g.add(radioButton_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 272, 480, 439);
		contentPane.add(textArea);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(496, 272, 486, 439);
		contentPane.add(textArea_1);
		
		TextArea textArea_2 = new TextArea();
		textArea_2.setBounds(10, 94, 972, 151);
		contentPane.add(textArea_2);
		
		Label label_4 = new Label("(\u8017\u65F6\uFF1A0ms)");
		label_4.setBounds(73, 251, 210, 23);
		contentPane.add(label_4);
		
		Label label_5 = new Label("(\u8017\u65F6\uFF1A0ms)");
		label_5.setBounds(558, 251, 139, 23);
		contentPane.add(label_5);
		
		Label label_7 = new Label("(\u8017\u65F6\uFF1A0ms)");
		label_7.setBounds(60, 65, 130, 23);
		contentPane.add(label_7);
		
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
						double signtime;double verifytime;double init;
						String id_A =textField.getText();
						String msg =textField_1.getText();
						if(radioButton.isSelected()) {
							init=0;signtime=0;verifytime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
				        	KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
				        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
				        	KGCWithStandardTestKey.k= new BigInteger("0130E78459D78545CB54C587E02CF480CE0B66340F319F348A1D5B1F2DC5F4", 16);
				        	MasterKeyPair signMasterKeyPair = kgc.genSignMasterKeyPair();
				        	init+=timer.stop(0);
				            label_7.setText("(耗时："+String.format("%.2f", init)+"ms)");
					        textArea_2.append("\n签名主私钥 ks:");
					        textArea_2.append(signMasterKeyPair.getPrivateKey().toString());
					        textArea_2.append("\n签名主公钥 Ppub-s:");
					        textArea_2.append(signMasterKeyPair.getPublicKey().toString());
					        textArea.append("\n实体A的标识IDA:");
					        textArea.append(id_A);
					        textArea.append("\nIDA的16进制表示:\n");
					        textArea.append(SM9Utils.toHexString(id_A.getBytes()));
					        timer.start(0);
					        PrivateKey signPrivateKey = kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
					        signtime+=timer.stop(0);
					        textArea.append(kgc.sb.toString());
					        textArea.append("\n签名私钥 ds_A:");
					        textArea.append(signPrivateKey.toString());
					        textArea.append("\n签名步骤中的相关值:\n");
					        textArea.append("\n待签名消息 M:");
					        textArea.append(msg);
					        textArea.append("\nM的16进制表示:\n");
					        textArea.append(SM9Utils.toHexString(msg.getBytes()));
					        SM9WithStandardTestKey.r = new BigInteger("033C8616B06704813203DFD00965022ED15975C662337AED648835DC4B1CBE", 16);
					        timer.start(0);
					        ResultSignature signature = sm9.sign(signMasterKeyPair.getPublicKey(), signPrivateKey, msg.getBytes());
					        signtime+=timer.stop(0);
					        textArea.append(sm9.sb_sign.toString());
					        textArea.append("\n消息M的签名为(h,s):\n");
					        textArea.append(signature.toString());
					        label_4.setText("(耗时："+String.format("%.2f", signtime)+"ms)");
					        textArea_1.append("\n验证步骤中的相关值:\n");
					        timer.start(0);
					        boolean yz=sm9.verify(signMasterKeyPair.getPublicKey(), id_A, msg.getBytes(), signature);
					        verifytime+=timer.stop(0);
					        textArea_1.append(sm9.sb_verify.toString());
					        if(yz)
					        	textArea_1.append("\n验证通过！");
					        else
					        	textArea_1.append("\n验证失败！");
					        label_5.setText("(耗时："+String.format("%.2f", verifytime)+"ms)");
						}
						else if(radioButton_1.isSelected()) {
							init=0;signtime=0;verifytime=0;
							textArea.setText("");textArea_1.setText("");textArea_2.setText("");
							timer.start(0);
							SM9Curve sm9Curve = new SM9Curve();
							textArea_2.append(sm9Curve.toString());
				        	KGC kgc = new KGC(sm9Curve);SM9 sm9 = new SM9(sm9Curve);
				        	MasterKeyPair signMasterKeyPair = kgc.genSignMasterKeyPair();
				        	init+=timer.stop(0);
				            label_7.setText("(耗时："+String.format("%.2f", init)+"ms)");
					        textArea_2.append("\n签名主私钥 ks:");
					        textArea_2.append(signMasterKeyPair.getPrivateKey().toString());
					        textArea_2.append("\n签名主公钥 Ppub-s:");
					        textArea_2.append(signMasterKeyPair.getPublicKey().toString());
					        textArea.append("\n实体A的标识IDA:");
					        textArea.append(id_A);
					        textArea.append("\nIDA的16进制表示:\n");
					        textArea.append(SM9Utils.toHexString(id_A.getBytes()));
					        PrivateKey signPrivateKey = kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
					        textArea.append(kgc.sb.toString());
					        textArea.append("\n签名私钥 ds_A:");
					        textArea.append(signPrivateKey.toString());
					        textArea.append("\n签名步骤中的相关值:\n");
					        textArea.append("\n待签名消息 M:");
					        textArea.append(msg);
					        textArea.append("\nM的16进制表示:\n");
					        textArea.append(SM9Utils.toHexString(msg.getBytes()));
					        timer.start(0);
					        ResultSignature signature = sm9.sign(signMasterKeyPair.getPublicKey(), signPrivateKey, msg.getBytes());
					        signtime+=timer.stop(0);
					        textArea.append(sm9.sb_sign.toString());
					        textArea.append("\n消息M的签名为(h,s):\n");
					        textArea.append(signature.toString());
					        label_4.setText("(耗时："+String.format("%.2f", signtime)+"ms)");
					        textArea_1.append("\n验证步骤中的相关值:\n");
					        timer.start(0);
					        boolean yz=sm9.verify(signMasterKeyPair.getPublicKey(), id_A, msg.getBytes(), signature);
					        verifytime+=timer.stop(0);
					        textArea_1.append(sm9.sb_verify.toString());
					        if(yz)
					        	textArea_1.append("验证通过！");
					        else
					        	textArea_1.append("验证失败！");
					        label_5.setText("(耗时："+String.format("%.2f", verifytime)+"ms)");
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button.setBounds(768, 6, 106, 23);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.setBounds(57, 7, 226, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u6807\u8BC6\uFF1A");
		label.setBounds(10, 10, 54, 15);
		contentPane.add(label);
		

		
		JLabel label_1 = new JLabel("\u6D88\u606F\uFF1A");
		label_1.setBounds(293, 10, 43, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(333, 7, 210, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_2 = new JLabel("\u7B7E\u540D\u7ED3\u679C\uFF1A");
		label_2.setBounds(10, 251, 68, 15);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u9A8C\u8BC1\u7ED3\u679C\uFF1A");
		label_3.setBounds(496, 251, 68, 15);
		contentPane.add(label_3);
		
		JLabel label_6 = new JLabel("\u521D\u59CB\u5316\uFF1A");
		label_6.setBounds(10, 65, 54, 15);
		contentPane.add(label_6);
		
		JLabel label_8 = new JLabel("\u9009\u62E9\u6570\u636E\u5BFC\u51FA\u8DEF\u5F84\uFF1A");
		label_8.setBounds(10, 38, 130, 15);
		contentPane.add(label_8);
		
		textField_2 = new JTextField();
		textField_2.setEnabled(false);
		textField_2.setBounds(129, 38, 254, 21);
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
		button_1.setBounds(397, 38, 43, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("\u6570\u636E\u5BFC\u51FA");
		button_2.addActionListener(new ActionListener() {
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
						String id_A=textField.getText();
			        	String msg=textField_1.getText();
						SM9Curve sm9Curve = new SM9Curve();
						KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
			        	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
			        	KGCWithStandardTestKey.k= new BigInteger("0130E78459D78545CB54C587E02CF480CE0B66340F319F348A1D5B1F2DC5F4", 16);
			        	MasterKeyPair signMasterKeyPair = kgc.genSignMasterKeyPair();
			        	PrivateKey signPrivateKey = kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
			        	SM9WithStandardTestKey.r = new BigInteger("033C8616B06704813203DFD00965022ED15975C662337AED648835DC4B1CBE", 16);
			        	ResultSignature signature = sm9.sign(signMasterKeyPair.getPublicKey(), signPrivateKey, msg.getBytes());
			        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/sign_msk.out");
			        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/sign_random.out");
			        	Serialize.serializeSk(signPrivateKey, savepath+"/sign_sk.out");
			        	Serialize.serializeResign(signature, savepath+"/sign.out");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		button_2.setBounds(464, 38, 93, 23);
		contentPane.add(button_2);
		
		JButton btnNewButton = new JButton("\u6279\u91CF\u6570\u636E\u5BFC\u51FA");
		btnNewButton.addActionListener(new ActionListener() {
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
						for(int i=0;i<50;i++) {
							String id_A=i+"";String msg=i+"";
							KGCWithStandardTestKey.k= SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	MasterKeyPair signMasterKeyPair = kgc.genSignMasterKeyPair();
				        	PrivateKey signPrivateKey = kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
				        	SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
				        	ResultSignature signature = sm9.sign(signMasterKeyPair.getPublicKey(), signPrivateKey, msg.getBytes());
				        	Serialize.serializeMsk(KGCWithStandardTestKey.k, savepath+"/sign_msk/msk"+i+".out");
				        	Serialize.serializeR(SM9WithStandardTestKey.r, savepath+"/sign_random/random"+i+".out");
				        	Serialize.serializeSk(signPrivateKey, savepath+"/sign_sk/sk"+i+".out");
				        	Serialize.serializeResign(signature, savepath+"/sign_data/signdata"+i+".out");
						}
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(578, 37, 119, 23);
		contentPane.add(btnNewButton);
		
		
		/*SM9Curve sm9Curve = new SM9Curve();
		KGCWithStandardTestKey kgc = new KGCWithStandardTestKey(sm9Curve);
    	SM9WithStandardTestKey sm9 = new SM9WithStandardTestKey(sm9Curve);
    	String id_A="AAA";
    	ArrayList<Double> signtime=new ArrayList<Double>();
    	ArrayList<Double> verifytime=new ArrayList<Double>();
    	Timer timer=new Timer();
    	double time1;double time2=0;
    	String msg="";
    	for(int i=0;i<10;i++) {
    		msg=msg+"AAAAAAAA";
    		time1=0;time2=0;
			KGCWithStandardTestKey.k= SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
        	MasterKeyPair signMasterKeyPair = kgc.genSignMasterKeyPair();
        	PrivateKey signPrivateKey = kgc.genPrivateKey(signMasterKeyPair.getPrivateKey(), id_A, PrivateKeyType.KEY_SIGN);
        	SM9WithStandardTestKey.r = SM9Utils.genRandom(sm9Curve.random, sm9Curve.N);
        	timer.start(0);
        	ResultSignature signature = sm9.sign(signMasterKeyPair.getPublicKey(), signPrivateKey, msg.getBytes());
        	System.out.println(timer.stop(0));
        	System.out.println("");
        	timer.start(0);
        	sm9.verify(signMasterKeyPair.getPublicKey(), id_A, msg.getBytes(), signature);
        	System.out.println(timer.stop(0));
    	}*/
	}
}
