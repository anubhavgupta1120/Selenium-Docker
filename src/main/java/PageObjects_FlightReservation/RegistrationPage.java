package PageObjects_FlightReservation;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends AbstractPage {
    WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(how = How.ID, using = "firstName")
    private WebElement FirstNameElement;

    @FindBy(how = How.ID, using = "lastName")
    private WebElement LastNameElement;

    @FindBy(how = How.ID, using = "email")
    private WebElement EmailElement;

    @FindBy(how = How.ID, using = "password")
    private WebElement PasswordElement;

    @FindBy(how = How.NAME, using = "street")
    private WebElement StreetElement;

    @FindBy(how = How.NAME, using = "city")
    private WebElement CityElement;

    @FindBy(how = How.NAME, using = "zip")
    private WebElement ZipElement;

    @FindBy(how = How.ID, using = "register-btn")
    private WebElement RegisterButtonElement;

    @FindBy(id = "inputState")
    private WebElement StateDropdownElement;

    public void fillForm(String firstName, String lastName, String email, String password, String street,
                         String city, String state, String zipcode) {
        FirstNameElement.sendKeys(firstName);
        LastNameElement.sendKeys(lastName);
        EmailElement.sendKeys(email);
        PasswordElement.sendKeys(password);
        StreetElement.sendKeys(street);
        CityElement.sendKeys(city);
        Select select = new Select(StateDropdownElement);
        select.selectByValue(state);
        ZipElement.sendKeys(zipcode);
    }

    public RegistrationConfirmationPage Register() {
        RegisterButtonElement.click();
        return new RegistrationConfirmationPage(driver);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(FirstNameElement));
        return FirstNameElement.isDisplayed();
    }
}
