package PageObjects_FlightReservation;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends AbstractPage {
    WebDriver driver;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    @FindBy(how = How.ID, using = "go-to-flights-search")
    private WebElement SearchFlightElement;

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(SearchFlightElement));
        return SearchFlightElement.isDisplayed();
    }

    public FlightSearchPage goToFlightSearch() {
        SearchFlightElement.click();
        return new FlightSearchPage(driver);
    }

}
