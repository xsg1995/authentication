package live.xsg.authentication.storage;

/**
 * 从存储中取出AppId对应的密码
 * Created by xsg on 2019/12/4.
 */
public interface CredentialStorage {

    /**
     * 根据appId获取密码
     * @param appId appId
     * @return appId 对应的 密码
     */
    String getPasswordByAppId(String appId);
}
