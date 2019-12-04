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
    private Map<String, String> paramsMap = new HashMap<>();

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    /**
     * 将参数加入到map中
     * @param key
     * @param val
     */
    public void put(String key, String val) {
        this.paramsMap.put(key, val);
    }

    /**
     * 根据key获取参数值
     * @param key
     * @return
     */
    public String get(String key) {
        return this.paramsMap.get(key);
    }

    /**
     * 解析url中的参数 http://localhost:8080/server?a=x&b=x
     * @return
     */
    public static UrlAnalysis generate(String url) {
        if(StringUtils.isEmpty(url)) {
            return new UrlAnalysis();
        }
        UrlAnalysis urlAnalysis = new UrlAnalysis();

        String[] urlSplit = url.split("\\?");
        String baseUrl = urlSplit[0];
        urlAnalysis.setBaseUrl(baseUrl);
        if(urlSplit[1] != null) {
            String params = urlSplit[1];
            String[] paramsSplit = params.split("&");
            if(paramsSplit != null && paramsSplit.length > 0) {
                for (String keyVal : paramsSplit) {
                    String[] keyValSplit = keyVal.split("=");
                    if(keyValSplit != null) {
                        urlAnalysis.put(keyValSplit[0], keyValSplit[1]);
                    }
                }
            }
        }
        return urlAnalysis;
    }

    public static void main(String[] args) {
        String url = "http://localhost:8080/server?a=x&b=x";
        UrlAnalysis generate = generate(url);
        System.out.println(generate.baseUrl);
        System.out.println(generate.paramsMap);
    }
}
