import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InsiderTest {
    WebDriver driver;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/Users/birkanbcc/Desktop/Google Chrome.app/Contents/MacOS/Google Chrome");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void testQAJobs() {
        try {

            driver.get("https://useinsider.com");

            WebElement companyMenu = driver.findElement(By.linkText("Company"));
            companyMenu.click();
            WebElement careersLink = driver.findElement(By.linkText("Careers"));
            careersLink.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("careers"));


            driver.get("https://useinsider.com/careers/quality-assurance/");

            WebElement seeAllQAJobsButton = driver.findElement(By.cssSelector(".btn.btn-outline-secondary.rounded.text-medium.mt-2.py-3.px-lg-5.w-100.w-md-50"));
            seeAllQAJobsButton.click();

            WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-search__field")));
            locationDropdown.click();

            WebElement istanbulOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'Istanbul, Turkey')]")));
            istanbulOption.click();

            WebElement departmentDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select2-selection__rendered")));
            departmentDropdown.click();

            WebElement qaOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(text(), 'Quality Assurance')]")));
            qaOption.click();

            List<WebElement> jobs = driver.findElements(By.cssSelector(".job-list-selector"));
            Assert.assertTrue("İş listesi boş!", jobs.size() > 0);

            for (WebElement job : jobs) {
                String title = job.findElement(By.cssSelector(".position")).getText();
                String location = job.findElement(By.cssSelector(".location")).getText();
                Assert.assertTrue("Başlık QA içermiyor: " + title, title.contains("Quality Assurance"));
                Assert.assertTrue("Lokasyon Istanbul değil: " + location, location.contains("Istanbul, Turkey"));
            }

            WebElement viewRoleButton = jobs.get(0).findElement(By.cssSelector(".view-role-selector"));
            viewRoleButton.click();


        } catch (AssertionError e) {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            screenshot.renameTo(new File("hata.png"));
            throw e;
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}