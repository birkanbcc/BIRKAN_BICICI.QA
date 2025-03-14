package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class QAJobsPage {
    WebDriver driver;

    By filterLocation = By.id("filter-by-location");
    By filterDepartment = By.id("filter-by-department");
    By jobList = By.cssSelector(".job-list-item");
    By viewRoleButton = By.cssSelector(".btn-apply");

    public QAJobsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void filterJobs(String location, String department) {
        driver.findElement(filterLocation).sendKeys(location);
        driver.findElement(filterDepartment).sendKeys(department);
    }

    public List<WebElement> getJobList() {
        return driver.findElements(jobList);
    }

    public void clickFirstViewRole() {
        driver.findElement(viewRoleButton).click();
    }
}