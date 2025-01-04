package StepDefinitions;


import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoginPage;
import Pages.PerformancePage;

import java.time.Duration;

public class PerformanceSteps {

    WebDriver driver;
    LoginPage loginPage;
    PerformancePage performancePage;

    @Given("User is on the Employee Reviews page")
    public void i_am_logged_in_as_an_admin() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        performancePage = new PerformancePage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/performance/searchEvaluatePerformanceReview");
    }


    @When("User enters {string} in the Employee Name field")
    public void userEntersInTheEmployeeNameField(String employeeName) {
        performancePage.enterEmployeeName(employeeName);
    }

    @When("User clicks the Search button for Employee Reviews")
    public void userClicksTheSearchButtonForEmployeeReviews() {
        performancePage.clickSearchButton();
    }

    @Then("System displays the search results for Employee Reviews")
    public void systemDisplaysTheSearchResultsForEmployeeReviews() {
    }


    @When("User select {string} from the Job Title dropdown")
    public void userSelectsJobTitleFromTheDropdown(String jobTitle) {
        performancePage.selectJobTitle(jobTitle);
    }



       @When("User selects {string} from the Review Status dropdown")
    public void userSelectsReviewStatus(String Status) {
       performancePage.selectReviewStatus(Status);
  }

    @When("User clicks the Reset button on the search form")
    public void userClicksTheResetButtonOnTheSearchForm() {
        performancePage.clickResetButton();
    }

    @Then("All fields in the search form are cleared to their default values")
    public void allFieldsInTheSearchFormAreClearedToTheirDefaultValues() {

    }
}

        










