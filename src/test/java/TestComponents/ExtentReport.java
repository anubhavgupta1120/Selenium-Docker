package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;

public class ExtentReport {
    protected static ExtentReports report;

    public static ExtentReports configExtentReport(String TesterName, String Title) throws IOException {
//        https://github.com/extent-framework/extentreports-java visit this and paste config.xml file in resource
        final String Path = System.getProperty("user.dir")+"/test-output/ExtentReport/TestReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(Path);
        reporter.config().setDocumentTitle("Test Case Report");
        reporter.config().setReportName(Title);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().thumbnailForBase64(true);
//        reporter.loadXMLConfig(System.getProperty("user.dir")+"/ExtentReportConfig/Spark-Config.xml");

        report = new ExtentReports();
        report.attachReporter(reporter);
        report.setSystemInfo("Tester", TesterName);
        return report;

    }
}
