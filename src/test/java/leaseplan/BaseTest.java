package leaseplan;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest
{
    public static Properties properties = new Properties();
    public static String protocol;
    public static String base_url;
    public static String base_livepath;
    public static String base_historicalpath;
    public static String base_changepath;
    public static String base_timeframepath;
    public static String access_key;
    public static String testlogs_dir;
    public static String testlog;

    public void setupTestDataSet()
    {
        properties = readProperties();
        protocol = properties.getProperty("test.protocol");
        base_url = properties.getProperty("test.base_url");
        base_livepath = properties.getProperty("test.base_livepath");
        base_historicalpath = properties.getProperty("test.base_historicalpath");
        base_changepath = properties.getProperty("test.base_changepath");
        base_timeframepath = properties.getProperty("test.base_timeframepath");
        access_key = properties.getProperty("test.access_key");
        testlogs_dir = properties.getProperty("test.logs_dirpath");
        testlog = "";
    }

    public Properties readProperties()
    {
        try (InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("test.properties")) {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            System.err.println("Unable to read or missing properties file: " + "test.properties");
        }
        return properties;
    }

}
