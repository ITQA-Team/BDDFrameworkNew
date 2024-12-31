package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import Pages.LoginPage;
import Pages.PIMPage;
import java.util.List;
import java.time.Duration;
import java.util.Map;
import org.testng.Assert;

import static org.junit.Assert.assertTrue;

public class PIMSteps {

    WebDriver driver;
    LoginPage loginPage;
    PIMPage pimPage;

    @Given("I am logged in as an admin")
    public void i_am_logged_in_as_an_admin() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage(driver);
        pimPage = new PIMPage(driver);

        loginPage.enterUsername("Admin");
        loginPage.enterPassword("admin123");
        loginPage.clickLogin();
    }

    @When("I navigate to the PIM module")
    public void i_navigate_to_the_pim_module() {
        pimPage.navigateToPIMModule();
    }

    @When("I click on {string}")
    public void i_click_on(String buttonText) {
        if (buttonText.equals("Add")) {
            pimPage.clickAddEmployee();
        }
        else if (buttonText.equals("Search")) {
            pimPage.clickSearchButton();
        }
    }

    @When("I enter employee details:")
    public void i_enter_employee_details(DataTable employeeData) {
        List<Map<String, String>> data = employeeData.asMaps(String.class, String.class);
        Map<String, String> employeeDetails = data.get(0);

        pimPage.enterEmployeeDetails(
                employeeDetails.get("firstName"),
                employeeDetails.get("middleName"),
                employeeDetails.get("lastName"),
                employeeDetails.get("employeeId")
        );
    }

    @When("I save the employee")
    public void i_save_the_employee() {
        pimPage.saveEmployee();
    }

    @When("I enter the following employee ID:")
    public void i_enter_the_employee_id(io.cucumber.datatable.DataTable dataTable) {
        String employeeId = dataTable.asMaps().get(0).get("employeeId");
        pimPage.searchEmployeeById(employeeId);
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Search")) {
            pimPage.clickSearchButton();
        }
    }

    @Then("the employee with ID {string} should appear in the employee list")
    public void the_employee_with_id_should_appear_in_the_employee_list(String employeeId) {
        Assert.assertTrue(pimPage.isEmployeeDisplayed(employeeId),
                "Employee with ID " + employeeId + " was not found in the list");
    }







//

}
