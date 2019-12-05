package live.xsg.authentication;

import live.xsg.authentication.auth.*;
import live.xsg.authentication.exception.TokenInvalidException;
import live.xsg.authentication.support.AuthTokenBuilder;

/**
 * 认证接口默认实现
 * Created by xsg on 2019/12/4.
 */
public class DefaultApiAuthencator implements ApiAuthencator {

    private CredentialStorage credentialStorage;
    private ResourceLoader resourceLoader;
    private AuthTokenBuilder authTokenBuilder;

    public DefaultApiAuthencator() {
        this(new DefaultCredentialStorage(), new DefaultResourceLoader(), new AuthTokenBuilder());
    }

    public DefaultApiAuthencator(ResourceLoader resourceLoader) {
        this(new DefaultCredentialStorage(), resourceLoader, new AuthTokenBuilder());
    }

    public DefaultApiAuthencator(CredentialStorage credentialStorage) {
        this(credentialStorage, new DefaultResourceLoader(), new AuthTokenBuilder());
    }

    public DefaultApiAuthencator(CredentialStorage credentialStorage, ResourceLoader resourceLoader) {
        this(credentialStorage, resourceLoader, new AuthTokenBuilder());
    }

    public DefaultApiAuthencator(CredentialStorage credentialStorage, ResourceLoader resourceLoader, AuthTokenBuilder authTokenBuilder) {
        this.credentialStorage = credentialStorage;
        this.resourceLoader = resourceLoader;
        this.authTokenBuilder = authTokenBuilder;
    }

    @Override
    public void auth(String url) throws TokenInvalidException {
        ApiRequest apiRequest = ApiRequest.createFromFullUrl(url);
        auth(apiRequest);
    }

    @Override
    public void auth(ApiRequest apiRequest) throws TokenInvalidException {
        String appId = apiRequest.getAppId();
        String token = apiRequest.getToken();
        long timeStamp = apiRequest.getTimeStamp();
        String baseUrl = apiRequest.getBaseUrl();

        AuthToken clientAuthToken = this.authTokenBuilder.generate(token, timeStamp, resourceLoader);
        if(clientAuthToken.isExpired()) {
            throw new TokenInvalidException("Token is Expired.");
        }

        String password = this.credentialStorage.getPasswordByAppId(appId);

        AuthToken serverAuthToken = this.authTokenBuilder.generate(baseUrl, appId, password, timeStamp, resourceLoader);
        if(!serverAuthToken.match(clientAuthToken)) {
            throw new TokenInvalidException("Token verify failed.");
        }
    }
}
