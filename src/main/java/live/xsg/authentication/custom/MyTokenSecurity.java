package live.xsg.authentication.custom;

import live.xsg.authentication.auth.TokenSecurity;

/**
 * Created by xsg on 2019/12/5.
 */
public class MyTokenSecurity implements TokenSecurity {
    @Override
    public String encrypt(String token) {
        return "my" + token;
    }

    @Override
    public String decrypt(String encryptToken) {
        throw new UnsupportedOperationException();
    }
}
