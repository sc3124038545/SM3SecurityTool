package Utils;
import SM9.*;
import java.io.*;
import java.math.BigInteger;

public class Serialize {

	public static BigInteger msk;
	public static BigInteger r;
	public static PrivateKey sk;
	public static ResultSignature rs;
	public static ResultKeyExchange rk;
	public static ResultEncapsulate re;
	public static ResultEncapsulateCipherText rec;
	public static ResultCipherText rc;
	
    public static void serializeMsk(BigInteger msk,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(msk);
        System.out.println("对象序列化成功！");
        oos.close();
    }
 
    public static boolean deserializeMsk(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof BigInteger)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.msk = (BigInteger) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
    
    public static void serializeR(BigInteger r,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(r);
        System.out.println("对象序列化成功！");
        oos.close();
     }
  
    public static boolean deserializeR(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof BigInteger)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.r = (BigInteger) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
     
    public static void serializeSk(PrivateKey sk,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(sk);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeSk(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof PrivateKey)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.sk = (PrivateKey) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
      
    public static void serializeRecipher(ResultCipherText rc,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(rc);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeRecipher(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof ResultCipherText)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.rc = (ResultCipherText) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
    
    public static void serializeReencap(ResultEncapsulate re,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(re);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeReencap(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof ResultEncapsulate)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.re = (ResultEncapsulate) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
    
    public static void serializeReencipher(ResultEncapsulateCipherText rec,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(rec);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeReencipher(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof ResultEncapsulateCipherText)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.rec = (ResultEncapsulateCipherText) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
    
    public static void serializeRekeyexchange(ResultKeyExchange rk,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(rk);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeRekeyexchange(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof ResultKeyExchange)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.rk = (ResultKeyExchange) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
    
    public static void serializeResign(ResultSignature rs,String savepath) throws IOException {
    	File file=new File(savepath);
    	File fileParent = file.getParentFile();
    	if(!fileParent.exists()){
    	    fileParent.mkdirs();
    	}
    	file.createNewFile();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(rs);
        System.out.println("对象序列化成功！");
        oos.close();
    }
   
    public static boolean deserializeResign(String openpath) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(openpath)));
        Object oo=ois.readObject();
        if(!(oo instanceof ResultSignature)) {
        	System.out.println("对象反序列化失败！");
        	ois.close();
        	return false;
        }
        else {
        	Serialize.rs = (ResultSignature) oo;
            System.out.println("对象反序列化成功！");
            ois.close();
            return true;
        }
    }
}
