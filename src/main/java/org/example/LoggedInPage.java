package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoggedInPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public LoggedInPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUrlCorrect() {
        return wait.until(ExpectedConditions.urlContains("practicetestautomation.com/logged-in-successfully/"));
    }

    public boolean isCongratulationsMessageVisible() {
        WebElement congratsMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Congratulations')]")));
        return congratsMessage != null;
    }

    public boolean isLogoutButtonVisible() {
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Log out')]")));
        return logoutButton != null;
    }

    public boolean isSuccessMessageCorrect() {
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-title")));
        String expectedMessage = "Logged In Successfully";
        String actualMessage = successMessage.getText();
        return expectedMessage.equals(actualMessage);
    }
}