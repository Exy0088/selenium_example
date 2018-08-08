package test.contenmanager.xaconsult;

import action.contentmanager.xaconsult.XAConsultAction;
import base.Message;
import base.UrlType;
import base.driver.BaseDriver;
import base.GlobalConfig;
import base.XACommon;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
import pageobject.BasePageObject;
import pageobject.LoginCaseData;
import pageobject.contentmanager.XAConsultCaseData;
import test.CaseBase;
import utils.Assertion;
import utils.ExcelUtils;
import utils.log.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-18 15:55
 **/
public class XAConsultCase extends CaseBase{

    private Log log = new Log(this.getClass());
    private BaseDriver driver;
    private XAConsultCaseData caseData;
    private XAConsultAction xaConsultAction;
    private Assertion assertion;
    private final String filePath = "testcase\\xiongan.xls";
    private final String caseSheet = "内容管理-雄安资讯测试数据";


    @BeforeClass
    public void init(){
      XACommon.writeSession(GlobalConfig.getUrl(UrlType.LOGIN), GlobalConfig.getKeyValue("DEFAULTUSERNAME"), GlobalConfig.getKeyValue("DEFAULTPASSWORD"));
    }

    @DataProvider(name = "XAConsultData")
    public Iterator<Object[]> XAConsultData(Method method) throws Exception {
        return XACommon.casesData(filePath,caseSheet,method);
    }

    @BeforeMethod
    public void setUp(){
        //使用父类的获得driver的方法
        this.driver = super.getDriver();
        xaConsultAction = new XAConsultAction(driver);
        assertion = new Assertion(driver);
        driver.browserMax();
        driver.get(GlobalConfig.getUrl(UrlType.LOGIN));
        driver.setCookie("SESSION");
    }

    @Test(description = "资讯页面title检查", dataProvider = "XAConsultData")
    public void checkPageTitle(BasePageObject bpo)throws Exception{
        xaConsultAction.checkPageTitle();
        Assert.assertTrue(assertion.VerityNotTextPresent(bpo.getExpected()),"资讯页面title检查");
    }

    @Test(description = "资讯检索", dataProvider = "XAConsultData")
    public void queryNews(BasePageObject object)throws Exception{
        caseData = getJson(object.getInputData(),XAConsultCaseData.class);
        xaConsultAction.queryConsult(caseData.getTitle());
        Assert.assertTrue(assertion.VerityNotTextPresent(object.getExpected()),"资讯检索");
    }

    @Test(description = "添加资讯页面title检查", dataProvider = "XAConsultData")
    public void checkAddTitle(BasePageObject object){
        xaConsultAction.checkAddTitle();
        Assert.assertTrue(assertion.VerityNotTextPresent(object.getExpected()),"添加资讯页面title检查");
    }

    @Test(description = "添加资讯", dataProvider = "XAConsultData")
    public void addNews(BasePageObject object){
        caseData = getJson(object.getInputData(),XAConsultCaseData.class);
        xaConsultAction.addNews(caseData);
        String newsTitle = XACommon.strSub(caseData.getNewsTitle(), 0, 15, true);
        String message = assertion.VerityNotTextPresent(newsTitle) ? Message.ADDSUCCESS : Message.ADDFAIL;
        Assert.assertEquals(message,object.getExpected());
    }

    @AfterMethod
    public void tearDown(Method method){
        driver.closed();
    }

}
