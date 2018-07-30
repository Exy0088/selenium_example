package test.contenmanager.xaconsult;

import action.LoginAction;
import action.contentmanager.xaconsult.XAConsultAction;
import base.BaseDriver;
import base.BrowserType;
import base.GlobalConfig;
import base.WebSession;
import org.openqa.selenium.Cookie;
import org.testng.annotations.*;
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


    @BeforeClass
    public void init(){
        WebSession.getSession(GlobalConfig.getLoginUrl(),GlobalConfig.DEFAULTUSERNAME,GlobalConfig.DEFAULTPASSWORD);
    }


    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("firefox") String browser){
        //继承了父类，使用父类的设置driver的方法
        super.setDriver(browser);
        //使用父类的获得driver的方法
        this.driver = super.getDriver();
        xaca = new XAConsultAction(driver);
        driver.browserMax();
        driver.get(GlobalConfig.getLoginUrl());
        Cookie cookie = new Cookie("SESSION",PropertiesUtil.getPro("SESSION"));
        driver.setCookie(cookie);
        driver.refresh();

    }

    @Test(dependsOnMethods = {"test.LoginCase.TestCase_Login_1"})
    public void search(){

        xaca.searchConsult(PropertiesUtil.getPro("SEARCHCONTENT"));

    }
}
