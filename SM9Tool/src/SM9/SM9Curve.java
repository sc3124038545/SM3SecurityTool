package SM9;

import SM9.pairing.SM9Pairing;
import java.math.BigInteger;
import java.security.SecureRandom;

import SM9.SM9CurveParameters;
import SM9.SM9Utils;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;
import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveField;
import it.unisa.dia.gas.plaf.jpbc.field.gt.GTFiniteField;

public class SM9Curve {
	public SecureRandom random;
    public BigInteger N; //阶数
    CurveField G1; //G1群
    CurveField G2; //G2群
    GTFiniteField GT; //GT群
    SM9Pairing sm9Pairing; //SM9 r-ate双线性对
    CurveElement P1; //P1点来自G1群
    CurveElement P2; //P2点来自G2群

    protected static final byte HID_SIGN = 0x01; //hid用于签名
    protected static final byte HID_KEY_EXCHANGE = 0x02; //hid用于密钥交换
    protected static final byte HID_ENCRYPT = 0x03; //hid用于加密

    public SM9Curve() {
        this(new SecureRandom());
    }

    public SM9Curve(SecureRandom random) {
        this.random = random;

        PairingParameters parameters = SM9CurveParameters.createSM9PropertiesParameters();

        this.sm9Pairing = new SM9Pairing(random, parameters);

        this.N = sm9Pairing.getN();
        this.G1 = (CurveField) sm9Pairing.getG1();
        this.G2 = (CurveField) sm9Pairing.getG2();
        this.GT = (GTFiniteField) sm9Pairing.getGT();

        //set P1
        P1 = G1.newElement();
        P1.setFromBytes(SM9CurveParameters.P1_bytes);

        //set P2
        P2 = G2.newElement();
        P2.setFromBytes(SM9CurveParameters.P2_bytes);
    }

    public Element pairing(CurveElement p1, CurveElement p2) {
        return sm9Pairing.pairing(p1, p2);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String newLine = "\n";
        PairingParameters pairingParameters = sm9Pairing.getPairingParameters();

        sb.append("----------------------------------------------------------------------");
        sb.append(newLine);
        sb.append("SM9 曲线参数:");
        sb.append(newLine);


        sb.append("b:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("b"))));
        sb.append(newLine);

        sb.append("t:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("t"))));
        sb.append(newLine);

        sb.append("q:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("q"))));
        sb.append(newLine);

        sb.append("N:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("r"))));
        sb.append(newLine);

        sb.append("beta:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("beta"))));
        sb.append(newLine);

        sb.append("alpha0:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("alpha0"))));
        sb.append(newLine);

        sb.append("alpha1:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(pairingParameters.getBigInteger("alpha1"))));
        sb.append(newLine);

        sb.append("P1:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(P1)));
        sb.append(newLine);

        sb.append("P2:\n");
        sb.append(SM9Utils.toHexString(SM9Utils.G2ElementToByte(P2)));

        sb.append("----------------------------------------------------------------------");
        sb.append(newLine);

        return sb.toString();
    }
}
