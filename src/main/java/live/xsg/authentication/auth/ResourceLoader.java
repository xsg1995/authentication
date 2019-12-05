package live.xsg.authentication.auth;

/**
 * 配置资源加载
 * Created by xsg on 2019/12/5.
 */
public interface ResourceLoader {

    /**
     * 根据key获取对于的值
     * @param key
     * @return
     */
    String getValueStringByKey(String key);

    /**
     * 根据key获取对于的值
     * @param key
     * @return
     */
    Long getValueLongByKey(String key);
}
