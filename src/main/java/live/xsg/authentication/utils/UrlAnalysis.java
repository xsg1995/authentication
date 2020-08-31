package live.xsg.authentication.utils;


import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析url请求中的参数
 * Created by xsg on 2019/12/4.
 */
public class UrlAnalysis {

    /**
     * url的baseUrl
     */
    private String baseUrl;
    /**
     * url中的参数
     */
    private Map<String, String> paramsMap;

    public UrlAnalysis(String baseUrl, Map<String, String> paramsMap) {
        this.baseUrl = baseUrl;
        this.paramsMap = paramsMap;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    /**
     * 根据key获取参数值
     * @param key key
     * @return key对应的参数
     */
    public String get(String key) {
        return this.paramsMap.get(key);
    }

    /**
     * 解析url中的参数 http://localhost:8080/server?a=x&b=x
     * @return UrlAnalysis
     */
    public static UrlAnalysis generate(String url) {
        if(StringUtils.isEmpty(url)) {
            return new UrlAnalysis(null, null);
        }

        String[] urlSplit = url.split("\\?");
        String baseUrl = urlSplit[0];

        Map<String, String> paramMap = new HashMap<>();
        if(urlSplit[1] != null) {
            String params = urlSplit[1];
            String[] paramsSplit = params.split("&");
            if(paramsSplit.length > 0) {
                for (String keyVal : paramsSplit) {
                    String[] keyValSplit = keyVal.split("=");
                    String key = keyValSplit.length > 0 ? keyValSplit[0] : "";
                    String val = keyValSplit.length > 1 ? keyValSplit[1] : "";
                    paramMap.put(key, val);
                }
            }
        }
        return new UrlAnalysis(baseUrl, paramMap);
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/server?a=x&b=x";
        UrlAnalysis generate = generate(url);
        System.out.println(generate.baseUrl);
        System.out.println(generate.paramsMap);
    }
}
