package test;

import action.LoginAction;
import base.*;
import base.driver.BaseDriver;
import cn.hutool.json.JSONUtil;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobject.BasePageObject;
import pageobject.LoginCaseData;
import utils.Assertion;
import utils.ExcelUtils;
import utils.PropertiesUtil;
import utils.log.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
  * 登录case
  * @author hezhaowei
  * @create 2018-07-15 17:05
 **/
public class LoginCase extends CaseBase{

    private Log log = new Log(this.getClass());
    private LoginAction loginAction;
    private BaseDriver driver;
    private Assertion asser;
    private LoginCaseData caseData;
    private final String filePath = "testcase\\xiongan.xls";
    private final String caseSheet = "登录测试数据";

    /**
     * 注入数据驱动
     * @param method 获得调用该方法的方法名
     * @return
     * @throws Exception
     */
    @DataProvider(name = "longinData")
    public Iterator<Object[]> loginData(Method method) throws Exception{
       return XACommon.casesData(filePath,caseSheet,method);
    }

    //每个Test运行时之前都要调用该方法
    @BeforeMethod
    public void setUp() throws Exception {//@Optional 这个注解是给传入的参数一个默认值，String browser是获得 @Parameters({"browser"})的值
                                                                           //若参数有多个@Parameters({"browser","par"}),String browser,String par,必须一一对应
        //使用父类的获得driver的方法
        this.driver = super.getDriver();
        //初始化LoginAction业务类
        loginAction = new LoginAction(driver);
        //初始化断言类
        asser = new Assertion(driver);
        //浏览器最大化
        driver.browserMax();
        //打开浏览器输入访问地址
        driver.get(GlobalConfig.getUrl(UrlType.LOGIN));//读取global.properties文件中的"LOGINURL"
    }

    /**
     * 登录成功
     * @throws Exception
     */
    @Test(description = "登录成功用例", dataProvider = "longinData")//注入数据
    public void loginSuccess(BasePageObject bpo)throws Exception {//需要一个LoginCaseData 这个实体类，获得Excel文件中的用户名、密码数据
        caseData = getJson(bpo.getInputData(),LoginCaseData.class);
        loginAction.login(caseData.getUsername(), caseData.getPassword());//调用loginaction业务类中login方法，
        //调用Assert类中的方法，实际值与预期值进行比较
        Assert.assertEquals(loginAction.asserLogin(),bpo.getExpected());
    }

    /**
     * 登录失败
     * @throws Exception
     */
    @Test(description = "登录失败用例", dataProvider = "longinData")
    public void loginFail(BasePageObject bpo)throws Exception{
        caseData = getJson(bpo.getInputData(),LoginCaseData.class);
        loginAction.login(caseData.getUsername(),caseData.getPassword());
        Assert.assertEquals(loginAction.asserLogin(),bpo.getExpected());
    }


    @AfterMethod
    public void tearDown(Method method){
        driver.closed();
    }

}
