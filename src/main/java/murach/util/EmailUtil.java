package murach.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author Lord_Bao
 * @Date 2024/6/18 17:14
 * @Version 1.0
 */
public class EmailUtil {
    private static final Properties p;
    static {
        p = new Properties();
        InputStream stream = EmailUtil.class.getClassLoader().getResourceAsStream("myemail.properties");
        try {
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Loading resources error!!!");
        }
    }

    public static String  getUser(){
        return p.getProperty("user") == null ? "john.stamos@gmail.com" : p.getProperty("user");
    }
    public static String getPassword(){
        return p.getProperty("password") == null ? "Perjalanan@2025" : p.getProperty("password");
    }


}