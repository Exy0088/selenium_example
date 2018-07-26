package action.contentmanager.xaconsult;

import base.BaseDriver;
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

    public void searchConsult(String content){
        if( content == null){
            xh.clickContentmanagerMemu();
            xh.clickXAconsultMemu();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            xh.sendKeysTitleInput(content);
            xh.clickSearchButton();
        }else {
            xh.clickContentmanagerMemu();
            xh.clickXAconsultMemu();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            baseDriver.swithFrame(1);
            xh.sendKeysTitleInput(content);
            xh.clickSearchButton();
        }
    }
}
