package SM9;

import java.io.Serializable;

import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;

public class ResultEncapsulateCipherText implements Serializable{

	transient CurveElement C;

    public ResultEncapsulateCipherText(CurveElement C) {
        this.C = C;
    }

    public static ResultEncapsulateCipherText fromByteArray(SM9Curve curve, byte[] data) {
        CurveElement e = curve.G1.newElement();
        e.setFromBytes(data);
        return new ResultEncapsulateCipherText(e);
    }

    public byte[] toByteArray()
    {
        return C.toBytes();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ÃÜÔ¿·â×°µÄÃÜÎÄ:");
        sb.append(SM9Utils.NEW_LINE);
        sb.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(C)));

        return sb.toString();
    }
}
