package live.xsg.authentication.auth;

import live.xsg.authentication.common.Constant;
import live.xsg.authentication.resource.DefaultResourceLoader;
import live.xsg.authentication.resource.ResourceLoader;
import live.xsg.authentication.security.ShaTokenSecurity;
import live.xsg.authentication.security.TokenSecurity;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xsg on 2019/12/5.
 */
public class AuthTokenBuilder {

    private TokenSecurity tokenSecurity;

    public AuthTokenBuilder() {
        this(new DefaultResourceLoader());
    }

    @SuppressWarnings("unchecked")
    public AuthTokenBuilder(ResourceLoader resourceLoader) {
        final String tokenSecurityClass = resourceLoader.getValueStringByKey(Constant.TOKEN_SECURITY_CLASS);
        if(StringUtils.isNotBlank(tokenSecurityClass)) {
            try {
                final Class<TokenSecurity> tokenSecurityClazz = (Class<TokenSecurity>) Class.forName(tokenSecurityClass);
                tokenSecurity = tokenSecurityClazz.newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        if(this.tokenSecurity == null) {
            tokenSecurity = new ShaTokenSecurity();
        }
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token，创建AuthToken返回
     * @param baseUrl  请求url
     * @param appId appId
     * @param password password
     * @param createTime 创建时间
     * @return 生成的 AutoToken 类
     */
    public AuthToken generate(String baseUrl, String appId, String password, long createTime) {
        String token = this.generateToken(baseUrl, appId, password, createTime);
        return new AuthToken(token, createTime);
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token，并指定配置读取，创建AuthToken返回
     * @param baseUrl 请求url
     * @param appId appId
     * @param password password
     * @param createTime 创建时间
     * @param resourceLoader 资源读取类
     * @return 生成的 AutoToken 类
     */
    public AuthToken generate(String baseUrl, String appId, String password, long createTime, ResourceLoader resourceLoader) {
        String token = this.generateToken(baseUrl, appId, password, createTime);
        return new AuthToken(token, createTime, resourceLoader);
    }

    /**
     * 根据URL、AppID、密码、时间戳生成加密后的token
     * @param baseUrl 请求url
     * @param appId appId
     * @param password password
     * @param createTime 创建时间
     * @return 生成的token
     */
    public String generateToken(String baseUrl, String appId, String password, long createTime) {
        String appendStr = baseUrl + appId + password + createTime;
        return this.tokenSecurity.encrypt(appendStr);
    }

    /**
     * 根据 token, 时间戳，并指定配置读取 生成 AuthToken
     * @param token token
     * @param createTime 创建时间
     * @param resourceLoader 戳资源读取类
     * @return 生成的 AutoToken 类
     */
    public AuthToken generate(String token, long createTime, ResourceLoader resourceLoader) {
        return new AuthToken(token, createTime, resourceLoader);
    }
}
