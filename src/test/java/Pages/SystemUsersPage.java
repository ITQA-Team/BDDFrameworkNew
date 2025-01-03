package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SystemUsersPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
    private WebElement usernameField;
    

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")
    private WebElement resetButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[1]/button")
    private WebElement addButton;
    
    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div/label/span/i")
    private WebElement userCheckbox;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/div/button")
    private WebElement deleteButton;

    @FindBy(xpath = "//*[@id=\"app\"]/div[3]/div/div/div/div[3]/button[2]")
    private WebElement confirmDeleteButton;

    @FindBy(xpath = "//div[contains(@class,'success-message')]")
    //private WebElement successMessage;
    
 // Web elements
    private By new_usernameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[4]/div/div[2]/input");
    private By roleDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
    private By employeeNameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[2]/div/div[2]/div/div/input");
    private By new_passwordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]/input");
    private By confirmPasswordField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]/input");
    private By saveButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[3]/button[2]");
    private By successMessage = By.id("successMessage");

    // Constructor
    public SystemUsersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods with clear context
    public void enterUsernameForSystemUsers(String username) {
        usernameField.clear();
        usernameField.sendKeys(username);
    }
    

    public void clickSearchButtonForSystemUsers() {
        searchButton.click();
    }

    public void clickResetButtonForSystemUsers() {
        resetButton.click();
    }

    public void selectUserRoleForSystemUsers(String role) {
        userRoleDropdown.sendKeys(role);
    }

    public void selectStatusForSystemUsers(String status) {
        statusDropdown.sendKeys(status);
    }

    public void clickAddButtonForSystemUsers() {
        addButton.click();
    }
    
    public void selectUser(String username) {
    	// Use WebDriverWait to ensure the element is present and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       
        // Wait until the checkbox is clickable
        WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[3]/div/div[2]/div/div/div[1]/div/div/label/span"));
        // Click the checkbox if not already selected
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void clickDeleteButton() {
        deleteButton.click();
    }

    public void confirmDeletion() {
        confirmDeleteButton.click();
    }

    /*public String getSuccessMessage() {
        return successMessage.getText();
    }*/
    
 // Methods for add new user 

    public void new_enterUsername(String username) {
        driver.findElement(new_usernameField).sendKeys(username);
    }

    public void selectRole(String role) {
        WebElement roleElement = driver.findElement(roleDropdown);
        roleElement.sendKeys(role);
    }

    public void enterEmployeeName(String employeeName) {
        driver.findElement(employeeNameField).sendKeys(employeeName);
    }

    public void new_enterPassword(String password) {
        driver.findElement(new_passwordField).sendKeys(password);
    }

    public void confirmPassword(String password) {
        driver.findElement(confirmPasswordField).sendKeys(password);
    }

    public void clickSaveButton() {
        driver.findElement(saveButton).click();
    }

    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }
}
