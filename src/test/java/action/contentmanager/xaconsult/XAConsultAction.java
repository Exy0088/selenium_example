package action.contentmanager.xaconsult;

import base.Message;
import base.XACommon;
import base.driver.BaseDriver;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import handler.LoginHandler;
import handler.contentmanager.XAConsultHandler;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import pageobject.BasePageObject;
import pageobject.contentmanager.XAConsultCaseData;
import utils.Assertion;
import utils.PropertiesUtil;
import utils.log.Log;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-18 17:03
 **/
public class XAConsultAction {

    private Log log = new Log(this.getClass());
    private XAConsultHandler xaConsultHandler;
    private BaseDriver driver;
    private Assertion assertion;

    public XAConsultAction(BaseDriver driver) {
        this.xaConsultHandler = new XAConsultHandler(driver);
        this.driver = driver;
        assertion = new Assertion(driver);
    }

    /**
     * 检查资讯页面title
     * @return
     */
    public void checkPageTitle(){

       publicOperation();

    }

    /**
     *检索资讯
     * @param content
     */
    public void queryConsult(String content){

        publicOperation();
        xaConsultHandler.sendKeysTitleInput(content);
        xaConsultHandler.clickSearchButton();
        //driver.moveScroll();

    }

    /**
     * 检查添加页面title
     */
    public void checkAddTitle(){
        publicOperation();
        xaConsultHandler.clickAddButton();
    }

    /**
     * 添加资讯
     * @param caseData
     * @return
     */
    public void addNews(XAConsultCaseData caseData) {
        checkAddTitle();
        driver.swithFrame(0);
        xaConsultHandler.sendKeysNewsTitle(caseData.getNewsTitle());
        xaConsultHandler.sendNewsSource(caseData.getNewsSource());
        xaConsultHandler.clickNewsNewsUpload();
        XACommon.uploadImg(caseData.getNewsUpload(),XACommon.getFilePath("image\\open.png"));
        XACommon.sleep(2000);
        xaConsultHandler.clickAddImgSub();
        XACommon.sleep(1000);
        xaConsultHandler.sendKeysNewsDescribe(caseData.getNewsDescribe());
        /*js处理富文本编辑框*/
        driver.swithFrame("ueditor_0");
        //定位到富文本输入框所在的frame
        JavascriptExecutor js = (JavascriptExecutor) driver.getDriver();
        js.executeScript("document.body.innerHTML='"+caseData.getNewsContent()+"'"); //通过js赋值进去
        //xaConsultHandler.sendKeysNewsContent(caseData.getNewsContent());
        driver.parentFrame();//添加页面
        xaConsultHandler.clickAddButtonSub();
        driver.refresh();
        XACommon.sleep(1000);
        driver.swithFrame(1);
    }

    public void publicOperation(){
        xaConsultHandler.clickContentmanagerMemu();
        xaConsultHandler.clickXAconsultMemu();
        driver.swithFrame(1);
    }
    public static void main(String[] args) {
        String filePath = XACommon.getFilePath("image\\open.png");
        System.out.println(filePath);
    }
}
