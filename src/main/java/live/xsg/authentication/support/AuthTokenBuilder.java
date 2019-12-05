package live.xsg.authentication.support;

import live.xsg.authentication.auth.*;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xsg on 2019/12/5.
 */
public class AuthTokenBuilder {

    private TokenSecurity tokenSecurity;

    public AuthTokenBuilder() {
        this(new DefaultResourceLoader());
    }

    public AuthTokenBuilder(ResourceLoader resourceLoader) {
        final String tokenSecurityClass = resourceLoader.getValueStringByKey(Constant.TOKEN_SECURITY_CLASS);
        if(StringUtils.isNotBlank(tokenSecurityClass)) {
            try {
                final Class<TokenSecurity> tokenSecurityClazz = (Class<TokenSecurity>) Class.forName(tokenSecurityClass);
                tokenSecurity = tokenSecurityClazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }

        if(this.tokenSecurity == null) {
            tokenSecurity = new DefaultTokenSecurity();
        }
    }

    public AuthTokenBuilder(TokenSecurity tokenSecurity) {
        this.tokenSecurity = tokenSecurity;
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token，创建AuthToken返回
     * @param baseUrl
     * @param appId
     * @param password
     * @param createTime
     * @return
     */
    public AuthToken generate(String baseUrl, String appId, String password, long createTime) {
        String token = this.generateToken(baseUrl, appId, password, createTime);
        return new AuthToken(token, createTime);
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token，并指定配置读取，创建AuthToken返回
     * @param baseUrl
     * @param appId
     * @param password
     * @param createTime
     * @param resourceLoader 具体的配置文件读取
     * @return
     */
    public AuthToken generate(String baseUrl, String appId, String password, long createTime, ResourceLoader resourceLoader) {
        String token = this.generateToken(baseUrl, appId, password, createTime);
        return new AuthToken(token, createTime, resourceLoader);
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token
     * @param baseUrl
     * @param appId
     * @param password
     * @param createTime
     * @return
     */
    public String generateToken(String baseUrl, String appId, String password, long createTime) {
        String appendStr = baseUrl + appId + password + createTime;
        String token = this.tokenSecurity.encrypt(appendStr);
        return token;
    }

    public AuthToken generate(String token, long timeStamp, ResourceLoader resourceLoader) {
        return new AuthToken(token, timeStamp, resourceLoader);
    }
}
