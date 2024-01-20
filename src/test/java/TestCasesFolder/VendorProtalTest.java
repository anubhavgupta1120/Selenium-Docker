package TestCasesFolder;

import PageObjects_VendorPortal.DashboardPage;
import PageObjects_VendorPortal.LoginPage;
import TestReusables.BaseTest;
import TestReusables.Constant;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class VendorProtalTest extends BaseTest {

    LoginPage loginPage;
    @Test(dataProvider = "getData")
    public void vendorPortalFlow(HashMap<String, String> input) throws IOException {
        loginPage = new LoginPage(driver);
        loginPage.goTo(getProperty(Constant.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        DashboardPage dashboardPage = loginPage.login(input.get("Username"), input.get("Password"));
        Assert.assertTrue(dashboardPage.isAt());
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), input.get("ExpectedMonthlyIncome"));
        Assert.assertEquals(dashboardPage.getAnnualEarning(), input.get("ExpectedAnnualIncome"));
        Assert.assertEquals(dashboardPage.getProfitMargin(), input.get("ExpectedProfitMargin"));
        Assert.assertEquals(dashboardPage.getAvailableInventory(), input.get("ExpectedAvailableInventory"));
        dashboardPage.searchOrderHistory(input.get("SearchKeyword"));
        Assert.assertEquals(dashboardPage.getSearchResultCount(), Integer.valueOf(input.get("ExpectedTableCount")));
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> JsonData = readJsonData("TestData/VendorPortalData.json");
        Object[][] data = new Object[JsonData.size()][1];
        for (int i = 0; i < JsonData.size(); i++) {
            data[i][0] = JsonData.get(i);
        }
        return data;
    }

}
