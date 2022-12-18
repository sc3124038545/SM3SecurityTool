package SM9.pairing;

import java.math.BigInteger;
import java.security.SecureRandom;

import SM9.pairing.SM9RatePairingMap;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Field;
import it.unisa.dia.gas.jpbc.PairingParameters;
import it.unisa.dia.gas.plaf.jpbc.field.poly.PolyModField;
import it.unisa.dia.gas.plaf.jpbc.pairing.f.TypeFPairing;

public class SM9Pairing extends TypeFPairing{
	protected BigInteger t; //���ڼ���r-ate��Բ���

    public SM9Pairing(PairingParameters curveParams) {
        super(curveParams);
    }

    public SM9Pairing(SecureRandom random, PairingParameters curveParams) {
        super(random, curveParams);
    }

    @Override
    protected void initParams() {
        super.initParams();

        t = curveParams.getBigInteger("t");
    }

    @Override
    protected void initMap() {
        pairingMap = new SM9RatePairingMap(this);
    }

    public BigInteger getN() {
        return r;
    }

    public Field getFq2() {
        return Fq2;
    }

    public PolyModField getFq12()
    {
        return Fq12;
    }

    public BigInteger getQ() {
        return q;
    }

    public Element getNegAlphaInv() {
        return negAlphaInv;
    }

    public PairingParameters getPairingParameters() {
        return curveParams;
    }
}
