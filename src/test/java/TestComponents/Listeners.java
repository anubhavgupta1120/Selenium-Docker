package TestComponents;

import TestReusables.ReusableTestMethods;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;

import java.io.IOException;

public class Listeners extends ReusableTestMethods implements ITestListener{
    public ExtentReports report = ExtentReport.configExtentReport("Anubhav Gupta", "Test-Report");
    public static ExtentTest Test;
    ThreadLocal<ExtentTest> threadLocal = new ThreadLocal<>();
    WebDriver driver;

    public Listeners() throws IOException {
    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub
        Test = report.createTest(result.getMethod().getMethodName());
        threadLocal.set(Test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // TODO Auto-generated method stub
        threadLocal.get().log(Status.PASS, "Test Case Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // TODO Auto-generated method stub
        threadLocal.get().fail(result.getThrowable());
//        String Path = null;
        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
//            Path = getScreenShot(result.getMethod().getMethodName(), driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String Path = getScreenShot(result.getMethod().getMethodName(), driver);
            threadLocal.get().addScreenCaptureFromBase64String(Path,result.getMethod().getMethodName());
//            threadLocal.get().addScreenCaptureFromPath(Path, result.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        threadLocal.get().addScreenCaptureFromPath(Path, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub
        threadLocal.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        // TODO Auto-generated method stub
        report.flush();
    }
}
