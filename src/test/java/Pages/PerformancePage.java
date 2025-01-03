package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import java.util.List;



public class PerformancePage {
    WebDriver driver;


    private final WebDriverWait wait;



    // Locators for Performance Module
    // Locators for Performance Module
    private final By performanceModule = By.xpath("//span[text()='Performance']");

   private final By employeeNameField = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div[1]/div/div[2]/div/div/input");
    private final By jobTitleDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div[2]/div/div[2]/div/div/div[2]");
    private final By jobTitleOption = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div[2]/div/div[2]/div/div/div[2]");
    private final By statusDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div[5]/div/div[2]/div/div/div[1]");
    private final By searchButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]");
    private final By resetButton = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]");
    private final By reviewRecord = By.xpath("//div[contains(@class,'oxd-table-row')]//div[contains(text(),'%s')]");
    private final By noRecordsMessage = By.xpath("//span[text()='No Records Found']");



    public PerformancePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Navigate to Performance Module
    public void navigateToPerformanceModule() {
        wait.until(ExpectedConditions.elementToBeClickable(performanceModule)).click();
    }


    // Select Job Title
    public void selectEmployeeName(String employeeName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(employeeNameField));
        dropdown.click();
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(employeeName, employeeNameField))));
        option.click();
    }



    // Click Search Button
    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }


    // Verify if Review Record is Displayed
    public boolean isReviewDisplayed(String employeeName) {
        try {
            WebElement record = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(employeeName, reviewRecord))));
            return record.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Verify No Records Found Message
    public boolean isNoRecordsMessageDisplayed() {
        try {
            WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(noRecordsMessage));
            return message.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public void enterEmployeeName(String employeeName) {
        WebElement nameField = driver.findElement(employeeNameField);
        nameField.clear();
        nameField.sendKeys(employeeName);
    }


public void selectJobTitle(String jobTitle) {
    try {
        // Locate and click the dropdown to expand options
        WebElement dropdown = driver.findElement(By.xpath("//div[contains(@class,'oxd-select-text')]"));
        dropdown.click();

        // Wait for the options to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Find all available options and print them for debugging
        List<WebElement> allOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@role='option']")
        ));

        System.out.println("Available options:");
        for (WebElement element : allOptions) {
            System.out.println(element.getText());
        }

        System.out.println("Available options:");
        for (WebElement element : allOptions) {
            System.out.println(element.getText());
        }

        // Try multiple possible XPath variations
        String[] xpathAttempts = {
                "//div[@role='option' and contains(., '" + jobTitle + "')]",
                "//div[@role='option' and normalize-space()='" + jobTitle + "']",
                "//div[@role='option']//span[contains(., '" + jobTitle + "')]"
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
            // Try JavaScript click if regular click doesn't work
            try {
                option.click();
            } catch (ElementClickInterceptedException e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", option);
            }
        } else {
            throw new NoSuchElementException("Could not find option: " + jobTitle);
        }

    } catch (Exception e) {
        System.out.println("Error during selection: " + e.getMessage());
        throw e;
    }
}
    public void selectReviewStatus(String status) {
        try {
            // Locate and click the dropdown to expand options
            WebElement dropdown = driver.findElement(By.xpath("//*[@id='app']/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div[5]/div/div[2]/div/div/div[1]"));
            dropdown.click();

            // Wait for the options to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Find all available options and print them for debugging
            List<WebElement> allOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//div[@role='option']")
            ));

            System.out.println("Available Review Status options:");
            for (WebElement element : allOptions) {
                System.out.println(element.getText());
            }

            // Try multiple possible XPath variations
            String[] xpathAttempts = {
                    "//div[@role='option' and contains(., '" + status + "')]",
                    "//div[@role='option' and normalize-space()='" + status + "']",
                    "//div[@role='option']//span[contains(., '" + status + "')]"
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
                // Try JavaScript click if regular click doesn't work
                try {
                    option.click();
                } catch (ElementClickInterceptedException e) {
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].click();", option);
                }
            } else {
                throw new NoSuchElementException("Could not find status option: " + status);
            }

        } catch (Exception e) {
            System.out.println("Error during status selection: " + e.getMessage());
            throw e;
        }
    }

    public void clickResetButton() {
        try {
            // Locate the Reset button using the locator and wait until it's clickable
            WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(resetButton));

            // Perform a click on the Reset button
            resetBtn.click();
        } catch (Exception e) {
            System.out.println("Error while clicking the Reset button: " + e.getMessage());
            throw e;
        }
    }



}
