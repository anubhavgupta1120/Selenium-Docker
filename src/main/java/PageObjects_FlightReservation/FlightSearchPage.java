package PageObjects_FlightReservation;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends AbstractPage {
    WebDriver driver;
    public FlightSearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(SearchFlightButton));
        return SearchFlightButton.isDisplayed();
    }

    @FindBy(id = "passengers")
    private WebElement PassengersSelectElement;

    @FindBy(id = "search-flights")
    private WebElement SearchFlightButton;

    public void setPassenger(String passengerCount){
        Select select = new Select(PassengersSelectElement);
        select.selectByValue(passengerCount);
    }

    public FlightSelectionPage searchFlight(){
        SearchFlightButton.click();
        return new FlightSelectionPage(driver);
    }
}
