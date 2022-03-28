package Libraries;

import PageObjects.GoogleHomePage;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import io.github.bonigarcia.wdm.managers.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.managers.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class DriverFactory {

    private static WebDriver driver;
    static protected GoogleHomePage googleHomePage;
   
    protected void Setup(String browser) {
        InitiateDriver(browser);
        InitializePages();
        //return driver;
    }

    private void InitiateDriver(String browser){
        //String os = System.getProperty("os.name");
        DesiredCapabilities caps = new DesiredCapabilities();
        switch (browser) {
            case "chrome":
                ChromeDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "ie":
                caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                InternetExplorerDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver(caps);
                break;
            case "phantom":
                PhantomJsDriverManager.phantomjs().setup();
                caps.setCapability("takesScreenshot", true);
                caps.setJavascriptEnabled(true);
//                driver = new PhantomJSDriver(caps);
                break;
            case "edge":
                File file = new File(System.getProperty("user.dir") + "\\drivers\\MicrosoftWebDriver.exe");
                System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
                //EdgeDriverManager.getInstance().setup();
                caps = DesiredCapabilities.edge();
                driver = new EdgeDriver(caps);
                break;
          
            default:
                fail("Unknown browser");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public WebDriver getPage(String url) {
        driver.get(url);
        return driver;
    }

    public void InitializePages() {
        googleHomePage    = PageFactory.initElements(driver,  GoogleHomePage.class);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageURL() {
        return driver.getCurrentUrl();
    }

    public void tearDown() {
        driver.quit();

    }

}
