package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;

    By companyMenu = By.linkText("Company");
    By careersLink = By.linkText("Careers");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToCareersPage() {
        driver.findElement(companyMenu).click(); // Company menüsüne tıkla
        driver.findElement(careersLink).click(); // Careers linkine tıkla
    }
}