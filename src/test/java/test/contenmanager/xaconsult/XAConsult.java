package test.contenmanager.xaconsult;

import action.LoginAction;
import action.contentmanager.xaconsult.XAConsultAction;
import base.BaseDriver;
import base.BrowserType;
import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import test.CaseBase;
import test.LoginCase;
import utils.Assertion;
import utils.PropertiesUtil;
import utils.log.Log;

import java.lang.reflect.Method;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-18 15:55
 **/
public class XAConsult extends CaseBase{

    private Log log = new Log(this.getClass());
    private XAConsultAction xaca;
    private Assertion asser;
    //private Cookie cookie;
    public BaseDriver driver;
    private LoginCase loginCase;

    public BaseDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setUp(){
        //this.driver = initDriver(BrowserType.FIREFOX);
//        xaca = new XAConsultAction(driver);
//        loginCase = new LoginCase();
//        asser = new Assertion(driver);
//        loginCase.driver = this.driver;
//        driver.browserMax();
//        driver.get(PropertiesUtil.getPro("LOGINURL"));
//        Cookie cookie = new Cookie("SESSION",PropertiesUtil.getPro("SESSION"));
//        driver.setCookie(cookie);
//        driver.refresh();
//        boolean baisiqin = asser.VerityNotTextPresent("baisiqin");
//        if(baisiqin){
//            log.info("登录成功");
//        }else{
//            log.info("登录失败");
//            loginCase.setUp(Method emthod);
//            loginCase.login();

  //      }
    }

    @Test(dependsOnMethods = {"test.LoginCase.TestCase_Login_1"})
    public void search(){

        xaca.searchConsult(PropertiesUtil.getPro("SEARCHCONTENT"));

    }
}
