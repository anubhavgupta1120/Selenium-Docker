package TestReusables;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;

public class BaseTest extends ReusableTestMethods {
    @BeforeSuite
    public void setProperties() throws IOException {
        initializeProperties();
    }
    //    FlightBooking
    //    VendorPortal
    public WebDriver driver;
    @BeforeMethod(alwaysRun = true)
    public void initializeDriver() throws IOException {
        if (Boolean.parseBoolean(getProperty(Constant.GRID_ENABLED))) {
            this.driver = initializeRemoteDriver();
        } else {
            this.driver = initializeLocalDriver();
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
