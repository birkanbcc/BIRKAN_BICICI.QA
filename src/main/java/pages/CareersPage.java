package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareersPage {
    WebDriver driver;

    By locationsSection = By.id("locations");
    By teamsSection = By.id("teams");
    By lifeSection = By.id("life");

    public CareersPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean areSectionsVisible() {
        return driver.findElement(locationsSection).isDisplayed() &&
                driver.findElement(teamsSection).isDisplayed() &&
                driver.findElement(lifeSection).isDisplayed();
    }
}