package live.xsg.authentication.auth;

import live.xsg.authentication.common.Constant;
import live.xsg.authentication.utils.UrlAnalysis;

/**
 * 从请求中解析出token，AppId，时间戳等信息
 * Created by xsg on 2019/12/4.
 */
public class ApiRequest {

    /**
     * 请求服务的url
     */
    private String baseUrl;

    /**
     * 请求中的token
     */
    private String token;

    /**
     * 请求中的appId
     */
    private String appId;

    /**
     * 请求中的时间戳
     */
    private long timeStamp;

    public ApiRequest(String baseUrl, String token, String appId, long timeStamp) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.appId = appId;
        this.timeStamp = timeStamp;
    }

    /**
     * 从url中解析出appId,baseUrl,token,timeStamp信息，创建ApiRequest返回
     * @param url 请求url
     * @return ApiRequest
     */
    public static ApiRequest createFromFullUrl(String url) {
        UrlAnalysis urlAnalysis = UrlAnalysis.generate(url);
        String baseUrl = urlAnalysis.getBaseUrl();
        String appId = urlAnalysis.get(Constant.APP_ID);
        String token = urlAnalysis.get(Constant.TOKEN);
        String timeStamp = urlAnalysis.get(Constant.TIME_STAMP);

        return new ApiRequest(baseUrl, token, appId, Long.parseLong(timeStamp));
    }

    /**
     * 获取请求url
     * @return url
     */
    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 获取请求token
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 获取请求的AppId
     * @return appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 获取请求的时间戳
     * @return timeStamp
     */
    public long getTimeStamp() {
        return timeStamp;
    }
}
