package SM9;

import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;

public class G1PublicKey {

	CurveElement Q;

    public G1PublicKey(CurveElement point)
    {
        this.Q = point;
    }

    public static G1PublicKey fromByteArray(SM9Curve curve, byte[] source) {
        CurveElement Q = curve.G1.newElement();
        Q.setFromBytes(source);
        return new G1PublicKey(Q);
    }

    public byte[] toByteArray() {
        return Q.toBytes();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("G1Ⱥ�еĹ�Կ:");
        sb.append(SM9Utils.NEW_LINE);
        sb.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(Q)));

        return sb.toString();
    }
}
