package SM9;
import java.math.BigInteger;

import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;

public class KGC {

protected SM9Curve mCurve;

	public StringBuffer sb=new StringBuffer();

    public KGC(SM9Curve curve) {
        mCurve = curve;
    }

    public SM9Curve getCurve() {
        return mCurve;
    }

    public MasterKeyPair genSignMasterKeyPair()
    {
        BigInteger ks = SM9Utils.genRandom(mCurve.random, mCurve.N);
        CurveElement ppubs = mCurve.P2.duplicate().mul(ks);
        return new MasterKeyPair(new MasterPrivateKey(ks), new MasterPublicKey(ppubs, true));
    }

    public MasterKeyPair genEncryptMasterKeyPair()
    {
        BigInteger ke = SM9Utils.genRandom(mCurve.random, mCurve.N);
        CurveElement ppube = mCurve.P1.duplicate().mul(ke);
        return new MasterKeyPair(new MasterPrivateKey(ke), new MasterPublicKey(ppube, false));
    }

    protected BigInteger T2(MasterPrivateKey privateKey, String id, byte hid) throws Exception {
        BigInteger h1 = SM9Utils.H1(id, hid, mCurve.N);
        sb.append("H1:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h1)));
        BigInteger t1 = h1.add(privateKey.d).mod(mCurve.N);
        if(t1.equals(BigInteger.ZERO))
            throw new Exception("需要更新主密钥");

        sb.append("\nt1:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(t1)));
        return privateKey.d.multiply(t1.modInverse(mCurve.N)).mod(mCurve.N);
    }

    /**
     * Generate SM9 private key.
     *
     * @param masterPrivateKey 主私钥
     * @param id 用户标识
     * @param privateKeyType 主私钥类型
     * @return 主私钥
     */
    public PrivateKey genPrivateKey(MasterPrivateKey masterPrivateKey, String id, PrivateKeyType privateKeyType) throws Exception {
        if(privateKeyType==PrivateKeyType.KEY_SIGN)
            return genSignPrivateKey(masterPrivateKey, id);
        else if(privateKeyType==PrivateKeyType.KEY_KEY_EXCHANGE)
            return genEncryptPrivateKey(masterPrivateKey, id, SM9Curve.HID_KEY_EXCHANGE);
        else if(privateKeyType==PrivateKeyType.KEY_ENCRYPT)
            return genEncryptPrivateKey(masterPrivateKey, id, SM9Curve.HID_ENCRYPT);
        else
            throw new Exception("Not support private key type");
    }

    PrivateKey genSignPrivateKey(MasterPrivateKey privateKey, String id) throws Exception {
        BigInteger t2 = T2(privateKey, id, SM9Curve.HID_SIGN);
        sb.append("\nt2:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(t2)));
        CurveElement ds = mCurve.P1.duplicate().mul(t2);
        return new PrivateKey(ds, SM9Curve.HID_SIGN,id);
    }

    PrivateKey genEncryptPrivateKey(MasterPrivateKey privateKey, String id, byte hid) throws Exception {
        BigInteger t2 = T2(privateKey, id, hid);
        sb.append("\nt2:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(t2)));
        CurveElement de = mCurve.P2.duplicate().mul(t2);
        return new PrivateKey(de, hid,id);
    }
}
