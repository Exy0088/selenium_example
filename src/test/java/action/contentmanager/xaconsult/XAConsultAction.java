package action.contentmanager.xaconsult;

import base.driver.BaseDriver;
import handler.LoginHandler;
import handler.contentmanager.XAConsultHandler;
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
    private XAConsultHandler xh;
    private BaseDriver baseDriver;

    public XAConsultAction(BaseDriver driver) {
        this.xh = new XAConsultHandler(driver);
        this.baseDriver = driver;
    }

    public void queryConsult(String content){

//            xh.clickContentmanagerMemu();
//            xh.clickXAconsultMemu();
            xh.sendKeysTitleInput(content);
            xh.clickSearchButton();

    }
    public Integer getsize() {
        return xh.getConsultSize();
    }
}
