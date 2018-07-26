package handler;

import base.BaseDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import page.LoginPage;
import utils.log.Log;

import java.util.List;

/**
  * 登录页面元素操作类
  * @author hezhaowei
  * @create 2018-07-15 16:30
 **/
public class LoginHandler extends BaseHandler {

    private Log log = new Log(this.getClass());
    private LoginPage lg;

    public LoginHandler(BaseDriver driver) {
        super(driver);
        lg = new LoginPage(driver);
    }


    /**
     * 输入用户名
     * @param username
     */
    public void SendKeysUserName(String username){
        this.clearAndSendKeys(lg.getUserName(),username);
    }

    /**
     * 输入密码
     * @param password
     */
    public void SendKeysPass(String password){
        this.clearAndSendKeys(lg.getPassword(),password);
    }

    /**
     * 点击登录
     */
    public void clickLoginBtn(){
        this.click(lg.getLoginBtn());
    }
}
