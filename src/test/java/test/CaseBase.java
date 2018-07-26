package test;

import base.BaseDriver;
import base.BrowserType;
import org.testng.annotations.*;
import utils.log.Log;

/**
  * Case基类
  * @author hezhaowei
  * @create 2018-07-15 12:57
  **/
@Listeners(utils.screenshot.ScreenshotListener.class)
public class CaseBase {

    private Log log = new Log(this.getClass());
    private BaseDriver driver;

    public BaseDriver getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = initDriver(driver);
    }

    private BaseDriver initDriver(String browserType){
        if(browserType.equalsIgnoreCase("IE") || browserType.equalsIgnoreCase("InternetExplorer")){
            return new BaseDriver(BrowserType.IE);
        }else if(browserType.equalsIgnoreCase("ff") || browserType.equalsIgnoreCase("FIREFOX")){
            return new BaseDriver(BrowserType.FIREFOX);
        }else{
            return new BaseDriver(BrowserType.CHROME);
        }
    }



    //    @Parameters({"browser"})
//    @BeforeMethod
//    public void init(String browserType){
//        if(browserType == "" || browserType == null){
//            log.error("浏览器传参有误");
//        }else {
//            Browser browser = null;
//            kill(browserType);
//            if(browserType.equals("IE") || browserType.equals("ie") || browserType.equals("InternetExplorer")){
//                browser = new Browser(Browser.BrowserType.IE);
//                log.info("初始化IE浏览器");
//            }else if(browserType.equals("ff") || browserType.equals("FF") || browserType.equals("FIREFOX")){
//                browser = new Browser(Browser.BrowserType.FIREFOX);
//                log.info("初始化火狐浏览器");
//            }else{
//                browser = new Browser(Browser.BrowserType.CHROME);
//                log.info("初始化谷歌浏览器");
//            }
//            try {
//                driver = browser.getDriver();
//                driver.manage().window().maximize();
//                log.info("------------------开始执行测试---------------");
//            } catch (Exception e) {
//                log.error("没有成功浏览器环境配置错误");
//                e.printStackTrace();
//            }
//        }
//    }

//    @Test
//    public void test(){
//        driver.get("http://www.baidu.com");
//    }

//    @Parameters({"browser"})
//    @AfterMethod
//    public void tearDown(String browserType){
//        driver.quit();
//        kill(browserType);
//    }


}
