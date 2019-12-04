package live.xsg.authentication.custom;

import live.xsg.authentication.auth.CredentialStorage;

/**
 * 从Mysql中取出AppId对应的密码
 * Created by xsg on 2019/12/4.
 */
public class MySqlCredentialStorage implements CredentialStorage {
    @Override
    public String getPasswordByAppId(String appId) {
        return "123456";
    }
}
