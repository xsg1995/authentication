package live.xsg.authentication.security;

/**
 * Created by xsg on 2019/12/5.
 */
public interface TokenSecurity {

    /**
     * token加密
     * @param token 要加密的token
     * @return 加密后的密文
     */
    String encrypt(String token);

}
