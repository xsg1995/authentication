package live.xsg.authentication.auth;

import live.xsg.authentication.utils.SecurityUtil;

/**
 * 负责token的生成、校验等功能
 * Created by xsg on 2019/12/4.
 */
public class AuthToken {

    /**
     * token默认的过期时间
     */
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 1 * 60 * 1000;

    /**
     * token
     */
    private String token;

    /**
     * token创建时间戳
     */
    private long createTime;

    /**
     * token过期时间窗口
     */
    private long expiredTimeInterval;

    public AuthToken(String token, long createTime) {
        this(token, createTime, DEFAULT_EXPIRED_TIME_INTERVAL);
    }

    public AuthToken(String token, long createTime, long expiredTimeInterval) {
        this.token = token;
        this.createTime = createTime;
        this.expiredTimeInterval = expiredTimeInterval;
    }

    /**
     * 根据URL、AppID、密码、时间戳生成token，创建AuthToken返回
     * @param baseUrl
     * @param appId
     * @param password
     * @param createTime
     * @return
     */
    public static AuthToken generate(String baseUrl, String appId, String password, long createTime) {
        String appendStr = baseUrl + appId + password + createTime;
        String token = SecurityUtil.encrypt(appendStr);
        return new AuthToken(token, createTime);
    }

    /**
     * 获取token
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 判断token是否过期
     * @return false 没有过期， true 过期
     */
    public boolean isExpired() {
        return this.createTime + this.expiredTimeInterval > System.currentTimeMillis() ? false : true;
    }

    /**
     * 验证两个token是否匹配
     * @param authToken
     * @return false 不匹配， true 匹配
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }
}
