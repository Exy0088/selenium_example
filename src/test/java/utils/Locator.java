package utils;

import base.BaseDriver;
import org.apache.velocity.texen.util.FileUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.log.Log;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 *元素定位类
 * @author hezhaowei
 * @create 2018-07-15 16:17
 **/
public class Locator {

    private Log log = new Log(Locator.class);
    private BaseDriver driver;
    private String path;

    public Locator(BaseDriver driver,String path) {
        this.driver = driver;
        this.path = path;
    }

//    public WebElement getElement(String key) {
//        HashMap<String, HashMap<String, String>> map = YamlFile.parseYamlFile(path);
//        String type = map.get(key).get("type");
//        String value = map.get(key).get("value");
//        WebElement element = this.watiForElement(this.getBy(type, value));
//        if(this.waitElementToBeDisplayed(element)){
//            return  element;
//        }else {
//            return null;
//        }
    //   }

    /**
     * 根据传入的type、id
     * @param type
     * @param value
     * @return
     */
    private By getBy(String type, String value) {
        By by = null;
        if (type.equals("id")) {
            by = By.id(value);
        }else if (type.equals("name")) {
            by = By.name(value);
        }else if (type.equals("xpath")) {
            by = By.xpath(value);
        }else if (type.equals("className")) {
            by = By.className(value);
        }else if (type.equals("linkText")) {
            by = By.linkText(value);
        }
        return by;
    }

    /**
     * 保证locator是显示在页面上的
     * @param by
     * @return
     */
    private WebElement watiForElement(final By by) {
        long waitTime = Long.parseLong(PropertiesUtil.getPro("WAITTIME"));
        try {
            WebElement  element = new WebDriverWait(BaseDriver.driver, Long.parseLong(PropertiesUtil.getPro("WAITTIME")),2000)
                    .until(new ExpectedCondition<WebElement>() {
                        public WebElement apply(WebDriver d) {
                            return d.findElement(by);
                        }
                    });
            return element;
        } catch (TimeoutException e) {
            throw new NoSuchElementException(" 等待 " + waitTime+"秒，没有定位到"+by.toString());
        }
    }

    /**
     * 只显示displayed的元素对象
     * @param element
     * @return
     */
    private boolean waitElementToBeDisplayed(final  WebElement element){
        boolean wait = false;
        if(element == null){
            return  wait;
        }else {
            wait = new WebDriverWait(BaseDriver.driver,Long.parseLong(PropertiesUtil.getPro("WAITTIME"))).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d){
                    return element.isDisplayed();
                }
            });
        }
        return wait;
    }

    /**
     * 读取yaml文件中的key,进行定位
     * @param key
     * @param wait
     * @return 定位元素
     */
    public WebElement getLocator(String key, boolean wait) {
        HashMap<String, HashMap<String, String>> map = ParseYaml.parseYamlFile(path);
        WebElement element = null;
        if (map.containsKey(key)) {
            HashMap<String, String> m = map.get(key);
            String type = m.get("type");
            String value = m.get("value");
            By by = this.getBy(type, value);
            if (wait) {
                element = this.watiForElement(by);
                boolean flag = this.waitElementToBeDisplayed(element);
                if (!flag) {
                    element = null;
                    log.error("页面中定位不到" + value + "元素");
                }
            } else {
                try {
                    element = driver.findElement(by);
                } catch (Exception e) {
                    element = null;
                }
            }
        } else{
            log.error(path+"文件中，没有找到"+"Locator:"+key);
        }
        return element;
    }

    public WebElement getLocator(String type, String value,boolean wait) {
        WebElement element = null;
        if(type != null && value !=null){
            By by = this.getBy(type, value);
            if (wait) {
                element = this.watiForElement(by);
                boolean flag = this.waitElementToBeDisplayed(element);
                if (!flag) {
                    element = null;
                    log.error("页面中定位不到" + value + "元素");
                }
            } else {
                try {
                    element = driver.findElement(by);
                } catch (Exception e) {
                    element = null;
                }
            }
        }else{
            log.error(path+"文件中，没有找到"+"Locator:"+type);
        }
        return element;

    }
}
