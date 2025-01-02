package StepDefinitions;

import io.cucumber.java.en.*;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.time.Duration;

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
        // Log in if redirected to login page
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
}
