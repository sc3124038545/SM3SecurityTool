package SM9;

import java.io.Serializable;

public class ResultEncapsulate implements Serializable{

	byte[] K;
    ResultEncapsulateCipherText C;

    public ResultEncapsulate(byte[] K, ResultEncapsulateCipherText C) {
        this.K = K;
        this.C = C;
    }

    public byte[] getK() {
        return K;
    }

    public ResultEncapsulateCipherText getC() {
        return C;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();

        sb.append("ÃÜÔ¿·â×°½á¹û:");
        sb.append(SM9Utils.NEW_LINE);
        sb.append("K:");
        sb.append(SM9Utils.NEW_LINE);
        sb.append(SM9Utils.toHexString(K));
        sb.append(SM9Utils.NEW_LINE);
        sb.append(C.toString());

        return sb.toString();
    }
}
