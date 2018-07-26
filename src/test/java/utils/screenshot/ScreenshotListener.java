package utils.screenshot;

import base.BaseDriver;
import org.apache.velocity.runtime.directive.Foreach;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import test.CaseBase;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by cdyoue on 2018/7/12.
 */
public class ScreenshotListener extends TestListenerAdapter {

    private ScreenShot screenShot;
    @Override
    public void onTestFailure(ITestResult tr){
        BaseDriver driver = null;
        Object instance = tr.getInstance();
        Class<?> clazz = instance.getClass();
        try {
            Method method = clazz.getMethod("getDriver", null);
            driver = ( BaseDriver)method.invoke(instance, null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        screenShot = new ScreenShot(driver);
        screenShot.takeScreenshot();
    }
}
