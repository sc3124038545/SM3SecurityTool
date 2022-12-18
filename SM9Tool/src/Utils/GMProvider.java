package Utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import java.security.Provider;


/**
 * @Author: ZeiCiji
 * @Description:GM algorithm provider
 * @Date:2019Äê9ÔÂ25ÈÕ
 */
public class GMProvider {

	private static Provider sProvider = null;

    public static Provider getProvider()
    {
        if(sProvider==null) {
            sProvider = new BouncyCastleProvider();
        }
        return sProvider;
    }
    private GMProvider(){
    }
}
