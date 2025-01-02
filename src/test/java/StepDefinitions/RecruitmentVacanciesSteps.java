package StepDefinitions;

import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.*;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import Pages.RecruitmentVacanciesPage;

public class RecruitmentVacanciesSteps {
    WebDriver driver;
    RecruitmentVacanciesPage recruitmentVacanciesPage;

    public RecruitmentVacanciesSteps() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe"); // Set correct path
        this.driver = new ChromeDriver();
        recruitmentVacanciesPage = PageFactory.initElements(driver, RecruitmentVacanciesPage.class);
    }

    @Given("User is on the Recruitment Vacancies page")
    public void userIsOnTheRecruitmentVacanciesPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        WebElement usernameField = driver.findElement(By.name("username"));
        WebElement passwordField = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.cssSelector(".orangehrm-login-button"));

        usernameField.sendKeys("Admin"); // Replace with valid username
        passwordField.sendKeys("admin123"); // Replace with valid password
        loginButton.click();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/recruitment/viewJobVacancy");
    }

    @When("User selects {string} from the Job Title dropdown")
    public void userSelectsJobTitleFromTheDropdown(String jobTitle) {
        recruitmentVacanciesPage.selectJobTitle(jobTitle);
    }

    @When("User selects {string} from the Vacancy dropdown")
    public void userSelectsVacancyFromTheDropdown(String vacancy) {
        recruitmentVacanciesPage.selectVacancy(vacancy);
    }

    @When("User selects {string} from the Hiring Manager dropdown")
    public void userSelectsHiringManagerFromTheDropdown(String hiringManager) {
        recruitmentVacanciesPage.selectHiringManager(hiringManager);
    }

    @When("User selects {string} from the Status dropdown")
    public void userSelectsStatusFromTheDropdown(String status) {
        recruitmentVacanciesPage.selectStatus(status);
    }

    @When("User clicks the Search button")
    public void userClicksSearchButton() {
        recruitmentVacanciesPage.clickSearchButton();
    }

    @Then("The results should display the vacancies matching the criteria")
    public void resultsShouldDisplayVacanciesMatchingCriteria() {
        // Add assertions to verify that the displayed vacancies match the search criteria
    }
    @Then("The results should display vacancies that match all the selected criteria")
    public void the_results_should_display_vacancies_that_match_all_the_selected_criteria() {
        List<WebElement> results = driver.findElements(By.cssSelector(".result-row"));

        for (WebElement result : results) {
            String jobTitle = result.findElement(By.cssSelector(".job-title")).getText();
            String vacancy = result.findElement(By.cssSelector(".vacancy")).getText();
            String hiringManager = result.findElement(By.cssSelector(".hiring-manager")).getText();
            String status = result.findElement(By.cssSelector(".status")).getText();

            assertTrue(jobTitle.equals("Account Assistant"), "Job Title should match the selected value");
            assertTrue(vacancy.equals("Vacancy Name"), "Vacancy should match the selected value");
            assertTrue(hiringManager.equals("John Doe"), "Hiring Manager should match the selected value");
            assertTrue(status.equals("Active"), "Status should match the selected value");
        }
    }

    @When("User applies filters with criteria that have no matching records")
    public void userAppliesFiltersWithNoMatchingCriteria() {
        recruitmentVacanciesPage.selectJobTitle("ABC"); // Replace with invalid value
        recruitmentVacanciesPage.selectVacancy("ABC"); // Replace with invalid value
        recruitmentVacanciesPage.selectHiringManager("ABC"); // Replace with invalid value
        recruitmentVacanciesPage.selectStatus("Inactive"); // Replace with invalid value
        recruitmentVacanciesPage.clickSearchButton();
    }

    @Then("No records should be displayed")
    public void noRecordsShouldBeDisplayed() {
        List<WebElement> results = driver.findElements(By.cssSelector(".result-row"));
        assertTrue(results.isEmpty(), "Results are displayed when none were expected.");
    }
    @When("User clicks the Reset button")
    public void userClicksResetButton() {
        recruitmentVacanciesPage.clickResetButton();
    }

    @Then("All records should be displayed")
    public void allRecordsShouldBeDisplayed() {
        List<WebElement> results = driver.findElements(By.cssSelector(".result-row"));
        assertFalse(results.isEmpty(), "Records are not displayed after reset.");
    }

    @Then("All dropdowns should display --Select--")
    public void allDropdownsShouldDisplaySelect() {
        List<WebElement> results = driver.findElements(By.cssSelector(".result-row"));
        for (WebElement result : results) {
            String jobTitle = result.findElement(By.cssSelector(".job-title")).getText();
            String vacancy = result.findElement(By.cssSelector(".vacancy")).getText();
            String hiringManager = result.findElement(By.cssSelector(".hiring-manager")).getText();
            String status = result.findElement(By.cssSelector(".status")).getText();
            assertEquals(recruitmentVacanciesPage.getJobTitleDropdownSelectedOption(), "-- Select --", jobTitle);
            assertEquals(recruitmentVacanciesPage.getVacancyDropdownSelectedOption(), "-- Select --", vacancy);
            assertEquals(recruitmentVacanciesPage.getHiringManagerDropdownSelectedOption(), "-- Select --", hiringManager);
            assertEquals(recruitmentVacanciesPage.getStatusDropdownSelectedOption(), "-- Select --", status);

        }

    }

    @Then("The test environment is cleaned up")
    public void cleanUpEnvironment() {
        if (driver != null) {
            driver.quit();
        }
    }

}
