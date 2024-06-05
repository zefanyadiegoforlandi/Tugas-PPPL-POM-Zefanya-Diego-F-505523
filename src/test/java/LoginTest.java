import org.example.LoggedInPage;
import org.example.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class LoginTest {

    @Test
    public void testLogin() {
        WebDriver driver = new EdgeDriver();


        try {
            driver.get("https://practicetestautomation.com/practice-test-login/");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.enterUsername("student");
            loginPage.enterPassword("Password123");
            loginPage.clickLoginButton();

            LoggedInPage login = new LoggedInPage(driver);

            if (login.isUrlCorrect()) {
                System.out.println("URL(https://practicetestautomation.com/practice-test-login/) verification passed");
            } else {
                System.out.println("URL (https://practicetestautomation.com/practice-test-login/)verification failed");
            }

            if (login.isCongratulationsMessageVisible()) {
                System.out.println("Congratulations Student Message verification passed");
            } else {
                System.out.println("Congratulations Student Message verification failed");
            }

            if (login.isLogoutButtonVisible()) {
                System.out.println("Log Out button verification passed");
            } else {
                System.out.println("Log Out button verification failed");
            }

            if (login.isSuccessMessageCorrect()) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            driver.quit();
        }
    }
}