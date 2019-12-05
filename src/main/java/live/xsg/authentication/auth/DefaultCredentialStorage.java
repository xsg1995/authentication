package live.xsg.authentication.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 从properties文件中取出AppId对应的密码
 * Created by xsg on 2019/12/4.
 */
public class DefaultCredentialStorage implements CredentialStorage {

    private static final String PROPERTIES_FILE_NAME = "/app.properties";
    private static Properties properties = new Properties();

    static {
        InputStream in = DefaultCredentialStorage.class.getResourceAsStream(PROPERTIES_FILE_NAME);
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPasswordByAppId(String appId) {
        return properties.getProperty(appId);
    }
}
