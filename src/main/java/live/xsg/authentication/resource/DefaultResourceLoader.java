package live.xsg.authentication.resource;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 从properties文件中获取配置信息
 * Created by xsg on 2019/12/5.
 */
public class DefaultResourceLoader implements ResourceLoader {

    private static final String DEFAULT_RESOURCE_NAME = "/authentication.properties";

    private static Properties prop = new Properties();

    static {
        try(InputStream is = DefaultResourceLoader.class.getResourceAsStream(DEFAULT_RESOURCE_NAME)) {
            prop.load(is);
        } catch (IOException ignored) { }
    }

    @Override
    public String getValueStringByKey(String key) {
        return prop.getProperty(key);
    }

    @Override
    public Long getValueLongByKey(String key) {
        try {
            String val = prop.getProperty(key);
            if(StringUtils.isNotBlank(val)) {
                return Long.parseLong(val);
            }
        } catch (Exception ignored) {}
        return null;
    }
}
