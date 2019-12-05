package live.xsg.authentication.auth;

/**
 * 默认不对token进行处理
 * Created by xsg on 2019/12/5.
 */
public class DefaultTokenSecurity implements TokenSecurity {

    static class DefaultTokenSecurityHolder {
        public static DefaultTokenSecurity instance = new DefaultTokenSecurity();
    }

    public static DefaultTokenSecurity getInstance() {
        return DefaultTokenSecurityHolder.instance;
    }

    @Override
    public String encrypt(String token) {
        return token;
    }

    @Override
    public String decrypt(String encryptToken) {
        return encryptToken;
    }
}
