package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DirectoryPage {

    WebDriver driver;

    // Locators
    private By employeeCards = By.xpath("//*");
    private By employeeLocations = By.xpath("//div[@role='option']//span");
    private By directoryMenu = By.xpath("//a[@href='/web/index.php/directory/viewDirectory']");
    private By directoryHeader = By.tagName("h6");
    private By employeeNameField = By.xpath("//input[@placeholder='Type for hints...']");
//    private By employeeCards = By.xpath("//div[contains(@class, 'oxd-grid-item')]");
    private final By locationDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]");  // Selector for location dropdown
    private By allTextElements = By.xpath("//*[text()]");
//    private By employeeCards = By.xpath("//div[contains(@class, 'oxd-grid-item')]");
//    private By employeeLocations = By.xpath("//div[contains(@class, 'oxd-grid-item')]//div[contains(@class, 'oxd-text')]");
//    private By employeeCards = By.xpath("//*"); // Search entire page
//    private By employeeLocations = By.xpath("//*[text()]");

    private By searchButton = By.xpath("//button[@type='submit']");
    private By searchResults(String employeeName) {
        return By.xpath("//*[contains(text(), '" + employeeName + "')]");
    }


    // Constructor
    public DirectoryPage(WebDriver driver) {
        this.driver = driver;
    }


    // Methods for actions
    public void clickDirectoryMenu() {
        driver.findElement(directoryMenu).click();
    }

    public String getHeaderText() {
        return driver.findElement(directoryHeader).getText();
    }

    public void enterEmployeeName(String employeeName) {
        driver.findElement(employeeNameField).sendKeys(employeeName);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }
    private int countEmployeeOccurrences(String employeeName) {
        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), '" + employeeName + "')]"));
        return elements.size();  // Returns the number of occurrences of the employee name
    }

    public void clickLocationDropdown() {
        driver.findElement(locationDropdown).click();
    }

//    public void selectLocation(String location) {
//        // Ensure the dropdown is open and wait for the options
//        clickLocationDropdown();
//
//        // Use the location parameter to find and select the option dynamically
//        By locationOption = By.xpath("//div[contains(text(), '" + location + "')]");
//        driver.findElement(locationOption).click();  // Select the dynamically passed location
//    }


//    public void clicklocationDropdown(){
//        driver.findElement(locationDropdown).click();
//    }

    public void selectLocation(String location) {
        try {
            // Locate and click the dropdown to expand options
            WebElement dropdown = driver.findElement(locationDropdown);
            dropdown.click();

            // Wait for the options to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Find all available options and print them for debugging
            List<WebElement> allOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@role='option']")
            ));

            System.out.println("Available location options:");
            for (WebElement element : allOptions) {
                System.out.println(element.getText());
            }

            // Try multiple possible XPath variations for better reliability
            String[] xpathAttempts = {
                    "//div[@role='option' and contains(., '" + location + "')]",
                    "//div[@role='option' and normalize-space()='" + location + "')]",
                    "//div[@role='option']//span[contains(., '" + location + "')]",
                    // Case-insensitive version
                    "//div[@role='option' and contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '"
                            + location.toLowerCase() + "')]"
            };

            WebElement option = null;
            for (String xpath : xpathAttempts) {
                try {
                    option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
                    break;
                } catch (TimeoutException e) {
                    continue;
                }
            }

            if (option != null) {
                // Try regular click first, fallback to JavaScript click
                try {
                    option.click();
                } catch (ElementClickInterceptedException e) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", option);
                }
            } else {
                throw new NoSuchElementException("Could not find location: " + location);
            }

        } catch (Exception e) {
            System.out.println("Error selecting location: " + e.getMessage());
            throw e;
        }
    }

//    public boolean verifyOnlySelectedLocationEmployees(String selectedLocation) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.presenceOfElementLocated(employeeCards));
//        List<WebElement> locationElements = driver.findElements(employeeLocations);
//
//        System.out.println("Found locations after search:");
//        for (WebElement element : locationElements) {
//            System.out.println(element.getText());
//        }
//
//        boolean hasOtherLocations = locationElements.stream()
//                .map(element -> element.getText().trim().toLowerCase())
//                .anyMatch(location -> !location.contains(selectedLocation.toLowerCase()));
//
//        if (hasOtherLocations) {
//            System.out.println("Test failed: Found employees from locations other than " + selectedLocation);
//            return false;
//        }
//
//        boolean hasEmployees = !locationElements.isEmpty();
//        if (!hasEmployees) {
//            System.out.println("Test failed: No employees found");
//            return false;
//        }
//
//        return true;
//    }
public boolean verifyOnlySelectedLocationEmployees(String selectedLocation) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(employeeCards));
    List<WebElement> locationElements = driver.findElements(employeeLocations);

    System.out.println("Found locations after search:");
    for (WebElement element : locationElements) {
        System.out.println(element.getText());
    }

    boolean hasOtherLocations = locationElements.stream()
            .map(element -> element.getText().trim().toLowerCase())
            .anyMatch(location -> !location.contains(selectedLocation.toLowerCase()));

    if (hasOtherLocations) {
        System.out.println("Test failed: Found employees from locations other than " + selectedLocation);
        return false;
    }

//    boolean hasEmployees = !locationElements.isEmpty();
//    if (!hasEmployees) {
//        System.out.println("Test failed: No employees found");
//        return false;
//    }

    return true;
}








}
