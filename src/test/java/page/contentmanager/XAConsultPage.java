package page.contentmanager;

import base.driver.BaseDriver;
import org.openqa.selenium.WebElement;
import utils.Locator;
import utils.log.Log;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-18 16:27
 **/
public class XAConsultPage {

    private Log log = new Log(this.getClass());
    private BaseDriver driver;
    private Locator locator;
    private final String path = "src\\test\\resources\\yaml\\contentmanager\\xaconsult.yaml";

    public XAConsultPage(BaseDriver driver) {
        this.driver = driver;
        this.locator = new Locator(driver,path);
    }

    /**
     * 定位内容管理菜单
     * @return
     */
    public WebElement getContentmanagerMemu(){
        return locator.getLocator("contentmanager_memu",true);
    }

    /**
     * 定位雄安咨询菜单
     * @return
     */
    public WebElement getXAconsultMemu(){
        return locator.getLocator("xaconsult_memu",true);
    }

    /**
     * 定位title(咨询管理)
     * @return
     */
    public WebElement getTitle(){
        return locator.getLocator("title",true);
    }

    /**
     * 定位标题名称输入框
     * @return
     */
    public WebElement getTitleInput(){
        return locator.getLocator("title_input",true);
    }

    /**
     * 定位搜索按钮
     * @return
     */
    public WebElement getSearchButton(){
        return locator.getLocator("search_button",true);
    }

    /**
     * 定位咨询列表
     * @return
     */
    public WebElement getTableTr(){
        return locator.getLocator("table_tr",true);
    }

    /**
     * 定位添加资讯按钮
     * @return
     */
    public WebElement getAddButton(){
        return locator.getLocator("add_button",true);
    }

    public WebElement getNewsTitle(){
        return locator.getLocator("news_title",true);
    }

    public WebElement getNewsSource(){
        return locator.getLocator("news_source",true);
    }

    public WebElement getNewsUpload(){
        return locator.getLocator("news_upload",true);
    }

    public WebElement getNewsDescribe(){
        return locator.getLocator("news_describe",true);
    }

    public WebElement getNewsContent(){
        return locator.getLocator("news_content",true);
    }

    public WebElement getAddImgSubmit(){
        return locator.getLocator("add_img_submit",true);
    }
    public WebElement getAddButtonSubmit(){
        return locator.getLocator("add_button_submit",true);
    }


}
