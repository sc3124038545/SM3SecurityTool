package SM9;

import AuxiliaryAlgorithm.SM4;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

import SM9.SM9Curve;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.field.curve.CurveElement;
import it.unisa.dia.gas.plaf.jpbc.util.Arrays;


public class SM9 {

	public StringBuffer sb_sign=new StringBuffer();
	public StringBuffer sb_verify=new StringBuffer();
	public StringBuffer sb_keyExchangeInit=new StringBuffer();
	public StringBuffer sb_keyExchange=new StringBuffer();
	public StringBuffer sb_keyEncapsulate=new StringBuffer();
	public StringBuffer sb_keyDecapsulate=new StringBuffer();
	public StringBuffer sb_encrypt=new StringBuffer();
	public StringBuffer sb_decrypt=new StringBuffer();
	
	protected SM9Curve mCurve;

    public SM9(SM9Curve curve){
        mCurve = curve;
    }

    public SM9Curve getCurve() {
        return mCurve;
    }
    
    /**
     * SM9ǩ������
     * @param masterPublicKey ǩ������Կ
     * @param privateKey ǩ��˽Կ
     * @param data ǩ��ԭʼ����
     * @return ��h,s�� ǩ�����h��s
     */
    public ResultSignature sign(MasterPublicKey masterPublicKey, PrivateKey privateKey, byte[] data)
    {
        BigInteger l, h;
        //1 : g = e(P1, Ppub-s)
        Element g = mCurve.pairing(mCurve.P1, masterPublicKey.Q);
        sb_sign.append("\nȺGT�е�Ԫ�� g:\n");
        sb_sign.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g)));
        do {
            //2: ���� r
            BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
            sb_sign.append("\n�����r��\n"+r.toString(16).toUpperCase()+"\n");
            
            //3 : ���� w=g^r
            Element w = g.duplicate().pow(r);
            sb_sign.append("\nȺGT�е�Ԫ�� w:\n");
            sb_sign.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w)));

            //4 : ���� h=H2(M||w,N)
            h = SM9Utils.H2(data, w, mCurve.N);
            sb_sign.append("\nh:\n");
            sb_sign.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h)));

            //5 : l=(r-h)mod N
            l = r.subtract(h).mod(mCurve.N);
            sb_sign.append("\nl:\n");
            sb_sign.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(l)));
        } while(l.equals(BigInteger.ZERO));

        //6 : S=[l]dSA=(xS,yS)
        CurveElement s = privateKey.d.duplicate().mul(l);
        sb_sign.append("\nȺG1�е�Ԫ�� s:\n");
        sb_sign.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(s)));

        //7 : signature=(h,s)
        return new ResultSignature(h, s);
    }
    /**
     * SM9 ǩ����֤
     *
     * @param masterPublicKey ǩ��������Կ
     * @param id ��ʶ
     * @param data ԭʼ����
     * @param signature SM9��ԭʼ���ݵ�ǩ��
     * @return ��ȷ����֤���
     */
    public boolean verify(MasterPublicKey masterPublicKey, String id, byte[] data, ResultSignature signature)
    {
        // 1 : ��� h �Ƿ����� [1, N-1]
        if(!SM9Utils.isBetween(signature.h, mCurve.N))
            return false;

        // 2 : ��� S �Ƿ����� G1
        if(!signature.s.isValid())
            return false;

        // 3 : g = e(P1, Ppub-s)
        Element g = mCurve.pairing(mCurve.P1, masterPublicKey.Q);
        sb_verify.append("\nȺGT�е�Ԫ�� g:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g)));

        // 4 : ���� t=g^h
        Element t = g.pow(signature.h);
        sb_verify.append("\nȺGT�е�Ԫ�� t:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(t)));

        // 5 : ���� h1=H1(IDA||hid,N)
        BigInteger h1 = SM9Utils.H1(id, SM9Curve.HID_SIGN, mCurve.N);
        sb_verify.append("\nh1:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h1)));

        // 6 : P=[h1]P2+Ppubs
        CurveElement p = mCurve.P2.duplicate().mul(h1).add(masterPublicKey.Q);
        sb_verify.append("\nȺG2�е�Ԫ�� p:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.G2ElementToByte(p)));

        // 7 : u=e(S,P)
        Element u = mCurve.pairing(signature.s, p);
        sb_verify.append("\nȺGT�е�Ԫ�� u:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(u)));

        // 8 : w=u*t
        Element w2 = u.mul(t);
        sb_verify.append("\nȺGT�е�Ԫ�� w':\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w2)));

        // 9 : h2=H2(M||w,N)
        BigInteger h2 = SM9Utils.H2(data, w2, mCurve.N);
        sb_verify.append("\nh2:\n");
        sb_verify.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h2)));
        if(h2.equals(signature.h)) {
        	sb_verify.append("\nh2 = h, ��֤ͨ��! \n");
        }

        return h2.equals(signature.h);
    }
    
    /**
     * SM9 ��Կ������ʼ������������G1Ⱥ�е���Կ�ԣ�
     *
     * @param masterPublicKey ���ܵ�����Կ
     * @param id ������ID
     * @return ����һ��G1Ⱥ����Կ��
     */
    public G1KeyPair keyExchangeInit(MasterPublicKey masterPublicKey, String id)
    {
    	sb_keyExchangeInit.setLength(0);
        //1 : QB =[H1(IDB||hid, N)]P1 +Ppub-e or QA = [H1(IDA || hid, N)]P1 + Ppub-e
        BigInteger h1 = SM9Utils.H1(id, SM9Curve.HID_KEY_EXCHANGE, mCurve.N);
        sb_keyExchangeInit.append("\nh1:\n");
        sb_keyExchangeInit.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h1)));
        
        CurveElement QB = mCurve.P1.duplicate().mul(h1).add(masterPublicKey.Q);
        sb_keyExchangeInit.append("\nQ:\n");
        sb_keyExchangeInit.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(QB)));

        //2: ���� r
        BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
        sb_keyExchangeInit.append("\nr:\n");
        sb_keyExchangeInit.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8)));

        //3 : RA = [rA]QB or RB= [rB]QA
        CurveElement R = QB.mul(r);
        sb_keyExchangeInit.append("\nR:\n");
        sb_keyExchangeInit.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(R)));

        return new G1KeyPair(new G1PrivateKey(r), new G1PublicKey(R));
    }
    
    /**
     * SM9 ��Կ���������ڼ��㹲����Կ��ѡ���ϣֵ��
     *
     * @param masterPublicKey ��������Կ
     * @param isSponsor Ϊtrue��Ϊ�����ߣ�ΪfalseΪ��Ӧ��
     * @param myId A�����
     * @param othId B�����
     * @param myPrivateKey ����ļ���˽Կ
     * @param myTempKeyPair G1Ⱥ�е�˽Կ��
     * @param othTempPublicKey G1Ⱥ�������Ĺ�Կ
     * @param keyByteLen ��Ҫ��������Կ�ı��س���
     * @return ����һ����Կ��˫���Ĺ�ϣֵ
     */
    public ResultKeyExchange keyExchange(MasterPublicKey masterPublicKey, boolean isSponsor, String myId, String othId,
                                         PrivateKey myPrivateKey, G1KeyPair myTempKeyPair, G1PublicKey othTempPublicKey, int keyByteLen)
            throws Exception
    {
    	sb_keyExchange.setLength(0);
        //��� R �Ƿ����� G1
        if(!othTempPublicKey.Q.isValid())
            throw new Exception("R ��Կ�� G1Ⱥ��");

        //A5��B4
        Element g1, g2, g3;
        Element gTemp0 = mCurve.pairing(masterPublicKey.Q, mCurve.P2);
        Element gTemp2 = mCurve.pairing(othTempPublicKey.Q, myPrivateKey.d);
        if(isSponsor) {
            g1 = gTemp0.pow(myTempKeyPair.prikey.d);
            g2 = gTemp2.duplicate();
        } else {
            g1 = gTemp2.duplicate();
            g2 = gTemp0.pow(myTempKeyPair.prikey.d);
        }
        g3 = gTemp2.pow(myTempKeyPair.prikey.d);
        
        sb_keyExchange.append("\ng1:\n");
        sb_keyExchange.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g1)));
        sb_keyExchange.append("\ng2:\n");
        sb_keyExchange.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g2)));
        sb_keyExchange.append("\ng3:\n");
        sb_keyExchange.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g3)));

        //6 : ����S1 SB
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] temp;
        if(isSponsor) {
            temp = myId.getBytes();
            bos.write(temp, 0, temp.length);
            temp = othId.getBytes();
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.G1ElementToBytes(myTempKeyPair.pubkey.Q);
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.G1ElementToBytes(othTempPublicKey.Q);
            bos.write(temp, 0, temp.length);
        } else {
            temp = othId.getBytes();
            bos.write(temp, 0, temp.length);
            temp = myId.getBytes();
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.G1ElementToBytes(othTempPublicKey.Q);
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.G1ElementToBytes(myTempKeyPair.pubkey.Q);
            bos.write(temp, 0, temp.length);
        }
        byte[] bIDR = bos.toByteArray();

        bos.reset();
        temp = SM9Utils.GTFiniteElementToByte(g2);
        bos.write(temp, 0, temp.length);
        temp = SM9Utils.GTFiniteElementToByte(g3);
        bos.write(temp, 0, temp.length);
        byte[] bG2G3 = bos.toByteArray();

        byte[] bG1 = SM9Utils.GTFiniteElementToByte(g1);

        bos.reset();
        bos.write(bG2G3, 0, bG2G3.length);
        bos.write(bIDR, 0, bIDR.length);
        byte[] bHashIDRG2G3 = SM9Utils.Hash(bos.toByteArray());

        //SB1
        bos.reset();
        bos.write(0x82);
        bos.write(bG1, 0, bG1.length);
        bos.write(bHashIDRG2G3, 0, bHashIDRG2G3.length);
        byte[] SB1 = SM9Utils.Hash(bos.toByteArray());

        //A8��B7 : ����SA S2
        bos.reset();
        bos.write(0x83);
        bos.write(bG1, 0, bG1.length);
        bos.write(bHashIDRG2G3, 0, bHashIDRG2G3.length);
        byte[] SA2 = SM9Utils.Hash(bos.toByteArray());

        //A7��B5 : ����SKA SKB
        bos.reset();
        bos.write(bIDR, 0, bIDR.length);
        bos.write(bG1, 0, bG1.length);
        bos.write(bG2G3, 0, bG2G3.length);
        byte[] SK = SM9Utils.KDF(bos.toByteArray(), keyByteLen);

        return new ResultKeyExchange(SK, SA2, SB1);
    }

    /**
     * SM9 ��Կ��װ
     * @param masterPublicKey ��������Կ
     * @param id B��ID
     * @param keyByteLen ��Կ���س���
     * @return ����һ�������Կ������
     */
    public ResultEncapsulate keyEncapsulate(MasterPublicKey masterPublicKey, String id, int keyByteLen)
    {
        byte[] K;
        CurveElement C;

        //1 : QB=[H1(IDB||hid, N)]P1+Ppub-e
        BigInteger h1 = SM9Utils.H1(id, SM9Curve.HID_ENCRYPT, mCurve.N);
        sb_keyEncapsulate.append("\nh1:\n");
        sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h1)));
        
        CurveElement QB = mCurve.P1.duplicate().mul(h1).add(masterPublicKey.Q);
        sb_keyEncapsulate.append("\nQB:\n");
        sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(QB)));

        do {
            //2: generate r
            BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
            sb_keyEncapsulate.append("\nr:\n");
            sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8)));

            //3 : C=[r]QB
            C = QB.mul(r);
            sb_keyEncapsulate.append("\nC:\n");
            sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(C)));

            //4 : g=e(Ppub-e, P2)
            Element g = mCurve.pairing(masterPublicKey.Q, mCurve.P2);
            sb_keyEncapsulate.append("\ng:\n");
            sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g)));

            //5 : ���� w=g^r
            Element w = g.duplicate().pow(r);
            sb_keyEncapsulate.append("\nw:\n");
            sb_keyEncapsulate.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w)));

            //6 : K = KDF(C || w || IDB, klen)
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] temp = SM9Utils.G1ElementToBytes(C);
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.GTFiniteElementToByte(w);
            bos.write(temp, 0, temp.length);
            temp = id.getBytes();
            bos.write(temp, 0, temp.length);
            K = SM9Utils.KDF(bos.toByteArray(), keyByteLen);

        } while (SM9Utils.isAllZero(K));
        
        sb_keyEncapsulate.append("\nK:\n");
        sb_keyEncapsulate.append(SM9Utils.toHexString(K));

        //7 : ��� (K,C)
        return new ResultEncapsulate(K, new ResultEncapsulateCipherText(C));
    }

    /**
     * SM9 ��Կ���
     * @param privateKey ������˽Կ
     * @param id BID
     * @param keyByteLen ��Կ���س�������Կ��װ�ı���ֵһ������
     * @param cipherText ����������Կ��װ
     * @return ������Կ
     */
    public byte[] keyDecapsulate(PrivateKey privateKey, String id, int keyByteLen, ResultEncapsulateCipherText cipherText) throws Exception
    {
        //1 : ���  C �Ƿ����� G1
        if(!cipherText.C.isValid())
            throw new Exception("C is not on G1");

        //2 : ���� w=e(C,de)
        Element w = mCurve.pairing(cipherText.C, privateKey.d);
        sb_keyDecapsulate.append("\nw':\n");
        sb_keyDecapsulate.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w)));

        //3 : K = KDF(C || w || IDB, klen)
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] temp = SM9Utils.G1ElementToBytes(cipherText.C);
        bos.write(temp, 0, temp.length);
        temp = SM9Utils.GTFiniteElementToByte(w);
        bos.write(temp, 0, temp.length);
        temp = id.getBytes();
        bos.write(temp, 0, temp.length);
        byte[] K = SM9Utils.KDF(bos.toByteArray(), keyByteLen);

        if(SM9Utils.isAllZero(K))
            throw new Exception("K is all zero");
        
        sb_keyDecapsulate.append("\nK':\n");
        sb_keyDecapsulate.append(SM9Utils.toHexString(K));

        //4 : ��� K
        return K;
    }
    
    /**
     * SM9 ����.
     *
     * @param masterPublicKey ��������Կ
     * @param id ����ID
     * @param data ԭʼ��Ϣ
     * @param isBaseBlockCipher Ϊtrue�������ķ���Ϊ������ܣ�Ϊfalse�������ķ�ʽΪKDF��ʽ
     * @param macKeyByteLen ��Կ���ȣ���MAC�йأ�����32bit��SM3��ϣ�㷨�� 
     * @return �������ģ��������C1��C2��C3��
     */
    public ResultCipherText encrypt(MasterPublicKey masterPublicKey, String id,
                                    byte[] data, boolean isBaseBlockCipher, int macKeyByteLen)
            throws Exception
    {
    	sb_encrypt.setLength(0);
        CurveElement C1;
        byte[] C2, C3, K1, K2;

        //1 : QB=[H1(IDB||hid, N)]P1+Ppub-e
        BigInteger h1 = SM9Utils.H1(id, SM9Curve.HID_ENCRYPT, mCurve.N);
        sb_encrypt.append("\nh1:\n");
        sb_encrypt.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(h1)));
        
        CurveElement QB = mCurve.P1.duplicate().mul(h1).add(masterPublicKey.Q);
        sb_encrypt.append("\nQB:\n");
        sb_encrypt.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(QB)));

        do {
            //2: ���� r
            BigInteger r = SM9Utils.genRandom(mCurve.random, mCurve.N);
            sb_encrypt.append("\nr:\n");
            sb_encrypt.append(SM9Utils.toHexString(SM9Utils.BigIntegerToBytes(r, SM9CurveParameters.nBits/8)));

            //3 : C1=[r]QB
            C1 = QB.mul(r);
            sb_encrypt.append("\nC1:\n");
            sb_encrypt.append(SM9Utils.toHexString(SM9Utils.G1ElementToBytes(C1)));

            //4 : g=e(Ppub-e, P2)
            Element g = mCurve.pairing(masterPublicKey.Q, mCurve.P2);
            sb_encrypt.append("\ng:\n");
            sb_encrypt.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(g)));

            //5 : ���� w=g^r
            Element w = g.duplicate().pow(r);
            sb_encrypt.append("\nw:\n");
            sb_encrypt.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w)));

            //6_1 : K = KDF(C1 || w || IDB, klen)
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] temp = SM9Utils.G1ElementToBytes(C1);
            bos.write(temp, 0, temp.length);
            temp = SM9Utils.GTFiniteElementToByte(w);
            bos.write(temp, 0, temp.length);
            temp = id.getBytes();
            bos.write(temp, 0, temp.length);

            int k1Len = SM4.KEY_BYTE_LENGTH;
            if(!isBaseBlockCipher)
                k1Len = data.length;
            
            if(isBaseBlockCipher) {
            	sb_encrypt.append("\n���������������:\n");
            }
            else {
            	sb_encrypt.append("\nKDF���������������:\n");
            }
            sb_encrypt.append("klen: "+(k1Len+macKeyByteLen) + " bytes");

            byte[] K = SM9Utils.KDF(bos.toByteArray(), k1Len+ macKeyByteLen);
            sb_encrypt.append("\nK=K1||K2:\n");
            sb_encrypt.append(SM9Utils.toHexString(K));
            
            K1 = Arrays.copyOfRange(K, 0, k1Len);
            K2 = Arrays.copyOfRange(K, k1Len, K.length);
        } while(SM9Utils.isAllZero(K1));

        //6_2
        if(isBaseBlockCipher) {
            //C2=Enc(K1,M)
            C2 = SM4.ecbCrypt(true, K1, data, 0, data.length);
        } else {
            //C2=M^K1
            C2 = SM9Utils.xor(data, K1);
        }
        sb_encrypt.append("\nK1:\n");
        sb_encrypt.append(SM9Utils.toHexString(K1));
        sb_encrypt.append("\nC2:\n");
        sb_encrypt.append(SM9Utils.toHexString(C2));

        //7 : C3=MAC(K2,C2)
        C3 = SM9Utils.MAC(K2, C2);
        sb_encrypt.append("\nK2:\n");
        sb_encrypt.append(SM9Utils.toHexString(K2));
        sb_encrypt.append("\nC3:\n");
        sb_encrypt.append(SM9Utils.toHexString(C3));

        //8 : C=C1|C3|C2
        return new ResultCipherText(C1, C2, C3);
    }

    /**
     * SM9 ����
     *
     * @param resultCipherText �ɼ����㷨���ɵ����� 
     * @param privateKey ˽Կ
     * @param id myself ����������Ϣ
     * @param isBaseBlockCipher Ϊtrue�������ķ���Ϊ������ܣ�Ϊfalse�������ķ�ʽΪKDF��ʽ
     * @param macKeyByteLen ��Կ���ȣ���MAC�йأ�����32bit��SM3��ϣ�㷨�� 
     * @return ������Ϣ
     */
    public byte[] decrypt(ResultCipherText resultCipherText, PrivateKey privateKey, String id,
                          boolean isBaseBlockCipher, int macKeyByteLen)
            throws Exception
    {
    	sb_decrypt.setLength(0);
        // 1 : ��� C1 �Ƿ�����G1
        if(!resultCipherText.C1.isValid())
            throw new Exception("C1 is not on G1 group");

        // 2 : w=e(C1,de)
        Element w = mCurve.pairing(resultCipherText.C1, privateKey.d);
        sb_decrypt.append("\nw':\n");
        sb_decrypt.append(SM9Utils.toHexString(SM9Utils.GTFiniteElementToByte(w)));

        // 3_1 : K = KDF(C1 || w || IDB, klen)
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] temp = SM9Utils.G1ElementToBytes(resultCipherText.C1);
        bos.write(temp, 0, temp.length);
        temp = SM9Utils.GTFiniteElementToByte(w);
        bos.write(temp, 0, temp.length);
        temp = id.getBytes();
        bos.write(temp, 0, temp.length);

        int k1Len = SM4.KEY_BYTE_LENGTH;
        if(!isBaseBlockCipher)
            k1Len = resultCipherText.C2.length;
        
        if(isBaseBlockCipher) {
        	sb_decrypt.append("\n���������������:\n");
        }
        else {
        	sb_decrypt.append("\nKDF���������������:\n");
        }
        sb_decrypt.append("klen: "+(k1Len+macKeyByteLen) + " bytes");

        byte[] K = SM9Utils.KDF(bos.toByteArray(),k1Len+macKeyByteLen);
        sb_decrypt.append("\nK=K1'||K2':\n");
        sb_decrypt.append(SM9Utils.toHexString(K));
        
        byte[] K1 = Arrays.copyOfRange(K, 0, k1Len);
        byte[] K2 = Arrays.copyOfRange(K, k1Len, K.length);

        if(SM9Utils.isAllZero(K1))
            throw new Exception("K1 is all zero");

        // 3_2
        byte[] M;
        if( isBaseBlockCipher ) {
            // M=Dec(K1,C2)
            M = SM4.ecbCrypt(false, K1, resultCipherText.C2, 0, resultCipherText.C2.length);
        } else {
            // M=C2^K1
            M = SM9Utils.xor(resultCipherText.C2, K1);
        }
        sb_decrypt.append("\nK1':\n");
        sb_decrypt.append(SM9Utils.toHexString(K1));
        sb_decrypt.append("\nM':\n");
        sb_decrypt.append(SM9Utils.toHexString(M));

        // 4 : u=MAC(K2,C2)
        byte[] u = SM9Utils.MAC(K2, resultCipherText.C2);
        sb_decrypt.append("\nK2':\n");
        sb_decrypt.append(SM9Utils.toHexString(K2));
        sb_decrypt.append("\nu:\n");
        sb_decrypt.append(SM9Utils.toHexString(u));
        
        if(!SM9Utils.byteEqual(u, resultCipherText.C3))
            throw new Exception("C3 verify failed");
        sb_decrypt.append("\nu=C3'\n");

        // 5
        return M;
    }
}
