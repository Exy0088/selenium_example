package page;

import base.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Locator;
import utils.log.Log;

import java.util.List;

/**
  * 登录页面元素类
  * @author hezhaowei
  * @create 2018-07-15 16:14
  **/
public class LoginPage {

    private Log log = new Log(this.getClass());
    private BaseDriver driver;
    private Locator locator;
    private final String path = "src\\test\\resources\\yaml\\login.yaml";

    public LoginPage(BaseDriver driver) {
        this.driver = driver;
        this.locator = new Locator(driver,path);
    }

    /**
     * 定位username输入框
     * @return
     */
    public WebElement getUserName(){
        return locator.getLocator("username",true);
    }

    /**
     * 定位password输入框
     * @return
     */
    public WebElement getPassword(){
        return locator.getLocator("password",true);
    }

    /**
     * 点击登录按钮
     * @return
     */
    public WebElement getLoginBtn(){
        return  locator.getLocator("login_button",true);
    }
}
