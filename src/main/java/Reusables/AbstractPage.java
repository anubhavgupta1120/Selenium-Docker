package Reusables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    public AbstractPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public abstract boolean isAt();
    public void waitUntilElementVisibility(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void goTo(String url){
        driver.get(url);
    }

}
