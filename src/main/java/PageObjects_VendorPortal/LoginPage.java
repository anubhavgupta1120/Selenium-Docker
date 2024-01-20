package PageObjects_VendorPortal;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AbstractPage {
    WebDriver driver;
    public LoginPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(id = "username")
    private WebElement UsernameElement;
    @FindBy(id = "password")
    private WebElement PasswordElement;
    @FindBy(id = "login")
    private WebElement LoginButtonElement;

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(LoginButtonElement));
        return LoginButtonElement.isDisplayed();
    }

    public DashboardPage login(String username, String password){
        UsernameElement.sendKeys(username);
        PasswordElement.sendKeys(password);
        LoginButtonElement.click();
        return new DashboardPage(driver);
    }
}
