package live.xsg.authentication;

import live.xsg.authentication.exception.TokenInvalidException;
import live.xsg.authentication.utils.SecurityUtil;

/**
 * Created by xsg on 2019/12/4.
 */
public class Demo {
    public static void main(String[] args) {

        String password = "123456";
        DefaultApiAuthencator authencator = new DefaultApiAuthencator();

        //从配置文件中读取app与对应的密码
//        String password = "xxx1";
//        DefaultApiAuthencator authencator = new DefaultApiAuthencator(new PropertiesCredentialStorage());

        String appId = "app1";
        long timeStamp = System.currentTimeMillis();

        String token = SecurityUtil.encrypt( "http://localhost:8080/server" + appId + password + timeStamp);
        String url = "http://localhost:8080/server?token="+token+"&app_id="+appId+"&time_stamp=" + timeStamp;

        try {
            authencator.auth(url);
            System.out.println("验证通过...");
        } catch (TokenInvalidException e) {
            System.out.println("验证失败，错误信息：" + e.getMessage());
        }
    }
}
