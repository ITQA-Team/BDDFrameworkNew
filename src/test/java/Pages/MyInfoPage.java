package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyInfoPage {

    WebDriver driver;

    // Locators
    private By firstNameField = By.name("firstName");
    private By lastNameField = By.name("lastName");
    private By saveButton = By.cssSelector(".orangehrm-left-space"); // CSS selector for the Save button

    // Constructor
    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods for actions

    /**
     * Method to enter the first name.
     * @param firstName - The first name to enter
     */
    public void enterFirstName(String firstName) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    /**
     * Method to enter the last name.
     * @param lastName - The last name to enter
     */
    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    /**
     * Method to click the Save button.
     */
    public void clickSave() {
        driver.findElement(saveButton).click();
    }

    /**
     * Method to verify the updated details.
     * @return true if the updated details are visible on the page, false otherwise
     */
    public boolean verifyUpdatedDetails(String firstName, String lastName) {
//        WebElement firstNameInput = driver.findElement(firstNameField);
//        WebElement lastNameInput = driver.findElement(lastNameField);
//        String firstNameInputValue = firstNameInput.getDomAttribute("value");
//        String lastNameInputValue = lastNameInput.getDomAttribute("value");
//      //  String pageSource = driver.getPageSource();
//        return firstNameInputValue.contains(firstName) && lastNameInputValue.contains(lastName);
        String displayedFirstName = driver.findElement(By.name("firstName")).getAttribute("value");
        String displayedLastName = driver.findElement(By.name("lastName")).getAttribute("value");
        return displayedFirstName.contains(firstName) && displayedLastName.contains(lastName);
    }
}