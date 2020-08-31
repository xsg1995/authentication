package live.xsg.authentication.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 默认不对token进行处理
 * Created by xsg on 2019/12/5.
 */
public class ShaTokenSecurity implements TokenSecurity {
    //SHA-256 加密算法
    private static final String SHA = "SHA-256";

    @Override
    public String encrypt(String token) {
        try {
            MessageDigest sha = MessageDigest.getInstance(SHA);
            sha.update(token.getBytes());
            byte[] digest = sha.digest();
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
