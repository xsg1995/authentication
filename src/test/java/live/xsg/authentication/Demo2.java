package live.xsg.authentication;

import live.xsg.authentication.auth.ApiRequest;
import live.xsg.authentication.auth.DefaultTokenSecurity;
import live.xsg.authentication.exception.TokenInvalidException;

/**
 * Created by xsg on 2019/12/4.
 */
public class Demo2 {
    public static void main(String[] args) {
        String baseUrl = "http://localhost:8080/server";
        String appId = "app1";
        String password = "xxx1";
        long timeStamp = System.currentTimeMillis();
        String token = DefaultTokenSecurity.getInstance().encrypt( baseUrl + appId + password + timeStamp);

        //默认使用PropertiesCredentialStorage，从配置文件app.properties获取appId和密码
        DefaultApiAuthencator authencator = new DefaultApiAuthencator();

        ApiRequest request = new ApiRequest(baseUrl, token, appId, timeStamp);
        try {
            authencator.auth(request);
            System.out.println("验证通过...");
        } catch (TokenInvalidException e) {
            System.out.println("验证失败，错误信息：" + e.getMessage());
        }
    }
}
