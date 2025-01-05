package StepDefinitions;

import io.cucumber.java.en.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoginPage;
import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

//    @Given("I am on the login page")
//    public void i_am_on_the_login_page() {
//        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        driver.manage().window().maximize();
//        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
//        loginPage = new LoginPage(driver);
//    }
    Dotenv dotenv = Dotenv.load();

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Use the URL from the .env file
        String baseUrl = dotenv.get("BASE_URL");
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("I should be redirected to the homepage")
    public void i_should_be_redirected_to_the_homepage() {
        String expectedURL = dotenv.get("HOME_PAGE_URL"); // Optional: Store expected URLs in .env too
        assertEquals(expectedURL, driver.getCurrentUrl());
        driver.quit();
    }

//    @Then("I should be redirected to the homepage")
//    public void i_should_be_redirected_to_the_homepage() {
//        String expectedURL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
//        assertEquals(expectedURL, driver.getCurrentUrl());
//        driver.quit();
//    }

}
