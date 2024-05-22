package org.example;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TugasTest {


    private static final String BASE_URL = "https://www.saucedemo.com/";
    private static final String PASSWORD = "secret_sauce";
    private static final String[] USERNAMES = {
            "standard_user",
            "locked_out_user",
            "problem_user",
            "performance_glitch_user",
            "error_user",
            "visual_user"
    };

    @Test
    public void accessSVUGMUsingEdge() {
        //System.setProperty("webdriver.edge.driver", "path/to/your/edgedriver.exe");
        WebDriver driver;
        driver = new EdgeDriver();
        driver.get("https://sv.ugm.ac.id/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        assertTrue(title.contains("Sekolah Vokasi"), "Title does not contain expected text");
        // WebElement someElement = driver.findElement(By.id("someElementId"));
        // assertTrue(someElement.isDisplayed(), "Element is not displayed as expected");

        // Close the browser
        driver.quit();
    }

    @Test
    public void loginTest() {
        WebDriver driver = new EdgeDriver();

        try {
            for (String username : USERNAMES) {
                driver.get(BASE_URL);

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

                WebElement usernameField = driver.findElement(By.id("user-name"));
                WebElement passwordField = driver.findElement(By.id("password"));
                WebElement loginButton = driver.findElement(By.id("login-button"));

                usernameField.sendKeys(username);
                passwordField.sendKeys(PASSWORD);

                long startTime = System.nanoTime();
                loginButton.click();

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

                boolean loginSuccessful = driver.findElements(By.className("inventory_list")).size() > 0;
                long endTime = System.nanoTime();
                long duration = (endTime - startTime) / 1_000_000;

                if (username.equals("locked_out_user")) {
                    WebElement errorMessage = driver.findElement(By.cssSelector("h3[data-test='error']"));
                    assertTrue(errorMessage.isDisplayed(), "Error message should be displayed for locked_out_user");
                    System.out.println(username + ": Login failed as expected. Time taken: " + duration + " ms.");
                } else {
                    assertTrue(loginSuccessful, "Login should be successful for " + username);
                    System.out.println(username + ": Login successful. Time taken: " + duration + " ms.");

                    if (loginSuccessful) {
                        WebElement menuButton = driver.findElement(By.id("react-burger-menu-btn"));
                        menuButton.click();
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));
                        logoutLink.click();
                    }
                }
            }
        } finally {
            driver.quit();
        }
    }
}







