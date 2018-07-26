package base;

import base.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
  *  浏览器驱动类
  * @author hezhaowei
  * @create 2018-07-15 12:51
  **/
public class Browser {
    private WebDriver driver;
    private DesiredCapabilities dc;
    private String projectPath = System.getProperty("user.dir");

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * 根据传入的BrowserType,进行实例化
     * @param type
     */
    public Browser(BrowserType type){

        switch(type){
            case IE:
                System.setProperty("webdriver.ie.driver",projectPath+"/src/test/resources/driver/IEDriverServer.exe");//设置加载ie浏览器的驱动位置
                dc = DesiredCapabilities.internetExplorer();//获取一个设置ie属性的对象
                dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                dc.setCapability("ignoreProtectedModeSettings", true);
                driver = new InternetExplorerDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", projectPath+"/src/test/resources/driver/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", projectPath+"/src/test/resources/driver/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
        }
    }
}
