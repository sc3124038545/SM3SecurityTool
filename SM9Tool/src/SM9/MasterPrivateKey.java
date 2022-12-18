package SM9;

import java.io.Serializable;
import java.math.BigInteger;

public class MasterPrivateKey implements Serializable{

	BigInteger d;

    public MasterPrivateKey(BigInteger d) {
        this.d = d;
    }

    public static MasterPrivateKey fromByteArray(byte[] source) {
        BigInteger d = new BigInteger(1, source);
        return new MasterPrivateKey(d);
    }

    public byte[] toByteArray() {
        return SM9Utils.BigIntegerToBytes(d, SM9CurveParameters.nBits/8);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(SM9Utils.NEW_LINE);
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(d, SM9CurveParameters.nBits/8)));

        return sb.toString();
    }
}
