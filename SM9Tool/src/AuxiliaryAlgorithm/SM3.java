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
     * 利用SM3算法获取消息摘要
     * @return SM3 消息摘要
     * @throws NoSuchAlgorithmException
     */
    public static MessageDigest getInstance() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SM3", GMProvider.getProvider());
    }
}
