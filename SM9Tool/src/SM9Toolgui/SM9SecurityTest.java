package SM9Toolgui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SM9.SM9;
import SM9.SM9Curve;
import SM9.SM9CurveParameters;
import SM9.SM9Utils;
import Utils.Timer;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.TextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;

public class SM9SecurityTest extends JFrame {

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
					SM9SecurityTest frame = new SM9SecurityTest();
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
	public SM9SecurityTest() {
		setTitle("\u5B89\u5168\u6027\u6D4B\u8BD5");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 669);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(137, 7, 360, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setBounds(10, 270, 569, 356);
		contentPane.add(textArea_1);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(10, 133, 569, 102);
		contentPane.add(textArea);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5355\u6BD4\u7279\u9891\u7387\u6D4B\u8BD5", "\u5757\u5185\u9891\u6570\u6D4B\u8BD5", "\u6E38\u7A0B\u603B\u6570\u6D4B\u8BD5", "\u5757\u5185\u6700\u957F\"1\"\u6E38\u7A0B\u6D4B\u8BD5", "\u77E9\u9635\u79E9\u6D4B\u8BD5", "\u79BB\u6563\u5085\u91CC\u53F6\u53D8\u6362\u6D4B\u8BD5", "Maurer\u901A\u7528\u7EDF\u8BA1\u6D4B\u8BD5", "\u7EBF\u6027\u590D\u6742\u5EA6\u6D4B\u8BD5", "\u91CD\u53E0\u5B50\u5E8F\u5217\u6D4B\u8BD5", "\u8FD1\u4F3C\u71B5\u6D4B\u8BD5", "\u7D2F\u79EF\u548C\u6D4B\u8BD5", "\u6251\u514B\u6D4B\u8BD5", "\u6E38\u7A0B\u5206\u5E03\u6D4B\u8BD5", "\u4E8C\u5143\u63A8\u5BFC\u6D4B\u8BD5", "\u81EA\u76F8\u5173\u6D4B\u8BD5"}));
		comboBox.setBounds(137, 31, 158, 21);
		contentPane.add(comboBox);

		String SignRandom=new BigInteger("033C8616B06704813203DFD00965022ED15975C662337AED648835DC4B1CBE", 16).toString(2);
		String KeyExchangeRandom1=new BigInteger("5879DD1D51E175946F23B1B41E93BA31C584AE59A426EC1046A4D03B06C8", 16).toString(2);
		String KeyExchangeRandom2=new BigInteger("018B98C44BEF9F8537FB7D071B2C928B3BC65BD3D69E1EEE213564905634FE", 16).toString(2);
		String EncapsulateRandom=new BigInteger("74015F8489C01EF4270456F9E6475BFB602BDE7F33FD482AB4E3684A6722", 16).toString(2);
		String EncryptRandom=new BigInteger("AAC0541779C8FC45E3E2CB25C12B5D2576B2129AE8BB5EE2CBE5EC9E785C", 16).toString(2);

		
		ButtonGroup group=new ButtonGroup();
	

		JLabel lblNewLabel = new JLabel("\u5355 \u5143 \u6D4B \u8BD5 \u9009 \u62E9\uFF1A");
		lblNewLabel.setBounds(10, 34, 117, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u5F85\u6D4B\u968F\u673A\u6570\uFF1A");
		label.setBounds(10, 112, 79, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u6D4B\u8BD5\u7ED3\u679C\uFF1A");
		label_1.setBounds(10, 249, 79, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u9636\u6BB5\u968F\u673A\u6570\u9009\u62E9\uFF1A");
		label_2.setBounds(10, 62, 117, 15);
		contentPane.add(label_2);
		
		JLabel lblNewLabel_1 = new JLabel("\u6D4B\u8BD5\u811A\u672C\u6587\u4EF6\u5939\u8DEF\u5F84\uFF1A");
		lblNewLabel_1.setBounds(10, 10, 146, 15);
		contentPane.add(lblNewLabel_1);

		JButton button_2 = new JButton("...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fileChooser.showOpenDialog(fileChooser);
				if(returnVal == JFileChooser.APPROVE_OPTION){
				String filepath= fileChooser.getSelectedFile().getAbsolutePath();
				textField.setText(filepath);
				}
			}
		});
		button_2.setBounds(507, 6, 35, 23);
		contentPane.add(button_2);
		
		JRadioButton radioButton_4 = new JRadioButton("\u968F\u673A\u751F\u6210");
		radioButton_4.setBounds(481, 58, 108, 23);
		contentPane.add(radioButton_4);
		group.add(radioButton_4);

		
		JRadioButton radioButton = new JRadioButton("\u7B7E\u540D");
		radioButton.setBounds(137, 58, 69, 23);
		contentPane.add(radioButton);
		radioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				textArea.append("标准测试中签名步骤的随机值：\n");
				textArea.append("033C8616B06704813203DFD00965022ED15975C662337AED648835DC4B1CBE\n");
				textArea.append("对应的二进制：\n"+SignRandom);
			}
		});
		group.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("\u5BC6\u94A5\u4EA4\u6362");
		radioButton_1.setBounds(219, 58, 87, 23);
		contentPane.add(radioButton_1);
		radioButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				textArea.append("标准测试中密钥交换步骤的随机值：\n");
				textArea.append("用户A:5879DD1D51E175946F23B1B41E93BA31C584AE59A426EC1046A4D03B06C8\n");
				textArea.append("用户B:018B98C44BEF9F8537FB7D071B2C928B3BC65BD3D69E1EEE213564905634FE\n");
				textArea.append("对应的二进制：\n用户A:"+KeyExchangeRandom1+"\n用户B:"+KeyExchangeRandom2);
			}
		});
		group.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("\u5BC6\u94A5\u5C01\u88C5");
		radioButton_2.setBounds(316, 58, 94, 23);
		contentPane.add(radioButton_2);
		radioButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				textArea.append("标准测试中密钥封装步骤的随机值：\n");
				textArea.append("74015F8489C01EF4270456F9E6475BFB602BDE7F33FD482AB4E3684A6722\n");
				textArea.append("对应的二进制：\n"+EncapsulateRandom);
			}
		});
		group.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("\u52A0\u5BC6");
		radioButton_3.setBounds(412, 58, 67, 23);
		contentPane.add(radioButton_3);
		radioButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");
				textArea.append("标准测试中加密步骤的随机值：\n");
				textArea.append("AAC0541779C8FC45E3E2CB25C12B5D2576B2129AE8BB5EE2CBE5EC9E785C\n");
				textArea.append("对应的二进制：\n"+EncryptRandom);
			}
		});
		group.add(radioButton_3);
		JButton button = new JButton("\u5355\u5143\u6D4B\u8BD5");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().length()<1) {
					JOptionPane.showMessageDialog(button,"没有选择脚本文件夹！","错误",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(radioButton.isSelected()) && !(radioButton_1.isSelected()) &&!(radioButton_2.isSelected()) &&!(radioButton_3.isSelected()) && !(radioButton_4.isSelected())) {
					JOptionPane.showMessageDialog(button,"没有选择阶段的随机数！","错误",JOptionPane.ERROR_MESSAGE);
				}
				String testpath=textField.getText();
				switch(comboBox.getSelectedIndex()) {
				case 0:testpath+="\\MonobitTest.py";break;
				case 1:testpath+="\\FrequencyWithinBlockTest.py";break;
				case 2:testpath+="\\RunsTest.py";break;
				case 3:testpath+="\\LongestRunOnesInBlockTest.py";break;
				case 4:testpath+="\\BinaryMatrixRankTest.py";break;
				case 5:testpath+="\\DFTTest.py";break;
				case 6:testpath+="\\MaurersUniversalTest.py";break;
				case 7:testpath+="\\LinearComplexityTest.py";break;
				case 8:testpath+="\\SerialTest.py";break;
				case 9:testpath+="\\ApproximateEntropyTest.py";break;
				case 10:testpath+="\\CumulativeSumsTest.py";break;
				case 11:testpath+="\\PukeTest.py";break;
				case 12:testpath+="\\RunsDistributionTest.py";break;
				case 13:testpath+="\\BinaryDerivativeTest.py";break;
				case 14:testpath+="\\AutocorrelationTest.py";break;
				}
				String[] arguments= new String[] {"python", testpath,SignRandom};
				try {
					if(radioButton.isSelected()) {
						arguments= new String[] {"python", testpath,SignRandom};
					}
					else if(radioButton_1.isSelected()) {
						arguments= new String[] {"python", testpath,KeyExchangeRandom2};
					}
					else if(radioButton_2.isSelected()) {
						arguments= new String[] {"python", testpath,EncapsulateRandom};
					}
					else if(radioButton_3.isSelected()) {
						arguments= new String[] {"python", testpath,EncryptRandom};
					}
					else if(radioButton_4.isSelected()) {
						SM9Curve mCurve=new SM9Curve();
						BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
						String rto16=SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8));
						String rto2=r.toString(2);
						textArea.setText("");
						textArea.append("普通测试中生成的随机值：\n");
						textArea.append(rto16+"\n");
						textArea.append("对应的二进制：\n"+rto2);
						arguments= new String[] {"python", testpath,rto2};
					}
					Process process = Runtime.getRuntime().exec(arguments);
				    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
				    String line = null;
				    textArea_1.setText("");
					while ((line = in.readLine()) != null) {  
					      //System.out.println(line); 
					    textArea_1.append(line);
					    textArea_1.append("\n");
					} 
					
					in.close();
					//process.waitFor()返回值为0表示我们调用python脚本成功，返回值为1表示调用python脚本失败
					int re = process.waitFor();
					if(re==1) {textArea_1.append("调用脚本失败！");}
					//System.out.println(re);
				} catch (Exception e1) {
					e1.printStackTrace();
				}  
			}
		});
		button.setBounds(332, 30, 93, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u6574\u4F53\u6D4B\u8BD5");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().trim().length()<1) {
					JOptionPane.showMessageDialog(button,"没有选择脚本文件夹！","错误",JOptionPane.ERROR_MESSAGE);
				}
				else if(!(radioButton.isSelected()) && !(radioButton_1.isSelected()) && !(radioButton_2.isSelected()) && !(radioButton_3.isSelected()) && !(radioButton_4.isSelected())) {
					JOptionPane.showMessageDialog(button,"没有选择阶段的随机数！","错误",JOptionPane.ERROR_MESSAGE);
				}
				String testpath=textField.getText();
				String[] arguments= new String[] {"python", testpath+"\\AllTest.py",SignRandom};
				try {
					if(radioButton.isSelected()) {
						arguments= new String[] {"python", testpath+"\\AllTest.py",SignRandom};
					}
					else if(radioButton_1.isSelected()) {
						arguments= new String[] {"python", testpath+"\\AllTest.py",KeyExchangeRandom2};
					}
					else if(radioButton_2.isSelected()) {
						arguments= new String[] {"python", testpath+"\\AllTest.py",EncapsulateRandom};
					}
					else if(radioButton_3.isSelected()) {
						arguments= new String[] {"python", testpath+"\\AllTest.py",EncryptRandom};
					}
					else if(radioButton_4.isSelected()) {
						SM9Curve mCurve=new SM9Curve();
						BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
						String rto16=SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8));
						String rto2=r.toString(2);
						textArea.setText("");
						textArea.append("普通测试中生成的随机值：\n");
						textArea.append(rto16.replace(" ", "")+"\n");
						textArea.append("对应的二进制：\n"+rto2);
						arguments= new String[] {"python", testpath+"\\AllTest.py",rto2};
					}
					Timer timer=new Timer();
					timer.start(0);
					Process process = Runtime.getRuntime().exec(arguments);
				    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
				    String line = null;
				    textArea_1.setText("");
					while ((line = in.readLine()) != null) {  
					      //System.out.println(line); 
					    textArea_1.append(line);
					    textArea_1.append("\n");
					} 
					in.close();
					double endtime=0;
					endtime+=timer.stop(0);
					System.out.println(endtime);
					//process.waitFor()返回值为0表示我们调用python脚本成功，返回值为1表示调用python脚本失败
					int re = process.waitFor();
					if(re==1) {textArea_1.append("调用脚本失败！");}
					//System.out.println(re);
				} catch (Exception e1) {
					e1.printStackTrace();
				}  
			}
		});
		button_1.setBounds(449, 30, 93, 23);
		contentPane.add(button_1);
		
		JLabel label_3 = new JLabel("\u901A\u8FC7\u7387\u6D4B\u8BD5\uFF1A");
		label_3.setBounds(10, 87, 106, 15);
		contentPane.add(label_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 84, 134, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u6D4B\u8BD5\u6B21\u6570\uFF08<500\uFF09:");
		lblNewLabel_2.setBounds(137, 87, 158, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("\u901A\u8FC7\u7387\u6D4B\u8BD5");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText("");
				SM9Curve mCurve=new SM9Curve();
				List<String> list1=new ArrayList<String>();
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
				if(!pattern.matcher(textField_1.getText().toString()).matches()) {
					JOptionPane.showMessageDialog(button,"输入的次数不为正整数！","错误",JOptionPane.ERROR_MESSAGE);
				}
				else if(textField_1.getText()==null) {
					JOptionPane.showMessageDialog(button,"没有输入测试次数！","错误",JOptionPane.ERROR_MESSAGE);
				}
				int n=Integer.parseInt(textField_1.getText().toString());
				if(n>500) {
					JOptionPane.showMessageDialog(button,"测试次数超过限制！","错误",JOptionPane.ERROR_MESSAGE);
				}
				for(int i=0;i<n;i++) {
					BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
					String rto16=SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8));
					list1.add(rto16.replace(" ", ""));
				}
				String testpath=textField.getText();
				String[] arguments= new String[] {"python", testpath+"\\AllPassRateTest.py",list1.toString()};
				try {
		            Process process = Runtime.getRuntime().exec(arguments);
		            BufferedReader in=new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
		            String line;
		            while ((line=in.readLine())!=null){
		            	textArea_1.append(line);
					    textArea_1.append("\n");
		            }
		            in.close();
		            int re=process.waitFor();
		            if(re==1) {textArea_1.append("调用脚本失败！");}
		        } catch (IOException e2) {
		            e2.printStackTrace();
		        } catch (InterruptedException e3) {
		            e3.printStackTrace();
		        }
			}
		});
		btnNewButton.setBounds(412, 83, 130, 23);
		contentPane.add(btnNewButton);
		
	}
}
