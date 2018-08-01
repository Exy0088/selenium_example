package handler.contentmanager;

import base.driver.BaseDriver;
import handler.BaseHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import page.contentmanager.XAConsultPage;
import utils.log.Log;

import java.util.List;

/**
 *  雄安咨询页面元素操作类
 * @author hezhaowei
 * @create 2018-07-18 16:46
 **/
public class XAConsultHandler extends BaseHandler{

    private Log log = new Log(this.getClass());
    private XAConsultPage xaConsultPage;

    public XAConsultHandler(BaseDriver driver) {
        super(driver);
        this.xaConsultPage = new XAConsultPage(driver);
    }

    /**
     * 点击内容管理菜单
     */
    public void clickContentmanagerMemu(){
        this.click(xaConsultPage.getContentmanagerMemu());
    }

    /**
     * 点击雄安咨询菜单
     */
    public void clickXAconsultMemu(){
        this.click(xaConsultPage.getXAconsultMemu());
    }

    /**
     * 输入搜索内容
     * @param content
     */
    public void sendKeysTitleInput(String content){
        this.clearAndSendKeys(xaConsultPage.getTitleInput(),content);
    }

    /**
     * 点击搜索按钮
     */
    public void clickSearchButton(){
        this.click(xaConsultPage.getSearchButton());
    }

    public Integer getConsultSize(){
        WebElement tableTr = xaConsultPage.getTableTr();
        WebElement tbody = tableTr.findElement(By.tagName("tbody"));
        List<WebElement> tr = tbody.findElements(By.tagName("tr"));
        return tr.size();
    }
}
