package live.xsg.authentication.custom;

import live.xsg.authentication.auth.ResourceLoader;

/**
 * 自定义配置资源加载，从mysql中获取
 * Created by xsg on 2019/12/5.
 */
public class MySqlResourceLoader implements ResourceLoader {

    @Override
    public String getValueStringByKey(String key) {
        return null;
    }

    @Override
    public Long getValueLongByKey(String key) {
        return 120000L;
    }
}
