package base;

import utils.PropertiesUtil;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 15:57
 **/
public class GlobalConfig {

    private static final String PROTOCOL = PropertiesUtil.getPro("PROTOCOL");
    private static final String DOMAIN = PropertiesUtil.getPro("DOMAIN");
    private static final String PORT = PropertiesUtil.getPro("PORT");
    private static final String LOGINURI = PropertiesUtil.getPro("LOGINURI");
    public static final String DEFAULTUSERNAME = PropertiesUtil.getPro("DEFAULTUSERNAME");
    public static final String DEFAULTPASSWORD = PropertiesUtil.getPro("DEFAULTPASSWORD");
    public static final String GLOBALPRO = System.getProperty("user.dir")+"/src/test/resources/properties/global.properties";
    public static final String SESSIONPRO = System.getProperty("user.dir")+"/src/test/resources/properties/session.properties";

    public static String getLoginUrl(){
        String url = PROTOCOL+"://"+DOMAIN+":"+PORT+LOGINURI;
        return url;
    }

}
