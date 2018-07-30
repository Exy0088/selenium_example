package base;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.testng.annotations.Test;
import utils.PropertiesUtil;

import java.net.HttpCookie;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 16:25
 **/
public class WebSession {

    public static void getSession(String url, String username, String password) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        map.put("password", password);
        String session = "";
        HttpResponse response = HttpRequest.post(url).form(map).timeout(2000).execute();
        List<HttpCookie> cookie = response.getCookie();
        for (HttpCookie httpCookie : cookie) {
            if (httpCookie.getName().equals("SESSION")) {
                PropertiesUtil.writePops(GlobalConfig.SESSIONPRO,httpCookie.getName(), httpCookie.getValue());
            }
        }
    }

    @Test
    public void test(){
        getSession(GlobalConfig.getLoginUrl(),GlobalConfig.DEFAULTUSERNAME,GlobalConfig.DEFAULTPASSWORD);
    }

}
