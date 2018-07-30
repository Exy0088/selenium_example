package base;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.KillProcess;
import utils.PropertiesUtil;
import utils.log.Log;

import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-17 13:18
 **/
public class BaseDriver {

    private Log log = new Log(this.getClass());
    public static  WebDriver driver;

    public BaseDriver(BrowserType browserType){
       this.driver = getDriver( browserType);
    }

    public WebDriver getDriver(BrowserType browserType){
        WebDriver driver = null;
        if( browserType == null) {
            log.error("浏览器传参有误");
        }else {
            Browser browser = null;
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
            try {
                driver = browser.getDriver();
                log.info("------------------开始执行测试---------------");
            } catch (Exception e) {
                log.error("没有成功浏览器环境配置错误");
                e.printStackTrace();
            }
        }
        return driver;
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

                PropertiesUtil.writePops(c.getName(),c.getValue());
            }
        }
    }

    public void setCookie(Cookie cookie){
        driver.manage().deleteAllCookies();
        driver.manage().addCookie(cookie);
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


    public void swichWindow(){
        Set<String> windowHandles = driver.getWindowHandles();
        for(String s : windowHandles){
        }
        String windowHandle = driver.getWindowHandle();
    }
}
