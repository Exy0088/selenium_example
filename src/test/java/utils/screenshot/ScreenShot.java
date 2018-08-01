package utils.screenshot;

import base.driver.BaseDriver;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.log.Log;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by cdyoue on 2018/7/12.
 */
public class ScreenShot {

    private Log log = new Log(this.getClass());
    private BaseDriver driver;


    public ScreenShot(BaseDriver driver) {
        this.driver = driver;
    }

    /**
     * 截图
     */
    public  void takeScreenshot(){
        long l = System.currentTimeMillis();
        String screenName = String.valueOf(l)+".jpg";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String screenshotFile = sdf.format(date);
        if (!new File("test-output\\screenshot\\"+screenshotFile).isDirectory()){
            new File("test-output\\screenshot\\"+screenshotFile).mkdirs();
        }
        final String screenPath = new File("test-output\\screenshot\\"+screenshotFile).getAbsoluteFile() + "\\"+screenName;
        File file = ((TakesScreenshot) driver.getDriver()).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(file,new File(screenPath));
            log.error("错误截图");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        String screenName = String.valueOf(1)+".jpg";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String screenshotFile = sdf.format(date);
        File f = new File("test-output\\screenshot\\"+screenshotFile);
        if(!f.exists()&&f.isDirectory()){
            f.mkdir();
        }
    }

}
