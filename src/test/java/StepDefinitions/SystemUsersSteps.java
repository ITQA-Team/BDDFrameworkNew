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
import Pages.SystemUsersPage;

public class SystemUsersSteps {
    WebDriver driver;
    SystemUsersPage systemUsersPage;

    public SystemUsersSteps() {
    	System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe"); // Set correct path
        this.driver = new ChromeDriver();
        systemUsersPage = PageFactory.initElements(driver, SystemUsersPage.class);
    }

    @Given("User is on the System Users page")
    public void userIsOnTheSystemUsersPage() {
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
        
        // Navigate to the System Users page
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    @When("User enters {string} in the username field for System Users")
    public void userEntersUsernameForSystemUsers(String username) {
        systemUsersPage.enterUsernameForSystemUsers(username);
    }
    

    @When("User clicks the search button for System Users")
    public void userClicksSearchButtonForSystemUsers() {
        systemUsersPage.clickSearchButtonForSystemUsers();
    }

    @When("User clicks the reset button for System Users")
    public void userClicksResetButtonForSystemUsers() {
        systemUsersPage.clickResetButtonForSystemUsers();
    }

    @When("User selects {string} as the user role for System Users")
    public void userSelectsUserRoleForSystemUsers(String role) {
        systemUsersPage.selectUserRoleForSystemUsers(role);
    }

    @When("User selects {string} as the status for System Users")
    public void userSelectsStatusForSystemUsers(String status) {
        systemUsersPage.selectStatusForSystemUsers(status);
    }

    @When("User clicks the add button for System Users")
    public void userClicksAddButtonForSystemUsers() {
        systemUsersPage.clickAddButtonForSystemUsers();
    }

    @Then("System displays the search results for System Users")
    public void systemDisplaysSearchResultsForSystemUsers() {
        // Add assertions to verify search results
    }

    @Then("System resets the search fields for System Users")
    public void systemResetsSearchFieldsForSystemUsers() {
        // Add assertions to verify reset action
    }

    @Then("System allows adding a new user for System Users")
    public void systemAllowsAddingNewUserForSystemUsers() {
        // Add assertions to verify navigation or modal appearance for adding a user
    }
    
    @And("User clicks the delete button for System Users")
    public void userClicksTheDeleteButtonForSystemUsers() {
        systemUsersPage.clickDeleteButton();
    }

    @And("User confirms the deletion for System Users")
    public void userConfirmsTheDeletionForSystemUsers() {
        systemUsersPage.confirmDeletion();
    }
    /*
    @Then("System displays the message {string} for System Users")
    public void systemDisplaysTheMessageForSystemUsers(String expectedMessage) {
        String actualMessage = systemUsersPage.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage);
    }*/
    
    
    //step definition to add new user 
    
    @When("User enters {string} in the username field")
    public void user_enters_username_in_the_username(String username) {
        systemUsersPage.new_enterUsername(username);
    }

    @When("User selects {string} as the user role")
    public void user_selects_role_as_the_user_role(String role) {
        systemUsersPage.selectRole(role);
    }

    @When("User enters {string} in the employee name field for System Users")
    public void user_enters_employee_name_in_the_employee_name_field_for_system_users(String employeeName) {
        systemUsersPage.enterEmployeeName(employeeName);
    }

    @When("User enters {string} in the password field for System Users")
    public void user_enters_password_in_the_password_field_for_system_users(String password) {
        systemUsersPage.new_enterPassword(password);
    }

    @When("User confirms {string} in the confirm password field for System Users")
    public void user_confirms_password_in_the_confirm_password_field_for_system_users(String password) {
        systemUsersPage.confirmPassword(password);
    }

    @When("User clicks the save button for System Users")
    public void user_clicks_the_save_button_for_system_users() {
        systemUsersPage.clickSaveButton();
    }

    @Then("System displays the message {string} for System Users")
    public void system_displays_the_message_for_system_users(String expectedMessage) {
        String actualMessage = systemUsersPage.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
    @When("User selects the user {string}")
    public void user_selects_the_user(String username) {
        systemUsersPage.selectUser(username);
    }
}