package live.xsg.authentication;

import live.xsg.authentication.auth.ApiRequest;
import live.xsg.authentication.auth.AuthToken;
import live.xsg.authentication.exception.TokenInvalidException;
import live.xsg.authentication.resource.DefaultResourceLoader;
import live.xsg.authentication.resource.ResourceLoader;
import live.xsg.authentication.storage.CredentialStorage;
import live.xsg.authentication.storage.PropertiesCredentialStorage;
import live.xsg.authentication.auth.AuthTokenBuilder;

/**
 * 认证接口实现
 * Created by xsg on 2019/12/4.
 */
public class ApiAuthencator {

    private CredentialStorage credentialStorage;
    private ResourceLoader resourceLoader;
    private AuthTokenBuilder authTokenBuilder;

    public ApiAuthencator() {
        this(new PropertiesCredentialStorage(), new DefaultResourceLoader(), new AuthTokenBuilder());
    }

    public ApiAuthencator(ResourceLoader resourceLoader) {
        this(new PropertiesCredentialStorage(), resourceLoader, new AuthTokenBuilder());
    }

    public ApiAuthencator(CredentialStorage credentialStorage) {
        this(credentialStorage, new DefaultResourceLoader(), new AuthTokenBuilder());
    }

    public ApiAuthencator(CredentialStorage credentialStorage, ResourceLoader resourceLoader) {
        this(credentialStorage, resourceLoader, new AuthTokenBuilder());
    }

    public ApiAuthencator(CredentialStorage credentialStorage, ResourceLoader resourceLoader, AuthTokenBuilder authTokenBuilder) {
        this.credentialStorage = credentialStorage;
        this.resourceLoader = resourceLoader;
        this.authTokenBuilder = authTokenBuilder;
    }

    /**
     * 验证url是否有访问权限
     * @param url 访问的url
     * @throws TokenInvalidException 如果token失效，则抛出该异常
     */
    public void auth(String url) throws TokenInvalidException {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    /**
     * 验证apiRequest参数是否有访问权限
     * @param apiRequest apiRequest
     * @throws TokenInvalidException 如果token失效，则抛出该异常
     */
    public void auth(ApiRequest apiRequest) throws TokenInvalidException {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long createTime = apiRequest.getTimeStamp();
        String baseUrl = apiRequest.getBaseUrl();

        AuthToken clientAuthToken = this.authTokenBuilder.generate(token, createTime, resourceLoader);
        if(clientAuthToken.isExpired()) {
            throw new TokenInvalidException("Token is Expired.");
        }

        String password = this.credentialStorage.getPasswordByAppId(appId);

        AuthToken serverAuthToken = this.authTokenBuilder.generate(baseUrl, appId, password, createTime, resourceLoader);
        if(!serverAuthToken.match(clientAuthToken)) {
            throw new TokenInvalidException("Token verify failed.");
        }
    }
}
