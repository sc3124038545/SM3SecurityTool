package SM9;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;

import org.bouncycastle.jcajce.provider.symmetric.Threefish;

import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;

public class PrivateKey implements Serializable{

	transient CurveElement d;
	String id;
    byte hid;

    public PrivateKey(CurveElement point, byte hid,String id) {
        this.d = point;
        this.hid = hid;
        this.id=id;
    }

    public static PrivateKey fromByteArray(SM9Curve curve, byte[] source,String id) {
        byte hid = source[0];
        CurveElement d;
        if(hid==SM9Curve.HID_SIGN)
            d = curve.G1.newElement();
        else
            d = curve.G2.newElement();
        d.setFromBytes(source, 1);

        return new PrivateKey(d, hid,id);
    }

    public byte[] toByteArray() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bos.write(hid);
        byte[] temp = d.toBytes();
        bos.write(temp, 0, temp.length);
        return bos.toByteArray();
    }

    public String getId() {
    	return this.id;
    }
    @Override
    public String toString() {
        if(hid==SM9Curve.HID_SIGN)
            return SM9Utils.NEW_LINE+SM9Utils.toHexString(SM9Utils.G1ElementToBytes(d));
        else
            return SM9Utils.NEW_LINE+SM9Utils.toHexString(SM9Utils.G2ElementToByte(d));
    }
}
