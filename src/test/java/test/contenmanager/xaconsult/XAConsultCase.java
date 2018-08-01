package test.contenmanager.xaconsult;

import action.contentmanager.xaconsult.XAConsultAction;
import base.UrlType;
import base.driver.BaseDriver;
import base.GlobalConfig;
import base.XACommon;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;
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
    private XAConsultAction xaca;
    private Assertion assertion;
    private final String filePath = "testcase\\xiongan.xls";
    private final String caseSheet = "内容管理-雄安资讯测试数据";
    private Map map;

    @BeforeClass
    public void init(){
        map = XACommon.getSession(GlobalConfig.getUrl(UrlType.LOGIN), GlobalConfig.getKeyValue("DEFAULTUSERNAME"), GlobalConfig.getKeyValue("DEFAULTPASSWORD"));
    }

    @BeforeMethod
    public void setUp(){

        //使用父类的获得driver的方法
        this.driver = super.getDriver();
        xaca = new XAConsultAction(driver);
        assertion = new Assertion(driver);
        driver.browserMax();
        driver.get(GlobalConfig.getUrl(UrlType.LOGIN));
        driver.setCookie(map);
        driver.get(GlobalConfig.getUrl(UrlType.XAOBNEWSRELEASE));
    }

    @Test(description = "资讯检索", dataProvider = "XAConsultData")
    public void query(XAConsultCaseData caseData){
        Reporter.log(caseData.toString());
        xaca.queryConsult(caseData.getTitleName());
        Assert.assertTrue(assertion.VerityNotTextPresent(XACommon.getNum(caseData.getExpected())));


    }

    @AfterMethod
    public void tearDown(Method method){
        driver.closed();
    }

    @DataProvider(name = "XAConsultData")
    public Iterator<Object[]> XAConsultData(Method method) throws Exception {
        //创建一个Object数组集合
        List<Object[]> result = new ArrayList<Object[]>();
        List<List<XAConsultCaseData>> caseDatas = XACommon.casesData(filePath, caseSheet, method, XAConsultCaseData.class);
        //循环遍历集合
        for(int i=0;i<caseDatas.size();i++){
            for(int j =0;j<caseDatas.get(i).size();j++){
                caseData = new XAConsultCaseData();
                caseData = caseDatas.get(i).get(j);
                //将遍历出来的映射实体类，添加到Object数组集合中
                result.add(new Object[]{caseData});
            }
        }
        return result.iterator();
    }


}
