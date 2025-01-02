package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecruitmentVacanciesPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")
    private WebElement jobTitleDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[2]/div/div[2]/div/div/div[1]")
    private WebElement vacancyDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")
    private WebElement hiringManagerDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[4]/div/div[2]/div/div/div[1]")
    private WebElement statusDropdown;

    @FindBy(xpath = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[2]")
    private WebElement searchButton;

//    private By jobDropdown = By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]");
    // Constructor
    public RecruitmentVacanciesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with the page elements
    public void selectJobTitle(String jobTitle) {

        jobTitleDropdown.sendKeys(jobTitle);
    }

    public void selectVacancy(String vacancy) {
        vacancyDropdown.sendKeys(vacancy);
    }

    public void selectHiringManager(String hiringManager) {
        hiringManagerDropdown.sendKeys(hiringManager);
    }

    public void selectStatus(String status) {
        statusDropdown.sendKeys(status);
    }

    public void clickSearchButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchButton);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));

        try {
            searchButton.click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Element click intercepted. Using JavaScript click.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchButton);
        }
    }

}
