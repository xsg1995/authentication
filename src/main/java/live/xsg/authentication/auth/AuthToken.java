package live.xsg.authentication.auth;

import live.xsg.authentication.common.Constant;
import live.xsg.authentication.resource.ResourceLoader;

/**
 * 负责token的生成、校验等功能
 * Created by xsg on 2019/12/4.
 */
public class AuthToken {

    /**
     * token默认的过期时间 单位 ms
     */
    private static final long DEFAULT_EXPIRED_TIME_INTERVAL = 60 * 1000;

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

    public AuthToken(String token, long createTime, ResourceLoader resourceLoader) {
       if(resourceLoader != null) {
           Long expiredTime = resourceLoader.getValueLongByKey(Constant.EXPIRED_TIME_INTERVAL);
           if(expiredTime != null && expiredTime > 0) {
               this.expiredTimeInterval = expiredTime;
           } else {
               this.expiredTimeInterval = DEFAULT_EXPIRED_TIME_INTERVAL;
           }
       }
        this.token = token;
        this.createTime = createTime;

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
        return (this.createTime + this.expiredTimeInterval) <= System.currentTimeMillis();
    }

    /**
     * 验证两个token是否匹配
     * @param authToken 待匹配的 token
     * @return false 不匹配， true 匹配
     */
    public boolean match(AuthToken authToken) {
        return this.token.equals(authToken.getToken());
    }

}
