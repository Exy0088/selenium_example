package base;

import utils.PropertiesUtil;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-30 15:57
 **/
public class GlobalConfig {

    private static ResourceBundle bundle= ResourceBundle.getBundle("properties/global", Locale.CHINA);;

    public static String getKeyValue(String key){
        if(bundle.containsKey(key)){
            return bundle.getString(key);
        }
        return null;
    }

    public static String getUrl(UrlType urlType){
        String baseUrl =  getKeyValue("PROTOCOL")+"://"+getKeyValue("DOMAIN")+":"+getKeyValue("PORT");
        if(urlType == UrlType.LOGIN){
            return baseUrl+getKeyValue("LOGINURI");
        }
        if(urlType == UrlType.XAOBNEWSRELEASE){
            return baseUrl+getKeyValue("XAOBNEWSRELEASEURI");
        }
        if(urlType == UrlType.BANNER){
            return baseUrl+getKeyValue("BANNERURI");
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(getUrl(UrlType.LOGIN));
    }



}
