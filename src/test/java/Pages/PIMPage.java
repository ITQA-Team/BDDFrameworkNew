package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import io.cucumber.datatable.DataTable;
import java.util.Map;

public class PIMPage {
    WebDriver driver;
    private WebDriverWait wait;

    // Existing locators...
    private By pimModule = By.xpath("//span[text()='PIM']");
    private By addEmployeeButton = By.xpath("//button[contains(@class,'oxd-button--secondary')]//i[contains(@class,'bi-plus')]/parent::button");
    private By firstNameField = By.cssSelector("input.orangehrm-firstname");
    private By middleNameField = By.cssSelector("input.orangehrm-middlename");
    private By lastNameField = By.cssSelector("input.orangehrm-lastname");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/../..//input");
    private By saveButton = By.xpath("//button[@type='submit' and contains(.,'Save')]");
    private By employeeInList = By.xpath("//td[contains(text(), '%s')]");
    private final By searchEmployeeIdField = By.xpath("//label[text()='Employee Id']/../..//input[contains(@class, 'oxd-input')]");
    private final By searchButton = By.cssSelector("button[type='submit'].oxd-button--secondary.orangehrm-left-space");
//    private final String employeeRecordXPath = "//div[contains(@class, 'oxd-table-row')]//div[contains(text(), '%s')]";
    private By errorMessage = By.xpath("//span[contains(@class,'oxd-text') and text()='Required']");
    private By noRecordsFoundMessage = By.xpath("//span[text()='No Records Found']");
    private By employeeRow = By.xpath("//td[contains(text(),'%s')]");
    private final String employeeRecordXPath = "//div[contains(@class, 'oxd-table-card')]//div[@class='data'][text()='%s']";

    private String firstName;
    private String middleName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }


    public PIMPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Existing navigation methods...
    public void navigateToPIMModule() {
        wait.until(ExpectedConditions.elementToBeClickable(pimModule)).click();
    }

    public void clickAddEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton)).click();
    }

    public void enterEmployeeDetails(String firstName, String middleName, String lastName, String employeeId) {
        WebElement firstNameInput = wait.until(ExpectedConditions.elementToBeClickable(firstNameField));
        WebElement middleNameInput = wait.until(ExpectedConditions.elementToBeClickable(middleNameField));
        WebElement lastNameInput = wait.until(ExpectedConditions.elementToBeClickable(lastNameField));
        WebElement employeeIdInput = wait.until(ExpectedConditions.elementToBeClickable(employeeIdField));

        // Clear and send values (even if empty)
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName != null ? firstName : "");

        middleNameInput.clear();
        middleNameInput.sendKeys(middleName != null ? middleName : "");

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName != null ? lastName : "");

        employeeIdInput.clear();
        employeeIdInput.sendKeys(employeeId != null ? employeeId : "");
    }

    public void saveEmployee() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton)).click();
    }

    public void searchEmployeeById(String employeeId) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchEmployeeIdField));
        searchField.clear();
        searchField.sendKeys(employeeId);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public boolean isEmployeeDisplayed(String employeeId) {
        try {
            Thread.sleep(5000);
            return !driver.findElements(By.xpath("//*[contains(text(),'" + employeeId + "')]")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isEmployeeDetailsPage() {
        try {
            Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/"));

            String expectedUrlStart = "demo.orangehrmlive.com/web/index.php/pim/viewPersonalDetails/empNumber/";
            String actualUrl = driver.getCurrentUrl();
            return actualUrl.contains(expectedUrlStart);
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isMessageDisplayed(String message) {
        try {
            Thread.sleep(5000);
            String pageSource = driver.getPageSource();
            return pageSource.contains(message);
        } catch (Exception e) {
            return false;
        }
    }

    public void clickDeleteButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-icon-button .bi-trash")
        ));
        deleteButton.click();
    }

    public void confirmDelete() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-button--label-danger .bi-trash")
        ));
        confirmButton.click();
    }

    public void clickEditButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.oxd-icon-button .bi-pencil-fill")
        ));
        editButton.click();
    }

    public void enterUpdatedEmployeeDetails(String firstName, String middleName, String lastName) {


        WebElement firstNameField = driver.findElement(By.className("orangehrm-firstname"));
        WebElement middleNameField = driver.findElement(By.className("orangehrm-middlename"));
        WebElement lastNameField = driver.findElement(By.className("orangehrm-lastname"));


        // Clear and send values (even if empty)
        firstNameField.clear();
        firstNameField.sendKeys(firstName != null ? firstName : "");

        middleNameField.clear();
        middleNameField.sendKeys(middleName != null ? middleName : "");

        lastNameField.clear();
        lastNameField.sendKeys(lastName != null ? lastName : "");

        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;

    }

    public boolean isEmployeeDetailsUpdated(String firstName, String middleName, String lastName) {
        try {
            // Wait for the page to load
            Thread.sleep(2000);

            // Get the entire page content
            String pageContent = driver.findElement(By.tagName("body")).getText();

            // Check if each name (if not null) exists in the page content
            boolean isFirstNameFound = (firstName == null) || pageContent.contains(firstName);
            boolean isMiddleNameFound = (middleName == null) || pageContent.contains(middleName);
            boolean isLastNameFound = (lastName == null) || pageContent.contains(lastName);

            // For debugging
            System.out.println("Checking names in page content:");
            System.out.println("First Name '" + firstName + "' found: " + isFirstNameFound);
            System.out.println("Middle Name '" + middleName + "' found: " + isMiddleNameFound);
            System.out.println("Last Name '" + lastName + "' found: " + isLastNameFound);

            // Return true if all provided names are found
            return isFirstNameFound && isMiddleNameFound && isLastNameFound;

        } catch (Exception e) {
            System.out.println("Error checking employee details: " + e.getMessage());
            return false;
        }
    }






}