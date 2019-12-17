package utils;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListeners implements ITestListener, IInvokedMethodListener {

    private static String getTestMethodName(ITestResult iTestResult){
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {iTestContext.setAttribute("WebDriver", BaseTest.getDriver());}

    @Override
    public void onFinish(ITestContext iTestContext) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        AttachUtils.takeScreenShot(BaseTest.getDriver(),"Test failed - " +getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        AttachUtils.takeScreenShot(BaseTest.getDriver(),"Test failed - " +getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        AttachUtils.takeScreenShot(BaseTest.getDriver(),"Test skipped - " +getTestMethodName(iTestResult));
    }

    @Override
        public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }
}