package Utils;

/**
 * @Author: ZeiCiji
 * @Description:String �� byte����֮��ģ�ʮ�����ƣ�ת��.
 * @Date:2019��9��25��
 */
public class Hex {
	/**
	 * @param  ԭʼ����.
	 * @return ʮ�������ַ���.
	 */
	public static String encodeToString(byte[] data) {
		return encodeToString(data, false);
	}
	/**
	 * @param  ԭʼ����.
	 * @return ʮ�������ַ���.
	 */
	public static String encodeToString(byte[] data, boolean isUpperCase) {
		char[] digital = "0123456789abcdef".toCharArray();
		if(isUpperCase)
			digital = "0123456789ABCDEF".toCharArray();
		StringBuffer sb = new StringBuffer("");
		int bit;
		for (int i = 0; i < data.length; i++) {
			bit = (data[i] & 0xF0) >> 4;
			sb.append(digital[bit]);
			bit = data[i] & 0x0F;
			sb.append(digital[bit]);
		}
		return sb.toString();
	}

	/**
	 * @param  ԭʼ����.
	 * @return ʮ����������.
	 */
	public static byte[] encode(byte[] data) {
		return encodeToString(data).getBytes();
	}

	/**
	 * @param  ԭʼ����.
	 * @return ʮ����������.
	 */
	public static byte[] encode(byte[] data, boolean isUpperCase) {
		return encodeToString(data, isUpperCase).getBytes();
	}


	/**
	 * String ���͵�ʮ�������ַ���ת��Ϊ byte �ڴ�����
	 * 
	 * @param  String ���͵�ʮ�������ַ���.
	 * @return byte �ڴ�����.
	 */
	public static byte[] decode(String hex) {
		String digital = "0123456789abcdef";
		char[] hex2char = hex.toLowerCase().toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int temp;
		for (int i = 0; i < bytes.length; i++) {
			temp = digital.indexOf(hex2char[2 * i]) << 4;
			temp += digital.indexOf(hex2char[2 * i + 1]);
			bytes[i] = (byte) (temp & 0xFF);
		}
		return bytes;
	}
}
