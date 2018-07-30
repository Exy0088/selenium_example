package utils;

import base.BaseDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import test.CaseBase;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author hezhaowei
 * @create 2018-07-27 17:40
 **/
public class AllureReporterListener implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName) throws IOException {

        return ((TakesScreenshot) BaseDriver.driver).getScreenshotAs(OutputType.BYTES);
    }
}
