package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.MyInfoPage;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MyInfoSteps {

    WebDriver driver;
    MyInfoPage myInfoPage;

    @Given("I am on the personal details page")
    public void i_am_on_the_personal_details_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".orangehrm-login-button"));
        usernameField.sendKeys("Admin"); // Replace with valid username
        passwordField.sendKeys("admin123"); // Replace with valid password
        loginButton.click();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/7");
        myInfoPage = new MyInfoPage(driver);
    }

    @When("I enter first name {string}")
    public void i_enter_first_name(String firstName) {
        myInfoPage.enterFirstName(firstName);
    }

    @When("I enter last name {string}")
    public void i_enter_last_name(String lastName) {
        myInfoPage.enterLastName(lastName);
    }

    @When("I click on save button")
    public void i_click_on_save_button() {
        myInfoPage.clickSave();
    }

    @Then("The first name and last name should be updated successfully")
    public void the_first_name_and_last_name_should_be_updated_successfully() {
        String expectedFirstName = "John";
        String expectedLastName = "Doe";
        assertTrue(myInfoPage.verifyUpdatedDetails(expectedFirstName, expectedLastName));
        driver.quit();
    }
}
