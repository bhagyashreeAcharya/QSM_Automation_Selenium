import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DeviationTest {

    WebDriver driver;
    WebDriverWait wait;

    By emailField = By.cssSelector("input[type='email']");
    By passwordField = By.cssSelector("input[type='password']");
    By signInButton = By.xpath("//button[contains(text(),'Sign In')]");

    By dashboardTitle = By.xpath("//h1[text()='AI Command Center']");
    By sidebarHandle = By.id("sidebar-handle");

    By qualityEventsHeader = By.xpath("//h3[contains(text(),'Quality Events')]");
    By eventsArrow = By.id("arrow-events");

    By deviationsMenu = By.xpath("//span[text()='Deviations']");
    By newDeviationButton = By.xpath("//button[contains(.,'New Deviation / Non-conformance')]");
    By deviationFormTitle = By.xpath("//h1[@title='Deviation / Non-conformance']");

    @BeforeMethod
    public void setup() {

        // Disable Chrome password popup
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open login page
        driver.get("http://216.48.184.249:5289/login");
    }

    @Test
    public void openDeviationForm() {

        // LOGIN
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField))
                .sendKeys("testing@aivoa.net");

        driver.findElement(passwordField).sendKeys("password123");

        driver.findElement(signInButton).click();

        // CONFIRM DASHBOARD
        wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));

        // OPEN SIDEBAR
        wait.until(ExpectedConditions.elementToBeClickable(sidebarHandle)).click();

        // WAIT FOR QUALITY EVENTS SECTION
        wait.until(ExpectedConditions.visibilityOfElementLocated(qualityEventsHeader));

        // EXPAND QUALITY EVENTS
        wait.until(ExpectedConditions.elementToBeClickable(eventsArrow)).click();

        // CLICK DEVIATIONS
        wait.until(ExpectedConditions.elementToBeClickable(deviationsMenu)).click();

      // WAIT FOR PAGE TO LOAD (Deviations page)
        wait.until(ExpectedConditions.visibilityOfElementLocated(newDeviationButton));

      // CLICK NEW DEVIATION
        wait.until(ExpectedConditions.elementToBeClickable(newDeviationButton)).click();
        // VERIFY FORM
        WebElement formTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(deviationFormTitle)
        );

        Assert.assertTrue(formTitle.isDisplayed(), "Deviation form not opened");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}