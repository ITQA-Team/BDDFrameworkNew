package StepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoginPage;
import Pages.DirectoryPage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

//import static org.junit.Assert.assertEquals;
import static org.testng.Assert.*;


public class DirectorySteps {

    WebDriver driver;
    LoginPage loginPage;
    DirectoryPage directoryPage;



    // Login into OrangeHRM
    @Given("User logs into the OrangeHRM application")
    public void user_logs_into_the_OrangeHRM_application() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        // Login
        loginPage = new LoginPage(driver);
        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();

        // Initialize Directory Page
        directoryPage = new DirectoryPage(driver);
    }

    // Click Directory Menu
    @When("User clicks on the Directory menu")
    public void user_clicks_on_the_directory_menu() {
        directoryPage.clickDirectoryMenu();
    }

    // Verify Directory Page Header
    @Then("User should see the Directory page")
    public void user_should_see_the_directory_page() {
        String expectedHeader = "Directory";
        assertEquals(expectedHeader, directoryPage.getHeaderText());
    }

    // Ensure User is on Directory Page
    @Given("User is on the Directory page")
    public void user_is_on_the_directory_page() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

            loginPage = new LoginPage(driver);
            loginPage.enterUsername("Admin");
            loginPage.enterPassword("admin123");
            loginPage.clickLogin();
        }

        if (directoryPage == null) {
            directoryPage = new DirectoryPage(driver);
        }
        directoryPage.clickDirectoryMenu();
    }

    // Enter Employee Name
    @When("User enters {string} in the employee name field")
    public void user_enters_employee_name(String employeeName) {
        directoryPage.enterEmployeeName(employeeName);
    }

    // Click Search Button
    @When("User clicks the search button")
    public void user_clicks_the_search_button() {
        directoryPage.clickSearchButton();
    }

    private int countEmployeeOccurrences(String employeeName) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + employeeName + "')]"));
        return elements.size();
    }

    @Then("System displays results for {string}")
    public void system_displays_results_for_employee_name(String employeeName) {
        int occurrenceCount = countEmployeeOccurrences(employeeName);
        assertEquals(occurrenceCount, 2, "The employee name should appear exactly twice on the page.");
        driver.quit();
    }

    @When("User selects {string} as the location")
    public void user_selects_location(String location) {
        // Use the complete selectLocation method instead of just clicking the dropdown
        directoryPage.selectLocation(location);
    }


//    @Then("System displays employees in {string}")
//    public void system_displays_employees_in_location(String location) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//        // Define the locator for employee cards/rows
//        By employeeList = By.xpath("//div[contains(@class, 'employee-card')]"); // Adjust selector based on your HTML
//
//        // Wait for employee results to be visible
//        wait.until(ExpectedConditions.presenceOfElementLocated(employeeList));
//
//        List<WebElement> employees = driver.findElements(employeeList);
//
//        // Verify we have results
//        assertFalse(employees.isEmpty(), "No employee results found");
//
//        // Check if at least one employee has the correct location
//        boolean found = employees.stream()
//                .anyMatch(employee -> employee.getText().toLowerCase()
//                        .contains(location.toLowerCase()));
//
//        assertTrue(found, "No employees in location '" + location + "' were found.");
//    }

    // In your steps file:
    @Then("System displays employees in {string}")
    public void system_displays_employees_in_location(String location) {
        assertTrue(directoryPage.verifyOnlySelectedLocationEmployees(location),
                "Either no employees found or employees from other locations were present");
    }
}