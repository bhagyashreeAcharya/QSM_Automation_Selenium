import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.PageLoadStrategy;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

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
    public void verifyLogin() {

        // Locators
        By emailField = By.cssSelector("input[type='email']");
        By passwordField = By.cssSelector("input[type='password']");
        By signInButton = By.xpath("//button[contains(text(),'Sign In')]");
        By dashboardTitle = By.xpath("//h1[text()='AI Command Center']");

        // Wait for login page
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(emailField)
        );

        // Enter credentials
        email.sendKeys("testing@aivoa.net");
        driver.findElement(passwordField).sendKeys("password123");

        // Click login
        driver.findElement(signInButton).click();

        // Wait for dashboard
        WebElement title = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dashboardTitle)
        );

        // Assertion
        Assert.assertTrue(title.isDisplayed(), "Login failed - Dashboard not visible");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}