package TestReusables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class ReusableTestMethods {

    private static final Logger log = LoggerFactory.getLogger(ReusableTestMethods.class);
    private static final String PATH = "GlobalData/GlobalData.properties";
    private static Properties properties;

    public List<HashMap<String, String>> readJsonData(String Path) throws IOException {
        String JsonString = FileUtils.readFileToString(new File(Path), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> Data = mapper.
                readValue(JsonString, new TypeReference<List<HashMap<String, String>>>() {
                });
        return Data;
    }

    private Properties loadProperties() throws IOException {
        FileInputStream FIS = new FileInputStream(PATH);
        Properties properties = new Properties();
        properties.load(FIS);
        return properties;
    }

    public void initializeProperties() throws IOException {
        properties = loadProperties();
        for (String key : properties.stringPropertyNames()) {
            if (System.getProperties().containsKey(key)) {
                properties.replace(key, System.getProperty(key));
            }
        }
//        for (String key : properties.stringPropertyNames()) {
//            log.info("{} = {}", key,getProperty(key));
//        }
    }

    public String getProperty(String key) throws IOException {
//        initializeProperties();
        return properties.getProperty(key);
    }

    public WebDriver initializeLocalDriver() throws IOException {
        WebDriver driver = null;
        if (Constant.CHROME.equalsIgnoreCase(getProperty(Constant.BROWSER))) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (Constant.FIREFOX.equalsIgnoreCase(getProperty(Constant.BROWSER))) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public RemoteWebDriver initializeRemoteDriver() throws IOException {
        Capabilities capabilities = null;
        if (Constant.CHROME.equalsIgnoreCase(getProperty(Constant.BROWSER))) {
            capabilities = new ChromeOptions();
        } else if (Constant.FIREFOX.equalsIgnoreCase(getProperty(Constant.BROWSER))) {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = getProperty(Constant.GRID_URL_FORMAT);
        String hubHost = getProperty(Constant.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url {}", url);
        RemoteWebDriver driver = new RemoteWebDriver(new URL(url), capabilities);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public String getScreenShot(String TestCaseName, WebDriver driver) throws IOException {
//        File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        String pathOfSS = System.getProperty("user.dir") + "/ScreenShots/" + TestCaseName + ".png";
//        FileUtils.copyFile(sourcePath, new File(pathOfSS));
//        return pathOfSS;
          String base64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
          return base64;
    }


}
