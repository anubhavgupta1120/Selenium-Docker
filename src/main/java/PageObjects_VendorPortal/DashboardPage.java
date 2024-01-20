package PageObjects_VendorPortal;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {
    WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    public DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "monthly-earning")
    private WebElement MonthlyEarningElement;
    @FindBy(id = "annual-earning")
    private WebElement AnnualEarningElement;
    @FindBy(id = "profit-margin")
    private WebElement ProfitMarginElement;
    @FindBy(id = "available-inventory")
    private WebElement InventoryElement;
    @FindBy(xpath = "//div[@id = 'dataTable_filter']//input")
    private WebElement SearchBoxElement;
    @FindBy(id = "dataTable_info")
    private WebElement TotalEntriesElement;
    @FindBy(xpath = "//img[contains(@class, 'img-profile')]")
    private WebElement UserProfileElement;
    @FindBy(xpath = "//a[@class = 'dropdown-item' and @data-toggle = 'modal']")
    private WebElement LogoutLinkElement;
    @FindBy(xpath = "//div[@id = 'logoutModal']//a")
    private WebElement LogoutButtonElement;

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(AnnualEarningElement));
        return AnnualEarningElement.isDisplayed();
    }

    public String getMonthlyEarning() {
        return MonthlyEarningElement.getText();
    }

    public String getAnnualEarning() {
        return AnnualEarningElement.getText();
    }

    public String getProfitMargin() {
        return ProfitMarginElement.getText();
    }

    public String getAvailableInventory() {
        return InventoryElement.getText();
    }

    public void searchOrderHistory(String keyword) {
        SearchBoxElement.sendKeys(keyword);
    }

    public int getSearchResultCount() {
        int count = Integer.parseInt(TotalEntriesElement.getText().split("of")[1].split("entries")[0].trim());
        log.info("Total entries in Table# {}", count);
        return count;
    }

    public void logout() {
        UserProfileElement.click();
        waitUntilElementVisibility(LogoutLinkElement);
        LogoutLinkElement.click();
        waitUntilElementVisibility(LogoutButtonElement);
        LogoutButtonElement.click();
    }

}
