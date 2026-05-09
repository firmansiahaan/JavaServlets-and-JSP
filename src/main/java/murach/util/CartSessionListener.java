package murach.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Author Lord_Bao
 * @Date 2024/6/23 10:02
 * @Version 1.0
 */
public class CartSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext sc = session.getServletContext();
        sc.log("Session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        //nothing here
        HttpSession session = se.getSession();
        ServletContext sc = session.getServletContext();
        sc.log("Session destroyed");
    }
}