package base;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
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

    private static PropertiesUtil propertiesUtil = new PropertiesUtil("properties\\global.properties","properties\\cookie.properties");

    public static String getKeyValue(String key){
       return propertiesUtil.getPro(key);
    }

    public static void setKeyValue(String file,String key, String value){
        if(StrUtil.isNotEmpty(key) && StrUtil.isNotEmpty(value)){
            propertiesUtil.writePops(file,key,value);
        }
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
        //setKeyValue("session","dsfsadfsadfsadfsadfsadfsda");
    }



}
