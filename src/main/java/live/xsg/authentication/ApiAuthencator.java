package live.xsg.authentication;

import live.xsg.authentication.auth.ApiRequest;
import live.xsg.authentication.exception.TokenInvalidException;

/**
 * 认证调用接口
 * Created by xsg on 2019/12/4.
 */
public interface ApiAuthencator {
    /**
     * 认证请求url是否有效
     * @param url
     */
    void auth(String url) throws TokenInvalidException;

    /**
     * 认证ApiRequest是否有效
     * @param apiRequest
     */
    void auth(ApiRequest apiRequest) throws TokenInvalidException;
}
