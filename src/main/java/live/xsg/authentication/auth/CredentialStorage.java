package live.xsg.authentication.auth;

/**
 * 从存储中取出AppId对应的密码
 * Created by xsg on 2019/12/4.
 */
public interface CredentialStorage {

    /**
     * 根据appId获取密码
     * @param appId
     * @return
     */
    String getPasswordByAppId(String appId);
}
