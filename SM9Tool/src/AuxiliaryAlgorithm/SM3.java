package AuxiliaryAlgorithm;

import Utils.GMProvider;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class SM3 {

	public static final int DIGEST_SIZE = 32;

    private SM3()
    {
    }

    /**
     * ����SM3�㷨��ȡ��ϢժҪ
     * @return SM3 ��ϢժҪ
     * @throws NoSuchAlgorithmException
     */
    public static MessageDigest getInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SM3", GMProvider.getProvider());
    }
}
