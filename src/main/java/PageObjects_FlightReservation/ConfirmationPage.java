package PageObjects_FlightReservation;

import Reusables.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfirmationPage extends AbstractPage {
    WebDriver driver;

    private static final Logger log = LoggerFactory.getLogger(ConfirmationPage.class);

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "(//p[@class = 'fw-bold'])[3]")
    private WebElement PriceElement;


    @Override
    public boolean isAt() {
        wait.until(ExpectedConditions.visibilityOf(PriceElement));
        return PriceElement.isDisplayed();
    }


    public String getPrice() {
        log.info("Total Price # {}", PriceElement.getText());
        return PriceElement.getText();
    }
}
