package base.driver;

import base.GlobalConfig;
import cn.hutool.core.util.StrUtil;
import org.openqa.selenium.*;
import utils.KillProcess;
import utils.log.Log;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-17 13:18
 **/
public class BaseDriver {

    private Log log = new Log(this.getClass());
    public  WebDriver driver;

    public BaseDriver(BrowserType browserType){
       this.driver = getDriver( browserType);
    }

    public  WebDriver getDriver() {
        return driver;
    }

    public WebDriver getDriver(BrowserType browserType){
        Browser browser = null;
        if( browserType == null) {
            log.error("浏览器传参有误");
        }else {
            KillProcess.kill(browserType);
            log.info("读取浏览器并将其初始化");
            switch (browserType){
                case IE:
                    browser = new Browser(BrowserType.IE);
                    log.info("初始化IE浏览器");
                    break;
                case FIREFOX:
                    browser = new Browser(BrowserType.FIREFOX);
                    log.info("初始化火狐浏览器");
                    break;
                case CHROME:
                    browser = new Browser(BrowserType.CHROME);
                    log.info("初始化谷歌浏览器");
                    break;
                default:
                    browser = new Browser(BrowserType.CHROME);
                    log.info("初始化谷歌浏览器");
                    break;
            }
        }
        return browser.getDriver();
    }




    /**
     * 打开浏览器
     * @param url
     */
    public void get(String url){
        driver.get(url);
        log.info("打开浏览器，访问"+url+"网址!");
    }

    /**
     * 查找元素
     * @param by
     */
    public WebElement findElement(By by){
        return  driver.findElement(by);
    }

    /**
     * 关闭浏览器
     */
    public void closed(){
        driver.close();
        log.info("关闭浏览器");
    }

    /**
     * 获取cookie
     */
    public Cookie getCookie(){
        Cookie cookie = null;
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie c :cookies){
            //PropertiesUtil.writePops(cookie.getName(),cookie.getValue());
            cookie = new Cookie(c.getName(),c.getValue());
        }
        return cookie;
    }

    /**
     * 获取cooke,并写入到properties中
     */
    public void getCookies(){
        Set<Cookie> cookies = driver.manage().getCookies();
        for(Cookie c :cookies){
            if(c.getName().equals("SESSION")){

               // PropertiesUtil.writePops(c.getName(),c.getValue());
            }
        }
    }

    /**
     * 设置Cookie
     * @param map
     */
    public void setCookie(Map map){
        Cookie cookie = null;
       if(map.size() > 0){
           Iterator it = map.entrySet().iterator();
           while (it.hasNext()){
               Map.Entry entry = (Map.Entry)it.next();
               cookie = new Cookie((String)entry.getKey(),(String)entry.getValue());
           }
       }
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(cookie);
        refresh();
    }

    /**
     * 设置Cookie
     * @param key
     */
    public void setCookie(String key){
        Cookie cookie = null;
        if(StrUtil.isNotEmpty(key)){
            cookie = new Cookie(key,GlobalConfig.getKeyValue(key));
        }
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(cookie);
        refresh();
    }


    public void refresh(){
        driver.navigate().refresh();
    }

    public void browserMax(){
        driver.manage().window().maximize();
    }

    public void swithFrame(int index){
        driver.switchTo().frame(index);

    }

    public void swithFrame(String name){
        driver.switchTo().frame(name);
    }
    public void defaultFrame(){
        driver.switchTo().defaultContent();
    }
    public void parentFrame(){
        driver.switchTo().parentFrame();
    }

    public void swichWindow(){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String s : windowHandles){
        }
        String windowHandle = driver.getWindowHandle();
    }

    /**
     * 移动到页面底部
     */
    public void moveScroll(){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

}
