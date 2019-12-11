package com.llxy.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 需求：
 * 类描述：
 * 在开发项目的过程中，一般都会设计几个配置管理组件，
 * 这些配置管理组件，对整个项目的读取的配置信息，进行管理
 */
public class ConfigurationManager {

    //1、构建properties
    private static Properties props = new Properties();

    static {
        try {
            //2、在类加载时properties文件输入流
            InputStream in = ConfigurationManager.class.getClassLoader().getResourceAsStream("db.properties");
            //通过properties对象加载文件中的数据
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能1:通过key来回去对应的value值
     *
     * @param key key
     * @return key对应的value
     */
    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    /**
     * 功能2：通过key获取对应的value，然后把value值转换integer
     * @param key key
     * @return key对应的value的integer类型
     */
    public static Integer getInteger(String key){
        String property = getProperty(key);
        try {
            return Integer.valueOf(property);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 功能3：通过key获取对应的value，然后把value值转换Boolean
     * @param key key
     * @return key对应的value的boolean类型
     */
    public static Boolean getBoolean(String key){
        String property = getProperty(key);
        try {
            return Boolean.valueOf(property);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 功能4：通过key获取对应的value，然后把value值转换Long
     * @param key key
     * @return key对应的value的long类型
     */
    public static Long getLong(String key){
        String property = getProperty(key);
        try {
            return Long.valueOf(property);
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
