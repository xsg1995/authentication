package live.xsg.authentication.auth;

/**
 * Created by xsg on 2019/12/5.
 */
public interface TokenSecurity {

    /**
     * token加密
     * @param token
     * @return
     */
    String encrypt(String token);

    /**
     * 加密后的token解密
     * @param encryptToken
     * @return
     */
    String decrypt(String encryptToken);
}
