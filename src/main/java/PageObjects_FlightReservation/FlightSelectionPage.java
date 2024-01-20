package PageObjects_FlightReservation;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {
    WebDriver driver;
    public FlightSelectionPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(ConfirmFlightElement));
        return ConfirmFlightElement.isDisplayed();
    }

    @FindBy(name = "departure-flight")
    private List<WebElement> DepartureFlightElement;
    @FindBy(name = "arrival-flight")
    private List<WebElement> ArrivalFlightElement;
    @FindBy(id = "confirm-flights")
    private WebElement ConfirmFlightElement;

    public void selectFlight(){
        int random = ThreadLocalRandom.current().nextInt(0,DepartureFlightElement.size());
        DepartureFlightElement.get(random).click();
        ArrivalFlightElement.get(random).click();
    }
    public ConfirmationPage confirmFlight(){
        ConfirmFlightElement.click();
        return new ConfirmationPage(driver);
    }
}
