package action;

import base.Message;
import base.driver.BaseDriver;
import handler.LoginHandler;
import org.openqa.selenium.WebDriver;
import utils.Assertion;
import utils.PropertiesUtil;
import utils.log.Log;

import java.util.List;

/**
 * 登录业务类
 * @author hezhaowei
 * @create 2018-07-15 16:39
 **/
public class LoginAction {

    private Log log = new Log(this.getClass());
    private BaseDriver driver;
    private LoginHandler lh;
    private Assertion asser;

    public LoginAction(BaseDriver driver) {
        this.driver = driver;
        this.lh = new LoginHandler(driver);
        this.asser = new Assertion(driver);
    }

    /**
     * 登录平台
     * @param username
     * @param password
     */
    public void login(String username,String password){
        if(username != null && username != ""){
            lh.SendKeysUserName(username);
        }
        if(password != null && password != ""){
            lh.SendKeysPass(password);
        }
        lh.clickLoginBtn();
    }

    /**
     *
     *
     * @return
     */
    public String asserLogin(){
        String message = null;
        if(lh.getLoginNameText() == null){
            message = Message.LOGINFAIL;
        }else {
            message = Message.LOGINSUCCESS;
        }
         return message;
    }
}
