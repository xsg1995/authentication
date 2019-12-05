package live.xsg.authentication;

import live.xsg.authentication.auth.DefaultTokenSecurity;
import live.xsg.authentication.auth.TokenSecurity;
import live.xsg.authentication.custom.MySqlCredentialStorage;
import live.xsg.authentication.exception.TokenInvalidException;

/**
 * Created by xsg on 2019/12/4.
 */
public class Demo {
    public static void main(String[] args) {
        TokenSecurity tokenSecurity = new DefaultTokenSecurity();
        String password = "123456";
        String appId = "app1";
        long timeStamp = System.currentTimeMillis();
        String token = tokenSecurity.encrypt( "http://localhost:8080/server" + appId + password + timeStamp);
        String url = "http://localhost:8080/server?token="+token+"&app_id="+appId+"&time_stamp=" + timeStamp;

        //指定MySqlCredentialStorage，从数据库中查询appId对应的密码
        DefaultApiAuthencator authencator = new DefaultApiAuthencator(new MySqlCredentialStorage());

        try {
            authencator.auth(url);
            System.out.println("验证通过...");
        } catch (TokenInvalidException e) {
            System.out.println("验证失败，错误信息：" + e.getMessage());
        }
    }
}
