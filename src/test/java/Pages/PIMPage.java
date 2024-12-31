package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PIMPage {
    WebDriver driver;
    private WebDriverWait wait;

    // Locators for PIM Module
    private By pimModule = By.xpath("//span[text()='PIM']");
    private By addEmployeeButton = By.xpath("//button[contains(@class,'oxd-button--secondary')]//i[contains(@class,'bi-plus')]/parent::button");

    private By firstNameField = By.cssSelector("input.orangehrm-firstname");
    private By middleNameField = By.cssSelector("input.orangehrm-middlename");
    private By lastNameField = By.cssSelector("input.orangehrm-lastname");
    private By employeeIdField = By.xpath("//label[text()='Employee Id']/../..//input");
    private By saveButton = By.xpath("//button[@type='submit' and contains(.,'Save')]");
    private By employeeInList = By.xpath("//td[contains(text(), '%s')]");
    private final By searchField = By.cssSelector("input.oxd-input.oxd-input--active");
//    private final By searchButton = By.cssSelector("button[type='submit'].oxd-button--secondary");
    private final String employeeRowTemplate = "//div[contains(@class,'oxd-table-row')]//div[contains(text(),'%s')]";

    public PIMPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to navigate to PIM Module
    public void navigateToPIMModule() {
        WebElement pimLink = driver.findElement(pimModule);
        pimLink.click();
    }

    // Method to click on "Add Employee"
    public void clickAddEmployee() {
        WebElement addEmployeeBtn = driver.findElement(addEmployeeButton);
        addEmployeeBtn.click();
    }

    // Method to enter employee details
    public void enterEmployeeDetails(String firstName, String middleName, String lastName, String employeeId) {
        WebElement firstNameInput = driver.findElement(firstNameField);
        WebElement middleNameInput = driver.findElement(middleNameField);
        WebElement lastNameInput = driver.findElement(lastNameField);
        WebElement employeeIdInput = driver.findElement(employeeIdField);

        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);

        middleNameInput.clear();
        middleNameInput.sendKeys(middleName);

        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);

        employeeIdInput.clear();
        employeeIdInput.sendKeys(employeeId);
    }

    // Method to save the employee
    public void saveEmployee() {
        WebElement saveBtn = driver.findElement(saveButton);
        saveBtn.click();
    }

    private final By searchEmployeeIdField = By.cssSelector("input.oxd-input.oxd-input--active");
    private final By searchButton = By.cssSelector("button[type='submit'].oxd-button--secondary.orangehrm-left-space");
    private final String employeeRecordXPath = "//div[contains(@class, 'oxd-table-row')]//div[contains(text(), '%s')]";


    public void searchEmployeeById(String employeeId) {
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(searchEmployeeIdField));
        searchField.clear();
        searchField.sendKeys(employeeId);
    }

    public void clickSearchButton() {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        search.click();
    }

    public boolean isEmployeeDisplayed(String employeeId) {
        String xpath = String.format(employeeRecordXPath, employeeId);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }











}
