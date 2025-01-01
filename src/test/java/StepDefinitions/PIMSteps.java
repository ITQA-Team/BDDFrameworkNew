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

    @Then("user will be redirected to the employee details page.")
    public void userWillBeRedirectedToTheEmployeeDetailsPage() {
        // Use the isEmployeeDetailsPage method from the PIMPage class
        PIMPage pimPage = new PIMPage(driver);
        boolean isOnDetailsPage = pimPage.isEmployeeDetailsPage();

        // Assert that the user is on the employee details page
        Assert.assertTrue(isOnDetailsPage,
                "User was not redirected to the employee details page.");
    }




    @Then("an error message should appear for the missing fields")
    public void anErrorMessageShouldAppearForTheMissingFields() {
        PIMPage pimPage = new PIMPage(driver);
        Assert.assertTrue(
                pimPage.isMessageDisplayed("Required"),
                "Required error message not displayed as expected"
        );
    }


    @Then("a message indicating no record were found should be displayed")
    public void aMessageIndicatingNoRecordWereFoundShouldBeDisplayed() {
        PIMPage pimPage = new PIMPage(driver);
        Assert.assertTrue(
                pimPage.isMessageDisplayed("No Records Found"),
                "No Records Found message not displayed as expected"
        );
    }

    @When("I click on the Delete button")
    public void iClickOnTheDeleteButton() {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickDeleteButton();  // calling the function to click on the button
    }

    @And("I confirm the deletion")
    public void iConfirmTheDeletion() {
            PIMPage pimPage = new PIMPage(driver);
            pimPage.confirmDelete();  // calling the function to click on the confirm
    }


    @And("I click on the Edit button")
    public void iClickOnTheEditButton() {
        PIMPage pimPage = new PIMPage(driver);
        pimPage.clickEditButton();
    }
    private Map<String, String> updatedEmployeeDetails; // Global variable
    @When("I enter updated  employee's details:")
    public void enterUpdatedEmployeeDetails(DataTable updatedemployeeData) {
        List<Map<String, String>> data = updatedemployeeData.asMaps(String.class, String.class);
        Map<String, String> UpdatedEmployeeDetails = data.get(0);

        updatedEmployeeDetails = data.get(0);

        PIMPage pimPage = new PIMPage(driver);
        pimPage.enterUpdatedEmployeeDetails(
                UpdatedEmployeeDetails.get("firstName"),
                UpdatedEmployeeDetails.get("middleName"),
                UpdatedEmployeeDetails.get("lastName")

        );
    }


    @Then("the employee details should be updated successfully")
    public void verifyEmployeeDetailsUpdated() {
        try {
            // Make sure we have the details
            if (updatedEmployeeDetails == null) {
                throw new AssertionError("No employee details were provided for verification");
            }

            PIMPage pimPage = new PIMPage(driver);

            boolean isUpdated = pimPage.isEmployeeDetailsUpdated(
                    updatedEmployeeDetails.get("firstName"),
                    updatedEmployeeDetails.get("middleName"),
                    updatedEmployeeDetails.get("lastName")
            );

            // TestNG assertion syntax: Assert.assertTrue(condition, message)
            Assert.assertTrue(isUpdated, "Updated employee details were not found on the page");

        } catch (Exception e) {
            Assert.fail("Error verifying employee details: " + e.getMessage());
        }
    }
}
