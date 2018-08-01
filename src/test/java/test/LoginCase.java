package test;

import action.LoginAction;
import base.*;
import base.driver.BaseDriver;
import org.apache.poi.ss.formula.functions.T;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
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
        //创建一个Object数组集合
        List<Object[]> result = new ArrayList<Object[]>();
        List<List<LoginCaseData>> caseDatas = XACommon.casesData(filePath, caseSheet, method, LoginCaseData.class);
        //循环遍历集合
        for(int i=0;i<caseDatas.size();i++){
            for(int j =0;j<caseDatas.get(i).size();j++){
                caseData = new LoginCaseData();
                caseData = caseDatas.get(i).get(j);
                //将遍历出来的映射实体类，添加到Object数组集合中
                result.add(new Object[]{caseData});
            }
        }
        return result.iterator();
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
    public void loginSuccess(LoginCaseData data)throws Exception {//需要一个LoginCaseData 这个实体类，获得Excel文件中的用户名、密码数据
        loginAction.login(data.getInputUsername(), data.getInputPassword());//调用loginaction业务类中login方法，
        //判断页面是否出现“雄安市民服务中心”文本，若出现则返回登录成功，若失败则返回登录失败字符
        final String message = asser.VerityNotTextPresent("首页") ? Message.LOGINSUCCESS : Message.LOGINFAIL;
        Reporter.log(data.toString());
        //调用Assert类中的方法，实际值与预期值进行比较
        Assert.assertEquals(message, data.getExpected());
    }

    /**
     * 登录失败
     * @throws Exception
     */
    @Test(description = "登录失败用例", dataProvider = "longinData")
    public void loginFail(LoginCaseData data)throws Exception{
        loginAction.login(data.getInputUsername(),data.getInputPassword());
        final String  message = asser.VerityNotTextPresent("首页")? Message.LOGINSUCCESS: Message.LOGINFAIL;
        Reporter.log(data.toString());
        Assert.assertEquals(message,data.getExpected());
    }


    @AfterMethod
    public void tearDown(Method method){
        driver.closed();
    }

}
